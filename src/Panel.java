import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/**
 * This program demonstrates how to draw lines using Graphics2D object.
 *
 * @author www.codejava.net
 */
class Panel extends JPanel implements KeyListener, Runnable {
    Circle circle1 = new Circle(Color.BLACK, 20, 400);
    Circle circle2 = new Circle(Color.BLUE, 300, 400);
    Arrow arrow1 = new Arrow(circle1, 45);
    Arrow arrow2 = new Arrow(circle2, 135);
    Rectangle2D health1 = new Rectangle2D.Double(0,0, 100, 20);
    Rectangle2D health2 = new Rectangle2D.Double(0, 40, 100, 20);

    boolean a = false;
    boolean d = false;
    boolean w = false;
    boolean s = false;
    boolean k = false;
    boolean i = false;
    boolean l = false;
    boolean j = false;
    boolean fired = false;
    boolean fired1 = false;
    long initialTime;
    long initialTime1;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        circle1.drawBall(g);
        circle2.drawBall(g);
        arrow1.drawArrow(g);
        arrow2.drawArrow(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fill(health1);
        g2d.setColor(Color.BLUE);
        g2d.fill(health2);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'e':
                if (!fired) {
                    initialTime = System.currentTimeMillis();
                }
                fired = true;
            case 'o':
                if (!fired) {
                    initialTime1 = System.currentTimeMillis();
                }
                fired1 = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                a = true;
                break;
            case 'd':
                d = true;
                break;
//            case 'e':
//                fired = true;
//                System.out.println("shshshs");
//                arrow1.setTime(System.currentTimeMillis());
//                break;
            case 'l':
                l = true;
                break;
            case 'j':
                j = true;
                break;
            case 'w':
                w = true;
                break;
            case 's':
                s = true;
                break;
            case 'i':
                i = true;
                break;
            case 'k':
                k = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                a = false;
                break;
            case 'd':
                d = false;
                break;
            case 'l':
                l = false;
                break;
            case 'j':
                j = false;
                break;
            case 'w':
                w = false;
                break;
            case 's':
                s = false;
                break;
            case 'i':
                i = false;
                break;
            case 'k':
                k = false;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (health1.getWidth() <= 0 || health2.getWidth() <= 0) {
                System.out.println("Game Over!");
                break;
            }
            int move = 10;
            try {
                if (a) {
                    if (circle1.getBallX() - move >= 0) {
                        circle1.moveBall(circle1.getBallX() - move);
                    }
                } else if (d) {
                    if (circle1.getBallX() + move <= 900) {
                        circle1.moveBall(circle1.getBallX() + move);
                    }
                }
                if (j) {
                    if (circle2.getBallX() - move >= 0) {
                        circle2.moveBall(circle2.getBallX() - move);
                    }
                } else if (l) {
                    if (circle2.getBallX() + move <= 900) {
                        circle2.moveBall(circle2.getBallX() + move);
                    }
                }

                if (w) {
                    if (arrow1.getDegree() + 1 <= 180) {
                        arrow1.setDegree(arrow1.getDegree() + 1);
                    }

                } else if(s) {
                    if (arrow1.getDegree() - 1 >= 0) {
                        arrow1.setDegree(arrow1.getDegree() - 1);
                    }
                } else if (i) {
                    if (arrow2.getDegree() + 1 <= 180) {
                        arrow2.setDegree(arrow2.getDegree() - 1);
                    }
                } else if (k) {
                    if (arrow2.getDegree() + 1 >= 0) {
                        arrow2.setDegree(arrow2.getDegree() + 1);
                    }
                }

                if (fired) {
                    // Will not follow circle
                    double duration = (System.currentTimeMillis() - initialTime) / 1000.0;
                    System.out.println("Duration: "+ duration);
                    arrow1.fly(duration);
                    if (arrow1.getX2() > 920 || arrow1.getX2() < 0 || arrow1.getY2() > 420 || arrow1.getY2() < 0) {
                        fired = false;
                        arrow1.setDegree(45);
                        arrow1.refresh();
                    }
                    if (circle2.contains(arrow1.getP2())) {
                        health2.setRect(health2.getX(), health2.getY(), health2.getWidth() - 20, health2.getHeight());
                        fired = false;
                        arrow1.setDegree(45);
                        arrow1.refresh();
                    }

                } else {
                    arrow1.refresh();
                }

                if (fired1) {
                    // Will not follow circle
                    double duration = (System.currentTimeMillis() - initialTime1) / 1000.0;
                    System.out.println("Duration: "+ duration);
                    arrow2.fly(duration);
                    if (arrow2.getX2() > 920 || arrow2.getX2() < 0 || arrow2.getY2() > 420 || arrow2.getY2() < 0) {
                        fired1 = false;
                        arrow2.setDegree(135);
                        arrow2.refresh();
                    }

                    // If hit
                    if (circle1.contains(arrow2.getP2())) {
                        System.out.println("Circle 1 kena hit");
                        health1.setRect(health1.getX(), health1.getY(), health1.getWidth() - 20, health1.getHeight());
                        fired1 = false;
                        arrow2.setDegree(135);
                        arrow2.refresh();
                    }

                } else {
                    arrow2.refresh();
                }
                repaint();
                Thread.sleep(10);
            } catch (Exception exc) {
                exc.printStackTrace();
                break;
            }
        }
    }
}