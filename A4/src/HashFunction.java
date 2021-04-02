import java.math.BigInteger;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

// -------------------------------------------------------

/**
 * A hash function implements the Function interface, i.e.,
 * it has a method "apply" that takes a key of type K and
 * returns an integer.
 *
 * The result must be in the range 0..bound-1
 *
 * The bound changes when we extend the hash table to a
 * bigger size.
 */
abstract class HashFunction<K> implements Function<K,Integer> {
    abstract int getBound ();
    abstract void setBound (int bound);
}

// -------------------------------------------------------

/**
 * This is probably the simplest possible hash function.
 *
 * When applied to a key K, it uses the built-in method
 * .hashCode() to get an integer and then use .floorMod
 * to get a result between 0..bound-1
 */
class HashMod<K> extends HashFunction<K> {
    private int bound;

    HashMod (int bound) { this.bound = bound; }

    int getBound () { return bound; }
    void setBound (int bound) { this.bound = bound; }

    public Integer apply (K key) {
        // TODO
        Integer result = Math.floorMod(key.hashCode(), this.bound);
        return result;
    }
}

// -------------------------------------------------------

/**
 * An instance of this class is created with two parameters: a
 * random number generator 'r' of type Random and a bound. An instance
 * of the class needs to maintain five parameters: the random number
 * generator r, and four integers: p, m, a, and b defined as follows:
 *   - the integer 'p' is a prime number greater than or equal to 'bound';
 *     pick the .nextProbablePrime() larger than bound
 *   - the integer 'm' is another name for 'bound'
 *   - the integer 'a' is a random number in the range 1..p-1
 *   - the integer 'b' is a random number in the range 0..p-1
 * The resulting hash function is universal hash function
 * as explained in the book.
 */
class HashUniversal<K> extends HashFunction<K> {
    private int p, m, a, b;
    private Random r;

    HashUniversal (Random r, int bound) {
        // TODO
        this.r = r;
        this.m = bound;
        this.p = BigInteger.valueOf(this.m).nextProbablePrime().intValue();
        this.a = r.nextInt(this.p-1) + 1;
        this.b = r.nextInt(this.p);
    }

    int getBound () { return m; }

    /**
     * When the bound changes, the four parameters 'p', 'm', 'a',
     * and 'b' need to be re-generated as explained in the
     * constructor.
     */
    void setBound (int bound) {
        // TODO
        this.m = bound;
        this.p = BigInteger.valueOf(this.m).nextProbablePrime().intValue();
        this.a = r.nextInt(this.p-1) + 1;
        this.b = r.nextInt(this.p);
    }

    /**
     * When a universal hash function is apply to a key of type K,
     * we first compute an integer using .hashCode and then process
     * this integer as explained in the book.
     */
    public Integer apply (K key) {
        // TODO see the picture in general that kevin sent
        Integer result = Math.floorMod(Math.floorMod(a*key.hashCode() + this.b, this.p), this.m);
        return result;
    }
}

// -------------------------------------------------------

/**
 * This is a variation of hash functions that will be used
 * to handle collisions. The constructor takes a basic hash
 * function 'basicF' and a second function 'adjustF' used as
 * follows.
 *
 * To apply the hash function, we pass two arguments: a key
 * of type K and an index of type Integer. We then perform
 * the following calculation:
 *   - the basic hash function is applied to the key to produce
 *     an integer 'i'
 *   - the function 'adjustF' is applied to the key and
 *     the previous result 'i' [index] to produce an integer 'j'
 *   - the final result is the sum of 'i' and 'j' (adjusted to
 *     be in the range 0..bound-1
 */
class HashFunctionIndexed<K> implements BiFunction<K,Integer,Integer> {
    private int bound;
    private HashFunction<K> basicF;
    private BiFunction<K,Integer,Integer> adjustF;

    HashFunctionIndexed (HashFunction<K> basicF, BiFunction<K,Integer,Integer> adjustF) {
        this.bound = basicF.getBound();
        this.basicF = basicF;
        this.adjustF = adjustF;
    }

    int getBound () { return bound; }

    void setBound (int bound) {
        this.bound = bound;
        basicF.setBound(bound);
    }

    public Integer apply (K key, Integer index) {
        // TODO
        Integer result = 0;
        int i = this.basicF.apply(key);
        int j = adjustF.apply(key, index);
        result = Math.floorMod(i+j, this.bound);
        return result;
    }

}

// -------------------------------------------------------
