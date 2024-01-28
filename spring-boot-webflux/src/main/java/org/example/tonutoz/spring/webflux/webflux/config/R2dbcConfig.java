package org.example.tonutoz.spring.webflux.webflux.config;

import java.util.ArrayList;
import java.util.List;
import org.example.tonutoz.spring.webflux.webflux.post.converter.PostReadConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.DialectResolver;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
@EnableR2dbcAuditing
public class R2dbcConfig {

  @Bean
  public R2dbcCustomConversions r2dbcCustomConversions(DatabaseClient databaseClient) {
    var dialect = DialectResolver.getDialect(databaseClient.getConnectionFactory());
    var converters = new ArrayList<>(dialect.getConverters());
    converters.addAll(R2dbcCustomConversions.STORE_CONVERTERS);

    return new R2dbcCustomConversions(
        CustomConversions.StoreConversions.of(dialect.getSimpleTypeHolder(), converters),
        getCustomConverters());
  }

  private List<Object> getCustomConverters() {
    return List.of(new PostReadConverter());
  }

}