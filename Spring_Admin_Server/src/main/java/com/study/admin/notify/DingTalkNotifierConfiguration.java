package com.study.admin.notify;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration.CompositeNotifierConfiguration;
import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration.NotifierTriggerConfiguration;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;

@Configuration
@ConditionalOnProperty(
        prefix = "spring.boot.admin.notify.dingtalk",
        name = {"webhook-token"}
)
@AutoConfigureBefore({NotifierTriggerConfiguration.class, CompositeNotifierConfiguration.class})
public class DingTalkNotifierConfiguration {
//    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "spring.boot.admin.notify.dingtalk")
    public DiyStatusChangeNotifier diyStatusChangeNotifier(InstanceRepository repository) {
        return new DiyStatusChangeNotifier(repository);
    }

}