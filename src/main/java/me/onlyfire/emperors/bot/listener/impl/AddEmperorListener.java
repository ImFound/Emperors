package me.onlyfire.emperors.bot.listener.impl;

import me.onlyfire.emperors.bot.EmperorsBot;
import me.onlyfire.emperors.bot.exceptions.EmperorException;
import me.onlyfire.emperors.bot.listener.BotListener;
import me.onlyfire.emperors.bot.mongo.EmperorsMongoDatabase;
import me.onlyfire.emperors.bot.mongo.models.MongoEmperor;
import me.onlyfire.emperors.bot.mongo.models.MongoGroup;
import me.onlyfire.emperors.essential.Language;
import me.onlyfire.emperors.user.impl.EmperorUserCreation;
import me.onlyfire.emperors.utils.MemberUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public record AddEmperorListener(EmperorsBot emperorsBot) implements BotListener {

    @Override
    public void execute(Update update, AbsSender sender) {
        Message message = update.getMessage();

        if (message == null)
            return;

        var chat = message.getChat();
        var groupId = String.valueOf(message.getChatId());
        var user = message.getFrom();
        if (!MemberUtils.isAdministrator(sender, user, chat))
            return;

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(String.valueOf(chat.getId()));
        sendMessage.setReplyToMessageId(message.getMessageId());

        if (!(emperorsBot.getUserMode().get(user) instanceof EmperorUserCreation emperorUserCreation))
            return;

        if (emperorUserCreation.getChat().getId().equals(chat.getId())) {
            if (message.hasPhoto()) {
                sendMessage.setText("Devi mandare una foto SENZA usare la compressione di telegram!");
                try {
                    sender.execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return;
            }

            if (!message.hasDocument() || message.getCaption() == null || message.getCaption().isEmpty())
                return;

            var emperorName = message.getCaption().toLowerCase();
            var database = emperorsBot().getMongoDatabase();
            var mongoGroup = database.getMongoGroup(chat);
            if (mongoGroup == null)
                throw new EmperorException("Could not fetch group id " + groupId);

            var mongoEmperor = database.getEmperorByName(chat, emperorName);
            if (mongoEmperor != null) {
                sendMessage.setText(Language.ALREADY_EXIST_EMPEROR.toString());
                try {
                    sender.executeAsync(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                emperorsBot.removeUserMode(user, chat, null);
                return;
            }

            sendMessage.setText(Language.CREATION_IN_PROGRESS.toString());
            try {
                sender.executeAsync(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            emperorUserCreation.completed(message, emperorName);
        }
    }
}