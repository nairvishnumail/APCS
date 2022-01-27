/*
1.      
Create a class that implements:

public interface MSJstuff {
  public void displaylhospitalRule();
  public boolean isLiu( String x );
  public int round( double a ) //round .5 up and below down  
}
*/

public class InterfaceLabProblem1 implements MSJstuff{
	public void displaylhospitalRule() {
		System.out.println("It's a math rule");
	}
	public boolean isLiu (String x) {
		if (x.equals("Liu")) {
			return true;
		}
		else return false;
	}

	public int round(double a) {
		if (a - (int) a >= 0.5) {
			return (int) (a+1);
		}
		else return (int) a;
	}
}

interface MSJstuff {
	public void displaylhospitalRule();
	public boolean isLiu(String x);
	public int round(double a); //round 0.5 up and below down
}
