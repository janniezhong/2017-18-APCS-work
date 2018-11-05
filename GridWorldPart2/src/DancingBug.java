import info.gridworld.actor.Bug;

public class DancingBug extends Bug {
	
	private int steps;
	private int[] numTurns;
	public DancingBug (int[] turns){
		// TODO Auto-generated constructor stub
		steps = 0;
		numTurns = turns;
	}

	public void act () {
		if (steps == numTurns.length) {
			steps = 0;
		}
		
		if (steps < numTurns.length) {
			int turns = numTurns[steps];
			for (int i = 0; i < turns; i++) {
				turn();
			}
			super.act();
			steps++;
		}
	}
	
}
