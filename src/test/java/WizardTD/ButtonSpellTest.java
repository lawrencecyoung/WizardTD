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

public class ButtonSpellTest {
    @Test
    public void testdisplay() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        ButtonSpell button = new ButtonSpell(app, 10, 20, 30, 40, "Pause", "P");

        button.display();
    }


    @Test
    public void testDisplay() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        ButtonSpell button = new ButtonSpell(app, 10, 20, 30, 40, "Pause", "P");

        button.display();
        app.mouseX = 10;
        app.mouseY = 20;
        button.display();

        button.setClicked(true);
        button.display();

        button.setOn(true);
    }

    @Test
    public void testSetSpell() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);

        ButtonSpell button = new ButtonSpell(app, 10, 20, 30, 40, "Pause", "P");
        app.getTowers().add(new Tower(app, app.getJson(), 100, 100, 10));

        button.setSpell(app.getManaBar(), app.getMonsters(), app.getTowers());
        button.display();
        button.setSpell(app.getManaBar(), app.getMonsters(), app.getTowers());
        button.display();
        button.setSpell(app.getManaBar(), app.getMonsters(), app.getTowers());
        button.display();
    }


}