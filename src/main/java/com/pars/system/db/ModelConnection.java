package com.pars.system.db;

import com.pars.system.params.SystemParams;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;

@Data
public class ModelConnection {

    private final CloseConnectionTask closeConnectionTask;
    private Date dhRequest;
    private EntityManagerFactory emf;
    private EntityManager em;
    private Integer timeout;

    public ModelConnection(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = this.emf.createEntityManager();
        this.timeout = Integer.valueOf((String) SystemParams.getParametro("pars.db.session.timeout"));
        this.closeConnectionTask = new CloseConnectionTask(this, this.getTimeout());
    }

    public ModelConnection(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
        this.timeout = (Integer) SystemParams.getParametro("pars.db.session.timeout");
        this.closeConnectionTask = new CloseConnectionTask(this, this.getTimeout());
    }

    public ModelConnection(EntityManagerFactory emf, EntityManager em, Integer timeout) {
        this.emf = emf;
        this.em = em;
        this.timeout = timeout;
        this.closeConnectionTask = new CloseConnectionTask(this, this.getTimeout());
    }

    public ModelConnection(Date dhRequest, EntityManagerFactory emf, EntityManager em, Integer timeout) {
        this.dhRequest = dhRequest;
        this.emf = emf;
        this.em = em;
        this.timeout = timeout;
        this.closeConnectionTask = new CloseConnectionTask(this, this.getTimeout());
    }

    public void stopTimer() {
        this.getCloseConnectionTask().cancel();
    }

    public void beginTransaction() {
        this.getEm().getTransaction().begin();
    }

    public void commitTransaction() {
        this.getEm().getTransaction().commit();
    }

    public void rollbackTransaction() {
        this.getEm().getTransaction().rollback();
    }

    public void closeAll() {
        this.getEmf().close();
    }

    @Override
    public String toString() {
        return "ModelConnection{" +
                "dhRequest=" + dhRequest +
                ", emf=" + emf +
                ", em=" + em +
                ", timeout=" + timeout +
                '}';
    }
}
