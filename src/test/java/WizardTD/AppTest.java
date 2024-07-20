
import org.junit.jupiter.api.BeforeEach;
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

public class AppTest {
    private App app;

    @BeforeEach
    public void setup() {
        app = new App();  // Create an instance of the App class for testing
        // You may initialize other required objects or variables here
    }

    @Test
    public void testGetGameMap() {
        String[] mapLines = {
            "                    ",
            "                    ",
            "XXXXX               ",
            "  S X   S  S      S ",
            "  SXXXXXXXXXXXXXX   ",
            "  S X X         X  S",
            "    S S        XX S ",
            "         WXXXXXXX S ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    ",
            "                    "
        };
        int[][] expectedMap = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {3,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,4,0,1,0,0,0,4,0,0,4,0,0,0,0,0,0,4,0},
            {0,0,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,4,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,4},
            {0,0,0,0,4,0,4,0,0,0,0,0,0,0,0,1,1,0,4,0},
            {0,0,0,0,0,0,0,0,0,2,1,1,1,1,1,1,1,0,4,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        int[][] result = app.getGameMap(mapLines);
        assertArrayEquals(expectedMap, result);
    }


    @Test
    public void testKeyPressed() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);

        app.keyPressed();
        app.key = 'f';
        app.keyPressed();
        app.key = 'F';
        app.keyPressed();
        app.key = 'p';
        app.keyPressed();
        app.key = 'P';
        app.keyPressed();
        app.key = 't';
        app.keyPressed();
        app.key = 'T';
        app.keyPressed();
        app.key = '1';
        app.keyPressed();
        app.key = '2';
        app.keyPressed();
        app.key = '3';
        app.keyPressed();
        app.key = 'm';
        app.keyPressed();
        app.key = 'M';
        app.keyPressed();
        app.key = 's';
        app.keyPressed();
        app.key = 'S';
        app.keyPressed();
        app.key = 'r';
        app.keyPressed();
        app.key = 'R';
        app.keyPressed();

    }


    @Test
    public void testFastForward() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);

        app.getTowers().add(new Tower(app, app.getJson(), 100, 100, 100));
        app.getSidebar().getButtonFastForward().setisFastForwarding(!app.getSidebar().getButtonFastForward().getisFastForwarding());
        app.getWaveTimer().isFastForwarding();
        app.getWaveTimer().getPreWavePause();

    }


    @Test
    public void testMousePressed() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);

        app.getSidebar().getButtonBuildTower().setisBuilding(!app.getSidebar().getButtonBuildTower().getisBuilding());
        app.mouseX = 400;
        app.mouseY = 400;
        app.mousePressed();

    }

    @Test
    public void buttonsOn() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);

        app.getSidebar().getButtonManaPool().setOn(true);
        app.getSidebar().getButtonSpell().setOn(true); 

    }

}