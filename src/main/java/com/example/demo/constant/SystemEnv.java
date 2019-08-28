package com.example.demo.constant;

import java.util.Arrays;
import java.util.List;

public class SystemEnv {
    public static final List<String> APP_RELEASE_ENVS = Arrays.asList("prd", "stg", "qa");
    public static final String APP_ENV = System.getenv().getOrDefault("APP_ENV", "local");
}
