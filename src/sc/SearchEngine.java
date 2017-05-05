package sc;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;



public class SearchEngine {
    private TrieNode root;
    SearchEngine() {
        root = new TrieNode();
    }


    public  void initialize(HashMap<String,String> map) {

    	
        System.out.println("-------------Here are all the source link in the directory---------");
        for (String key : map.keySet()) {
            System.out.println(key);
            // split the content by space, \n or others
            StringTokenizer st = new StringTokenizer(map.get(key), "[\n\\.,=<> ]");
            while (st.hasMoreTokens()) {
                String term = st.nextToken();
//                System.out.println(term);
                TrieNode pointer = this.root;
                //construct Trie Tree
                for (int i = 0; i < term.length(); i++) {
                    char c = term.charAt(i);
                    if (!pointer.nexts.containsKey(c))
                        pointer.nexts.put(c, new TrieNode());
                    pointer = pointer.nexts.get(c);
                }
                pointer.isLeaf = true;
                
                //construct occurrenceList
                if (pointer.occurrenceList == null)
                    pointer.occurrenceList = new HashMap<>();
                
                if (!pointer.occurrenceList.containsKey(key))
                    pointer.occurrenceList.put(key,1);
                else
                	pointer.occurrenceList.put(key, pointer.occurrenceList.get(key)+ 1);
            }
        }
    }


    public HashMap<String,Integer> search(String[] keywords) {
        HashMap<String,Integer> res = new HashMap<>();
        if (keywords == null || keywords.length == 0 )
            return res;
        for (int i = 0; i < keywords.length; i++) {
            HashMap<String,Integer> list = searchWord(keywords[i]);
            // Merge the occurrence lists into one list
            res = Utils.merge(res, list);
        }
        return res;
    }

    private  HashMap<String,Integer> searchWord(String word) {
        TrieNode pointer = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (pointer.nexts.containsKey(c)) {
                pointer = pointer.nexts.get(c);
            } else {
                System.out.println("Cannot find the keyword: " + word);
                return new HashMap<>();
            }
        }
        if (pointer.isLeaf == false) {
            System.out.println("Cannot find the keyword: " + word);
            return new HashMap<>();
        } else {
            return pointer.occurrenceList;
        }
    }

}
