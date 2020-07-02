
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

public class MergeCandidates {
    List<Integer[]> Ck ;
    List<Integer[]> Ckupda = new ArrayList<Integer[]> ();
    public static List<Integer> uniqueofCk = new ArrayList<Integer>();
   
    MergeCandidates(List<Integer[]> as){
     Ck = as;     
     join();
    }
    public void join(){      
        merge();
       // hashTreeGeneration hst = new hashTreeGeneration(Ckupda,4,Ckupda.get(0).length);
        /*  addtoHashtree();
        subsetofeachtrnsction();
         searchinhashtree();
         incrementcountinhash();
         removeinfrequentfromck();
        rulegenerateofupdatedck();
          writecktofreq.data();
          writerulestorule.data();       
        
        
        if(Ck.get(0).size<=uniqueitems.getsize())
        MergeCandidates m1 = new MergeCandidates(Ckupdate,k+1);
    */}
    public void merge(){
    int cnt=0;
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
  //System.out.println(uniqueofCk.size());
    
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
                    /*for(int p=0;p<cnt;p++){
                    for(int q=0;q<Ckupda.get(0).length;q++){
                            System.out.print(Ckupda.get(p)[q]);
                    }
                    System.out.println();
                    } */                   
                }
            }           
            
        }
       }
        
   }
       for(int p =0;p<uniqueofCk.size();p++) mark [p] = 0;
   }
   
  /*for(int p=0;p<Ckupda.size();p++){
     for(int q=0;q<=Ckupda.get(0).length-1;q++){
         System.out.print(Ckupda.get(p)[q]+" ");
     }
     System.out.print("\n");
  }*/
        
        
           
    }

    
}
