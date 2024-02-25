package me.ethan;

import io.github.cdimascio.dotenv.Dotenv;
import me.ethan.events.MessageEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");
        JDA bot = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("with zack dal's bbc"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new MessageEventListener())
                .build();

    }

}