package combat;

import entities.*;
import entities.enemy.Enemy;
import entities.player.Player;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;

import app.Overworld;

import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Random;

public class CombatDriver{

    private Overworld overworld;
    private Random rand = new Random();

    public CombatDriver(Overworld overworld){
        this.overworld = overworld;
    }

    public void doOneRound(){
        //Player goes first
        if(playerIsFaster()){
            System.out.println("Player was faster.");
            overworld.getCurrentEnemy().takeDamage(calculateDamageDealtFrom(this.overworld.getPlayer()));
            if(overworld.getCurrentEnemy().isAlive()){
                overworld.getPlayer().takeDamage(calculateDamageDealtFrom(this.overworld.getCurrentEnemy()));
            }
        }
        else{
            System.out.println("Enemy was faster.");
            overworld.getPlayer().takeDamage(calculateDamageDealtFrom(this.overworld.getCurrentEnemy()));
            if(overworld.getPlayer().isAlive()){
                overworld.getCurrentEnemy().takeDamage(calculateDamageDealtFrom(this.overworld.getPlayer()));
            }
        }
        
       
        if(!overworld.getCurrentEnemy().isAlive()&&overworld.getPlayer().isAlive()){
            giveRewards();
        }
        //System.out.println("Player Health: " + player.getHealth() + " Player Attack: " + player.getAttack());
        //System.out.println("Enemy health: "+currentEnemy.getHealth()+ " Enemy Attack: " + currentEnemy.getAttack());

    }

    public double calculateDamageDealtFrom(Entity entity){
        if(entity.getCritChance() > rand.nextInt(100)){
            double damage = entity.getAttack() + (entity.getAttack()*(entity.getCritDamageModfier()/100));
            overworld.getTextWriter().add("Critical Strike for " + String.format("%.2f",damage));
            System.out.println("Critical Strike for " + String.format("%.2f",damage) + " insted of " + entity.getAttack());
            return damage;
        }
        else {
            return entity.getAttack();
        }
    }

    private double calculateScore(){
        double score = 0;

        score = ((overworld.getCurrentEnemy().getBaseScoreGive() * overworld.getLevel())
                / (2 * (overworld.getPlayer().getMaxHealth() + overworld.getPlayer().getAttack())));

        return score;
    }

    public boolean playerIsFaster(){
        if(overworld.getPlayer().getSpeed()>overworld.getCurrentEnemy().getSpeed()){
            return true;
        }
        return false;
    }

    public void giveRewards(){
        overworld.getTextWriter().add("Gained " + overworld.getCurrentEnemy().getBaseGoldGive() + " gold.");
        overworld.getPlayer().addGold(overworld.getCurrentEnemy().getBaseGoldGive());

        overworld.getTextWriter().add("Score added.");
        overworld.getPlayer().addScore(calculateScore());
    }

}