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

public class ButtonManaPoolTest {
    @Test
    public void testDisplay() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);
        ButtonManaPool button = new ButtonManaPool(app, 650, 350, 40, 40, "Mana pool", "M", 100, 2, 2, 2);
        button.display();
        app.mouseX = 650;
        app.mouseY = 350;
        button.display();

        assertEquals(0, button.getManaPoolGainedMultiplier());

        button.setOn(true);
        button.display();
    }
    @Test
    public void testManaPoolSpell() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);
        ButtonManaPool button = new ButtonManaPool(app, 650, 350, 40, 40, "Mana pool", "M", 100, 2, 2, 2);
        button.upgradingManaPool(app.getManaBar());


    }
}