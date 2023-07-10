/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

public final class Vector {

    private double x;
    private double y;
    private double mag;

    public Vector() {
        this.x = 0;
        this.y = 0;
        this.mag = 0;
    }
    
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
        setMagnitude(this.x, this.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setMagnitude(double x, double y) {
        this.mag = Math.pow((x * x + y * y), 0.5);
    }

    public double getMagnitude() {
        return this.mag;
    }

    public void normalize() {
        double m = this.mag;
        if (m <= 0.0001) {
            m = 1; // m = Tolerance Variable
        }
        this.x /= m;
        this.y /= m;
        this.setMagnitude(this.x, this.y);
    }

    public Vector add(Vector m) {
        this.x += m.x;
        this.y += m.y;
        this.setMagnitude(this.x, this.y);
        return this;
    }

    public Vector sub(Vector m) {
        this.x -= m.x;
        this.y -= m.y;
        this.setMagnitude(this.x, this.y);
        return this;
    }

    public Vector scalarMult(int m) {
        this.x *= m;
        this.y *= m;
        this.setMagnitude(this.x, this.y);
        return this;
    }

    public Vector scalarDiv(double m) {
        this.x /= m;
        this.y /= m;
        this.setMagnitude(this.x, this.y);
        return this;
    }
    
    public void zero(){
        this.x = 0;
        this.y = 0;
        this.setMagnitude(this.x, this.y);
    }
    
    public void lim(double lim){        
        if (this.mag != 0 && this.mag > lim) {
            x *= lim / mag;
            y *= lim / mag;
        }
    }
}
