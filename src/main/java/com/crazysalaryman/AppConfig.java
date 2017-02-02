package com.crazysalaryman;

import lombok.extern.slf4j.Slf4j;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * Created by lse0101 on 1/24/17.
 */
@Slf4j
@Configuration
public class AppConfig {

  @Autowired
  DataSourceProperties dataSourceProperties;
  DataSource dataSource;

//  @Bean
//  @ConfigurationProperties(prefix = "spring.datasource")
//  DataSourceProperties dataSourceProperties(){
//    DataSourceProperties dataSourceProperties = new DataSourceProperties();
//    return dataSourceProperties;
//  }

//  @Bean
//  DataSource realDataSource(){
//    DataSourceBuilder factory = DataSourceBuilder
//        .create(dataSourceProperties.getClassLoader())
//        .url(dataSourceProperties.getUrl())
//        .username(dataSourceProperties.getUsername())
//        .password(dataSourceProperties.getPassword());
//
//    this.dataSource = factory.build();
//
//    return this.dataSource;
//  }

  @Bean
  @Primary
  DataSource dataSource(){
    DataSourceBuilder factory = DataSourceBuilder
            .create(dataSourceProperties.getClassLoader())
            .url(dataSourceProperties.getUrl())
            .username(dataSourceProperties.getUsername())
            .password(dataSourceProperties.getPassword());

    return new Log4jdbcProxyDataSource(factory.build());
  }

/*  @Bean
  NamedParameterJdbcTemplate jdbcTemplate(){
    return new NamedParameterJdbcTemplate(this.dataSource());
  }*/

/*  @Bean
  DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();

    dataSourceInitializer.setDataSource(dataSource);
    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
    resourceDatabasePopulator.addScript(new ClassPathResource("schema.sql"));
    resourceDatabasePopulator.addScript(new ClassPathResource("data.sql"));
    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
    dataSourceInitializer.setEnabled(true);

    return dataSourceInitializer;
  }*/

}
