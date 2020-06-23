package entities.enemy;

import entities.*;
import org.jsfml.system.Vector2f;
import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

public class EnemyDriver {

    private int enemyClassNumber;
    private Random rand = new Random();
    private int x;
    private int r;

    public Enemy makeNewEnemy(int level) {

        if (Arrays.asList(50,100,150).contains(level)) { //Levels to encounter boss
            enemyClassNumber = EnemyClassList.BOSS_ORC_CHIEF;
        } 
        else if(Arrays.asList(200,250,300).contains(level)){
            enemyClassNumber = EnemyClassList.BOSS_AWAKENED_TREANT;
        }
        else {
            enemyClassNumber = generateRandomNumber();
        }

        Enemy enemy = new Enemy(EnemyClassList.getEnemyClass(enemyClassNumber));
        enemy.scaleStats((double) level);
        enemy.getShape().setPosition(250, 250);
        return enemy;
    }

    private int generateRandomNumber() {
        x = 0;
        r = rand.nextInt(1000);
        if (r < 180) {
            x = EnemyClassList.ENEMY_GOBLIN; // 18%
        } else if (r < 330) { 
            x = EnemyClassList.ENEMY_GOBLIN_RAIDER; // 15%
        } else if (r < 470) {
            x = EnemyClassList.ENEMY_GOBLIN_ARCHER; // 13%
            //
        } else if (r < 610) {
            x = EnemyClassList.ENEMY_ORC_BARSERKER; // 14%
        } else if (r < 740) {
            x = EnemyClassList.ENEMY_ORC_PLUNDERER; // 13%
        } else if (r < 870) {
            x = EnemyClassList.ENEMY_ORC_ARCHER; // 13%
        }
        //
        else if (r < 930) {
            x = EnemyClassList.ENEMY_SCARAB; // 6%
        }
        else if (r < 950) {
            x = EnemyClassList.ENEMY_HERCULES_SCARAB; // 2%
        }
        else if (r < 1000) {
            x = EnemyClassList.ENEMY_BUTTERFLY; // 6%
        }

        return x;
    }

}
