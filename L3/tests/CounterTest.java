import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    @Test
    void init () {
        Counter c = new Counter(4);
        System.out.printf("array = %s%n", c);
        assertEquals(0,c.get(0));
        assertEquals(0,c.get(1));
        assertEquals(0,c.get(2));
        assertEquals(0,c.get(3));
        assertEquals(0, c.toDecimal());
    }

    @Test
    void count1 () {
        Counter c = new Counter(3);
        assertEquals(0, c.toDecimal());
        c.inc();
        assertEquals(1, c.toDecimal());
        c.inc();
        assertEquals(2, c.toDecimal());
        c.inc();
        assertEquals(3, c.toDecimal());
        c.inc();
        assertEquals(4, c.toDecimal());
        c.inc();
        assertEquals(5, c.toDecimal());
        c.inc();
        assertEquals(6, c.toDecimal());
        c.inc();
        assertEquals(7, c.toDecimal());
    }

    // create amortization tests here

    @Test
    void time1000(){
        long t1, st, et;
        Counter c = new Counter(10);
        st = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            c.inc();
        }
        et = System.currentTimeMillis();
        t1 = et - st;
        System.out.println("Time for 1000: " + t1);
    }

    @Test
    void time10000(){
        long t1, st, et;
        Counter c = new Counter(14);
        st = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            c.inc();
        }
        et = System.currentTimeMillis();
        t1 = et - st;
        System.out.println("Time for 10000: " + t1);
    }

    @Test
    void time100000(){
        long t1, st, et;
        Counter c = new Counter(17);
        st = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++){
            c.inc();
        }
        et = System.currentTimeMillis();
        t1 = et - st;
        System.out.println("Time for 100000: " + t1);
    }
}