import java.awt.geom.Point2D;

public class Point {

    private Point2D.Double location;
    private double toX;
    private double toY;

    public Point(Point2D.Double location, double toX, double toY) {
        this.location = location;
        this.toX = toX;
        this.toY = toY;
    }

    public Point2D.Double getLocation() {
        return location;
    }

    public double getToX() {
        return toX;
    }

    public double getToY() {
        return toY;
    }

    public void setLocation(Point2D.Double location) {
        this.location = location;
    }

    public void setToX(double toX) {
        this.toX = toX;
    }

    public void setToY(double toY) {
        this.toY = toY;
    }
}
