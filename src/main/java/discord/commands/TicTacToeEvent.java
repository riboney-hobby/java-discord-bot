package discord.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sudoku.platform.discord.DiscordApp;


public class TicTacToeEvent extends ListenerAdapter {
    public void onGuildMessageReceivedEvent(GuildMessageReceivedEvent event){
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String msg = message.getContentRaw().toLowerCase();
        String prefix = DiscordApp.prefix;

        if(msg.startsWith(prefix + "start")){
            createGame(author);
        }
    }

    public void createGame(User author){
        
    }
}
