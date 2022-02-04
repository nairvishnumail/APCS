import java.util.Scanner;

public class Mowing {
	
	public static int[][] matrix;
	public static int[][] mowed;
	public static int[][] dir = {
			{-1, -1}, {-1, 0}, {-1, 1},
			{0, -1}, {0, 1},
			{1, -1}, {1, 0}, {1, 1}
			};
	public static int l, w;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		for (int grids = 0; grids < n; grids++) {
			
			l = in.nextInt();
			w = in.nextInt();
			
			
			int rCent = in.nextInt();
			int cCent = in.nextInt();
			
			
			matrix = new int[l][w];
			mowed = new int[l][w];
			char[][] grid = new char[l][w];
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < w; j++) {
					if (in.next().equals("T")) {
						matrix[i][j] = -1;
						grid[i][j] = 'T';
					}
					else grid[i][j] = '.';	
				}
			}
			
			
			trav(rCent, cCent);

			
			
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < w; j++) {
					if (mowed[i][j] == 1) grid[i][j] = 'C';
				}
			}
			
			for (char[] i : grid) {
				for (char j : i) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
			
		}
	}
	
	public static void trav(int r, int c) {
		if (matrix[r][c] == 1) return;
		matrix[r][c] = 1; // visited
		if (check(r, c)) {
			add(r, c);
			
			for (int[] i : dir) {
				trav(r+i[0], c+i[1]);
			}
		}
		
		
	}
	public static boolean check(int r, int c) {
		for (int[] i : dir) {
			// check boundries
			if (r + i[0] < 0 || r + i[0] >= l) {
				return false;
			}
			if (c + i[1] < 0 || c + i[1] >= w) {
				return false;
			}
			
			
			// check if there is a tree
			if (matrix[r+i[0]][c+i[1]] == -1) {
				return false;
			}
			
		}
		
		return true;
		
	}
	public static void add(int r, int c) {
		mowed[r][c] = 1;
		for (int[] i : dir) {
			mowed[r+i[0]][c+i[1]] = 1;
		}
	}

}
