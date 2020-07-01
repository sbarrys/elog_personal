package com.kry.elog_personal.config;


import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile(value = {"local"})
@Configuration
public class FlywayConfig {

    private final DataSource dataSource;

    private Flyway flyway;

    public FlywayConfig(DataSource dataSource) {
        this.dataSource = dataSource;
        this.migrate();
    }

    private void migrate() {
        flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }


}
