package com.shehel.chernobyl.entity;

import com.shehel.chernobyl.graphics.Sprite;

import java.util.ArrayList;

/**
 * Created by shehel on 8/9/2016.
 */
public abstract class Mob extends Entity {


    protected Sprite sprite;
    //North, East, West, South?
    protected int dir = 0;
    protected boolean moving = false;
    private final static int TILE_SIZE = 16;

    protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    public void move(int xa, int ya) {
        //if moving on two axes
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

        //xa and ya can take -1, 0, 1 based on the side moving to
        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    public void update() {}

    public void render() {}

    protected void shoot(int x, int y, double dir) {
        System.out.println("Angle" + Math.toDegrees(dir));
        Projectile p = new SpellProjectile(x, y, dir);
        projectiles.add(p);
        level.add(p);
    }

    private boolean collision(int xa, int ya) {
        /**Primitive and imprecise collision detection
         * Before: Look forward and check if the tile in front is solid()
         * Now: Corner System
         */
        boolean solid = false;
        for (int c =0; c < 4; c++) {
            // Get the corners
            System.out.println("x--"+x+"  y--"+y+" xc--"+(c % 2 * 12 - 7)+"  yc--"+(c / 2 * 2 - 1));
            int xt = ((x + xa) + c % 2 * 12 - 7) / 16;
            int yt = ((y + ya) + c / 2 * 2 - 1) / 16;
            if (level.getTile(xt, yt).solid()) solid = true;
        }
       // int xt = (x + xa) / TILE_SIZE;
       // int yt = (y + ya) / TILE_SIZE;
        return solid;
    }




}
