package app;

import org.jsfml.audio.Music;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class MusicDriver {

    private static Music startScreenMusic = new Music();
    private static Music battleMusic = new Music();

    ////////////////////////
    //START MUSIC METHODS

    public static void startStartScreenMusic(){
        try {
            startScreenMusic.openFromFile(Paths.get("assets" + App.pathVariable + "soundtrack" + App.pathVariable + "startMusic.ogg"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        startScreenMusic.setLoop(true);
        startScreenMusic.play();
    }

    public static void startBattleMusic(){
        try {
            battleMusic.openFromFile(Paths.get("assets" + App.pathVariable + "soundtrack" + App.pathVariable + "battleMusic.ogg"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        battleMusic.setLoop(true);
        battleMusic.play();
    }

    ///////////////////////
    //STOP MUSIC METHODS

    public static void stopStartMusic(){
        startScreenMusic.stop();
    }

    public static void stopBattleMusic(){
        battleMusic.stop();
    }
}