package com.shehel.chernobyl.entity;

import com.shehel.chernobyl.graphics.Sprite;

/**
 * Created by shehe on 8/18/2016.
 */
public class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, rateOfFire, range, damage;

    public Projectile(int x, int y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
    }
}
