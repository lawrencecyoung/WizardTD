package WizardTD;

import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.StringList;
import processing.core.PImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import WizardTD.App;
import processing.core.PVector;

/**
 * The GameMap class represents the game map and its elements.
 */
public class GameMap extends App{
    private PApplet parent;
    private String mapFileName;
    private String[] mapLines;
    private int[][] pathGrid;
    private List<int[]> startingPoints;
    private int[] targetPoint;

    PImage grassImage;
    PImage shrubImage;
    PImage wizardhouseImage;

    PImage pathstraightImage;
    PImage pathcornerImage;
    PImage pathtjuncImage;
    PImage pathcentreImage;
    PImage wizardHouseRotated;
    int wizardhouseX;
    int wizardhouseY;

    App appInstance = new App();
    int cellsize = App.CELLSIZE;
    int sidebar = App.SIDEBAR;
    int topbar = App.TOPBAR;
    int board_width = App.BOARD_WIDTH;
    int width = App.WIDTH;
    int height = App.HEIGHT;

    /**
     * Constructs a GameMap object.
     *
     * @param parent      The PApplet instance to which the map belongs.
     * @param mapFileName The filename of the map data.
     */

    public GameMap(PApplet parent, String mapFileName) {
        this.parent = parent;
        this.mapFileName = mapFileName;
        loadMapData();
    }

    /**
     * Draws the game map and its elements.
     */
    public void draw() {
        parent.fill(136, 116, 76);
        parent.noStroke();
        parent.rect(0, 0, width, topbar);

        parent.fill(136, 116, 76);
        parent.rect(width - sidebar, topbar, sidebar, height - topbar);

        for (int row = 0; row < mapLines.length; row++) {
            if (row >= mapLines.length) {continue;}
            String line = mapLines[row];
            for (int col = 0; col < line.length(); col++) {
                if (col >= line.length()) {continue;}

                int x = col * cellsize;
                int y = row * cellsize + topbar; // Adjust for the top bar
                char cellChar = line.charAt(col);

                if (cellChar == ' ' || cellChar == 'W') {
                    parent.image(grassImage, x, y);
                } else if (cellChar == 'S') {
                    parent.image(shrubImage, x, y);
                } else if (cellChar == 'X') {
                    PImage pathImage = choosePathImage(mapLines, row, col);
                    parent.image(pathImage, x, y);
                }
            }
        }
        for (int row = 0; row < mapLines.length; row++) {
            String line = mapLines[row];
            for (int col = 0; col < line.length(); col++) {
                int x = col * cellsize;
                int y = row * cellsize + topbar; // Adjust for the top bar
                char cellChar = line.charAt(col);
                if (cellChar == 'W') {
                    wizardHouseRotated = chooseWizardImage(mapLines, row, col);
                    wizardhouseX = col * cellsize + (cellsize / 2) - 24;
                    wizardhouseY = row * cellsize + (cellsize / 2) + topbar - 24; // Adjust for the top bar

                    parent.image(wizardHouseRotated, wizardhouseX , wizardhouseY, 48, 48);
                }
            }
        }
    }

    /**
     * Loads the map data from the map file and sprites.
     */
    private void loadMapData() {
        startingPoints = new ArrayList<>();
        mapLines = parent.loadStrings(mapFileName);

        grassImage = parent.loadImage("src/main/resources/WizardTD/grass.png");
        shrubImage = parent.loadImage("src/main/resources/WizardTD/shrub.png");
        wizardhouseImage = parent.loadImage("src/main/resources/WizardTD/wizard_house.png");

        pathstraightImage = parent.loadImage("src/main/resources/WizardTD/path0.png");
        pathcornerImage = parent.loadImage("src/main/resources/WizardTD/path1.png");
        pathtjuncImage = parent.loadImage("src/main/resources/WizardTD/path2.png");
        pathcentreImage = parent.loadImage("src/main/resources/WizardTD/path3.png");

        int numRows = mapLines.length;
        int numCols = mapLines[0].length();

        pathGrid = new int[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                char cellChar = mapLines[row].charAt(col);
                if (cellChar == 'X') {
                    if (row == 0 || col == 0 || row  == numRows-1 || col == numCols-1) {
                        pathGrid[row][col] = 3;
                        int[] coordinates = {row, col};
                        startingPoints.add(coordinates);
                    } else {
                        pathGrid[row][col] = 1;
                    }
                } else if (cellChar == 'W') {
                    pathGrid[row][col] = 2;  // 2 = Wizard House
                    targetPoint = new int[] {row, col};
                } else {
                    pathGrid[row][col] = 0;
                }
            }
        }
    }
    
    /**
     * Chooses the appropriate wizard house image based on the map layout and cell position.
     *
     * @param layout The map layout.
     * @param row    The row index of the cell.
     * @param col    The column index of the cell.
     * @return The chosen wizard house image.
     */
    public PImage choosePathImage(String[] layout, int row, int col) {
        char above = ' ';
        char below = ' ';
        char left = ' ';
        char right = ' ';
      
        // Check above
        if (row > 0) {above = layout[row - 1].charAt(col);}
        // Check below
        if (row < layout.length - 1) {below = layout[row + 1].charAt(col);}
        // Check left
        if (col > 0) {left = layout[row].charAt(col - 1);}
        // Check right
        if (col < layout[row].length() - 1) {right = layout[row].charAt(col + 1);}
      
        if (above == 'X' && below == 'X' && left == 'X' && right == 'X') {
            return pathcentreImage;
        } else if (below == 'X' && left == 'X' && right == 'X') {
            return pathtjuncImage;
        } else if (above == 'X' && left == 'X' && right == 'X') {
            return appInstance.rotateImageByDegrees(pathtjuncImage, 180);
        } else if (above == 'X' && below == 'X' && right == 'X') {
            return appInstance.rotateImageByDegrees(pathtjuncImage, 270);
        } else if (above == 'X' && below == 'X' && left == 'X') {
            return appInstance.rotateImageByDegrees(pathtjuncImage, 90);
        } else if (above == 'X' && below == 'X') {
            return appInstance.rotateImageByDegrees(pathstraightImage,90);
        } else if (left == 'X' && right == 'X') {
            return pathstraightImage;
        } else if (above == 'X' && left == 'X') {
            return appInstance.rotateImageByDegrees(pathcornerImage,90);
        } else if (left == 'X' && below == 'X') {
            return pathcornerImage;
        } else if (right == 'X' && below == 'X') {
            return appInstance.rotateImageByDegrees(pathcornerImage,270);
        } else if (above == 'X' && right == 'X') {
            return appInstance.rotateImageByDegrees(pathcornerImage,180);
        } else if (above == 'X') {
            return appInstance.rotateImageByDegrees(pathstraightImage,90);
        } else if (below == 'X') {
            return appInstance.rotateImageByDegrees(pathstraightImage,90);
        } else if (left == 'X') {
            return pathstraightImage;
        } else if (right == 'X') {
            return pathstraightImage;
        }
        return null;
    }


    public PImage chooseWizardImage(String[] layout, int row, int col) {
        char above = ' ';
        char below = ' ';
        char left = ' ';
        char right = ' ';
      
        // Check above
        if (row > 0) {above = layout[row - 1].charAt(col);}
        // Check below
        if (row < layout.length - 1) {below = layout[row + 1].charAt(col);}
        // Check left
        if (col > 0) {left = layout[row].charAt(col - 1);}
        // Check right
        if (col < layout[row].length() - 1) {right = layout[row].charAt(col + 1);}
      
        // Determine the appropriate path image based on surroundings
        if (above == 'X' && below == ' ' && left == ' ' && right == ' ') {
            return appInstance.rotateImageByDegrees(wizardhouseImage, 90);
        } else if (above == ' ' && below == ' ' && left == ' ' && right == 'X')  {
            return appInstance.rotateImageByDegrees(wizardhouseImage, 180);
        } else if (above == ' ' && below == 'X' && left == ' ' && right == ' ')  {
            return appInstance.rotateImageByDegrees(wizardhouseImage, 270);
        } else if (above == ' ' && below == ' ' && left == 'X' && right == ' ')  {
            return appInstance.rotateImageByDegrees(wizardhouseImage, 0);
        } 
        return null;
    }

    public int[][] getPathGrid() {
        return pathGrid;
    }

    public List<int[]> getStartingPoints() {
        return startingPoints;
    }

    public int[] getTargetPoint() {
        return targetPoint;
    }

    public String[] getMapLines() {
        return mapLines;
    }

    public PImage getWizardhouseImage() {
        return wizardHouseRotated;
    }

    public int getWizardhouseX() {
        return wizardhouseX;
    }

    public int getWizardhouseY() {
        return wizardhouseY;
    }
    
    
}
    
