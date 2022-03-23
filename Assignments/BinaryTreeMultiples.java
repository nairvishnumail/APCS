public class BinaryTreeMultiples {
	public static void main(String[] args) {
		Number trying = new Number(1);
		numTree(trying);
		System.out.println();
		displayMult(trying);
		
	}

	// creates a number tree with any number inputed by a user in the main
	// This must be a recursive method.
	public static void numTree (Number x) {
		int val = x.getVal();
		int factor = val - 1;
		 
		boolean done  = false;
		while (factor > 1 && !done) {
			if (val%factor == 0) {
				done = true;
				
				Number next = new Number(factor);
				Number small = new Number(val/factor);
				
				System.out.println("next: " + factor);
				System.out.println("small: " + (val/factor));
				
				x.setLeft(small);
				x.setRight(next);
				
				numTree(next);
				
			}
			factor--;
		}
	}
	
	// traverses the number tree and prints out the lowest multiples. (i.e. 54 -> 2 3 3 3)
	public static void displayMult (Number x) {
		System.out.print(x.getVal() + " ->");
		
		Number t = x;
		
		while (t.getLeft() != null) {
			
			System.out.print(" " + t.getLeft().getVal());
			t = t.getRight();
		}
		System.out.print(" " + t.getVal());
	}
}

class Number {
	// attributes
	private int val;
	private Number left = null;
	private Number right = null;
	
	// getters & setters
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public Number getLeft() {
		return left;
	}
	public void setLeft(Number left) {
		this.left = left;
	}
	public Number getRight() {
		return right;
	}
	public void setRight(Number right) {
		this.right = right;
	}
	
	// constructor
	public Number(int val, Number left, Number right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	public Number(int val) {
		this.val = val;
	}
	
	
	
}