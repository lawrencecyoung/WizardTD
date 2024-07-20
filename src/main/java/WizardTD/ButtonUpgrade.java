package WizardTD;

import processing.core.PApplet;

/**
 * The ButtonUpgrade class represents a button that allows upgrading in the game.
 * It extends the Button class.
 */

public class ButtonUpgrade extends Button {
    private boolean isUpgrading;


    /**
     * Constructs a ButtonUpgrade object.
     *
     * @param parent      The PApplet instance to which the button belongs.
     * @param x           The x-coordinate of the button's top-left corner.
     * @param y           The y-coordinate of the button's top-left corner.
     * @param width       The width of the button.
     * @param height      The height of the button.
     * @param label       The label text displayed on the button.
     * @param abbreviation An abbreviation or short label for the button.
     */
    public ButtonUpgrade(PApplet parent, float x, float y, float width, float height, String label, String abbreviation) {
        super(parent, x, y, width, height, label, abbreviation);
        this.isUpgrading = false;
    }

    /**
     * Displays the button and handles its behavior.
     */
    public void display() {
        super.display(); // Display the button

        if (isClicked) {
            isUpgrading = true;
        } else if (!isClicked) {
            isUpgrading = false;
        }
    }

    public boolean getisUpgrading() {
        return isUpgrading;
    }
    public void setisUpgrading(boolean isUpgrading) {
        isClicked = isUpgrading;
        this.isUpgrading = isUpgrading;
    }


}