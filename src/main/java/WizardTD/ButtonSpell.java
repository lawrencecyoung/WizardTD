package WizardTD;

import processing.core.PApplet;
import java.io.*;
import java.util.*;
import processing.core.PImage;

/**
 * The ButtonSpell class represents a button that allows players to use different spells in the game.
 * It extends the TooltipButton class.
 */
public class ButtonSpell extends TooltipButton {
    private int numberOfUses;
    private int currentSpellCost;
    private boolean isOn;

    /**
     * Constructs a ButtonSpell object.
     *
     * @param parent The PApplet instance to which the button belongs.
     * @param x      The x-coordinate of the button's top-left corner.
     * @param y      The y-coordinate of the button's top-left corner.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param label  The label text displayed on the button.
     * @param abbr   An abbreviation or short label for the button.
     */
    public ButtonSpell(PApplet parent, float x, float y, float width, float height, String label, String abbr) {
        super(parent, x, y, width, height, label, abbr);
        this.isOn = false;

        currentSpellCost = 50;
        numberOfUses = 0;
    }

    /**
     * Displays the button and handles its behavior.
     */
    public void display() {
        super.display();
        parent.textSize(12);
        if (numberOfUses%3==0) {
        parent.text("Freeze", x + width / 2 + 27, y+32);
        } else if (numberOfUses%3==1) {
        parent.text("Poison", x + width / 2 + 27, y+32);
        } else if (numberOfUses%3==2) {
        parent.text("Earthquake", x + width / 2 + 27, y+32);
        }
        if (isMouseOver()) {
            parent.fill(255);
            parent.stroke(0);
            parent.rect(x - 75, y - 5, 60, 20);
            parent.fill(0);
            parent.textSize(11);
            parent.text("Cost: " + getCurrentSpellCost(), x - 70, y + 10);
        }
        if (isClicked) {
            isOn = true;
        } else if (!isClicked) {
            isOn = false;
        }
    }

    public boolean getisOn() {
        return isOn;
    }
    public void setOn(boolean isOn) {
        isClicked = isOn;
        this.isOn = isOn;
    }

    /**
     * Use a spell and update game elements accordingly.
     *
     * @param manaBar   The mana bar to deduct mana from when using the spell.
     * @param monsters  The list of monsters to affect with the spell.
     * @param towers    The list of towers to check for range and apply effects.
     */
    public void setSpell(ManaBar manaBar, List<Monster> monsters, List<Tower> towers) {
        if (manaBar.getMana()>=getCurrentSpellCost() && numberOfUses % 3 == 0) {
            numberOfUses += 1;
            isClicked = false;
            isOn = false;
            manaBar.useMana(getCurrentSpellCost());

            for (Monster monster : monsters) {
                parent.fill(135,206,250);
                parent.rect(0,40,640,640);
                monster.setFrozen(true);
            }
        } else if (manaBar.getMana()>=getCurrentSpellCost() && numberOfUses % 3 == 1) {
            numberOfUses += 1;
            isClicked = false;
            isOn = false;
            manaBar.useMana(getCurrentSpellCost());
            for (Tower tower:towers) {
                for (Monster monster : monsters) {
                    parent.fill(122,94,44);
                    parent.rect(0,40,640,640);
                    if (tower.isinRange(monster)) {
                        monster.setCurrentHP((float) (monster.getCurrentHP()*(1-0.2)));
                    }
                }
            }
        } else if (manaBar.getMana()>=getCurrentSpellCost() && numberOfUses % 3 == 2) {
            numberOfUses += 1;
            isClicked = false;
            isOn = false;
            manaBar.useMana(getCurrentSpellCost());
            for (Monster monster : monsters) {
                parent.fill(192,192,192);
                parent.rect(0,40,640,640);
                monster.setCurrentHP((float) (monster.getCurrentHP()*(1-0.15)));
            }
        }
    }

    public int getCurrentSpellCost() {
        return currentSpellCost + numberOfUses*25;
    }

    
}

