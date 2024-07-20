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

public class MonsterTest {

    @Test
    public void createMonster() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000);
        PathFinding path = new PathFinding(app, app.getGrid());
        MonsterConfig monsterConfig = new MonsterConfig(app, "gremlin", 100, 2, 2, 5, 1); 
        Monster monster = new Monster(app, app.getGameMap().getStartingPoints().get(0), app.getGameMap().getTargetPoint(), path, monsterConfig);
        assertTrue(monster.isExists());
        assertFalse(monster.isFastForwarding());
        assertFalse(monster.isFrozen());
    }

    @Test
    public void damageMonster() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(3000);
        PathFinding path = new PathFinding(app, app.getGrid());
        MonsterConfig monsterConfig = new MonsterConfig(app, "gremlin", 100, 2, 2, 5, 1); 
        Monster monster = new Monster(app, app.getGameMap().getStartingPoints().get(0), app.getGameMap().getTargetPoint(), path, monsterConfig);
        monster.receiveDamage(10);
        monster.update();
        monster.receiveDamage(100);
        monster.update();
    }

    @Test
    public void monsterMovementTest() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(3000);
        PathFinding path = new PathFinding(app, app.getGrid());
        MonsterConfig monsterConfig = new MonsterConfig(app, "gremlin", 100, 2, 2, 5, 1); 
        Monster monster = new Monster(app, app.getGameMap().getStartingPoints().get(0), app.getGameMap().getTargetPoint(), path, monsterConfig);
        monster.update();
        monster.setSpeed(10);
        monster.movetoStart();
        monster.setExists(false);
        monster.update();
        monster.setFastForwarding(true);
        monster.update();
    }


}
