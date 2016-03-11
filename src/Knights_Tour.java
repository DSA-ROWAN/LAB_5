
public class Knights_Tour {
	static int[][] board = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board = new int[10][10];
		
		int[][] moves = getNextMoves(5,5);
		
		for(int[] move : moves){
			System.out.print("X: " + move[0] + " ");
			System.out.println("Y: " + move[1]);
		}
		
	}
	
	private static int knightsTour(int x, int y, int moveNumber, int[][] board){
		
		
		return 0;
	}
	
	private static int[][] getNextMoves(int x, int y){
		int[][] retArray = new int[8][2];
		
		retArray[0][0] = x + 3;  
		retArray[1][0] = x + 3;  
		retArray[2][0] = x - 3;  
		retArray[3][0] = x - 3;  
		retArray[4][0] = x + 1;  
		retArray[5][0] = x + 1;  
		retArray[6][0] = x - 1;  
		retArray[7][0] = x - 1;  
		
		retArray[0][1] = y + 1;
		retArray[1][1] = y - 1;
		retArray[2][1] = y + 1;
		retArray[3][1] = y - 1;
		retArray[4][1] = y + 3;
		retArray[5][1] = y - 3;
		retArray[6][1] = y + 3;
		retArray[7][1] = y - 3;
		
		return retArray;
	}
	
	private static int[] weightMoves(int[][] moves){
		int[] weights = new int[7];
		int x = 0;
		int y = 1;
		int xMax = board[0].length;
		int yMax = board.length;
		
		for(int move = 0; move < moves.length - 1; move++){
			int weight = 0;
			if(moves[move][x] >= 0 && moves[move][y] >= 0){
				if(moves[move][x] < xMax && moves[move][y] < yMax){
					if(board[moves[move][x]][moves[move][y]] > 0){
						int runWeight = ((moves[move][x] - xMax) > (xMax - moves[move][x])) ? xMax - moves[move][x]: moves[move][x] - xMax;
						int riseWeight = ((moves[move][y] - yMax) > (yMax - moves[move][y])) ? yMax - moves[move][y]: moves[move][y] - yMax;
						
						weight = runWeight + riseWeight;
					}else{
						weight = xMax + yMax + 1;
					}
				}else{
					weight = xMax + yMax + 1;
				}
			}else{
				weight = xMax + yMax + 1;
			}
			weights[move] = weight;
		}
		return weights;
	}
}
