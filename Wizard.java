import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends SmoothMover
{
    private int size = 16; 
    private int life = 10;
    private int stability;
    
    private int reloadDelayCount;
    private static final int gunReloadTime = 30;
    
    /**
     * Act - do whatever the Wizard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        reloadDelayCount++;
        move();
        find();// Add your action code here.
    }  
    
    public Wizard()
    {
        this(55);
    }
    
    public Wizard(int size)
    {
        super(new Vector(Greenfoot.getRandomNumber(360), 2));
    }
    
     private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {
            Bullet bullet = new Bullet (getVelocity(), getRotation());
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
    
    private void find()
    {
       if(getObjectsInRange(200, Rocket.class).size() > 0)
       {
                  turnTowards(getObjectsInRange(200, Rocket.class).get(0).getX(),
                  getObjectsInRange(200, Rocket.class).get(0).getY());
                  fire();
       }
    }
}
