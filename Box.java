import java.util.ArrayList;

public class Box {
	private ArrayList<Integer> possibilities;
	private int index1;
	private int index2;
	
	//Initializes the possibilities for a box and the index
	public Box(int x, int index1, int index2) {
		this.possibilities = new ArrayList<Integer>();
		if(x == 0) {
			for(int i = 1; i < 10; i++) {
				this.possibilities.add(i);
			}
		}
		else {
			this.possibilities.add(x);
		}
		this.index1 = index1;
		this.index2 = index2;
	}
	
	//this method is just for debugging, sets up a new box with
	//a specific set of possibilities
	public Box(int[] x) {
		this.possibilities = new ArrayList<Integer>();
		for(int i = 0; i < x.length; i++) {
			this.possibilities.add(x[i]);
		}
	}

	public ArrayList<Integer> getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(ArrayList<Integer> possibilities) {
		this.possibilities = possibilities;
	}
	
	public void setPossibilities(int x) {
		this.possibilities = new ArrayList<Integer>();
		this.possibilities.add(x);
	}

	public int getIndex1() {
		return index1;
	}

	public int getIndex2() {
		return index2;
	}
	
}
