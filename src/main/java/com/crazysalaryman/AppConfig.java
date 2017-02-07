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
import java.net.URI;
import java.net.URISyntaxException;


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
  DataSource dataSource() throws URISyntaxException {

    String url;
    String username;
    String password;

    String databaseUrl = System.getenv("DATABASE_URL");
    log.info("######################################################################################");
    log.info("database_url " + databaseUrl);

    if (databaseUrl != null) {
      URI dbUri = new URI(databaseUrl);
      url = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath()
              +"&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
      username = dbUri.getUserInfo().split(":")[0];
      password = dbUri.getUserInfo().split(":")[1];
    } else {
      url = this.dataSourceProperties.getUrl();
      username = this.dataSourceProperties.getUsername();
      password = this.dataSourceProperties.getPassword();
    }

    log.info(url);
    log.info(username);
    log.info(password);

    DataSourceBuilder factory = DataSourceBuilder
            .create(dataSourceProperties.getClassLoader())
            .url(url)
            .username(username)
            .password(password);

    log.info("######################################################################################");
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
