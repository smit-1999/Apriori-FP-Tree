package datamining;

import java.util.ArrayList;
import java.util.List;
import datamining.readFile.*;

public class fpTree {
    
    public static int uniqueSize;
    Node root;
    Node global[];
    Node head[];
    Node tmp1,tmp;
    List<int[]> sortD = new ArrayList<>();
    
    fpTree(List<int[]> t,int uniqueSize){
            sortD           = t;
            this.uniqueSize = uniqueSize;
            root            = new Node(-1,uniqueSize); 
            global          = new Node[uniqueSize];
            head            = new Node[uniqueSize];
            
            tmp1            = root;  
            tmp             = root;

    }
    public void createTree(){
        for(int i=0;i<uniqueSize;i++){
            global[i]   = null;
            head[i]     = null;
        }          

        for(int i =0;i<sortD.size();i++){
            for(int j=0;j<sortD.get(i).length;j++){
                int g = sortD.get(i)[j];
                 if(g!=0){                     
                    if(tmp.child[g-1] == null){
                        Node s =new Node(0,uniqueSize);
                        s.data          = g;
                        s.parent        = tmp;
                        tmp.child[g-1]  = s;
                        tmp             = s;
                    }
                    else{
                        tmp = tmp.child[g-1];                        
                        tmp.support +=1;
                    }
                }
            }
            
            tmp = root;
        }
        connectLinks(root);
        traverseTreethroughLinks();
       
    }
    public void connectLinks(Node vert){
        for(int i =0;i<vert.length;i++){
            if(vert.child[i] != null){
                if(global[i] == null){
                            head[i]        =    vert.child[i];
                            global[i]      =    vert.child[i];
                            global[i].next =    null;
                        }
                else{
                            global[i].next =    vert.child[i];
                            global[i]      =    vert.child[i];
                            global[i].next =    null;
                }
                
                Node t1 = vert.child[i];
                connectLinks(t1);
            }
        }    
    }
    
    
    public void callPrint(){
       print(root);
    }
    
    
    public void print(Node tmp){
        //for testing purpose
    }
    
    
    public void traverseTreethroughLinks(){
        for(int i = 0;i<head.length;i++){
            int supp_i=0;
            if(head[i]!=null){                
                Node p = head[i];
                supp_i = p.support;
                while(p.next!=null){
                    p       = p.next;
                    supp_i += p.support;
                }  
            }   
          }
    }
    
    public void callprefixTree(Node e,int pr){
        prefixTree p = new prefixTree(e,pr,global,head);

        if( p.finSupport(pr,0) >= readFile.min_support){
            p.dfs(e,pr);
            p.init_supp(e, pr);
            p.uds_parent(e,pr);
            p.recur(e,pr);
            p.printFreq();
        }
    }
    
   public void prnt_1(){
        for(int x =0;x<MergeCandidates.uniqueofCk.size();x++){        
        System.out.println(readFile.hash_map2.get(MergeCandidates.uniqueofCk.get(x)));
       }
   }
    
}
    
