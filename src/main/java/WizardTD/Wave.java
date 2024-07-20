package WizardTD;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The `Wave` class represents a wave of monsters in a tower defense game.
 * It stores information about the monsters in each wave, such as their types, attributes, and quantities.
 * The class also keeps track of the wave's status, including when it starts, its duration, and if it's over.
 */
public class Wave extends App{
    private List<List<MonsterConfig>> waves = new ArrayList<>();
    private List<List<Monster>> waveMonsters = new ArrayList<>();

    private float duration;  // Duration of the wave in seconds
    private float preWavePause;  // Pause before the wave starts
    private boolean waveStarted;  // Indicates if the wave has started
    private float waveStartTime;  // Time when the wave started
    private JSONArray wavesArray;
    private JSONArray monstersArray;    
    private PApplet parent;

    /**
     * Constructs a new `Wave` object.
     *
     * @param parent The PApplet instance for rendering.
     * @param config A JSON object containing wave and monster configuration data.
     */
    public Wave(PApplet parent, JSONObject config) {
        wavesArray = config.getJSONArray("waves");
        
        for (int i = 0; i < wavesArray.size(); i++) {
            JSONObject wave = wavesArray.getJSONObject(i);
            monstersArray = wave.getJSONArray("monsters");

            List<MonsterConfig> monsterConfigs = new ArrayList<>();
        
            for (int j = 0; j < monstersArray.size(); j++) {
                JSONObject monster = monstersArray.getJSONObject(j);
                String type = monster.getString("type");
                int hp = monster.getInt("hp");
                double speed = monster.getDouble("speed");
                double armour = monster.getDouble("armour");
                int manaGainedOnKill = monster.getInt("mana_gained_on_kill");
                int quantity = monster.getInt("quantity");
        
                MonsterConfig monsterConfig = new MonsterConfig(parent, type, hp, speed, armour, manaGainedOnKill, quantity);
                monsterConfigs.add(monsterConfig);
            }
            waves.add(monsterConfigs);
        }
    }

    public List<List<MonsterConfig>> getWaves() {
        return waves;
    }

    /**
     * Checks if the current wave is over.
     *
     * @return `true` if the wave has ended, `false` otherwise.
     */
    public boolean isWaveOver() {
        if (waveStarted) {
            return parent.millis() - waveStartTime >= (preWavePause + duration) * 1000;
        }
        return false;
    }

    public boolean isWaveStarted() {
        return waveStarted;
    }

    public float getPreWavePause() {
        return preWavePause;
    }

    public float getWaveStartTime() {
        return waveStartTime;
    }

    public List<List<Monster>> getWaveMonsters() {
        return waveMonsters;
    }


}
