package entities.enemy;

import entities.*;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.LinkedList;

public class EnemyClass extends EntityClass{

  private String name;
  private int baseGoldGive;
  private int baseScoreGive;

  public EnemyClass(String name, int race,double baseHealth,double baseAttack, double baseArmour, double baseCritChance,double critDamageModifier,double speed,int baseGoldGive,int textureID,Vector2f size,int baseScore){
    super(race,baseHealth,baseAttack,baseArmour,baseCritChance,critDamageModifier,speed,textureID,size);
    this.name = name;
    this.baseGoldGive = baseGoldGive;
    this.baseScoreGive = baseScore;
  }

  public String getName(){
    return this.name;
  }

  public int getBaseScoreGive(){
    return this.baseScoreGive;
  }

  public int getBaseGoldGive(){
    return this.baseGoldGive;
  }


}