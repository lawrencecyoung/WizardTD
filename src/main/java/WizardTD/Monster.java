package WizardTD;

import java.util.List;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * The Monster class represents a monster in the game.
 */

public class Monster extends App {
    private double[] position;
    private double[] target;
    private List<double[]> path;
    private int step = 0;
    private PathFinding pathFinder;
    private MonsterConfig config;
    private float currentHP;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private boolean isDead;
    private boolean atHouse;
    private PApplet parentApp; // Added PApplet instance
    private double speed;
    private int[] startingPoint;
    private boolean exists;
    private boolean isFastForwarding;
    private boolean isFrozen;


    /**
     * Constructs a Monster object.
     *
     * @param parentApp The parent PApplet instance.
     * @param startingPoint The starting point of the monster.
     * @param target The target point of the monster.
     * @param pathFinder The pathfinder for finding the path.
     * @param config The configuration for the monster.
     */

    public Monster(PApplet parentApp, int[] startingPoint, int[] target, PathFinding pathFinder, MonsterConfig config){
        this.position = new double[]{(double) startingPoint[0], (double) startingPoint[1]};
        this.target = new double[]{(double) target[0], (double) target[1]};
        this.pathFinder = pathFinder;
        this.path = pathFinder.findPath(this.position, this.target);
        this.config = config;
        this.currentHP = config.getMaxHP();
        this.deathAnimationIndex = 0;
        this.deathAnimationDelay = 4;
        this.isDead = false;
        this.atHouse = false;
        this.parentApp = parentApp; // Store the PApplet instance
        this.speed = config.getSpeed();
        this.startingPoint = startingPoint;
        this.exists = true;
        this.isFastForwarding = false;
        this.isFrozen = false;
    }


    /**
     * Updates the monster's position and state.
     */

    public void update() {
        if (isDead) {
            playDeathAnimation();
        } else if (step < path.size()) {
            double[] nextStep = path.get(step);
            double nextX = nextStep[0];
            double nextY = nextStep[1];
            double stepSize = (speed / 60);

            if (isFastForwarding) {
                stepSize = stepSize*2;
            }

            if (isFrozen) {
                stepSize = stepSize/2;
            }

            double deltaX = nextX - position[0];
            double deltaY = nextY - position[1];
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            double directionX = deltaX / distance;
            double directionY = deltaY / distance;

            if (distance > stepSize) {
                position[0] += stepSize * directionX;
                position[1] += stepSize * directionY;
            } else {
                position[0] = nextX;
                position[1] = nextY;
                step++;
            }

            if (step == path.size()) {
                atHouse = true;
            }
        }
    }

    /**
     * Draws the monster on the screen.
     */
    public void draw() {
        if (atHouse) {
            movetoStart();
        } else if (!isDead) {
            PImage sprite = config.getSpriteImage();
            parentApp.image(sprite, (float) (position[1] * App.CELLSIZE + App.CELLSIZE / 2 - 10), (float) (position[0] * App.CELLSIZE + App.CELLSIZE / 2 + App.TOPBAR - 10));
            drawHealthBar();
            if (isFrozen) {
                parentApp.fill(0,0,255);
                parentApp.ellipse((float) (position[1] * App.CELLSIZE + App.CELLSIZE / 2), (float) (position[0] * App.CELLSIZE + App.TOPBAR + App.CELLSIZE/2), 10, 10);
            }
        }
    }

    /**
     * Draws the health bar for the monster.
     */
    private void drawHealthBar() {
        double healthBarWidth = (currentHP / (double) config.getMaxHP()) * config.getSpriteImage().width;
        parentApp.stroke(0);
        parentApp.strokeWeight(1);
        parentApp.fill(255, 0, 0);
        parentApp.rect((float) (position[1] * App.CELLSIZE + App.CELLSIZE / 2 - config.getSpriteImage().width / 2), (float) (position[0] * App.CELLSIZE + App.CELLSIZE / 2 + App.TOPBAR - 20), (float) config.getSpriteImage().width, 5);
        parentApp.fill(0, 255, 0);
        parentApp.rect((float) (position[1] * App.CELLSIZE + App.CELLSIZE / 2 - config.getSpriteImage().width / 2), (float) (position[0] * App.CELLSIZE + App.CELLSIZE / 2 + App.TOPBAR - 20), (float) healthBarWidth, 5);
    }

    /**
     * Plays the death animation of the monster.
     */
    private void playDeathAnimation() {
        if (deathAnimationIndex < config.getDeathAnimationFrames().size()) {
            PImage frame = config.getDeathAnimationFrames().get(deathAnimationIndex);
            parentApp.image(frame, (float) (position[1] * App.CELLSIZE + App.CELLSIZE / 2 - 10), (float) (position[0] * App.CELLSIZE + App.CELLSIZE / 2 + App.TOPBAR - 10));

            if (parentApp.frameCount % deathAnimationDelay == 0) {
                deathAnimationIndex++;
            }
        }
    }


    /**
     * Inflicts damage on the monster.
     *
     * @param damage The amount of damage to inflict.
     */
    public void receiveDamage(float damage) {
        double effectiveDamage = damage * (1.0 - config.getArmor());
        currentHP = (float) (currentHP - effectiveDamage);
        if (currentHP <= 0) {
            currentHP = 0;
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isAtHouse() {
        return atHouse;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double[] getPosition() {
        return position;
    }

    public float getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(float currentHP) {
        this.currentHP = currentHP;
    }

    /**
     * Moves the monster back to its starting point.
     */
    public void movetoStart(){
        position = new double[]{(double) startingPoint[0], (double) startingPoint[1]};
    }

    public MonsterConfig getConfig() {
        return config;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean isFastForwarding() {
        return isFastForwarding;
    }

    public void setFastForwarding(boolean isFastForwarding) {
        this.isFastForwarding = isFastForwarding;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

}
