package net.ddns.pzshare.messenger.rmq;

import net.ddns.pzshare.messenger.WorkerCfg;

public class RmqConfig implements WorkerCfg {
    private String host;

    private String outQueue;

    private String inQueue;

    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOutQueue() {
        return outQueue;
    }

    public void setOutQueue(String outQueue) {
        this.outQueue = outQueue;
    }

    public String getInQueue() {
        return inQueue;
    }

    public void setInQueue(String inQueue) {
        this.inQueue = inQueue;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
