import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Arrow extends Line2D {
    private Circle circle;
    private Point2D start;
    private Point2D end;


    private static final double v = 40;
    private double degree;
    private static final int width = 50;

    Arrow(Circle circle, double degree) {
        this.circle = circle;
        this.start = circle.getCenter();
        this.degree = degree;
        setEnd();
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }


    public void setEnd() {
        this.end = new Point2D.Double(this.getX1() + Math.cos(Math.toRadians(degree)) * width, this.getY1() - Math.sin(Math.toRadians(degree)) * width);
    }


    void drawArrow(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(this);
    }

    // During fly
    void fly(double duration) {
        double x_v = v * Math.cos(getRadians());
        double y_v = v * Math.sin(getRadians());

        double s_x = x_v * duration;
        double s_y = y_v * duration - 9.8 * Math.pow(duration, 2) / 2;
        System.out.println("s_x: " + s_x + ",s_y:" + s_y);
        // Find out degree and set end point
        if (s_y != 0) {
            double radians = Math.atan(s_y / s_x);
            if (getDegree() >= 90) {
                setDegree(180 + Math.toDegrees(radians));
            } else {
                setDegree(Math.toDegrees(radians));
            }
            refresh(s_x, s_y);
        }
    }

    static double toPixel(double value, int degree) {
        // 50 pixel / seconds
        return value / Math.pow(50, degree);
    }

    // Move with certain direction
    void refresh(double s_x, double s_y) {
        // Set first point
        setStart(getX1() + s_x, getY1() - s_y);
        setEnd();
    }

    // Move with ball
    void refresh() {
        setStart(this.circle.getCenter());
        setEnd();
    }


    @Override
    public String toString() {
        return "Arrow{" +
                ", start=" + start +
                ", end=" + end +
                ", degree=" + degree +
                '}';
    }

    public void setStart(Point2D start) {
        this.start = start;
    }

    public void setStart(double x, double y) {
        this.start = new Point2D.Double(x, y);
    }

    public double getRadians() {
        return Math.toRadians(this.degree);
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }


    @Override
    public double getX1() {
        return this.start.getX();
    }

    @Override
    public double getY1() {
        return this.start.getY();
    }

    @Override
    public Point2D getP1() {
        return this.start;
    }

    @Override
    public double getX2() {
        return this.end.getX();
    }

    @Override
    public double getY2() {
        return this.end.getY();
    }

    @Override
    public Point2D getP2() {
        return this.end;
    }

    public double getDegree() {
        return degree;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {
        this.start = new Point2D.Double(x1, y1);
        this.end = new Point2D.Double(x2, y2);
    }
}
