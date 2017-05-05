package sc;
import java.io.*;
import java.util.HashMap;


public class Utils {
    
    public static HashMap<String,Integer> merge(HashMap<String,Integer> a,HashMap<String,Integer> b){
    	for(String c : b.keySet()){
    		if(!a.containsKey(c)){
    			a.put(c,b.get(c));
    		}else{
    			a.put(c, a.get(c) + b.get(c));
    		}
    	}
    	
    	return a;
    }

}
