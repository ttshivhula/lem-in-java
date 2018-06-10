package bfs;

public class Move extends Algo {

	private int ants;
	private int errors;
	public Move(Vertex start, String end, int num_ants, int errors) {
		super(start, end);
		this.errors = errors;
		this.ants = num_ants;
	}
	
	private int move_ant(int ant, int b) {
		
		int print = b - ant;
		if ((b >= ant - 1)) 
		{
			for (String x : getPath()) {
				if (print <= 0)
					break ;
				if (print == 1) {
					System.out.print("L" + (ant + 1) +"-" + x + " ");
					if ((ant + 1 == ants) && x.equals(end))
						return 1;
				}
				print--;
			}
		}
		return 0;
	}
	
	public void	print_path() {
		
		int f = 1;
		int done = 0;
		bfs_search();
		System.out.println();
		if (!getPath().isEmpty() && errors == 0) {
			while (done != 1) {
				int i = -1;
				while (++i < ants)
					done = move_ant(i, f);
				System.out.println();
				f++;
				}
			}
		else
			System.out.println("Error");
		}
}
