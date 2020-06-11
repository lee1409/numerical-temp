import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class Circle extends Ellipse2D {
    private double ballX;
    private double ballY;
    private Point2D center;
    private double ballWidth = 20;
    private double ballHeight = 20;
    private Color color;

    Circle(Color color, int ballX, int ballY) {
        this.color = color;
        this.ballX = ballX;
        this.ballY = ballY;
        this.center = new Point2D.Double(ballX + 10, ballY + 10);
    }

    void drawBall(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.fill(this);
    }

    void setCenter () {
        this.center = new Point2D.Double(getBallX() + 10, getBallY() + 10);
    }


    public double getBallX() {
        return ballX;
    }

    public void setBallX(double ballX) {
        this.ballX = ballX;
    }

    void moveBall(double ballX) {
        setBallX(ballX);
        setCenter();
    }

    public double getBallY() {
        return ballY;
    }

    public void setBallY(double ballY) {
        this.ballY = ballY;
    }

    public double getBallWidth() {
        return ballWidth;
    }

    public void setBallWidth(double ballWidth) {
        this.ballWidth = ballWidth;
    }

    public double getBallHeight() {
        return ballHeight;
    }

    public void setBallHeight(double ballHeight) {
        this.ballHeight = ballHeight;
    }

    public Point2D getCenter() {
        return center;
    }

    @Override
    public double getX() {
        return this.ballX;
    }

    @Override
    public double getY() {
        return this.ballY;
    }

    @Override
    public double getWidth() {
        return this.ballWidth;
    }

    @Override
    public double getHeight() {
        return this.ballHeight;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        setBallX(x);
        setBallY(y);
        setBallWidth(w);
        setBallHeight(h);
    }

    @Override
    public Rectangle2D getBounds2D() {

        return new Rectangle2D.Double(getWidth(), getHeight(), getBallX(), getBallY());
    }
}