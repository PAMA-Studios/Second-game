package nl.rocmondriaan.greenfoot.game;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.Random;

public class Particles extends Actor {
    private GreenfootImage smoke1 = new GreenfootImage("WhiteSmoke1.png");
    private GreenfootImage smoke2 = new GreenfootImage("WhiteSmoke2.png");
    private GreenfootImage smoke3 = new GreenfootImage("WhiteSmoke3.png");
    private GreenfootImage beam = new GreenfootImage("BeamVanAllah.png");
    private GreenfootImage confetti = new GreenfootImage("confetti.png");
    private GreenfootImage confettim = new GreenfootImage("confetti.png");

    private int random3;
    private int random2;
    private int deathFadeTime;
    private int deathTime;
    private int timePassed;
    private String type;

    public void act() {
        Random rn = new Random();
        random3 = rn.nextInt(3) + 1;
        timePassed += 1;
        if (type.equals("smoke")) {
            setLocation(getX(), getY()-1);
            if (timePassed >= deathTime) {
                fade();
            }
        }
        else if (type.equals("beam")) {
            if (timePassed >= deathTime) {
                fadelong();
            }
        }
        else if (type.equals("confetti")) {
            setLocation(getX() - 2, getY() - 4); //Making it fly up
            if (timePassed >= deathTime) {
                fade();
            }
        }
        else if (type.equals("confettim")) { //This is mirrored
            setLocation(getX() + 2, getY() - 4); //Making it fly up
            if (timePassed >= deathTime) {
                fade();
            }
        }
        else { System.out.println(type); }

    }

    Particles(String type) {
        this.type = type;
        if (type.equals("smoke")){
            deathTime = 60; //How long to be destroyed yes it's randomised now
            if (random3 == 1){ //3 random images
                setImage(smoke1);
                smoke1.scale((Options.blockSize) / 3,(Options.blockSize) / 3);
                smoke1.setTransparency(150);
            }
            else if (random3 == 2) {
                setImage(smoke2);
                smoke2.scale((Options.blockSize) / 3,(Options.blockSize) / 3);
                smoke2.setTransparency(150);
            }
            else{
                setImage(smoke3);
                smoke3.scale((Options.blockSize) / 3,(Options.blockSize) / 3);
                smoke3.setTransparency(150);
            }
        }
        if (type.equals("beam")){
            setImage(beam);
        }
        if (type.equals("confetti") || type.equals("confettim")){
            confetti();
        }
    }

    public void fade(){ //Fade out en kill object
        deathFadeTime += 1;
        if (150 - deathFadeTime*2 > 0) {
            this.getImage().setTransparency(150-deathFadeTime*2);
        }
        if (deathFadeTime>=60) {deathFadeTime = 0; getWorld().removeObject(this); timePassed = 0;}
    }
    public void fadelong(){ //Fade out en kill object
        deathFadeTime += 1;
        if (220 - deathFadeTime > 0) {
            this.getImage().setTransparency(220 - deathFadeTime);
        }
        if (deathFadeTime >= 110) {deathFadeTime = 0; getWorld().removeObject(this); timePassed = 0;}
    }
    public void confetti(){
        setImage(confetti);
        confetti.scale((Options.blockSize) * 4, (Options.blockSize) * 4);
    }
}
