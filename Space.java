import greenfoot.*;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Ava Jorgensen
 * @version 1.1
 */
public class Space extends World
{
    private Counter scoreCounter;
    private int startAsteroids = 2;
    private static int pointsToAdd = 5;
    /**
     * Create the space and all objects within it.
     */
    public Space() 
    {
        super(600, 500, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill(); 

        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);

        addAsteroids(startAsteroids);
        paintStars(555);

        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 480);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
        prepare();
        
        
    }

    public void setBackground()
    {
        new GreenfootImage("screenPage1"); 
         
    }
    
    private void paintStars(int count)
    {
        GreenfootImage background = getBackground();
        for(int i = 0; i < count; i++)
        {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());

            int deltaRed = Greenfoot.getRandomNumber(80) - 30;
            int deltaGreen = Greenfoot.getRandomNumber(80) - 45;
            int deltaBlue = Greenfoot.getRandomNumber(80) - 45;

            int starBrightness = Greenfoot.getRandomNumber(120) + 80;

            Color StarColor = new Color(starBrightness + deltaRed, starBrightness + deltaGreen, starBrightness + deltaBlue);

            int starSize = Greenfoot.getRandomNumber(3) + 1;

            background.setColor(StarColor);
            background.fillOval(x, y, starSize, starSize);
        }
    }

    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }

    public void updateScore(int addToScore)
    {
        scoreCounter.add(addToScore);
    }

    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        int x = getWidth()/2;
        int y = getHeight()/2; 
        int currentScore = scoreCounter.getValue();
        addObject(new ScoreBoard(currentScore), x ,y); 
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Wizard wizard = new Wizard();
        addObject(wizard,163,391);
    }
}