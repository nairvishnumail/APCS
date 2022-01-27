
public class InterfaceLabProblem2 { 
	public static void main (String[] args) {
		Third t = new Third();
		t.yelena();
	}
}

interface First {
	public void yelena();
}

abstract class Second implements First {
	public abstract void yelena();
}

class Third extends Second{

	public void yelena() {
		System.out.println("yelena");
	}
	
}