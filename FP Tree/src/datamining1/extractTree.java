package datamining1;

import java.util.ArrayList;
import java.util.List;
//import datamining1.fpTree.*;
import datamining1.DataMining1.*;
public class extractTree {
    Node root;int prefix;
    List<Node> stk = new ArrayList<Node>();
    List<Node> sp_stk = new ArrayList<Node>();
    List<Node> create_stk= new ArrayList<Node>();
    List<Node> tmp_stk= new ArrayList<Node>();
    List<Integer> fin_stk= new ArrayList<Integer>();
    List<List<Integer> > freq_items  =new ArrayList<List<Integer>>();
    Node updtetree_root;
    Node ref;
    Node global[];Node head[];
    extractTree(Node g,int h){
        root = g;
        prefix = h;
        updtetree_root = new  Node(-2,root.length);
        ref = updtetree_root; 
        //global = new Node[fpTree.uniqueSize];
        //head = new Node[fpTree.uniqueSize];
        cutTree(prefix);
       // printArraylist();
    }
    public void cutTree(int prfix){         
        Node strt = root;
        stk.add(root); 
            for(int i=0;i<root.length;i++){
                global[i]=null;
                head[i]= null;
       } 
            dfs(root,prfix);
            create_prfxtree(root,prfix,ref);
            updtesupport(updtetree_root,prfix);
           // dfs(updtetree_root,4);
          //  Node uptr3 = new Node (-2,root.length);
           // Node ref2 = uptr3;           
          //  create_prfxtree(updtetree_root,4,ref2);
            test(updtetree_root);
          //  
          //  connect_links(updtetree_root);     
            //prnt_prfxtree(updtetree_root);
           // fin(updtetree_root,prfix);
         
    }
    public void test(Node a){ 

        System.out.println(a.child[2].child[4].support);
        
    }
    public void dfs(Node vert,int prefix1){
        for(int i =0;i<vert.length;i++){
            if(vert.child[i]!=null){
                stk.add(vert.child[i]); 
                if(vert.child[i].data == prefix1){
                    vert.child[i].prfxflg = 1;
                    for(int e = 0;e<stk.size();e++){
                        stk.get(e).prfxflg = 1;
                    }
                    stk.remove(stk.size()-1);                 

                }
                else{
                    dfs(stk.get(stk.size()-1),prefix1);
                }
            }
        }
        if(stk.size()!=0)
        stk.remove((stk.size()-1));
    }
    public void prnt_prfxtree(Node tmp){
       /* for(int i =0;i<tmp.length;i++){
            if(tmp.child[i] != null && tmp.child[i].prfxflg == 1){
                System.out.println(tmp.child[i].data+"  "+tmp.child[i].support);
                prnt_prfxtree(tmp.child[i]);
            }
        }*/
       //System.out.println(head[3].next+" "+global[4]+" "+global[2]);
   
    }
    public void create_prfxtree(Node tmp,int pf,Node r){           
            if(tmp_stk.size()!=0){            
                tmp_stk.remove(0);
                create_stk.remove(0);
            }            
            for(int i =0;i<tmp.length;i++){             
            if(tmp.child[i] != null && tmp.child[i].prfxflg == 1 && tmp.child[i].data!=pf){              
             Node f = new Node(0,root.length);            
             f.prfxflg = 0;
             f.data = tmp.child[i].data;
             f.flag = tmp.child[i].flag; 
             r.child[i] = f;     
             create_stk.add(f);
             tmp_stk.add(tmp.child[i]);
            }
        } 
            if(!tmp_stk.isEmpty()){        
                r = create_stk.get(0);
                create_prfxtree(tmp_stk.get(0),pf,r);
            }
    }
    public void connect_links(Node tmp){
        for(int i = 0;i<tmp.length;i++){
            if(tmp.child[i]!=null){
                //System.out.println(tmp.child[i]+" "+tmp.child[i].data);
                if(global[i] == null){
                            head[i] = tmp.child[i];
                            global[i] = tmp.child[i];
                            global[i].next=null;
                        }
                        else{
                            global[i].next = tmp.child[i];
                            global[i] =  tmp.child[i];
                            global[i].next =null;
                        }
               Node t = tmp.child[i];
               connect_links(t);
            }
        }
    }
    public void updtesupport(Node tmp,int prefix1){
            int cnt =0;
            if(sp_stk.size()!=0) sp_stk.remove(0);   
            if(tmp.data == prefix1 && tmp.prfxflg == 1) {                
                cnt = cnt+1;                
            }
            else{ 
                for(int i =0;i<tmp.length;i++){
            if(tmp.child[i] != null &&tmp.child[i].prfxflg == 1){              
                cnt=cnt+1;             
                sp_stk.add(tmp.child[i]);
            }
        }       
        }
            tmp.support = cnt;
            if(!sp_stk.isEmpty()){                
                updtesupport(sp_stk.get(0),prefix1);
            }          
    }
    
    public void fin(Node vert,int prfx){
        for(int i=0;i<vert.length;i++){
            int flag = 0;
            
           // System.out.println("printing stack with i as"+i);
            for(int g = 0;g < fin_stk.size();g++){
                
             //  System.out.print(fin_stk.get(g)+" ");
                if(fin_stk.get(g) == i) flag = 1;
            }
         //   System.out.println();
        //    System.out.println("printing stack finished with i as"+i);
            
                if(i+1==prfx && find_sup(i,vert)>=datamining1.readFile.min_support && flag == 0){
                fin_stk.add(i);
                List <Integer> l = new ArrayList<Integer>();
                l.add(fin_stk.get(0));
                freq_items.add(l);
            }   

            if(flag == 0 && (i+1)!=prfx){
                
        int t=find_sup(i,vert);
     // System.out.println("support="+t+" i="+i+"prfx="+prfx);
        if(t>=datamining1.readFile.min_support){
            fin_stk.add(i);
            List <Integer> l = new ArrayList<Integer>();
            
            for(int y=0;y<fin_stk.size();y++){
            l.add(fin_stk.get(y));
            }           
            freq_items.add(l);
            if(l.size()>=vert.length){
            break;
            }
          //  System.out.println("In tree with prfx"+prfx);
            int newprfx=i+1;
           //  System.out.println("new tree just to be created with prfx"+ newprfx);   
            cutTree(newprfx);            
        }
        }
        }
        if(!fin_stk.isEmpty())     fin_stk.remove(fin_stk.size()-1);
              //  System.out.print("finstksize");
          //  for(int m =0;m<fin_stk.size();m++)
          //  System.out.print(fin_stk.get(m)+" zz ");
      //  System.out.println("forloopended");
     }        
    
    public int find_sup(int p,Node vert){
        Node e = head[p];
        int summ=0;
        while(e!=null){
        summ = summ + e.support;
        if(p==2){
       // System.out.print("\n"+e+" "+e.data+" "+e.support+" "+e.next);            
//System.out.print(summ+" ");
        }
        e = e.next;
        }
        System.out.println();
        return summ;
    }
    /*public void printArraylist(){
        for(int i=0;i<freq_items.size();i++){
            for(int j=0;j<freq_items.get(i).size();j++){
                System.out.print(freq_items.get(i).get(j));
            }
            System.out.println();
        }
    }*/
}
