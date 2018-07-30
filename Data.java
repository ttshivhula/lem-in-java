package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

/*
 * This class reads the data from standard input and stores it in 
 * the correct variables... Probably the worst one to explain
 * bacause the code is dirty ;)
 */

public class Data {
	
	/* We need a linked list of type Vertex(See Vertex.java) to store
	 * all the rooms, we also keep separate variables to store the start
	 * end and the number of ants. Can probably be done better. But if
	 * there is code and it sort of works that's what we go with. Well that
	 * is true for code you don't care about
	 */

	private String line;
	private int start_end = 0; //flag used to check if room is start or end
	private List<String> r = new ArrayList<String>(); //Just a temp list that stores all lines read from Stdin
	private List<Vertex> rooms = new LinkedList<Vertex>();
	private String start = null;
	private String end = null;
	private int ants = -1;
	private int errors = 0;
	
	/* Takes name of the string x and splits by space to get the room name
	 * and add the room to the list and updates the error variable and set
	 * it to 1 if there is an error, if the start_end flag was set then 
	 * we use it to set the room as a starting or end then we resert the
	 * flag
	 */

	private void add_rooms(String x) {
		String str[] = x.split(" ");
		if (str.length == 3) {
			Vertex v = new Vertex(str[0]);
			if (getRoom(str[0]) == null)
				rooms.add(v);
			else
				errors = 1;
			if (start_end == 1)
				start = str[0];
			if (start_end == 2)
				end = str[0];
			start_end = 0;
		}
	}
	
	/* Takes name of a rooms and loops through all the rooms and returns
	 * the room that matches the name... else return null
	 */

	private Vertex getRoom(String name) {
		for (Vertex x : rooms) {
			if (x.getName().equals(name))
				return x;
		}
		return (null);
	}
	
	/* Connects two rooms together, takes string x and splits it by 
	 * - and get the proper rooms using getRoom and then connect the rooms
	 * using the addNeigbour(See Vertex.java). but also checks if there is
	 * errors if there is then the errors variable is set to 1, damn i should
	 * have just made errors a boolean.
	 */

	private void connect_rooms(String x) {
		String str[] = x.split("-");
		if (str.length == 2) {
			Vertex v1 = getRoom(str[0]);
			Vertex v2 = getRoom(str[1]);
			if ((v1 == null) || (v2 == null))
				errors = 1;
			v2.addNeigbour(v1);
			v1.addNeigbour(v2);
		}
	}

	/* This loops through all the lines in the list (r) and
	 * uses the data to set the number of ants, start, end rooms, add
	 * rooms and connect rooms... Straight forward. CS101
	 */

	private void extract_info() {
		
		for (String x : r) {
			if (ants <= 0)
				ants = Integer.valueOf(x);
			else if (x.equals("##start"))
				start_end = 1;
			else if (x.equals("##end"))
				start_end = 2;
			else {
				add_rooms(x);
				connect_rooms(x);
			}
		}
	}
	
	/*
	 * Reads the data from the standard input using BafferefReader and replaces
	 * all the comments using replaceAll(Built in), using C would have been fun though
	 * every line that is read and after replacing the comments has a length greater than
	 * 0 is added to the list(r). We always try and catch the error and print
	 * Error provided there is an error. after all this not so interesting stuff
	 * we call extrac_info. Extract info will extract all the useful information
	 */

	public void readData() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				if (!(line.charAt(0) == '#' && line.charAt(1) == '#'))
					line = line.replaceAll(" *(#.*(?=\\n|$))", "");
				if (line.length() > 0)
					r.add(line);
			}
		} catch (IOException e) {
			System.out.println("\nError");
		}
		extract_info();
		/*
		 * We pass the start room getRoom(start) Vertex, name of the 
		 * end room, number of ants and the errors variable
		 * Move will try to solve the path using Breadth First Search 
		 * provided we dont have errors.
		 */
		Move solve = new Move(getRoom(start), end, ants, errors);
		/*
		 * Provided there is a valid path(solution) print_path prints
		 * the path else prints error. (See Move.java)
		 */
		solve.print_path();
	}
}
