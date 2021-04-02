import java.util.Hashtable;

// more efficient way to store/search for things
// huffman coding by frequency

public class Trie implements Words {
    private boolean endsHere;
    private final Hashtable<Character,Trie> children;

    Trie () {
        // affected by words inserted by REAL dictionary the ends here
        this.endsHere = false;
        this.children = new Hashtable<>();
    }

    /**
     * Inserts the string s in the current trie.
     * No duplicate keys per row
     *
     */
    void insert(String s) {
        // TODO
        // if ""
        /*
        "cat"
        1: <c, new Trie()>
        2: <c, <a, new Trie()>>
        3: <c, <a, <t, new Trie()>>>
        4: <c, <a, <t, new Trie().endshere = true :D>>>
         */
        if(s.isEmpty()){
            this.endsHere = true;
        }else{
            if(!children.containsKey(s.charAt(0))){
                this.children.put(s.charAt(0), new Trie());
            }
            this.children.get(s.charAt(0)).insert(s.substring(1));
        }
    }

    /**
     * Checks whether the string s is a full word
     * stored in the current trie.
     */
    public boolean contains(String s) {
        // TODO
        if(s.isEmpty()){
            return this.endsHere;
        }else{
            if(this.children.containsKey(s.charAt(0))){
                return this.children.get(s.charAt(0)).contains(s.substring(1));
            }
            else{
                return false;
            }
        }
    }

    /**
     * Checks whether the string s is a prefix
     * of at least one word in the current trie.
     * example: "fix" for prefix = false; "pre" for prefix true
     */
    public boolean possiblePrefix (String s) {
        // TODO
        if(s.isEmpty()){
            return true;
        }else{
            if(this.children.containsKey(s.charAt(0))){
                return this.children.get(s.charAt(0)).possiblePrefix(s.substring(1));
            }
            else{
                return false;
            }
        }
    }

    public String toString () {
        return children.toString();
    }
}
