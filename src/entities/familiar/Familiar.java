
package entities.familiar;

import entities.*;
import entities.enemy.Enemy;
import entities.player.Player;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Familiar {

    private String name;
    private String descriptionSentence;
    private String extendedDescription;
    private int awakeningGoldCost;
    private int familiarClass;
    

    public Familiar(String name,String descriptionSentence,String extendedDescription,int awakenCost,int familiarClass){

        this.name = name;
        this.descriptionSentence = descriptionSentence;
        this.extendedDescription = extendedDescription;
        this.awakeningGoldCost = awakenCost;
        this.familiarClass = familiarClass;
    }

    //EFFECT METHODS

    //Method for familiars to use their special ability
    public void useAbility(Player player, Enemy currentEnemy){
        switch(familiarClass){

        }
    }

    /////////////////////////
    //ACCESSOR METHODS

    public String getName(){
        return this.getName();
    }

    public String getDescriptionSentence(){
        return this.descriptionSentence;
    }

    public String getExtendedDescription(){
        return this.extendedDescription;
    }

    public int getAwakeningCost(){
        return this.awakeningGoldCost;
    }

    public int getFamiliarClass(){
        return this.familiarClass;
    }

    ///////////////////////
    //MUTATOR METHODS

}