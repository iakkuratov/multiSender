package net.ddns.pzshare.messanger.tg;

import net.ddns.pzshare.messanger.ProxyConfig;
import net.ddns.pzshare.messanger.WorkerCfg;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class TgConfig implements WorkerCfg {
    private final String botName;

    private final String botToken;

    private final DefaultBotOptions options = new DefaultBotOptions();

    public TgConfig(String botName, String botToken) {
        this(botName, botToken, null);
    }

    public TgConfig(String botName, String botToken, ProxyConfig proxyCfg) {
        this.botName = botName;

        this.botToken = botToken;

        if (proxyCfg != null && !proxyCfg.getProxyType().toUpperCase().equals("NO_PROXY")) {
            options.setProxyType(DefaultBotOptions.ProxyType.valueOf(proxyCfg.getProxyType()));

            options.setProxyHost(proxyCfg.getProxyUrl());

            options.setProxyPort(proxyCfg.getProxyPort());
        }
    }

    String getBotName() {
        return botName;
    }

    String getBotToken() {
        return botToken;
    }

    DefaultBotOptions getOptions() {
        return options;
    }
}
