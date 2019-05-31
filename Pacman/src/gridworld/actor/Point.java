package gridworld.actor;

/**

 * Created because Java Point Class in double. For use in Depth first and breath first search algorithm
 */
public class Point implements Comparable
{
    /**
     * X coordinate (row)
     */
    private int x;

    /**
     * Y coordinate (col)
     */
    private int y;


    /**
     * Init x to x and y to y
     * @param x the x coordinate or Row
     * @param y the y coordinate or Col
     */
    public Point( int x, int y )
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Converts the point into an string
     * @return a string
     */
    @Override public String toString()
    {
        return "(" + x + ", " + y + ")";
    }


    /**
     * gets x
     * @return x
     */
    public int getX()
    {
        return x;
    }


    /**
     * gets y
     * @return y
     */
    public int getY()
    {
        return y;
    }


    /**
     * stub, never used for our implemnetatin
     * @param o an object
     * @return 0
     */
    @Override public int compareTo( Object o )
    {
        return 0;
    }
}
