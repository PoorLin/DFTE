package com.medicine.dfte.config;

import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.domain") // 替換成你的實體類所在的包
                .properties(hibernateProperties())
                .persistenceUnit("yourPersistenceUnit")
                .build();
    }

    private Map<String, String> hibernateProperties() {
        Map<String, String> properties = new HashMap<>();
        // 使用 JPA 兼容的隱式命名策略，禁用自動添加底線
        properties.put("hibernate.implicit_naming_strategy", ImplicitNamingStrategyJpaCompliantImpl.class.getName());
        // 使用標準的實體命名策略，保留實體類中的命名
        properties.put("hibernate.physical_naming_strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        // 根據需要添加其他Hibernate屬性
        return properties;
    }
}
