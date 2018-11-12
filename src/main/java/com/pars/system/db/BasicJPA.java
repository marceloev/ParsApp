package com.pars.system.db;

import com.pars.system.impls.ModelOptional;
import com.pars.system.impls.ModelPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.List;

public class BasicJPA {

    private final Logger logger = LoggerFactory.getLogger(BasicJPA.class);

    public int executeStatement(String SQL, QueryType queryType, ModelPair<String, Object>... filtros) throws Exception {
        ModelOptional<Integer> rowsAffected = new ModelOptional<>();
        ModelOptional<Exception> exception = new ModelOptional<>();
        ModelConnection connection = ConnectionFactory.buildConnection();
        try {
            connection.beginTransaction();
            Query query = getQuery(connection, SQL, queryType);
            for (ModelPair<String, Object> filtro : filtros)
                query.setParameter(filtro.getKey(), filtro.getValue());
            rowsAffected.set(query.executeUpdate());
            connection.commitTransaction();
        } catch (Exception ex) {
            exception.set(ex);
            logger.error(ex.getMessage(), ex);
        } finally {
            connection.closeAll();
        }
        if (exception.contains())
            throw exception.get();
        else
            return rowsAffected.get();
    }

    public List retrieveStatement(ModelConnection connection, String SQL, QueryType queryType, ModelPair<String, Object>... filtros) throws Exception {
        ModelOptional<List> resultSet = new ModelOptional<>();
        ModelOptional<Exception> exception = new ModelOptional<>();
        try {
            connection.beginTransaction();
            Query query = getQuery(connection, SQL, queryType);
            for (ModelPair<String, Object> filtro : filtros)
                query.setParameter(filtro.getKey(), filtro.getValue());
            resultSet.set(query.getResultList());
            connection.commitTransaction();
        } catch (Exception ex) {
            exception.set(ex);
            logger.error(ex.getMessage(), ex);
        }
        if (exception.contains())
            throw exception.get();
        else
            return resultSet.get();
    }

    public List retrieveStatement(String SQL, QueryType queryType, ModelPair<String, Object>... filtros) throws Exception {
        ModelOptional<List> resultSet = new ModelOptional<>();
        ModelOptional<Exception> exception = new ModelOptional<>();
        ModelConnection connection = ConnectionFactory.buildConnection();
        try {
            connection.beginTransaction();
            Query query = getQuery(connection, SQL, queryType);
            for (ModelPair<String, Object> filtro : filtros)
                query.setParameter(filtro.getKey(), filtro.getValue());
            resultSet.set(query.getResultList());
            connection.commitTransaction();
        } catch (Exception ex) {
            exception.set(ex);
            logger.error(ex.getMessage(), ex);
        } finally {
            connection.closeAll();
        }
        if (exception.contains())
            throw exception.get();
        else
            return resultSet.get();
    }

    public Query getQuery(ModelConnection modelConnection, String SQL, QueryType queryType) throws Exception {
        switch (queryType) {
            case Normal_Query:
                return modelConnection.getEm().createQuery(SQL);
            case Named_Query:
                return modelConnection.getEm().createNamedQuery(SQL);
            case Native_Query:
                return modelConnection.getEm().createNativeQuery(SQL);
            default:
                throw new Exception("BasicJPA.getQuery(" + queryType + ") não suportado.");
        }
    }
}