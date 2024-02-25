package me.ethan;

import me.ethan.events.MessageEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {

        JDA bot = JDABuilder.createDefault("MTIxMTE3MzYzNTg5Nzk1MDIxOA.G0lhci.ktVnMNjDMlX5fIjeQLgux1QFW_RdVtvxo_276U")
                .setActivity(Activity.listening("tyler askins"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new MessageEventListener())
                .build();

    }

}