package projects.maze;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testPathFound(){
        maze.solve();
        Cell[] solvedMaze = maze.getAllCells();
        for (Cell cell : solvedMaze) {
            System.out.println(cell.getStatus());
        }
        assertEquals(solvedMaze[0].getStatus(), CellStatus.S);
        assertEquals(solvedMaze[1].getStatus(), CellStatus.P);
        assertEquals(solvedMaze[2].getStatus(), CellStatus.P);
        assertEquals(solvedMaze[3].getStatus(), CellStatus.E);
        assertEquals(solvedMaze[4].getStatus(), CellStatus.O);
    }
}