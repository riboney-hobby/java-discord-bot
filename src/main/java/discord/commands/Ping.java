package discord.commands;


import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sudoku.platform.discord.DiscordApp;


public class Ping extends ListenerAdapter {


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String msg = message.getContentRaw().toLowerCase();

        if(msg.startsWith(DiscordApp.prefix + "ping")){
            channel.sendMessage("I'm alive! Yes, don't worry").queue();
        }
    }

//    @Override
//    public void onMessageReceived(MessageReceivedEvent event){
//        User author = event.getAuthor();
//        Message message = event.getMessage();
//        MessageChannel channel = event.getChannel();
//        String msg = message.getContentRaw().toLowerCase();
//
//        if(msg.startsWith(sudoku.platform.discord.DiscordApp.prefix + "ping")){
//            channel.sendMessage("I'm alive! Yes, don't worry");
//        }
//    }
}
