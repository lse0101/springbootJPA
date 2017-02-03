package com.crazysalaryman;

import lombok.extern.slf4j.Slf4j;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

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

  @Order(Ordered.HIGHEST_PRECEDENCE)
  @Bean
  CharacterEncodingFilter characterEncodingFilter() {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    return filter;
  }

}
