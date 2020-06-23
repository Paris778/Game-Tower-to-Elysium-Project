package app;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard.Key;

import combat.CombatDriver;
import entities.player.*;
import event.EventDriver;

public class Controller {
    protected RenderWindow window;
    protected Player player;
    private CombatDriver combatDriver;
    private long interactDelay;
    private long inventoryDelay;
    private App app;

    public Controller(RenderWindow window, Player player, App app) {
        this.window = window;
        this.player = player;
        this.app = app;
    }

    public void parseKey(Key key) {
        switch (key) {
            case ESCAPE:
                if (EventDriver.EVENT_IS_HAPPENING || EventDriver.SHOP_ACTIVE) {
                    EventDriver.SHOP_ACTIVE = false;
                    EventDriver.EVENT_IS_HAPPENING = false;
                } else {
                    app.stop();
                }
                break;
            case SPACE:
                if (System.currentTimeMillis() - interactDelay > 400) {
                    if (Overworld.inCombat() && !EventDriver.EVENT_IS_HAPPENING && !EventDriver.SHOP_ACTIVE) {
                        Overworld.doOneRound();
                    }
                }

                if (!app.getUI().isInStartScreen() && App.selectingplayer && !Overworld.inCombat()
                        && !EventDriver.EVENT_IS_HAPPENING && !EventDriver.SHOP_ACTIVE) {
                    app.makePlayer();
                    MusicDriver.stopStartMusic();
                    MusicDriver.startBattleMusic();
                }

                break;
            case RETURN:
                if (Overworld.inCombat() && !EventDriver.EVENT_IS_HAPPENING && !EventDriver.SHOP_ACTIVE) {
                    Overworld.stopCombat();
                }
                break;
            case X:
                if (app.getUI().isInStartScreen()) {
                    app.getUI().startGame();
                }
                else if (app.getPlayer() != null) {
                    if (!app.getOverworld().getPlayer().isAlive()) {
                        app.resetGame();
                    }
                }
                System.out.println("Presseed X");
                break;
            case Z:
                app.decreasePlayerSelectionCount();
                break;
            case C:
                app.increasePlayerSelectionCount();
                break;
            case NUM1:
                if (EventDriver.SHOP_ACTIVE) {
                    app.getOverworld().getEventDriver().getShop().executePurchase(0);
                }
                break;
            case NUM2:
                if (EventDriver.SHOP_ACTIVE) {
                    app.getOverworld().getEventDriver().getShop().executePurchase(1);
                }
                break;
            case NUM3:
                if (EventDriver.SHOP_ACTIVE) {
                    app.getOverworld().getEventDriver().getShop().executePurchase(2);
                }
                break;
            case NUM4:
                if (EventDriver.SHOP_ACTIVE) {
                    app.getOverworld().getEventDriver().getShop().executePurchase(3);
                }
                break;
            case NUM5:
                if (EventDriver.SHOP_ACTIVE) {
                    app.getOverworld().getEventDriver().getShop().executePurchase(4);
                }
                break;
            default:
                break;
        }

    }

}