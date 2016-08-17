package com.shehel.chernobyl.level.tile;

import com.shehel.chernobyl.graphics.Screen;
import com.shehel.chernobyl.graphics.Sprite;

/**
 * Created by shehel on 8/2/2016.
 */
public class Tile {

    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile voidTile = new voidTile(Sprite.voidSprite);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile flower = new FlowerTile(Sprite.flower);

    public Tile (Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solid() {
        return false;
    }
}
