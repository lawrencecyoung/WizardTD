package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.*;

/**
 * The base class for the Wizard Tower Defense game.
 * It extends the PApplet class from the Processing library to provide the game's functionality.
 */
public class App extends PApplet {

    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE+TOPBAR;

    public static final int FPS = 60;

    public String configPath;
    public JSONObject json;
    public String mapFileName;

    public Random random = new Random();
    
    // Feel free to add any additional methods or attributes you want. Please put classes in different files.

    private GameMap gameMap;

    private List<Monster> monsters = new ArrayList<>();
    private List<Tower> towers = new ArrayList<>();

    private WaveTimer waveTimer;
    private ManaBar manaBar;
    private Sidebar sidebar;
    private Wave waves;
    private List<List<Monster>> waveMonsters = new ArrayList<>();
    private List<Monster> currentWave;
    private int waveSize;

    private int towerCost;

    private int[][] grid;  // Your game grid with 1, 2, and 3 values
    private int[][] gameMappy;
    private int monsterIndex = 0;
    private boolean lost = false;

    private boolean allMonstersDead = false;

    /**
     * Draws all the towers on the game map.
     * This method iterates through the list of towers and displays each tower on the screen.
     */
    public void drawTowers() {
        for (Tower tower : towers) {
            tower.display(); // Display each tower
        }
    }

    /**
     * Converts a map file text into a 2D integer array.
     * This method takes an array of strings, where each character represents a specific cell type,
     * and converts it into a 2D integer array representing the game map.
     *
     * @param mapLines An array of strings representing the map layout.
     * @return A 2D integer array representing the game map, where each value corresponds to a cell type.
     */
    public int[][] getGameMap(String[] mapLines) {
        int numRows = mapLines.length;
        int numCols = mapLines[0].length();

        int[][] mapGrid = new int[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                char cellChar = mapLines[row].charAt(col);
                if (cellChar == 'X') {
                    if (row == 0 || col == 0 || row  == numRows-1 || col == numCols-1) {
                        mapGrid[row][col] = 3;
                    } else {
                        mapGrid[row][col] = 1;
                    }
                } else if (cellChar == 'W') {
                    mapGrid[row][col] = 2;  // 2 = Wizard House
                } else if (cellChar == 'S') {
                    mapGrid[row][col] = 4;
                }
            }
        }
        return mapGrid;
    }

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
     */
    @Override
    public void setup() {
        frameRate(FPS);
        
        json = loadJSONObject(configPath);
        mapFileName = json.getString("layout");
        waveTimer = new WaveTimer(this, json);
        manaBar = new ManaBar(this, json);      
        gameMap = new GameMap(this, mapFileName);
        sidebar = new Sidebar(this, json);
        waves = new Wave(this, json);


        grid = gameMap.getPathGrid();

        towerCost = json.getInt("tower_cost");

        List<int[]> startingPoints = gameMap.getStartingPoints();
        int[] end = gameMap.getTargetPoint();

        String[] mapLines = gameMap.getMapLines();
        gameMappy = getGameMap(mapLines);

        List<List<MonsterConfig>> wavesArray = waves.getWaves();


        for (List<MonsterConfig> monsterConfigsForWave : wavesArray) {
            List<Monster> monstersForWave = new ArrayList<>();

            // Iterate through the MonsterConfig objects for the current wave
            for (MonsterConfig monsterConfig : monsterConfigsForWave) {
                int quantity = monsterConfig.getQuantity();

                if (monsterConfig.getType().equals("gremlin")) {
                    monsterConfig.setSpriteImage(loadImage("src/main/resources/WizardTD/gremlin.png"));
                    monsterConfig.getDeathAnimationFrames().add(loadImage("src/main/resources/WizardTD/gremlin1.png"));
                    monsterConfig.getDeathAnimationFrames().add(loadImage("src/main/resources/WizardTD/gremlin2.png"));
                    monsterConfig.getDeathAnimationFrames().add(loadImage("src/main/resources/WizardTD/gremlin3.png"));
                    monsterConfig.getDeathAnimationFrames().add(loadImage("src/main/resources/WizardTD/gremlin4.png"));
                    monsterConfig.getDeathAnimationFrames().add(loadImage("src/main/resources/WizardTD/gremlin5.png"));
                } else if (monsterConfig.getType().equals("beetle")) {
                    monsterConfig.setSpriteImage(loadImage("src/main/resources/WizardTD/beetle.png"));
                } else if (monsterConfig.getType().equals("worm")) {
                    monsterConfig.setSpriteImage(loadImage("src/main/resources/WizardTD/worm.png"));
                }



                // Create the specified number of Monster objects with the same properties
                for (int i = 0; i < quantity; i++) {
                    int[] randomStart = startingPoints.get(random.nextInt(startingPoints.size()));
                    PathFinding path = new PathFinding(this, grid);
                    Monster monster = new Monster(this, randomStart, end, path, monsterConfig);
                    monstersForWave.add(monster);
                }
            }
            waveMonsters.add(monstersForWave);
        }
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(){
        if (key == 'f' || key == 'F'){
            sidebar.getButtonFastForward().setisFastForwarding(!sidebar.getButtonFastForward().getisFastForwarding());
        }

        if (key == 'p' || key == 'P'){
            sidebar.getButtonPause().setPaused(!sidebar.getButtonPause().getisPaused());
        }

        if (key == 't' || key == 'T'){
            sidebar.getButtonBuildTower().setisBuilding(!sidebar.getButtonBuildTower().getisBuilding());
        }

        if (key == '1'){
            sidebar.getButtonUpgradeRange().setisUpgrading(!sidebar.getButtonUpgradeRange().getisUpgrading());
        }

        if (key == '2'){
            sidebar.getButtonUpgradeSpeed().setisUpgrading(!sidebar.getButtonUpgradeSpeed().getisUpgrading());
        }

        if (key == '3'){
            sidebar.getButtonUpgradeDamage().setisUpgrading(!sidebar.getButtonUpgradeDamage().getisUpgrading());
        }

        if (key == 'm' || key == 'M'){
            sidebar.getButtonManaPool().setClicked(!sidebar.getButtonManaPool().isClicked());
        }

        if (key == 'r' || key == 'R'){
            monsters.clear();
            towers.clear();
            waveMonsters.clear();
            currentWave.clear();
            lost = false;
            setup();
        }

        if (key == 's' || key == 'S'){
            sidebar.getButtonSpell().setClicked(!sidebar.getButtonSpell().isClicked());
        }



    }

    /**
     * Receive key released signal from the keyboard.
     */
    @Override
    public void keyReleased(){
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (sidebar.getButtonBuildTower().getisBuilding() && mousePressed) {
            if (manaBar.getMana() >= towerCost) {
                for (int row = 0; row < gameMappy.length; row++) {
                    for (int col = 0; col < gameMappy[0].length; col++) {
                        if (mouseY>TOPBAR) {
                            if (mouseX>row*CELLSIZE && mouseX<(row+1)*CELLSIZE && mouseY>(col*CELLSIZE)+TOPBAR && mouseY<((col+1)*CELLSIZE)+TOPBAR) {
                                if (gameMappy[col][row]==0) {
                                    fill(255);
                                    text(col, row*CELLSIZE, (col*CELLSIZE)+TOPBAR+CELLSIZE/2);
                                    towers.add(new Tower(this, json, manaBar.getMana(), row*CELLSIZE, (col*CELLSIZE)+TOPBAR));
                                    manaBar.setMana(manaBar.getMana()-towerCost);
                                    gameMappy[col][row] = 9;
                                }
                            }
                        }
                    }
                }
                
            }
        }

        if (!sidebar.getButtonPause().getisPaused()) {
            for (Tower tower : towers) {
                if (tower.isMouseOver() && mousePressed) {
                    int totalCost = tower.costmenu(sidebar.getButtonUpgradeRange().getisUpgrading(), sidebar.getButtonUpgradeSpeed().getisUpgrading(), sidebar.getButtonUpgradeDamage().getisUpgrading());
                    if (manaBar.getMana() >= totalCost){
                        tower.setLevel(sidebar.getButtonUpgradeRange().getisUpgrading(), sidebar.getButtonUpgradeSpeed().getisUpgrading(), sidebar.getButtonUpgradeDamage().getisUpgrading());
                        manaBar.setMana(manaBar.getMana()-(totalCost));
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /*@Override
    public void mouseDragged(MouseEvent e) {

    }*/

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {
        gameMap.draw();
        sidebar.display();
        waveTimer.displayTimer(10, 30);
        manaBar.display(380, 10);
        drawTowers();

        for (Monster monster : monsters) {
            monster.draw();
            image(gameMap.getWizardhouseImage(),gameMap.getWizardhouseX(),gameMap.getWizardhouseY());
        }

        if (waveTimer.isAllDone()) {
            allMonstersDead = true;
            for (Monster monster : monsters) {
                if (!monster.isDead() && !monster.isAtHouse()) {
                    allMonstersDead = false;
                };
            }
            if (allMonstersDead == true) {
                textSize(30);
                fill(255,50,255);
                text("YOU WIN", 250, 225);
                lost = true;
            }
        }

        float waveDuration = waveTimer.getWaveDuration();
        int timeInterval = (int) ((waveDuration / waveSize) * 1000);
        int elapsedTime = waveTimer.getElapsedTime();

        if (manaBar.getMana()<=0) {
            lost = true;
            manaBar.setMana(0);
            textSize(30);
            fill(0,255,0);
            text("YOU LOST", 250, 225);
            textSize(20);
            text("Press 'r' to restart", 235, 275);
        }

        if (waveTimer.getTimeRemaining() < 0 && !waveTimer.isAllDone()) {
            currentWave = waveMonsters.get(waveTimer.getCurrentWaveIndex() - 1);
            waveSize = currentWave.size();
            monsterIndex = 0; // Initialize the index to add monsters from the beginning
        }

        if (elapsedTime > monsterIndex * timeInterval) {
            if (monsterIndex < waveSize) {
                Monster mons = currentWave.get(monsterIndex);
                monsters.add(mons);
                monsterIndex++;
            }
        }
            
        if (!lost) {
        
            if (sidebar.getButtonFastForward().getisFastForwarding() && !sidebar.getButtonPause().getisPaused()) {
                manaBar.setFastForwarding(true);
                manaBar.update();
                waveTimer.setFastForwarding(true);
                waveTimer.updateTimer();

                for (Monster monster : monsters) {
                    for (Tower tower : towers) {
                        tower.setFastForwarding(true);
                        tower.isWithinRange(monster); // Display each tower
                    }
                    monster.setFastForwarding(true);
                    monster.update(); // Display each tower 
                    if (monster.isAtHouse()) {
                        manaBar.setMana(manaBar.getMana() - monster.getCurrentHP());
                        monster.setCurrentHP(0);
                    } else if (monster.isDead() && monster.isExists()) {
                        manaBar.setMana(manaBar.getMana() + monster.getConfig().getManaGainedOnKill()*(1+(sidebar.getButtonManaPool().getManaPoolGainedMultiplier())));
                        monster.setExists(false);
                    }
                }
            } else if (!sidebar.getButtonPause().getisPaused()) {
                manaBar.setFastForwarding(false);
                manaBar.update();

                waveTimer.setFastForwarding(false);
                waveTimer.updateTimer();

                for (Monster monster : monsters) {
                    for (Tower tower : towers) {
                        tower.setFastForwarding(false);
                        tower.isWithinRange(monster); // Display each tower
                    }
                    monster.setFastForwarding(false);
                    monster.update(); // Display each tower
                    
                    
                    if (monster.isAtHouse()) {
                        manaBar.setMana(manaBar.getMana() - monster.getCurrentHP());
                        monster.setCurrentHP(0);
                    } else if (monster.isDead() && monster.isExists()) {
                        manaBar.setMana(manaBar.getMana() + monster.getConfig().getManaGainedOnKill()*(1+(sidebar.getButtonManaPool().getManaPoolGainedMultiplier())));
                        monster.setExists(false);
                    }
                }
            } 
        }

    for (Tower tower : towers) {
        if (tower.isMouseOver()) {
            tower.costmenu(sidebar.getButtonUpgradeRange().getisUpgrading(), sidebar.getButtonUpgradeSpeed().getisUpgrading(), sidebar.getButtonUpgradeDamage().getisUpgrading());
        }
    }

    if (sidebar.getButtonManaPool().getisOn()) {
        sidebar.getButtonManaPool().upgradingManaPool(manaBar);
    } 

    if (sidebar.getButtonSpell().getisOn()) {
        sidebar.getButtonSpell().setSpell(manaBar, monsters, towers);    
    } 

}
    
    public static void main(String[] args) {
        PApplet.main("WizardTD.App");
    }
    /**
     * Source: https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * @param pimg The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = this.createImage(newWidth, newHeight, ARGB);
        //BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }
        return result;
    }

    public JSONObject getJson() {
        return json;
    }

    public int[][] getGrid() {
        return grid;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public ManaBar getManaBar() {
        return manaBar;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    public WaveTimer getWaveTimer() {
        return waveTimer;
    }
    
}

