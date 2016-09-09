package cmpt.maze;
import java.util.*;
/*Authored By Edward Sutka
 * May 2nd, 2014
 * Software Dev I
 * Programming Assignment 3
 */

public class MazeCalculator {
	
		private int rows; //maze rows
		private int columns; //maze columns 
		private ArrayList<String> maze; // maze
		private String[][]unsolvedMaze; //unsolved maze 2D char representation
		private String results = " "; // for results printer method 
		

	public String[][] mazeRetriaval(String argument){ //mazeRetriaval method returns 2D char Array of maze
		
		MazeReader mazeBuilder = new MazeReader();// imports file reader
		mazeBuilder.openFile(argument); //opens file from cmd arg
		maze = mazeBuilder.readFile(); // place ArrayString from file to new string array
		
		columns = Integer.parseInt(maze.get(0)) ; //gets rows
		rows = Integer.parseInt(maze.get(1)) ; //gets columns
		System.out.println(columns);
		System.out.println(rows);
		unsolvedMaze = new String[rows][columns]; //creates a 2D char array of a valid size
		
		ArrayList<String> mazeholder = new ArrayList<String>(); // creates maze with out the rows and columns
		for(int a = 2; a < maze.size(); a++){
			mazeholder.add(maze.get(a));
		}
		
		
		for(int x = 0; x < rows; x++){ //loops through columns and rows placing every chat into the right spot in the 2D array
				for(int y = 0; y < columns ; y++){
					unsolvedMaze[x][y] = String.valueOf(mazeholder.get(x).charAt(y));
				}
		}
		return unsolvedMaze;
	}
	
	public void mazePrint(String[][] maze){ //prints mazes from char Arrays
		for(int x = 0; x < rows; x++){
			for(int y = 0; y < columns; y++){
				System.out.print(maze[x][y]);
			}
			System.out.println("");
		}	
	}
	
	public int[] startFinder(){ //stores starting columns in constructor
		int[] start = new int[2];
		for(int x = 0; x < rows; x++){
			for(int y = 0; y < columns; y++){
				if(unsolvedMaze[x][y].equals("S")){
					start[0] = x;
					start[1] = y;
				}
			}
		}
		return start;
	}
	
	
	
	public boolean mazeSolver(int row, int column){ // maze solving method
		
		if(unsolvedMaze[row][column].equals("G")){ // if goal is found end method
			results = "solved";
			return true;
		}

		
		if(row > 0 && unsolvedMaze[row-1][column].equals("G")){ // checks for goal
			row = row -1;
			mazeSolver(row, column);
			return true;
		}
		else if(column != columns && unsolvedMaze[row][column + 1].equals("G")){
			column = column +1;
			mazeSolver(row, column);
			return true;
		}
		else if(row != rows && unsolvedMaze[row+1][column].equals("G")){
			row = row +1;
			mazeSolver(row, column);
			return true;
		}
		else if(column > 0 && unsolvedMaze[row][column-1].equals("G")){
			column = column -1;
			mazeSolver(row, column);
			return true;	
		}	
	
		
		// next 4 check north east south and west for open spots then fills them with p according to order
	if(row > 0 && unsolvedMaze[row-1][column].equals(".")){ 
		row = row -1 ;
		unsolvedMaze[row][column] = "p";
		mazeSolver(row,column);
		return false;
	}
		else if(column != columns && unsolvedMaze[row][column + 1].equals(".")){
			column = column + 1;
			unsolvedMaze[row][column]= "p";
			mazeSolver(row,column);
			return true;
	}
		else if(row != rows && unsolvedMaze[row+1][column].equals(".")){
		row = row +1 ; 
		unsolvedMaze[row][column]= "p";
		mazeSolver(row,column);
		return true;
	}
		else if(column > 0 && unsolvedMaze[row][column -1].equals(".")){
		column = column - 1; 
		unsolvedMaze[row][column] = "p";
		mazeSolver(row,column);
		return true;
	}
	
	
	// next 4 check north east south and west for p spots then fills them with v according to order
	else if(row > 0 && unsolvedMaze[row-1][column].equals("p")){
		unsolvedMaze[row][column]= "v";
		row = row -1;
		mazeSolver(row,column);
	}
	else if( column != columns && unsolvedMaze[row][column + 1].equals("p")){
		unsolvedMaze[row][column] = "v";
		column = column +1 ;
		mazeSolver(row, column);	
	}
	else if(row != rows && unsolvedMaze[row+1][column].equals("p")){
		unsolvedMaze[row][column]= "v";
		row = row +1;
		mazeSolver(row, column);
	}
	
	else if(column > 0 && unsolvedMaze[row][column-1].equals("p")){
		unsolvedMaze[row][column]= "v";
		column = column -1;
		mazeSolver(row, column);	
	}
	
	
	// Checks to see if the path has returned to the start because there are no more solutions
	else if(row > 0 && unsolvedMaze[row-1][column].equals("S")){
		unsolvedMaze[row][column]= "v";
		return false;
	}
	else if(column < columns && unsolvedMaze[row][column + 1].equals("S")){
		unsolvedMaze[row][column]= "v";
		return false;
		
	}
	else if(column != columns && unsolvedMaze[row+1][column].equals("S")){
		unsolvedMaze[row][column]= "v";
		return false;
	}
	else if(row != rows && column > 0 && unsolvedMaze[row][column-1].equals("S")){
		unsolvedMaze[row][column]= "v";
		return false;
	}
	else{
		return false;
	}
	return false;
	}	
	
	public void results(){ // results printer method 
		if( results.equals("solved")){
			System.out.println("The Maze was successfully solved!");
		}
		else{
			System.out.println("The Maze has no solution");
		}
	}
}

