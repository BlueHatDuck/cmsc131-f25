package projects.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CellTest {
    private Cell cell;
    private Coords coords;
    private CellStatus status;

    @BeforeEach
    void setup(){
        coords = new Coords(0, 0);
        status = CellStatus.S;
        cell = new Cell(coords, status);
    }

    @Test
    public void invalidStatus(){
        Exception e = assertThrows(
        IllegalArgumentException.class, 
        () -> {new Cell(coords, null);}
        );
        assertEquals(
            "Status must be either S, E, O, or P!",
            e.getMessage()
        );
    }

    @Test
    public void testSetStatus(){
        status = CellStatus.E;
        cell.setStatus(status);
        assertEquals(CellStatus.E, cell.getStatus());
    }

    @Test
    public void testSetNeighbors(){
        cell.setNeighbors();
        Coords[] cord = cell.getNeighbors();
        int n1row = cord[2].getRow();
        int n1col = cord[2].getCol();
        int n2row = cord[3].getRow();
        int n2col = cord[3].getCol();

        assertEquals(n1col, coords.getCol());
        assertEquals(n1row, coords.getRow() + 1);
        assertEquals(n2col, coords.getCol() + 1);
        assertEquals(n2row, coords.getRow());
    }

}

