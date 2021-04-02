import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.Optional;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashFunctionTest {

    void assertEqualsL (long i, long j) {
        assertEquals(i,j);
    }

    @Test
    public void hashModInt () {
        HashFunction<Integer> hf = new HashMod<>(13);
        assertEqualsL(5, hf.apply(5));
        assertEqualsL(5, hf.apply(18));
        assertEqualsL(5, hf.apply(5+13*9));
        assertEqualsL(0, hf.apply(13));
        assertEqualsL(1, hf.apply(27));
    }

    @Test
    public void hashModString () {
        HashFunction<String> hf = new HashMod<>(13);
        assertEqualsL(4, hf.apply("aaa"));
        assertEqualsL(9, hf.apply("bbb"));
        assertEqualsL(1, hf.apply("ccc"));
        assertEqualsL(6, hf.apply("ddd"));
        assertEqualsL(11, hf.apply("eee"));
    }

    @Test
    public void hashUniversalInt () {
        Random r = new Random(1);
        HashFunction<Integer> hf = new HashUniversal<>(r, 31);
        assertEqualsL(13, hf.apply(0));
        assertEqualsL(10, hf.apply(1));
        assertEqualsL(7, hf.apply(2));
        assertEqualsL(4, hf.apply(3));
        assertEqualsL(1, hf.apply(4));
        assertEqualsL(4, hf.apply(5));
        assertEqualsL(1, hf.apply(6));
        assertEqualsL(29, hf.apply(7));
        assertEqualsL(26, hf.apply(8));
        assertEqualsL(23, hf.apply(9));
        assertEqualsL(20, hf.apply(10));
        assertEqualsL(17, hf.apply(11));
        assertEqualsL(14, hf.apply(12));
        assertEqualsL(11, hf.apply(13));
        assertEqualsL(8, hf.apply(14));
        assertEqualsL(5, hf.apply(15));
    }

    @Test
    public void hashFunctionIndexed () {
        HashFunction<String> bf = new HashMod<>(10);
        BiFunction<String,Integer,Integer> af = (k,i) -> i;
        HashFunctionIndexed<String> h = new HashFunctionIndexed<>(bf, af);
        assertEqualsL(9, h.apply("cat", 7));
        assertEqualsL(1, h.apply("dog", 7));
    }

    /*
        Sophia's own tests below here:
            Edition: Correctness :D
     */

    // HashMod HashUniversal HashIndexed HashTable

    @Test
    // this is for HashMod strictly
    public void apply(){
        HashFunction<Integer> a = new HashMod<>(7);
        HashFunction<String> b = new HashMod<>(13);
        assertEqualsL(5, a.apply(5));
        assertEqualsL(0, a.apply(7));
        assertEqualsL(1, a.apply(8));
        assertEqualsL(4, a.apply(-3));
        assertEqualsL(3, a.apply(17));
        System.out.println("string apply('pp'): " + b.apply("pp"));
        System.out.println("string  apply('tiktok'): " + b.apply("tiktok"));
        System.out.println("string apply(''): " + b.apply(""));
    }

    @Test
    // tests the constructor, setBound and apply
    public void hashUniversal(){
        // constructors
        Random r = new Random(2);

        HashFunction<Integer> i = new HashUniversal<>(r, 17);
        HashFunction<String> s = new HashUniversal<>(r, 11);

        System.out.println("i bound: "+ i.getBound()); // to show it is instantiated
        System.out.println("s bound: "+ s.getBound()); // to show it is instantiated

        i.setBound(7);
        s.setBound(13);
        System.out.println("i bound: "+ i.getBound()); // to show setBound
        System.out.println("s bound: "+ s.getBound()); // to show setBound

        assertEqualsL(5, i.apply(7));
        assertEqualsL(4, i.apply(-3));
        assertEqualsL(1, i.apply(13 + 20));
        assertEqualsL(1, i.apply(0));
        assertEqualsL(5, i.apply(-20/5));
        assertEqualsL(0, s.apply("bbb"));
        assertEqualsL(6, s.apply(""));
        assertEqualsL(8, s.apply(" "));
        assertEqualsL(4, s.apply("tiktok"));
    }

    @Test
    // correctness for apply
    public void HashIndex(){
        HashFunction<String> sf = new HashMod<>(14);
        BiFunction<String,Integer,Integer> sif = (k,i) -> i;
        HashFunctionIndexed<String> s = new HashFunctionIndexed<>(sf, sif);
        HashFunction<Integer> iff = new HashMod<>(14);
        BiFunction<Integer,Integer,Integer> iif = (k,i) -> i;
        HashFunctionIndexed<Integer> i = new HashFunctionIndexed<>(iff, iif);
        assertEqualsL(3, s.apply("mouse", 6));
        assertEqualsL(6, s.apply("", 6));
        assertEqualsL(12, s.apply("foot", 10));
        assertEqualsL(4, s.apply(" ", 0));
        assertEqualsL(4, i.apply(12, 6));
        assertEqualsL(7, i.apply(15, 6));
        assertEqualsL(3, i.apply(-7, 10));
        assertEqualsL(10, i.apply(-8*2/4, 0));
    }

    @Test
    public void hashTable(){
        HashFunction<Integer> ihf = new HashMod<>(7);
        HashFunction<String> shf = new HashMod<>(11);
        HashTable<Integer, String> i = new HashLinearProbing<>(ihf);
        HashTable<String, Integer> s = new HashLinearProbing<>(shf);

        // insert
        i.insert(6, "mayo");
        i.insert(1, "pecan");
        i.insert(-1, "toes");
        //assertEquals(new ArrayList<Integer,String>().add(-1, "toes").add(1, "pecan").add(new Empty()).add(new Empty()).add(new Empty()).add(new Empty()).add(6, "mayo"), i.toString());

        s.insert("sabry", 0);
        s.insert("kailen", 8);
        s.insert("kevin", 10);
        s.insert(" ", -30);
        System.out.println("s inserts: \n" + s);

        // delete
        i.delete(6);
        i.delete(10);
        System.out.println("i deleted 6 and 10*: \n" + i); // didn't actually do anything for 10
        s.delete(" ");
        s.delete("sabry");
        s.delete("grin");
        System.out.println("s deleted 'sabry' and ' ' did not delete grin because not in: \n" + s);

        // SEARCH TIME HEHE
        assertEquals(Optional.of("toes"), i.search(-1));
        assertEquals(Optional.of("pecan"), i.search(1));
        assertEquals(Optional.empty(), i.search(89));
        assertEquals(Optional.of(8), s.search("kailen"));
        assertEquals(Optional.of(10), s.search("kevin"));
        assertEquals(Optional.empty(), s.search("peepeepoopoo"));

        // rehash time
        i.insert(9, "penguin"); // will be at index 2
        i.insert(2, "a"); // will be at index 4
        i.insert(3, "b");  // will be index 5
        i.insert(4, "y"); // will be indxe 6
        i.insert(6, "t");
        System.out.println("prior to rehashing for i: \n" + i);
        i.insert(19, "rehashed");
        System.out.println("rehashed i: \n" + i);
        s.insert("a", 1); // index 10
        s.insert("b", 2); // index 11
        s.insert("c", 3); // index 3
        s.insert("d", 4); // index 4
        s.insert("e", 5); // indez 5
        s.insert("f", 6); // index 6
        s.insert("g", 7);// index 7
        s.insert("12", 9); //index 8
        s.insert("0", 10); // index 9
        System.out.println("prior to rehashing for s: \n" + s);
        s.insert("rehash", 123);
        System.out.println("rehashed s: \n" + s);


    }

    @Test
    public void HashQuadProbing(){
        HashFunction<Integer> ihf = new HashMod<>(5);
        HashFunction<String> shf = new HashMod<>(5);
        HashTable<Integer, String> i = new HashQuadProbing<>(ihf);
        HashTable<String, Integer> s = new HashQuadProbing<>(shf);
        // insert
        i.insert(3, "mayo");
        i.insert(27, "pecan");
        i.insert(-1, "toes");
        System.out.println("i inserts: \n" + i);
        s.insert("jan", -123);
        s.insert("feb", 7);
        s.insert("mar", 29);
        s.insert(" ", -90+100);
        System.out.println("s inserts: \n" + s);

        // delete

        i.delete(3);
        i.delete(10);
        System.out.println("i deleted 3 and 10*: \n" + i); // didn't actually do anything for 10
        s.delete(" ");
        s.delete("jan");
        s.delete("grin");
        System.out.println("s deleted 'jan' and ' ' did not delete grin because not in: \n" + s);

        // SEARCH TIME HEHE

        assertEquals(Optional.of("toes"), i.search(-1));
        assertEquals(Optional.of("pecan"), i.search(27));
        assertEquals(Optional.empty(), i.search(89));
        assertEquals(Optional.of(7), s.search("feb"));
        assertEquals(Optional.of(29), s.search("mar"));
        assertEquals(Optional.empty(), s.search("teehee"));

        // rehash time
        i.insert(9, "coconut");
        i.insert(1, "marsh");
        System.out.println("prior to rehashing for i: \n" + i);
        i.insert(5, "rehashed");
        System.out.println("rehashed i: \n" + i);
        s.insert("a", 1);
        s.insert("b", 2);
        s.insert("c", 3);
        System.out.println("prior to rehashing for s: \n" + s);
        s.insert("rehash", 123);
        System.out.println("rehashed s: \n" + s);
    }

    @Test
    public void HashDouble(){
        HashFunction<Integer> ihf = new HashMod<>(5);
        HashFunction<String> shf = new HashMod<>(5);
        HashTable<Integer, String> i = new HashDouble<>(ihf, ihf);
        HashTable<String, Integer> s = new HashDouble<>(shf, shf);
        // insert
        i.insert(8, "a");
        i.insert(0, "c");
        i.insert(3, "a");
        i.insert(4, "b");
        i.insert(9, "whoop");
        System.out.println("i inserts: \n" + i);
        s.insert("dog", 23);
        s.insert("cat", -100);
        s.insert("a", 0);
        s.insert(" ", 100+24/2);
        s.insert("bunny", 5);
        System.out.println("s inserts: \n" + s);

        // delete
        i.delete(0);
        i.delete(879);
        System.out.println("i deleted 0 and 879*: \n" + i); // didn't actually do anything for 0
        s.delete(" ");
        s.delete("trump");
        System.out.println("s deleted ' ' did not delete trump because not in: \n" + s);

        // SEARCH TIME HEHE
        assertEquals(Optional.of("a"), i.search(3));
        assertEquals(Optional.of("whoop"), i.search(9));
        assertEquals(Optional.empty(), i.search(89));
        assertEquals(Optional.of(5), s.search("bunny"));
        assertEquals(Optional.of(0), s.search("a"));
        assertEquals(Optional.empty(), s.search("joji"));

        // rehash time
        i.insert(17, "pina colada");
        System.out.println("prior to rehashing for i: \n" + i);
        i.insert(5, "rehashed");
        System.out.println("rehashed i: \n" + i);
        s.insert("joke", 3);
        System.out.println("prior to rehashing for s: \n" + s);
        s.insert("rehash", 123);
        System.out.println("rehashed s: \n" + s);
    }

    // efficiency tests hell yeah

    @Test // tests for hashmod, hashuniversal, hashfunctionindexed, hashlinear, hashquad, hashdouble
    public void efficiency(){

        HashFunction<Integer> s = new HashMod<>(100);
        HashFunction<Integer> m = new HashMod<>(1000);
        HashFunction<Integer> l = new HashMod<>(10000);
        HashTable<Integer, String> shl = new HashLinearProbing<>(s);
        HashTable<Integer, String> mhl = new HashLinearProbing<>(m);
        HashTable<Integer, String> lhl = new HashLinearProbing<>(l);
        HashTable<Integer, String> shqp = new HashQuadProbing<>(s);
        HashTable<Integer, String> mhqp = new HashQuadProbing<>(m);
        HashTable<Integer, String> lhqp = new HashQuadProbing<>(l);
        HashTable<Integer, String> shd = new HashDouble<>(s, s);
        HashTable<Integer, String> mhd = new HashDouble<>(m, m);
        HashTable<Integer, String> lhd = new HashDouble<>(l, l);
        Instant start, end;

        for(int i = 0; i < 99; i++){
            shl.insert(i, Integer.toString(i));
            shqp.insert(i, Integer.toString(i));
            shd.insert(i, Integer.toString(i));
        }
        for(int i = 0; i < 999; i++){
            mhl.insert(i, Integer.toString(i));
            mhqp.insert(i, Integer.toString(i));
            mhd.insert(i, Integer.toString(i));
        }
        for(int i = 0; i < 9999; i++){
            lhl.insert(i, Integer.toString(i));
            lhqp.insert(i, Integer.toString(i));
            lhd.insert(i, Integer.toString(i));
        }

        // insert
        System.out.println("Insert");
        start = Instant.now();
        shl.insert(99, "99");
        end = Instant.now();
        System.out.println("Insert for 100 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shqp.insert(99, "99");
        end = Instant.now();
        System.out.println("Insert for 100 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shd.insert(99, "99");
        end = Instant.now();
        System.out.println("Insert for 100 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        mhl.insert(999, "999");
        end = Instant.now();
        System.out.println("Insert for 1000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhqp.insert(999, "999");
        end = Instant.now();
        System.out.println("Insert for 1000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhd.insert(999, "999");
        end = Instant.now();
        System.out.println("Insert for 1000 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        lhl.insert(9999, "9999");
        end = Instant.now();
        System.out.println("Insert for 10000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhqp.insert(9999, "9999");
        end = Instant.now();
        System.out.println("Insert for 10000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhd.insert(9999, "9999");
        end = Instant.now();
        System.out.println("Insert for 10000 on hd: " + Duration.between(start, end).toMillis());
        System.out.println();


        // delete
        System.out.println("Delete");
        start = Instant.now();
        shl.delete(99);
        end = Instant.now();
        System.out.println("Delete for 100 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shqp.delete(99);
        end = Instant.now();
        System.out.println("Delete for 100 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shd.delete(99);
        end = Instant.now();
        System.out.println("Delete for 100 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        mhl.delete(999);
        end = Instant.now();
        System.out.println("Delete for 1000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhqp.delete(999);
        end = Instant.now();
        System.out.println("Delete for 1000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhd.delete(999);
        end = Instant.now();
        System.out.println("Delete for 1000 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        lhl.delete(9999);
        end = Instant.now();
        System.out.println("Delete for 10000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhqp.delete(9999);
        end = Instant.now();
        System.out.println("Delete for 10000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhd.delete(9999);
        end = Instant.now();
        System.out.println("Delete for 10000 on hd: " + Duration.between(start, end).toMillis());
        System.out.println();

        // search
        System.out.println("Search");
        start = Instant.now();
        shl.search(99);
        end = Instant.now();
        System.out.println("Search for 100 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shqp.search(99);
        end = Instant.now();
        System.out.println("Search for 100 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shd.search(99);
        end = Instant.now();
        System.out.println("Search for 100 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        mhl.search(999);
        end = Instant.now();
        System.out.println("Search for 1000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhqp.search(999);
        end = Instant.now();
        System.out.println("Search for 1000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhd.search(999);
        end = Instant.now();
        System.out.println("Search for 1000 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        lhl.search(9999);
        end = Instant.now();
        System.out.println("Search for 10000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhqp.search(9999);
        end = Instant.now();
        System.out.println("Search for 10000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhd.search(9999);
        end = Instant.now();
        System.out.println("Search for 10000 on hd: " + Duration.between(start, end).toMillis());
        System.out.println();

        // rehash
        System.out.println("Rehash");
        shl.insert(99, "99");
        shqp.insert(99, "99");
        shd.insert(99, "99");
        mhl.insert(999, "999");
        mhqp.insert(999, "999");
        mhd.insert(999, "999");
        lhl.insert(9999, "9999");
        lhqp.insert(9999, "9999");
        lhd.insert(9999, "9999");
        start = Instant.now();
        shl.insert(100, "100");
        end = Instant.now();
        System.out.println("Rehash for 100 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shqp.insert(100, "100");
        end = Instant.now();
        System.out.println("Rehash for 100 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        shd.insert(100, "100");
        end = Instant.now();
        System.out.println("Rehash for 100 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        mhl.insert(1000, "1000");
        end = Instant.now();
        System.out.println("Rehash for 1000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhqp.insert(1000, "1000");
        end = Instant.now();
        System.out.println("Rehash for 1000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        mhd.insert(1000, "1000");
        end = Instant.now();
        System.out.println("Rehash for 1000 on hd: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        lhl.insert(10000, "10000");
        end = Instant.now();
        System.out.println("Rehash for 10000 on hl: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhqp.insert(10000, "10000");
        end = Instant.now();
        System.out.println("Rehash for 10000 on hqp: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        lhd.insert(10000, "10000");
        end = Instant.now();
        System.out.println("Rehash for 10000 on hd: " + Duration.between(start, end).toMillis());
        System.out.println();

    }

    @Test
    void shit(){
        HashFunction<Integer> hf = new HashMod<>(11);
        HashTable<Integer, String> ht = new HashLinearProbing<>(hf);
        ht.insert(1, "_");
        ht.insert(12, "_");
        ht.insert(23, "_");
        ht.insert(34, "_");
        ht.insert(45, "_");
        ht.insert(3, "_");
        System.out.println(ht);
    }

}