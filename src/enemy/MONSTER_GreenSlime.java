package enemy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;

public class MONSTER_GreenSlime extends Monster {


    public MONSTER_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        name = "Green Slime";
        
        getImage();
    }

    @Override
    public void getImage() {
        up1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);

    }

    @Override
    public void update() {

        this.setAction();

        this.collisionOn = false;
        this.checkTile();
        this.checkObject();
        this.checkMonster();
        
        boolean contactPlayer = checkPlayer();

        if(contactPlayer == true){
            gamePanel.getPlayer().receiveDamage(1);
        }

        if(this.collisionOn == false){
            switch (this.direction) {
                case "up": this.worldY -= speed; break;
                case "down": this.worldY += speed; break;
                case "left": this.worldX -= speed; break;
                case "right": this.worldX += speed; break;
            }
        }

        this.spriteCounter ++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }



    }

    @Override
    public void draw(Graphics2D graphics2d) {

        BufferedImage image = null;

        int screenX = this.worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = this.worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();


        if(worldX > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() - gamePanel.tileSize &&
           worldX < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() + gamePanel.tileSize &&
           worldY > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() - gamePanel.tileSize &&
           worldY < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY() + gamePanel.tileSize){
            switch (this.direction) {
                case "up": 
                    if(spriteNum == 1){ image = up1; }
                    if(spriteNum == 2){ image = up2; }
                    break;
                case "down": 
                    if(spriteNum == 1){ image = down1; }
                    if(spriteNum == 2){ image = down2; }
                    break;
                case "left":
                    if(spriteNum == 1){ image = left1; }
                    if(spriteNum == 2){ image = left2; }
                    break;
                case "right": 
                    if(spriteNum == 1){ image = right1; }
                    if(spriteNum  == 2){ image = right2; }
                    break;
            }


            //MONSTER HP BAR


            graphics2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }


    }



    @Override
    public void setAction() {
        actionLockCounter ++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "up";
            }
            else if(25 < i && i <= 50){
                direction = "down";
            }
            else if(50 < i && i <= 75){
                direction = "left";
            }
            else{
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    @Override
    public void attack() {
        
    }



}
