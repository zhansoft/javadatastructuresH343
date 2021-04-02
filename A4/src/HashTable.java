import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.BiFunction;

// -------------------------------------------------------

/**
 *
 * Before implementing the hash tables proper, we will
 * implement a small class hierarchy of hash tables entries. There are
 * three kinds of entries: an entry that contains an key-value pair, an
 * entry that is empty and hence available, and an entry that is
 * marked as deleted. A deleted entry is available for insertions but
 * cannot be marked as empty. See the book for details.
 *
 */
abstract class Entry<K,V> {
    abstract boolean available ();
}

class Empty<K,V> extends Entry<K,V> {
    boolean available () { return true; }
    public String toString () { return ""; }
}

class Deleted<K,V> extends Entry<K,V> {
    boolean available () { return true; }
    public String toString () { return "X"; }
}

class KeyValue<K,V> extends Entry<K,V> {
    private K key;
    private V value;
    KeyValue (K key, V value) {
        this.key = key;
        this.value = value;
    }
    K getKey () { return this.key; }
    V getValue () { return this.value; }
    boolean available () { return false; }
    public String toString () {
        return String.valueOf(this.key) + "," + String.valueOf(this.value);
    }
}

// -------------------------------------------------------

/**
 *
 * This class, although abstract, will have a constructor and an
 * implementation of each of the three methods: insert, delete, and
 * search. It will also have an implementation of rehash().
 *
 * The constructor should take two arguments: an
 * argument 'basicF' of type HashFunction, and an argument 'adjustF'
 * of type BiFunction<K,Integer,Integer>.  The subclasses for
 * linear probing, quadratic probing, and double hashing, will each
 * pass an appropriate function 'adjustF' to control the
 * auxiliary function.
 *
 * The constructor should make an indexed hash functions from these
 * two arguments and store it as an instance variable 'f'. The constructor
 * should also create an ArrayList of the same size as the bound
 * for 'basicF' and initialize each slot in that array with an Empty
 * entry.
 *
 * Each of the methods insert, delete, and search takes a 'key'
 * to process. Each of the methods will involve a loop that iterates
 * over all the positions in the ArrayList. At iteration 'i', an
 * integer position is calculated using:
 *
 *   int h = f.apply(key,i)
 *
 * For insert: if the position 'h' is available then the key-value pair
 * is stored at that position. It is possible for the array to be full
 * and in that case, we rehash and try to insert again.
 *
 * For delete: if position 'h' has an entry of kind 'KeyValue' and if the
 * stored key is identical to the parameter 'key' then the entry is marked
 * as deleted.
 *
 * For search: if position 'h' is Empty then the item is not found. If
 * position 'h' has an entry of kind 'KeyValue' and if the stored key
 * is identical to the parameter 'key' then the item is found.
 *
 */
abstract class HashTable<K,V> {
    private int capacity;
    private HashFunctionIndexed<K> f;
    private ArrayList<Entry<K,V>> slots;

    HashTable (HashFunction<K> basicF, BiFunction<K,Integer,Integer> adjustF) {
        // TODO
        this.capacity = basicF.getBound();
        this.f = new HashFunctionIndexed<K>(basicF, adjustF);
        this.slots = new ArrayList<>(this.f.getBound());
        for(int i = 0; i < this.f.getBound(); i++){
            this.slots.add(i, new Empty());
        }
    }

    int getCapacity () { return capacity; }

    void insert (K key, V value) {
        // TODO
        int h;
        for(int i = 0; i < this.getCapacity(); i++){
            h = f.apply(key, i);
            if(this.slots.get(h).available()){
                this.slots.set(h, new KeyValue<>(key, value));
                return;
            }
        }
        this.rehash();
        this.insert(key, value);
    }

    void delete (K key) {
        // TODO
        int h;
        for(int i = 0; i < this.getCapacity(); i++){
            h = f.apply(key, i);
            if(this.slots.get(h) instanceof KeyValue && ((KeyValue<K, V>) this.slots.get(h)).getKey().equals(key)){
                this.slots.set(h, new Deleted<>());
                return;
            }
        }
    }

    Optional<V> search (K key) {
        // TODO
        int h;
        for(int i = 0; i < this.getCapacity(); i++){
            h = f.apply(key, i);
            if(this.slots.get(h) instanceof Empty){
                return Optional.empty();
            }
            else if(this.slots.get(h) instanceof KeyValue && ((KeyValue<K, V>) this.slots.get(h)).getKey().equals(key)){
                return Optional.of(((KeyValue<K, V>) this.slots.get(h)).getValue());
            }
        }
        return Optional.empty();
    }

    /**
     * To rehash, we double the capacity of the array, adjust the hash
     * function with the new bound, and rehash all the values in the old
     * array
     */
    void rehash () {
        // TODO
        int p = BigInteger.valueOf(this.getCapacity()*2).nextProbablePrime().intValue();
        f.setBound(p);
        ArrayList<Entry<K,V>> temp = new ArrayList<>(p);
        for(int i = 0; i < p; i++){
            temp.add(i, new Empty());
        }

        // hash
        int h;
        for(int i = 0; i < this.getCapacity(); i++){
            if(this.slots.get(i) instanceof KeyValue){
                for(int j = 0; j < p; j++){
                    h = f.apply(((KeyValue<K,V>) this.slots.get(i)).getKey(), j);
                    if(temp.get(h).available()){
                        temp.set(h, new KeyValue<>(((KeyValue<K,V>) this.slots.get(i)).getKey(), ((KeyValue<K,V>) this.slots.get(i)).getValue()));
                        break;
                    }
                }
            }
        }

        this.capacity = p;
        this.slots = temp;
    }


    public String toString () {
        String result = "";
        for (int i = 0; i< capacity; i++) {
            result += "entry[" + i + "] = ";
            result += slots.get(i).toString();
            result += "\n";
        }
        return result;
    }
}

// -------------------------------------------------------

// How to handle collisions
// one by one
class HashLinearProbing<K,V> extends HashTable<K,V> {
    HashLinearProbing(HashFunction<K> hf) {
        super(hf, (key,index) -> index);
    }
}

// 2^i or i^2
class HashQuadProbing<K,V> extends HashTable<K,V> {
    HashQuadProbing (HashFunction<K> hf) {
        super(hf, (key,index) -> index*index);
    }
}

// hash the key twice
class HashDouble<K,V> extends HashTable<K,V> {
    HashDouble (HashFunction<K> hf, HashFunction<K> hf2) {
        super(hf, (key,index) -> index * hf2.apply(key));
    }
}

// -------------------------------------------------------
