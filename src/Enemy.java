
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Enemy extends Character{
    Random rand = new Random();
    int timeToChangeDirection;
    public Enemy(MyPanel panel, int x, int y){
        super(panel);
        reloadTime = 70;
        direction = rand.nextInt(4);
        this.changeSpeed();
        allImage = new HashMap<>();
        try {
            for(int i =0; i <=3; i++){
                imageDirection = new ArrayList<>();
                for(int j =0; j<=2; j++){
                    imageDirection.add(ImageIO.read(new File("png/"+i+"/"+j+".png")));
                }
                allImage.put(i, imageDirection);
            }
        } catch (IOException e) {
        }
        image = allImage.get(direction).get(0);
        this.x = x;
        this.y = y;
    }
    @Override
    public void move(){
        timeToChangeDirection++;
        if(timeToChangeDirection >= 150){
            timeToChangeDirection= 0;
            direction = rand.nextInt(4);
            changeSpeed();
        }
        this.condition();
        super.move();
        image = allImage.get(direction).get(0);
        this.fire();
    }
    @Override
    public void condition(){
        if(x + speedX > panel.getWidth() - image.getWidth() || x + speedX < 0){
            speedX *= -1;
            if(speedX > 0) direction = 2;
            else direction = 1;
            changeSpeed();
        }
        if(y + speedY > panel.getHeight()-50 - image.getHeight() || y + speedY < 0){
            speedY *= -1;
            if(speedY > 0) direction = 0;
            else direction = 3;
            changeSpeed();
        }
    }
    @Override
    public void damaged(){
        if(immortalTime <= 0){
            panel.trash.add(this);
            panel.controlMusic.add("Battle City (MP3)/SFX destroy.wav");
            panel.controlObject.totalEnemy--;
        }
    }
    @Override
    public void fire(){
        if(reloadTime <=0 && bullet == null){
            reloadTime = 70;
            bullet = new Bullet(panel,x+13,y+13,direction,this);
        }
    }
    @Override
    public void increaseHealth(){
        if(panel.controlObject.totalEnemy < 10){
            panel.controlObject.totalEnemy++;
            panel.controlObject.enemyLeft++;
        }
    }
}
