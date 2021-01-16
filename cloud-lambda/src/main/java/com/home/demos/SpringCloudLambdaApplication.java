package com.home.demos;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.home.demos.domain.Order;
import com.home.demos.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringCloudLambdaApplication {

    @Autowired
    private OrderDao orderDao;

    @Bean
    public Supplier<List<Order>> orders() {
        return () -> orderDao.buildOrders();
    }

    @Bean
    public Function<APIGatewayProxyRequestEvent, List<Order>> findOrderByNameFromEvent() {
        return (event) -> orderDao.buildOrders()
                .stream()
                .filter(order -> order.getName().equals(event.getQueryStringParameters().get("orderName")))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(
                SpringCloudLambdaApplication.class, args);
    }
}
