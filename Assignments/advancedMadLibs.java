import java.io.*;
import java.util.*;

public class advancedMadLibs {
    public static void main(String[] args) {
        try {
            FileWriter write = new FileWriter("C:\\Users\\vishnu\\eclipse-workspace\\APCS\\Work\\newStory.txt");
            File original = new File("C:\\Users\\vishnu\\eclipse-workspace\\APCS\\Work\\original.txt");
            
            Scanner input = new Scanner(original);
            while (input.hasNextLine()) {
            	
                String[] arr = input.nextLine().split(" ");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].contains("Lion")) {
                        arr[i] = arr[i].substring(0, arr[i].indexOf("Lion")) + "Bully" + arr[i].substring(arr[i].indexOf("Lion") + "Lion".length());
                    }
                    if (arr[i].contains("lion")) {
                        arr[i] = arr[i].substring(0, arr[i].indexOf("lion")) + "bully" + arr[i].substring(arr[i].indexOf("lion") + "lion".length());
                    }
                }
                
                String s = "";
                for (int x = 0; x < arr.length; x++) {
                    s += arr[x] + " ";
                }
                write.write(s);
            }
            write.close();
            
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
