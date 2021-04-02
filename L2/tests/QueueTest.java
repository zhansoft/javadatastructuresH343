import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;


class QueueTest {

    @Test
    void test1 () throws EmptyQueueE {
        Queue q = new Queue();
        System.out.printf("q = %s%n", q);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.printf("q = %s%n", q);

        System.out.printf("Dequeue %d%n", q.dequeue());
        System.out.printf("q = %s%n", q);

        System.out.printf("Dequeue %d%n", q.dequeue());
        System.out.printf("q = %s%n", q);

        System.out.printf("Dequeue %d%n", q.dequeue());
        System.out.printf("q = %s%n", q);

        System.out.printf("Dequeue %d%n", q.dequeue());
        System.out.printf("q = %s%n", q);

        System.out.printf("Dequeue %d%n", q.dequeue());
        System.out.printf("q = %s%n", q);
    }

    // create amortization tests below
    // timer function from A1
    <A,B> Duration timeit (Function<A,B> f, A x) {
        Instant start = Instant.now();
        f.apply(x);
        Instant end = Instant.now();
        return Duration.between(start, end);
    }


    @Test
    void amortization() throws EmptyQueueE{
        // test for 1000 elements
        // time 1000 enqueing and the time the dequeuing the 1000 individually
        Queue a = new Queue();
        Queue b = new Queue();
        Queue c = new Queue();
        ArrayList<Long> enquetimes = new ArrayList<Long>(); // it'll read like a,a,b,b,c,c
        ArrayList<Long> dequetimes = new ArrayList<Long>(); // it'll read like a,b,c

        long stime, etime, enqtime, deqtime;
        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            a.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            a.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            a.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes.add(etime-stime);


        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            b.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            b.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            b.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes.add(etime-stime);


        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            c.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            c.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            c.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes.add(etime-stime);

        System.out.println("Enqueue times for 1000: " + enquetimes);
        System.out.println("Dequeue times for 1000: " + dequetimes);


        // reset
        for(int i = 0 ; i < 1000; i++){
            a.dequeue();
            b.dequeue();
            c.dequeue();
        }


        // now we try 5000 MUAHAHAHAHAHAHAHAH
        ArrayList<Long> enquetimes5 = new ArrayList<Long>(); // it'll read like a,a,b,b,c,c
        ArrayList<Long> dequetimes5 = new ArrayList<Long>(); // it'll read like a,b,c

        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            a.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes5.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            a.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes5.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            a.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes5.add(etime-stime);


        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            b.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes5.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            b.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes5.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            b.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes5.add(etime-stime);


        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            c.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes5.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            c.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes5.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            c.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes5.add(etime-stime);

        System.out.println("Enqueue times for 5000: " + enquetimes5);
        System.out.println("Dequeue times for 5000: " + dequetimes5);


        //time to go 10000
        ArrayList<Long> enquetimes10 = new ArrayList<Long>(); // it'll read like a,a,b,b,c,c
        ArrayList<Long> dequetimes10 = new ArrayList<Long>(); // it'll read like a,b,c

        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            a.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes10.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            a.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes10.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            a.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes10.add(etime-stime);


        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            b.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes10.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            b.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes10.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            b.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes10.add(etime-stime);


        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            c.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes10.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            c.dequeue();
        }
        etime = System.currentTimeMillis();
        dequetimes10.add(etime-stime);
        stime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            c.enqueue(1);
        }
        etime = System.currentTimeMillis();
        enquetimes10.add(etime-stime);

        System.out.println("Enqueue times for 10000: " + enquetimes10);
        System.out.println("Dequeue times for 10000: " + dequetimes10);

        // reset
        for(int i = 0 ; i < 10000; i++){
            a.dequeue();
            b.dequeue();
            c.dequeue();
        }




    }
}