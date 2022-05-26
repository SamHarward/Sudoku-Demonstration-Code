import java.util.ArrayList;


public class Board {
	private Box[][] board_data;
	
	public Board(Box[][] board_data) {
		this.board_data = board_data;
	}
	
	//here for dubugging, please ignore
	public void debugger() {
		Box[] temp = getssrn2(board_data[1][8]);
		Box[] temp2 = getssrn3(board_data[1][8]);
		for(int i = 0; i < temp.length; i++) {
			System.out.println(temp[i].getPossibilities());
		}
		for(int i = 0; i < temp2.length; i++) {
			System.out.println(temp2[i].getPossibilities());
		}
	}
	
	//prints the board
	public void printboard() {
		System.out.println("-------------------------------------");
		String a = " ";
		String b = " | ";
		String c = " |";
		String print = "| ";
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board_data[i][j].getPossibilities().size() > 1) {
					print = print + a;
				}
				else {
					print = print + board_data[i][j].getPossibilities().get(0);
				}
				if(j == 8) {
					print = print + c;
				}
				else {
					print = print + b;					
				}
			}
			System.out.println(print);
			System.out.println("-------------------------------------");
			print = "| ";
		}
		
	}

	//prints the board in the form of a list of box possibilities
	public void printboard2() {
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j++) {
				System.out.println("cell number " + (i+1) + (j+1) + ": " + board_data[i][j].getPossibilities());
			}
		}
	}
	
	
	
	//Helper Functions
	
	//returns row neighbors including box x
	private Box[] getrowneighbors(Box x) {
		Box[] returnlist = new Box[9];
		int row_index = x.getIndex1();
		for(int i = 0; i < 9; i++) {
			returnlist[i] = board_data[row_index][i];
		}
		return returnlist;
	}
	
	//returns row neighbors not including box x
	private Box[] getrowneighbors2(Box x) {
		Box[] returnlist = new Box[8];
		int row_index = x.getIndex1();
		for(int i1 = 0, i2 = 0; i1 < 9; i1++, i2++) {
			if(i1 == x.getIndex2()) {
				i2--;
				continue;
			}
			returnlist[i2] = board_data[row_index][i1];
		}
		return returnlist;
	}
	
	//returns column neighbors including box x
	private Box[] getcolumnneighbors(Box x) {
		Box[] returnlist = new Box[9];
		int column_index = x.getIndex2();
		for(int i = 0; i < 9; i++) {
			returnlist[i] = board_data[i][column_index];
		}
		return returnlist;
	}
	
	//returns column neighbors not including box x
	private Box[] getcolumnneighbors2(Box x) {
		Box[] returnlist = new Box[8];
		int column_index = x.getIndex2();
		for(int i1 = 0, i2 = 0; i1 < 9; i1++, i2++) {
			if(i1 == x.getIndex1()) {
				i2--;
				continue;
			}
			returnlist[i2] = board_data[i1][column_index];
		}
		return returnlist;
	}
	
	//returns square neighbors including box x
	private Box[] getsquareneighbors(Box x) {
		Box[] returnlist = new Box[9];
		int row_index = x.getIndex1();
		int column_index = x.getIndex2();
		int boxindex1 = (row_index/3)*3;
		int boxindex2 = (column_index/3)*3;
		for (int i1 = boxindex1, i2 = 0; i1 < boxindex1+3; i1++) {
			for (int j = boxindex2; j < boxindex2+3; j++, i2++) {
				returnlist[i2] = board_data[i1][j];
			}
		}
		return returnlist;
	}
	
	//returns square neighbors not including box x
	private Box[] getsquareneighbors2(Box x) {
		Box[] returnlist = new Box[8];
		int row_index = x.getIndex1();
		int column_index = x.getIndex2();
		int boxindex1 = (row_index/3)*3;
		int boxindex2 = (column_index/3)*3;
		for (int i1 = boxindex1, i2 = 0; i1 < boxindex1+3; i1++) {
			for (int j = boxindex2; j < boxindex2+3; j++, i2++) {
				if(i1 == row_index && j == column_index) {
					i2--;
					continue;
				}
				returnlist[i2] = board_data[i1][j];
			}
		}
		return returnlist;
	}
	
	//used in step 3, returns the shared boxes between
	//x's row and square
	private Box[] getssrn1(Box x) {
		Box[] rowneighbors = getrowneighbors(x);
		Box[] outputlist = new Box[3];
		int column_index = x.getIndex2();
		if(column_index <= 2) {
			outputlist[0] = rowneighbors[0];
			outputlist[1] = rowneighbors[1];
			outputlist[2] = rowneighbors[2];
		}
		else if(column_index <= 5) {
			outputlist[0] = rowneighbors[3];
			outputlist[1] = rowneighbors[4];
			outputlist[2] = rowneighbors[5];
		}
		else if(column_index <= 8) {
			outputlist[0] = rowneighbors[6];
			outputlist[1] = rowneighbors[7];
			outputlist[2] = rowneighbors[8];
		}
		return outputlist;
	}
	
	//used in step 3, returns the boxes in x's
	//square but not x's row
	private Box[] getssrn2(Box x) {
		Box[] squareneighbors = getsquareneighbors(x);
		Box[] outputlist = new Box[6];
		int row_index = x.getIndex1();
		if(row_index % 3 == 0) {
			outputlist[0] = squareneighbors[3];
			outputlist[1] = squareneighbors[4];
			outputlist[2] = squareneighbors[5];
			outputlist[3] = squareneighbors[6];
			outputlist[4] = squareneighbors[7];
			outputlist[5] = squareneighbors[8];
		}
		else if(row_index % 3 == 1) {
			outputlist[0] = squareneighbors[0];
			outputlist[1] = squareneighbors[1];
			outputlist[2] = squareneighbors[2];
			outputlist[3] = squareneighbors[6];
			outputlist[4] = squareneighbors[7];
			outputlist[5] = squareneighbors[8];
		}
		else if(row_index % 3 == 2) {
			outputlist[0] = squareneighbors[0];
			outputlist[1] = squareneighbors[1];
			outputlist[2] = squareneighbors[2];
			outputlist[3] = squareneighbors[3];
			outputlist[4] = squareneighbors[4];
			outputlist[5] = squareneighbors[5];
		}
		return outputlist;
	}
	
	//used in step 3, returns the boxes in x's
	//row but not x's square
	private Box[] getssrn3(Box x) {
		Box[] rowneighbors = getrowneighbors(x);
		Box[] outputlist = new Box[6];
		int column_index = x.getIndex2();
		if(column_index <= 2) {
			outputlist[0] = rowneighbors[3];
			outputlist[1] = rowneighbors[4];
			outputlist[2] = rowneighbors[5];
			outputlist[3] = rowneighbors[6];
			outputlist[4] = rowneighbors[7];
			outputlist[5] = rowneighbors[8];
		}
		else if(column_index <= 5) {
			outputlist[0] = rowneighbors[0];
			outputlist[1] = rowneighbors[1];
			outputlist[2] = rowneighbors[2];
			outputlist[3] = rowneighbors[6];
			outputlist[4] = rowneighbors[7];
			outputlist[5] = rowneighbors[8];
		}
		else if(column_index <= 8) {
			outputlist[0] = rowneighbors[0];
			outputlist[1] = rowneighbors[1];
			outputlist[2] = rowneighbors[2];
			outputlist[3] = rowneighbors[3];
			outputlist[4] = rowneighbors[4];
			outputlist[5] = rowneighbors[5];
		}
		return outputlist;
	}
	
	//used in step 3, returns the shared boxes between
	//x's column and square
	private Box[] getsscn1(Box x) {
		Box[] columnneighbors = getcolumnneighbors(x);
		Box[] outputlist = new Box[3];
		int row_index = x.getIndex1();
		if(row_index <= 2) {
			outputlist[0] = columnneighbors[0];
			outputlist[1] = columnneighbors[1];
			outputlist[2] = columnneighbors[2];
		}
		else if(row_index <= 5) {
			outputlist[0] = columnneighbors[3];
			outputlist[1] = columnneighbors[4];
			outputlist[2] = columnneighbors[5];
		}
		else if(row_index <= 8) {
			outputlist[0] = columnneighbors[6];
			outputlist[1] = columnneighbors[7];
			outputlist[2] = columnneighbors[8];
		}
		return outputlist;
	}
	
	//used in step 3, returns the boxes in x's
	//square but not x's column
	private Box[] getsscn2(Box x) {
		Box[] squareneighbors = getsquareneighbors(x);
		Box[] outputlist = new Box[6];
		int column_index = x.getIndex2();
		if(column_index % 3 == 0) {
			outputlist[0] = squareneighbors[1];
			outputlist[1] = squareneighbors[4];
			outputlist[2] = squareneighbors[7];
			outputlist[3] = squareneighbors[2];
			outputlist[4] = squareneighbors[5];
			outputlist[5] = squareneighbors[8];
		}
		else if(column_index % 3 == 1) {
			outputlist[0] = squareneighbors[0];
			outputlist[1] = squareneighbors[3];
			outputlist[2] = squareneighbors[6];
			outputlist[3] = squareneighbors[2];
			outputlist[4] = squareneighbors[5];
			outputlist[5] = squareneighbors[8];
		}
		else if(column_index % 3 == 2) {
			outputlist[0] = squareneighbors[0];
			outputlist[1] = squareneighbors[3];
			outputlist[2] = squareneighbors[6];
			outputlist[3] = squareneighbors[1];
			outputlist[4] = squareneighbors[4];
			outputlist[5] = squareneighbors[7];
		}
		return outputlist;
	}
	
	//used in step 3, returns the boxes in x's
	//column but not x's square
	private Box[] getsscn3(Box x) {
		Box[] columnneighbors = getcolumnneighbors(x);
		Box[] outputlist = new Box[6];
		int row_index = x.getIndex1();
		if(row_index <= 2) {
			outputlist[0] = columnneighbors[3];
			outputlist[1] = columnneighbors[4];
			outputlist[2] = columnneighbors[5];
			outputlist[3] = columnneighbors[6];
			outputlist[4] = columnneighbors[7];
			outputlist[5] = columnneighbors[8];
		}
		else if(row_index <= 5) {
			outputlist[0] = columnneighbors[0];
			outputlist[1] = columnneighbors[1];
			outputlist[2] = columnneighbors[2];
			outputlist[3] = columnneighbors[6];
			outputlist[4] = columnneighbors[7];
			outputlist[5] = columnneighbors[8];
		}
		else if(row_index <= 8) {
			outputlist[0] = columnneighbors[0];
			outputlist[1] = columnneighbors[1];
			outputlist[2] = columnneighbors[2];
			outputlist[3] = columnneighbors[3];
			outputlist[4] = columnneighbors[4];
			outputlist[5] = columnneighbors[5];
		}
		return outputlist;
	}
	
	//helper function for recursive brute force solve
	//checks if box x is a valid box placement
	private boolean isvalidplacement(Box x) {
		Box[] neighbors = getrowneighbors2(x);
		for(int i = 0; i < neighbors.length; i++) {
			if(neighbors[i].getPossibilities().size()== 1 && x.getPossibilities().get(0) == neighbors[i].getPossibilities().get(0)) {
				return false;
			}
		}
		neighbors = getcolumnneighbors2(x);
		for(int i = 0; i < neighbors.length; i++) {
			if(neighbors[i].getPossibilities().size()== 1 && x.getPossibilities().get(0) == neighbors[i].getPossibilities().get(0)) {
				return false;
			}
		}
		neighbors = getsquareneighbors2(x);
		for(int i = 0; i < neighbors.length; i++) {
			if(neighbors[i].getPossibilities().size()== 1 && x.getPossibilities().get(0) == neighbors[i].getPossibilities().get(0)) {
				return false;
			}
		}
		return true;
	}
		
	//Board Solving steps
	
	//Very simple step, for every box that has a single possibility,
	//this step will go through and remove that possibility from all
	//of that box's row, column, and square neighbors
	public boolean step1() {
		Box[] neighborlist;
		int magicnumber;
		int index;
		boolean didsomething = false;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board_data[i][j].getPossibilities().size() == 1) {
					magicnumber = board_data[i][j].getPossibilities().get(0);
					//row neighbor removal
					neighborlist = getrowneighbors(board_data[i][j]);
					for(int k = 0; k < 9; k++) {
						if(neighborlist[k].getPossibilities().contains(magicnumber) && neighborlist[k].getPossibilities().size() != 1) {
							index = neighborlist[k].getPossibilities().indexOf(magicnumber);
							neighborlist[k].getPossibilities().remove(index);
							didsomething = true;
						}
					}
					//column neighbor removal
					neighborlist = getcolumnneighbors(board_data[i][j]);
					for(int k = 0; k < 9; k++) {
						if(neighborlist[k].getPossibilities().contains(magicnumber) && neighborlist[k].getPossibilities().size() != 1) {
							index = neighborlist[k].getPossibilities().indexOf(magicnumber);
							neighborlist[k].getPossibilities().remove(index);
							didsomething = true;
						}
					}
					//square neighbor removal
					neighborlist = getsquareneighbors(board_data[i][j]);
					for(int k = 0; k < 9; k++) {
						if(neighborlist[k].getPossibilities().contains(magicnumber) && neighborlist[k].getPossibilities().size() != 1) {
							index = neighborlist[k].getPossibilities().indexOf(magicnumber);
							neighborlist[k].getPossibilities().remove(index);
							didsomething = true;
						}
					}
				}
			}
		}
		return didsomething;
	}
	
	
	//Step 2 takes every box that has more than a single possibility,
	//then checks each of those possibilities to see if any are absent 
	//from the possibilities of all of that box's neighbors. If that is the
	//case, then all other possibilities are removed from the original box
	public boolean step2() {
		int temp = 0;
		Box[] neighbors;
		boolean didsomething = false;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board_data[i][j].getPossibilities().size() != 1) {
					
					//row neighbor check
					for(int k = 0; k < board_data[i][j].getPossibilities().size(); k++) {
						neighbors =  getrowneighbors(board_data[i][j]);
						for(int l = 0; l < 9; l++) {
							if(neighbors[l].getPossibilities().contains(board_data[i][j].getPossibilities().get(k))) {
								temp = temp + 1;
							}
						}
						if(temp == 1) {
							board_data[i][j].setPossibilities(board_data[i][j].getPossibilities().get(k));
							temp = 0;
							didsomething = true;
							break;
						}
						temp = 0;
					}
					
					//column neighbor check
					for(int k = 0; k < board_data[i][j].getPossibilities().size(); k++) {
						neighbors =  getcolumnneighbors(board_data[i][j]);
						for(int l = 0; l < 9; l++) {
							if(neighbors[l].getPossibilities().contains(board_data[i][j].getPossibilities().get(k))) {
								temp = temp + 1;
							}
						}
						if(temp == 1) {
							board_data[i][j].setPossibilities(board_data[i][j].getPossibilities().get(k));
							temp = 0;
							didsomething = true;
							break;
						}
						temp = 0;
					}
					
					//square neighbor check
					for(int k = 0; k < board_data[i][j].getPossibilities().size(); k++) {
						neighbors =  getsquareneighbors(board_data[i][j]);
						for(int l = 0; l < 9; l++) {
							if(neighbors[l].getPossibilities().contains(board_data[i][j].getPossibilities().get(k))) {
								temp = temp + 1;
							}
						}
						if(temp == 1) {
							board_data[i][j].setPossibilities(board_data[i][j].getPossibilities().get(k));
							temp = 0;
							didsomething = true;
							break;
						}
						temp = 0;
					}
					
				}
			}
		}
		return didsomething;
	}
	
	//step 3 is tricky to understand. If there is a possibility present
	//in the 3 shared boxes between a column and a square or a row and
	//a square, and that possibility is absent from the remaining
	//neighbors in either the column/row or square, that possibility can be
	//removed from the rest of the other column/row or square.
	public boolean step3() {
		Box[] ssrn1;
		Box[] ssrn2;
		Box[] ssrn3;
		ArrayList<Integer> temp1 = new ArrayList<>();
		ArrayList<Integer> temp2 = new ArrayList<>();
		boolean didsomething = false;
		
		//row, square shared boxes
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j = j + 3) {
				ssrn1 = getssrn1(board_data[i][j]);
				ssrn2 = getssrn2(board_data[i][j]);
				temp1.clear();
				temp2.clear();
				for(int k = 0; k < 3; k++) {
					for(int l = 0; l < ssrn1[k].getPossibilities().size(); l++) {
						if(!temp1.contains(ssrn1[k].getPossibilities().get(l))) {
							temp1.add(ssrn1[k].getPossibilities().get(l));
						}
					}
				}
				for(int k = 0; k < 6; k++) {
					for(int l = 0; l < ssrn2[k].getPossibilities().size(); l++) {
						if(!temp2.contains(ssrn2[k].getPossibilities().get(l))) {
							temp2.add(ssrn2[k].getPossibilities().get(l));
						}
					}
				}
				for(int k = 0; k < temp1.size(); k++) {
					if(!temp2.contains(temp1.get(k))) {
						ssrn3 = getssrn3(board_data[i][j]);
						for(int m = 0; m < ssrn3.length; m++) {
							if(ssrn3[m].getPossibilities().contains(temp1.get(k))) {			
								ssrn3[m].getPossibilities().remove(ssrn3[m].getPossibilities().indexOf(temp1.get(k)));
								didsomething = true;
							}
						}
					}
				}
			}
		}
		
		
		//column, square shared boxes
		for(int i = 0; i < 9; i = i + 3) {
			for(int j = 0; j < 9; j++) {
				ssrn1 = getsscn1(board_data[i][j]);
				ssrn2 = getsscn2(board_data[i][j]);
				temp1.clear();
				temp2.clear();
				for(int k = 0; k < 3; k++) {
					for(int l = 0; l < ssrn1[k].getPossibilities().size(); l++) {
						if(!temp1.contains(ssrn1[k].getPossibilities().get(l))) {
							temp1.add(ssrn1[k].getPossibilities().get(l));
						}
					}
				}
				for(int k = 0; k < 6; k++) {
					for(int l = 0; l < ssrn2[k].getPossibilities().size(); l++) {
						if(!temp2.contains(ssrn2[k].getPossibilities().get(l))) {
							temp2.add(ssrn2[k].getPossibilities().get(l));
						}
					}
				}
				for(int k = 0; k < temp1.size(); k++) {
					if(!temp2.contains(temp1.get(k))) {
						ssrn3 = getsscn3(board_data[i][j]);
						for(int m = 0; m < ssrn3.length; m++) {
							if(ssrn3[m].getPossibilities().contains(temp1.get(k))) {
								ssrn3[m].getPossibilities().remove(ssrn3[m].getPossibilities().indexOf(temp1.get(k)));
								didsomething = true;
							}
						}
					}
				}
			}
		}
		return didsomething;
	}
	
	//Step 4 looks for two boxes in a row, column, or square
	//that each have the same two possibilities. These two 
	//possibilities can be removed from the rest of said
	//row column or square.
	public boolean step4() {
		
		//row checking
		Box[] neighbors;
		ArrayList<Integer> possibilities = new ArrayList<>();
		boolean gotime = false;
		boolean didsomething = false;
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j++) {
				neighbors = getrowneighbors2(board_data[i][j]);
				possibilities.clear();
				for(int k = 0; k < board_data[i][j].getPossibilities().size(); k++) {
					possibilities.add(board_data[i][j].getPossibilities().get(k));
				}
				if(possibilities.size() != 2) {
					continue;
				}
				for(int k = 0; k < 8; k++) {
					if(neighbors[k].getPossibilities().size() == 2) {
						if(neighbors[k].getPossibilities().contains(possibilities.get(0))) {
							if(neighbors[k].getPossibilities().contains(possibilities.get(1))) {
								gotime = true;
							}
						}
					}
				}
				if(gotime) {
					for(int k = 0; k < 8; k++) {
						if(neighbors[k].getPossibilities().contains(possibilities.get(0))) {
							if(!neighbors[k].getPossibilities().contains(possibilities.get(1)) || neighbors[k].getPossibilities().size() != 2) {
								neighbors[k].getPossibilities().remove(neighbors[k].getPossibilities().indexOf(possibilities.get(0)));
								didsomething = true;
							}
						}
						if(neighbors[k].getPossibilities().contains(possibilities.get(1))) {
							if(!neighbors[k].getPossibilities().contains(possibilities.get(0)) || neighbors[k].getPossibilities().size() != 2) {
								neighbors[k].getPossibilities().remove(neighbors[k].getPossibilities().indexOf(possibilities.get(1)));
								didsomething = true;
							}
						}
					}
				}
				gotime = false;
			}
		}
		
		//column checking
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j++) {
				neighbors = getcolumnneighbors2(board_data[i][j]);
				possibilities.clear();
				for(int k = 0; k < board_data[i][j].getPossibilities().size(); k++) {
					possibilities.add(board_data[i][j].getPossibilities().get(k));
				}
				if(possibilities.size() != 2) {
					continue;
				}
				for(int k = 0; k < 8; k++) {
					if(neighbors[k].getPossibilities().size() == 2) {
						if(neighbors[k].getPossibilities().contains(possibilities.get(0))) {
							if(neighbors[k].getPossibilities().contains(possibilities.get(1))) {
								gotime = true;
							}
						}
					}
				}
				if(gotime) {
					for(int k = 0; k < 8; k++) {
						if(neighbors[k].getPossibilities().contains(possibilities.get(0))) {
							if(!neighbors[k].getPossibilities().contains(possibilities.get(1)) || neighbors[k].getPossibilities().size() != 2) {
								neighbors[k].getPossibilities().remove(neighbors[k].getPossibilities().indexOf(possibilities.get(0)));
								didsomething = true;
							}
						}
						if(neighbors[k].getPossibilities().contains(possibilities.get(1))) {
							if(!neighbors[k].getPossibilities().contains(possibilities.get(0)) || neighbors[k].getPossibilities().size() != 2) {
								neighbors[k].getPossibilities().remove(neighbors[k].getPossibilities().indexOf(possibilities.get(1)));
								didsomething = true;
							}
						}
					}
				}
				gotime = false;
			}
		}
		
		//square checking
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j++) {
				neighbors = getsquareneighbors2(board_data[i][j]);
				possibilities.clear();
				for(int k = 0; k < board_data[i][j].getPossibilities().size(); k++) {
					possibilities.add(board_data[i][j].getPossibilities().get(k));
				}
				if(possibilities.size() != 2) {
					continue;
				}
				for(int k = 0; k < 8; k++) {
					if(neighbors[k].getPossibilities().size() == 2) {
						if(neighbors[k].getPossibilities().contains(possibilities.get(0))) {
							if(neighbors[k].getPossibilities().contains(possibilities.get(1))) {
								gotime = true;
							}
						}
					}
				}
				if(gotime) {
					for(int k = 0; k < 8; k++) {
						if(neighbors[k].getPossibilities().contains(possibilities.get(0))) {
							if(!neighbors[k].getPossibilities().contains(possibilities.get(1)) || neighbors[k].getPossibilities().size() != 2) {
								neighbors[k].getPossibilities().remove(neighbors[k].getPossibilities().indexOf(possibilities.get(0)));
								didsomething = true;
							}
						}
						if(neighbors[k].getPossibilities().contains(possibilities.get(1))) {
							if(!neighbors[k].getPossibilities().contains(possibilities.get(0)) || neighbors[k].getPossibilities().size() != 2) {
								neighbors[k].getPossibilities().remove(neighbors[k].getPossibilities().indexOf(possibilities.get(1)));
								didsomething = true;
							}
						}
					}
				}
				gotime = false;
			}
		}
		return didsomething;
	}
	
	//attempts to recursively brute force the puzzle
	public boolean recursivebruteforce() {
		ArrayList<Integer> possibilities;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				possibilities = board_data[i][j].getPossibilities();
				if(possibilities.size() == 1) {
					continue;
				}
				for(int k = 0; k < possibilities.size(); k++) {
					board_data[i][j].setPossibilities(possibilities.get(k));
					if(isvalidplacement(board_data[i][j])) {
						if(recursivebruteforce()) {
							return true;
						}
					}
				}
				board_data[i][j].setPossibilities(possibilities);
				return false;
			}
		}
		return true;
	}
	
}
