package sc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.*;
import org.jsoup.nodes.*;

public class Main {

    public static void main(String[] args) throws IOException {
	    
    	HashMap<String,String> map = new HashMap<>();
    	
    	
    	// Web Crawler, fetch all links from given url
    	
    	FileInputStream fis = new FileInputStream("source.txt");
    	//Construct BufferedReader from InputStreamReader
    	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
     
    	String line = null;	// mother URL
    	while ((line = br.readLine()) != null) {
    		
    		// URL.txt will record all urls grabbed from source.txt
    		HashSet<String> A = new WebCrawler("URL.txt",line).links;
    		
    		String data = "";
    		
    		for(String c : A){	// for each children link of one mother link -- line here
    			Document doc = Jsoup.connect(c).get();
            	Element body = doc.body();
    			data += " " + body.html();
    		}
    		
    		map.put(line, data);
    		
    	}
    	br.close();
    	
    	
    	
    	//Search Engine
    	
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.initialize(map);
        System.out.println("Search Engine has initialized successfully!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPlease input your keywords(separated by space): ");
            String input = scanner.nextLine();
            HashMap<String,Integer> res = searchEngine.search(input.split(" "));
            System.out.println("---------------------Here is search result-------------------");
            System.out.println("Find " + res.size() + " URLs which contains your keyword(s)!\n");
            // ranking
            PriorityQueue<String> q = new PriorityQueue<>(100,(a,b)->res.get(b)-res.get(a));
            for(String c:res.keySet())
            	q.add(c);
            for (String c : q) {
                System.out.println( "URL: " + c +  "\t\t\tOccurance-frequency:   " + res.get(c));
            }
        }
    }
}
