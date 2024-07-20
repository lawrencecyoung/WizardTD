import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PApplet;

import WizardTD.Monster;
import WizardTD.Tower;
import WizardTD.MonsterConfig;
import WizardTD.App;
import WizardTD.PathFinding;
import WizardTD.Button;
import WizardTD.ButtonFastForward;
import WizardTD.ButtonPause;
import WizardTD.ButtonBuildTower;
import WizardTD.ButtonUpgrade;
import WizardTD.ButtonManaPool;
import WizardTD.ButtonSpell;
import WizardTD.Sidebar;

public class ButtonPauseTest {
    @Test
    public void testConstructor() {
        App app = new App();
        ButtonPause button = new ButtonPause(app, 10, 20, 30, 40, "Pause", "P");
        assertFalse(button.getisPaused());
    }

    @Test
    public void testSetPaused() {
        App app = new App();
        ButtonPause button = new ButtonPause(app, 10, 20, 30, 40, "Pause", "P");
        button.setPaused(true);
        assertTrue(button.getisPaused());
        button.setPaused(false);
        assertFalse(button.getisPaused());
    }


    @Test
    public void testDisplay() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        ButtonPause button = new ButtonPause(app, 10, 20, 30, 40, "Pause", "P");
        button.display();
        button.setClicked(true);
        button.display();
    }

}