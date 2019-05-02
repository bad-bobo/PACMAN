import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class Main {

    public static void main(String[] args) {
        BoundedGrid grid = new BoundedGrid(15, 15);
        World world = new World<>(grid);
        Ghost ghost = new Ghost();
        ghost.putSelfInGrid(grid, new Location(0, 0));
        world.show();
    }

}
