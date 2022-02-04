import java.util.ArrayList;
import java.util.Scanner;

public class Checkers {
	
	public final static int MAX_BOARD = 8;
	public static ArrayList<ArrayList<pieces>> pieceBoard = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
	
		set();
		print();
		
		
		int r;
		int c;
		
		int currR;
		int currC;
		
		boolean isWhite = false;
		String printName = "";
		while(!win()) {
			if (!isWhite) {
				printName = "Black";
				isWhite = true;
			}
			else {
				printName = "White";
				isWhite = false;
			}
			
			System.out.println(printName + "'s turn (row SPACE col)");
			System.out.print("Which checker do you want to move?: ");
			currR = in.nextInt();
			currC = in.nextInt();
			System.out.println();
			
			while (checkIfValidChecker(currR, currC, !isWhite) == false) {
				currR = in.nextInt();
				currC = in.nextInt();
			}
			
			System.out.println("Where to?: ");
			r = in.nextInt();
			c = in.nextInt();
			System.out.println();
			
			currR--;
			currC--;
			while (checkMove(pieceBoard.get(currR).get(currC), r, c) == false) {
				r = in.nextInt();
				c = in.nextInt();
			}
			
			checkAndMakeKing();
			print();
		}

		
	}
	public static void checkAndMakeKing() {
		for (int col = 0; col < 7; col++) {
			//if there are any white pieces on the black side of the board
			if (pieceBoard.get(0).get(col) != null && pieceBoard.get(0).get(col).isWhite()) {
				pieceBoard.get(0).get(col).makeKing(true);
			}
			
			
			if (pieceBoard.get(7).get(col) != null && !pieceBoard.get(7).get(col).isWhite()) {
				pieceBoard.get(7).get(col).makeKing(true);
			}
			
		}

		
	}
	
	
	public static boolean checkIfValidChecker(int currR, int currC, boolean isWhite) {
		// must be an actual checker of player color
		currR--;
		currC--;
		if (currR < 0 || currR > MAX_BOARD-1 || currC < 0 || currC > MAX_BOARD-1) {
			System.out.println("Out of bounds");
			return false;
		}
		if (pieceBoard.get(currR).get(currC) == null) {
			System.out.println("No checker there. Try again.");
			return false;
		}
		if (pieceBoard.get(currR).get(currC).isWhite() != isWhite) {
			System.out.println("Pick a checker of your color. Try again.");
			return false;
		}
		return true;
	}
	
	//send coor - 1
	public static boolean checkMove(pieces piece, int toR, int toC) {
		
		//checkers must stay on dark squares 
		if (!( (toR%2 == 0 && toC%2 == 1) || (toR%2 == 1 && toC%2 == 0) )) {
			System.out.println("Moves must to dark squares (diagonals). Try again.");
			return false;
		}
		
		//check if in bounds
		if (toR < 1 || toR > MAX_BOARD || toC < 1 || toC > MAX_BOARD) {
			System.out.println("Move is out of bounds. Try again.");
			return false;
		}
		
		// coordinates are now 0-indexed
		toR--;
		toC--;
		
		
		//there are no checkers on the square on which to jump to
		if (pieceBoard.get(toR).get(toC) != null ) {
			print();
			System.out.println("There is a piece to where you're moving. Try again.");
			return false;
		}
		
		
		//king
		if (piece.kingStatus()) {
			if ( !(toR == piece.getX()-1 || toR == piece.getX()-2 || toR == piece.getX()+1 || toR == piece.getX()+2) ) {
				System.out.println("Move must only go in diagonals. Try again.");
				return false;
			}
			if ( !(toC == piece.getY()+1 || toC == piece.getY()-1 || toC == piece.getY()+2 || toC == piece.getY()-2)) {
				System.out.println("Move must only go in diagonals. Try again.");
				return false;
			}
		}
		
		//only can move in forward diagonals
		else if (piece.isWhite()) {
			// 5 2 --> 4 3 or 4 1
			if ( !(toR == piece.getX()-1 || toR == piece.getX()-2) ) {
				System.out.println("Move must only go in front diagonals. Try again.");
				return false;
			}
			if ( !(toC == piece.getY()+1 || toC == piece.getY()-1 || toC == piece.getY()+2 || toC == piece.getY()-2)) {
				System.out.println("Move must only go in front diagonals. Try again.");
				return false;
			}
			
		}
		else if (!piece.isWhite()) {
			// 2 1 --> 3 0 or 3 2
			if ( !(toR == piece.getX()+1 || toR == piece.getX()+2)) {
				System.out.println("Move must only go in front diagonals. Try again.");
				return false;
			}
			if ( !(toC == piece.getY()+1 || toC == piece.getY()-1 || toC == piece.getY()+2 || toC == piece.getY()-2)) {
				System.out.println("Move must only go in front diagonals. Try again.");
				return false;
			}
			//print();
		}
		
	// MOVING
		//must only move one space unless opposite color-checker in path
		//if jumping over checker (moving two spaces), make sure there is a checker in the middle
			//call "REMOVE" method
			//call "MOVE" method
		//if, after jumping over checker, there is another checker to jump over: must jump over it
			//call "anyMoreJumps" method
		
		//jumping
		int toColumnCheck = 0;
		if (toC == piece.getY()-2) toColumnCheck = -1;
		else if (toC == piece.getY()+2) toColumnCheck = 1;
		
		if ((piece.isWhite() || piece.kingStatus()) && toR == piece.getX()-2) {
					
			// two-space diagonal    and  there is no opposite-color checker in the middle
			if (pieceBoard.get(piece.getX()-1).get(piece.getY()+toColumnCheck) == null || pieceBoard.get(piece.getX()-1).get(piece.getY()+toColumnCheck).isWhite() == piece.isWhite()) {
				System.out.println("Cannot jump a space if there is no / your-colored checker in the middle. Try again.");
				return false;
			}
			//otherwise, its a genuine jump
			
			// remove that piece
			remove(piece.getX()-1, piece.getY()+toColumnCheck);
			//move current players piece
			move(piece, toR, toC);
			
			if (piece.kingStatus()) anyMoreKingJumps(piece);
			else anyMoreJumps(piece);
			return true;
			
		}
		//black checker
		else if ((!piece.isWhite() || piece.kingStatus()) && toR == piece.getX()+2){
			if (pieceBoard.get(piece.getX()+1).get(piece.getY()+toColumnCheck) == null || pieceBoard.get(piece.getX()+1).get(piece.getY()+toColumnCheck).isWhite() == piece.isWhite()) {
				System.out.println("Cannot jump a space if there is no / your-colored checker in the middle. Try again.");
				return false;
			}
			remove(piece.getX()+1, piece.getY()+toColumnCheck);
			move (piece, toR, toC);
			
			if (piece.kingStatus()) anyMoreKingJumps(piece);
			else anyMoreJumps(piece);
			return true;
		}
		
		//if not jumping, move one space
		else {
			move(piece, toR, toC);
			return true;
		}

	}
	
	private static void anyMoreJumps(pieces piece) {
		//check forward diagonals to see if there are any more jumps possible
			//if only one jump is possible, take it
			//if there are multiple jumps possible ask for input
				//call checkMove
		
		int currR = piece.getX();
		int currC = piece.getY();
		
		boolean isWhite = piece.isWhite();
		
		boolean leftDiag = false;
		boolean rightDiag = false;
		
		//bound check
			
		if (isWhite && (currR-2) >= 0) {
			// left upper diagonal (if diagonal is opposite color & there is a space to jump)
			if ((currC-2) >= 0 && pieceBoard.get(currR-1).get(currC-1) != null && !pieceBoard.get(currR-1).get(currC-1).isWhite()   &&  pieceBoard.get(currR-2).get(currC-2) == null) leftDiag = true;
			// right upper diagonal
			if ((currC+2) < MAX_BOARD && pieceBoard.get(currR-1).get(currC+1) != null && !pieceBoard.get(currR-1).get(currC+1).isWhite()   &&  pieceBoard.get(currR-2).get(currC+2) == null) rightDiag = true;	
		}
		else if (!isWhite && (currR+2) < MAX_BOARD){
			
			if ((currC-2) >= 0 && pieceBoard.get(currR+1).get(currC-1) != null && pieceBoard.get(currR+1).get(currC-1).isWhite()   &&   pieceBoard.get(currR+2).get(currC-2) == null) leftDiag = true;
			
			if ((currC+2) < MAX_BOARD && pieceBoard.get(currR+1).get(currC+1) != null && pieceBoard.get(currR+1).get(currC+1).isWhite()   &&   pieceBoard.get(currR+2).get(currC+2) == null) rightDiag = true;
			
		}
		
		
		Scanner in = new Scanner(System.in);
		int r, c;
		
		if (leftDiag && rightDiag) {
			print();
			
			System.out.println("You must make one more move! \nChoose to capture either the left or the right checker...");			
			System.out.print("Please input to which (row SPACE col): ");
					
			r = in.nextInt()-1;
			c = in.nextInt()-1;
													// white checker    leftDiag                             rightDiag                            black         leftDiag                              rightDiag
			while(checkMove(piece, r+1, c+1) == false || !( (isWhite && ( (r == currR - 2 && c == currC-2) || (r == currR-2 && c == currC+2 )))  ||  (!isWhite && ( (r == currR + 2 && c == currC - 2) || (r == currR + 2 && c == currC + 2)) ))) {
				move(piece, currR, currC);
				print();
				
				System.out.print("Must jump to the left or to the right. Try again (row SPACE column): ");
				r = in.nextInt()-1;
				c = in.nextInt()-1;
				System.out.println();
			}
			System.out.println();
		}
		else if (leftDiag) {
			print();
			
			System.out.println("You must also capture the checker on your left!");
			System.out.print("Please input to which (row SPACE col): ");
			
			r = in.nextInt() - 1;
			c = in.nextInt() - 1;
			
			
			while(checkMove(piece, r+1, c+1) == false || !( (isWhite && (r == currR - 2 && c == currC-2))  ||  (!isWhite && (r == currR + 2 && c == currC - 2)))) {
				move(piece, currR, currC);
				print();
				
				
				System.out.print("Must jump to the left. Try again (row SPACE column): ");
				r = in.nextInt() - 1;
				c = in.nextInt() - 1;
				System.out.println();
			}
			System.out.println();
		}
		else if (rightDiag) {
			print();
			
			System.out.println("You must also capture the checker on your right!");
			System.out.print("Please input to which (row SPACE col): ");
			
			r = in.nextInt()-1;
			c = in.nextInt()-1;
			
			
			while(checkMove(piece, r+1, c+1) == false || !( (isWhite && (r == currR-2 && c == currC+2 ))  ||  (!isWhite && (r == currR + 2 && c == currC + 2)) )) {
				move(piece, currR, currC);
				print();
				
				System.out.print("Must jump to the right. Try again (row SPACE column): ");
				r = in.nextInt()-1;
				c = in.nextInt()-1;
				System.out.println();
			}
			System.out.println();
		}
	}
	
	private static void anyMoreKingJumps(pieces piece) {
		//check forward + back diagonals to see if there are any more jumps possible
			//if only one jump is possible, take it
			//if there are multiple jumps possible ask for input
				//call checkKingMove
		
		boolean isWhite = piece.isWhite();
		
		int currR = piece.getX();
		int currC = piece.getY();
			
		boolean leftTopDiag = false;
		boolean leftBottomDiag = false;
		boolean rightTopDiag = false;
		boolean rightBottomDiag = false;
		
		//bound check
		if ((currR-2) >= 0) {
			// left upper diagonal (if diagonal is opposite color & there is a space to jump)
			if ((currC-2) >= 0 && pieceBoard.get(currR-1).get(currC-1) != null && pieceBoard.get(currR-1).get(currC-1).isWhite() != isWhite  &&  pieceBoard.get(currR-2).get(currC-2) == null) leftTopDiag = true;
			// right upper diagonal
			if ((currC+2) < MAX_BOARD && pieceBoard.get(currR-1).get(currC+1) != null && pieceBoard.get(currR-1).get(currC+1).isWhite() != isWhite   &&  pieceBoard.get(currR-2).get(currC+2) == null) rightTopDiag = true;	
		}
		if ((currR+2) < MAX_BOARD){
			
			if ((currC-2) >= 0 && pieceBoard.get(currR+1).get(currC-1) != null && pieceBoard.get(currR+1).get(currC-1).isWhite() != isWhite   &&   pieceBoard.get(currR+2).get(currC-2) == null) leftBottomDiag = true;
					
			if ((currC+2) < MAX_BOARD && pieceBoard.get(currR+1).get(currC+1) != null && pieceBoard.get(currR+1).get(currC+1).isWhite() != isWhite   &&   pieceBoard.get(currR+2).get(currC+2) == null) rightBottomDiag = true;
					
		}
		
		if (leftTopDiag || leftBottomDiag || rightTopDiag || rightBottomDiag) {
			Scanner in = new Scanner(System.in);
			int r, c;
			
			print();
			System.out.println("You must make one more move and capture another checker!");
			System.out.print("(row SPACE col): ");
			
			r = in.nextInt()-1;
			c = in.nextInt()-1;
			
			System.out.println((r == currR + 2) || (r == currR - 2));
			System.out.println(( c == currC - 2 ) || ( c == currC + 2 ));
			
			while ( !(( (r == currR + 2) || (r == currR - 2) ) && ( ( c == currC - 2 ) || ( c == currC + 2 ) ) )  || !checkMove(piece, r+1, c+1)  ) {
				move(piece, currR, currC);
				print();
				
				System.out.println("You must capture a checker. Try again.");
				System.out.print("(row SPACE col): ");
				
				
				r = in.nextInt()-1;
				c = in.nextInt()-1;
				System.out.println();
				
			}
			
			System.out.println();
				
			
		}
		
		
	}
	
	private static void remove(int removeR, int removeC) {
		//find piece with indexes
			//remove from arraylist & make position null
		
		pieceBoard.get(removeR).set(removeC, null);
	}
	
	private static void move(pieces piece, int newR, int newC) {
		//move piece in arraylist
		pieceBoard.get(piece.getX()).set(piece.getY(), null);
		pieceBoard.get(newR).set(newC, piece);
		
		//get curr indexes of piece (getX & getY)
			//change indexes of piece
		
		piece.setXAndY(newR, newC);
		
		
	}
	
	public static boolean win() {
		//if all checkers of one color has been captured
		
		int isWhite = 0;
		int isBlack = 0;
		for (int i = 0; i < MAX_BOARD; i++) {
			for (int j = 0; j < MAX_BOARD; j++) {
				if (pieceBoard.get(i).get(j) != null) {
					if (pieceBoard.get(i).get(j).isWhite()) isWhite++;
					else isBlack++;
				}
			}
		}
		if (isBlack == 0) {
			System.out.println("White wins!");
			return true;
		}
		else if (isWhite == 0) {
			System.out.println("Black wins!");
			return true;
		}
		
		
		
		//OR if all checkers of one color are blocked & cant move anymore
		
		boolean blockWhite = true;
		boolean blockBlack = true;
		
		boolean leftTop, leftBottom, rightTop, rightBottom; //blocked = true for that position, else false
		for (int i = 0; i < MAX_BOARD; i++) {
			for (int j = 0; j < MAX_BOARD; j++) {
				leftTop = false;
				leftBottom = false;
				rightTop = false;
				rightBottom = false;
				
				// For kings and whites (moving up)
				if (pieceBoard.get(i).get(j) != null && (pieceBoard.get(i).get(j).kingStatus() || pieceBoard.get(i).get(j).isWhite())) {
					// out of bounds
					if (i-1 < 0 || j-1 < 0) leftTop = true;
					// if left diagonal is blocked by the same color
					else if (pieceBoard.get(i-1).get(j-1) != null 
							&& pieceBoard.get(i-1).get(j-1).isWhite() == pieceBoard.get(i).get(j).isWhite()) {
						leftTop = true;
					}
					// if left diagonal is blocked by opposite, but there is something stopping it from making a jump
					else if (pieceBoard.get(i-1).get(j-1) != null && (i-2 < 0 || j-2 < 0 || pieceBoard.get(i-2).get(j-2) != null)) leftTop = true;
					
					// out of bounds
					if (i-1 < 0 || j+1 > MAX_BOARD-1) rightTop = true;
					// if right diagonal is blocked by the same color
					else if (pieceBoard.get(i-1).get(j+1) != null && pieceBoard.get(i-1).get(j+1).isWhite() == pieceBoard.get(i).get(j).isWhite()) {
						rightTop = true;
					}
					// out of bounds & if right diagonal is blocked by opposite, but there is something stopping it from making a jump
					else if (pieceBoard.get(i-1).get(j+1) != null && (i-2 < 0 || j+2 > MAX_BOARD-1 || pieceBoard.get(i-2).get(j+2) != null)) rightTop = true;
					
				}
				
				
				// For kings and blacks (moving down)
				if (pieceBoard.get(i).get(j) != null && (pieceBoard.get(i).get(j).kingStatus() || !pieceBoard.get(i).get(j).isWhite())) {
					// out of bounds
					if (i+1 > MAX_BOARD-1 || j-1 < 0) leftBottom = true;
					// left bottom
					else if (pieceBoard.get(i+1).get(j-1) != null && pieceBoard.get(i+1).get(j-1).isWhite() == pieceBoard.get(i).get(j).isWhite()) {
						leftBottom = true;
					}
					else if (pieceBoard.get(i+1).get(j-1) != null && (i+2 > MAX_BOARD-1 || j-2 < 0 || pieceBoard.get(i+2).get(j-2) != null)) leftBottom = true;
					
					// out of bounds
					if (i+1 > MAX_BOARD-1 || j+1 > MAX_BOARD-1) rightBottom = true;
					// right bottom
					else if (pieceBoard.get(i+1).get(j+1) != null && pieceBoard.get(i+1).get(j+1).isWhite() == pieceBoard.get(i).get(j).isWhite()) {
						rightBottom = true;
					}
					else if (pieceBoard.get(i+1).get(j+1) != null && (i+2 > MAX_BOARD-1 || j+2 > MAX_BOARD-1 || pieceBoard.get(i+2).get(j+2) != null)) rightBottom = true;
				}
				
				
				if (pieceBoard.get(i).get(j) != null && pieceBoard.get(i).get(j).kingStatus() &&
						!(leftTop && leftBottom && rightTop && rightBottom)) {
					if (pieceBoard.get(i).get(j).isWhite()) blockWhite = false;
					else blockBlack = false;
					
				}
				else if (pieceBoard.get(i).get(j) != null && pieceBoard.get(i).get(j).isWhite() &&
						!(leftTop && rightTop)) blockWhite = false;
				else if ((pieceBoard.get(i).get(j) != null && !pieceBoard.get(i).get(j).isWhite() &&
						!(leftBottom && rightBottom))) {
					blockBlack = false;
				}
				//System.out.println(blockBlack);
				//if (!blockBlack) System.out.println((i+1) + " " + (j+1));
			}
		}

		if (blockWhite) {
			System.out.println("Black wins!");
			return true;
		}
		if (blockBlack) {
			System.out.println("White wins!");
			return true;
		}
		
		return false;
		
	}
	
	public static void print() {
		System.out.println("\n    1   2   3   4   5   6   7   8");
		System.out.println("  ---------------------------------");
		for (int i = 0; i < MAX_BOARD; i++) {
			System.out.print(i+1 + " ");
			for (int j = 0; j < MAX_BOARD; j++) {
				System.out.print("| ");
				if (pieceBoard.get(i).get(j) == null) System.out.print("  ");
				else if (pieceBoard.get(i).get(j).kingStatus()) System.out.print(pieceBoard.get(i).get(j));
				else System.out.print(pieceBoard.get(i).get(j) + " ");
				//System.out.print(pieceBoard.get(i).get(j).kingStatus());}
			}
			System.out.println("|");
		}
		System.out.println("  ---------------------------------");
	}
	
	public static void set() {
		ArrayList<pieces> temp;
		
		int change = 0;
		for (int r = 1; r <= 3; r++) {
			
			if (change == 0) change = 1;
			else change = 0;
			temp = new ArrayList<>();
			for (int c = 1; c <= MAX_BOARD; c++) {	
				if (c%2 == change) temp.add(null);
				else temp.add(new pieces(r-1, c-1, false, false));
			}
			
			pieceBoard.add(temp);
		}
		
		
		for (int r = 4; r <= 5; r++) {
			
			temp = new ArrayList<>();
			for (int c = 1; c <= MAX_BOARD; c++) {
				temp.add(null);
			}
			
			pieceBoard.add(temp);
		}
		
		change = 1;
		for (int r = 6; r <= 8; r++) {
			
			if (change == 0) change = 1;
			else change = 0;
			temp = new ArrayList<>();
			for (int c = 1; c <= MAX_BOARD; c++) {
				if (c%2 == change) temp.add(null);
				else temp.add(new pieces(r-1, c-1, false, true));
			}
			
			pieceBoard.add(temp);
		}
		
	}

}

class pieces {
	private int xCor;
	private int yCor;
	private boolean king;
	private boolean white;

	public pieces() {
		xCor = 0;
		yCor = 0;
		king = false;
		white = false;
	}
	
	public pieces(int x, int y, boolean b, boolean a) {
		xCor = x;
		yCor = y;
		king = b;
		white = a;
	}

	public int getX() {
		return xCor;
	}

	public int getY() {
		return yCor;
	}

	public boolean kingStatus() {
		return king;
	}
	
	public boolean isWhite() {
		return white;
	}

	public void setX(int x) {
		xCor = x;
	}

	public void setY(int y) {
		yCor = y;
	}

	public void setXAndY(int x, int y) {
		xCor = x;
		yCor = y;
	}
	
	public void makeKing(boolean k) {
		king = k;
	}
	
	public String toString() {
		String piece = "";
		if (isWhite()) piece += "W";
		else piece += "B";
		if (kingStatus()) piece += "K";
		return piece;
	}
}
