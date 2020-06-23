package app;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import combat.CombatDriver;
import entities.player.*;
import event.*;
import entities.enemy.*;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

import javax.swing.event.HyperlinkEvent.EventType;

public class Overworld {

    private RenderWindow window;
    private TextWriter textWriter;
    private Vector2f origin;
    private Vector2f center;
    private EventHandler eventHandler;
    private UserInterface userInterface;
    private static CombatDriver combatDriver;
    private EnemyDriver enemyDriver = new EnemyDriver();
    private EventDriver eventDriver;
    private Player player;
    private Enemy currentEnemy;
    private RectangleShape map;

    //Gamepay

    private int level = 1;

    /// Misc
    private static boolean inCombat = false;

    public Overworld(App app) {
        this.window = app.getWindow();
        this.center = new Vector2f(window.getSize().x/2,window.getSize().y/2);
        this.origin = new Vector2f(window.getSize().x-window.getSize().x,window.getSize().y-window.getSize().y);
        this.eventHandler = app.getEventHandler();
        this.player = app.getPlayer();
        this.userInterface = app.getUI();
        this.textWriter = app.getTextWriter();
        initialiseMap();
        initialiseNewEnemy();
        makeCombatDriver();
        makeEventDriver();
    }

    public void draw() {
        window.draw(map);
        player.draw(window);
        currentEnemy.draw(window);
        eventDriver.draw();
    }

    public void initialiseMap() {
        map = new RectangleShape(new Vector2f(1200, 675));
        try {
            map.setTexture(TextureList.getTexture(TextureList.TEST_AREA_1));
            map.setPosition(center.x/2-100,center.y/2-180);

            player.setPosition(map.getPosition().x+50, map.getPosition().y+260);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runEvents() {
        if(!EventDriver.EVENT_IS_HAPPENING){
            if(eventDriver.isEventLevel(this.getLevel())){
                eventDriver.executeEvent(this.getLevel());
            }
            else{ //Else it's a comabt level
                initialiseNewEnemy();
                enterCombat();
            }
        // run other events
        level++;
        }
    }

    public void updateMap() {

    }

    ///////////////////
    // Comabt Methods
    /////////////////

    public void initialiseNewEnemy() {
        currentEnemy = enemyDriver.makeNewEnemy(this.level);
        if(currentEnemy.getSizeClass() == TextureList.SIZE_SMALL){
            currentEnemy.setPosition(map.getPosition().x+780, map.getPosition().y+420);
        } 
        else if(currentEnemy.getSizeClass() == TextureList.SIZE_MEDIUM){
            currentEnemy.setPosition(map.getPosition().x+650, map.getPosition().y+340);
        } 
        else if(currentEnemy.getSizeClass() == TextureList.SIZE_LARGE){
            currentEnemy.setPosition(map.getPosition().x+580, map.getPosition().y+200);
        } 
        
    }

    public void enterCombat() {

        inCombat = true;
        textWriter.add("You encountered a " + currentEnemy.getName());
        System.out.println("You encountered a " + currentEnemy.getName());
        do {
            window.clear();
            this.draw();
            eventHandler.runEvents();
            userInterface.updateHealthBar();
            userInterface.drawUI();
            

            window.display();
        } while (player.isAlive() && currentEnemy.isAlive() && inCombat);

        if (!currentEnemy.isAlive() && player.isAlive()) {
            //System.out.println("ENEMY WAS KILLED !!!");
            this.eventDriver.generateEvent();
        } else if (!player.isAlive() && currentEnemy.isAlive()) {
            System.out.println("YOU DIED !!!!");
        } else {
            System.out.println("you skipped this enemy");
        }
        userInterface.drawUI();
        window.display();

        inCombat = false;
    }

    public static void doOneRound() {
        combatDriver.doOneRound();
    }

    // MISC METHODS

    public static boolean inCombat() {
        return inCombat;
    }

    public static void stopCombat() {
        if (inCombat()) {
            inCombat = false;
        }
    }

    public void makeCombatDriver() {
        this.combatDriver = new CombatDriver(this);
    }

    public void makeEventDriver(){
        this.eventDriver = new EventDriver(this);
    }

    public static CombatDriver getCombatDriver() {
        return combatDriver;
    }

    //////ACCESSOR METHODS
    /////////////////////////
    public int getLevel() {
        return level;
    }

    public Player getPlayer(){
        return this.player;
    }

    public RenderWindow getWindow(){
        return this.window;
    }

    public Enemy getCurrentEnemy(){
        return this.currentEnemy;
    }
    //

    public EventDriver getEventDriver(){
        return this.eventDriver;
    }

    public Vector2f getOrigin(){
        return this.origin;
    }

    public UserInterface getUI(){
        return this.userInterface;
    }

    public TextWriter getTextWriter(){
        return this.textWriter;
    }
}