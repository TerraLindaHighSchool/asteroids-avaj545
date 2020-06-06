import greenfoot.*;

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key. 'z' releases a proton wave.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 1.1
 */
public class Rocket extends SmoothMover
{
    private static final int gunReloadTime = 5; // The minimum delay between firing the gun.
    private int life;
    public int score; 
    private int reloadDelayCount;               // How long ago we fired the gun the last time.
    private Vector acceleration;
    
    private GreenfootImage rocket = new GreenfootImage("rocket.png");    
    private GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust.png");
    /**
     * Initialise this rocket.
     */
    public Rocket()
    {
        reloadDelayCount = 0;
        addToVelocity(new Vector(150,.5));
        acceleration = (new Vector(0, 0.3));
        increaseSpeed(new Vector(13, 0.3));
        life = 20; 
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        move();
        checkKeys();
        reloadDelayCount++;
        checkCollision(); 
        checkLife();
    }
    
      /**
    * Check whether there are any key pressed and react to them.
     */
     private void checkKeys() 
    {
        ignite(Greenfoot.isKeyDown("up"));
        
        if (Greenfoot.isKeyDown("space")) 
        {
            fire();
        }
        if (Greenfoot.isKeyDown("left")) 
        {
            turn(-5);
        }
        if (Greenfoot.isKeyDown("right")) 
        {
            turn(5);
        }
    }
    
    private void checkLife()
    {
        Space space = (Space) getWorld();
        if(life == 0)
        {
            Greenfoot.playSound("EnergyGun.wav");
            space.gameOver();
        }
    }
    
    private void ignite(boolean boosterOn)
    {
        if (boosterOn) 
        {
            setImage(rocketWithThrust);
            acceleration.setDirection(getRotation());
            increaseSpeed(acceleration);
        }
        else
        {
            setImage(rocket);
        }
    }
    
    /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {
            Bullet bullet = new Bullet (getVelocity(), getRotation(),"Rocket" );
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
     
    public int getX()
    {
     return super.getX();
    }

    public int getY()
    {
     return super.getY();
    }
    
    private void checkCollision()
    {
       Space space = (Space) getWorld();
       if(getOneIntersectingObject(Asteroid.class) != null)
       { 
        space.addObject(new Explosion(), getX(), getY());
        space.removeObject(this);
        space.gameOver();
       }
       
       if(getOneIntersectingObject(Bullet.class) != null)
       { 
        score--;
        space.removeObject(this);
        life--;
       }
    }
}