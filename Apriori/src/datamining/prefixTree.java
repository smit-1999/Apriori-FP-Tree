package datamining;

import java.util.ArrayList;
import java.util.List;
import datamining.fpTree.*;
import datamining.DataMining.*;
import datamining.readFile.*;
import datamining.MergeCandidates.*;


public class prefixTree {
    
    int prefix;
    Node head;
    Node globalPtr[];
    Node headPtr[];
    List<Node> stk       = new ArrayList<Node>();
    List<Node> updt_Stk  = new ArrayList<Node>();
    List<Node> init_stk  = new ArrayList<Node>();
    
    List<Integer> tmpStk,firstElement;
    List<List<Integer>> freq_items ;
    

 
    prefixTree(Node root,int pr,Node [] a,Node [] b){
        
        freq_items      = new ArrayList<List<Integer>>();
        tmpStk          = new ArrayList<Integer>();
        firstElement    = new ArrayList<Integer>();
        head            = root;
        prefix          = pr;
        globalPtr       = a;
        headPtr         = b;
        
        tmpStk.add(pr);
        firstElement.add(pr);
        freq_items.add(firstElement);
        stk.add(head);    
        updt_Stk.add(head);
        
    }
    
    public void addTotmpStk(int p){
        tmpStk.add(p);
    }
    
    public void dfs(Node vert,int pr1){
        for(int i =0;i<vert.length;i++){
            if(vert.child[i]!=null){
                stk.add(vert.child[i]);  
                if(vert.child[i].chk == tmpStk.size()-1){                    
                    if(vert.child[i].data == pr1+1){
                        vert.child[i].support += 1;
                        vert.child[i].chk += 1;
                        for(int e = 0;e<stk.size();e++){
                            stk.get(e).chk = vert.child[i].chk;
                        }
                        stk.remove(stk.size()-1);                 

                    }
                    else{
                        dfs(stk.get(stk.size()-1),pr1);
                    }
                }                
                else stk.remove(stk.size()-1);
            }
        }
        if(stk.size()!=0)   stk.remove((stk.size()-1));
    }
    public void test(){    
        //for testing purposes
    }
    
    public int  finSupport(int pr,int c){
        Node e = headPtr[pr];
        int summ=0;     
        while(e!=null ){
            if(e.chk == c) summ = summ + e.support;
            
            e = e.next;
        } 
        
        return summ;
    }
    
    public int finInitSupp(int pr,int c){
        
        Node e = headPtr[pr];
        int summ=0;     
        while(e!=null ){
            if(e.chk == c) summ = summ + 1;
            
            e = e.next;
        }
        
        return summ;
    }
    
    
    public void recur(Node vert,int pr){
        
        for(int i = 0;i<vert.length;i++){           
            int flg = 0;
            for(int g =0;g<tmpStk.size();g++){
                if(i == tmpStk.get(g)){
                    flg = 1;
                    break;
                }               
            }
            
            if(flg == 0){            
                int grtr = finSupport(i,tmpStk.size());
              
                if(grtr >= datamining.readFile.min_support){
                   
                    tmpStk.add(i); 
                    List <Integer> l = new ArrayList<Integer>();
            
                    for(int y=0;y<tmpStk.size();y++) l.add(tmpStk.get(y));                    
                    freq_items.add(l);
                                      
                    dfs(head,i);
                    init_supp(head,tmpStk.get(tmpStk.size()-1));
                    uds_parent(head,tmpStk.get(tmpStk.size()-1));                    
                    recur(vert,i);
                    
                }
            }
        }
   
        if(!tmpStk.isEmpty()) {
            
            tmpStk.remove(tmpStk.size()-1);
            decrementChk(head,tmpStk.size()+1);
            
            if(!tmpStk.isEmpty()){
                init_supp(head,tmpStk.get(tmpStk.size()-1));
                uds_parent(head,tmpStk.get(tmpStk.size()-1));
            }
            
        }
    }
    
    
    public void decrementChk(Node vert,int n){
        for(int i =0;i<vert.length;i++){
            if(vert.child[i] != null){
                if(vert.child[i].chk == n){
                    vert.child[i].chk -= 1;
                }
                decrementChk(vert.child[i],n);
            }
        }
    }
    
    
    public void updateSupport(Node vert){
        if(!updt_Stk.isEmpty()) updt_Stk.remove(0);
        
        for(int i = 0;i<vert.length;i++){
            if(vert.child[i]!=null){              
            updt_Stk.add(vert.child[i]);
            int cnt = 0;
                for(int j = 0;j<vert.length;j++){
                    if( vert.child[j]!= null){
                        if(vert.child[j].chk == tmpStk.size()){
                            cnt = cnt + vert.child[j].support;
                        }
                        else{
                            if(! updt_Stk.isEmpty()) updt_Stk.remove(updt_Stk.get(updt_Stk.size()-1));                                    
                            }                      
                    }
                }
                
            vert.support = cnt;
            
            }
        }
        
        if(!updt_Stk.isEmpty()) updateSupport(updt_Stk.get(0));
        
    }
            
    public int uds(Node vert){
        for(int j =0;j<vert.length;j++){
            if(vert.child[j] != null){
                if((vert.child[j].chk) == tmpStk.size()){
                    Node re     = vert;
                    Node send   = vert.child[j];                    
                    re.support += uds(send);
                }
            }
        }
        
    return vert.support;      
    }
    
    
    public void init_supp(Node vert,int pr){
        for(int i =0;i<vert.length;i++){
            if(vert.child[i] != null){
            init_stk.add(vert.child[i]);
                if(vert.child[i].chk == tmpStk.size()){
                    if(vert.child[i].data == pr+1){                        
                        for(int j =0;j<init_stk.size()-1;j++) init_stk.get(j).support = 0;
                        
                        init_stk.remove(init_stk.get(init_stk.size() - 1));    
                    }
                    else{
                       init_supp(vert.child[i],pr); 
                    }
                    
                }
                else init_stk.remove(init_stk.get(init_stk.size() - 1));
            }
        }
        if(! init_stk.isEmpty())  init_stk.remove(init_stk.get(init_stk.size() - 1));
    }
    
    
    public int uds_parent(Node vert,int pr){
        for(int i =0 ;i<vert.length;i++){
            if(vert.child[i] != null){
                if(vert.child[i].chk == tmpStk.size()){                   
                    if(vert.child[i].data == pr+1){                          
                        vert.support      += vert.child[i].support;
                    }
                    else{
                        vert.support      += uds_parent(vert.child[i],pr);
                    }  
                }
            }
        }
        
    return vert.support;
    
    }
    
    
   public void printFreq(){
       
        for(int y =0;y<freq_items.size();y++){
            if(freq_items.get(y).size() != 1){
                System.out.print("{");                
                for(int z =0;z<freq_items.get(y).size();z++)
                System.out.print(readFile.hash_map2.get(freq_items.get(y).get(z) +1));
                
                
                System.out.print("}\n");
            
            }
        }
      //System.out.println(readFile.hash_map2.get(1));
       
    }
}