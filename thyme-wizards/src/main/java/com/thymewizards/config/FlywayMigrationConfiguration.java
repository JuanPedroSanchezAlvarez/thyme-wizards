package com.thymewizards.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayMigrationConfiguration {

//	/**
//	 * Override default flyway initializer to do nothing
//	 */
//	@Bean
//    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
//		return new FlywayMigrationInitializer(flyway, (f) -> {} );
//    }
//
//    /**
//     * Create a second flyway initializer to run after jpa has created the schema
//     */
//    @Bean
//    @DependsOn("entityManagerFactory")
//    FlywayMigrationInitializer delayedFlywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway, null);
//    }

}
