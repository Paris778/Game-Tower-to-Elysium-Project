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

public class Quest {

    private String title;
    private String description;
    private String objectiveText;
    private String reward;
    private int rewardGoldAmount;
    private int currentProgress;
    private int objectiveNumber;

    public Quest(){

    }

}