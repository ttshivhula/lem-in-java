package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

public class Data {
	private String line;
	private int start_end = 0;
	private List<String> r = new ArrayList<String>();
	private List<Vertex> rooms = new LinkedList<Vertex>();
	private String start = null;
	private String end = null;
	private int ants = -1;
	private int errors = 0;
	
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
	
	private Vertex getRoom(String name) {
		for (Vertex x : rooms) {
			if (x.getName().equals(name))
				return x;
		}
		return (null);
	}
	
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
		Move solve = new Move(getRoom(start), end, ants, errors);
		solve.print_path();
	}
}