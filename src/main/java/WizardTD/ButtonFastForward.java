package WizardTD;

import processing.core.PApplet;
/**
 * The ButtonFastForward class represents a button that enables fast-forwarding in the game.
 * It extends the Button class.
 */

public class ButtonFastForward extends Button {
    private boolean isFastForwarding;

    /**
     * Constructs a ButtonFastForward object.
     *
     * @param parent The PApplet instance to which the button belongs.
     * @param x      The x-coordinate of the button's top-left corner.
     * @param y      The y-coordinate of the button's top-left corner.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param label  The label text displayed on the button.
     * @param abbr   An abbreviation or short label for the button.
     */

    public ButtonFastForward(PApplet parent, float x, float y, float width, float height, String label, String abbr) {
        super(parent, x, y, width, height, label, abbr);
        this.isFastForwarding = false;
    }

    /**
     * Displays the button and handles its behavior.
     */

    public void display() {
        super.display();

        if (isClicked) {
            isFastForwarding = true;
        } else if (!isClicked) {
            isFastForwarding = false;
        }
    }

    public boolean getisFastForwarding() {
        return isFastForwarding;
    }


    public void setisFastForwarding(boolean isFastForwarding) {
        isClicked = isFastForwarding;
        this.isFastForwarding = isFastForwarding;
    }
}
