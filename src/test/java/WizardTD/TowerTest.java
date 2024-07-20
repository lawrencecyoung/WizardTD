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

public class TowerTest {
    @Test
    public void buildTower() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);

    }

    @Test
    public void costmenuTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);
        tower.costmenu(true, true, true);
        tower.costmenu(false, true, true);
        tower.costmenu(true, false, true);
        tower.costmenu(true, true, false);
        tower.costmenu(false, false, true);
        tower.costmenu(true, false, false);
        tower.costmenu(false, true, false);
        tower.costmenu(false, false, false);

    }

    @Test
    public void setLevel() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);

        tower.setLevel(true, true, true);
        tower.setLevel(false, true, true);
        tower.setLevel(true, false, true);
        tower.setLevel(true, true, false);
        tower.setLevel(false, false, true);
        tower.setLevel(true, false, false);
        tower.setLevel(false, true, false);
        tower.setLevel(false, false, false);

        tower.setRangeLevel(1);
        tower.setDamageLevel(1);
        tower.setSpeedLevel(1);
        tower.setLevel(true, true, true);
        tower.setLevel(false, true, true);
        tower.setLevel(true, false, true);
        tower.setLevel(true, true, false);
        tower.setLevel(false, false, true);
        tower.setLevel(true, false, false);
        tower.setLevel(false, true, false);
        tower.setLevel(false, false, false);

        tower.setRangeLevel(2);
        tower.setDamageLevel(2);
        tower.setSpeedLevel(2);
        tower.setLevel(true, true, true);
        tower.setLevel(false, true, true);
        tower.setLevel(true, false, true);
        tower.setLevel(true, true, false);
        tower.setLevel(false, false, true);
        tower.setLevel(true, false, false);
        tower.setLevel(false, true, false);
        tower.setLevel(false, false, false);
        
        tower.setRangeLevel(3);
        tower.setDamageLevel(3);
        tower.setSpeedLevel(3);
        tower.setLevel(true, true, true);
        tower.setLevel(false, true, true);
        tower.setLevel(true, false, true);
        tower.setLevel(true, true, false);
        tower.setLevel(false, false, true);
        tower.setLevel(true, false, false);
        tower.setLevel(false, true, false);
        tower.setLevel(false, false, false);

        tower.setRangeLevel(3);
        tower.setDamageLevel(1);
        tower.setSpeedLevel(3);
        tower.setLevel(true, true, true);
        tower.setLevel(false, true, true);
        tower.setLevel(true, false, true);
        tower.setLevel(true, true, false);
        tower.setLevel(false, false, true);
        tower.setLevel(true, false, false);
        tower.setLevel(false, true, false);
        tower.setLevel(false, false, false);

        tower.setRangeLevel(1);
        tower.setDamageLevel(3);
        tower.setSpeedLevel(3);
        tower.setLevel(true, true, true);
        tower.setLevel(false, true, true);
        tower.setLevel(true, false, true);
        tower.setLevel(true, true, false);
        tower.setLevel(false, false, true);
        tower.setLevel(true, false, false);
        tower.setLevel(false, true, false);
        tower.setLevel(false, false, false);

        tower.setRangeLevel(3);
        tower.setDamageLevel(3);
        tower.setSpeedLevel(1);
        tower.setLevel(true, true, true);
        tower.setLevel(false, true, true);
        tower.setLevel(true, false, true);
        tower.setLevel(true, true, false);
        tower.setLevel(false, false, true);
        tower.setLevel(true, false, false);
        tower.setLevel(false, true, false);
        tower.setLevel(false, false, false);


    }

    @Test
    public void setlevelTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);

        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);

        tower.setRangeLevel(1);
        tower.setDamageLevel(1);
        tower.setSpeedLevel(1);

        assertEquals(1, tower.getRangeLevel());
        assertEquals(1, tower.getDamageLevel());
        assertEquals(1, tower.getSpeedLevel());

        tower.setRangeLevel(2);
        tower.setDamageLevel(2);
        tower.setSpeedLevel(2);

        assertEquals(2, tower.getRangeLevel());
        assertEquals(2, tower.getDamageLevel());
        assertEquals(2, tower.getSpeedLevel());

    }

    @Test
    public void displayTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);
        tower.display();

        tower.setRangeLevel(1);
        tower.setDamageLevel(0);
        tower.setSpeedLevel(0);
        tower.display();
        tower.setRangeLevel(0);
        tower.setDamageLevel(1);
        tower.setSpeedLevel(1);
        tower.display();
        tower.setRangeLevel(0);
        tower.setDamageLevel(2);
        tower.setSpeedLevel(2);
        tower.display();
        tower.setRangeLevel(0);
        tower.setDamageLevel(3);
        tower.setSpeedLevel(3);
        tower.display();
        tower.setRangeLevel(1);
        tower.setDamageLevel(0);
        tower.setSpeedLevel(3);
        tower.display();
        tower.setRangeLevel(2);
        tower.setDamageLevel(0);
        tower.setSpeedLevel(3);
        tower.display();
        tower.setRangeLevel(3);
        tower.setDamageLevel(0);
        tower.setSpeedLevel(3);
        tower.display();


        tower.setRangeLevel(1);
        tower.setDamageLevel(1);
        tower.setSpeedLevel(1);
        tower.display();

        tower.setRangeLevel(1);
        tower.setDamageLevel(2);
        tower.setSpeedLevel(2);
        tower.display();

        tower.setRangeLevel(1);
        tower.setDamageLevel(3);
        tower.setSpeedLevel(3);
        tower.display();

        tower.setRangeLevel(2);
        tower.setDamageLevel(1);
        tower.setSpeedLevel(3);
        tower.display();

        tower.setRangeLevel(3);
        tower.setDamageLevel(1);
        tower.setSpeedLevel(3);
        tower.display();


        tower.setRangeLevel(2);
        tower.setDamageLevel(2);
        tower.setSpeedLevel(2);
        tower.display();

        tower.setRangeLevel(3);
        tower.setDamageLevel(3);
        tower.setSpeedLevel(3);
        tower.display();
    }

    @Test
    public void isinRangeTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);
        MonsterConfig monsterConfig = new MonsterConfig(app, "gremlin", 100, 2, 2, 5, 1); 
        PathFinding path = new PathFinding(app, app.getGrid());
        Monster monster = new Monster(app, app.getGameMap().getStartingPoints().get(0), app.getGameMap().getTargetPoint(), path, monsterConfig);

        tower.isinRange(monster);
        tower.isMouseOver();
        tower.isMouseOverTower();
 
    }

    @Test
    public void isWithinRangeTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);
        MonsterConfig monsterConfig = new MonsterConfig(app, "gremlin", 100, 2, 2, 5, 1); 
        PathFinding path = new PathFinding(app, app.getGrid());
        Monster monster = new Monster(app, app.getGameMap().getStartingPoints().get(0), app.getGameMap().getTargetPoint(), path, monsterConfig);

        tower.isWithinRange(monster);
        tower.setFastForwarding(true);
        tower.isWithinRange(monster);
 
    }

    @Test
    public void fireballTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);
        tower.fireball(10,10,20,20);

    }


    @Test
    public void testgetters() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);
        assertFalse(tower.isFastForwarding());
        assertEquals(tower.getTowerCost(), 100);

    }

    @Test
    public void testMouseOver() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        Tower tower = new Tower(app, app.getJson(), 100, 10, 10);
        app.mouseX = 10;
        app.mouseY = 10;
        tower.display();

    }
}