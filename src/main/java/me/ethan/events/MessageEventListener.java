package me.ethan.events;

import me.ethan.config.Commands;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MessageEventListener extends ListenerAdapter {

    Map<String, Integer> commands = Commands.getCommands();
    String prefix = ".";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (isCommand(message.getContentRaw())) {
            int commandBytecode = checkCommand(message);
            switch (commandBytecode){
                case 10:
                    executeWockkyCommand(event);
                    break;
                default:
                    System.out.println("invalid command");
            }
        }
    }

    private void executeWockkyCommand(MessageReceivedEvent event) {
        Guild guild = event.getGuild();

        if (guild == null) {
            //event.reply("Server not found").setEphemeral(true).queue();
            return;
        }


        User user = event.getJDA().getUserById("608866724443324416");

        if (user.isBot()) {
           // event.replyEmbeds(BotUtils.createEmbed("Error", Color.RED, null , null,"Can't time out BOT")).queue();
            return;
        }


        guild.timeoutFor(user, 5, TimeUnit.SECONDS).queue();
    }

    private int checkCommand(Message message) {
        int spaceIndex = message.getContentRaw().indexOf(" ");
        if (isSpaceIndexNull(spaceIndex)) spaceIndex = message.getContentRaw().length();
        for (String key : commands.keySet()) {
            if (message.getContentRaw().substring(1, spaceIndex).equals(key)) {
                return commands.get(key);
            }
        }
        return 0;
    }

    public boolean isSpaceIndexNull(int spaceIndex) {
        return spaceIndex == -1;
    }

    private boolean isCommand(String contentRaw) {
        return contentRaw.startsWith(prefix);
    }


}
