package projects.maze;

public class Main {

    static void phase1() {
        Maze maze = MazeReader.load( "data/sample_maze.txt" );
        System.out.println("Maze successfully loaded!");
        maze.serialize("data/sample_maze_phase1.txt");
    }

    private static void checkSolve(String filename, String descr) {
        Maze maze = MazeReader.load(filename);
        if (maze.solve()) {
            System.out.println("Solved " + descr);
            maze.serialize("data/checkSolve.txt");
        } else {
            System.out.println("No solution to " + descr);
        }
    }

    static void phase2() {
        checkSolve("data/hard_maze.txt", "hard maze");
        checkSolve("data/hard_maze_nosol.txt", "hard maze (nosol)");
    }

    public static void main(String[] args) {
        phase1();
        phase2();
    }

}