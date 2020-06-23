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

public class HeroClassList {

    private static HeroClass knightClass = new HeroClass("Knight", EntityClass.RACE_HUMAN, 100.0, 9.0, 3.2, 7.0, 25.0,
            12.5, 25, TextureList.HERO_KNIGHT, TextureList.SIZE_HUMAN);
    private static HeroClass femaleArcher = new HeroClass("Ranger", EntityClass.RACE_HUMAN, 90, 7, 0.5, 20, 75, 17, 60,
            TextureList.HERO_ARCHER, TextureList.SIZE_HUMAN);
    private static HeroClass slasher = new HeroClass("Slasher", EntityClass.RACE_HUMAN, 90, 11, 1.5, 12, 25, 15, 45,
            TextureList.HERO_SLASHER, TextureList.SIZE_HUMAN);
    private static HeroClass barbarian = new HeroClass("Barbarian", EntityClass.RACE_HUMAN, 125, 8.1, 2, 10, 17, 11.5,
            50, TextureList.HERO_BARBARIAN, TextureList.SIZE_HUMAN);

    public static final LinkedList<HeroClass> heroList = new LinkedList<HeroClass>();

    ///////////
    // CLASS ID
    public static final int KNIGHT_CLASS = 100;
    public static final int ARCHER_CLASS = 101;
    public static final int SLASHER_CLASS = 102;
    public static final int BARBARIAN_CLASS = 103;

    public static void initialiseHeroList() {
        //
        heroList.add(knightClass);
        //
        heroList.add(femaleArcher);
        //
        heroList.add(slasher);
        //
        heroList.add(barbarian);
    }

    public static HeroClass getHeroClass(int heroClassNumber) {
        switch (heroClassNumber) {
            case (KNIGHT_CLASS):
                return HeroClassList.knightClass;
            case (ARCHER_CLASS):
                return femaleArcher;
            case (SLASHER_CLASS):
                return HeroClassList.slasher;
            case (BARBARIAN_CLASS):
                return barbarian;
            default:
                return knightClass;
        }
    }

    public static LinkedList<HeroClass> getHeroList() {
        return heroList;
    }

}