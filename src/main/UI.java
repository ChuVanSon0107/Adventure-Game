package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Heart;

public class UI {
    private GamePanel gamePanel;
    private Graphics2D graphics2D;
    private Font arial_40, arial_80;

    private BufferedImage heart_full, heart_half, heart_blank;


    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);

        OBJ_Heart heart = new OBJ_Heart(gamePanel);
        heart_full = heart.getImage1();
        heart_half = heart.getImage2();
        heart_blank = heart.getImage3();

    }

    public void draw(Graphics2D graphics2D){
        this.graphics2D = graphics2D;

        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.WHITE);

        drawPlayerLife();

        
    }

    public void drawPlayerLife(){
        int x = gamePanel.tileSize / 2;
        int y = gamePanel.tileSize / 2;

        int i = 0;

        //DRAW MAX LIFE
        while(i < gamePanel.getPlayer().getMaxLife() / 2){
            graphics2D.drawImage(heart_blank, x, y, null);
            i ++;
            x += gamePanel.tileSize;
        }

        //RESET
        x = gamePanel.tileSize / 2;
        y = gamePanel.tileSize / 2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i < gamePanel.getPlayer().getLife()){
            graphics2D.drawImage(heart_half, x, y, null);
            i ++;

            if(i < gamePanel.getPlayer().getLife()){
                graphics2D.drawImage(heart_full, x, y, null);
            }

            i++;
            x += gamePanel.tileSize;
        }
    }

}



