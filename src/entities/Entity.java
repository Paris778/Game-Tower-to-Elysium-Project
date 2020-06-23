
package entities;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Entity {
    
    //Stats
    private int race;
    private double maxHealth;
    private double originalMaxHealth;
    private double health;
    private double attack;
    private double armour;
    private double critChance;
    private double getCritDamageModfier;
    private double speed;
    //Appearance
    private RectangleShape shape;
    private Texture entityTexture;
    private Vector2f sizeClass;

    public Entity(EntityClass entClass){
        System.out.println("Into entity");
        System.out.println(entClass);
        this.race = entClass.getRace();
        this.maxHealth = entClass.getHealth();
        this.originalMaxHealth = this.maxHealth;
        this.health = this.maxHealth;
        this.maxHealth = this.health;
        this.attack = entClass.getAttack();
        this.armour = entClass.getArmour();
        this.critChance = entClass.getCritChance();
        this.getCritDamageModfier = entClass.getCritDamageModifier();
        this.entityTexture = entClass.getTexture();
        this.shape = new RectangleShape(entClass.getSize());
        this.shape.setTexture(this.entityTexture);
        this.sizeClass = entClass.getSize();
        this.speed = entClass.getSpeed();
    }

    ////////////////////
    //Accessor methods/
    ///////////////////

    public int getRace(){
        return this.race;
    }

    public double getHealth(){
        return this.health;
    }

    public double getOriginalMaxHealth(){
        return this.originalMaxHealth;
    }

    public double getMaxHealth(){
        return this.maxHealth;
    }

    public double getAttack(){
        return this.attack;
    }

    public double getArmour(){
        return this.armour;
    }

    public double getCritChance(){
        return this.critChance;
    }

    public double getCritDamageModfier(){
        return this.getCritDamageModfier;
    }

    public double getSpeed(){
        return this.speed;
    }

    public RectangleShape getShape(){
        return this.shape;
    }

    public Vector2f getSizeClass(){
        return this.sizeClass;
    }

    public boolean isAlive(){
        if(this.health>0){
            return true;
        }
        return false;
    }

    ///////////////////
    //Set Methods
    //////////////////

    public void setHealth(double health){
        if(health<0){
            health = 0;
        }
        this.health = health;
    }

    public void setMaxHealth(double maxHealth){
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
    }

    public void setAttack(double attack){
        this.attack = attack;
    }
    
    public void setArmour(double armour){
        this.armour = armour;
    }

    public void setPosition(float x,float y){
        this.shape.setPosition(x,y);
    }

    /////////////////////////////
    //Stat modification methods
    ////////////////////////////

    public void takeDamage(double damageAmount){
        //System.out.println("Shit took damage!");
        double damage = damageAmount - this.armour;
        if(damage<0){
            damage = 0;
        }
        this.setHealth(this.getHealth()-damage);
    }

    public void heal(double healAmount){
        this.health+=healAmount;
        if(this.health>this.maxHealth){
            this.health = this.maxHealth;
        }
    }

    public void addAttack(double bonus){
        this.attack+=bonus;
    }

    public void addArmour(double bonus){
        this.armour+=bonus;
    }

    public void increaseMaxHealth(double amount){
        this.maxHealth+=amount;
    }

    ////////////////
    //Other methods
    ////////////////

    public void draw(RenderWindow window){
        window.draw(shape);
    }

}