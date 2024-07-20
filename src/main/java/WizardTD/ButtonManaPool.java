package WizardTD;

import processing.core.PApplet;


/**
 * The ButtonManaPool class represents a button that allows players to upgrade their mana pool.
 * It extends the TooltipButton class.
 */

public class ButtonManaPool extends TooltipButton {

    private int manaPoolInitial;
    private int manaPoolIncreasePerUse;
    private float manaPoolCapMultiplier;
    private float manaPoolGainedMultiplier;
    private int numberOfUses;
    private int currentManaPoolCost;
    private boolean isOn;

    /**
     * Constructs a ButtonManaPool object.
     *
     * @param parent               The PApplet instance to which the button belongs.
     * @param x                    The x-coordinate of the button's top-left corner.
     * @param y                    The y-coordinate of the button's top-left corner.
     * @param width                The width of the button.
     * @param height               The height of the button.
     * @param label                The label text displayed on the button.
     * @param abbr                 An abbreviation or short label for the button.
     * @param manaPoolInitial      The initial size of the mana pool.
     * @param manaPoolIncreasePerUse The amount by which the mana pool increases with each use.
     * @param manaPoolCapMultiplier The multiplier for the mana pool capacity.
     * @param manaPoolGainedMultiplier The multiplier for the mana pool gained per second.
     */

    public ButtonManaPool(PApplet parent, float x, float y, float width, float height, String label, String abbr, int manaPoolInitial, int manaPoolIncreasePerUse, float manaPoolCapMultiplier, float manaPoolGainedMultiplier) {
        super(parent, x, y, width, height, label, abbr);
        this.manaPoolInitial = manaPoolInitial;
        this.manaPoolIncreasePerUse = manaPoolIncreasePerUse;
        this.manaPoolCapMultiplier = manaPoolCapMultiplier;
        this.manaPoolGainedMultiplier = manaPoolGainedMultiplier;
        this.isOn = false;

        currentManaPoolCost = manaPoolInitial;
        numberOfUses = 0;
    }
    /**
     * Displays the button and handles its behavior.
     */
    public void display() {
        super.display(); // Display the button

        parent.textSize(12);
        parent.text("cost: " + getCurrentManaPoolCost(), x + width / 2 + 27, y+32);

        if (isMouseOver()) {
            parent.fill(255); // No background fill
            parent.stroke(0); // Black border 
            parent.rect(x - 75, y - 5, 60, 20);
            parent.fill(0); // Black 
            parent.textSize(11);
            parent.text("Cost: " + getCurrentManaPoolCost(), x - 70, y + 10);
        }

        if (isClicked) {
            isOn = true;
        } else if (!isClicked) {
            isOn = false;
        }

    }


    
    public float getManaPoolGainedMultiplier() {
        return (manaPoolGainedMultiplier-1)*numberOfUses;
    }

    public int getCurrentManaPoolCost() {
        return currentManaPoolCost + manaPoolIncreasePerUse*numberOfUses;
    }

    public boolean getisOn() {
        return isOn;
    }
    public void setOn(boolean isOn) {
        isClicked = isOn;
        this.isOn = isOn;
    }

    /**
     * Upgrades the mana pool and updates related game elements like the mana bar.
     *
     * @param manaBar The mana bar to update during the mana pool upgrade.
     */

    public void upgradingManaPool(ManaBar manaBar) {
        if (manaBar.getMana()>=getCurrentManaPoolCost()) {
            manaBar.setManaCap(manaBar.getManaCap()*manaPoolCapMultiplier);
            numberOfUses += 1;
            isClicked = false;
            isOn = false;
            manaBar.useMana(currentManaPoolCost);
            manaBar.setManaGainRate(manaBar.getManaGainRate()+manaPoolGainedMultiplier-1);
        }
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }
}

