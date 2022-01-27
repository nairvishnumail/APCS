/*
Create an interface class called First with one method called yelena.

Create an ABSTRACT class called Second that inherits it.

DO NOT Implement yelena in Second

Create a class called Third that inherits from Second

Implement yelena in Third

Run yelena in Main from a variable of type
Third to make sure that yelena works

Turn in on google classroom Interface3
*/


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
