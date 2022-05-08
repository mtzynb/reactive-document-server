package com.arusha.entrancetask.configuration;


import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class ApplicationConfiguration {
    /**
     * When using R2DBC, there is no support in Spring Boot to for initialising a database using schema.sql or data.sql.
     * <p>
     * Database cannot be initialized with schema or seed data by annotating the configuration class with
     *
     * @param connectionFactory
     * @return connectionFactoryInitializer
     * @EnableAutoConfiguration or by specifying initialization-mode config param.
     */
    @Bean
    public ConnectionFactoryInitializer databaseInitializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema/schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema/data.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}
