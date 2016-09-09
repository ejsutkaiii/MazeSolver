package cmpt.maze;

import java.util.*;
import java.io.*;

public class MazeReader {
	private Scanner x;
	
		public void openFile(String arguement){
			try{
					x = new Scanner(new File(arguement));
			}
			catch(Exception e){
				System.out.println("Could not find file");
			}
		}
	
	public ArrayList<String> readFile(){
		
		ArrayList<String> results = new ArrayList<String>();
		while(x.hasNext()){
			String a = x.next();
			results.add(a);
		}
		return results;
	}
	
	public void closeFile(){
		x.close();
	}
	
	
	
	

}
