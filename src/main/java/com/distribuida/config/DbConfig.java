package com.distribuida.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;

@ApplicationScoped
public class DbConfig {
    @Inject
    @ConfigProperty(name = "db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name = "db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name = "db.url")
    String dbUrl;
    Jdbi jdbi = null;

    //Creacion de Pool
    @Produces
    @ApplicationScoped
    public DataSource dataSource(){
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl(dbUrl);
        ds.setUsername(dbUser);
        ds.setPassword(dbPassword);
        return ds;
    }

    @Produces
    public Jdbi jdbi(){
        DataSource miData = dataSource();
        return Jdbi.create(miData).installPlugin(new SqlObjectPlugin());
    }
}
