package item;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import app.App;
import app.EventHandler;
import app.Overworld;
import app.TextureList;
import combat.CombatDriver;
import entities.player.*;
import entities.enemy.*;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Item {

    /// CONSTANTS
    private final int STANDARD_POTION_PRICE = 35;
    private final int STANDARD_WEAPON_PRICE = 80;
    private final int STANDARD_ARMOUR_PRICE = 65;
    private final int STANDARD_RING_PRICE = 72;

    //////////////////
    // Item Variables
    private String name;
    private int itemType;
    private String bonusCategory;
    private double bonusAmount;
    private int price;
    private Random rand = new Random();

    ///////////////////////
    /// Lists With Names

    private String[] qualityNames = {"","Bloodstained","Dull","Blessed","Rusty","Iron","Shiny","Cursed","Bronze","Steel","Ancient","Enchanted","Polished","Royal","Fiery","Sacred","Stained","Magic","Moonlight","Legendary","Epic"};
    private String[] weaponNames = {"Dagger","Straight Sword","Pike","Sabre","Greatsword","Curved Sword","Katana","Rapier","Claymore","Spear","Haldberg","Scythe","Twinswords","Blade","Scimitar","Falchion"};
    private String[] armourNames = {"Chestplate","Gauntlets","Boots","Helmet","Bracers","Leggings","Breastplate","Pauldron"};

    /////////////////
    // Constructor
    public Item(int itemType) {
        this.itemType = itemType;
        switch (itemType) {
            case ItemClassList.POTION:
                generatePotionItem();
                break;
            case ItemClassList.WEAPON:
                generateWeaponItem();
                break;
            case ItemClassList.ARMOUR:
                generateArmourItem();
                break;
            case ItemClassList.RING:
                generateRingItem();
                break;
            default:
                generatePotionItem();
                break;

        }
    }

    ///////////////////////
    //// Make Items Methods

    public void generatePotionItem() {
        this.name = "Healing Potion";
        this.bonusCategory = "Health";
        this.bonusAmount = (double) rand.nextInt(45) + 25;
        this.price = (int) (((int) this.bonusAmount / 26) * (STANDARD_POTION_PRICE * (150 / (rand.nextInt(70) + 100)))
                + rand.nextInt(25)); // Can
        if (this.price < STANDARD_POTION_PRICE) {
            generatePotionItem();
        }
    }

    public void generateWeaponItem() {
        this.name = generateWeaponName();
        this.bonusCategory = "Attack";
        this.bonusAmount = rand.nextInt(25) + 10;
        this.price = (int) (((((int) this.bonusAmount) / 9) * (STANDARD_WEAPON_PRICE * (150 / ((rand.nextInt(92) + 75)))))+ rand.nextInt(25));
        if (this.price < STANDARD_WEAPON_PRICE) {
            generateWeaponItem();
        }
    }

    public void generateArmourItem() {
        this.name = generateArmourName();
        this.bonusCategory = "Armour";
        this.bonusAmount = rand.nextInt(10) + 2;
        this.price = (int) (((int) this.bonusAmount / 3.2) * (STANDARD_ARMOUR_PRICE * (150 / (rand.nextInt(92) + 75)))
                + rand.nextInt(35));
        if (this.price < STANDARD_ARMOUR_PRICE) {
            generateArmourItem();
        }
    }

    public void generateRingItem() {
        this.name = "Ring of Vitality";
        this.bonusCategory = "Max Health";
        this.bonusAmount = rand.nextInt(20) + 10;
        this.price = (int) (((int) this.bonusAmount / 8) * (STANDARD_RING_PRICE * (150 / (rand.nextInt(92) + 75)))
                + rand.nextInt(20));
        if (this.price < STANDARD_RING_PRICE) {
            generateRingItem();
        }

    }
    //////////////////
    //Generate Names

    public String generateWeaponName(){
        return (qualityNames[rand.nextInt(qualityNames.length)] + " "+ weaponNames[rand.nextInt(weaponNames.length)]);
    }

    public String generateArmourName(){
        return (qualityNames[rand.nextInt(qualityNames.length)] + " "+ armourNames[rand.nextInt(armourNames.length)]);
    }

    //////////////////
    // Equipt etc
    public void equipItem(Player player) {
        switch (this.itemType) {
            case ItemClassList.POTION:
                player.heal(this.bonusAmount);
                break;
            case ItemClassList.WEAPON:
                player.addAttack(this.bonusAmount);
                break;
            case ItemClassList.ARMOUR:
                player.addArmour(this.bonusAmount);
                break;
            case ItemClassList.RING:
                player.increaseMaxHealth(this.bonusAmount);
                break;
            default:
                System.out.println("No item detected");
                break;

        }
    }
    //////////////////////
    // Accessor Methods

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public double getBonusAmount() {
        return this.bonusAmount;
    }

    public String getBonusCategory() {
        return this.bonusCategory;
    }

    ////////////////////
    // Mutator Methods

}