
package datamining1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import datamining1.readFile.*;

public class ruleGenerationWriting{
    int confidence;
    
        List<Integer[]> war;
        List<Integer[]> subset;
        PrintWriter pw =null;
        File file1 = null;
        FileWriter fr1 = null;
        ruleGenerationWriting( List<Integer[]> war1,int confidence){      
        this.war = war1;
        this.confidence = confidence;
        try{
            file1 = new File("C:\\Users\\Smit\\Desktop\\Data Mining-Assignment 1\\AssociationRuleGeneration.txt");
            fr1 = new FileWriter(file1);
            pw = new PrintWriter(fr1);    
            int o=0;
        
        for(int i=0;i<war.size();i++){ 
            if(war.get(i)[war.get(i).length-1]==1){
            //System.out.println("Writing to file");
            int n=war.get(i).length-2;
             List <Integer >ar= new ArrayList<Integer>() ;            
            for(int j=0;j<war.get(i).length-2;j++){
            ar.add(war.get(i)[j]);
            }           
            subSet(ar,pw);
            pw.println();
            }
           
                    
        }
        if(pw!=null)  pw.close();
        }catch(IOException e){
            e.printStackTrace();            
        }
        finally{
            
        }
        }
    
        void subSet(List<Integer> ar,PrintWriter pw){       
        List<Integer> set = ar;
        int n = ar.size(); 
        List<List<Integer>> abc = new ArrayList<List<Integer>>();       
        for(int i = 0; i < (1<<n); i++) 
        {           
            List<Integer> a = new ArrayList<Integer>(); 
            int k=0;            
            for (int j = 0; j < n; j++)  
                if ((i & (1 << j)) > 0){
                   if(set.get(j)!=0){
                    a.add(set.get(j));                    
                   }                  
                }                 
            abc.add(a);            
        }     
        List<List<Integer>> left = new ArrayList<List<Integer>> ();
        List<List<Integer>> right = new ArrayList<List<Integer>> ();
        
        for(int i=1;i< (1 << n-1);i++){            
            left.add(abc.get(i));                        
            right.add(abc.get((1 << n)-1-i));            
        } 
            
            List<Integer> supp = new ArrayList<Integer>();
            for(int q=0;q<left.size();q++){
            int cntl=0,cntr=0;
            for(int d=0;d<left.get(q).size();d++){                
            supp.add(left.get(q).get(d)); 
            cntl++;
            }
            for(int w=0;w<right.get(q).size();w++){                 
            supp.add(right.get(q).get(w));
            cntr++;
            }            
        Integer[] arr = new Integer[supp.size()]; 
        Integer[] brr = new Integer[cntl];
        Integer[] crr = new Integer[cntr];
        for(int k=0;k<left.get(q).size();k++){
        brr[k]=left.get(q).get(k);
        }
       for(int k=0;k<right.get(q).size();k++){
        crr[k]=right.get(q).get(k);
        }
        arr = supp.toArray(arr);
        for(int x=0;x<arr.length;x++){
        }
         double h = (double)find_global_support(arr);
         System.out.println();               
         double r = (double)find_global_support(brr);
         double right_support  = (double)find_global_support(crr);
         double t_left = (h/r)*100;
         double t_right = (h/right_support)*100;
         if(t_left>=(double)confidence){      
          for(int j=0;j<left.get(q).size();j++){
            if(left.get(q).get(j)!=null){                
                pw.print(" "+readFile.hash_map2.get(left.get(q).get(j))+" ");
            }
           pw.print("( "+Double.toString(r)+" ) ");
        }
          pw.print(" --> ");
          for(int j=0;j<right.get(q).size();j++){
            if(right.get(q).get(j)!=null){
              pw.print(" "+readFile.hash_map2.get(right.get(q).get(j)));
            }
            pw.print(" ( " + right_support+" ) ");
        } 
          pw.print(" Confidence: "+Double.toString(t_left));
          pw.println();         
        }
         /*else{
             System.out.println("Not frequent ones left");         
          for(int j=0;j<left.get(q).size();j++){
            if(left.get(q).get(j)!=null){              
              System.out.print(" "+left.get(q).get(j));
            }           
        }
          System.out.print("-->");
          for(int j=0;j<right.get(q).size();j++){
            if(right.get(q).get(j)!=null){
              System.out.print(" "+right.get(q).get(j));
            }            
        }           
          System.out.println();
         }*/
         
         if(t_right>=(double)confidence){  
               for(int j=0;j<right.get(q).size();j++){
            if(right.get(q).get(j)!=null){
                 pw.print(" "+readFile.hash_map2.get( right.get(q).get(j))+" ");
            }
        }
               pw.print("( "+Double.toString(right_support)+" )");
               pw.print(" --> ");
          for(int j=0;j<left.get(q).size();j++){
            if(left.get(q).get(j)!=null){
                 pw.print(" "+readFile.hash_map2.get(left.get(q).get(j))+" ");
            }
            pw.print("( "+Double.toString(r)+" ) ");
            pw.print("Confidence: "+Double.toString(t_right));
            pw.println();
        }         
        }
         /*else{
         System.out.println("Not frequent ones right");
          for(int j=0;j<left.get(q).size();j++){
            if(left.get(q).get(j)!=null){
              System.out.print(" "+left.get(q).get(j));
            }
        }
          System.out.print("-->");
          for(int j=0;j<right.get(q).size();j++){
            if(right.get(q).get(j)!=null){
              System.out.print(" "+right.get(q).get(j));
            }
            System.out.println();
        }
         }*/
         supp.clear();
     }
    } 
    public int find_global_support(Integer[] a){      
        int support =0;
        int check = 0; 
        int check_again = 0;
        for(int i=0;i<war.size();i++){
            if(war.get(i).length-2 == a.length ){
            for(int j=0;j<war.get(i).length-2;j++){
                for(int k=0;k< a.length;k++){
                    if(a[j]==war.get(i)[k]){
                        check_again++;
                        break;
                    }
                }
            }
            if(check_again == a.length){
            check=1;
            }
            else{
                check=0;
            }
            if(check==1){
                support = war.get(i)[war.get(i).length-2];
                break;
            }
            }
            check=0;
            check_again=0;
        }
        return support;
    }
}
