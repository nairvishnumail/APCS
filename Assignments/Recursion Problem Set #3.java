public class Main {
    public static void main(String args[]) {
      DM(5);
      printNum(3);
      System.out.println();
      Binary(8);
      System.out.println();
      
    }
// 3. 
    public static void Binary(int a) {
        if (a == 0) return;
        
        Binary((int)(a/2));
        
        if ((a - (2*(int)(a/2))) == 1) System.out.print(1);
        if ((a - (2*(int)(a/2))) == 0) System.out.print(0);
        
    }
    
    
// 2.
    public static void printNum(int a) {
        if (a == 0) return;

        
        printNum(a-1);
        if (a == 1) {
            System.out.print(a);
            return;
        }
        System.out.print("," + a);
    }
    
    
// 1.     
    public static void DM(int a) {
        if (a == 1) System.out.println("*");
        else {
            DMprint(a);
            DM(a-1);
            DMprint(a);
        }
    }
    public static void DMprint(int a) {
        for (int i = 0; i < a; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}