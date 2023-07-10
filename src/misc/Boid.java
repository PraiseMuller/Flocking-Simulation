/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import java.util.ArrayList;
import java.util.Random;

public class Boid {

    //constants
    private final int limX;
    private final int limY;
    private final int baseLength = 10; //Boid Base Legth
    private final int height = 25;     //Boid Height

    //movement variables
    private final Vector position;
    private final Vector acceleration;
    private final Vector velocity;

     //forces and such
    private final double maxAlignForce = 1;
    private final double maxCohesionForce = 0.1;
    private final double maxSeperationForce = 0.3;
    private final int minVel = -5;
    private final int maxVel = 5;
    private final int perceptionRadius = 50;

    //utils
    private final Random r = new Random();

    public Boid(int lx, int ly) {
        this.limX = lx;
        this.limY = ly;

        int x1 = r.nextInt(2 * this.baseLength, this.limX - this.baseLength);
        int y1 = r.nextInt(1, this.limY - this.height);

        this.position = new Vector(x1, y1);
        this.acceleration = new Vector(0, 0);
        this.velocity = new Vector(r.nextInt(minVel, maxVel), r.nextInt(minVel, maxVel));
    }

    void update(Boid[] boids, ArrayList<Obstruction> obs) {

        //GENERAL MOVEMENT
        this.acceleration.add(align(boids));
        this.acceleration.add(cohesion(boids));
        this.acceleration.add(seperation(boids));
        this.acceleration.add(obstruct(obs));
        this.velocity.add(this.acceleration);
        this.position.add(this.velocity);


        //Edges
        if (this.position.getX() + 0.5 * this.baseLength < 0) {
            this.position.setX(this.limX);
//                this.velocity.scalarMult(-1);
        }
        if (this.position.getX() > this.limX + 0.5 * this.baseLength) {
            this.position.setX(0);
//            this.velocity.scalarMult(-1);
        }

        if (this.position.getY() < -this.height) {
            this.position.setY(this.limY);
//                this.velocity.scalarMult(-1);
        }
        if (this.position.getY() > this.limY + this.height) {
            this.position.setY(-this.height);
//                this.velocity.scalarMult(-1);
        }
        
        acceleration.zero();
    }

    //ALIGNMENT
    private Vector align(Boid[] boids) {

        Vector steering = new Vector();
        int total = 0;

        for (Boid b : boids) {
            if (b != this) {
                double d = dist(b.getPosition(), this.getPosition());
                if (d <= this.perceptionRadius) {
                    steering.add(b.velocity);
                    total++;
                }
            }
        }

        if (total > 0) {
            steering.scalarDiv(total);
            steering.normalize();
            steering.scalarMult(maxVel);
            steering.sub(this.velocity);
            steering.lim(this.maxAlignForce);
        }
        return steering;
    }

    //COHESION - Place self at avg position between flockmates
    private Vector cohesion(Boid[] boids) {

        Vector steering = new Vector();
        int total = 0;

        for (Boid b : boids) {
            if (b != this) {
                if (dist(b.getPosition(), this.getPosition()) <= this.perceptionRadius) {
                    steering.add(b.position);
                    total++;
                }
            }
        }

        if (total > 0) {
            steering.scalarDiv(total);
            steering.sub(this.position);
            steering.sub(this.velocity);
            steering.normalize();
            steering.scalarMult(this.maxVel);
            steering.lim(this.maxCohesionForce);
        }
        return steering;
    }

    //SEPERATION - Steer to avoid local Flockmates
    private Vector seperation(Boid[] boids) {

        Vector steering = new Vector();
        int total = 0;

        for (Boid b : boids) {
            if (b != this) {
                double d = dist(b.getPosition(), this.getPosition());
                if (d <= this.perceptionRadius) {
                    Vector diff = sub2Vectors(this.position, b.position);
                    diff.normalize();
                    diff.scalarDiv(d);
                    steering.add(diff);
                    total++;
                }
            }
        }

        if (total > 0) {
            steering.scalarDiv(total);
        }

        if (steering.getMagnitude() > 0) {
            steering.normalize();
            steering.scalarMult(this.maxVel);
            steering.sub(this.velocity);
            steering.lim(this.maxSeperationForce);
        }
        return steering;
    }

    //Obstruction - Steer to avoid obstruction
    private Vector obstruct(ArrayList<Obstruction> obstr) {

        Vector steering = new Vector();
        int total = 0;

        if (!obstr.isEmpty()) {
            for (Obstruction obs : obstr) {
                double d = dist(obs.getPos(), this.getPosition());
                if (d <= obs.getSize() + 15) {
                    Vector diff = sub2Vectors(this.position, obs.getPos());
                    diff.normalize();
                    diff.scalarDiv(d);
                    steering.add(diff);
                    total++;
                }
            }

            if (total > 0) {
                steering.scalarDiv(total);
            }

            if (steering.getMagnitude() > 0) {
                steering.normalize();
                steering.scalarMult(this.maxVel);
                steering.sub(this.velocity);
                steering.lim(this.maxSeperationForce * 3.5);
            }
        }
        return steering;
    }

    public double dist(Vector v1, Vector v2) {
        double a = v1.getX() - v2.getX();
        double b = v1.getY() - v2.getY();
        return Math.pow((a * a + b * b), 0.5);
    }

    public Vector sub2Vectors(Vector v1, Vector v2) {
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public int getBaseLength() {
        return this.baseLength;
    }

    public int getHeight() {
        return this.height;
    }
}
