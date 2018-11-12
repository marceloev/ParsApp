package com.pars.system.db;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

@Data
public class CloseConnectionTask extends TimerTask {

    private Logger logger = LoggerFactory.getLogger(CloseConnectionTask.class);
    private ModelConnection connection;
    private Integer timeout = 0;
    private Timer timer = new Timer();

    public CloseConnectionTask(ModelConnection connection, Integer timeout) {
        this.setConnection(connection);
        this.setTimeout(timeout);
        this.getTimer().schedule(this, this.getTimeout() * 1000);
    }

    @Override
    public void run() {
        if (this.getConnection().getEmf().isOpen()) {
            String closeMSG = String.format("O sistema está finalizando a conexão a seguir pois," +
                            " expirou o tempo máximo delimitado. O tempo máximo era %d e a sessão finalizada é %s",
                    this.getTimeout(),
                    this.getConnection().toString());
            this.getLogger().warn(closeMSG);
            this.getConnection().getEmf().close();
        }
    }
}