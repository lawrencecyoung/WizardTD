package WizardTD;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;



/**
 * The Tower class represents a tower in a tower defense game.
 */
public class Tower extends App{

    PImage tower0;
    PImage tower1;
    PImage tower2;
    PApplet parent;
    private float towerRange;

    private float towerFiringSpeed;
    private long lastFireTime;

    private float towerDamage;
    private int towerCost;

    private int rangeLevel;
    private int damageLevel;
    private int speedLevel;

    private double[] position;
    private float y;
    private float x;

    private float distance;

    private PImage fireball;
    private boolean isFastForwarding;

    App appInstance = new App();
    int cellsize = App.CELLSIZE;
    int topbar = App.TOPBAR;

    /**
     * Constructs a new Tower object.
     *
     * @param parent The parent PApplet for rendering.
     * @param config Tower configuration data from a JSON object.
     * @param currentMana The current amount of mana available in the game.
     * @param x The x-coordinate of the tower's position.
     * @param y The y-coordinate of the tower's position.
     */
    public Tower(PApplet parent, JSONObject config, float currentMana, float x, float y) {
        this.parent = parent;
        loadTowerSprites();
        
        fireball = parent.loadImage("src/main/resources/WizardTD/fireball.png");

        rangeLevel = 0;
        damageLevel = 0;
        speedLevel = 0;

        this.x = x;
        this.y = y;


        towerRange = config.getFloat("initial_tower_range");
        towerFiringSpeed = config.getFloat("initial_tower_firing_speed");
        towerDamage = config.getFloat("initial_tower_damage");
        towerCost = config.getInt("tower_cost");
    }

    private void loadTowerSprites() {
        tower0 = parent.loadImage("src/main/resources/WizardTD/tower0.png");
        tower1 = parent.loadImage("src/main/resources/WizardTD/tower1.png");
        tower2 = parent.loadImage("src/main/resources/WizardTD/tower2.png");
    }

    public void display() {
        if (rangeLevel >= 2 && damageLevel >= 2 && speedLevel >= 2){
            parent.image(tower2, x, y);

            parent.noFill();
            parent.stroke(106, 184, 230);
            if (speedLevel == 3) {
                parent.strokeWeight(2);
                parent.rect(x+5,y+5,cellsize-11,cellsize-11);
            }
            parent.fill(255,0,255);  // Text color (white)
            parent.textSize(10);  // Adjust the text size as needed
            if (rangeLevel == 3) {
                parent.text("O", x, y+8);
            }
            if (damageLevel == 3) {
                parent.text("X", x, y+cellsize);
            }

        } else if (rangeLevel >= 1 && damageLevel >= 1 && speedLevel >= 1){
            parent.image(tower1, x, y);
            
            parent.noFill();
            parent.stroke(106, 184, 230);
            if (speedLevel == 2) {
                parent.strokeWeight(2);
                parent.rect(x+5,y+5,cellsize-11,cellsize-11);
            } else if (speedLevel == 3) {
                parent.strokeWeight(3);
                parent.rect(x+5,y+5,cellsize-11,cellsize-11);
            }

            parent.fill(255,0,255);  // Text color (white)
            parent.textSize(10);  // Adjust the text size as needed
            if (rangeLevel == 2) {
                parent.text("O", x, y+8);
            } else if (rangeLevel == 3) {
                parent.text("OO", x, y+8);
            }

            if (damageLevel == 2) {
                parent.text("X", x, y+cellsize);
            } else if (damageLevel == 3) {
                parent.text("XX", x, y+cellsize);
            }

        } else if (rangeLevel == 0 || damageLevel == 0 || speedLevel == 0) {
            parent.image(tower0, x, y);

            parent.noFill();
            parent.stroke(106, 184, 230);
            if (speedLevel == 1) {
                parent.strokeWeight(2);
                parent.rect(x+5,y+5,cellsize-11,cellsize-11);
            } else if (speedLevel == 2) {
                parent.strokeWeight(3);
                parent.rect(x+5,y+5,cellsize-11,cellsize-11);
            } else if (speedLevel == 3) {
                parent.strokeWeight(4);
                parent.rect(x+5,y+5,cellsize-11,cellsize-11);
            }

            parent.fill(255,0,255);  // Text color (white)
            parent.textSize(10);  // Adjust the text size as needed
            if (rangeLevel == 1) {
                parent.text("O", x, y+8);
            } else if (rangeLevel == 2) {
                parent.text("OO", x, y+8);
            } else if (rangeLevel == 3) {
                parent.text("OOO", x, y+8);
            }

            if (damageLevel == 1) {
                parent.text("X", x, y+cellsize);
            } else if (damageLevel == 2) {
                parent.text("XX", x, y+cellsize);
            } else if (damageLevel == 3) {
                parent.text("XXX", x, y+cellsize);
            }
        }

        if (isMouseOver()) {
            parent.noFill();
            parent.stroke(255, 255, 0);
            parent.strokeWeight(1);
            parent.ellipse(x + cellsize / 2, y + cellsize / 2, towerRange+rangeLevel*cellsize, towerRange+rangeLevel*cellsize);
        }
    }
        
    public boolean isMouseOver() {
        return parent.mouseX >= x && parent.mouseX <= x + cellsize && parent.mouseY >= y && parent.mouseY <= y + cellsize;
    }

    public void setRangeLevel(int rangeLevel) {
        this.rangeLevel = rangeLevel;
    }
    public void setDamageLevel(int damageLevel) {
        this.damageLevel = damageLevel;
    }
    public void setSpeedLevel(int speedLevel) {
        this.speedLevel = speedLevel;
    }

    public int getRangeLevel() {
        return rangeLevel;
    }
    public int getDamageLevel() {
        return damageLevel;
    }
    public int getSpeedLevel() {
        return speedLevel;
    }

    /**
     * Checks if a monster is within the tower's range and attacks.
     *
     * @param monster The monster to check for being within range.
     */

    public void isWithinRange(Monster monster) {
        float towerCenterX = x + cellsize / 2;
        float towerCenterY = y + cellsize / 2;

        float monsterCenterY = (float) monster.getPosition()[0] * cellsize + topbar + cellsize/2;
        float monsterCenterX = (float) monster.getPosition()[1] * cellsize + cellsize/2;

        float rateOfFire = (float) ((1.0 / (towerFiringSpeed + 0.5*speedLevel)));

        if (isFastForwarding) {
            rateOfFire = rateOfFire/2;
        }

        // Check if the monster is within the tower's range (ellipse)
        if (isinRange(monster) && parent.millis() - lastFireTime >= rateOfFire * 1000) {
            if (monster.getCurrentHP() > 0) {
                fireball(towerCenterX, towerCenterY, monsterCenterX, monsterCenterY); 
                monster.receiveDamage(towerDamage+(towerDamage/2)*damageLevel);
                lastFireTime = parent.millis();
            }
        }
    }

    /**
     * Checks if a monster is within the tower's range.
     *
     * @param monster The monster to check for being within range.
     * @return True if the monster is within range, false otherwise.
     */
    public boolean isinRange(Monster monster) {
        float towerCenterX = x + cellsize / 2;
        float towerCenterY = y + cellsize / 2;
        float monsterCenterY = (float) monster.getPosition()[0] * cellsize + topbar + cellsize/2;
        float monsterCenterX = (float) monster.getPosition()[1] * cellsize + cellsize/2;

        float deltaX = towerCenterX - monsterCenterX;
        float deltaY = towerCenterY - monsterCenterY;
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance*2 <= towerRange+(rangeLevel*cellsize)+32) {
            return true;
        }
        return false;
    }

    /**
     * Launches a fireball from the tower towards a target monster.
     * The fireball follows a trajectory towards the target's center.
     *
     * @param towerCenterX The x-coordinate of the tower's center.
     * @param towerCenterY The y-coordinate of the tower's center.
     * @param monsterCenterX The x-coordinate of the monster's center.
     * @param monsterCenterY The y-coordinate of the monster's center.
     */
    public void fireball(float towerCenterX, float towerCenterY, float monsterCenterX, float monsterCenterY) {
        float x_fireball = towerCenterX;
        float y_fireball = towerCenterY;
        float x_monster = monsterCenterX;
        float y_monster = monsterCenterY;
        float dx = x_monster - x_fireball;
        float dy = y_monster - y_fireball;
        float distance = parent.sqrt(dx * dx + dy * dy);

        float directionX = dx / distance;
        float directionY = dy / distance;

        while (distance > 0) {
            dx = x_monster - x_fireball;
            dy = y_monster - y_fireball;
            parent.image(fireball, x_fireball, y_fireball);
            x_fireball += directionX*1;
            y_fireball += directionY*1;
            distance -= 1;
        }
    }

    public int getTowerCost() {
        return towerCost;
    }
    
    public boolean isMouseOverTower() {
        return parent.mouseX >= x && parent.mouseX <= x + cellsize && parent.mouseY >= y && parent.mouseY <= y + cellsize;
    }


    /**
     * Displays the upgrade cost in the game menu based on the selected upgrade options.
     *
     * @param isUpgradingRange   True if upgrading the range, false otherwise.
     * @param isUpgradingSpeed   True if upgrading the firing speed, false otherwise.
     * @param isUpgradingDamage  True if upgrading the damage, false otherwise.
     * @return The calculated upgrade cost.
     */
    public int costmenu(boolean isUpgradingRange, boolean isUpgradingSpeed, boolean isUpgradingDamage) {
        parent.textSize(12);
        if (isUpgradingRange && !isUpgradingSpeed && !isUpgradingDamage && (rangeLevel<3)) {
            parent.fill(255);        // Set the fill color to white
            parent.stroke(0);        // Set the border color to black
            parent.rect(650, 580, 85, 60);

            parent.fill(0);          // Set the text color to black
            parent.text("Upgrade Cost", 653, 595);
            parent.text("range: "+(20+getRangeLevel()*10), 653, 615);

            parent.line(650, 600, 735, 600);
            parent.line(650, 620, 735, 620);
            parent.text("Total: "+(20+getRangeLevel()*10), 653, 635);

            return (20+getRangeLevel()*10);

        } else if (!isUpgradingRange && isUpgradingSpeed && !isUpgradingDamage && (speedLevel<3)) {
            parent.fill(255);        // Set the fill color to white
            parent.stroke(0);        // Set the border color to black
            parent.rect(650, 580, 85, 60);

            parent.fill(0);          // Set the text color to black
            parent.text("Upgrade Cost", 653, 595);
            parent.text("speed: "+(20+getSpeedLevel()*10), 653, 615);

            parent.line(650, 600, 735, 600);
            parent.line(650, 620, 735, 620);
            parent.text("Total: "+(20+getSpeedLevel()*10), 653, 635);

            return (20+getSpeedLevel()*10);

        } else if (!isUpgradingRange && !isUpgradingSpeed && isUpgradingDamage && (damageLevel<3)) {
            parent.fill(255);        // Set the fill color to white
            parent.stroke(0);        // Set the border color to black
            parent.rect(650, 580, 85, 60);

            parent.fill(0);          // Set the text color to black
            parent.text("Upgrade Cost", 653, 595);
            parent.text("damage: "+(20+getDamageLevel()*10), 653, 615);

            parent.line(650, 600, 735, 600);
            parent.line(650, 620, 735, 620);
            parent.text("Total: "+(20+getDamageLevel()*10), 653, 635);

            return (20+getDamageLevel()*10);
        } else if (isUpgradingRange && isUpgradingSpeed && !isUpgradingDamage && (rangeLevel<3 && speedLevel<3)) {
            parent.fill(255);        // Set the fill color to white
            parent.stroke(0);        // Set the border color to black
            parent.rect(650, 580, 85, 80);

            parent.fill(0);          // Set the text color to black
            parent.text("Upgrade Cost", 653, 595);
            parent.text("range: "+(20+getRangeLevel()*10), 653, 615);
            parent.text("speed: "+(20+getSpeedLevel()*10), 653, 635);

            parent.line(650, 600, 735, 600);
            parent.line(650, 640, 735, 640);
            parent.text("Total: "+((20+getRangeLevel()*10)+(20+getSpeedLevel()*10)), 653, 655);

            return ((20+getRangeLevel()*10)+(20+getSpeedLevel()*10));

        } else if (isUpgradingRange && !isUpgradingSpeed && isUpgradingDamage && (rangeLevel<3 && damageLevel<3)) {
            parent.fill(255);        // Set the fill color to white
            parent.stroke(0);        // Set the border color to black
            parent.rect(650, 580, 85, 80);

            parent.fill(0);          // Set the text color to black
            parent.text("Upgrade Cost", 653, 595);
            parent.text("range: "+(20+getRangeLevel()*10), 653, 615);
            parent.text("damage: "+(20+getDamageLevel()*10), 653, 635);

            parent.line(650, 600, 735, 600);
            parent.line(650, 640, 735, 640);
            parent.text("Total: "+((20+getRangeLevel()*10)+(20+getDamageLevel()*10)), 653, 655);

            return ((20+getRangeLevel()*10)+(20+getDamageLevel()*10));

        } else if (!isUpgradingRange && isUpgradingSpeed && isUpgradingDamage && (speedLevel<3 && damageLevel<3)) {
            parent.fill(255);        // Set the fill color to white
            parent.stroke(0);        // Set the border color to black
            parent.rect(650, 580, 85, 80);

            parent.fill(0);          // Set the text color to black
            parent.text("Upgrade Cost", 653, 595);
            parent.text("speed: "+(20+getSpeedLevel()*10), 653, 615);
            parent.text("damage: "+(20+getDamageLevel()*10), 653, 635);

            parent.line(650, 600, 735, 600);
            parent.line(650, 640, 735, 640);
            parent.text("Total: "+((20+getSpeedLevel()*10)+(20+getDamageLevel()*10)), 653, 655);
            
            return ((20+getSpeedLevel()*10)+(20+getDamageLevel()*10));

        } else if (isUpgradingRange && isUpgradingSpeed && isUpgradingDamage && (rangeLevel<3 && speedLevel<3 && damageLevel<3)) {
            parent.fill(255);        // Set the fill color to white
            parent.stroke(0);        // Set the border color to black
            parent.rect(650, 560, 85, 100);

            parent.fill(0);          // Set the text color to black
            parent.text("Upgrade Cost", 653, 575);
            parent.text("range: "+(20+getRangeLevel()*10), 653, 595);
            parent.text("speed: "+(20+getSpeedLevel()*10), 653, 615);
            parent.text("damage: "+(20+getDamageLevel()*10), 653, 635);
            

            parent.line(650, 580, 735, 580);
            parent.line(650, 640, 735, 640);
            parent.text("Total: "+((20+getRangeLevel()*10)+(20+getSpeedLevel()*10)+(20+getDamageLevel()*10)), 653, 655);

            return ((20+getRangeLevel()*10)+(20+getSpeedLevel()*10)+(20+getDamageLevel()*10));
        }

        return 0;
    }

    /**
     * Sets the tower's upgrade level based on the selected upgrade options.
     *
     * @param isUpgradingRange   True if upgrading the range, false otherwise.
     * @param isUpgradingSpeed   True if upgrading the firing speed, false otherwise.
     * @param isUpgradingDamage  True if upgrading the damage, false otherwise.
     */
    public void setLevel(boolean isUpgradingRange, boolean isUpgradingSpeed, boolean isUpgradingDamage) {
        if (isUpgradingRange && !isUpgradingSpeed && !isUpgradingDamage && (rangeLevel<3)) {
            setRangeLevel(rangeLevel+1);
        } else if (!isUpgradingRange && isUpgradingSpeed && !isUpgradingDamage && (speedLevel<3)) {
            setSpeedLevel(speedLevel+1);
        } else if (!isUpgradingRange && !isUpgradingSpeed && isUpgradingDamage && (damageLevel<3)) {
            setDamageLevel(damageLevel+1);
        } else if (isUpgradingRange && isUpgradingSpeed && !isUpgradingDamage && (rangeLevel<3 && speedLevel<3)) {
            setRangeLevel(rangeLevel+1);
            setSpeedLevel(speedLevel+1);
        } else if (isUpgradingRange && !isUpgradingSpeed && isUpgradingDamage && (rangeLevel<3 && damageLevel<3)) {
            setRangeLevel(rangeLevel+1);
            setDamageLevel(damageLevel+1);
        } else if (!isUpgradingRange && isUpgradingSpeed && isUpgradingDamage && (speedLevel<3 && damageLevel<3)) {
            setSpeedLevel(speedLevel+1);
            setDamageLevel(damageLevel+1);
        } else if (isUpgradingRange && isUpgradingSpeed && isUpgradingDamage && (rangeLevel<3 && speedLevel<3 && damageLevel<3)) {
            setRangeLevel(rangeLevel+1);
            setSpeedLevel(speedLevel+1);
            setDamageLevel(damageLevel+1);
        } 
    }

    public boolean isFastForwarding() {
        return isFastForwarding;
    }

    public void setFastForwarding(boolean isFastForwarding) {
        this.isFastForwarding = isFastForwarding;
    }
    
}


