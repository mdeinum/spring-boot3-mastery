package com.ing.mastery;

import java.time.Duration;
import com.ing.mastery.chuck.ChuckClient;
import com.ing.mastery.chuck.ChuckHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class SpringBoot3MasteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3MasteryApplication.class, args);
    }

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(WebClient.Builder builder) {
        var client = builder.build();
        var clientAdapter = WebClientAdapter.forClient(client);
        return HttpServiceProxyFactory.builder()
                .blockTimeout(Duration.ofSeconds(10))
                .clientAdapter(clientAdapter)
                .build();
    }

    @Bean
    public ChuckClient chuckClient(HttpServiceProxyFactory proxyFactory) {
        return proxyFactory.createClient(ChuckClient.class);
    }

    @Bean
    public RouterFunction<ServerResponse> routes(ChuckHandler chuckHandler) {
        return RouterFunctions.route()
                .GET("/random", chuckHandler::randomQuote)
                .GET("/search", chuckHandler::searchQuote)
                .build();
    }
}
