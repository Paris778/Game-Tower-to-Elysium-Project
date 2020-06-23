package entities.enemy;

import entities.*;

import org.jsfml.system.Vector2f;



import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy extends Entity{

    private String name;
    private int baseGoldGive;
    private int baseScoreGive;

    public Enemy(EnemyClass ec){
        super(ec);
        this.getShape().setSize(ec.getSize());
        this.name = ec.getName();
        this.baseGoldGive = ec.getBaseGoldGive();
        this.baseScoreGive = ec.getBaseScoreGive();
    }

    ////////////////////
    //ACCESSOR METHODS
    ///////////////////

    public String getName(){
        return this.name;
    }

    public int getBaseGoldGive(){
        return this.baseGoldGive;
      }

    public int getBaseScoreGive(){
        return this.baseScoreGive;
    }

    public void scaleStats(double level){
        //System.out.println("Level: "+level+"\nMAX HEALTH: "+ this.getMaxHealth()+ "\nFactor: " + this.healthModifierFactor(level));
        this.setMaxHealth(this.getMaxHealth()+(this.getMaxHealth()*this.healthModifierFactor(level)));
        //System.out.println("FINAL HEALTH: "+ this.getMaxHealth());
        //System.out.println("Level: "+level+"\nMAX Attack: "+ this.getAttack()+ "\nFactor: " + this.attackModifierFactor(level));
        this.setAttack(this.getAttack()+(this.getAttack()*this.attackModifierFactor(level)));
        //System.out.println("FINAL attack: "+ this.getAttack());
        this.setArmour(this.getArmour()+(this.getArmour()*this.armourModifierFactor(level)));

    }

    //////////////////////////////
    //Stat scalers and modifiers
    ////////////////////////////

    public double healthModifierFactor(double level){
        return level * (level/10200); //doubles around level 100
    }

    public double attackModifierFactor(double level){
        return level * ((2*level)/16200); //doubles around level 112
    }

    public double armourModifierFactor(double level){
        return (2*level) * (level/18500); //doubes around level 115
    }
}
