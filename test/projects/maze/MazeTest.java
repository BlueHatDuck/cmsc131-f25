package projects.maze;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MazeTest {
    private Maze maze;
    private Cell c;
    private Coords cr;
    private CellStatus status;
    
    @Before
    public void setupMaze() {
        maze = new Maze(100);
        maze.serialize("data/MazeTest-setupMaze.txt");
        // avoid overwriting an input file
    }

    @Test
    public void testGetStart(){
        c = maze.getStart();
        status = CellStatus.S;
        assertEquals(status, c.getStatus());
    }
}
