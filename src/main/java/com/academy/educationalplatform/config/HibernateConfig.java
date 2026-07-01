package com.academy.educationalplatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(
            DataSource dataSource,
            @Value("${platform.hibernate.dialect}") String dialect,
            @Value("${platform.hibernate.hbm2ddl-auto}") String hbm2ddlAuto,
            @Value("${platform.hibernate.show-sql}") boolean showSql) {

        Properties props = new Properties();
        props.put("hibernate.dialect", dialect);
        props.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        props.put("hibernate.show_sql", Boolean.toString(showSql));

        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.platform.entity");
        factory.setHibernateProperties(props);
        return factory;
    }
}
