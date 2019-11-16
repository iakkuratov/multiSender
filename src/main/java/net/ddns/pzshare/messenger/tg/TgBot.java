package net.ddns.pzshare.messenger.tg;

import net.ddns.pzshare.messenger.SendException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

class TgBot extends TelegramLongPollingBot {
    private static final Logger log = LogManager.getLogger();

    private final TgConfig config;

    private final TgConsumer tgConsumer;

    TgBot(TgConfig config, TgConsumer receiver) {
        super(config.getOptions());

        this.config = config;

        this.tgConsumer = receiver;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage() == null ? update.getEditedMessage() : update.getMessage();

        String chatId = msg.getChatId().toString();

        String text = msg.getText();

        tgConsumer.toSubscribers(chatId, text);
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    void send(String chatId, String text) throws SendException {
        try {
            log.debug("Trying to send msg: " + text + " for chatId: " + chatId);

            execute(new SendMessage().setChatId(chatId).setText(text));

        } catch (TelegramApiException ex) {
            log.error(ex);

            throw new SendException(ex.getMessage());
        }
    }
}
