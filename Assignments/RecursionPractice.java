// recursively write a method that turns all the vowels to v & constants to c

import java.util.Scanner;

public class RecursionPractice {

     public static void main(String []args){
        System.out.println("Hello World");
        
        Scanner in = new Scanner(System.in);
        String s = in.next();
        
        String newy = recurse(s, 0, "");
        System.out.println(newy);
     }
     
     
     public static String recurse (String x, int i, String newy) {
         if (i >= x.length()) return newy;
         
         if (x.charAt(i) == 'a' || x.charAt(i) == 'e' || x.charAt(i) == 'i' || x.charAt(i) == 'o' || x.charAt(i) == 'u') {
             newy += "v";
         }
         else newy += "c";
         
         newy = recurse(x, i+1, newy);
         return newy;
     }
}