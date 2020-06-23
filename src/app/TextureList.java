package app;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import entities.enemy.EnemyClassList;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.EventListener;

public class TextureList {

    // AREAS AND MAPS
    public static final int TEST_AREA_1 = 0; // 800 x 450
    // HEROES
    public static final int HERO_KNIGHT = 100;
    public static final int HERO_ARCHER = 101;
    public static final int HERO_SLASHER = 102;
    public static final int HERO_BARBARIAN = 103;
    // SIZES
    public static final Vector2f SIZE_SMALL = new Vector2f(165, 155);
    public static final Vector2f SIZE_MEDIUM = new Vector2f(270, 230);
    public static final Vector2f SIZE_LARGE = new Vector2f(465, 352);
    public static final Vector2f SIZE_HUMAN = new Vector2f(249, 227);

    public static final Texture getTexture(int type) throws IOException {

        Texture texture = new Texture();

        switch (type) {
            /// MAPS
            case TEST_AREA_1:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "overworld" + App.pathVariable + "forestMap.png"));
                break;
            /// HEROES
            case HERO_KNIGHT:
                texture.loadFromFile(FileSystems.getDefault()
                        .getPath(System.getProperty("user.dir") + App.pathVariable + "assets" + App.pathVariable
                                + "mobs" + App.pathVariable + "heros" + App.pathVariable + "Knight.png"));
                break;
            case HERO_ARCHER:
                texture.loadFromFile(FileSystems.getDefault()
                        .getPath(System.getProperty("user.dir") + App.pathVariable + "assets" + App.pathVariable
                                + "mobs" + App.pathVariable + "heros" + App.pathVariable + "ArcherFemale.png"));
                break;
            case HERO_SLASHER:
                texture.loadFromFile(FileSystems.getDefault()
                        .getPath(System.getProperty("user.dir") + App.pathVariable + "assets" + App.pathVariable
                                + "mobs" + App.pathVariable + "heros" + App.pathVariable + "Slasher.png"));
                break;
            case HERO_BARBARIAN:
                texture.loadFromFile(FileSystems.getDefault()
                        .getPath(System.getProperty("user.dir") + App.pathVariable + "assets" + App.pathVariable
                                + "mobs" + App.pathVariable + "heros" + App.pathVariable + "Barbarian.png"));
                break;
            //// ENEMEIES
            //Goblins
            case EnemyClassList.ENEMY_GOBLIN:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Goblin Grunt.png"));
                break;
            case EnemyClassList.ENEMY_GOBLIN_ARCHER:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Goblin Archer.png"));
                break;
            case EnemyClassList.ENEMY_GOBLIN_RAIDER:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Goblin Raider.png"));
                break;
            //Orcs
            case EnemyClassList.ENEMY_ORC_BARSERKER:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Orc Axe Warrior.png"));
                break;
            case EnemyClassList.ENEMY_ORC_PLUNDERER:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Orc Sword Warrior.png"));
                break;
            case EnemyClassList.ENEMY_ORC_ARCHER:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Orc Archer.png"));
                break;
            // Insects
            case EnemyClassList.ENEMY_SCARAB:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Scarab.png"));
                break;
            case EnemyClassList.ENEMY_HERCULES_SCARAB:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Hercules Scarab.png"));
                break;
            case EnemyClassList.ENEMY_BUTTERFLY:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Butterfly.png"));
                break;
            // BOSSES
            case EnemyClassList.BOSS_ORC_CHIEF:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Boss Orc Chief.png"));
                break;
            case EnemyClassList.BOSS_AWAKENED_TREANT:
                texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                        + "assets" + App.pathVariable + "mobs" + App.pathVariable + "Boss Treant.png"));
                break;
        }
        return texture;
    }

    public static Texture getStartScreenBack() {
        Texture texture = new Texture();
        try {
            texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "startscreen1.png"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texture;
    }

    public static Texture getHeroStatsTexture() {
        Texture texture = new Texture();
        try {
            texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "heroUI.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texture;
    }

    public static Texture getHeroStatsMenu() {
        Texture texture = new Texture();
        ;
        try {
            texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "statsMenu.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texture;
    }

    public static Texture getEnemyStatsTexture() {
        Texture texture = new Texture();
        ;
        try {
            texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "EnemyBarStats.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texture;
    }

    public static Texture getShopTexture() {
        Texture texture = new Texture();
        ;
        try {
            texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "shopUI.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }

    public static Texture getDeathScreen(){
        Texture texture = new Texture();
        try {
            texture.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "Death Screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }

}