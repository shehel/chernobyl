package com.shehel.chernobyl.graphics;

import com.shehel.chernobyl.entity.mob.Player;
import com.shehel.chernobyl.level.tile.Tile;
import com.sun.prism.paint.Color;

import java.util.Random;

/**
 * Displays pixels with specified colors
 * Created by shehel on 7/25/2016.
 */
public class  Screen {
    public int width, height;
    public int[] pixels;
    public final int mapSize = 64;
    public final int mapSizeMask = mapSize - 1;

    public int xOffset, yOffset;

    public int[] tiles = new int[mapSize * mapSize];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }


    //ya = absolute position
    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            if (ya < -tile.sprite.SIZE || ya >= height) break;
            if (ya < 0) ya = 0;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderPlayer(int xp, int yp, Sprite sprite, boolean flip) {
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < 32; y++) {
            int ya = y + yp;
            if (ya < -32 || ya >= height) break;
            if (ya < 0) ya = 0;
            for (int x = 0; x < 32; x++) {
                int xa = x + xp;
                int xs = 31 - x;
                if (xa < -32 || xa >= width) break;
                if (xa < 0) xa = 0;
                int col;
                if (flip) {
                    col = sprite.pixels[xs + y * 32];
                } else {
                    col = sprite.pixels[x + y * 32];
                }

                if (col != 0xffff00ff) pixels[xa + ya * width] = col;
            }
        }

    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


}

/**
 * Entity: Something to 
 **/