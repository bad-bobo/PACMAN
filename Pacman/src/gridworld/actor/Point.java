package gridworld.actor;

/**

 * Created because Java Point Class in double. For use in Depth first and breath first search algorithm
 */
public class Point
{
    /**
     * X coordinate (row)
     */
    private int x;

    /**
     * Y coordinate (col)
     */
    private int y;


    public Point( int x, int y )
    {
        this.x = x;
        this.y = y;
    }


    @Override public String toString()
    {
        return "(" + x + ", " + y + ")";
    }


    public int getX()
    {
        return x;
    }


    public int getY()
    {
        return y;
    }
}
