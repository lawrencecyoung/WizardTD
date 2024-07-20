package WizardTD;

import processing.core.PApplet;
import processing.data.JSONObject;

/**
 * The ManaBar class represents the mana bar for the game.
 */
public class ManaBar extends App {
    private PApplet parent;
    private float mana;
    private float manaCap;
    private float manaGainRate;
    private int lastUpdateTime;
    private JSONObject config;
    private boolean isFastForwarding;

    /**
     * Constructs a ManaBar object.
     *
     * @param parent The PApplet instance to which the mana bar belongs.
     * @param config The configuration for initializing mana-related variables.
     */
    public ManaBar(PApplet parent, JSONObject config) {
        this.parent = parent;
        this.config = config;
        mana = config.getFloat("initial_mana");
        manaCap = config.getFloat("initial_mana_cap");
        manaGainRate = config.getFloat("initial_mana_gained_per_second");
        lastUpdateTime = parent.millis();
    }

    /**
     * Updates the mana bar, including gaining mana over time.
     */
    public void update() {
        int currentTime = parent.millis();
        int elapsedTime = currentTime - lastUpdateTime;
        lastUpdateTime = currentTime;

        if (isFastForwarding) {
            mana += (elapsedTime / 1000.0) * manaGainRate*2;
        } else {
            mana += (elapsedTime / 1000.0) * manaGainRate;
        }

        // Ensure mana doesn't exceed the cap
        mana = Math.min(mana, manaCap);
    }

    /**
     * Displays the mana bar at the specified position.
     *
     * @param x The x-coordinate of the mana bar.
     * @param y The y-coordinate of the mana bar.
     */

    public void display(int x, int y) {
        int barWidth = 340;
        int barHeight = 20;
        int outlineThickness = 2;

        int roundedMana = PApplet.round(mana);

        parent.text("MANA:", x-85, y + barHeight);
        
        parent.noStroke();
        parent.fill(255); 
        parent.rect(x, y, barWidth, barHeight); // White background
        
        parent.stroke(0);
        parent.strokeWeight(outlineThickness);
        parent.noFill();
        parent.rect(x, y, barWidth, barHeight);
    
        // Calculate the width of the colored mana bar based on current mana
        float manaBarWidth = PApplet.map(mana, 0, manaCap, 0, barWidth);
    
        parent.noFill();
        parent.stroke(0);
        parent.rect(x, y, manaBarWidth, barHeight);
    
        parent.stroke(0);
        parent.fill(0, 214, 220);
        parent.rect(x, y, manaBarWidth, barHeight);

        parent.fill(0);
        parent.textSize(16);
        float textX = barWidth + x/2 - 45;
        float textY = barHeight + (y/2) + 2;
        parent.text(roundedMana + "/" + PApplet.nf(manaCap, 0, 0), textX, textY);
    }

    public float getMana() {
        return mana;
    }

    public float getManaCap() {
        return manaCap;
    }

    public float getManaGainRate() {
        return manaGainRate;
    }

    public void setMana(float newMana) {
        mana = newMana;
    }

    public void setManaCap(float newManaCap) {
        manaCap = newManaCap;
    }

    public void setManaGainRate(float newManaGainRate) {
        manaGainRate = newManaGainRate;
    }

    /**
     * Uses mana by reducing it by a specified cost.
     *
     * @param cost The mana cost to be deducted.
     */

    public void useMana(float cost) {
        if ((mana - cost) > 0) {
            mana = mana - cost;
        }
    }

    public boolean isFastForwarding() {
        return isFastForwarding;
    }

    public void setFastForwarding(boolean isFastForwarding) {
        this.isFastForwarding = isFastForwarding;
    }
}
