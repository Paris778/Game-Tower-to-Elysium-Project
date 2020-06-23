package entities.player;

import entities.*;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import app.TextureList;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;



public class HeroClass extends EntityClass{

    private int baseGold;
    private String name;
   
    public HeroClass(String name,int race,double baseHealth,double baseAttack, double baseArmour, double baseCritChance,double critDamageModifier,double speed,int baseGold,int textureID,Vector2f size){
        super(race,baseHealth,baseAttack,baseArmour,baseCritChance,critDamageModifier,speed,textureID,size);
        this.name= name;
        this.baseGold = baseGold;
    }

    public int getBaseGold(){
        return this.baseGold;
    }

    public String getName(){
        return this.name;
    }
}
