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
import java.util.List;
import java.nio.file.Paths; 
import java.util.ArrayList; 
public class Node {
    int data ;   
    Node child[];
    Node next;
    int support;
    int depth;
    int length;
    int flag;
    int prfxflg;
    Node parent;
    int chk;
    
    Node(int support1,int length1){         
    this.support = support1;
    this.length = length1;    
    this.depth=0;    
    this.flag = 0;
    this.prfxflg = 0;
    this.chk = 0;
    this.next =  null;
    this.parent = null;
    initNode();
    }   

    
    public void initNode(){
       child  = new Node[length];      
       for(int i=0;i<length;i++){
            child[i]=null;
       }          
    }       
    
}

