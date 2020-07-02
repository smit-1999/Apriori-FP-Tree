package datamining1;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; 
import java.util.ArrayList; import java.util.List;
import datamining1.readFile.*;

public class findMaximalClosed {
    public static List <Integer[]> max_sub_find = new ArrayList<Integer[]>();
    public static List <Integer[]> max_sub_add  = new ArrayList<Integer[]>();
    public static List <Integer[]> max_sub_add1  = new ArrayList<Integer[]>();
    findMaximalClosed(List sd){
        this.max_sub_find = sd;
        maximal_find();
        closed_find();
    }
    public void maximal_find(){
        for(int i=0;i<max_sub_find.size();i++){
            int flag=0;
            if(max_sub_find.get(i)[max_sub_find.get(i).length-1]==1){            
            for(int j=i;j<max_sub_find.size();j++){                
                if(max_sub_find.get(i).length == max_sub_find.get(j).length-1){
                    int check=0;                    
                    for(int k=0;k<max_sub_find.get(i).length-2;k++){
                        for(int l=0;l<max_sub_find.get(j).length-2;l++){
                            if(max_sub_find.get(i)[k] == max_sub_find.get(j)[l]){
                                check++;
                            }
                        }
                        
                    }
                    if(check == max_sub_find.get(i).length-2){
                        if(max_sub_find.get(j)[max_sub_find.get(j).length-1]==1){
                            flag=1;                            
                        }
                    }
                    check=0;              
                }
            }
            if(flag==0){
               max_sub_add.add(max_sub_find.get(i)); 
            }
        }
            flag=0;
        }
        System.out.println("Maximal Subsets are");
        for(int i=0;i<max_sub_add.size();i++){
            for(int j=0;j<max_sub_add.get(i).length-2;j++){
                System.out.print(readFile.hash_map2.get(max_sub_add.get(i)[j])+" ");
            }
            System.out.println();
        }
        System.out.println("Maximal Subsets end");
    }
      public void closed_find(){
        for(int i=0;i<max_sub_find.size();i++){
            int flag=0;
            if(max_sub_find.get(i)[max_sub_find.get(i).length-1]==1){            
            for(int j=i;j<max_sub_find.size();j++){                
                if(max_sub_find.get(i).length == max_sub_find.get(j).length-1){
                    int check=0;                    
                    for(int k=0;k<max_sub_find.get(i).length-2;k++){
                        for(int l=0;l<max_sub_find.get(j).length-2;l++){
                            if(max_sub_find.get(i)[k] == max_sub_find.get(j)[l]){
                                check++;
                            }
                        }
                        
                    }
                    if(check == max_sub_find.get(i).length-2){
                        if(max_sub_find.get(j)[max_sub_find.get(j).length-2]==max_sub_find.get(i)[max_sub_find.get(i).length-2]){
                            flag=1;                            
                        }
                    }
                    check=0;              
                }
            }
            if(flag==0){
               max_sub_add1.add(max_sub_find.get(i)); 
            }
        }
            flag=0;
        }
        System.out.println();
        System.out.println("Closed Subsets are");
        for(int i=0;i<max_sub_add1.size();i++){
            for(int j=0;j<max_sub_add1.get(i).length-2;j++){
                System.out.print(readFile.hash_map2.get(max_sub_add1.get(i)[j])+" ");
            }
            System.out.println();
        }
        
        /*String name="Closed Frequent Itemset(700,15)";
        writeFile wf=new writeFile(max_sub_add,hash_map2,name);
        wf.write1();*/
        
        
    }
    
    
}
