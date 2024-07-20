import org.junit.jupiter.api.Test;
import WizardTD.Monster; // Import the Monster class from your game code
import WizardTD.Tower;  // Import the Tower class from your game code
import WizardTD.MonsterConfig;  // Import the MonsterConfig class from your game code
import WizardTD.App;  // Import the MonsterConfig class from your game code
import WizardTD.PathFinding;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathFindingTest {
    @Test
    public void testFindPath_NoObstacles() {
        int[][] grid = {
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };

        PathFinding pathFinding = new PathFinding(null, grid);
        List<double[]> path = pathFinding.findPath(new double[]{0, 0}, new double[]{2, 2});
        assertEquals(5, path.size());
    }

    @Test
    public void testFindPath_WithObstacles() {
        int[][] grid = {
            {1, 1, 0, 0},
            {0, 1, 1, 1},
            {0, 1, 0, 1},
            {0, 1, 1, 1}
        };
        PathFinding pathFinding = new PathFinding(null, grid);
        List<double[]> path = pathFinding.findPath(new double[]{0, 0}, new double[]{3, 3});
        assertEquals(7, path.size());
    }

    @Test
    public void testFindPath_NoValidPath() {
        // Create a grid with no valid path
        int[][] grid = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0}
        };

        // Create a PathFinding object
        PathFinding pathFinding = new PathFinding(null, grid);

        // Test finding a path from (0, 0) to (2, 2)
        List<double[]> path = pathFinding.findPath(new double[]{0, 0}, new double[]{2, 2});

        // There should be no valid path, so the result should be an empty list.
        assertTrue(path.isEmpty());
    }
}
