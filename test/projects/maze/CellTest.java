package projects.maze;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
    void testConstructor(){
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Cell(null, status);}
        );
        // assertEquals(compare messages)
        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Cell(coords, null);}
        );
    }

    @Test
    void constructorDataValidation() {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Cell(null, CellStatus.S);}
        );
        assertEquals(
            e.getMessage(),
            "Parameter c cannot be null"
        );
        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Cell(new Coords(0, 0), null);}
        );
        assertEquals(
            e.getMessage(),
            "Parameter s cannot be null"
        );
    }
}