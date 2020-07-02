package datamining1;

import java.util.ArrayList;
import java.util.List;
public class ruleGeneration{
        int confidence;
        List<Integer[]> Ckupda ;
        List<Integer[]> war;
        
        ruleGeneration( List<Integer[]> Ckupda,List<Integer[]> war,int confidence){
            //System.out.println("hi i am in rule generation");
            this.Ckupda=Ckupda;
            this.war = war;
            this.confidence = confidence;
            start();
         }
        
    void start (){
        for(int u=0;u<war.size();u++){         
        for(int f=0;f<war.get(u).length;f++){
        if(f<war.get(u).length-2)  System.out.print(readFile.hash_map2.get(war.get(u)[f])+" ");  
        else  System.out.print(war.get(u)[f]+" ");        
        }
        System.out.println();
        }       
        List<Integer[]> subset;
        for(int i=0;i<Ckupda.size();i++){
            //if(Ckupda.get(i)[Ckupda.get(i).length-1]==1&&Ckupda.get(i).length!=Ckupda.get(Ckupda.size()-1).length){
            int n=Ckupda.get(i).length-2;
             List <Integer >ar= new ArrayList<Integer>() ;            
            for(int j=0;j<Ckupda.get(i).length-2;j++){
            ar.add(Ckupda.get(i)[j]);
            }           
            subSet(ar);               
            System.out.println();
            System.out.println();            
          
        }
    }
    
        void subSet(List<Integer> ar){       
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
        System.out.println("This is left side");
        for(int k=0;k<left.get(q).size();k++){
        brr[k]=left.get(q).get(k);
        System.out.print(" "+readFile.hash_map2.get(brr[k]));
        }
        System.out.println();
        System.out.println("This is right side");
       for(int k=0;k<right.get(q).size();k++){
        crr[k]=right.get(q).get(k);
        System.out.print(" "+readFile.hash_map2.get(crr[k]));
        }
        arr = supp.toArray(arr);
        System.out.println();
        System.out.println("Combined array is :");
        for(int x=0;x<arr.length;x++){
        System.out.print(" "+readFile.hash_map2.get(arr[x])+" ");
        }
         double h = (double)find_global_support(arr);
         System.out.println();
         System.out.println("the support value for combined is:"+h);                  
         double r = (double)find_global_support(brr);
         double right_support  = (double)find_global_support(crr);
         System.out.println("the left support value is:"+r);
         double t_left = (h/r)*100;
         double t_right = (h/right_support)*100;
         System.out.println("left confidence value is:" + t_left);
         if(t_left>=(double)confidence){      
          for(int j=0;j<left.get(q).size();j++){
            if(left.get(q).get(j)!=null){              
              System.out.print(" "+readFile.hash_map2.get(left.get(q).get(j)));
            }            
            }
          System.out.print( "( " +r+ ") ");
          System.out.print(" --> ");
          for(int j=0;j<right.get(q).size();j++){
            if(right.get(q).get(j)!=null){
              System.out.print(" "+readFile.hash_map2.get(right.get(q).get(j)));
            }            
        } 
          System.out.print("(" + right_support+")");
          System.out.println();         
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
                 System.out.print(" "+readFile.hash_map2.get(right.get(q).get(j)));
            }
        }
               System.out.print("(" + right_support+")");
                 System.out.print("-->");
          for(int j=0;j<left.get(q).size();j++){
            if(left.get(q).get(j)!=null){
                 System.out.print(" "+readFile.hash_map2.get(left.get(q).get(j)));
            }
            
        }
        System.out.println("("+r+")"); 
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
        
    
        
        
        
    
    


