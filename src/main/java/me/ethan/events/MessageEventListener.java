package me.ethan.events;

import me.ethan.config.Commands;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MessageEventListener extends ListenerAdapter {

    Map<String, Integer> commands = Commands.getCommands();
    String prefix = ".";
    String altPrefix = "/";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (isCommand(message.getContentRaw())) {
            int commandBytecode = checkCommand(message);
            switch (commandBytecode) {
                case 10 -> executeWockkyCommand(event);
                case 11 -> executeECommand(event);
                default ->
                        event.getChannel().sendMessage("command does not exist, check out https://github.com/ethanrey19/phoenix-discord-bot for help").queue();
            }
        }
    }

    private void executeECommand(MessageReceivedEvent event) {
        Random random = new Random();
        int randomNumber = random.nextInt(7) +  1;
        File file = new File("src/main/resources/e" + randomNumber + ".png");

        if(Objects.nonNull(file)){
            event.getChannel().sendMessage("esex me pls").addFiles(FileUpload.fromData(file)).queue();
        }else{
            event.getChannel().sendMessage("The bot had an error, dm uhshark for help").queue();
        }
    }

    private void executeWockkyCommand(MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        int timeoutAmount = 30;

        UserSnowflake user = User.fromId("608866724443324416");

        guild.timeoutFor(user, timeoutAmount, TimeUnit.SECONDS).queue();
        event.getMessage().getChannel().sendMessage("The user " + user.getAsMention() + " has been timed out for " + timeoutAmount + " seconds").queue();
    }

    private int checkCommand(Message message) {
        int spaceIndex = message.getContentRaw().indexOf(" ");
        if (isSpaceIndexNull(spaceIndex)) spaceIndex = message.getContentRaw().length();
        for (String key : commands.keySet()) {
            if (message.getContentRaw().substring(1, spaceIndex).equalsIgnoreCase(key)) {
                return commands.get(key);
            }
        }
        return 0;
    }

    public boolean isSpaceIndexNull(int spaceIndex) {
        return spaceIndex == -1;
    }

    private boolean isCommand(String contentRaw) {
        return contentRaw.startsWith(prefix) || contentRaw.startsWith(altPrefix);
    }

}
