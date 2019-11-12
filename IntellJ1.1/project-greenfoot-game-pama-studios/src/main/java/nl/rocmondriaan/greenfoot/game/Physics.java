package nl.rocmondriaan.greenfoot.game;

import nl.rocmondriaan.greenfoot.engine.Mover;
import greenfoot.*;

public class Physics extends Mover
{
    protected double vSpeed = 0;
    protected double acceleration = 0.9;

    //Emily is a gay tranny who wanted doubles
    private double doubleX;
    private double doubleY;
    public double getDoubleX() {
        return doubleX;
    }
    public double getDoubleY() {
        return doubleY;
    }
    protected void setDoubleX(double x) {
        doubleX = x;
    }
    protected void setDoubleY(double y) {
        doubleY = y;
    }
    public void setNewLocation(double x, double y) {
        doubleX = x;
        doubleY = y;
        setLocation((int) doubleX, (int) doubleY);
    }
    public void setRelativeLocation(double x, double y) {
        doubleX = doubleX + x;
        doubleY = doubleY + y;
        setLocation((int) doubleX, (int) doubleY);
    }
    public void entityOffset() {
        doubleX = doubleX + Camera.entityXOffset;
        doubleY = doubleY + Camera.entityYOffset;
    }
    public void jump(double height)
    {
        setRelativeLocation(0, - 1);
        vSpeed = vSpeed - height;
    }
    public void updateGravity()
    {
        if(!onGround() && !willBumpHead() && !onLadder())
        {
            vSpeed = vSpeed + acceleration;
            if (vSpeed > 20) vSpeed = 20;
            setRelativeLocation(0,vSpeed);
        }
        else
        {
            vSpeed = 0;
        }
    }
    public boolean onLadder()
    {
        if (getOneObjectAtOffset(0, -1 + getImage().getHeight() / 2 + (int) vSpeed, Ladder.class) != null) {
            if (getOneObjectAtOffset(0, -1 + (int) vSpeed, Ladder.class) != null) {
                return true;
            }
            else
            {
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean onGround()
    {
        if (getOneObjectAtOffset(getImage().getWidth() / -2 + 1, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 - 1, getImage().getHeight() / 2 + (int) vSpeed + 1, Solid.class) != null) {
            return true;
        } else {
            return false;
        }
    }
    public boolean willBumpHead(){
        if (getOneObjectAtOffset(getImage().getWidth() / -2, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null ||
                getOneObjectAtOffset(0, getImage().getHeight() / -2 + (int) vSpeed, Solid.class) != null)
            return true;
        return false;
    }
    public boolean canMoveLeft(double speed){
        if (getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, getImage().getHeight() / -2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, 0,  Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / -2 - (int) speed - 1, getImage().getHeight() / 2 - 1,  Solid.class) != null)
            return false;
        if (doubleX - speed < getImage().getWidth()/2) return false;
        return true;
    }
    public boolean canMoveRight(double speed){
        if (getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, getImage().getHeight() / -2, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, 0, Solid.class) != null ||
                getOneObjectAtOffset(getImage().getWidth() / 2 + (int) speed + 1, getImage().getHeight() / 2 - 1, Solid.class) != null)
            return false;
        if (doubleX - speed > Options.screenWidth - getImage().getWidth()/2) return false;
        return true;
    }
    public void moveRight(double speed)
    {
        setRelativeLocation(speed,0);
    }
    public void moveLeft(double speed)
    {
        setRelativeLocation(- speed,0);
    }
}
