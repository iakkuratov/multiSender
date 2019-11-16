package net.ddns.pzshare.messenger.tg;

import net.ddns.pzshare.messenger.SendException;
import net.ddns.pzshare.messenger.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class TgWorker extends Worker implements TgConsumer {
    private static final Logger log = LogManager.getLogger();

    private static TelegramBotsApi telegramBotsApi;

    private final TgBot bot;

    public TgWorker(TgConfig config){
        bot = new TgBot(config, this);
    }

    @Override
    public void start() {
        if (telegramBotsApi == null){
            ApiContextInitializer.init();

            telegramBotsApi = new TelegramBotsApi();
        }
        try {
            telegramBotsApi.registerBot(bot);

            log.info("Bot successfully registered.");

        } catch (TelegramApiRequestException ex) {
            log.error("Failed to start bot.", ex);
        }
    }

    @Override
    public void send(String userId, String msg) throws SendException {
        bot.send(userId, msg);
    }
}
