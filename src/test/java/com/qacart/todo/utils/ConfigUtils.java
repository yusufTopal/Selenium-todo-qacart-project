package com.qacart.todo.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtils {

    private static ConfigUtils configUtils;
    private Properties properties;

    private ConfigUtils() {
        properties = readProperties();
    }

    public static ConfigUtils getInstance() {
        if(configUtils == null) {
            return new ConfigUtils();
        }
        return configUtils;
    }

    private Properties readProperties() {
        FileInputStream inputStream = null;
        try {
            String env = System.getProperty("env", "PRODUCTION");
            switch (env) {
                case "PRODUCTION" -> {
                    inputStream = new FileInputStream("src/test/resources/env/production.properties");
                }
                case "LOCAL" -> {
                    inputStream = new FileInputStream("src/test/resources/env/local.properties");
                }
                default -> {
                    throw new RuntimeException("Environment is not supported");
                }
            }

            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }

    public String getBaseUrl() {
        return properties.get("URL").toString();
    }
}
