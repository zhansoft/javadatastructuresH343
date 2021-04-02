import java.util.function.Function;

// -------------------------------------------------------

abstract class HashFunction implements Function<String,Integer> {}

// -------------------------------------------------------

/* hash function 0
 *
 * Each java object has a built in hash function that returns a hash of an object.
 * The hash code returned will still need to be put in the correct bound.
 *
 * Ex:
 *     String x = "hello";
 *     int hash = x.hashCode() % bound;
 */

// hash function 1
class LenMod extends HashFunction {
    private int bound;
    LenMod (int bound) { this.bound = bound; }

    // Creates a hashcode by finding the module of the key length with the bound
    public Integer apply (String key) {
        Integer result = 0;
        result = Math.floorMod(key.length(), this.bound);
        return result;
    }
}

// hash function 2
class CharMod extends HashFunction {
    private int bound;
    CharMod (int bound) { this.bound = bound; }

    // Creates a hashcode by summing the floormod of
    // the code so far plus the character with the bound.
    // This can be done with either loops or streams!
    public Integer apply (String key) {
        Integer result = 0;
        String temp = key;
        int accumulator = 0;
        do{
            char tempchar = temp.charAt(0);
            temp = temp.substring(1);
            accumulator += Math.floorMod(accumulator + tempchar, this.bound);
        }while(temp.length() > 0);
        return accumulator;
    }
}

// hash function 3
class RollingMod extends HashFunction {
    private int bound;
    private int p = 31;

    RollingMod (int bound) { this.bound = bound; }

    // Creates a hashcode by looping through each char in key
    // then summing the floodmod of the result plus the char raised to a power
    // with the bound.
    // The power is then changed before the next characer is added.
    public Integer apply (String key) {
        Integer result = 0;
        Integer pow = 1;
        for (char c : key.toCharArray()) {
            result = Math.floorMod(result + (c - 'a' + 1) * pow,bound);
            pow = (pow * p) % bound;
        }
        return result;
    }
}

// -------------------------------------------------------
// -------------------------------------------------------
