import java.util.Scanner;

public class TowerOfHanoi {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		recursive(n, "a", "b", "c");
	}

	
	public static void recursive(int n, String from, String middle, String to) {
		
		// base case
		if (n == 1) {
			System.out.println(from + " --> " + to);
			return;
		}
		
		// move people to middle
		recursive(n-1, from, to, middle);
		
		
		System.out.println(from + " --> " + to);
		
		// now move middle people to destination
		recursive(n-1, middle, from, to);
		
		
	}
}

