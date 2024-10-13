package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    private static final double
        WIGGLINESS = 0.2,
        WANDER_FROM_CENTER = 60000;

    private CanvasWindow canvas;
    private Random rand = new Random();
    private List<Cell> cells;
    

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            
            for (Cell cell : cells) {
                cell.moveAround(canvasCenter);
                cell.grow(0.02);
            }
            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCells() {
        cells = new ArrayList<>();
        for (int i = 0; i < 200; i++){
            Cell newCell = new Cell();
            double size = rand.nextInt(5) + 2;
            newCell.Cell(rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            cells.add(newCell);
            canvas.add(newCell.getShape());
        }

        
    }

    private static double sqr(double x) {
        return x * x;
    }

}
