package sudoku.platform.discord;

import discord.commands.Ping;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sudoku.platform.discord.SudokuEvent;

import javax.security.auth.login.LoginException;

public class DiscordApp {

    public static final String prefix = "*r ";

    public static void main(String[] args) {
        String token = "NjYzNzM1NzQ2MDQ5MzQzNTE4.XjyiEw.Hg88VfLBfOcLWtDdIapJOlNnYe4";
        try{
            JDA jda = new JDABuilder(token)
                    .setActivity(Activity.playing("watching fox news"))
                    .addEventListeners(new SudokuEvent())
                    .addEventListeners((new Ping()))
                    .build();
            jda.awaitReady();
            System.out.println("JDA object built, captain!");

        }
        catch (LoginException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class DiscordUtils{
        private GuildMessageReceivedEvent event;
        public DiscordUtils(GuildMessageReceivedEvent event){
            this.event = event;
        }
        public void sendMsg(String msg){
            MessageChannel channel = this.event.getChannel();
            channel.sendMessage(msg).queue();
        }
    }
}
