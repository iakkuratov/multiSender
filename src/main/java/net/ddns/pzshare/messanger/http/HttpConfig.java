package net.ddns.pzshare.messanger.http;

import net.ddns.pzshare.messanger.WorkerCfg;

import java.net.InetSocketAddress;

public class HttpConfig implements WorkerCfg {
    private final InetSocketAddress socket;

    private final String listenPath;

    public HttpConfig(Integer port, String listenPath) {
        socket = new InetSocketAddress(port == null ? 8080 : port);
        this.listenPath = listenPath;
    }

    InetSocketAddress getSocket() {
        return socket;
    }

    String getListenPath() {
        return listenPath;
    }
}
