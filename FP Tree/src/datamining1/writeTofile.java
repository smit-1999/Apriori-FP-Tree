package datamining1;
import static datamining1.readFile.rows;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList;
import java.util.List;
import datamining1.readFile.*;
import datamining1.ruleGeneration.*;
import datamining1.findMaximalClosed.*;
import static datamining1.findMaximalClosed.max_sub_add;
import static datamining1.findMaximalClosed.max_sub_add1;



public class writeTofile {
    List<Integer[]> writefile = new ArrayList<Integer[]>();
    String filename;
    writeTofile(List<Integer[]> ls){
        this.writefile=ls;
        this.filename = filename;
        //writeClosed();
        //writeFreqItem();
        //writeClosed();
    }
    public void writeFreqItem(){
        File file = new File("C:\\Users\\Smit\\Desktop\\Data Mining-Assignment 1\\FrequentItemSets.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        try{
            fr = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fr);
            printWriter.print("1 Frequent Itemsets are");
            printWriter.println();
            printWriter.println();
            int cnt=0;
            for(int i=0;i<writefile.size();i++){
                if(writefile.get(i)[writefile.get(i).length-1]==1){                   
                    if((writefile.get(i).length!=writefile.get(cnt).length)){                   
                        Integer g =0;
                        g = writefile.get(i).length-2;
                        g.toString();
                        printWriter.println();
                        printWriter.println();
                        printWriter.print(g+" Frequent Item Sets are:");
                        printWriter.println();
                        printWriter.println();
                    }
                for(int j=0;j<writefile.get(i).length-2;j++){
                    printWriter.print(readFile.hash_map2.get(writefile.get(i)[j])+" ");
                }
                printWriter.println();
                cnt=i;
                }
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeMaximal(){
        File file = new File("C:\\Users\\Smit\\Desktop\\Data Mining-Assignment 1\\TestCase1-(500,20%)\\maximalFrequent(500,20%).txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        try{
            fr = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fr);
            for(int i=0;i<max_sub_add.size();i++){
            for(int j=0;j<max_sub_add.get(i).length-2;j++){
                printWriter.print(readFile.hash_map2.get(max_sub_add.get(i)[j])+" ");
            }
                printWriter.println();
        }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeClosed(){
        File file = new File("C:\\Users\\Smit\\Desktop\\Data Mining-Assignment 1\\TestCase1-(500,20%)\\closedFrequent(500,20%).txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        try{
            fr = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fr);
           for(int i=0;i<max_sub_add1.size();i++){
            for(int j=0;j<max_sub_add1.get(i).length-2;j++){
                printWriter.print(readFile.hash_map2.get(max_sub_add1.get(i)[j])+" ");
            }
            printWriter.println();
        }
           printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
