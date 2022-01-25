// Vishnu Nair

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String one = "1";
		String two = "2";
		String three = "3";
		String four = "4";
		String five = "5";
		String six = "6";
		String seven = "7";
		String eight = "8";
		String nine = "9";
		
		int one_num = -1;
		int two_num = -1;
		int three_num = -1;
		int four_num = -1;
		int five_num = -1;
		int six_num = -1;
		int seven_num = -1;
		int eight_num = -1;
		int nine_num = -1;
		
		int x_num = 1;
		int o_num = 0;
		String x = "X";
		String o = "O";
		
		
		boolean player = false;
		
		boolean win = false;
		boolean draw = false;
		int pos;
		
		int times = 0;
		
		while (win == false && draw == false) {
			times++;
			
			System.out.println("\n\t" + one + " | " + two + " | " + three);
			System.out.println("\t---------");
			System.out.println("\t" + four + " | " + five + " | " + six);
			System.out.println("\t---------");
			System.out.println("\t" + seven + " | " + eight + " | " + nine);
			
			System.out.print("\nWhich position?: ");
			pos = input.nextInt();
			input.nextLine();
			
			if (player == false) { 
				player = true;
				if (pos == 1) {
					one  = x;
					one_num = x_num;
				}
				else if (pos == 2) {
					two = x;
					two_num = x_num;
				}
				else if (pos == 3) { 
					three = x;
					three_num = x_num;
				}
				else if (pos == 4) { 
					four = x;
					four_num = x_num;
				}
				else if (pos == 5) { 
					five = x;
					five_num = x_num;
				}
				else if (pos == 6) { 
					six = x;
					six_num = x_num;
				}
				else if (pos == 7) { 
					seven = x;
					seven_num = x_num;
				}
				else if (pos == 8) { 
					eight = x;
					eight_num = x_num;
				}
				else if (pos == 9) { 
					nine = x;
					nine_num = x_num;
				}
			}
			
			else {
				player = false;
				
				if (pos == 1) {
					one  = o;
					one_num = o_num;
				}
				else if (pos == 2) {
					two = o;
					two_num = o_num;
				}
				else if (pos == 3) { 
					three = o;
					three_num = o_num;
				}
				else if (pos == 4) { 
					four = o;
					four_num = o_num;
				}
				else if (pos == 5) { 
					five = o;
					five_num = o_num;
				}
				else if (pos == 6) { 
					six = o;
					six_num = o_num;
				}
				else if (pos == 7) { 
					seven = o;
					seven_num = o_num;
				}
				else if (pos == 8) { 
					eight = o;
					eight_num = o_num;
				}
				else if (pos == 9) { 
					nine = o;
					nine_num = o_num;
				}
			}
			
			// Diagonals
			if (one_num == x_num && five_num == x_num && nine_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (one_num == o_num && five_num == o_num && nine_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			
			if (three_num == x_num && five_num == x_num && seven_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (three_num == o_num && five_num == o_num && seven_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			
			
			// Rows
			if (one_num == x_num && two_num == x_num && three_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (one_num == o_num && two_num == o_num && three_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			//--
			if (four_num == x_num && five_num == x_num && six_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (four_num == o_num && five_num == o_num && six_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			//--
			if (seven_num == x_num && eight_num == x_num && nine_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (seven_num == o_num && eight_num == o_num && nine_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			
			
			// Columns
			if (one_num == x_num && four_num == x_num && seven_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (one_num == o_num && four_num == o_num && seven_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			//--
			if (two_num == x_num && five_num == x_num && eight_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (two_num == o_num && five_num == o_num && eight_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			//--
			if (three_num == x_num && six_num == x_num && nine_num == x_num) {
				System.out.println("X won!");
				win = true;
			}
			if (three_num == o_num && six_num == o_num && nine_num == o_num) {
				System.out.println("O won!");
				win = true;
			}
			
			if (times == 9) {
				System.out.println("Draw!");
				draw = true;
			}
		}

	}

}
