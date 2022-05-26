
public class Solver {
	private Board board;
	//turns the inputboard list of ints into the real board
	//class and initializes the solver
	Solver(int[][] inputboard){
		Box[][] boxboard = new Box[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boxboard[i][j] = new Box(inputboard[i][j], i, j);
			}
		}
		Box[][] board_data = new Box[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board_data[i][j] = boxboard[i][j];
			}
		}
		Board board = new Board(board_data);
		this.board = board;
	}
	
	//Attempts to solve the board. Will start with
	//the easy steps and work its way to the harder
	//steps. Will stop once all steps can no longer
	//make any changes to the board.
	public void solve(boolean recursion) {
		board.printboard();
		while(true) {
			if(board.step1()) {
				System.out.println("Step 1");
			}
			if(board.step2()) {
				System.out.println("Step 2");
				continue;
			}
			else {
				if(board.step3()) {
					System.out.println("Step 3");
					continue;
				}
				else {
					if(board.step4()) {
						System.out.println("Step 4");
						continue;
					}
					else {
						break;
					}
				}
			}
		}
		board.printboard();
		//if recursion is set to true after
		//using the steps program will attempt
		//to recursively brute force solve 
		//the rest of the board.
		if(recursion) {
			System.out.println("Recursive Brute Force Starting");
			if(!board.recursivebruteforce()) {
				System.out.println("Invalid Board");
				return;
			}
			board.printboard();
		}
		return;
	}
	
	public static void main(String[] args) {
		boolean RecursiveBruteForce = true;
        int[][] InputBoard = {
                {6,0,0,0,0,0,0,0,9},
                {0,7,0,4,1,8,0,2,0},
                {0,8,2,0,0,0,7,4,0},
                {0,0,0,3,0,7,0,0,0},
                {0,0,0,0,5,0,0,0,0},
                {0,0,0,2,0,4,0,0,0},
                {0,6,7,0,0,0,8,5,0},
                {0,4,0,5,8,6,0,3,0},
                {9,0,0,0,0,0,0,0,4}};
		Solver solver = new Solver(InputBoard);
		solver.solve(RecursiveBruteForce);
		return;
	}
}