import org.junit.jupiter.api.Test;
import WizardTD.Monster; // Import the Monster class from your game code
import WizardTD.Tower;  // Import the Tower class from your game code
import WizardTD.MonsterConfig;  // Import the MonsterConfig class from your game code
import WizardTD.App;  // Import the MonsterConfig class from your game code
import WizardTD.PathFinding;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.*;
import java.util.*;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterConfigTest {
    App app = new App();
    MonsterConfig monsterConfig = new MonsterConfig(app, "gremlin", 100, 2, 2, 5, 1);  

    @Test
    public void testType() {     
        String expectedType = "gremlin";
        assertEquals(expectedType, monsterConfig.getType());
    }

    @Test
    public void testSpeed() {    
        assertEquals(2, monsterConfig.getSpeed());
        monsterConfig.setSpeed(5);
        float expectedSpeed = 5;
        assertEquals(expectedSpeed, monsterConfig.getSpeed());
    }

    @Test
    public void testQuantity() {    
        int expectedQuantity = 1;
        assertEquals(expectedQuantity, monsterConfig.getQuantity());
    }

    @Test
    public void testArmor() {    
        int expectedArmor = 2;
        assertEquals(expectedArmor, monsterConfig.getArmor());
    }

    @Test
    public void testMaxHP() {    
        int expectedHP = 100;
        assertEquals(expectedHP, monsterConfig.getMaxHP());
    }

    @Test
    public void testManaGainedOnKill() {    
        int expectedManaGainedOnKill = 5;
        assertEquals(expectedManaGainedOnKill, monsterConfig.getManaGainedOnKill());
        monsterConfig.setManaGainedOnKill(10);
        assertEquals(10, monsterConfig.getManaGainedOnKill());
    }

    @Test
    public void testDeathAnimation() {    
        ArrayList<PImage> deathAnimationFrames = new ArrayList<PImage>();
        assertEquals(deathAnimationFrames, monsterConfig.getDeathAnimationFrames());
    }
}