package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * The ButtonBuildTower class represents a button that allows the player to build a tower.
 * It extends the TooltipButton class.
 */
public class ButtonBuildTower extends TooltipButton {
    private String cost;
    PImage tower0;
    private boolean isBuilding;

    /**
     * Constructs a ButtonBuildTower object.
     *
     * @param parent The PApplet instance to which the button belongs.
     * @param x      The x-coordinate of the button's top-left corner.
     * @param y      The y-coordinate of the button's top-left corner.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param label  The label text displayed on the button.
     * @param abbr   An abbreviation or short label for the button.
     * @param cost   The cost of building the tower.
     */

    public ButtonBuildTower(PApplet parent, float x, float y, float width, float height, String label, String abbr, int cost) {
        super(parent, x, y, width, height, label, abbr);
        this.cost = Integer.toString(cost);
        tower0 = parent.loadImage("src/main/resources/WizardTD/tower0.png");
        this.isBuilding = false;

    }

    /**
     * Displays the button and handles its behavior.
     */
    
    public void display() {
        super.display(); // Display the button

        if (isMouseOver()) {
            // Display the tooltip when hovered over
            parent.fill(255); // No background fill
            parent.stroke(0); // Black border color
            // parent.strokeWeight(3); // Adjust the border thickness as needed

            parent.rect(x - 75, y - 5, 60, 20); // Adjust the size as needed
            parent.fill(0); // Black text color
            parent.textSize(11);
            parent.text("Cost: " + cost, x - 70, y + 10);
        }

        if (isClicked) {
            isBuilding = true;
        } else if (!isClicked) {
            isBuilding = false;
        }
    }

    public boolean getisBuilding() {
        return isBuilding;
    }
    public void setisBuilding(boolean isBuilding) {
        isClicked = isBuilding;
        this.isBuilding = isBuilding;
    }
}

