
package app;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import combat.CombatDriver;
import entities.player.HeroClass;
import entities.player.HeroClassList;
import entities.player.Player;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

public class App {

    private RenderWindow window;
    public static String pathVariable;
    private Player player;
    private Overworld overworld;
    private UserInterface userInterface;
    /// public static Map currentMap;
    // private static LinkedList<FloatRect> intersects;
    private static Controller controller;
    private EventHandler eventHandler;
    private CombatDriver combatDriver;
    public static boolean selectingplayer = true;
    private TextWriter textWriter;
    public Vector2f origin;
    private Font font = new Font();
    public static boolean keepGoing = true;
    //
    ////////////////////////////////
    /// Player selection

    private RectangleShape spriteContainer;
    private Text selectingPlayerText;
    private int playerSelectionCounter = 100;
    
    /////
   

    /**
     * Constructor
     */
    public App(String pathVariable) {
        this.pathVariable = pathVariable;
        window = new RenderWindow();
        window.create(VideoMode.getDesktopMode(), "Just another game");
        controller = new Controller(window, player, this);
        eventHandler = new EventHandler(window, controller);
        window.setFramerateLimit(60);
        window.setKeyRepeatEnabled(false); // Ensures the key repeat doesn't break player movement
        initialiseUIStuff();
        HeroClassList.initialiseHeroList();
      
        run();
    }
  
    public void stop() {
        window.close();
        // music.stop();
        System.exit(1);
    }

    /**
     * Method to run main events
     */
    public void run() {

        MusicDriver.startStartScreenMusic();

        //Initialise userInterface
        if(this.userInterface == null){
            userInterface = new UserInterface(this);
        }
        

        while (userInterface.isInStartScreen()) {
            window.clear();
            userInterface.drawStartScreen(window);
            window.display();
            eventHandler.runEvents();
        }

        while (!userInterface.isInStartScreen() && selectingplayer) {
            runPlayerSelection(eventHandler, HeroClassList.getHeroList());
        }

        do {
            System.out.println("===============================\nREINCARNATION");

            makeOverworld();
            this.player = overworld.getPlayer();
            userInterface.initialiseUiAfterPlayerCreation();
            System.out.println(window.isOpen());
            System.out.println(player.isAlive());
            System.out.println(userInterface.isInStartScreen());
            eventHandler.runEvents();

            while (player.isAlive() && !userInterface.isInStartScreen() && !selectingplayer) {

                // overworld.draw();
                System.out.println("=========\nLevel: " + overworld.getLevel() + "\n=========");

                window.display();

                overworld.runEvents();

                eventHandler.runEvents();

                // player.draw();
                // ui.drawUI();

                // ui.drawMouse();
                // window.display();
            }
        } while (window.isOpen());
    }

    public void resetGame() {
        System.out.println("TIME TO RESET THE GAME");
        window.clear();
        MusicDriver.stopBattleMusic();
        userInterface.reset();
        selectingplayer = true;
        run();
    }

    public void makePlayer() {
        selectingplayer = false;
        player = new Player(HeroClassList.getHeroClass(playerSelectionCounter));
        player.setPosition(150, 150);
    }

    public void makeOverworld() {
        overworld = new Overworld(this);
    }

    public UserInterface getUI() {
        return this.userInterface;
    }

    public Overworld getOverworld() {
        return overworld;
    }

    public Player getPlayer() {
        return this.player;
    }

    public EventHandler getEventHandler() {
        return this.eventHandler;
    }

    public RenderWindow getWindow() {
        return this.window;
    }

     ////////////////////////////////
    //// HERO SELECTION
    public void runPlayerSelection(EventHandler eventHandler,LinkedList<HeroClass> heroClassList) {

        playerSelectionCounter = 100;
        
        System.out.println("In player selection");

        ///BOXES FOR GRAPHICS 

        RectangleShape menu = new RectangleShape(new Vector2f(600, 600));
        menu.setPosition(new Vector2f(origin.x+950, origin.y+200));
        menu.setTexture(TextureList.getHeroStatsMenu());
        RectangleShape spriteContainer = new RectangleShape(new Vector2f(250, 250));
        spriteContainer.setPosition(new Vector2f(origin.x+500, origin.y+400));

        //TEXT
        Text selectingPlayerText = new Text("           SELECT CHARACTER\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n<-  Z                                     C  ->\n\n             SPACE To confirm", font);
        selectingPlayerText.setCharacterSize(32);
        selectingPlayerText.setColor(Color.WHITE);
        selectingPlayerText.setPosition(new Vector2f(origin.x+600, origin.y+100));
        //
        Text heroName = new Text("",font);
        heroName.setCharacterSize(32);
        heroName.setColor(Color.WHITE);
        heroName.setPosition(new Vector2f(spriteContainer.getPosition().x+55, spriteContainer.getPosition().y-100));
        //
        Text stastTitle = new Text("Stats",font);
        stastTitle.setCharacterSize(41);
        stastTitle.setColor(Color.BLACK);
        stastTitle.setPosition(new Vector2f(menu.getPosition().x+255, menu.getPosition().y+70));
        //
        Text playerStatsTitles = new Text("Health:\n\nAttack:\n\nArmor:\n\nCrit Chance:\n\nCrit Damage:\n\nSpeed\n\nGold:",font);
        playerStatsTitles.setCharacterSize(22);
        playerStatsTitles.setColor(Color.BLACK);
        playerStatsTitles.setPosition(new Vector2f(menu.getPosition().x+115, menu.getPosition().y+170));
        //
        
        Text playerStatsValues = new Text("",font);
        //
        playerStatsValues.setCharacterSize(22);
        playerStatsValues.setColor(Color.BLACK);
        playerStatsValues.setPosition(new Vector2f(menu.getPosition().x+425, menu.getPosition().y+170));

        

        while (App.selectingplayer) {
            eventHandler.runEvents();
            window.clear();
            try {
                spriteContainer.setTexture(TextureList.getTexture(playerSelectionCounter),true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Update Value Display
            playerStatsValues.setString(String.valueOf(String.valueOf(heroClassList.get(playerSelectionCounter-100).getHealth())+"\n\n" 
            + String.valueOf(heroClassList.get(playerSelectionCounter-100).getAttack())+"\n\n"
            + String.valueOf(heroClassList.get(playerSelectionCounter-100).getArmour())+"\n\n"
            + String.valueOf(heroClassList.get(playerSelectionCounter-100).getCritChance())+"%"+"\n\n"
            + "+"+ String.valueOf(heroClassList.get(playerSelectionCounter-100).getCritDamageModifier())+"%"+"\n\n"
            + String.valueOf(heroClassList.get(playerSelectionCounter-100).getSpeed())+"\n\n"
            + String.valueOf(heroClassList.get(playerSelectionCounter-100).getBaseGold())+"\n\n"));
            //
            heroName.setString(heroClassList.get(playerSelectionCounter-100).getName());

            // DRAW
            window.draw(menu);
            //
            window.draw(spriteContainer);
            //
            window.draw(selectingPlayerText);
            window.draw(playerStatsTitles); 
            window.draw(stastTitle);
            window.draw(playerStatsValues);
            window.draw(heroName); 
            //
            window.display();
        }
    }

    public void increasePlayerSelectionCount(){
        this.playerSelectionCounter++;
        if(this.playerSelectionCounter>(100+HeroClassList.getHeroList().size()-1)){
            this.playerSelectionCounter=100;
        }
        System.out.println("Selection: " + playerSelectionCounter);
    }

    public void decreasePlayerSelectionCount(){
        this.playerSelectionCounter--;
        if(this.playerSelectionCounter<(100)){
            this.playerSelectionCounter=(100+HeroClassList.getHeroList().size()-1);
        }
        System.out.println("Selection: " + playerSelectionCounter);
    }

    public int getPlayerSelectionCount(){
        return this.playerSelectionCounter;
    }


    public void initialiseUIStuff(){
        this.textWriter = new TextWriter(window);
        this.origin = new Vector2f(window.getSize().x - window.getSize().x, window.getSize().y - window.getSize().y);
        try {
            font.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "IMMORTAL.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextWriter getTextWriter(){
        return this.textWriter;
    }

}