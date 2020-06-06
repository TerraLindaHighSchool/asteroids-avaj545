import greenfoot.*;

/**
 * A bullet that can hit asteroids.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 */
public class Bullet extends SmoothMover
{
    /** The damage this bullet will deal */
    private static final int damage = 1;
    
    /** A bullet looses one life each act, and will disappear when life = 0 */
    private int life = 25;
    private String creator;
    public int score;
    private static final int pointsToAdd = 5;
    /**
     * Default constructor for testing.
     */
    public Bullet()
    {
    }
    
    /**
     * Create a bullet with given speed and direction of movement.
     */
    public Bullet(Vector speed, int rotation, String creator)
    {
        super(speed);
        setRotation(rotation);
        addToVelocity(new Vector(rotation, 15));
        Greenfoot.playSound("EnergyGun.wav");
        this.creator = creator; 
    }
    
    /**
     * The bullet will damage asteroids if it hits them.
     */
    public void act()
    {
        if(life <= 0) {
            //getWorld().removeObject(this);
        } 
        else {
            move();
            if(creator. equals("Wizard"))
            {
                Space space = (Space) getWorld();
                if(this.isTouching(Rocket.class))
                {
                    score--; 
                    // removeObject(Rocket.class);
                    //getWorld();
                }
            }
            if(creator.equals("Rocket"))
            {
                checkWizardHit();
                checkAsteroidHit();
            }
            life--;
        }  
    }
    
    /**
     * Check whether we have hit an asteroid.
     */
    private void checkAsteroidHit()
    {
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        if (asteroid != null)
        {
           ((Space) getWorld()).updateScore(pointsToAdd);
            getWorld().removeObject(this);
        }
    }
    
    private void checkWizardHit()
    {
        Wizard wizard = (Wizard) getOneIntersectingObject(Wizard.class);
        if (wizard != null)
        {
            ((Space)getWorld()).updateScore(pointsToAdd);
            getWorld().removeObject(this);
        }
    }
} 