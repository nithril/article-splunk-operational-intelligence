package org.nlab.splunk.article.jmxtrans;

import org.jmxtrans.embedded.spring.EmbeddedJmxTransFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nlabrot on 31/05/15.
 */
@Configuration
public class JmxTransConfiguration {

    @Bean
    public EmbeddedJmxTransFactory jmxTransFactory(){
        EmbeddedJmxTransFactory jmxTransFactory = new EmbeddedJmxTransFactory();
        jmxTransFactory.setConfigurationUrl("classpath:/jmxtrans/jvm.json");
        return jmxTransFactory;
    }
}
