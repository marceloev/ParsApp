package com.pars.system.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Persistence;

public class ConnectionFactory {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
    private static String PERSISTENCE_UNIT = "UNDEFINED";

    public static ModelConnection buildConnection() {
        ModelConnection connection = new ModelConnection(Persistence.createEntityManagerFactory(PERSISTENCE_UNIT));
        return connection;
    }

    public static void setPersistenceUnit(Persistences persistence) {
        logger.warn("Objeto de persistência alterado para unidade: " + persistence.toString());
        PERSISTENCE_UNIT = persistence.toString();
    }
}