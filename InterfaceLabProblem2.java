/*
Create an interface class called First with one method called yelena.


Create an ABSTRACT class called Second that inherits it.


Implement yelena in Second


Create a class called Third that inherits from Second


Run yelena in Main from a variable of type
Third to make sure that yelena works
*/


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
