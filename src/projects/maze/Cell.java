/** `Cell` class additions

Extra attributes

- `neighbors` (Coords[]) - coordinates of neighbors
    - needs accessor/mutator

- `explored` (boolean) - traversal flag (defaults to false)
    - needs accessor/mutator

- `status` (CellStatus enum) - cell's role/state
    - Enum values    
        - `S` maze entrance
        - `E` maze exit  
        - `O` open cell
        - `P` part of solution path
    - Needs accessor/mutator

A cell will be constructed with a coordinates and a status. Decide for yourself what are sensible default values (if any) for the other attributes.
 */

package projects.maze;

public class Cell {

    private final Coords coords;
    private CellStatus status;
    private Coords[] neighbors;
    private boolean explored;

    public Cell(Coords c, CellStatus s) {
        coords = c;
        setStatus(s);
        setNeighbors();
        explored = false;
    }

    public Coords getCoords() {
        return coords;
    }

    public CellStatus getStatus(){
        return status;
    }

    public void setStatus(CellStatus st){
        String s = String.valueOf(st);
        if(s.toUpperCase().equals("S") || s.toUpperCase().equals("E") || 
        s.toUpperCase().equals("O") || s.toUpperCase().equals("P")){
            status = st;
        } else {
            throw new IllegalArgumentException("Status must be either S, E, O, or P!");
        }
    }

    public boolean getExplored(){
        return explored;
    }

    public void setExplored(boolean torf){
        explored = torf;
    }

    public Coords[] getNeighbors(){
        return neighbors;
    }

    public void setNeighbors(){

        Coords south = new Coords(coords.getRow() + 1, coords.getCol());
        Coords east = new Coords(coords.getRow(), coords.getCol() - 1);

        if(coords.getCol() != 0 && coords.getRow() != 0){
            Coords north = new Coords(coords.getRow() - 1, coords.getCol());
            Coords west = new Coords(coords.getRow(), coords.getCol() - 1);
            neighbors = new Coords[4];
            neighbors[0] = north;
            neighbors[1] = south;
            neighbors[2] = west;
            neighbors[3] = east;
        } else {
            neighbors = new Coords[2];
            neighbors[0] = south;
            neighbors[1] = east;
        }
    }

}