package bfs;

import java.util.ArrayList;
import java.util.List;

/*
 * The Vertex class is used as a data structure all the necesary variables
 * together with their setters and getters are defined in this class
 * name is the room name, visited is a boolean we use to check if the room
 * is visited, neigbours is a list of type Vertex because all the neigbours have
 * all the properties of the Vertex class. we can define neigbours as rooms 
 * connected to a room. 
 */

public class Vertex {
	private String name;
	private boolean visited;
	private List<Vertex> neigbours;
	private List<Vertex> parent;
	
	public Vertex(String name) {
		this.name = name;
		this.neigbours = new ArrayList<>();
		this.parent = new ArrayList<>();
	}
	public List<Vertex> getParent() {
		return parent;
	}
	public void setParent(Vertex parent) {
		this.parent.add(parent);
	}
	public void addNeigbour(Vertex vertex) {
		this.neigbours.add(vertex);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public List<Vertex> getNeigbours() {
		return neigbours;
	}
	
}
