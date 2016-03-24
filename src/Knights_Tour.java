import java.io.IOException;
import java.util.Scanner;

import List.*;

public class Knights_Tour {
	public static class Move{
		public int row;
		public int column;
		public int weight;
		
		public Move(int row, int column, int weight){
			this.row = row;
			this.column = column;
			this.weight = weight;
		}
		
		public int compareTo(Move b){
			
			if(this.weight == b.weight){
				if(this.row == b.row){
					return 0;
				}else if(this.row > b.row){
					return 1;
				}else if(this.row < b.row){
					return -1;
				}
			}else if(this.weight > b.weight){
				return 1;
			}else if(this.weight < b.weight){
				return -1;
			}
			return 0;
		}
	}
	
	static int[][] board = null;
	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.out.println("  (\\=,   	Welcome to Knights Tour.\r\n" + 
					       " //  .\\  	Please enter the number of Rows and Columns\r\n" + 
					       "(( \\_  \\ 	followed by the starting row and column\r\n" + 
					       " ))  `\\_)	\r\n" + 
					       "(/     \\ 	Entering a -1 for either the starting row or column\r\n" + 
					       " | _.-'| 	will generate all the possible knights tours for\r\n" + 
					       "  )___(  	the specified row/column dimensions\r\n" + 
					       " (=====) 	\r\n" + 
					       " }====={ 	\r\n" + 
					       "(_______)	\r\n" + 
					       "");
		int numRows, numCols, startRow, startCol;
		
		try{
			Scanner keyboard = new Scanner(System.in);
			
			System.out.print("Enter Number of Rows: ");
			numRows = keyboard.nextInt();
			
			System.out.print("Enter Number of Columns: ");
			numCols = keyboard.nextInt();
			
			System.out.print("Enter Starting Row: ");
			startRow = keyboard.nextInt();
			
			System.out.print("Enter Starting Column: ");
			startCol = keyboard.nextInt();
			System.out.println();
		}catch(Exception e){
			System.out.println();		
			System.out.println("Input Error: Bad Row or Column Input");
			return;
		}

		
		
		if(numRows > 0 && numCols > 0){
			if(startRow == -1 || startCol == -1){
				for(int i = 0; i < numRows; i++){
					for(int j = 0; j < numCols; j++){
						board = new int[numRows][numCols];
						if(knightsTour(i,j,1) == 1){
							if(numRows >= 6 && numCols >= 6){
								if(numRows%2 == 0 || numCols%2 == 0){
									System.out.print("Error");
								}else{
									System.out.print("No Solution");
								}
							}else{
								System.out.print("No Solution");
							}
						}
						System.out.println(" FOR start of " + i + ", " + j);
					}
				}
			}else if((startRow > -1 && startRow < numRows) && (startCol < numCols && startCol > -1)){
				board = new int[numRows][numCols];
				if(knightsTour(startRow,startCol,1) == 1){
					if(numRows >= 6 && numCols >= 6){
						if(numRows%2 == 0 || numCols%2 == 0){
							System.out.print("Error");
						}else{
							System.out.print("No Solution");
						}
					}else{
						System.out.print("No Solution");
					}
					
				}
				System.out.println(" FOR start of " + startRow + ", " + startCol);			
			}else{
				System.out.println();		
				System.out.println("Input Error: Bad Starting Row or Column");
			}
		}else{
			System.out.println();		
			System.out.println("Input Error: Bad Row or Column Size");		
		}
	}
	
	private static int knightsTour(int row, int column, int moveNumber) throws InterruptedException, IOException{
		int numRows = board.length;
		int numCols = board[0].length;
		int stop = numRows + numCols + 1;
		
		board[row][column] = moveNumber;
		
		Move[] moves = getNextMoves(row, column);
		
		for(Move move : moves){
			if(move.weight == stop){
				//printBoard(board);
				if(validateBoard(board)){
					printBoard(board);
					System.out.print("VALID");
					return 0;
				}else{
					board[row][column] = 0;
					return 1;
				}
			}else{
				if(knightsTour(move.row, move.column, moveNumber + 1) == 0){
					return 0;
				}
			}
		}
		return 1;
	}
	
	private static Move[] getNextMoves(int row, int col){
		Move[] movesArray = new Move[8];
		                       
		movesArray[0] = new Move(row + 2, col + 1, 0);  
		movesArray[1] = new Move(row + 2, col - 1, 0);  
		movesArray[2] = new Move(row - 2, col + 1, 0);  
		movesArray[3] = new Move(row - 2, col - 1, 0);  
		movesArray[4] = new Move(row + 1, col + 2, 0);  
		movesArray[5] = new Move(row + 1, col - 2, 0);
		movesArray[6] = new Move(row - 1, col + 2, 0);  
		movesArray[7] = new Move(row - 1, col - 2, 0);  

		movesArray = weightMoves(movesArray);
		movesArray = sortMoves(movesArray);
		
		return movesArray;
	}
	
	private static Move[] weightMoves(Move[] moves){
		int numRows = board.length;
		int numCols = board[0].length;
		
		for(int move = 0; move < moves.length; move++){
			int weight = 0;
			if(moves[move].row >= 0 && moves[move].column >= 0){
				if(moves[move].row < numRows && moves[move].column < numCols){
					if(board[moves[move].row][moves[move].column] == 0){
						int runWeight = ((moves[move].column) > (numRows - moves[move].column)) ? numRows - moves[move].column: moves[move].column;
						int riseWeight = ((moves[move].row) > (numCols - moves[move].row)) ? numCols - moves[move].row: moves[move].row;
						
						weight = 0;//runWeight + riseWeight;
					}else{
						weight = numRows + numCols + 1;
					}
				}else{
					weight = numRows + numCols + 1;
				}
			}else{
				weight = numRows + numCols + 1;
			}
			moves[move].weight = weight;
		}
		return moves;
	}
	
	private static Move[] sortMoves(Move[] moves){
		List<Move> sortedMoves = new LinkedList<Move>();
		
		for(Move mve : moves){
			if(sortedMoves.isEmpty()){
				sortedMoves.add(mve);
			}else{
				int i = 1;
				for(; i <= sortedMoves.size() + 1; i++){
					if(i > sortedMoves.size()){
						sortedMoves.add(mve);
						break;
					}else{
						if(mve.compareTo(sortedMoves.get(i)) < 0){
							sortedMoves.add(i,mve);
							break;
						}
					}
				}
			}
		}
		
		for(int i = 1; i <= sortedMoves.size(); i++){
			moves[i-1] = sortedMoves.get(i);
		}
		return moves;
	}
	
	private static boolean validateBoard(int[][] gameBoard){
		
		for(int[] row: gameBoard){
			for (int col: row){
				if(col == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private static void printBoard(int[][] gameBoard) throws InterruptedException, IOException{
		//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		for(int[] row: gameBoard){
			for (int col: row){
				System.out.print("|"+String.format("%02d", col));
			}
			System.out.println("|");
		}
	}
	
}
