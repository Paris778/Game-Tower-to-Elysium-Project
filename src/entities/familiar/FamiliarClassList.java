
package entities.familiar;

import entities.*;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

public class FamiliarClassList {

    //Slime class integers

    private static final int ATTACK_SLIME = 0;
    private static final int DEFEND_SLIME = 1;
    private static final int HEAL_SLIME = 2;
    private static final int BUFFER_SLIME = 3;
    private static final int DEBUFFER_SLIME = 4;
    private static final int CROWN_SLIME = 5;
    private static final int LIGHT_SLIME = 6;
    private static final int DARK_SLIME = 7;
    private static final int WIND_SLIME = 8;
    private static final int UNDEAD_SLIME = 9;
    private static final int EARTH_SLIME = 10;
    private static final int ICE_SLIME = 11;

    //Slime instances

    private static Familiar attackSlime = new Familiar("Attacker Slime", "Boosts you damage output", "This slime boosts the damage of each of your hits by 5%", 100, ATTACK_SLIME);
    private static Familiar defenderSlime = new Familiar("Slime Defender", "Boosts your defences", "This slime gives you 10 additional points of armour and increases your armour by 10%", 100, DEFEND_SLIME);
    private static Familiar medicSlime = new Familiar("Healer Slime", "Increases player health", "This slime increases the maximum player health by 25 and heal the player by 40 points after awakening. 5% chance to heal the player after defeating a monster", 125,HEAL_SLIME);
    private static Familiar buffSlime = new Familiar("Buffer Slime", "Buffs the players stats", "This slime increases maximum health,attack and armour by 5%", 115, BUFFER_SLIME);
    private static Familiar debuffSlime = new Familiar("Debuff Slime", "Debuffs enemies", "This slime decreases enemy maximum health and attack by 10% for the rest of the game.", 100, DEBUFFER_SLIME);
    private static Familiar crownSlime = new Familiar("Crown Slime", "Makes items cheaper", "This slime reduces the prices in all shops by 15% for the rest of the game. Uppon awakening gives 300 gold to the player", 100, CROWN_SLIME);
    private static Familiar lightSlime = new Familiar("Light Slime", "Greately boosts hero stats", "This slime increases all player stats by 10% and  increases player speed by 10 uppon awakening. Increase hit damage by 2% for the rest of the game", 100, LIGHT_SLIME);
    private static Familiar darkSlime = new Familiar("Dark Slime", "Steals health from your opponent", "This slime gives a 10% chance to heal the hero by 5% of the damage taken by the enemy", 100, DARK_SLIME);
    private static Familiar windSlime = new Familiar("Wind Slime", "Boosts speed and critical damage", "This slime increases hero speed by 5, critical chance by 15% and critical damage by 5%", 100, WIND_SLIME);
    private static Familiar undeadSlime = new Familiar("Undead Slime", "Radiates the essence of the undead", "This slime decreases enemy armour by 40%, enemy attack by 5% and enemy maximum heatlh by 10%. Hero maximum health is decreased by 30 points uppon awakening", 100, UNDEAD_SLIME);
}