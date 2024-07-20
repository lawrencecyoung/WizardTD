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

public class ButtonBuildTowerTest {
    @Test
    public void testSetPaused() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        ButtonBuildTower button = new ButtonBuildTower(app, 10, 20, 30, 40, "Pause", "P", 100);

        assertFalse(button.getisBuilding());

        button.setisBuilding(true);
        assertTrue(button.getisBuilding());
    }

    @Test
    public void testMouseOver() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        ButtonBuildTower button = new ButtonBuildTower(app, 10, 20, 30, 40, "Pause", "P", 100);

        button.setClicked(true);
    }

    @Test
    public void testDisplay() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        ButtonBuildTower button = new ButtonBuildTower(app, 10, 20, 30, 40, "Pause", "P", 100);

        button.display();
        app.mouseX = 10;
        app.mouseY = 20;
        button.display();

        button.setClicked(true);
        button.display();
    }


}