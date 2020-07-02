
package datamining1;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; 
import java.util.ArrayList; import java.util.List;
import datamining1.readFile.*;

public class redundantRules {
    List <Integer[]> ovrall = new ArrayList<Integer[]>();
    List <Integer[]> closed = new ArrayList<Integer[]>();
    List <Integer[]> redn = new ArrayList<Integer[]>();
    redundantRules(List<Integer[]> ovrall,List<Integer[]> closed){
        this.ovrall = ovrall;
        this.closed = closed;
    }
    public void findRedundant(){
        for(int i=0;i<closed.size();i++){
            int count = 0;
            for(int j=0;j<ovrall.size();j++){
                if((closed.get(i).length==ovrall.get(j).length)&&(ovrall.get(j)[ovrall.get(j).length-1]==1)){
                    for(int k=0;k<closed.get(i).length-2;k++){
                        for(int l=0;l<ovrall.get(j).length-2;l++){
                            if(closed.get(i)[k]==ovrall.get(j)[l]){
                                count++;
                            }                                
                        }
                    }
                    if(count == ovrall.get(j).length-2){
                        ovrall.get(j)[ovrall.get(j).length-1]=2;
                        count=0;
                        break;
                    }                    
                }
                
            }
        }
        System.out.println("The redundant Subsets are:");
        for(int i=0;i<ovrall.size();i++){            
            if(ovrall.get(i)[ovrall.get(i).length-1]==1){
                redn.add(ovrall.get(i));
            }            
        }
        for(int i=0;i<redn.size();i++){                            
            for(int j=0;j<redn.get(i).length-2;j++){
                System.out.println(readFile.hash_map2.get(redn.get(i)[j])+" ");
            }                
        }
    }
    
}
