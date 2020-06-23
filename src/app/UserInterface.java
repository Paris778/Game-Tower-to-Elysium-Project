package app;

import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import entities.player.HeroClass;
import entities.player.HeroClassList;
import entities.player.Player;

import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.LinkedList;

public class UserInterface {

    private RenderWindow window;
    private TextWriter textWriter;
    private Vector2f origin;
    private Vector2f center;
    private App overworld;
    private RectangleShape heroStatsShape = new RectangleShape(new Vector2f(864, 300));
    private RectangleShape enemyStatsShape = new RectangleShape(new Vector2f(630, 300));
    private static final float HERO_HEALTHBAR_WIDTH = 300, HERO_HEALTHBAR_HEIGHT = 25, ENEMY_HEALTHBAR_WIDTH = 200,
            ENEMY_HEALTHBAR_HEIGHT = 25;
    private RectangleShape healthBarHero = new RectangleShape(
            new Vector2f(HERO_HEALTHBAR_WIDTH, HERO_HEALTHBAR_HEIGHT));
    private RectangleShape maxHealthBarHero = new RectangleShape(
            new Vector2f(HERO_HEALTHBAR_WIDTH, HERO_HEALTHBAR_HEIGHT));
    private RectangleShape healthBarEnemy = new RectangleShape(
            new Vector2f(ENEMY_HEALTHBAR_WIDTH, ENEMY_HEALTHBAR_HEIGHT));
    private RectangleShape customMouse = new RectangleShape(new Vector2f(50, 70));
    private RectangleShape deathScreen;
    private RectangleShape inventory = new RectangleShape(new Vector2f(250, 620));
    // private InventoryShape[] boxes = {new InventoryShape(95, 95, Color.BLUE,
    // Color.WHITE, 0), new InventoryShape(95, 95, Color.BLUE, Color.WHITE, 0), new
    // InventoryShape(95, 95, Color.BLUE, Color.WHITE, 0), new InventoryShape(95,
    // 95, Color.BLUE, Color.WHITE, 0), new InventoryShape(95, 95,Color.BLUE,
    // Color.WHITE, 0)};
    private RectangleShape startScreen;
    private Text currentLevel, heroHealth, heroAttack, heroArmour, currencyDisplay, enemyName, enemyHealth, enemyAttack,
            enemyArmour,currentScore;
    private Text startScreenText, deathScreenText;
    private Font font = new Font();
    private static boolean inStartScreen = true;
    private boolean selectingCharacter = true;
    private Player player;
    private RectangleShape endScreen;


    public UserInterface(App app) {
        this.overworld = app;
        this.player = app.getPlayer();
        this.window = app.getWindow();
        this.center = new Vector2f(window.getSize().x / 2, window.getSize().y / 2);
        this.origin = new Vector2f(window.getSize().x - window.getSize().x, window.getSize().y - window.getSize().y);
        this.textWriter = app.getTextWriter();
        initialiseUi();
    }

    public void drawUI() {

        this.updateHealthBar();
        this.updateNumberDisplay();
        // Shapes
        window.draw(heroStatsShape);
        window.draw(enemyStatsShape);
        window.draw(maxHealthBarHero);
        window.draw(healthBarHero);
        window.draw(healthBarEnemy);
        // Numbers
        window.draw(currentLevel);
        window.draw(currentScore);
        window.draw(heroHealth);
        window.draw(heroAttack);
        window.draw(heroArmour);
        window.draw(currencyDisplay);
        //
        window.draw(enemyName);
        window.draw(enemyHealth);
        window.draw(enemyAttack);
        window.draw(enemyArmour);
        //
        textWriter.run();
        // drawInventory();
        if (!player.isAlive()) {
            window.draw(deathScreen);
            window.draw(deathScreenText);
        }
    }

    /*
     * public void drawMouse(){
     * customMouse.setPosition(window.mapPixelToCoords(Mouse.getPosition(),
     * player.getView()).x-34, window.mapPixelToCoords(Mouse.getPosition(),
     * player.getView()).y-30); window.draw(customMouse); }
     */

    /*
     * public void drawInventory() { window.draw(inventory); for (int i = 0; i <
     * boxes.length; i++) { Texture view = this.player.getInventory().itemView(i);
     * // System.out.println(view); if (view == null)
     * boxes[i].getrecc().setFillColor(Color.TRANSPARENT); else {
     * boxes[i].getrecc().setFillColor(Color.WHITE);
     * boxes[i].getrecc().setTexture(view); } window.draw(boxes[i].getrecc()); }
     * 
     * }
     */

    public void drawStartScreen(RenderWindow window) {
        window.draw(startScreen);
        window.draw(startScreenText);
    }

    public void drawEndScreen() {
        window.draw(endScreen);
    }

    public void startGame() {
        inStartScreen = false;
    }

    public void reset(){
        inStartScreen = true;
    }

    public boolean isInStartScreen() {
        return inStartScreen;
    }

    private void initialiseUi() {

        startScreen = new RectangleShape(new Vector2f(window.getSize().x, window.getSize().y));
        endScreen = new RectangleShape(new Vector2f(window.getSize().x, window.getSize().y));
        deathScreen = new RectangleShape(new Vector2f(window.getSize().x, window.getSize().y));

        heroStatsShape.setPosition(center.x - 890, center.y + 200);
        enemyStatsShape.setPosition(center.x + 330, center.y - 520);

        //deathScreen.setPosition(0 - (window.getView().getSize().x / 2), 0 -(window.getView().getSize().y / 2));
        // startScreen.setPosition(0 - (window.getView().getSize().x / 2), 0 -
        // (window.getView().getSize().y / 2));
        startScreen.setTexture(TextureList.getStartScreenBack());
        endScreen.setPosition(0 - (window.getView().getSize().x / 2), 0 - (window.getView().getSize().y / 2));
        // endScreen.setTexture(TextureList.getEndScreen());
        deathScreen.setTexture(TextureList.getDeathScreen());
        // window.setMouseCursorVisible(false);

        healthBarHero.setPosition(heroStatsShape.getPosition().x + 145, heroStatsShape.getPosition().y + 61);
        maxHealthBarHero.setPosition(healthBarHero.getPosition());
        healthBarEnemy.setPosition(enemyStatsShape.getPosition().x + 120, enemyStatsShape.getPosition().y + 125);

        heroStatsShape.setTexture(TextureList.getHeroStatsTexture(), true);
        enemyStatsShape.setTexture(TextureList.getEnemyStatsTexture(), true);

        healthBarHero.setFillColor(Color.RED);
        healthBarHero.setOutlineColor(Color.BLACK);
        healthBarHero.setOutlineThickness(2);

        maxHealthBarHero.setFillColor(Color.TRANSPARENT);
        maxHealthBarHero.setOutlineColor(Color.add(Color.BLACK, Color.WHITE));
        maxHealthBarHero.setOutlineThickness(2);
        //
        healthBarEnemy.setFillColor(Color.RED);
        healthBarEnemy.setOutlineColor(Color.BLACK);
        healthBarEnemy.setOutlineThickness(2);

        // customMouse.setTexture(TextureList.getCustomMouseTexture());
        deathScreen.setTexture(TextureList.getDeathScreen());
        // startScreen.setTexture(TextureList.getStartScreenBack());
        customMouse.setOrigin(0, 0);
        customMouse.rotate(-20);
        customMouse.setPosition((Mouse.getPosition().x - 10), (Mouse.getPosition().y - 10));
        inventory.setPosition(((window.getView().getSize().x / 2) - 314), 0 - (window.getView().getSize().y / 2 - 50));

        /*
         * inventory.setTexture(TextureList.getInventory());
         * boxes[0].getrecc().setPosition(((window.getView().getSize().x / 2) - 237), 0
         * - (window.getView().getSize().y / 2) + 120);
         * boxes[1].getrecc().setPosition(((window.getView().getSize().x / 2) - 237), 0
         * - (window.getView().getSize().y / 2) + 215);
         * boxes[2].getrecc().setPosition(((window.getView().getSize().x / 2) - 237), 0
         * - (window.getView().getSize().y / 2) + 305);
         * boxes[3].getrecc().setPosition(((window.getView().getSize().x / 2) - 237), 0
         * - (window.getView().getSize().y / 2) + 396);
         * boxes[4].getrecc().setPosition(((window.getView().getSize().x / 2) - 237), 0
         * - (window.getView().getSize().y / 2) + 487);
         */

        // Numbers
        try {
            font.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "IMMORTAL.ttf"));

            ///////////////
            // Start screen
            startScreenText = new Text("Press X to start game", font);
            startScreenText.setCharacterSize(50);
            startScreenText.setColor(Color.WHITE);
            startScreenText.setPosition(startScreen.getPosition().x + 560, startScreen.getPosition().y + 750);
            //
            deathScreenText = new Text("Press X to restart the game",font);
            deathScreenText.setCharacterSize(40);
            deathScreenText.setColor(Color.WHITE);
            deathScreenText.setPosition(startScreen.getPosition().x + 1100, startScreen.getPosition().y + 880);
        } catch (IOException ex) {
            // Failed to load font
            ex.printStackTrace();
        }

    }

    public void initialiseUiAfterPlayerCreation(){
        this.player = overworld.getOverworld().getPlayer();
         /// WORLD NUMBERS
         currentLevel = new Text(("LEVEL: " + String.valueOf(overworld.getOverworld().getLevel())), font);
         currentLevel.setCharacterSize(30);
         currentLevel.setColor(Color.WHITE);
         currentLevel.setPosition(origin.x + 450, origin.y + 40);
         //SCORE NUMBERS
         currentScore = new Text((String.format("Score: %10.2f", overworld.getPlayer().getScore())), font);
         currentScore.setCharacterSize(30);
         currentScore.setColor(Color.WHITE);
         currentScore.setPosition(origin.x + 1600, origin.y + 900);
         // HERO NUMBERS
         System.out.println("Before");
         System.out.println("Player ID: " + player);
         System.out.println(player.getHealth());
         heroHealth = new Text((String.valueOf(player.getHealth()) + "/" + String.valueOf(player.getMaxHealth())),
                 font);
         heroHealth.setCharacterSize(23);
         heroHealth.setColor(Color.BLACK);
         heroHealth.setPosition(heroStatsShape.getPosition().x + 150, heroStatsShape.getPosition().y + 59);
         //
         heroAttack = new Text(String.valueOf(player.getAttack()), font);
         heroAttack.setCharacterSize(30);
         heroAttack.setColor(Color.BLACK);
         heroAttack.setPosition(heroStatsShape.getPosition().x + 145, heroStatsShape.getPosition().y + 97);
         //
         heroArmour = new Text(String.valueOf(player.getArmour()), font);
         heroArmour.setCharacterSize(30);
         heroArmour.setColor(Color.BLACK);
         heroArmour.setPosition(heroStatsShape.getPosition().x + 145, heroStatsShape.getPosition().y + 137);
         //
         currencyDisplay = new Text(String.valueOf(player.getGold()), font);
         currencyDisplay.setCharacterSize(30);
         currencyDisplay.setColor(Color.BLACK);
         currencyDisplay.setPosition(heroStatsShape.getPosition().x + 145, heroStatsShape.getPosition().y + 177);
         ////////////////
         // ENEMY numbers
         //
         enemyName = new Text(overworld.getOverworld().getCurrentEnemy().getName(), font);
         enemyName.setCharacterSize(25);
         enemyName.setColor(Color.BLACK);
         enemyName.setPosition(enemyStatsShape.getPosition().x + 125, enemyStatsShape.getPosition().y + 65);
         //
         enemyHealth = new Text((String.valueOf(overworld.getOverworld().getCurrentEnemy().getHealth()) + " / "
                 + String.valueOf(overworld.getOverworld().getCurrentEnemy().getMaxHealth())), font);
         enemyHealth.setCharacterSize(23);
         enemyHealth.setColor(Color.BLACK);
         enemyHealth.setPosition(enemyStatsShape.getPosition().x + 125, enemyStatsShape.getPosition().y + 121);
         //
         enemyAttack = new Text(String.valueOf(overworld.getOverworld().getCurrentEnemy().getAttack()), font);
         enemyAttack.setCharacterSize(30);
         enemyAttack.setColor(Color.BLACK);
         enemyAttack.setPosition(enemyStatsShape.getPosition().x + 120, enemyStatsShape.getPosition().y + 165);
         //
         enemyArmour = new Text(String.valueOf(overworld.getOverworld().getCurrentEnemy().getArmour()), font);
         enemyArmour.setCharacterSize(30);
         enemyArmour.setColor(Color.BLACK);
         enemyArmour.setPosition(enemyStatsShape.getPosition().x + 120, enemyStatsShape.getPosition().y + 210);
    }

    public static Font getFont() {
        try {
            Font font = new Font();
            font.loadFromFile(FileSystems.getDefault().getPath(System.getProperty("user.dir") + App.pathVariable
                    + "assets" + App.pathVariable + "UI" + App.pathVariable + "IMMORTAL.ttf"));
            return font;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void move(float x, float y) {
        heroStatsShape.move(x, y);
        healthBarHero.move(x, y);
        healthBarEnemy.move(x, y);
        currencyDisplay.move(x, y);
        customMouse.move(x, y);
        // feed.move(x, y);
        inventory.move(x, y);
        deathScreen.move(x, y);
        endScreen.move(x, y);
        /*
         * for (InventoryShape inventoryShape : boxes) { inventoryShape.move(x, y); }
         */
    }

    public void updateHealthBar() {
        this.healthBarHero.setSize(
                new Vector2f((float) (HERO_HEALTHBAR_WIDTH * ((float) this.player.getHealth() / player.getMaxHealth())
                        * (player.getMaxHealth() / player.getOriginalMaxHealth())), HERO_HEALTHBAR_HEIGHT));
        this.maxHealthBarHero.setSize(
                new Vector2f((float) (HERO_HEALTHBAR_WIDTH * (player.getMaxHealth() / player.getOriginalMaxHealth())),
                        HERO_HEALTHBAR_HEIGHT));
        this.healthBarEnemy.setSize(
                new Vector2f((float) (ENEMY_HEALTHBAR_WIDTH * ((float) this.overworld.getOverworld().getCurrentEnemy().getHealth()
                        / this.overworld.getOverworld().getCurrentEnemy().getMaxHealth())), ENEMY_HEALTHBAR_HEIGHT));
    }

    public void updateNumberDisplay() {
        // World
        this.currentLevel.setString("LEVEL: " + String.valueOf(overworld.getOverworld().getLevel()));
        // Score
        this.currentScore.setString(String.format("Score: %12.0f", overworld.getPlayer().getScore()));
        // Hero
        this.heroHealth.setString((String.valueOf(String.format("%.2f", player.getHealth())) + " / "
                + String.valueOf(String.format("%.2f", player.getMaxHealth()))));
        this.heroAttack.setString(String.valueOf(player.getAttack()));
        this.heroArmour.setString(String.valueOf(player.getArmour()));
        this.currencyDisplay.setString(String.valueOf(player.getGold()) + "G");
        // Enemy
        this.enemyName.setString(overworld.getOverworld().getCurrentEnemy().getName());
        this.enemyHealth.setString((String.valueOf(String.format("%.2f", overworld.getOverworld().getCurrentEnemy().getHealth()))
                + " / " + String.valueOf(String.format("%.2f", overworld.getOverworld().getCurrentEnemy().getMaxHealth()))));
        this.enemyAttack.setString(String.valueOf(String.format("%.2f", this.overworld.getOverworld().getCurrentEnemy().getAttack())));
        this.enemyArmour.setString(String.valueOf(String.format("%.2f", this.overworld.getOverworld().getCurrentEnemy().getArmour())));
    }

    public RectangleShape getStartScreen() {
        return this.startScreen;
    }



    public TextWriter getTextWriter() {
        return this.textWriter;
    }

    public boolean isSleectingCharacter() {
        return this.selectingCharacter;
    }

    public void doneSelectingCharacter() {
        this.selectingCharacter = false;
    }
}