package projects.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class CellTest {
    private Cell cell;
    private Coords coords;
    private CellStatus status;

    @BeforeEach
    void setup(){
        String s = "S";
        status = CellStatus.valueOf(s);
        coords = new Coords(0, 0);
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
}
