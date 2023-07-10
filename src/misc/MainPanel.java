/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseMotionListener {

    private final Boid[] boidsArr;
    private ArrayList<Obstruction> obstructionsArr;

    public MainPanel(Boid[] bA, int w, int h) {
        this.boidsArr = bA;
        obstructionsArr = new ArrayList<>();
        this.setSize(new Dimension(w, h));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        addMouseMotionListener(this);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
//        if(e.getX()%10==0 && e.getY()%10==0)
            obstructionsArr.add(new Obstruction(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    
    //DRAW BOIDS IN HERE
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;

        //FOR ALL BOIDS DRAW THEM
        for (Boid b : this.boidsArr) {
            
            b.update(boidsArr, obstructionsArr);

            int x = (int) b.getPosition().getX();
            int y = (int) b.getPosition().getY();
            

            int h = b.getHeight();
            int bs = b.getBaseLength();

            int[] xPoints = {x, x - (int) (0.5 * bs), x + (int) (0.5 * bs)};
            int[] yPoints = {y, y + h, y + h};
            
            double sx = 0.5 * b.getVelocity().getX();
            double sy = 0.5 * b.getVelocity().getY();

            double theta =  Math.PI - Math.atan2(sx, sy);

            Polygon shape = new Polygon(xPoints, yPoints, 3);

            FNs.rotate(shape, theta);
//            g1.setColor(new Color((int)(Math.random() * 0x1000000 )));
            g1.setColor(Color.LIGHT_GRAY);
            g1.draw(shape);
            
        }
                    
        //DRAW OBSTRUCTIONS           
        for(Obstruction b : obstructionsArr){
            int x = (int) b.getPos().getX();
            int y = (int) b.getPos().getY();
            int s = b.getSize();
           
            g1.setColor(Color.DARK_GRAY);
            g1.fillOval(x, y, s, s);
        }
    }
}
