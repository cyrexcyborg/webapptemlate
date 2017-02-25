package ru.sareth.webapp.config;


import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.sareth.webapp.storage.StorageProperties;
import ru.sareth.webapp.storage.StorageService;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@SpringBootApplication(scanBasePackages = {"ru.sareth.webapp"})
@EnableJpaRepositories(basePackages = "ru.sareth.webapp.domain")
@EntityScan(basePackages = "ru.sareth.webapp.domain")
@ComponentScan(basePackages = {"ru.sareth.webapp"})
@EnableConfigurationProperties(StorageProperties.class)
public class Application extends SpringBootServletInitializer {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public ApplicationListener<ContextRefreshedEvent> startupLoggingListener() {
        return event -> {
            String startMessage = "\n\n" +
                    "@@@@@  @@@@@    @@     @@@@@@   @@@@@   @@@@@@  @@@@@@\n" +
                    "@        @     @  @    @    @     @     @       @     @\n" +
                    "@        @    @    @   @    @     @     @       @     @\n" +
                    "@@@@@    @    @@@@@@   @@@@@      @     @@@@@@  @     @\n" +
                    "    @    @    @    @   @   @      @     @       @     @\n" +
                    "    @    @    @    @   @    @     @     @       @     @\n" +
                    "@@@@@    @    @    @   @     @    @     @@@@@@  @@@@@@\n" +
                    "\n";
            LOGGER.info(startMessage);
            RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();

            LOGGER.info(mxBean.getInputArguments().toString());
            LOGGER.info("Angrycat Application Has Started.");
        };
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}