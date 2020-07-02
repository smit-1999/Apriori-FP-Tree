

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining1;
import java.io.*;
import java.util.*;
/**
 *
 * @author Smit
 */
public class DataMining1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String file_name;
        int min_support,confidence;
        System.out.println("Please enter the file name,support and confidence va"
                + "lues.The file should be present in dataset folder");
        Scanner sc = new Scanner(System.in);
        file_name = sc.nextLine();
        min_support = sc.nextInt();
        confidence = sc.nextInt();
        
        readFile rf = new readFile (System.getProperty("user.dir") + "/dataset/" 
                + file_name,min_support,confidence);
      
      //  System.out.println(file_name + support + confidence);
      
    }
    
}
