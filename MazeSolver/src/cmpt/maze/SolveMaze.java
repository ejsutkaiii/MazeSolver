package cmpt.maze;
/*Authored By Edward Sutka
 * May 2nd, 2014
 * Software Dev I
 * Programming Assignment 3
 */

public class SolveMaze {

	public static void main(String[] args) {
		MazeCalculator results = new MazeCalculator(); // creates new maze calculator object
		String[][] unsolvedMaze = results.mazeRetriaval(args[0]); // creates maze and populates maze with maze retrieval method
		results.mazePrint(unsolvedMaze); // prints unsolved maze
		
		System.out.println("");
		System.out.println("Solving Maze...");
		
	
		int[] start = results.startFinder(); // finds maze start and stores ints into an array consisting of a size of 2
		results.mazeSolver(start[0],start[1]); // puts starting coords into mazeSolver method
		results.results(); // returns results
		
	
		results.mazePrint(unsolvedMaze); // prints solved maze
		
		
		
	}
}