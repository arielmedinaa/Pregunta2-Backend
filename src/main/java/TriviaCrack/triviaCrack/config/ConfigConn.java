package TriviaCrack.triviaCrack.config;


import jakarta.annotation.PostConstruct;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ConfigConn {

    @Value("${pServidor:localhost}")
    private String servidor;

    @Value("${pPuerto:5432}")
    private int puerto;

    @Value("${pBaseDato:trivia}")
    private String baseDato;

    @Value("${pUsuario}")
    private String usuario;

    @Value("${pClave:postgres}")
    private String clave;

    public static DataSource dataSource;

    @PostConstruct
    private void init() {
        if (dataSource == null) {
            String url = "jdbc:postgresql://" + servidor.trim() + ":" + puerto + "/" + baseDato;
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setValidationQuery("SELECT 1");
            basicDataSource.setTestOnBorrow(true);
            basicDataSource.setDriverClassName("org.postgresql.Driver");
            basicDataSource.setUsername(usuario);
            basicDataSource.setPassword(clave);
            basicDataSource.setUrl(url);
            basicDataSource.setMaxActive(50);
            basicDataSource.setMaxIdle(40);
            basicDataSource.setInitialSize(25);
            basicDataSource.setMinIdle(10);
            basicDataSource.setMinEvictableIdleTimeMillis(900000);
            basicDataSource.setLogAbandoned(false);
            basicDataSource.setRemoveAbandoned(true);
            basicDataSource.setRemoveAbandonedTimeout(300);
            dataSource = basicDataSource;
        }
    }

    public Connection getConnLocal() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
