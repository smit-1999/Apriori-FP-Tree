/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining1;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; 
import java.util.ArrayList; import java.util.List;
public class NodeHash{
    NodeHash child[];
    List<Integer[]> ls ;   
    int support=0;
    int depth;
    int length;
    NodeHash(List<Integer[]> ls,int support,int length){
    this.ls = ls;    
    this.support = support;
    this.length = length-2;    
    this.depth=0;    
    }   
    public void addNode(NodeHash parent){
        for(int i=0;i<length;i++){
        child[i]=null;
        }
        for(int y=0;y<parent.ls.size();y++){
                int tmp = (parent.ls.get(y)[depth]%length);                      
                
                 System.out.println("bi");
                if(child[tmp].ls.size()<length){
                    child[tmp].ls.add(parent.ls.get(y));
                }
                else if(child[tmp].ls.size()==length&&child[tmp].depth<length){
                    child[tmp].ls.add(parent.ls.get(y));                    
                    child[tmp].depth=parent.depth+1;
                    addNode(child[tmp]);
                }
                else if(child[tmp].depth>=length){
                    child[tmp].ls.add(parent.ls.get(y));
                }
            }
        }
    public void printNode(Node parent){
        for(int i=0;i<length;i++){
              for(int j=0;j<child[i].ls.size();j++){
                  for(int k=0;k<child[i].ls.get(0).length;k++){
                      System.out.println(child[i].ls.get(i)[j]);
                  }
              }  
        }   
    }
        
    
    }
