package WizardTD;

import WizardTD.App;
import processing.core.PApplet;
import processing.data.JSONObject;

/**
 * The Button class represents a clickable button in a graphical user interface.
 */

public class Button extends App{
    protected PApplet parent;
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected String label;
    protected String[] labelLines;
    protected String abbv;
    protected boolean isHovered;
    protected boolean isClicked;

    /**
     * Constructs a Button object.
     *
     * @param parent The PApplet instance to which the button belongs.
     * @param x      The x-coordinate of the button's top-left corner.
     * @param y      The y-coordinate of the button's top-left corner.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param label  The label text displayed on the button.
     * @param abbv   An abbreviation or short label for the button.
     */

    public Button(PApplet parent, float x, float y, float width, float height, String label, String abbv) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.labelLines = label.split("&");   
        this.label = label;
        this.abbv = abbv;
        this.isClicked = false;
    }


    /**
     * Displays the button on screen.
     */

    public void display() {
        parent.stroke(0);
        parent.strokeWeight(3);
        if (parent.mousePressed && isClicked == false && isMouseOver() == true){
            isClicked = true;
        } else if (parent.mousePressed && isClicked == true && isMouseOver() == true){
            isClicked = false;
        }

        if (isClicked == false) {
            if (isMouseOver()) {
                parent.fill(255);
                
            } else {
                parent.noFill();
            }

        } else if (isClicked == true) {
            parent.fill(255,255,0);
        }

        parent.rect(x, y, width, height);
        parent.fill(0); // Black text color
        parent.textSize(12); // Adjust the text size as needed

        float labelY = y + height / 2 - 5; // Adjust the vertical position of the label
        for (String line : labelLines) {
            parent.text(line, x + width / 2 + 27, labelY);
            labelY += 18; // Adjust the vertical spacing between lines
        }

        parent.textSize(26); // Adjust the text size as needed
        parent.text(abbv, x + width / 2 - 15, y + height / 2 + 10); 

    }

    /**
     * Checks if the mouse pointer is over the button.
     *
     * @return true if the mouse pointer is over the button, false otherwise.
     */
    public boolean isMouseOver() {
        return parent.mouseX >= x && parent.mouseX <= x + width && parent.mouseY >= y && parent.mouseY <= y + height;
    }

    public boolean isClicked() {
        return isClicked;
    }

    /**
     * Sets the click state of the button.
     *
     * @param isClicked true to set the button as clicked, false to set it as not clicked.
     */

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

}