package com.shehel.chernobyl.level.tile;

import com.shehel.chernobyl.graphics.Screen;
import com.shehel.chernobyl.graphics.Sprite;

/**
 * Created by shehel on 8/3/2016.
 */
public class GrassTile extends Tile{

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {

        screen.renderTile(x << 4, y << 4, this);
    }
    public boolean solid() {
        return false;
    }
}
