package com.epam.hw34.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket libraryApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("library")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.epam.hw34.controller"))
        .paths(PathSelectors.ant("/library/**"))
        .build();
  }
  @Bean
  public Docket userApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("user")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.epam.hw34.controller"))
        .paths(PathSelectors.ant("/user/**"))
        .build();
  }

  @Bean
  public LinkDiscoverers discoverers() {
    List<LinkDiscoverer> plugins = new ArrayList<>();
    plugins.add(new CollectionJsonLinkDiscoverer());
    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
  }

}
