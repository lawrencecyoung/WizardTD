package WizardTD;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import processing.core.PApplet;

/**
 * The PathFinding class provides a pathfinding algorithm for finding a path from start to end on a grid.
 */
public class PathFinding extends App {
    private PApplet parentApp;
    private int[][] grid;
    private int numRows;
    private int numCols;
    private boolean[][] visited;
    private int[][] parent;

    App appInstance = new App();
    double cellsize = App.CELLSIZE;

    /**
     * Constructs a PathFinding object.
     *
     * @param parentApp The PApplet instance for drawing.
     * @param grid      The grid representing the game map.
     */
    public PathFinding(PApplet parentApp, int[][] grid) {
        this.parentApp = parentApp;
        this.grid = grid;
        this.numRows = grid.length;
        this.numCols = grid[0].length;
        this.visited = new boolean[numRows][numCols];
        this.parent = new int[numRows][numCols];
    }

    /**
     * Finds a path from the start point to the end point on the grid.
     *
     * @param start The start point as an array of two double values.
     * @param end   The end point as an array of two double values.
     * @return A list of points representing the path from start to end.
     */
    public List<double[]> findPath(double[] start, double[] end) {
        int startX = (int) start[0];
        int startY = (int) start[1];
        int endX = (int) end[0];
        int endY = (int) end[1];
    
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        parent[startX][startY] = -1;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (current[0] == endX && current[1] == endY) {
                List<double[]> path = reconstructPath(start, end);
                return path;
            }

            for (int[] direction : directions) {
                int nextX = current[0] + direction[0];
                int nextY = current[1] + direction[1];

                if (isValid(nextX, nextY) && !visited[nextX][nextY]) {
                    if (grid[nextX][nextY] == 1 || grid[nextX][nextY] == 2) { // Consider both 1 and 2 as valid paths
                        queue.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        parent[nextX][nextY] = current[0] * numCols + current[1];
                    }
                }
            }
        }
        return new ArrayList<>();
    }
    
    /**
     * Checks if a position is valid within the grid.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @return True if the position is valid; false otherwise.
     */
    private boolean isValid(int x, int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols;
    }

    /**
     * Reconstructs the path from the start to the end point.
     *
     * @param start The start point as an array of two double values.
     * @param end   The end point as an array of two double values.
     * @return A list of points representing the reconstructed path.
     */
    private List<double[]> reconstructPath(double[] start, double[] end) {
        List<double[]> path = new ArrayList<>();
        int currentX = (int) end[0];
        int currentY = (int) end[1];
    
        if (currentX == -1 || currentY == -1) {
            return path;
        }
    
        if (currentX < 0 || currentX >= numRows || currentY < 0 || currentY >= numCols) {
            return path;
        }
    
        while (currentX != -1) {
            path.add(0, new double[]{(double) currentX, (double) currentY});
            int parentIndex = parent[currentX][currentY];
            if (parentIndex == -1) {
                break;
            }
            currentX = parentIndex / numCols;
            currentY = parentIndex % numCols;
        }
    
        return path;
    }
}
