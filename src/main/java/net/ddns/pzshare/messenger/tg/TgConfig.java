package net.ddns.pzshare.messenger.tg;

import net.ddns.pzshare.messenger.ConfigurationException;
import net.ddns.pzshare.messenger.ProxyConfig;
import net.ddns.pzshare.messenger.WorkerCfg;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class TgConfig implements WorkerCfg {
    private final String botName;

    private final String botToken;

    private final DefaultBotOptions options = new DefaultBotOptions();

    public TgConfig(String botName, String botToken) throws ConfigurationException {
        this(botName, botToken, null);
    }

    public TgConfig(String botName, String botToken, ProxyConfig proxyCfg) throws ConfigurationException {
        if (validateEmpty(botName))
            throw new ConfigurationException("Option botName is necessary");
        this.botName = botName;
        if (validateEmpty(botToken))
            throw new ConfigurationException("Option botName is necessary");

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
