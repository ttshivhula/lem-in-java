package bfs;

import java.util.ArrayList;
import java.util.List;

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
