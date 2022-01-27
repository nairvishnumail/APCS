
public class InterfaceLabProblem3 {

	public static void main(String[] args) {
		Third t = new Third();
		t.yelena();
	}
}


interface First {
	public void yelena();
}

abstract class Second implements First {
}

class Third extends Second {
	public void yelena() {
		System.out.println("yelena");
	}
}
