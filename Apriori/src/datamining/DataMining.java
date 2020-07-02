
package datamining;
import java.io.*;
import java.util.*;

public class DataMining {

    public static int min_support,confidence;
    public static void main(String[] args) {

        String file_name;
        System.out.println("Please enter the file name,support and confidence va"
                + "lues.The file should be present in dataset folder");
        Scanner sc = new Scanner(System.in);
        file_name = sc.nextLine();
        min_support = sc.nextInt();
        confidence = sc.nextInt();
        
        readFile rf = new readFile (System.getProperty("user.dir") + "/dataset/"+
                file_name,min_support);  
      
    }
    
}
