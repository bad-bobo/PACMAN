package project;

import java.util.ArrayList;


/**
 * Creates an Adjacency matrix for a given matrix with nodes numbered >0  and empty location < 0
 */
public class CreateAdjacencyMatrix
{
    public static void main( String[] args )
    {
        int[][] map = Mechanics.loadFile( "NodeMatrix_level1", Main.ROW, Main.COL, "\t" );
        int numNodes = getNumNodes( map );
        int[][] adjMatrix = new int[numNodes][numNodes];

//        System.out.println( getNeighbors( map, 235 ) );

        for ( int r = 0; r < map.length; r++ )
        {
            for ( int c = 0; c < map[0].length; c++ )
            {
                int num = map[r][c];
                if ( num >= 0 )
                {
                    ArrayList<Integer> neighbors = getNeighbors( map, num );

                    for(int neighbor: neighbors)
                    {
                        adjMatrix[num][neighbor] = 1;
                    }

                }
            }
        }

        Mechanics.print2DArray( adjMatrix, "" );

    }


    /**
     * Gets the neighbors ( up, down, left, right) of a number in the arr( if the neighbors are positive)
     * @param arr a 2d array
     * @param source the number to get the neighbors of
     * @return an ArrayList<Integer> of neighbors
     */
    public static ArrayList<Integer> getNeighbors( int[][] arr, int source )
    {
        ArrayList<Integer> neighbors = new ArrayList<>( 4 );

        for ( int r = 0; r < arr.length; r++ )
        {
            for ( int c = 0; c < arr[0].length; c++ )
            {

                if ( arr[r][c] == source )
                {

                    //Up
                    if ( ( r - 1 ) >= 0 && arr[r - 1][c] >= 0 )
                    {
                        neighbors.add( arr[r - 1][c] );
                    }

                    //down
                    if ( ( r + 1 ) < arr.length && arr[r + 1][c] >= 0 )
                    {
                        neighbors.add( arr[r + 1][c] );
                    }

                    //Left
                    if ( ( c - 1 ) >= 0 && arr[r][c - 1] >= 0 )
                    {
                        neighbors.add( arr[r][c - 1] );
                    }

                    //Right
                    if ( ( c + 1 ) < arr[0].length && arr[r][c + 1] >= 0 )
                    {
                        neighbors.add( arr[r][c + 1] );
                    }
                }
            }
        }
        return neighbors;

    }


    /**
     * Gets the number of nodes in an arr. A node is a positive number
     * @param arr
     * @return
     */
    public static int getNumNodes( int[][] arr )
    {
        int nodeCount = 0;
        for ( int r = 0; r < arr.length; r++ )
        {
            for ( int c = 0; c < arr[0].length; c++ )
            {
                if ( arr[r][c] >= 0 )
                {
                    nodeCount++;
                }
            }
        }
        return nodeCount;
    }
}
