package WizardTD;

import processing.core.PApplet;
import processing.data.JSONObject;
import java.io.*;
import java.util.*;

import WizardTD.ButtonBuildTower;
import WizardTD.ButtonManaPool;
import WizardTD.ButtonPause;
import WizardTD.ButtonUpgrade;

/**
 * The Sidebar class represents the sidebar in the game interface with various buttons and options.
 */
public class Sidebar extends App{
    private PApplet parent;
    private ButtonFastForward buttonFastForward;
    private ButtonPause buttonPause;
    private ButtonBuildTower buttonBuildTower;

    private ButtonUpgrade buttonUpgradeRange;
    private ButtonUpgrade buttonUpgradeSpeed;
    private ButtonUpgrade buttonUpgradeDamage;
    private ButtonSpell buttonSpell;

    private ButtonManaPool buttonManaPool;

    private int towerCost;
    private int manaPoolInitial;
    private int manaPoolIncreasePerUse;
    private float manaPoolCapMultiplier;
    private float manaPoolGainedMultiplier;

    private int currentMana;

    /**
     * Constructs a Sidebar object.
     *
     * @param parent The PApplet instance for drawing.
     * @param json   The JSON object containing configuration data.
     */

    public Sidebar(PApplet parent, JSONObject json) {
        this.parent = parent;

        this.towerCost = json.getInt("tower_cost");
        this.manaPoolInitial = json.getInt("mana_pool_spell_initial_cost");
        this.manaPoolIncreasePerUse = json.getInt("mana_pool_spell_cost_increase_per_use");
        this.manaPoolCapMultiplier = json.getFloat("mana_pool_spell_cap_multiplier");
        this.manaPoolGainedMultiplier = json.getFloat("mana_pool_spell_mana_gained_multiplier");


        this.buttonFastForward = new ButtonFastForward(parent, 650, 50, 40, 40, "2x speed", "FF");
        this.buttonPause = new ButtonPause(parent, 650, 100, 40, 40, "PAUSE", "P");
        this.buttonBuildTower = new ButtonBuildTower(parent, 650, 150, 40, 40, "Build&tower", "T", towerCost);
        this.buttonUpgradeRange = new ButtonUpgrade(parent, 650, 200, 40, 40, "Upgrade&Range", "U1");
        this.buttonUpgradeSpeed = new ButtonUpgrade(parent, 650, 250, 40, 40, "Upgrade&Speed", "U2");
        this.buttonUpgradeDamage = new ButtonUpgrade(parent, 650, 300, 40, 40, "Upgrade&Damage", "U3");
        this.buttonManaPool = new ButtonManaPool(parent, 650, 350, 40, 40, "Mana pool", "M", manaPoolInitial, manaPoolIncreasePerUse, manaPoolCapMultiplier, manaPoolGainedMultiplier);
        this.buttonSpell = new ButtonSpell(parent, 650, 400, 40, 40, "Spell", "S");

    }

    /**
     * Displays the buttons and options on the sidebar.
     */
    public void display() {
        buttonFastForward.display();
        buttonPause.display();
        
        buttonUpgradeRange.display();
        buttonUpgradeSpeed.display();
        buttonUpgradeDamage.display();

        buttonBuildTower.display();
        buttonManaPool.display();
        if (buttonManaPool.getNumberOfUses()>=1) {
            buttonSpell.display();
        }
    }

    public ButtonFastForward getButtonFastForward() {
        return buttonFastForward;
    }

    public ButtonPause getButtonPause() {
        return buttonPause;
    }

    public ButtonBuildTower getButtonBuildTower() {
        return buttonBuildTower;
    }

    public ButtonUpgrade getButtonUpgradeRange() {
        return buttonUpgradeRange;
    }

    public ButtonUpgrade getButtonUpgradeSpeed() {
        return buttonUpgradeSpeed;
    }

    public ButtonUpgrade getButtonUpgradeDamage() {
        return buttonUpgradeDamage;
    }

    public ButtonManaPool getButtonManaPool() {
        return buttonManaPool;
    }

    public ButtonSpell getButtonSpell() {
        return buttonSpell;
    }
}