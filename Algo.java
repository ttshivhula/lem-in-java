package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Algo {
	protected String end;
	private Vertex start;
	private List<String> path;

	public Algo(Vertex start, String end) {
		this.start = start;
		this.end = end;
		this.path = new ArrayList<>();
	}
	
	private void create_path(Vertex current, String start) {

		while (!current.getName().equals(start)) {
			path.add(0, current.getName());
			current = current.getParent().remove(0);
		}
	}
	
	public void bfs_search() {
		
		Queue<Vertex> queue = new LinkedList<>();
		start.setVisited(true);
		queue.add(start);
		while (!queue.isEmpty()) {
			Vertex data = queue.remove();
			if (data.getName() == end) {
				create_path(data, start.getName());
				break ;
			}
			for (Vertex x : data.getNeigbours()) {
				if (!x.isVisited()) {
					x.setVisited(true);
					x.setParent(data);
					queue.add(x);
				}
			}
		}
	}
	
	public List<String> getPath() {
		return path;
	}	
}
