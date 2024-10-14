package ua.javarush;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static ua.javarush.TelegramBotContent.*;
import static ua.javarush.TelegramBotUtils.*;

public class MyFirstTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        // name for bot
        return "de_cat_25_bot";
    }

    @Override
    public String getBotToken() {
        // token
        return "7294658505:AAFocAQItqHVjx0yOOvTBCSSbSCGoa6BHeo";
    }

    @Override
    public void onUpdateReceived(Update update) {
      Long chatId = getChatId(update);

//      if(update.hasMessage() && (update.getMessage().getText().equals("/start"))) {
//              SendMessage message = createMessage(chatId, "Hi, _future_ *programmer*!");     //_future_- курсив  *programmer* - жирным
//              sendApiMethodAsync(message);
//          }
//      if(update.hasMessage() && update.getMessage().getText().contains("Hi")){
//          SendMessage message = createMessage(chatId, "Hi, what is your name?");
//          sendApiMethodAsync(message);
//      }
//      if(update.hasMessage() && update.getMessage().getText().contains("my name is")){
//            SendMessage message = createMessage(chatId, "Nice to meet you, I'm *Cat_DE*");
//            sendApiMethodAsync(message);
//      }

        if(update.hasMessage() && update.getMessage().getText().equals("/start")) {
            SendMessage message = createMessage(chatId, STEP_1_TEXT, Map.of(
                    "hack the refrigerator", "step_1_btn"
            ));
            sendApiMethodAsync(message);
        }
        if(update.hasCallbackQuery()){
            if(update.getCallbackQuery().getData().equals("step_1_btn")){
                createMessage(chatId, STEP_2_TEXT,
                        Map.of("Get the sausage! +20 glory", "step_2_btn"),
                        "")
            }
        }



    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}