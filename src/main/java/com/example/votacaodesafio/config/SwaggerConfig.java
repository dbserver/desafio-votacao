package com.example.votacaodesafio.config;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData())
                .useDefaultResponseMessages(false)
                .tags(new Tag("", ""))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build();
    }

    private ApiInfo metaData() {
        final String projectVersion = "1.0.0";
        return new ApiInfoBuilder()
                .title("")
                .description("\"Spring Boot REST for Votação\"")
                .version(projectVersion)
                .license("Victor Alexsander © 2023")
                .build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl(Strings.EMPTY)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/swagger-ui.html"), req ->
                ServerResponse.temporaryRedirect(URI.create("/swagger-ui/index.html")).build());
    }

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanMappings() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(final Object value, final String beanName) throws BeansException {
                if (value instanceof WebMvcRequestHandlerProvider) {
                    changeSpringfoxV3HandlerMappings(this.getHandlerMappings(value));
                }
                return value;
            }

            private <G extends RequestMappingInfoHandlerMapping> void changeSpringfoxV3HandlerMappings(final List<G> mappings) {
                mappings.removeIf(mapping -> Objects.nonNull(mapping.getPatternParser()));
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(final Object value) {
                try {
                    final Field field = ReflectionUtils.findField(value.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(value);
                } catch (final Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}