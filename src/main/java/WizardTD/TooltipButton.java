package WizardTD;

import processing.core.PApplet;


/**
 * The TooltipButton class represents a button with a tooltip for additional information.
 */
public class TooltipButton extends Button {

    /**
     * Constructs a TooltipButton with the specified parameters.
     *
     * @param parent       The PApplet instance for drawing.
     * @param x            The x-coordinate of the button.
     * @param y            The y-coordinate of the button.
     * @param width        The width of the button.
     * @param height       The height of the button.
     * @param label        The label or text displayed on the button.
     * @param abbreviation A short abbreviation or symbol associated with the button.
     */
    
    public TooltipButton(PApplet parent, float x, float y, float width, float height, String label, String abbreviation) {
        super(parent, x, y, width, height, label, abbreviation);
    }


}
