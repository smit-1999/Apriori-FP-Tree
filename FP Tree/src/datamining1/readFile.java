package datamining1;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; 
import java.util.ArrayList; import java.util.List;
import datamining1.writeTofile.*;


public class readFile {
    public static int COUNT=0;
    List<Integer[]> ls = new ArrayList<Integer[]>(); 
    public String path_name;
    public  List<String[]> data = new ArrayList<>();
    public  List <String> uniqueItems = new ArrayList<String>();
    public static int rows =0 ;
    public  List <Integer[]> supportCount = new ArrayList<Integer[]>();
    public static int min_support;
    public static int confidence;
    public static HashMap<String, Integer> hash_map1 = new HashMap<String, Integer>();
    public static HashMap<Integer, String> hash_map2 = new HashMap<Integer, String>();
    
    readFile(String s,int m,int confidence){
            path_name = s;
            min_support = m;
            this.confidence= confidence;
            read();
    }
   public void createUniqueItems(){
       for(int i = 0;i<rows;i++){
           for(int j=0;j<data.get(i).length;j++){
              // System.out.print(data.get(i)[j]+" ");
              int flag=0;
               if(i==0&&j==0) uniqueItems.add(data.get(0)[0]);
               
                
              for(int k=0;k<uniqueItems.size();k++){
                   if(data.get(i)[j].equals( uniqueItems.get(k))&&flag==0){
                      //System.out.println("ffff"+i+j);
                       flag = 1;
                       break;
                   }                   
               }
              if(flag == 0 && data.get(i)[j]!="") uniqueItems.add(data.get(i)[j]);
               
               flag = 0;
           }
         
       }
         sort(); 
 
     
    }
   public void read(){
     try{  
       FileReader fr =  new FileReader(path_name);
       BufferedReader br = new BufferedReader(fr);
       String t;
       System.out.println(path_name);
       while((t=br.readLine())!=null){          
           String[] attributes = t.split(",");     
           rows+=1;
           data.add(attributes);
       }
      createUniqueItems();
     }
     catch(IOException e){
         System.out.println("error reading file"+e);
     }
       
   }
public void sort(){
    java.util.Collections.sort(uniqueItems);
    //System.out.print(uniqueItems);
    findSupport();
}

public void findSupport(){
    
    for(int i = 0;i<uniqueItems.size();i++){
        Integer a[] = {i+1,0,0};
        supportCount.add(a);
        //ls.add(a);
    }
    for(int k = 0;k<uniqueItems.size();k++){
    for(int i = 0;i<rows;i++){
           for(int j=0;j<data.get(i).length;j++){
                if(data.get(i)[j].equals (uniqueItems.get(k))){
                    supportCount.get(k)[1]++;
                    //ls.get(k)[1] = supportCount.get(k)[1];
                }
           }
    }
    }
    
    for(int i=0;i<supportCount.size();i++){
       if(supportCount.get(i)[1]>=min_support){
           supportCount.get(i)[2] = 1;
           //ls.get(i)[2]=supportCount.get(i)[2];
       }
    }
    for(int i=0;i<supportCount.size();i++){
        if(supportCount.get(i)[2] == 1)
        System.out.println(uniqueItems.get(i)+" "+supportCount.get(i)[1]+" "+supportCount.get(i)[2]);
    }
   createHashmap();
   
   
   MergeCandidates mc;
   
//= new MergeCandidates(supportCount,min_support,rows,data,hash_map1,hash_map2);
   //amc[0].aCK=supportCount;//.aCk=supportCount;
   /*for(int y=0;y<supportCount.size();y++){
   Integer[] arr = new Integer[supportCount.get(y).length]; 
   arr = supportCount.toArray(arr);
   ls.add(arr);
   }*/
    
   mc=new MergeCandidates(supportCount,min_support,rows,data,hash_map1,hash_map2,supportCount,confidence);
   findMaximalClosed jet = new findMaximalClosed(MergeCandidates.sd);   
   //writeTofile write_file1 = new writeTofile(MergeCandidates.sd);     
   //ruleGenerationWriting wer  = new ruleGenerationWriting(MergeCandidates.sd,readFile.confidence);
   redundantRules rrules = new redundantRules(MergeCandidates.sd,findMaximalClosed.max_sub_add1);
   rrules.findRedundant();
    
   
       // System.out.println(i+" "+supportCount.get(i)[1]);
}

public void createHashmap(){
    
    for(int i = 0;i<uniqueItems.size();i++){
        
        hash_map1.put(uniqueItems.get(i),i+1);
        hash_map2.put(i+1,uniqueItems.get(i));
        //System.out.println("The Value is: " + hash_map1.get(uniqueItems.get(i))+" "+hash_map2.get(i+1));
        
    }    
   
}


   }
   
    

