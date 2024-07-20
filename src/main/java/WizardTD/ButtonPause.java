package WizardTD;

import processing.core.PApplet;

/**
 * The ButtonPause class represents a button that allows pausing and unpausing the game.
 * It extends the Button class.
 */

public class ButtonPause extends Button {

    private boolean isPaused;

    /**
     * Constructs a ButtonPause object.
     *
     * @param parent The PApplet instance to which the button belongs.
     * @param x      The x-coordinate of the button's top-left corner.
     * @param y      The y-coordinate of the button's top-left corner.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param label  The label text displayed on the button.
     * @param abbr   An abbreviation or short label for the button.
     */

    public ButtonPause(PApplet parent, float x, float y, float width, float height, String label, String abbr) {
        super(parent, x, y, width, height, label, abbr);
        this.isPaused = false;
    }
    
    /**
     * Displays the button and handles its behavior.
     */

    public void display() {
        super.display();

        if (isClicked) {
            isPaused = true;
        } else if (!isClicked) {
            isPaused = false;
        }
    }


    public boolean getisPaused() {
        return isPaused;
    }
    public void setPaused(boolean isPaused) {
        isClicked = isPaused;
        this.isPaused = isPaused;
    }
    
}
