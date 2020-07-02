
package datamining1;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; 
import java.util.ArrayList; import java.util.List;
public class prefixSubtree {
    Node root,curr;
    Node s = null;
    int prefix,h;
    Node temp;
    Node temp2;    
    prefixSubtree(Node root,int prefix){
    this.root=root;
    this.prefix=prefix;
    temp=root;
    //temp2=root;
    }    
    /*public void createSubtree(Node root,int prefix,Node prev){
    curr=root;
    s=prev;    
    int y =0;
    for(int i=0;i<root.length;i++){        
        y=i;        
        if(temp.child[i]!=null){           
           if(temp.child[i].data==prefix){
                temp.child[i].prefixend=1;
                curr.prefixend=1;
                return;
           }           
           temp=temp.child[i];
           createSubtree(temp,prefix,s);
           if(s.child[i].prefixend==1){
               curr.prefixend=1;
           }           
        }            
    }
    if(curr.prefixend==0){       
        for(int u=0;u<root.length;u++){
        if(s.data==curr.data){
            h=u;
            break;
        }
        }
        s.child[h]=null;
    }    
}
public void findPath(Node root){
    temp2=root;
    for(int i=0;i<root.length;i++){       
        if(temp2.child[i]!=null){        
          System.out.println(temp2.child[i].data+"hi");     
        }       
    }    
} */   
}
