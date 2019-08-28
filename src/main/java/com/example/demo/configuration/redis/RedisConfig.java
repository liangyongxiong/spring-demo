package com.example.demo.configuration.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.pool.max-active}")
    private int poolMaxActive;

    @Value("${spring.redis.pool.max-wait}")
    private int poolMaxWait;

    @Value("${spring.redis.pool.max-idle}")
    private int poolMaxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int poolMinIdle;

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public KeyGenerator cacheKeyGenerator() {
        return new KeyGenerator() {
            private final ObjectMapper mapper = new ObjectMapper();

            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(":" + method.getName() + ":");
                for (Object obj : params) {
                    String value = null;
                    try {
                        value = mapper.writeValueAsString(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                    sb.append(value.hashCode());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        JedisConnectionFactory factory = jedisConnectionFactory();
        RedisCacheWriter writer = RedisCacheWriter.nonLockingRedisCacheWriter(factory);

        // 设置缓存有效期一小时
        Duration duration = Duration.ofHours(1);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(duration);

        return RedisCacheManager.builder(writer).cacheDefaults(configuration).build();
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        return new StringRedisTemplate(jedisConnectionFactory());
    }

    private JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory(getRedisStandaloneConfiguration(), getJedisClientConfiguration());
    }

    private JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        JedisPoolConfig config = getJedisPoolConfig();
        JedisClientConfiguration configuration = builder.usePooling().poolConfig(config).build();
        return configuration;
    }

    private JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(poolMaxActive);
        config.setMaxWaitMillis(poolMaxWait);
        config.setMinIdle(poolMinIdle);
        config.setMaxIdle(poolMaxIdle);
        config.setTestWhileIdle(true);
        return config;
    }

    private RedisStandaloneConfiguration getRedisStandaloneConfiguration(){
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        String password = redisProperties.getPassword();

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setPassword(RedisPassword.of(password));

        return configuration;
    }
}
