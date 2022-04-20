package sudoku.platform.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sudoku.gamelogic.utils.MoveValidationUtil;
import sudoku.gamelogic.utils.SolutionCheckerUtil;
import sudoku.gamelogic.GameLoop;
import sudoku.model.GameTexts;
import sudoku.model.Grid;
import sudoku.model.PlayerMove;

import java.awt.*;

public class DiscordGameLoop extends GameLoop {

    // Grid represents game board (an array of rows that contain an array of cells
    private Grid grid;

    // Represents player input
    private String input;

    // Used to recreate sudoku board; leave blank to generate empty board for testing purposes
    private String preset;

    // Represents the Discord event that the game will use to for the input + sending messages to user
    private GuildMessageReceivedEvent event;

    // To use discord utility methods defined in sudoku.platform.discord.DiscordApp.java
    //private sudoku.platform.discord.DiscordApp.DiscordUtils discordUtils;

    // String output from runGame() for SudokuEvent.java
    private String output;


    public DiscordGameLoop(Grid grid, String preset, GuildMessageReceivedEvent event) {
        this.grid = grid;
        this.input = "";
        this.preset = preset;
        this.event = event;
        //this.discordUtils = new sudoku.platform.discord.DiscordApp.DiscordUtils(this.event);
        this.output = "";
    }

    // used for empty boards
    public DiscordGameLoop(Grid grid, GuildMessageReceivedEvent event){
        this(grid, "", event);
    }




    // set the event with new event
    public void setEvent(GuildMessageReceivedEvent event){
        this.event = event;
    }

    //@Override
    public String inputLoop(DiscordApp.DiscordUtils discordUtils){
        boolean keepInputting = true;
        //     1.raw input   2.command+prefix removed  3. input formatted for setCellValue()
        String unprocessedInput = "", unCleanInput = "", cleanedInput = "";

        while(keepInputting) {
            discordUtils.sendMsg(GameTexts.userPromptAsString);
            unprocessedInput = event.getMessage().getContentRaw().toLowerCase();

            // Only for testing purposes, remove later
            // display the messages user sends in console
            System.out.println(unprocessedInput);

            // if input does not match command call, then ignore
            if(unprocessedInput.startsWith(DiscordApp.prefix)){
                // clean the input to remove the command prefixes
                unCleanInput = unprocessedInput.replace(DiscordApp.prefix + "move", "").trim();

                // End game
                if(unCleanInput.equals("-1")) {
                    discordUtils.sendMsg(GameTexts.endGamePromptString);
                    return "-1";
                } else if(unCleanInput.startsWith("c")){
                    discordUtils.sendMsg(SolutionCheckerUtil.checkSolution(this.grid));
                    return "c";
                } else{
                    // clean input to make it XYVALUE and ready for validation
                    cleanedInput = cleanInput(unCleanInput);
                }

                // if input is valid, exit input loop and return the cleaned input
                if(MoveValidationUtil.inputIsValid(cleanedInput, this.grid.getGridSize())) {
                    keepInputting = false;
                }
                else{
                    discordUtils.sendMsg(GameTexts.wrongInputString);
                    return "wrong input";
                }
            }
        }
        System.out.println("cleaned input: " + cleanedInput);
        return cleanedInput;
    }

    // Performs start-up tasks for runGame: create game board and display message to user
    @Override
    public void gameStartUp(){
        // Create gameboard with the preset value
        if(!this.preset.isEmpty()){
            this.grid.createGrid(this.preset);
        }
        // Create empty gameboard if preset is empty or if grid total is zero (i.e. has no values in it)
        else if(this.grid.getGridTotal() == 0){
            this.grid.createGrid();
        }

    }

    // display game board
    public void displayGame(DiscordApp.DiscordUtils discordUtils){
        discordUtils.sendMsg(GameTexts.titleGame);
        discordUtils.sendMsg(this.grid.gridAsString(".", "```"));
    }

    public void displayGame(){
        DiscordApp.DiscordUtils discordUtils = new DiscordApp.DiscordUtils(this.event);
        displayGame(discordUtils);
    }

    public void displayGameEmbeds(){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(GameTexts.titleGame);
        eb.setColor(Color.BLUE);
        eb.addField("input format: *r move X,Y Value", ("```\n-----------------------------------\n" + this.grid.gridAsString(".") + "\n```"), true);
        this.event.getChannel().sendMessage(eb.build()).queue();
    }

    // Main game loop

    //@Override
    public void runGame(){
        PlayerMove move;
        DiscordApp.DiscordUtils discordUtils = new DiscordApp.DiscordUtils(this.event);
        //displayGame(discordUtils);


        // Should I change from this.input, to input variable that is local to this method?
        // get input from user
        this.input = inputLoop(discordUtils);

        // end game/ break loop
        if(this.input.equals("-1") || this.input.equals("c")){
            this.output = "end";
            return;
        }
        // if input is not in correct format, return error message
        else if(this.input.toLowerCase().startsWith("w")){
            this.output = "wrong input";
            return;
        }

        // set move
        move = new PlayerMove(this.input);
        setCellValue(move.getMoveMap(), this.grid);
        // command to send to sudoku event
        this.output = "continue game";
        return;
    }

    // Hacky way to allow runGame to have the same type as the abstract version (void)
    public String returnRunGameValue(){
        System.out.println("returnRunGameValue: " + this.output);
        return this.output;
    }

}
