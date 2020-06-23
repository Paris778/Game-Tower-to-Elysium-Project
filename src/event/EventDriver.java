package event;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import app.Overworld;
import combat.CombatDriver;
import entities.player.*;
import entities.enemy.*;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class EventDriver {

    // Constants
    public static final int EVENT_HEAL = 1;
    public static final int EVENT_LOOT = 2;
    //Statics
    public static boolean SHOP_ACTIVE = false;
    public static boolean EVENT_IS_HAPPENING = false;
    // Variables
    private Random rand = new Random();
    private Overworld overworld;
    private List<Integer> smallAfterCombatEvents = Arrays.asList(10,30,40);
    private List<Integer> shopEventLevels = Arrays.asList(10,30,45,55,65,85,100,150,175,202,250,275,300,350,400,450,505);
    private Shop shop;
    

    public EventDriver(Overworld overworld) {
        this.overworld = overworld;
        shop = new Shop(overworld);

    }

    public void draw(){
        if(EventDriver.SHOP_ACTIVE){
            this.shop.drawShop();
        }
    }

    public boolean isEventLevel(int level){
        //If this level is a shop level
        if(shopEventLevels.contains(level)){
            return true;
        }
        return false;
    }

    ///

    public void generateEvent() {
        if (smallCombatEventIsHappening(overworld.getLevel())) {
            System.out.println("EVENT IS HAPPENING");
            switch (generateRandomEventNumber()) {
                case 0:
                //Nothing
                break;
                case 1:
                    executeHealEvent();
                    break;
                case 2:
                    executeLootEvent();
                    break;
                default:
                //Nothing
                break;
            }
        }

    }

    //EVENT METHODS

    public void executeEvent(int level){
        EventDriver.EVENT_IS_HAPPENING = true;
        if(shopEventLevels.contains(level)){
            executeShopEvent();
        }
        EventDriver.EVENT_IS_HAPPENING = false;
    }

    private void executeHealEvent(){
        double heal = rand.nextInt(25)+5;
        overworld.getPlayer().heal(heal);
        overworld.getTextWriter().add("The forest fairies healed you by " + heal + " HP");
    }

    private void executeLootEvent(){
        double bonus = rand.nextInt(15)+1;
        overworld.getPlayer().addAttack(bonus);
        overworld.getTextWriter().add("You found loot and gained " + bonus + " attack");
    }

    private void executeShopEvent(){
        SHOP_ACTIVE = true;
        shop.executeShop();
    }
    //

    private boolean smallCombatEventIsHappening(int level){
        if(smallAfterCombatEvents.contains(level)){
            return true;
        }
        else if(15 > rand.nextInt(100)){ //15% chance of an event
            return true;
        }
        return false;
    }

    private boolean questEventIsHappening(){
        if(1<rand.nextInt(50)){
            return true;
        }
        return false;
    }


    //////////////
    //RNG METHODS
    //////////////
    private int generateRandomEventNumber() {
        int x = 0;
        int r = rand.nextInt(1000);
        if (r < 450) {
            x = EVENT_HEAL;
        } else if (r < 620) {
            x = EVENT_LOOT;
        }
        else{
            System.out.println("Unlucky, no event ;;;;;; ");
        }

        return x;
    }

    ///ACCESSOR METHODS

    public Shop getShop(){
        return this.shop;
    }

}