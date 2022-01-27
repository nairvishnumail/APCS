import java.util.ArrayList;
import java.util.Scanner;


public class BlackJack {

	public static boolean s = true;
	public int aceCounter = 0;
	
	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);

	    
		System.out.print("Welcome. Please enter your name: ");
		String name = in.next();
		
		while (s) {
			System.out.println();
	    	startGame(name);
	    	
	    	System.out.print("Start a new game? (y or n): ");
	    	String again = in.next();
	    	if (again.equals("y")) s = true;
	    	else s = false;
	    }
		System.out.println("\nEnd of Blackjack");
	}
	
	public static void startGame(String name) {
		Player player = new Player(name, 500);
		
		boolean goAgain = playGame(player);
		while (goAgain && player.getCash() > 0) {
			goAgain = playGame(player);
		}
		if (player.getCash() < 0) System.out.println("You're out of cash. Come again later.");
		else if (!goAgain) System.out.println("Alright, see you later.");
	}
	
	
	public static boolean playGame(Player player) {
		Scanner in = new Scanner(System.in);
		
		Player dealer = new Player("Dealer", 100000);
		player.setNewDeck();
		
		Deck deck = new Deck();
		deck.shuffle();
		
		
		System.out.println("You have $" + player.getCash());
		System.out.print("How much money to bet?: ");
		int bet = in.nextInt();
		
		while (bet > player.getCash() || bet < 0) {
			System.out.println();
			System.out.println("Not a valid bet. Try again");
			
			System.out.print("How much money to bet?: ");
			bet = in.nextInt();
		}
		System.out.println();
		
		
		
		
		
		// player cards
		System.out.println("These are your two cards:");
		Card temp = deck.drawCard();
		player.addCard(temp);
		System.out.println(temp);
		
		temp = deck.drawCard();
		player.addCard(temp);
		System.out.println(temp);
		
		// dealer cards
		System.out.println();
		System.out.println("Dealer's card: ");
		temp = deck.drawCard();
		dealer.addCard(temp);
		System.out.println(temp);
		
		dealer.addCard(deck.drawCard());
		
		
		
		// check player values
		if (checkBlackJack(player, bet)) {
			System.out.print("Continue playing? (y or n): ");
			String ans = in.next();
			System.out.println();
			if (ans.equals("n")) return false;
			else return true;
		}
		
		// otherwise
		System.out.print("Do you want to hit? (y or n): ");
		String ans = in.next();
		System.out.println();
		while (ans.equals("y")) {
			player.addCard(deck.drawCard());		
			
			if (checkBust(player, bet)) {
				System.out.print("Continue playing? (y or n): ");
				ans = in.next();
				System.out.println();
				if (ans.equals("n")) return false;
				else return true;
			}
			if (checkBlackJack(player, bet)) {
				System.out.print("Continue playing? (y or n): ");
				ans = in.next();
				System.out.println();
				if (ans.equals("n")) return false;
				else return true;
			}
			
			System.out.println("Your cash is " + player.getCash());
			System.out.println("Your cards are: ");
			player.printDeck();
			
			System.out.print("Do you want to hit? (y or n): ");
			ans = in.next();
			System.out.println();
		}
		
		boolean bust = false;
		// dealer
		while (dealer.getTotalValue() < 17) {
			dealer.addCard(deck.drawCard());
			System.out.println("Dealer's cards: ");
			dealer.printDeck();
			System.out.println("Dealer's total value " + dealer.getTotalValue());
			
			if (checkBust(dealer, bet)) {
				System.out.println("The dealer has busted!");
				player.WonCash(bet*2);
				bust = true;
				
				System.out.print("Continue playing? (y or n): ");
				ans = in.next();
				System.out.println();
				if (ans.equals("n")) return false;
				else return true;
			}
		}
		if (!bust && dealer.totalVal >= 17) {
			calcWinner(player, dealer, bet);
			System.out.print("Continue playing? (y or n): ");
			ans = in.next();
			System.out.println();
			if (ans.equals("n")) return false;
			else return true;
		}
		
		System.out.print("Continue playing? (y or n): ");
		ans = in.next();
		System.out.println();
		if (ans.equals("n")) return false;
		else return true;

	}
	
	public static void checkDealersMoney(Player player) {
		if (player.getCash() < 0) {
			System.out.println("Resetting dealer's cash. One moment...");
			player.setCash(1000000);
		}
	}
	
	public static void calcWinner(Player player, Player dealer, int bet) {
		if (dealer.getTotalValue() == player.getTotalValue()) {
			System.out.println("Tie game. No money lost.");
		}
		else if (dealer.getTotalValue() > player.getTotalValue()) {
			System.out.println("The dealer wins");
		}
		else {
			System.out.println("You win!");
			player.WonCash(bet);
			
			if (player.getName().equals("Dealer")) checkDealersMoney(player);
		}
		
		System.out.println("Dealer's cards: ");
		dealer.printDeck();
	}
	
	public static boolean checkBust(Player player, int bet) { //check Ace
		while (player.getTotalValue() > 21 && player.getNumOfAce() > 0) {
			player.setNumOfAce(player.getNumOfAce()-1);
			player.setTotalValue(player.getTotalValue()-10);
		}
		
		if (player.getName().equals("Dealer")) checkDealersMoney(player);
		
		if (player.getTotalValue() > 21) {
			System.out.println(player.getName() + " is over 21. Bust!");
			System.out.println("These are the cards: ");
			player.printDeck();
			player.lostCash(bet);
			

			
			System.out.println("Cash: $" + player.getCash());
			return true;
		}
		return false;
	}
	
	public static boolean checkBlackJack(Player player, int bet) {
		Scanner in = new Scanner(System.in);
		if (player.getTotalValue() == 21) {
			System.out.println(player.getName() + "You hit a jackpot!");
			player.WonCash(bet*(3/2));
			System.out.println(player.getName() + " now has $" + player.getCash());
			return true;
		}
		return false;
	}
	

}

class Deck {
	ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
		deck.add(new Card("Ace", "Diamonds"));
		deck.add(new Card("Two", "Diamonds"));
		deck.add(new Card("Three", "Diamonds"));
		deck.add(new Card("Four", "Diamonds"));
		deck.add(new Card("Five", "Diamonds"));
		deck.add(new Card("Six", "Diamonds"));
		deck.add(new Card("Seven", "Diamonds"));
		deck.add(new Card("Eight", "Diamonds"));
		deck.add(new Card("Nine", "Diamonds"));
		deck.add(new Card("Ten", "Diamonds"));
		deck.add(new Card("Jack", "Diamonds"));
		deck.add(new Card("Queen", "Diamonds"));
		deck.add(new Card("King", "Diamonds"));
		
		deck.add(new Card("Ace", "Hearts"));
		deck.add(new Card("Two", "Hearts"));
		deck.add(new Card("Three", "Hearts"));
		deck.add(new Card("Four", "Hearts"));
		deck.add(new Card("Five", "Hearts"));
		deck.add(new Card("Six", "Hearts"));
		deck.add(new Card("Seven", "Hearts"));
		deck.add(new Card("Eight", "Hearts"));
		deck.add(new Card("Nine", "Hearts"));
		deck.add(new Card("Ten", "Hearts"));
		deck.add(new Card("Jack", "Hearts"));
		deck.add(new Card("Queen", "Hearts"));
		deck.add(new Card("King", "Hearts"));
		
		deck.add(new Card("Ace", "Spades"));
		deck.add(new Card("Two", "Spades"));
		deck.add(new Card("Three", "Spades"));
		deck.add(new Card("Four", "Spades"));
		deck.add(new Card("Five", "Spades"));
		deck.add(new Card("Six", "Spades"));
		deck.add(new Card("Seven", "Spades"));
		deck.add(new Card("Eight", "Spades"));
		deck.add(new Card("Nine", "Spades"));
		deck.add(new Card("Ten", "Spades"));
		deck.add(new Card("Jack", "Spades"));
		deck.add(new Card("Queen", "Spades"));
		deck.add(new Card("King", "Spades"));
		
		deck.add(new Card("Ace", "Clubs"));
		deck.add(new Card("Two", "Clubs"));
		deck.add(new Card("Three", "Clubs"));
		deck.add(new Card("Four", "Clubs"));
		deck.add(new Card("Five", "Clubs"));
		deck.add(new Card("Six", "Clubs"));
		deck.add(new Card("Seven", "Clubs"));
		deck.add(new Card("Eight", "Clubs"));
		deck.add(new Card("Nine", "Clubs"));
		deck.add(new Card("Ten", "Clubs"));
		deck.add(new Card("Jack", "Clubs"));
		deck.add(new Card("Queen", "Clubs"));
		deck.add(new Card("King", "Clubs"));

	}

	public void shuffle() {  // check
		Card temp;
		for (int i = 0; i < (52 + 52); i++) {

			int rand1 = (int) (Math.random() * 52);
			int rand2 = (int) (Math.random() * 52);

			temp = deck.get(rand2);
			deck.set(rand2, deck.get(rand1));
			deck.set(rand1, temp);
		}
	}

	public Card drawCard() {
		return deck.remove(0);
	}
}

class Player {
	String num;
	int cash;
	
	int totalVal = 0;
	int aceCounter = 0;
	ArrayList<Card> playerCards = new ArrayList<>();

	public Player(String n, int c) {
		num = n;
		cash = c;
	}

	public void setNewDeck() {
		playerCards = new ArrayList<>();
	}
	
	public void addCard(Card c) {
		playerCards.add(c);
		if (c.getNum().equals("ace")) aceCounter++;
		totalVal += c.getValue();
	}
	public int getTotalValue() {
		return totalVal;
	}
	public void setTotalValue(int t) {
		totalVal = t;
	}
	public int getNumOfAce() {
		return aceCounter;
	}
	public void setNumOfAce(int a) {
		aceCounter = a;
	}
	public void printDeck() {
		for (Card c : playerCards) {
			System.out.println(c);
		}
	}
	
	
	public String getName() {
		return num;
	}

	public void setName(String n) {
		num = n;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int c) {
		cash = c;
	}

	public void WonCash(int won) {
		cash += won;
	}

	public void lostCash(int lost) {
		cash -= lost;
	}
}

class Card {
	String num;
	String suit;
	int value;

	// based on indexes of top array
	public Card(String n, String s) {
		suit = s;
		num = n;
		setFirstValue();
	}

	public String toString() {
		return num + " of " + suit;
	}

	public String getNum() { // returns index
		return num;
	}

	public String getSuit() {
		return suit;
	}

	private void setFirstValue() {
		if (num.equals("Ace")) {
			value = 11; // Ace can also be 1
		} else if (num.equals("Jack") || num.equals("Queen")
				|| num.equals("King") || num.equals("Ten")) {
			value = 10; // Jack, Queen, King
		} else if (num.equals("Two")) {
			value = 2;
		} else if (num.equals("Three")) {
			value = 3;
		} else if (num.equals("Four")) {
			value = 4;
		} else if (num.equals("Five")) {
			value = 5;
		} else if (num.equals("Six")) {
			value = 6;
		} else if (num.equals("Seven")) {
			value = 7;
		} else if (num.equals("Eight")) {
			value = 8;
		} else if (num.equals("Nine")) {
			value = 9;
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int set) {
		value = set;
	}
}
