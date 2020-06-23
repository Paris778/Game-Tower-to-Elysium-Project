package entities.enemy;

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

public class EnemyClassList {

    // ENEMIES
    public static final int ENEMY_GOBLIN = 1000;
    public static final int ENEMY_GOBLIN_ARCHER = 1001;
    public static final int ENEMY_GOBLIN_RAIDER = 1002;
    //
    public static final int ENEMY_ORC_BARSERKER = 1003;
    public static final int ENEMY_ORC_PLUNDERER = 1004;
    public static final int ENEMY_ORC_ARCHER = 1005;
    //
    public static final int ENEMY_SCARAB = 1006;
    public static final int ENEMY_HERCULES_SCARAB = 1007;
    public static final int ENEMY_BUTTERFLY = 1008;
    ////////
    // BOSSES
    public static final int BOSS_ORC_CHIEF = 5000;
    public static final int BOSS_AWAKENED_TREANT = 5001;

    // Manual
    /*
     * (1) Name (2) Class (3) Health (4) Attack (5) Armour (6) Crit.Chance % (7)
     * Crit Damage Modifier % (8) Speed (9) Base Gold Give (10) Texture ID (11) Size
     * Vector (12) Base Score give
     * 
     */

    // Goblins
    private static EnemyClass goblin = new EnemyClass("Goblin", EntityClass.RACE_MONSTER, 10.0, 4.0, 0.2, 2.0, 25.0, 5,
            5, ENEMY_GOBLIN, TextureList.SIZE_SMALL,10);
    private static EnemyClass goblinWarrior = new EnemyClass("Goblin Raider", EntityClass.RACE_MONSTER, 15, 4.5, 0.5,
            2.0, 25.0, 6, 6, ENEMY_GOBLIN_RAIDER, TextureList.SIZE_SMALL,11);
    private static EnemyClass goblinArcher = new EnemyClass("Goblin Archer", EntityClass.RACE_MONSTER, 8, 4.5, 0.1, 2.5,
            25.0, 10, 7, ENEMY_GOBLIN_ARCHER, TextureList.SIZE_SMALL,11);
    // Orcs
    private static EnemyClass orcBerserker = new EnemyClass("Orc Berserker", EntityClass.RACE_MONSTER, 22.0, 5.0, 0.3,
            2.0, 25.0, 11, 10, ENEMY_ORC_BARSERKER, TextureList.SIZE_MEDIUM,15);
    private static EnemyClass orcPlunderer = new EnemyClass("Orc Plunderer", EntityClass.RACE_MONSTER, 25.0, 5.4, 0.4,
            2.0, 25.0, 12, 11, ENEMY_ORC_PLUNDERER, TextureList.SIZE_MEDIUM,15);
    private static EnemyClass orcArcher = new EnemyClass("Orc Archer", EntityClass.RACE_MONSTER, 20.0, 5.8, 0.2, 2.5,
            25.0, 13, 12, ENEMY_ORC_ARCHER, TextureList.SIZE_MEDIUM,17);
    // Insects
    private static EnemyClass scarab = new EnemyClass("Scarab", EntityClass.RACE_INSECT, 25.0, 2.0, 20, 10, 10, 5, 15,
            ENEMY_SCARAB, TextureList.SIZE_SMALL,8);
    private static EnemyClass moth = new EnemyClass("Moth", EntityClass.RACE_INSECT, 14.0, 3.5, 0.5, 22, 45, 18, 26,
            ENEMY_BUTTERFLY, TextureList.SIZE_MEDIUM,22);
    // Mini_Bosses
    private static EnemyClass monstrusScarab = new EnemyClass("Monstrus Scarab", EntityClass.RACE_INSECT, 65.0, 7.2,
            3.5, 12, 45, 16, 42, ENEMY_HERCULES_SCARAB, TextureList.SIZE_LARGE,50);
    // Bosses
    private static EnemyClass bossOrcChief = new EnemyClass("Orc Chief", EntityClass.RACE_MONSTER, 85.0, 10.5, 3.2, 2.8,
            50.0, 16, 120, BOSS_ORC_CHIEF, TextureList.SIZE_LARGE,100);
    private static EnemyClass bossTreant = new EnemyClass("Awakened Treant", EntityClass.RACE_MONSTER, 102.0, 12.5, 7.5,
            2.8, 50.0, 13, 250, BOSS_AWAKENED_TREANT, TextureList.SIZE_LARGE,200);

    public static EnemyClass getEnemyClass(int enemyClass) {

        switch (enemyClass) {
            //Goblin
            case ENEMY_GOBLIN:
                return goblin;
            case ENEMY_GOBLIN_RAIDER:
                return goblinWarrior;
            case ENEMY_GOBLIN_ARCHER:
                return goblinArcher;
            //Orc
            case ENEMY_ORC_BARSERKER:
                return orcBerserker;
            case ENEMY_ORC_PLUNDERER:
                return orcPlunderer;
            case ENEMY_ORC_ARCHER:
                return orcArcher;
            //Insects
            case ENEMY_SCARAB:
                return scarab;
            case ENEMY_BUTTERFLY:
                return moth;
            case ENEMY_HERCULES_SCARAB:
                return monstrusScarab;
            //Bosses
            case BOSS_ORC_CHIEF:
                return bossOrcChief;
            case BOSS_AWAKENED_TREANT:
                return bossTreant;
            //
            default:
                return goblin;
        }
    }

}