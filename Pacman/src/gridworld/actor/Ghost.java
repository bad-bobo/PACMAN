package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public abstract class Ghost extends MovableActor {
    public static final int SCARE_TIME = 30;//30
    public static int Scared = -1;
    /**
     * The previous cell
     */
    protected Location oldLocation = location;


    /**
     * Creates a new Ghost, sets color to color.
     *
     * @param color The Color for Ghost.
     */
    public Ghost(Color color) {
        super();
        setColor(color);
    }

    public static boolean isScared() {
        return Scared >= 0;
    }

    public static void scare() {
        Scared = SCARE_TIME;
    }

    /**
     * Moves the Ghost the the new Location by
     * 1) deleting this Ghost
     * 2) If the new Location hold no Actor, creates a new Ghost there
     * 2) If there is a pellet in the new Location, deletes the pellet and
     * puts a pellet in this location
     * 3) Put a new Ghost in the new Location
     *
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation) {
        if (grid == null) throw new IllegalStateException(
                "This actor is not in a grid.");

        if (!grid.isValid(newLocation)) {
            throw new IllegalArgumentException(
                    "Location " + newLocation + " is not valid.");
        }

        if (newLocation.equals(location)) return;
        grid.remove(location);
        Actor other = grid.get(newLocation);
        if (other != null) {
            if(other instanceof Pacman)
            {
                if(isScared())
                {
                    this.removeSelfFromGrid();
                }
            }
            if (other instanceof PowerPellet) {
                PowerPellet p = new PowerPellet();
                other.removeSelfFromGrid();
                //  grid.put( location, p ); DONT USE THIS, <bold>use</bold>
                // putSelfInGrid
                p.putSelfInGrid(grid, location);
                grid.put(newLocation, this);
                oldLocation = location;
                location = newLocation;
                return;
            }
            if (other instanceof Pellet) {
                Pellet p = new Pellet();
                other.removeSelfFromGrid();
                //  grid.put( location, p ); DONT USE THIS, <bold>use</bold>
                // putSelfInGrid
                p.putSelfInGrid(grid, location);
                grid.put(newLocation, this);
                oldLocation = location;
                location = newLocation;
                return;
            }

            other.removeSelfFromGrid();

        }
        oldLocation = location;
        location = newLocation;
        grid.put(location, this);
    }


    /**
     * A Ghost  can move anywhere except if the next location has a wall or
     * another Ghost
     *
     * @param next The next Location
     * @return True if can move, false otherwise.
     */
    public boolean canMove(Location next) {

        Grid<Actor> gr = getGrid();
        if (gr == null) return false;

        if (!gr.isValid(next)) return false;
        Actor neighbor = gr.get(next);

        if (next.equals(oldLocation)) {
            return false;
        }
        if (neighbor instanceof Wall) {
            return false;
        }

        return !(neighbor instanceof Ghost);
//        return true;
    }


    public void visualizePath(ArrayList<Point> path) {
        for (int i = 1; i < path.size(); i++) {
            Pellet x = new Pellet();
            x.setColor(this.getColor());
//            x.setColor( Color.GREEN );

            Location loc = new Location(path.get(i).getX(), path.get(i).getY());
            Actor next = grid.get(loc);
            if ((next instanceof Pellet && !(next instanceof PowerPellet)) ||
                    !(next instanceof Actor)) {
                x.putSelfInGrid(grid, loc);
            }

        }
    }

}
