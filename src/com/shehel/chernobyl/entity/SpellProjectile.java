package com.shehel.chernobyl.entity;

import com.shehel.chernobyl.graphics.Screen;
import com.shehel.chernobyl.graphics.Sprite;
import com.shehel.chernobyl.level.tile.Tile;

/**
 * Vector Maths
 * Has angle and length
 * Pixel percision with zizag movement
 * Use cos and sin to get the values to move each tic
 * speed amplifies the rate at which it moves
 * Created by shehe on 8/18/2016.
 */
public class SpellProjectile extends Projectile {

    public SpellProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = 200;
        speed = 4;
        damage = 20;
        rateOfFire = 15;
        sprite = Sprite.flower;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
        this.x = x;
        this.y = y;
    }

    public void update() {
        move();
    }

    protected void move() {
        x += nx;
        y += ny;
    }

    public void render(Screen screen) {
        screen.renderTile(x, y, sprite);
    }
}