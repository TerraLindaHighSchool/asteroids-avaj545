import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rules here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rules extends World
{

    /**
     * Constructor for objects of class Rules.
     * 
     */
    public Rules()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(350,300,1); 
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld(new Space());
        }
    }
}
