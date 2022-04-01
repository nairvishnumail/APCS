import java.util.ArrayList;

public class IllegalExpressions {
	public static void main(String[] args) {
		
		//ArithmeticException();
		//NullPointerException();
		//ClassCastException(); 
		//ArrayIndexOutOfBoundsException();
		//IndexOutOfBoundsException(); 
		//IllegalArgumentException(); 
	}
	
	
	public static void ArithmeticException() {
		int x = 100/0;
	}
	public static void NullPointerException() {
		String x = null;
		System.out.println(x.length());
	}
	public static void ClassCastException() {
		Heel x = new Shoe(1, "bl", "nike");
	}
	public static void ArrayIndexOutOfBoundsException() {
		int[] x = {};
		System.out.println(x[0]);
	}
	public static void IndexOutOfBoundsException() { 
		ArrayList<Integer> a = new ArrayList<>();
        a.add(2); 
        System.out.println(aL.get(5));
	}
	public static void IllegalArgumentException() {
        Shoe s = new Shoe(1, null);
        if (s.getColor() == null)
        {
          throw new IllegalArgumentException("not a real shoe");
        }
	}
}
