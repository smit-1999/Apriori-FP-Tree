
package datamining1;

import java.util.ArrayList;
import java.util.List;
//import datamining1.fpTree.*;
import datamining1.DataMining1.*;
public class prefixTree {
    Node head;
    int prefix;
    List<Node> stk = new ArrayList<Node>();
    List<Node> updt_Stk = new ArrayList<Node>();
    List<List<Integer>> freq_items ;
    List<Integer> tmpStk;
    Node globalPtr[];
    Node headPtr[];
 
    prefixTree(Node root,int pr,Node [] a,Node [] b){
       freq_items  =new ArrayList<List<Integer>>();
        tmpStk = new ArrayList<Integer>();
        head = root;
        prefix = pr;
        globalPtr = a;
        headPtr = b;
        addTotmpStk(pr);
        freq_items.add(tmpStk);
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
                        vert.child[i].chk += 1;
                        for(int e = 0;e<stk.size();e++){
                            stk.get(e).chk = vert.child[i].chk;
                        }
                        stk.remove(stk.size()-1);                 

                    }
                    else{
                        //System.out.println((char)(stk.get(stk.size()-1).data + 96));
                        dfs(stk.get(stk.size()-1),pr1);
                    }
                }                
                else stk.remove(stk.size()-1);
            }
        }
        if(stk.size()!=0) {
           // System.out.println("forloop ended and now removing "+stk.get((stk.size()-1)).data);
            stk.remove((stk.size()-1));
        }        
    }
    public void test(){    
     // System.out.println(head.child[102].chk);
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
                if(grtr>=datamining1.readFile.min_support){
                    
                    tmpStk.add(i); 
                    List <Integer> l = new ArrayList<Integer>();
            
                    for(int y=0;y<tmpStk.size();y++){
                        l.add(tmpStk.get(y));
                    }           
                    freq_items.add(l);
                    
                    dfs(head,i);
                    updateSupport(head);
                    recur(vert,i);
            }
            }
        }
        if(!tmpStk.isEmpty()) {
            tmpStk.remove(tmpStk.size()-1);
            decrementChk(head,tmpStk.size()+1);
            updateSupport(head);
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
        if(!updt_Stk.isEmpty()){
            updt_Stk.remove(0);
        }
      for(int i = 0;i<vert.length;i++){
          if(vert.child[i]!=null){
              updt_Stk.add(vert.child[i]);
          int cnt = 0;
              for(int j = 0;j<vert.length;j++){
                  if( vert.child[j]!= null){
                      if(vert.child[j].chk == tmpStk.size()){
                      cnt++;
                  }
              }
              }
              vert.support = cnt;
      }
      } 
      if(!updt_Stk.isEmpty()) updateSupport(updt_Stk.get(0));
      
    }
            
    
    
    
   public void printFreq(){
        //System.out.println("why/?");
       for(int y =0;y<freq_items.size();y++){
        System.out.print(freq_items.get(y));            
           System.out.print("\n");
        }
    }
}
