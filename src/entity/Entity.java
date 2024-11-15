package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enemy.Monster;;

public abstract class Entity {
    
    protected GamePanel gamePanel;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    //BufferedImage: describes an Image with an accessible buffer of image data (we use to store our image files)
    protected UtilityTool utilityTool = new UtilityTool();
    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected int solidAreaDefaultX, solidAreaDefaultY;

    //STATE
    protected int worldX, worldY;
    protected String direction = "down";
    protected int spriteNum = 1;
    protected int speed = 0;
    protected boolean collisionOn = false;


    //COUNTER
    protected int spriteCounter = 0;

    
    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public Rectangle getSolidArea(){
        return this.solidArea;
    }

    public void setDefaultSolidArea(){
        this.solidArea.x = this.solidAreaDefaultX;
        this.solidArea.y = this.solidAreaDefaultY;
    }

    public String getDirection(){
        return this.direction;
    }

    //ABSTRACT METHOD
    public abstract void update();
    public abstract void draw(Graphics2D graphics2D);



    protected BufferedImage setup(String imagePath,int width, int height){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public void checkTile(){
        int leftWorldX = worldX + solidArea.x;
        int rightWorldX = worldX + solidArea.x + solidArea.width;
        int topWorldY = worldY + solidArea.y;
        int bottomWorldY = worldY + solidArea.y + solidArea.height;

        int leftCol = leftWorldX / gamePanel.tileSize;
        int rightCol = rightWorldX / gamePanel.tileSize;
        int topRow = topWorldY / gamePanel.tileSize;
        int bottomRow = bottomWorldY / gamePanel.tileSize;

        int tileNum1 , tileNum2;

        switch (this.direction) {
            case "up":
                topRow = (topWorldY - speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[leftCol][topRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[rightCol][topRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
            case "down":
                bottomRow = (bottomWorldY + speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[leftCol][bottomRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[rightCol][bottomRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftWorldX - speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[leftCol][topRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[leftCol][bottomRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightWorldX + speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.getTileManager().mapTileNum[rightCol][topRow];
                tileNum2 = gamePanel.getTileManager().mapTileNum[rightCol][bottomRow];
                if(gamePanel.getTileManager().tile[tileNum1].collision == true || gamePanel.getTileManager().tile[tileNum2].collision == true ){
                    this.collisionOn = true;
                }
                break;
        }
    }

    public int checkMonster(Monster[] monsters){

        int index = -1;

        for(int i = 0; i < monsters.length; i++){
            if(monsters[i] != null){
                //get entity's solid area position
                this.solidArea.x = this.worldX + this.solidArea.x;
                this.solidArea.y = this.worldY + this.solidArea.y;

                //get the monster's solid area position
                monsters[i].getSolidArea().x = monsters[i].getWorldX() + monsters[i].getSolidArea().x;
                monsters[i].getSolidArea().y = monsters[i].getWorldY() + monsters[i].getSolidArea().y;


                switch (this.direction) {
                    case "up": this.solidArea.y -= this.speed; break;
                    case "down": this.solidArea.y += this.speed; break;
                    case "left": this.solidArea.x -= this.speed; break;
                    case "right": this.solidArea.x += this.speed; break;
                }

                if(this.solidArea.intersects(monsters[i].getSolidArea())){
                    this.collisionOn = true;
                    index = i;
                }
                this.setDefaultSolidArea();
                monsters[i].setDefaultSolidArea();
            }
        }


        return index;

    }

    public int checkObject(){
        int i = -1;

        return i;
    }

}
