package datamining;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files;
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.List;


public class readFile {
    String path_name;
    List<String[]> data = new ArrayList<>();
    List<int[]> sortedData = new ArrayList<>();
    public static List <String> uniqueItems = new ArrayList<String>();    
    List <Integer[]> supportCount = new ArrayList<Integer[]>();
    int rows =0 ;
    public static int min_support;
    public static  HashMap<String, Integer> hash_map1 = new HashMap<String, Integer>();
    public static HashMap<Integer, String>  hash_map2 = new HashMap<Integer, String>();
    public static HashMap <Integer,Integer> fp = new HashMap<Integer,Integer>();
    
    
    readFile(String s,int m){
            path_name = s;
            min_support = m;
            read();
    }
    
    
    public void createUniqueItems(){
        for(int i = 0;i<rows;i++){
            for(int j=0;j<data.get(i).length;j++){ 
                int flag=0;
                if(i==0&&j==0) uniqueItems.add(data.get(0)[0]);
               
                
                for(int k=0;k<uniqueItems.size();k++){
                    if(data.get(i)[j].equals( uniqueItems.get(k)) && flag == 0){  
                       flag = 1;
                       break;
                   }                   
                }
                if(flag == 0 && data.get(i)[j] != "") uniqueItems.add(data.get(i)[j]);
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
            System.out.println("Error reading file"+e);
        }
       
    }
    
    
    public void sort(){
    
        java.util.Collections.sort(uniqueItems);
        findSupport();
    
    }
    
    
    public void findSupport(){
    
        for(int i = 0;i<uniqueItems.size();i++){
            Integer a[] = {i+1,0,0};
            supportCount.add(a);
        }
    
    
        for(int k = 0;k<uniqueItems.size();k++){
            for(int i = 0;i<rows;i++){
                for(int j=0;j<data.get(i).length;j++){
                    if(data.get(i)[j].equals (uniqueItems.get(k))){
                    supportCount.get(k)[1]++;                    
                    }
                }
            }
        }
    
    for(int i=0;i<supportCount.size();i++){
        if(supportCount.get(i)[1]>=min_support){
        supportCount.get(i)[2] = 1;
       }
    }
    MergeCandidates mc = new MergeCandidates(supportCount);
    createHashmap();
    

    }

    public void createHashmap(){   
    
        for(int i = 0;i<uniqueItems.size();i++){        
            hash_map1.put(uniqueItems.get(i),i+1);
            hash_map2.put(i+1,uniqueItems.get(i));
            fp.put(i+1,supportCount.get(i)[1]);           
        }
    
        sorttransactions();
   
    }
    
    
    public static void sortbyColumn(int arr[][], int col){ 
     
        // Using built-in sort function Arrays.sort 
        Arrays.sort(arr, new Comparator<int[]>() { 
            
          @Override              
          // Compare values according to columns 
        public int compare(final int[] entry1,  
                             final int[] entry2) { 
  
            // To sort in descending order revert  
            // the '>' Operator 
            if (entry1[col] < entry2[col]) 
                return 1; 
            else
                return -1; 
          } 
        });  // End of function call sort(). 
    } 
    
    
    public void sorttransactions(){
    
        for(int i = 0;i<data.size();i++){
            int [][] tmp =new int [data.get(i).length][2];
            int columnData[] = new int[data.get(i).length];
            
            for(int j =0;j<data.get(i).length;j++){
                if(fp.get(hash_map1.get((data.get(i)[j]))) >= min_support){
                    tmp[j][0] = fp.get(hash_map1.get((data.get(i)[j])));
                    tmp[j][1] = hash_map1.get(data.get(i)[j]);
                }
            }
            
        sortbyColumn(tmp,0);
        
        for(int k=0;k<data.get(i).length;k++) columnData[k] = tmp[k][1]; 
        
        sortedData.add(columnData);
    
        }   
    
        startTreeconstruction();
    }
    
    
    public void t(){
        
        for(int h = 0;h<sortedData.size();h++){
            for(int i = 0;i<sortedData.get(h).length;i++)
                System.out.print(sortedData.get(h)[i]+" ");
                System.out.println();
        }
        
    }
    
    
    public void startTreeconstruction(){
        
        fpTree f = new fpTree(sortedData,uniqueItems.size());
        f.createTree();    
        f.prnt_1(); 
        
        for(int y =0 ;y < uniqueItems.size(); y++){
            fpTree  dup = new fpTree(sortedData,uniqueItems.size());
            dup.createTree();
            dup.callprefixTree(dup.root,y);
        }
        

    }   
}


   


