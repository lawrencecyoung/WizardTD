package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.*;

/**
 * The MonsterConfig class represents the configuration of a monster in the game.
 */
public class MonsterConfig extends App {
    private String type;
    private PImage spriteImage;
    private int maxHP;
    private double speed;
    private double armor;
    private int manaGainedOnKill;
    private ArrayList<PImage> deathAnimationFrames = new ArrayList<PImage>();
    private int quantity;

    /**
     * Constructs a MonsterConfig object.
     * @param parent          The PApplet instance.
     * @param type            The type or name of the monster.
     * @param maxHP           The maximum health points of the monster.
     * @param speed           The movement speed of the monster.
     * @param armor           The armor value of the monster.
     * @param manaGainedOnKill The amount of mana gained when the monster is killed.
     * @param quantity        The quantity of this type of monster.
     */
    public MonsterConfig(PApplet parent, String type, int maxHP, double speed, double armor, int manaGainedOnKill, int quantity) {
        this.type = type;
        this.maxHP = maxHP;
        this.speed = speed;
        this.armor = armor;
        this.manaGainedOnKill = manaGainedOnKill;
        this.quantity = quantity;
    }

    public PImage getSpriteImage() {
        return spriteImage;
    }

    /**
     * Sets the sprite image of the monster.
     *
     * @param spriteImage The sprite image to set.
     */
    public void setSpriteImage(PImage spriteImage) {
        this.spriteImage = spriteImage;
    }

    public ArrayList<PImage> getDeathAnimationFrames() {
        return deathAnimationFrames;
    }

    public String getType() {
        return type;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getArmor() {
        return armor;
    }

    public int getManaGainedOnKill() {
        return manaGainedOnKill;
    }

    public void setManaGainedOnKill(int manaGainedOnKill) {
        this.manaGainedOnKill = manaGainedOnKill;
    }

    public int getQuantity() {
        return quantity;
    }

}
