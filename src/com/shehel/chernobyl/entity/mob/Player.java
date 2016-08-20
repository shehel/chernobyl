package com.shehel.chernobyl.entity.mob;

import com.shehel.chernobyl.Game;
import com.shehel.chernobyl.entity.Mob;
import com.shehel.chernobyl.entity.Projectile;
import com.shehel.chernobyl.graphics.Screen;
import com.shehel.chernobyl.graphics.Sprite;
import com.shehel.chernobyl.input.Keyboard;
import com.shehel.chernobyl.input.Mouse;

/**
 * Created by shehel on 8/9/2016.
 */
public class Player extends Mob{

    private Keyboard input;
    private Sprite sprite;
    private boolean flip = false;
    private int anim = 0;
    private boolean walking = false;

    public Player(Keyboard input) {
        this.input = input;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_forward;
    }

    public void update() {
        int xa = 0, ya = 0;
        //counter to shift between animation
        if (anim < 7500) anim++;
        else anim = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;


        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        clear();
        updateShooting();

    }

    private void clear() {
        for (int i = 0; i < level.projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            if (p.isRemoved()) projectiles.remove(i);
        }
    }
    private void updateShooting() {

        if (Mouse.getButton() == 1) {
            //using players co-ordinate doesn't work because we need his co-ordinate
            //on the window and not the map because thats what mouse uses
            double dx = (Mouse.getX() - Game.getWindowWidth() / 2);
            double dy = (Mouse.getY() - Game.getWindowHeight() / 2);
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
        }
    }

    public void render(Screen screen) {
        //int xx = x - 16;
        //int yy = y -16;
        flip = false;
        if (dir == 0)  {
            sprite = Sprite.player_forward;
            if(walking) {
                sprite = Sprite.player_forward_1;
                if (anim % 20 > 10) {
                    sprite = Sprite.player_forward_2;
                } else {
                    sprite = Sprite.player_forward_3;
                }
            }
        }
        if (dir == 2) {
            sprite = Sprite.player_back;
            if(walking) {
                sprite = Sprite.player_back_1;
                if (anim % 20 > 10) {
                    sprite = Sprite.player_back_2;
                } else {
                    sprite = Sprite.player_back_3;
                }
            }
        }
        if (dir == 1) {
            sprite = Sprite.player_side;
            if(walking) {
                sprite = Sprite.player_side_1;
                if (anim % 20 > 10) {
                    sprite = Sprite.player_side_2;
                } else {
                    sprite = Sprite.player_side_3;
                }
            }        }
        if (dir == 3) {
            sprite = Sprite.player_side;
            if(walking) {
                sprite = Sprite.player_side_1;
                if (anim % 20 > 10) {
                    sprite = Sprite.player_side_2;
                } else {
                    sprite = Sprite.player_side_3;
                }
            }
            flip = true;
        }


        screen.renderPlayer(x - 16, y - 16, sprite, flip);
      //  screen.renderPlayer(xx + 16, yy, Sprite.player1);
        //screen.renderPlayer(xx, yy + 16, Sprite.player2);
        //screen.renderPlayer(xx + 16, yy + 16, Sprite.player3);

    }
}
