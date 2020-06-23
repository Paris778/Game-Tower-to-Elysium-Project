package event;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import app.App;
import app.EventHandler;
import app.Overworld;
import app.TextureList;
import combat.CombatDriver;
import entities.player.*;
import item.Item;
import item.ItemClassList;
import entities.enemy.*;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Shop {

    private Overworld overworld;
    private RectangleShape windowBackground;
    private Text topLabels, instructions;
    private final int NUMBER_OF_ITEMS_IN_SHOP = 5;
    private Text itemText[] = new Text[NUMBER_OF_ITEMS_IN_SHOP];
    private Item itemArray[] = new Item[NUMBER_OF_ITEMS_IN_SHOP];
    private Font font;
    private Random rand = new Random();

    public Shop(Overworld overworld) {
        this.overworld = overworld;
        initialiseShop();
    }

    private void initialiseShop() {
        font = new Font();
        try {
            font.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "IMMORTAL.ttf"));
        } catch (IOException e) {

            e.printStackTrace();
        }
        ////////////
        // Graphics
        windowBackground = new RectangleShape(new Vector2f(1000, 600));
        windowBackground.setTexture(TextureList.getShopTexture());
        windowBackground.setPosition(overworld.getOrigin().x + 350, overworld.getOrigin().y + 170);
        /////////////////
        /// Instructions
        instructions = new Text("Select one of the numbered options with the number keys. ESC to close shop.", font);
        instructions.setCharacterSize(17);
        instructions.setColor(Color.BLACK);
        instructions.setPosition(windowBackground.getPosition().x + 195, windowBackground.getPosition().y + 535);
        ////////
        // Item Text
        topLabels = new Text(
                String.format("%-3s    %-17s      %-5s %-9s       %5s", "Num", "Item Name", "Bonus", "", "Price"),
                font);
        topLabels.setCharacterSize(25);
        topLabels.setColor(Color.BLACK);
        topLabels.setPosition(windowBackground.getPosition().x + 120, windowBackground.getPosition().y + 165);
        //
        for (int i = 0; i < NUMBER_OF_ITEMS_IN_SHOP; i++) {
            itemText[i] = new Text("", font);
            itemText[i].setCharacterSize(23);
            itemText[i].setColor(Color.BLACK);
            itemText[i].setPosition(windowBackground.getPosition().x + 120,
                    windowBackground.getPosition().y + 215 + (50 * i));
        }
    }

    public void drawShop() {
        this.overworld.getWindow().draw(this.windowBackground);
        // Numbers and crap;
        updateShopText();
        //
        this.overworld.getWindow().draw(this.topLabels);
        this.overworld.getWindow().draw(this.instructions);
        for (Text text : itemText) {
            this.overworld.getWindow().draw(text);
        }
    }

    ///
    public void updateShopText() {
        for (int i = 0; i < NUMBER_OF_ITEMS_IN_SHOP; i++) {
            this.itemText[i].setString(String.format("(" + (i + 1) + ")" + "     %-23.23s  + %-4.2f %12s     %4d G",
                    itemArray[i].getName(), (float) itemArray[i].getBonusAmount(), itemArray[i].getBonusCategory(),
                    itemArray[i].getPrice()));
        }
    }

    ////

    public void executeShop() {
        System.out.println("SHOP EXECUTED---------------");
        generateItems();
        drawShop();
    }

    public void executePurchase(int orderNumber) {

        // If they have enough gold
        if (overworld.getPlayer().getGold() >= itemArray[orderNumber].getPrice()) {

            itemArray[orderNumber].equipItem(overworld.getPlayer());
            overworld.getPlayer().removeGold(itemArray[orderNumber].getPrice());
        } else {
            overworld.getTextWriter().add("Not enough gold");
        }

    }

    public void generateItems() {
        for (int i = 0; i < itemArray.length; i++) {
            int randomItemType = rand.nextInt(4) + 1;
            itemArray[i] = new Item(ItemClassList.getItemClass(randomItemType));
        }
    }

}