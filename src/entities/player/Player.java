
package entities.player;

import entities.*;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;



import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Player extends Entity{
 
    private HeroClass heroClass;
    private int gold;
    private double score = 0;

    public Player(HeroClass hc){
        super(hc);
        ///
        System.out.println("Player created with class: " + hc);
        System.out.println("Player ID: " +this);

        this.gold = hc.getBaseGold();
        this.getShape().setSize(new Vector2f(249,227));
        //setPosition(150,150);
    }
    ///////////////////
    //ACCESSOR METHODS
    //////////////////
    public int getGold(){
        return this.gold;
    }

    public double getScore(){
        return this.score;
    }

    /////////////////
    //MUTATOR METHODS
    /////////////////

    public void addGold(int goldToAdd){
        this.gold+=goldToAdd;
    }

    public void removeGold(int goldToRemove){
        this.gold-=goldToRemove;
    }

    public void addScore(double score){
        this.score += score;
    }

}