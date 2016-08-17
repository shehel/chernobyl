package com.shehel.chernobyl.entity;

import com.shehel.chernobyl.graphics.Screen;
import com.shehel.chernobyl.level.Level;

import java.util.Random;

/**
 * Created by shehel on 8/8/2016.
 */
public abstract class Entity {
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update() {

    }

    public void render(Screen screen) {

    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level) {
        this.level = level;
    }
}
