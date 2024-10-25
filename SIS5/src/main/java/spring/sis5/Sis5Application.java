package spring.sis5;

import db.callback.FlywayDatabaseSeeder;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sis5Application {

    public static void main(String[] args) {
        SpringApplication.run(Sis5Application.class, args);
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return (flywayOld) -> {

		/*
		 Update the existing autoconfigured Flyway
		 bean to include our callback class
		*/
            Flyway flyway = Flyway.configure()
                    .configuration(flywayOld.getConfiguration())
                    .callbacks(new FlywayDatabaseSeeder())
                    .load();

            flyway.migrate();

        };
    }
}
