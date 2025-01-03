package enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public abstract class Monster {
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
    protected boolean collisionOn = false;


    //COUNTER
    protected int spriteCounter = 0;
    protected int actionLockCounter = 0;//to lock the action of the monster

    //MONSTER STATUS
    protected String name;
    protected int speed;
    protected int life;
    protected int maxLife;


    public Monster(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }


    public Rectangle getSolidArea(){
        return this.solidArea;
    }

    public void setDefaultSolidArea(){
        this.solidArea.x = this.solidAreaDefaultX;
        this.solidArea.y = this.solidAreaDefaultY;
    }

    public int getWorldX(){
        return this.worldX;
    }

    public int getWorldY(){
        return this.worldY;
    }

    public void setPosition(int x, int y){
        this.worldX = x * gamePanel.tileSize;
        this.worldY = y * gamePanel.tileSize;
    }

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

        int tileNum1, tileNum2;

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

    public boolean checkPlayer(){
        boolean contactPlayer = false;

        //Get monster's solid area position
        this.solidArea.x = this.worldX + this.solidArea.x;
        this.solidArea.y = this.worldY + this.solidArea.y;

        //Get the player's solid area
        gamePanel.getPlayer().getSolidArea().x = gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidArea().x;
        gamePanel.getPlayer().getSolidArea().y = gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidArea().y;

        switch(this.direction){
            case "up": this.solidArea.y -= this.speed; break;
            case "down": this.solidArea.y += this.speed; break;
            case "left": this.solidArea.x -= this.speed; break;
            case "right": this.solidArea.x += this.speed; break;
        }

        if(this.solidArea.intersects(gamePanel.getPlayer().getSolidArea())){
            this.collisionOn = true;
            contactPlayer = true;
        }

        this.setDefaultSolidArea();
        gamePanel.getPlayer().setDefaultSolidArea();

        return contactPlayer;
    }

    public void checkObject(){
        
    }

    public void checkMonster(){
        
    }

    public abstract void getImage();
    public abstract void update();
    public abstract void draw(Graphics2D graphics2D);
    public abstract void setAction();
    public abstract void attack();
    

    public void receiveDamage(){
        
    }
}
