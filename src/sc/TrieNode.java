package sc;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class TrieNode {
    Map<Character, TrieNode> nexts;
    boolean isLeaf;
    HashMap<String,Integer> occurrenceList;
    TrieNode() {
        nexts = new HashMap<Character, TrieNode>();
        isLeaf = false;
        occurrenceList = null;
    }
}
