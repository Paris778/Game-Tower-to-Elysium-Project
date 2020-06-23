package entities;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import app.TextureList;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

//Abstract Class for entities(heroes, enemies)
public class EntityClass {

    // CONSTANT RACES
    public static final int RACE_HUMAN = 0;
    public static final int RACE_UNDEAD = 1;
    public static final int RACE_ELF = 2;
    public static final int RACE_MONSTER = 3;
    public static final int RACE_WILD_BEAST = 4;
    public static final int RACE_INSECT = 5;
    public static final int RACE_DRAGON = 6;

    private int race;
    private double baseHealth;
    private double baseAttack;
    private double baseArmour;
    private double baseCritChance;
    private double crtiDamageModifier;
    private double speed;
    private int entityTextureID;
    private Vector2f size;
    // private int startGold;

    public EntityClass(int race, double baseHealth, double baseAttack, double baseArmour, double baseCritChance,double crtiDamageModifier, double speed,
            int textureID, Vector2f size) {
        this.race = race;
        System.out.println("This race is: " + race);
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.baseArmour = baseArmour;
        this.baseCritChance = baseCritChance;
        this.speed = speed;
        this.crtiDamageModifier = crtiDamageModifier;
        this.entityTextureID = textureID;
        this.size = size;
    }

    ////////////////////
    // Accessor methods/
    ///////////////////

    public int getRace() {
        return this.race;
    }

    public double getHealth() {
        return this.baseHealth;
    }

    public double getAttack() {
        return this.baseAttack;
    }

    public double getArmour() {
        return this.baseArmour;
    }

    public double getCritChance() {
        return this.baseCritChance;
    }

    public double getCritDamageModifier() {
        return this.crtiDamageModifier;
    }

    public double getSpeed(){
        return this.speed;
    }

    public Texture getTexture() {
        try {
            return TextureList.getTexture(this.entityTextureID);
        } catch (IOException e) {
            System.out.println("ERROR: TEXTURE NOT FOUND");
            e.printStackTrace();
        }
        return null;
    }

    public Vector2f getSize() {
        return this.size;
    }

}