import org.junit.jupiter.api.Test;
import WizardTD.Monster;
import WizardTD.Tower;
import WizardTD.MonsterConfig;
import WizardTD.App;
import WizardTD.PathFinding;
import WizardTD.Button;
import processing.data.JSONArray;
import processing.data.JSONObject;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.*;
import java.util.*;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonTest {
    App app = new App();
    Button button = new Button(app, 650, 50, 40, 40, "2x speed", "FF");

    @Test
    public void testIsClicked() {     
        assertEquals(false, button.isClicked());
        button.setClicked(true);
        assertEquals(true, button.isClicked());
    }

    @Test
    public void testIsMouseOver() {     
        assertEquals(false, button.isMouseOver());
        app.mouseX = 655;
        app.mouseY = 55;
        assertEquals(true, button.isMouseOver());
    }


    @Test
    public void testisclicked() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);
        Button button = new Button(app, 10, 20, 30, 40, "Pause", "P");
        app.mouseX=10;
        app.mouseY=20;
        app.mousePressed();
    }
}
