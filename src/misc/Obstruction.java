/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

public class Obstruction {
    
    private Vector pos;
    private final short size = 20;
    
    public Obstruction(int x, int y){
        this.pos = new Vector(x, y);
    }

    public Vector getPos() {
        return pos;
    }
    
    public int getSize() {
        return size;
    }
    
}
