
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
public class MergeCandidates {
    public int FLAG=0;
    public int confidence;
    public int COUNT;
    public List<Integer[]> Ck; 
    public static List<Integer[]> sd;
    public List<Integer[]> Ckupda = new ArrayList<Integer[]> ();
    public  List<String[]> data;
    public HashMap<String, Integer> hash_map1;
          public  HashMap<Integer,String > hash_map2;
   int min_support;
   int rows;
    MergeCandidates(List<Integer[]> as,int min_support,int rows,List<String[]> data,HashMap<String, Integer> hash_map1,HashMap< Integer,String> hash_map2,List<Integer[]> sd,int confidence){
     this.COUNT=COUNT;
     this.data=data;
     this.rows=rows;
     this.min_support=min_support;
     this.hash_map1= hash_map1;
     this.hash_map2 = hash_map2; 
     this.sd=sd;
     this.Ck = as; 
     this.confidence = confidence;    
     join();
    }
    public void join(){
        FLAG++;       
        merge();       
   }
    public void merge(){
        //System.out.println("hi"+FLAG);
    int cnt=0;
    List<Integer> uniqueofCk = new ArrayList<Integer>();    
    
    for(int i =0;i<Ck.size();i++){
        for(int j=0;j<Ck.get(0).length -2;j++){
            int flag=0;
            for(int k=0;k<uniqueofCk.size();k++){
                if(Ck.get(i)[j] == uniqueofCk.get(k) ) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0 && Ck.get(i)[Ck.get(0).length -1]==1) uniqueofCk.add(Ck.get(i)[j]);
            flag = 0;
        }
    }
    System.out.println(uniqueofCk);
    for(int p=0;p<uniqueofCk.size();p++)
        System.out.println(hash_map2.get(uniqueofCk.get(p)));
   
    
    
    
   Integer mark[] = new Integer[uniqueofCk.size()];
   HashMap<Integer,Integer> hash_uniqueCkindex = new HashMap<Integer, Integer>();
     for(int i =0;i<uniqueofCk.size();i++) {
         mark [i] = 0;
         hash_uniqueCkindex.put(uniqueofCk.get(i),i);
     }     
   for(int i =0;i<Ck.size();i++){
       if(Ck.get(i)[Ck.get(0).length-1] == 1){
           
       
     Integer tmp[] = new Integer[(Ck.get(0).length)+1];
       for(int k =0;k<(Ck.get(0).length) -2;k++) {
           tmp[k] = Ck.get(i)[k];         
           mark[hash_uniqueCkindex.get(tmp[k])] = 1;
       }
           
       
        for(int j=i+1;j<Ck.size();j++){
            if(Ck.get(j)[(Ck.get(0).length)-1] == 1){                
            
            for(int l =0;l<(Ck.get(0).length) -2;l++){
                
                if(mark[hash_uniqueCkindex.get(Ck.get(j)[l])] == 0){
                    mark[hash_uniqueCkindex.get(Ck.get(j)[l])] = 1;                    
                    tmp[Ck.get(0).length -2] = Ck.get(j)[l];
                    tmp[(Ck.get(0).length)-1]=0;
                    tmp[(Ck.get(0).length)]=0;                    
                    Integer tmp2 [] = new Integer[(Ck.get(0).length)+1];
                    for(int u=0;u<tmp2.length;u++){
                    tmp2[u]=tmp[u];
                    }
                    
                    Ckupda.add(tmp2);               
            
                    cnt++;
                                  
                }
            }           
            
        }
       }
     
        
       
       
        
        
        
   }
       for(int p =0;p<uniqueofCk.size();p++) mark [p] = 0;
   }
   
   
   // System.out.println("before finsupport");
         /*for(int p=0;p<Ckupda.size();p++){
     for(int q=0;q<Ckupda.get(p).length;q++){
         System.out.print(Ckupda.get(p)[q]+" ");
     }
     System.out.print("\n");
  }*/
         
         
        
        
       
       finSupport(Ckupda);
       //readFile.amc[COUNT].aCK=Ckupda;
   /*    amc[COUNT].aCK = new ArrayList<Integer[]>();*/ 
       for(int i=0;i<Ckupda.size();i++){       
           sd.add(Ckupda.get(i));  
       }
       
       //System.out.println("called");
       ////////////////
   System.out.print("\n");
   
   
  for(int p=0;p<Ckupda.size();p++){
     for(int q=0;q<Ckupda.get(p).length;q++){
         if(q<Ckupda.get(p).length-2)  System.out.print(readFile.hash_map2.get(Ckupda.get(p)[q])+" ");
         else System.out.print(Ckupda.get(p)[q]+" ");
     }     
     System.out.print("\n");
  }
  
  
      int flagg=0;
for(int k=0;k<Ckupda.size();k++){
if(Ckupda.get(k)[Ckupda.get(k).length-1] == 1)
    flagg=1;
}
  ruleGeneration rg =new ruleGeneration(Ckupda,sd,confidence);
  if(Ckupda.size() != 0 && flagg==1){  
  MergeCandidates nmc = new MergeCandidates(Ckupda,min_support,rows,data,hash_map1,hash_map2,sd,confidence);
  }
       
      
           
    }

   

public void finSupport(List<Integer[]> Ckupda){
   int flag;
//   System.out.println(Ckupda.get(1)[1]);
   
 for(int k = 0;k<Ckupda.size();k++){
        
       for(int i = 0;i<rows;i++){
           flag=0;
           ///////////System.out.print(" "+i);
           //System.out.print(" "+rows);
   for(int l=0;l<Ckupda.get(k).length-2;l++){
                      //  for(int i = 0;i<rows;i++){
                     /////////// System.out.print(" "+Ckupda.get(k)[l]);
                           for(int j=0;j<data.get(i).length;j++){
                           if(data.get(i)[j].equals (hash_map2.get(Ckupda.get(k)[l]))){
   
                           flag++;
                           break;}
                           }
                           
                           
        }
  /////////// System.out.print(" "+(Ckupda.get(k)[Ckupda.get(k).length- 2] ));
   ////////////System.out.print(" "+flag);
        if(flag==Ckupda.get(k).length - 2){
        (Ckupda.get(k)[Ckupda.get(k).length- 2] )++; 
        }
    /////////////System.out.print(" "+(Ckupda.get(k)[Ckupda.get(k).length- 2] ));
    ///////////System.out.println();
 }
 }
 //}

    for(int i=0;i<Ckupda.size();i++){
        if((Ckupda.get(i)[Ckupda.get(i).length- 2] )>=min_support){
           (Ckupda.get(i)[Ckupda.get(i).length- 1] )= 1;
                }
    }

}




    
}
