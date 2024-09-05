package me.maktoba;

import java.io.IOException;
import java.util.Collections;
import me.maktoba.listeners.SlashCommandListener;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class JBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public JBot() throws LoginException {
        config = Dotenv.configure().load();

        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("YOU"));
        builder.addEventListeners(new SlashCommandListener());

        shardManager = builder.build();

    }

    public Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) throws IOException {
        try {
            new JBot();
        } catch(Exception e) {
            System.err.println("invalid token received");
        }
    }
}