
public abstract class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Maze grid = new Maze("test3.txt");
		System.out.println(grid);
		
		
		//System.out.println(grid.isAlone(2,3));
		
		boolean b = grid.solveMaze();
		//System.out.println(b);
		System.out.println(grid);

		
		
	}

}
