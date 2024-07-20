import org.junit.jupiter.api.Test;
import WizardTD.Monster;
import WizardTD.Tower;
import WizardTD.MonsterConfig;
import WizardTD.App;
import WizardTD.PathFinding;
import WizardTD.Button;
import WizardTD.Wave;

import processing.data.JSONArray;
import processing.data.JSONObject;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.*;
import java.util.*;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaveTest {
    @Test
    public void testWaveSetup() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(5000);

        Wave wave = new Wave(app, app.getJson());
        wave.isWaveOver();
        wave.isWaveStarted();
        wave.getPreWavePause();
        wave.getWaveStartTime();
        wave.getWaveMonsters();
    }
}
