package WizardTD;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;


/**
 * The `WaveTimer` class manages the timing and progression of waves in a tower defense game.
 * It calculates the time remaining for the current wave and handles the transition to the next wave.
 */
public class WaveTimer extends App{
    private PApplet parent;
    private JSONArray waveData;
    private int currentWaveIndex;
    private int waveStartTime;
    private float preWavePause;
    private int timeRemaining;
    private boolean isFastForwarding;
    private boolean allDone;
    private int waveDuration;
    private int elapsedTime;


    /**
     * Constructs a new `WaveTimer` object.
     *
     * @param parent The PApplet instance for rendering.
     * @param config A JSON object containing wave data configuration.
     */
    public WaveTimer(PApplet parent, JSONObject config) {
        this.parent = parent;
        this.waveData = config.getJSONArray("waves");
        this.currentWaveIndex = 0;
        this.waveStartTime = 0;
        this.preWavePause = 0;
        this.isFastForwarding = false;
        this.allDone = false;

        // Check if there are waves in the configuration
        if (waveData.size() > 0) {
            preWavePause = waveData.getJSONObject(0).getFloat("pre_wave_pause");
        }
    }

    /**
     * Updates the timer, calculates the time remaining for the current wave, and handles wave progression.
     */
    public void updateTimer() {
        int currentTime = parent.millis();
        elapsedTime = (currentTime - waveStartTime);

        if (currentWaveIndex < waveData.size()) {
            waveDuration = waveData.getJSONObject(currentWaveIndex).getInt("duration");
            int waveInterval = waveDuration + (int)(preWavePause * 1000);
            

            if (isFastForwarding){
                elapsedTime = elapsedTime*2;
            }
            
            timeRemaining = waveInterval - elapsedTime;
    
            if (timeRemaining <= 0) {
                // Move to the next wave                
                currentWaveIndex++;
                waveStartTime = currentTime;
                if (currentWaveIndex < waveData.size()) {
                    preWavePause = waveData.getJSONObject(currentWaveIndex).getFloat("pre_wave_pause");
                }
            }
        } else {
            allDone = true;
        }
    }

    /**
     * Displays the wave timer at the specified coordinates on the screen.
     *
     * @param x The x-coordinate for displaying the timer.
     * @param y The y-coordinate for displaying the timer.
     */
    public void displayTimer(int x, int y) {
        parent.fill(0);
        parent.textSize(24);
        if (currentWaveIndex < waveData.size()) {
            int secondsRemaining = timeRemaining / 1000;
            parent.text("Wave " + (currentWaveIndex + 1) + " starts: " + secondsRemaining, x, y);
        }
    }

    public boolean isFastForwarding() {
        return isFastForwarding;
    }

    public void setFastForwarding(boolean isFastForwarding) {
        this.isFastForwarding = isFastForwarding;
    }

    public int getCurrentWaveIndex() {
        return currentWaveIndex;
    }

    public float getPreWavePause() {
        return preWavePause;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public boolean isAllDone() {
        return allDone;
    }

    public float getWaveDuration() {
        return waveDuration;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }
}