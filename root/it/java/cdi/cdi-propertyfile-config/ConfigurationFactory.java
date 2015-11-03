package com.ahamojo.dam.repo.web.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class ConfigurationFactory {

    private volatile static Properties configProperties;
    public static final String propertiesFilePath="/home/andy/application.properties";

    @PostConstruct
    private void init(){
        System.out.println("init");
    }

    public synchronized static Properties getProperties() {

        if(configProperties==null) {
            configProperties=new Properties();
            try {
                configProperties.load(new FileInputStream(propertiesFilePath));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

        return configProperties;
    }




    public @Produces @Config String getConfiguration(InjectionPoint p) {

        String configKey=p.getMember().getDeclaringClass().getName()+"."+p.getMember().getName();

        System.out.println(configKey);

        Properties config=getProperties();
        if(config.getProperty(configKey)==null) {
            configKey=p.getMember().getDeclaringClass().getSimpleName()+"."+p.getMember().getName();
            if(config.getProperty(configKey)==null)
                configKey=p.getMember().getName();
        }
        System.err.println("Config key= "+configKey+" value = "+config.getProperty(configKey));

        return config.getProperty(configKey);
    }

     public @Produces @Config Double getConfigurationDouble(InjectionPoint p) {

         String val=getConfiguration(p);
         return Double.parseDouble(val);

     }

}
