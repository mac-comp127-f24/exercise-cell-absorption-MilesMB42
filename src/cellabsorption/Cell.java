package cellabsorption;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.Random;

public class Cell {
    private static final double
        WIGGLINESS = 0.2,
        WANDER_FROM_CENTER = 60000;

    private CanvasWindow canvas;
    private Ellipse cellShape;
    private double radius;
    private double direction;

    public void Cell(double x, double y, double radius, Color color) {
        cellShape = new Ellipse(x, y, radius * 2, radius * 2);
        cellShape.setFillColor(color);
        this.radius = radius;
        direction = normalizeRadians(Math.random() * Math.PI * 2);
    }

    public void moveAround(Point centerOfGravity) {
        cellShape.moveBy(Math.cos(direction), Math.sin(direction));

        double distToCenter = cellShape.getCenter().distance(centerOfGravity);
        double angleToCenter = centerOfGravity.subtract(cellShape.getCenter()).angle();
        double turnTowardCenter = normalizeRadians(angleToCenter - direction);

        direction = normalizeRadians(
            direction
                + (Math.random() - 0.5) * WIGGLINESS
                + turnTowardCenter * Math.tanh(distToCenter / WANDER_FROM_CENTER));
    }

    public Ellipse getShape(){
        return cellShape;
    }

    public void grow(double amount) {
        setRadius(radius + amount);
    }

    private void setRadius(double newRadius) {
        if (newRadius < 0) {
            newRadius = 0;
        }
        radius = newRadius;
        Point previousCenter = cellShape.getCenter();
        cellShape.setSize(new Point(newRadius * 2, newRadius * 2));
        cellShape.setCenter(previousCenter);
    }

    private static double normalizeRadians(double theta) {
        double pi2 = Math.PI * 2;
        return ((theta + Math.PI) % pi2 + pi2) % pi2 - Math.PI;
    }
}
