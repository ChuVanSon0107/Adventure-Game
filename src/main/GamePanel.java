package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import enemy.MONSTER_GreenSlime;
import enemy.Monster;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTING
    public final int originalTileSize = 16; //16x16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight  = tileSize * maxScreenRow;

    //WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    private int FPS = 60; //FPS: FRAME PER SECOND

    //SYSTEM
    private KeyHandler keyHandler = new KeyHandler(this);
    private TileManager tileManager = new TileManager(this);
    private Thread gameThread;

    //OBJECT, MONSTER, ENTITY
    private Player player = new Player(this, keyHandler);
    private Monster monsters[] = new Monster[20];

    //GAME STATE
    public GameState gameState;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));//set the size of this class(JPanel)
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public Player getPlayer(){
        return player;
    }
    public TileManager getTileManager(){
        return tileManager;
    }

    public Monster[] getMonsters(){
        return this.monsters;
    }

    public void setupGame(){

        gameState = GameState.BEGIN_STATE;

        this.setMonster();
        this.setObject();
        this.setNPC();

    }

    public void retry(){

    }


    public void restart(){

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();

                delta --;
                drawCount ++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update(){
        
        //PLAYER
        player.update();

        //MONSTERS
        for(int i = 0; i < monsters.length; i++){
            if(monsters[i] != null){
                monsters[i].update();
            }
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D)g;

        //DRAW TILE
        tileManager.draw(graphics2D);

        //DRAW PLAYER
        player.draw(graphics2D);

        for(int i = 0; i < monsters.length; i++){
            if(monsters[i] != null){
                monsters[i].draw(graphics2D);
            }
        }

        graphics2D.dispose();
    }


    

    public void setMonster(){

    }

    public void setObject(){
        int i = 0;
        this.monsters[i] = new MONSTER_GreenSlime(this);
        this.monsters[i].setPosition(23, 36);
        i++;

        this.monsters[i] = new MONSTER_GreenSlime(this);
        this.monsters[i].setPosition(23, 37);
        i++;

    }

    public void setNPC(){

    }

}
