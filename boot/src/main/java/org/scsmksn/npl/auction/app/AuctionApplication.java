package org.scsmksn.npl.auction.app;

import org.scsmksn.npl.auction.config.AuctionWebFilterConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.scsmksn.npl.auction")
@EnableJpaRepositories(basePackages = "org.scsmksn.npl.auction")
@EntityScan(basePackages = "org.scsmksn.npl.auction")
@Import({AuctionWebFilterConfiguration.class})
public class AuctionApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuctionApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuctionApplication.class);
    }
}