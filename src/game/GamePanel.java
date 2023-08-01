package game;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;


    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 786
    public final int screenHeight =  tileSize * maxScreenRow; // 567


    //WORLD SETTINGS
    public final int maxWorldCol = 70;
    public final int maxWorldRow = 50;


    //FPS
    int FPS = 60;


    //System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;


    //Entity and Object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] =new SuperObject[10];

    public Entity npc[] = new Entity[12];

    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public final int dialougeState = 3;

    public final int titleState = 0;

    public GamePanel() throws IOException, FontFormatException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setUpGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double newDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            update();
            repaint();
            drawCount++;

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;

            }

            try {

                double remainingTime = newDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0 ) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                newDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public void update() {
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            //nothing
        }

    }



    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Debug start
        long drawStart = System.nanoTime();
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();

        }

        //Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        //Others

        else {

            //Tile
            tileM.draw(g2);


            //Object
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }

            //Player
            player.draw(g2);

            //UI
            ui.draw(g2);
        }

        //Debug end
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd -drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: "+ passed, 10, 400);
            System.out.println("Draw Time: "+passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void resumeMusic() {
        music.play();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
