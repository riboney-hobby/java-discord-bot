package sudoku.platform.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sudoku.gamelogic.utils.SudokuMakerUtil;
import sudoku.model.Grid;


public class SudokuEvent extends ListenerAdapter {

    private String preset;
    private Grid grid;
    private DiscordGameLoop game;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String msg = message.getContentRaw().toLowerCase();
        String prefix = DiscordApp.prefix;
        DiscordApp.DiscordUtils discordUtils = new DiscordApp.DiscordUtils(event);

        // Starts the game, and runs the createGame method
        // TO-DO: Include size feature so user can set the size of the board
        if(msg.startsWith(prefix + "sd")){
            channel.sendMessage("Starting game...").queue();
            //channel.sendMessage("Valid input format -> *r move X,Y Value").queue();
            createGame(event);
            this.game.displayGameEmbeds();
            //this.game.displayGame();
        }

        if(msg.startsWith(prefix + "move")){
            if(this.game == null){
                discordUtils.sendMsg("Error! Start game first");
                return;
            }

            else{
                //run game loop
                this.game.setEvent(event);
                this.game.runGame();
                String playerInput = this.game.returnRunGameValue();
                if(playerInput.startsWith("e")){
                    createGame(event);
                    discordUtils.sendMsg("Game has ended!");
                    return;
                }
                else if(playerInput.startsWith("w")){
                    discordUtils.sendMsg("Wrong input!");
                    return;
                }
                else{
                    this.game.displayGameEmbeds();
                    //this.game.displayGame();
                }
            }
            return;
        }
    }

    // CREATES sudoku game stuff and runs it
    public void createGame(GuildMessageReceivedEvent event){
        this.preset = SudokuMakerUtil.createPuzzle(9);
        this.grid = new Grid(9);
        this.game = new DiscordGameLoop(grid, preset, event);
        this.game.gameStartUp();
        //this.game = new DiscordGameLoop(grid, event);
    }

}
