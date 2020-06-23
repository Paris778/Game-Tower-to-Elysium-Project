package item;

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

public class ItemClassList {

    // ENEMIES
    public static final int POTION = 1;
    public static final int WEAPON = 100;
    public static final int ARMOUR = 1000;
    public static final int RING = 1500;

    //Manual
    /*
    (1) Item Type
    (2) 
    (3) 
    (4)

    */

    //private static ItemClass potion = new ItemClass(POTION);
    //private static ItemClass weapon = new ItemClass(WEAPON);
    //private static ItemClass armour = new ItemClass(ARMOUR);
    //private static ItemClass ring = new ItemClass(RING);
   


    public static int getItemClass(int itemClass) {

        switch (itemClass) {
            case 1:
                return POTION;
            case 2:
                return WEAPON;
            case 3:
                return ARMOUR;
            case 4:
                return RING;
            //
            default:
                return POTION;
        }
    }

}