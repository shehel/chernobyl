package com.shehel.chernobyl.level;

import com.shehel.chernobyl.graphics.Screen;
import com.shehel.chernobyl.level.tile.Tile;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by shehel on 8/2/2016.
 */
public class Level {

    protected int width, height;
    protected int[] tilesInt;
    //old method
    //protected Tile[] tiles;

    protected int[] tiles;
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateRandomLevel();
    }

    public Level(String path) {

        loadLevel(path);
        generateLevel();

    }

    protected void loadLevel(String path) {

    }

    public void update() {

    }

    protected void generateLevel() {

    }

    private void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {

        screen.setOffset(xScroll, yScroll);
        /** >>4 = /16 converting to pixels
         * The below 4 variables makes the render region
         * called corner pins
         **/
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;

        int y0 = (yScroll) >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        // x and y are the tile position because
        // we are using tile precision for corner pins
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
                //void tile if out of bounds
                //old method
                /*if (x + y * 16 < 0 || x + y * 16 >= 256) {
                    Tile.voidTile.render(x, y, screen);
                    continue;
                }*/
                //tiles[x + y * 16].render(x, y, screen);
            }
        }
    }

   public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            //System.out.println("x  " + x + "   y    " + y);
            return Tile.voidTile;
        }
        if (tiles[x + y * width] == 0xFF00FF00) {
            return Tile.grass;
        }
        if (tiles[x + y * width] == 0xFFFFFF00) {
            return Tile.flower;
        }
        if (tiles[x + y * width] == 0xFF7F7F00) {
            return Tile.rock;
        }
        return Tile.rock;
    }

    protected void generateRandomLevel() {

    }
}
