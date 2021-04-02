import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class DequeueArrayTest {

    @Test
    public void dequeDoubleNoResize() throws NoSuchElementE {
        DequeueArray<Integer> d = new DequeueArrayDouble<>(5);
        assertEquals(0, d.size());
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeLast());
        assertEquals(2, (int) d.removeLast());
        assertEquals(0, d.size());
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
    }

    @Test
    public void dequeDoubleResize() throws NoSuchElementE {
        DequeueArray<Integer> d = new DequeueArrayDouble<>(5);
        d.addLast(1);
        System.out.println(d.toString());
        d.addLast(2);
        System.out.println(d.toString());
        d.addFirst(3);
        System.out.println(d.toString());
        d.addLast(4);
        System.out.println(d.toString());
        d.addFirst(5);
        System.out.println(d.toString());
        d.addFirst(6);
        System.out.println(d.toString());
        assertEquals(10, d.getCapacity());
        assertEquals(6, (int) d.removeFirst());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
    }

    // own correction tests :D

    @Test
    public void clear(){
        DequeueArray<String> a = new DequeueArrayDouble<>(3);
        DequeueArray<String> b = new DequeueArrayDouble<>(3);
        DequeueArray<Integer> c = new DequeueArrayDouble<>(5);
        DequeueArray<Integer> d = new DequeueArrayDouble<>(5);
        b.addFirst("sucks");
        b.addFirst("medication");
        b.addFirst("pain");
        System.out.println(b.toString());
        b.clear(3);
        System.out.println("b cleared");
        System.out.println(b.toString());
        assertEquals(a, b);
        d.addLast(666);
        d.addLast(69696969);
        d.addLast(696969);
        d.addLast(6969);
        d.addLast(69);
        System.out.println(d.toString());
        d.clear(5);
        System.out.println("d cleared");
        System.out.println(d.toString());
        assertEquals(c, d);
    }

    @Test
    public void size(){
        DequeueArray<String> a = new DequeueArrayDouble<>(5);
        DequeueArray<Integer> b = new DequeueArrayDouble<>(10);
        assertEquals(0, a.size());
        assertEquals(0, b.size());
        a.addLast("no");
        a.addLast("more");
        a.addLast("throat");
        a.addLast("abscess");
        a.addLast("pls");
        assertEquals(5, a.size());
        b.addFirst(1);
        b.addFirst(12);
        b.addFirst(123);
        assertEquals(3, b.size());
    }

    @Test
    public void addFirst(){
        DequeueArray<Integer> a = new DequeueArrayOneAndHalf<>(3);
        DequeueArray<String> b = new DequeueArrayOneAndHalf<>(4);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        System.out.println("a: 1 2 3");
        System.out.println(a.toString());
        b.addFirst("significant");
        b.addFirst("culturally");
        b.addFirst("is");
        b.addFirst("wap");
        System.out.println("b: wap is culturally significant");
        System.out.println(b.toString());
    }

    @Test
    public void addLast(){
        DequeueArray<Integer> a = new DequeueArrayOneAndHalf<>(3);
        DequeueArray<String> b = new DequeueArrayOneAndHalf<>(4);
        a.addLast(5);
        a.addLast(4);
        a.addLast(3);
        System.out.println("a: 5 4 3");
        System.out.println(a.toString());
        b.addLast("i");
        b.addLast("am");
        b.addLast("so");
        b.addLast("baby");
        System.out.println("b: i am so baby");
        System.out.println(b.toString());
    }

    @Test
    public void getFirst() throws NoSuchElementE{
        DequeueArray<Integer> a = new DequeueArrayOneAndHalf<>(3);
        DequeueArray<String> b = new DequeueArrayOneAndHalf<>(4);
        a.addFirst(3);
        a.addLast(5);
        a.addFirst(4);
        assertEquals(5, a.getFirst());
        b.addFirst("trash");
        b.addLast("League");
        b.addFirst("is");
        b.addFirst("mega");
        assertEquals("League", b.getFirst());
        assertThrows(NoSuchElementE.class, () -> {
            DequeueArray<String> c = new DequeueArrayOneAndHalf<>(5);
            c.getFirst();
        });
    }

    @Test
    public void getLast() throws NoSuchElementE{
        DequeueArray<Integer> a = new DequeueArrayOneAndHalf<>(3);
        DequeueArray<String> b = new DequeueArrayOneAndHalf<>(4);
        a.addLast(6);
        a.addLast(2);
        a.addFirst(1);
        assertEquals(1, a.getLast());
        b.addLast("he");
        b.addLast("chose");
        b.addLast("mf");
        b.addLast("LEAGUE");
        assertEquals("LEAGUE", b.getLast());
        assertThrows(NoSuchElementE.class, () -> {
            DequeueArray<String> c = new DequeueArrayOneAndHalf<>(5);
            c.getLast();
        });
    }

    @Test
    public void removeFirst() throws NoSuchElementE {
        DequeueArray<Integer> a = new DequeueArrayOneAndHalf<>(3);
        DequeueArray<String> b = new DequeueArrayOneAndHalf<>(5);
        a.addLast(6);
        a.addLast(2);
        a.addFirst(1);
        assertEquals(1, a.removeFirst());
        b.addFirst("check");
        b.addFirst("poo");
        b.addLast("pee");
        b.addLast("pee");
        b.addLast("poo");
        assertEquals("poo", b.removeFirst());
        assertThrows(NoSuchElementE.class, () -> {
            DequeueArray<String> c = new DequeueArrayOneAndHalf<>(5);
            c.removeFirst();
        });
    }

    @Test
    public void removeLast() throws NoSuchElementE{
        DequeueArray<Integer> a = new DequeueArrayOneAndHalf<>(3);
        DequeueArray<String> b = new DequeueArrayOneAndHalf<>(5);
        a.addLast(8);
        a.addLast(9);
        assertEquals(9, a.removeLast());
        b.addLast("cats");
        b.addFirst("dogs");
        b.addFirst("cat");
        b.addLast("dog");
        b.addLast("poetic");
        assertEquals("poetic", b.removeLast());
        assertThrows(NoSuchElementE.class, () -> {
            DequeueArray<String> c = new DequeueArrayOneAndHalf<>(5);
            c.removeLast();
        });
    }

    @Test
    public void dequeueArrayDouble() throws NoSuchElementE {
        DequeueArray<String> a = new DequeueArrayDouble<>(5);
        a.addLast("crocs");
        a.addLast("are");
        a.addFirst("supreme");
        a.addLast("super");
        a.addFirst("duper");
        assertEquals(5, a.getCapacity());
        System.out.println(a.toString());
        a.addLast("...");
        a.addFirst("?");
        a.addFirst("stutter");
        a.addLast("did");
        a.addLast("i");
        assertEquals(10, a.getCapacity());
        System.out.println(a.toString());
        assertEquals("i", a.removeLast());
        assertEquals("stutter", a.removeFirst());
        assertEquals("did", a.removeLast());
        assertEquals("?", a.removeFirst());
        assertEquals("...", a.removeLast());
        assertEquals("super", a.removeLast());
        assertEquals("are", a.removeLast());
        assertEquals("crocs", a.removeLast());
        assertEquals("supreme", a.removeLast());
        assertEquals("duper", a.removeFirst());
        System.out.println(a.toString());
        assertThrows(NoSuchElementE.class, () -> {
            DequeueArray<Integer> b = new DequeueArrayDouble<>(3);
            b.removeLast();
        });
    }

    @Test
    public void dequeueArrayOneAndHalf() throws NoSuchElementE {
        DequeueArray<Integer> a = new DequeueArrayOneAndHalf<>(4);
        a.addFirst(6);
        a.addFirst(5);
        a.addLast(4);
        a.addLast(3);
        System.out.println(a.toString());
        a.addFirst(17);
        System.out.println(a.toString());
        a.addLast(9);
        System.out.println(a.toString());
        a.addFirst(1);
        System.out.println(a.toString());
        assertEquals(9, a.removeLast());
        assertEquals(3, a.removeLast());
        assertEquals(4, a.removeLast());
        assertEquals(1, a.removeFirst());
        assertEquals(17, a.removeFirst());
        System.out.println(a.toString());
        assertThrows(NoSuchElementE.class, () -> {
            DequeueArray<String> b = new DequeueArrayDouble<>(10);
            b.removeLast();
        });
    }

    @Test
    public void dequeueArrayPlusOne() throws NoSuchElementE {
        DequeueArray<String> a = new DequeueArrayPlusOne<>(5);
        a.addFirst("freak");
        a.addFirst("certified");
        a.addLast("7 days");
        a.addLast("a");
        a.addLast("week");
        System.out.println(a.toString());
        a.addFirst("wap");
        System.out.println(a.toString());
        a.addLast("this is slightly innapropriate i apologize");
        System.out.println(a.toString());
        assertEquals("wap", a.removeFirst());
        assertEquals("certified", a.removeFirst());
        assertEquals("freak", a.removeFirst());
        assertEquals("this is slightly innapropriate i apologize", a.removeLast());
        assertEquals("week", a.removeLast());
        assertEquals("a", a.removeLast());
        System.out.println(a.toString());
        assertThrows(NoSuchElementE.class, () -> {
            DequeueArray<Integer> d = new DequeueArrayDouble<>(6);
            d.removeLast();
        });
    }

    // Efficiency test ;w;

    static void randomizer(int count, DequeueArray<Integer> d){
        Random rand = new Random();
        for(int i = 0; i < count; i++){
            if(i % 3 == 0 || i % 7 == 0){
                d.addLast(rand.nextInt(500) + 1);
            }
            else {
                d.addFirst(rand.nextInt(500) + 1);
            }
        }
    }

    @Test

    public void efficiencyTesting(){

        int size = 7;
        DequeueArray<Integer> a = new DequeueArrayDouble<>(size);
        DequeueArray<Integer> b = new DequeueArrayOneAndHalf<>(size);
        DequeueArray<Integer> c = new DequeueArrayPlusOne<>(size);

        long t1 = 0, st1, et1;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(500, a);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 500 items for 2x dequeue, a: " + t1);

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(500, b);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 500 items for 1.5 dequeue, b: " + t1);

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(500, c);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 500 items for +1 dequeue, c: " + t1);

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(5000, a);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 5000 items for 2x dequeue, a: " + t1);

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(5000, b);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 5000 items for 1.5 dequeue, b: " + t1);

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(5000, c);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 5000 items for +1 dequeue, c: " + t1); // my computer is WEEPING.

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(20000, a);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 20000 items for 2x dequeue, a: " + t1);

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            randomizer(20000, b);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 20000 items for 1.5 dequeue, b: " + t1);

        t1 = 0;
        st1 = 0;
        et1 = 0;
        for(int i = 0; i < 3; i++){ // cut it down more because it literally took forever.
            st1 = System.currentTimeMillis();
            randomizer(20000, c);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        t1 = t1 / 10;
        System.out.println("Time for 20000 items for +1 dequeue, c: " + t1); // my computer just seppukued itself


    }

}
