/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import java.awt.Polygon;

public class FNs {

    private FNs() {
    }

    public static void rotate(Polygon p, double angle) {
        // Step 1: Find center point
        int centerX = 0;
        int centerY = 0;
        for (int i = 0; i < p.npoints; i++) {
            centerX += p.xpoints[i];
            centerY += p.ypoints[i];
        }
        centerX /= p.npoints;
        centerY /= p.npoints;

        // Step 2: Translate to origin
        for (int i = 0; i < p.npoints; i++) {
            p.xpoints[i] -= centerX;
            p.ypoints[i] -= centerY;
        }

        // Step 3: Rotate around origin
        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);
        for (int i = 0; i < p.npoints; i++) {
            int x = p.xpoints[i];
            int y = p.ypoints[i];
            p.xpoints[i] = (int) (x * cosTheta - y * sinTheta);
            p.ypoints[i] = (int) (x * sinTheta + y * cosTheta);
        }

        // Step 4: Translate back to original position
        for (int i = 0; i < p.npoints; i++) {
            p.xpoints[i] += centerX;
            p.ypoints[i] += centerY;
        }
    }
}
