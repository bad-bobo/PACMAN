package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelPellet extends Pellet implements Edible {

    public static final int ROW = 27;
    public static final int COL = 24;
    private static ArrayList<String[][]> levels = new ArrayList<>();

    static {
        levels.add(new String[][]{
                {"N_00.00", "N_00.01", "N_00.02", "N_00.03", "N_00.04",
                        "N_00.05", "N_00.06", "N_00.07", "N_00.08", "N_00.09",
                        "N_00.10", "N_00.11", "N_00.12", "N_00.13", "N_00.14",
                        "N_00.15", "N_00.16", "N_00.17", "N_00.18", "N_00.19",
                        "N_00.20", "N_00.21", "N_00.22", "N_00.23",},
                {"N_01.00", "N_01.01", "N_01.02", "N_01.03", "N_01.04",
                        "N_01.05", "N_01.06", "N_01.07", "N_01.08", "N_01.09",
                        "N_01.10", "N_01.11", "N_01.12", "N_01.13", "N_01.14",
                        "N_01.15", "N_01.16", "N_01.17", "N_01.18", "N_01.19",
                        "N_01.20", "N_01.21", "N_01.22", "N_01.23",},
                {"X_02.00", "X_02.01", "X_02.02", "X_02.03", "X_02.04",
                        "X_02.05", "X_02.06", "X_02.07", "X_02.08", "X_02.09",
                        "X_02.10", "X_02.11", "X_02.12", "X_02.13", "X_02.14",
                        "X_02.15", "X_02.16", "X_02.17", "X_02.18", "X_02.19",
                        "X_02.20", "X_02.21", "X_02.22", "X_02.23",},
                {"X_03.00", "O_03.01", "O_03.02", "O_03.03", "O_03.04",
                        "O_03.05", "O_03.06", "O_03.07", "O_03.08", "O_03.09",
                        "O_03.10", "X_03.11", "X_03.12", "O_03.13", "O_03.14",
                        "O_03.15", "O_03.16", "O_03.17", "O_03.18", "O_03.19",
                        "O_03.20", "O_03.21", "O_03.22", "X_03.23",},
                {"X_04.00", "O_04.01", "X_04.02", "X_04.03", "O_04.04",
                        "X_04.05", "X_04.06", "O_04.07", "X_04.08", "X_04.09",
                        "O_04.10", "X_04.11", "X_04.12", "O_04.13", "X_04.14",
                        "X_04.15", "O_04.16", "X_04.17", "X_04.18", "O_04.19",
                        "X_04.20", "X_04.21", "O_04.22", "X_04.23",},
                {"X_05.00", "O_05.01", "X_05.02", "X_05.03", "O_05.04",
                        "O_05.05", "O_05.06", "O_05.07", "X_05.08", "X_05.09",
                        "O_05.10", "X_05.11", "X_05.12", "O_05.13", "X_05.14",
                        "X_05.15", "O_05.16", "O_05.17", "O_05.18", "O_05.19",
                        "X_05.20", "X_05.21", "O_05.22", "X_05.23",},
                {"X_06.00", "O_06.01", "O_06.02", "O_06.03", "O_06.04",
                        "X_06.05", "X_06.06", "O_06.07", "O_06.08", "O_06.09",
                        "O_06.10", "O_06.11", "O_06.12", "O_06.13", "O_06.14",
                        "O_06.15", "O_06.16", "X_06.17", "X_06.18", "O_06.19",
                        "O_06.20", "O_06.21", "O_06.22", "X_06.23",},
                {"X_07.00", "O_07.01", "X_07.02", "X_07.03", "X_07.04",
                        "X_07.05", "O_07.06", "O_07.07", "X_07.08", "O_07.09",
                        "X_07.10", "X_07.11", "X_07.12", "X_07.13", "O_07.14",
                        "X_07.15", "O_07.16", "O_07.17", "X_07.18", "X_07.19",
                        "X_07.20", "X_07.21", "O_07.22", "X_07.23",},
                {"X_08.00", "O_08.01", "O_08.02", "O_08.03", "O_08.04",
                        "O_08.05", "O_08.06", "X_08.07", "X_08.08", "O_08.09",
                        "O_08.10", "O_08.11", "O_08.12", "O_08.13", "O_08.14",
                        "X_08.15", "X_08.16", "O_08.17", "O_08.18", "O_08.19",
                        "O_08.20", "O_08.21", "O_08.22", "X_08.23",},
                {"X_09.00", "X_09.01", "X_09.02", "X_09.03", "X_09.04",
                        "O_09.05", "X_09.06", "X_09.07", "X_09.08", "X_09.09",
                        "O_09.10", "X_09.11", "X_09.12", "O_09.13", "X_09.14",
                        "X_09.15", "X_09.16", "X_09.17", "O_09.18", "X_09.19",
                        "X_09.20", "X_09.21", "X_09.22", "X_09.23",},
                {"X_10.00", "X_10.01", "X_10.02", "X_10.03", "X_10.04",
                        "O_10.05", "X_10.06", "X_10.07", "O_10.08", "O_10.09",
                        "O_10.10", "O_10.11", "O_10.12", "O_10.13", "O_10.14",
                        "O_10.15", "X_10.16", "X_10.17", "O_10.18", "X_10.19",
                        "X_10.20", "X_10.21", "X_10.22", "X_10.23",},
                {"X_11.00", "X_11.01", "X_11.02", "X_11.03", "X_11.04",
                        "O_11.05", "X_11.06", "O_11.07", "O_11.08", "X_11.09",
                        "X_11.10", "X_11.11", "X_11.12", "X_11.13", "X_11.14",
                        "O_11.15", "O_11.16", "X_11.17", "O_11.18", "X_11.19",
                        "X_11.20", "X_11.21", "X_11.22", "X_11.23",},
                {"O_12.00", "O_12.01", "X_12.02", "O_12.03", "X_12.04",
                        "O_12.05", "O_12.06", "O_12.07", "X_12.08", "X_12.09",
                        "X_12.10", "X_12.11", "X_12.12", "X_12.13", "X_12.14",
                        "X_12.15", "O_12.16", "O_12.17", "O_12.18", "X_12.19",
                        "O_12.20", "X_12.21", "O_12.22", "O_12.23",},
                {"X_13.00", "O_13.01", "O_13.02", "O_13.03", "X_13.04",
                        "O_13.05", "X_13.06", "O_13.07", "X_13.08", "X_13.09",
                        "X_13.10", "X_13.11", "X_13.12", "X_13.13", "X_13.14",
                        "X_13.15", "O_13.16", "X_13.17", "O_13.18", "X_13.19",
                        "O_13.20", "O_13.21", "O_13.22", "X_13.23",},
                {"X_14.00", "X_14.01", "X_14.02", "X_14.03", "X_14.04",
                        "O_14.05", "X_14.06", "O_14.07", "X_14.08", "X_14.09",
                        "X_14.10", "X_14.11", "X_14.12", "X_14.13", "X_14.14",
                        "X_14.15", "O_14.16", "X_14.17", "O_14.18", "X_14.19",
                        "X_14.20", "X_14.21", "X_14.22", "X_14.23",},
                {"X_15.00", "X_15.01", "X_15.02", "X_15.03", "X_15.04",
                        "O_15.05", "X_15.06", "O_15.07", "X_15.08", "O_15.09",
                        "O_15.10", "O_15.11", "O_15.12", "O_15.13", "O_15.14",
                        "X_15.15", "O_15.16", "X_15.17", "O_15.18", "X_15.19",
                        "X_15.20", "X_15.21", "X_15.22", "X_15.23",},
                {"X_16.00", "X_16.01", "X_16.02", "X_16.03", "X_16.04",
                        "O_16.05", "X_16.06", "O_16.07", "X_16.08", "O_16.09",
                        "X_16.10", "X_16.11", "X_16.12", "X_16.13", "O_16.14",
                        "X_16.15", "O_16.16", "X_16.17", "O_16.18", "X_16.19",
                        "X_16.20", "X_16.21", "X_16.22", "X_16.23",},
                {"X_17.00", "O_17.01", "O_17.02", "O_17.03", "O_17.04",
                        "O_17.05", "X_17.06", "O_17.07", "O_17.08", "O_17.09",
                        "O_17.10", "O_17.11", "O_17.12", "O_17.13", "O_17.14",
                        "O_17.15", "O_17.16", "X_17.17", "O_17.18", "O_17.19",
                        "O_17.20", "O_17.21", "O_17.22", "X_17.23",},
                {"X_18.00", "O_18.01", "X_18.02", "X_18.03", "O_18.04",
                        "X_18.05", "X_18.06", "X_18.07", "X_18.08", "O_18.09",
                        "X_18.10", "X_18.11", "X_18.12", "X_18.13", "O_18.14",
                        "X_18.15", "X_18.16", "X_18.17", "X_18.18", "O_18.19",
                        "X_18.20", "X_18.21", "O_18.22", "X_18.23",},
                {"X_19.00", "O_19.01", "X_19.02", "X_19.03", "O_19.04",
                        "O_19.05", "O_19.06", "O_19.07", "O_19.08", "O_19.09",
                        "O_19.10", "X_19.11", "X_19.12", "O_19.13", "O_19.14",
                        "O_19.15", "O_19.16", "O_19.17", "O_19.18", "O_19.19",
                        "X_19.20", "X_19.21", "O_19.22", "X_19.23",},
                {"X_20.00", "O_20.01", "X_20.02", "X_20.03", "X_20.04",
                        "X_20.05", "O_20.06", "X_20.07", "X_20.08", "X_20.09",
                        "O_20.10", "O_20.11", "O_20.12", "O_20.13", "X_20.14",
                        "X_20.15", "X_20.16", "O_20.17", "X_20.18", "X_20.19",
                        "X_20.20", "X_20.21", "O_20.22", "X_20.23",},
                {"X_21.00", "O_21.01", "O_21.02", "O_21.03", "O_21.04",
                        "O_21.05", "O_21.06", "O_21.07", "X_21.08", "O_21.09",
                        "O_21.10", "X_21.11", "X_21.12", "O_21.13", "O_21.14",
                        "X_21.15", "O_21.16", "O_21.17", "O_21.18", "O_21.19",
                        "O_21.20", "O_21.21", "O_21.22", "X_21.23",},
                {"X_22.00", "O_22.01", "X_22.02", "X_22.03", "X_22.04",
                        "O_22.05", "X_22.06", "O_22.07", "X_22.08", "O_22.09",
                        "X_22.10", "X_22.11", "X_22.12", "X_22.13", "O_22.14",
                        "X_22.15", "O_22.16", "X_22.17", "O_22.18", "X_22.19",
                        "X_22.20", "X_22.21", "O_22.22", "X_22.23",},
                {"X_23.00", "O_23.01", "X_23.02", "X_23.03", "X_23.04",
                        "O_23.05", "X_23.06", "O_23.07", "X_23.08", "O_23.09",
                        "X_23.10", "X_23.11", "X_23.12", "X_23.13", "O_23.14",
                        "X_23.15", "O_23.16", "X_23.17", "O_23.18", "X_23.19",
                        "X_23.20", "X_23.21", "O_23.22", "X_23.23",},
                {"X_24.00", "O_24.01", "O_24.02", "O_24.03", "O_24.04",
                        "O_24.05", "O_24.06", "O_24.07", "O_24.08", "O_24.09",
                        "X_24.10", "X_24.11", "X_24.12", "X_24.13", "O_24.14",
                        "O_24.15", "O_24.16", "O_24.17", "O_24.18", "O_24.19",
                        "O_24.20", "O_24.21", "O_24.22", "X_24.23",},
                {"X_25.00", "X_25.01", "X_25.02", "X_25.03", "X_25.04",
                        "X_25.05", "X_25.06", "X_25.07", "X_25.08", "X_25.09",
                        "X_25.10", "X_25.11", "X_25.12", "X_25.13", "X_25.14",
                        "X_25.15", "X_25.16", "X_25.17", "X_25.18", "X_25.19",
                        "X_25.20", "X_25.21", "X_25.22", "X_25.23",},
                {"N_26.00", "N_26.01", "N_26.02", "N_26.03", "N_26.04",
                        "N_26.05", "N_26.06", "N_26.07", "N_26.08", "N_26.09",
                        "N_26.10", "N_26.11", "N_26.12", "N_26.13", "N_26.14",
                        "N_26.15", "N_26.16", "N_26.17", "N_26.18", "N_26.19",
                        "N_26.20", "N_26.21", "N_26.22", "N_26.23",}});
    }

    private String levelName;

    private int lev = 0;

    public LevelPellet(String lvlName) {
        super();
        levelName = lvlName;
        setColor(new Color(247, 255, 160));
    }

    public void action(Grid grid) {
//        String[][] map = levels.get(lev);
//        for (int r = 0; r < ROW; r++) {
//            for (int c = 0; c < COL; c++) {
//
//
//                //Walls
//                if (map[r][c].contains("X")) {
//                    Wall w = new Wall();
//                    w.putSelfInGrid(grid, new Location(r, c));
//                }
//
//                if (map[r][c].contains("O")) {
//                    Pellet p = new Pellet();
//                    p.putSelfInGrid(grid, new Location(r, c));
//                }
//
//            }
//        }\
        ArrayList<Location> locs = grid.getOccupiedLocations();
        for (Location loc : locs) {
            if (grid.get(loc) instanceof Pacman || grid.get(
                    loc) instanceof Edible) {
                Actor why = (Actor) (grid.get(loc));
                why.removeSelfFromGrid();
            }
        }

        try {
            File level = new File("Pacman/src/Levels/" + levelName + ".txt");
            String line;
            BufferedReader br = new BufferedReader(new FileReader(level));
            for (int i = 0; i < 24; i++) {
                do {//find next good line
                    line = br.readLine();
                    line = line.replaceAll("[^A-Za-z0-9]", "");//clean up string(remove commas or spaces and stuff)
                } while (line.length() <= 0);
                System.out.println("*" + line);
                for (int j = 0; j < 24; j++) {
                    if (line.charAt(j) == 'x') {
                        Wall w = new Wall();
                        w.putSelfInGrid(grid, new Location(i + 2, j));
                    } else if (line.charAt(j) == 'o') {
                        Pellet p = new Pellet();
                        p.putSelfInGrid(grid, new Location(i + 2, j));
                    }
                }
                //System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("shet");
            System.exit(-1);
        }

        Pacman pacman = new Pacman();
        pacman.putSelfInGrid(grid, new Location(20, 11));
        //Inky inky = new Inky();
        //inky.putSelfInGrid(grid, new Location(10, 8));

    }

}
