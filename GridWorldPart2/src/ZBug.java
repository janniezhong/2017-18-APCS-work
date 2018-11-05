import info.gridworld.actor.Bug;

public class ZBug extends Bug{

	private int steps;
	private int sideLength;
	
	public ZBug(int length) {
		// TODO Auto-generated constructor stub
		//super();
		steps = 0;
		sideLength = length;
		turn();
		turn();
		
	}
	
	public void act()
    {
//        if (steps < sideLength && canMove())
//        {
//            move();
//            steps++;
//        }
//        else if (steps == sideLength){
//        	move();
//        	for (int i = 0; i < 3; i++) {
//        		turn();
//        	}
//        	steps++;
//        } 
//        
//        
//        else {
//            turn();
//            turn();
//            steps = 0;
//        }
		
		
		
		if (!canMove()) {
			
		} else {
			if (steps < sideLength) {
				move();
				steps++;
			} else if (steps == sideLength) {
			 	for (int i = 0; i < 3; i++) {
	        		turn();
	        	}
			 	steps++;
			} else if (steps > sideLength && steps < 2*sideLength + 1) {
				move();
				steps++;
			} else if (steps == 2*sideLength +1) {
				for (int i = 0; i < 5; i++) {
	        		turn();
	        	}
				steps++;
			} else if (steps > 2*sideLength +1 && steps < 3*sideLength + 2) {
				move();
				steps++;
			} else {
			
			}
		}
    }
	
	

}
