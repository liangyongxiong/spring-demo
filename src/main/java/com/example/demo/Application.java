package com.example.demo;

import com.example.demo.configuration.kafka.KafkaConsumerConfig;
import com.example.demo.constant.SystemEnv;
import com.example.demo.util.command.BaseCommand;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableFeignClients
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.demo.kafka.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.demo.schedule.*"),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                KafkaConsumerConfig.class,
        })
})
public class Application {

	public static void main(String[] args) {
        System.setProperty("user.timezone", "Asia/Shanghai");
		SpringApplication app = new SpringApplication(Application.class);
		app.setAdditionalProfiles(SystemEnv.APP_ENV);
		app.setBannerMode(Banner.Mode.CONSOLE);
        app.setWebApplicationType(WebApplicationType.SERVLET);

		ConfigurableApplicationContext context = app.run(args);
        if (args.length == 0) {
            context.start();
        } else {
            processContext(context, args);
            context.close();    // 关闭 context，退出 springboot 进程
        }
	}

	public static void processContext(ConfigurableApplicationContext context, String[] args) {
        String action = args[0];
        if ("help".equals(action)) {
            printHelp();
            return;
        }

        args = Arrays.copyOfRange(args, 0, args.length-1);
        if (args.length == 0) {
            System.out.println("bean name required");
            return;
        }

        String className = args[0];
        Class clazz = getClass(className);
        if (clazz == null) {
            System.out.println("unknown bean name");
            return;
        }

        args = Arrays.copyOfRange(args, 0, args.length-1);
        switch (action) {
            case "command":
                BaseCommand command = (BaseCommand) context.getBean(clazz);
                command.run(args);
                break;
            case "kafka":
                break;
            case "cron":
                break;
            default:
                System.out.println("unknown action");
        }
    }

    public static void printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("java -jar APPLICATION-NAME-VERSION.jar [ACTION] [BEAN_NAME]\n");
        sb.append("\n");
        sb.append("[ ACTION ]\n");
        sb.append("* command	- run specified task command\n");
        sb.append("* kafka		- run kafak consumer\n");
        sb.append("* cron		- run cron daemon\n");
        sb.append("* help		- display help info\n");
        sb.append("\n");
        sb.append("[ BEAN_NAME ]\n");
        sb.append("valid bean name within spring contaner\n");
        System.out.println(sb.toString());
    }

	public static Class<?> getClass(String className) {
		String packageName = Application.class.getPackage().getName();
		String fullClassName = String.format("%s.command.%s", packageName, className);

		try {
			Class clazz = Class.forName(fullClassName);
			return clazz;
		} catch (ClassNotFoundException cnfe) {
			System.out.println(String.format("%s not found", fullClassName));
		}

		return null;
	}

}
