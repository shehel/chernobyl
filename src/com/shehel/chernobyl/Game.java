package com.shehel.chernobyl;

import com.shehel.chernobyl.entity.mob.Player;
import com.shehel.chernobyl.graphics.Screen;
import com.shehel.chernobyl.input.Keyboard;
import com.shehel.chernobyl.input.Mouse;
import com.shehel.chernobyl.level.Level;
import com.shehel.chernobyl.level.RandomLevel;
import com.shehel.chernobyl.level.SpawnLevel;
import com.shehel.chernobyl.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

/**
 * Created by shehel on 7/23/2016.
 */
public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private Screen screen;

    private Keyboard key;
    private Mouse mouse;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    //game looper
    private boolean running = false;
    private Level level;
    private Player player;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);


        screen = new Screen(width, height);
        frame = new JFrame();

        key = new Keyboard();
        //level = new RandomLevel(64, 64);
        level = new SpawnLevel("/texture/levels/level.png");

        TileCoordinate playerSpawn = new TileCoordinate(4, 5);
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        player.init(level);
        frame.addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updateCounter = 0;

        while (running = true) {
            frame.requestFocus();
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updateCounter++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("Chernobyl " + updateCounter + " updates per sec, " + frames + " frames");
                updateCounter = 0;
                frames = 0;
            }
        }
    }

    public void update() {
        key.update();
        player.update();
    }

    //main render method
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();

        //g.setColor(Color.black);
        //g.fillRect(0,0, getWidth(), getHeight());

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
       // System.out.println(Mouse.getX() + "  ," +Mouse.getY());
        g.fillRect(Mouse.getX(), Mouse.getY(), 64, 64);
        g.dispose();

        //buffer swap, bullet - release memory and show the frame in buffer
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.add(game);
        game.frame.pack();
        game.frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //centre window
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

}
