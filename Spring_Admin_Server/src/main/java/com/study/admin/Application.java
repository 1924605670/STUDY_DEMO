package com.study.admin;

import com.study.admin.notify.DingTalkNotifierConfiguration;
import com.study.admin.notify.DiyStatusChangeNotifier;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAdminServer
@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@Import({DingTalkNotifierConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public DiyStatusChangeNotifier customNotifier(InstanceRepository repository) {
        return new DiyStatusChangeNotifier(repository);
    }

}
