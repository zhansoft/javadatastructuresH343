import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DupTest {
    private List<Integer> ints;

    @Test
    void sort () {
        ints.clear();
        Collections.addAll(ints,1, 4, 0, 2, 2, 5, 3);
        System.out.println(Dup.noDups3(ints));
        System.out.println(ints); // no longer the same list
    }

    @Test
    void small () {
        List<Integer> ints = new ArrayList<>();

        ints.clear();
        Collections.addAll(ints,1,2,2,3);
        System.out.println(Dup.noDups1(ints));

        ints.clear();
        Collections.addAll(ints,1,2,2,3);
        System.out.println(Dup.noDups2(ints));

        ints.clear();
        Collections.addAll(ints,1,2,2,3);
        System.out.println(Dup.noDups3(ints));

        ints.clear();
        Collections.addAll(ints,1,2,2,3);
        System.out.println(Dup.noDups4(ints));

        ints.clear();
        Collections.addAll(ints,1,2,2,3);
        System.out.println(Dup.noDups5(ints));
    }

    @BeforeEach
    void bigList () {
        Random r = new Random();
        IntStream s = r.ints(10000);
        ints = s.boxed().collect(Collectors.toList());
    }

    <A,B> Duration timeit (Function<A,B> f, A x) {
        Instant start = Instant.now();
        f.apply(x);
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void big () {
        long t1, t2, t3, t4, t5;

        t1 = timeit(Dup::noDups1, ints).toMillis();
        System.out.printf("noDups1: %d ms%n", t1);

        t2 = timeit(Dup::noDups2, ints).toMillis();
        System.out.printf("noDups2: %d ms%n", t2);

        t3 = timeit(Dup::noDups3, ints).toMillis();
        System.out.printf("noDups3: %d ms%n", t3);

        t4 = timeit(Dup::noDups4, ints).toMillis();
        System.out.printf("noDups4: %d ms%n", t4);

        t5 = timeit(Dup::noDups5, ints).toMillis();
        System.out.printf("noDups5: %d ms%n", t5);

        // t1, t4, and t5 are close to each other
        // t3 is next
        // t2 is worst
        assertTrue(t1 < t2);
        assertTrue(t1 < t3);
        assertTrue(t3 < t2);
    }


    @Test
    void correctness() {
        // assert a list containing lots of duplicates but 3 distinct integers and the return should be size 3
        List<Integer> ints = new ArrayList<>();


        ints.clear();
        Collections.addAll(ints, 1, 2, 3, 2, 3, 2, 3, 1, 1, 2, 3, 2, 3, 2, 3, 1, 1, 2, 3, 2, 3, 2, 3, 1, 1, 2, 3, 2, 3, 2, 3, 1, 1, 2, 3, 2, 3, 2, 3, 1, 1, 2, 3, 2, 3, 2, 3, 1);
        List<Integer> testans = Dup.noDups1(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups2(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups4(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups5(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups3(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());


        ints.clear();
        Collections.addAll(ints, 1,2,3,2,3,2,3,2,3,1,2,3);
        testans = Dup.noDups1(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups2(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups4(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups5(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups3(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());


        ints.clear();
        Collections.addAll(ints, 1,2,3,2);
        testans = Dup.noDups1(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups2(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups4(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups5(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());

        ints.clear();
        for(int i = 0; i < 302; i++){
            ints.add(1);
        }
        for(int i = 0; i < 200; i++){
            ints.add(2);
        }
        for(int i = 0; i < 500; i++){
            ints.add(3);
        }
        testans = Dup.noDups1(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups2(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups4(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());
        testans = Dup.noDups5(ints);
        assertEquals(1, testans.get(0));
        assertEquals(2, testans.get(1));
        assertEquals(3, testans.get(2));
        assertEquals(3, testans.size());

    }


    // testing the efficiency of the methods.
    @Test
    void efficiency1(){
        List<Integer> ints = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        long t1, t2, t3, t4, t5;

        List<String> method1time = new ArrayList<>();
        List<String> method2time = new ArrayList<>();
        List<String> method3time = new ArrayList<>();
        List<String> method4time = new ArrayList<>();
        List<String> method5time = new ArrayList<>();

        // noDups1 for empty list
        ints.clear();
        ans.clear();
        System.out.println("Empty Set");
        t1 = timeit(Dup::noDups1, ints).toMillis();
        System.out.printf("noDups1: %d ms%n", t1);
        assertEquals(ans, Dup.noDups1(ints));
        method1time.add(Long.toString(t1));
        t2 = timeit(Dup::noDups2, ints).toMillis();
        System.out.printf("noDups2: %d ms%n", t2);
        assertEquals(ans, Dup.noDups2(ints));
        method2time.add(Long.toString(t2));
//        t3 = timeit(Dup::noDups3, ints).toMillis();
//        System.out.printf("noDups3: %d ms%n", t3);
//        assertEquals(ans, Dup.noDups3(ints));
        method3time.add("-");
        t4 = timeit(Dup::noDups4, ints).toMillis();
        System.out.printf("noDups4: %d ms%n", t4);
        assertEquals(ans, Dup.noDups4(ints));
        method4time.add(Long.toString(t4));
        t5 = timeit(Dup::noDups5, ints).toMillis();
        System.out.printf("noDups5: %d ms%n", t5);
        assertEquals(ans, Dup.noDups5(ints));
        method5time.add(Long.toString(t5));
        System.out.println();


        // noDups1 for 10000
        ints.clear();
        Collections.addAll(ans, 2,3,4);
        for(int i = 0 ; i < 5000; i++){
            ints.add(2);
        }
        for(int i = 0 ; i < 2000; i++){
            ints.add(3);
        }
        for(int i = 0 ; i < 3000; i++){
            ints.add(4);
        }
        System.out.println("10000 Set");
        t1 = timeit(Dup::noDups1, ints).toMillis();
        System.out.printf("noDups1: %d ms%n", t1);
        assertEquals(ans, Dup.noDups1(ints));
        method1time.add(Long.toString(t1));

        t2 = timeit(Dup::noDups2, ints).toMillis();
        System.out.printf("noDups2: %d ms%n", t2);
        assertEquals(ans, Dup.noDups2(ints));
        method2time.add(Long.toString(t2));

        t4 = timeit(Dup::noDups4, ints).toMillis();
        System.out.printf("noDups4: %d ms%n", t4);
        assertEquals(ans, Dup.noDups4(ints));
        method4time.add(Long.toString(t4));

        t5 = timeit(Dup::noDups5, ints).toMillis();
        System.out.printf("noDups5: %d ms%n", t5);
        assertEquals(ans, Dup.noDups5(ints));
        method5time.add(Long.toString(t5));

        t3 = timeit(Dup::noDups3, ints).toMillis();
        System.out.printf("noDups3: %d ms%n", t3);
        assertEquals(ans, Dup.noDups3(ints));
        method3time.add(Long.toString(t3));
        System.out.println();


        // noDups1 for 50000
        ints.clear();
        for(int i = 0 ; i < 15000; i++){
            ints.add(2);
        }
        for(int i = 0 ; i < 15000; i++){
            ints.add(3);
        }
        for(int i = 0 ; i < 20000; i++){
            ints.add(4);
        }
        System.out.println("50000 Set");
        t1 = timeit(Dup::noDups1, ints).toMillis();
        System.out.printf("noDups1: %d ms%n", t1);
        assertEquals(ans, Dup.noDups1(ints));
        method1time.add(Long.toString(t1));

        t2 = timeit(Dup::noDups2, ints).toMillis();
        System.out.printf("noDups2: %d ms%n", t2);
        assertEquals(ans, Dup.noDups2(ints));
        method2time.add(Long.toString(t2));

        t4 = timeit(Dup::noDups4, ints).toMillis();
        System.out.printf("noDups4: %d ms%n", t4);
        assertEquals(ans, Dup.noDups4(ints));
        method4time.add(Long.toString(t4));

        t5 = timeit(Dup::noDups5, ints).toMillis();
        System.out.printf("noDups5: %d ms%n", t5);
        assertEquals(ans, Dup.noDups5(ints));
        method5time.add(Long.toString(t5));

        t3 = timeit(Dup::noDups3, ints).toMillis();
        System.out.printf("noDups3: %d ms%n", t3);
        assertEquals(ans, Dup.noDups3(ints));
        method3time.add(Long.toString(t3));
        System.out.println();


        // 100000
        ints.clear();
        for(int i = 0 ; i < 50000; i++){
            ints.add(2);
        }
        for(int i = 0 ; i < 30000; i++){
            ints.add(3);
        }
        for(int i = 0 ; i < 20000; i++){
            ints.add(4);
        }
        System.out.println("100000 Set");
        t1 = timeit(Dup::noDups1, ints).toMillis();
        System.out.printf("noDups1: %d ms%n", t1);
        assertEquals(ans, Dup.noDups1(ints));
        method1time.add(Long.toString(t1));

        t2 = timeit(Dup::noDups2, ints).toMillis();
        System.out.printf("noDups2: %d ms%n", t2);
        assertEquals(ans, Dup.noDups2(ints));
        method2time.add(Long.toString(t2));

        t4 = timeit(Dup::noDups4, ints).toMillis();
        System.out.printf("noDups4: %d ms%n", t4);
        assertEquals(ans, Dup.noDups4(ints));
        method4time.add(Long.toString(t4));

        t5 = timeit(Dup::noDups5, ints).toMillis();
        System.out.printf("noDups5: %d ms%n", t5);
        assertEquals(ans, Dup.noDups5(ints));
        method5time.add(Long.toString(t5));

        t3 = timeit(Dup::noDups3, ints).toMillis();
        System.out.printf("noDups3: %d ms%n", t3);
        assertEquals(ans, Dup.noDups3(ints));
        method3time.add(Long.toString(t3));
        System.out.println();

        // noDups for 500000
        ints.clear();
        for(int i = 0 ; i < 100000; i++){
            ints.add(2);
        }
        for(int i = 0 ; i < 300000; i++){
            ints.add(3);
        }
        for(int i = 0 ; i < 100000; i++){
            ints.add(4);
        }
        System.out.println("500000 Set");
        t1 = timeit(Dup::noDups1, ints).toMillis();
        System.out.printf("noDups1: %d ms%n", t1);
        assertEquals(ans, Dup.noDups1(ints));
        method1time.add(Long.toString(t1));

        t2 = timeit(Dup::noDups2, ints).toMillis();
        System.out.printf("noDups2: %d ms%n", t2);
        assertEquals(ans, Dup.noDups2(ints));
        method2time.add(Long.toString(t2));

        t4 = timeit(Dup::noDups4, ints).toMillis();
        System.out.printf("noDups4: %d ms%n", t4);
        assertEquals(ans, Dup.noDups4(ints));
        method4time.add(Long.toString(t4));

        t5 = timeit(Dup::noDups5, ints).toMillis();
        System.out.printf("noDups5: %d ms%n", t5);
        assertEquals(ans, Dup.noDups5(ints));
        method5time.add(Long.toString(t5));

        t3 = timeit(Dup::noDups3, ints).toMillis();
        System.out.printf("noDups3: %d ms%n", t3);
        assertEquals(ans, Dup.noDups3(ints));
        method3time.add(Long.toString(t3));
        System.out.println();

        // noDups1 for 1000000
        ints.clear();
        for(int i = 0 ; i < 500000; i++){
            ints.add(2);
        }
        for(int i = 0 ; i < 300000; i++){
            ints.add(3);
        }
        for(int i = 0 ; i < 200000; i++){
            ints.add(4);
        }
        System.out.println(ints.size());
        System.out.println("1000000 Set");
        t1 = timeit(Dup::noDups1, ints).toMillis();
        System.out.printf("noDups1: %d ms%n", t1);
        assertEquals(ans, Dup.noDups1(ints));
        method1time.add(Long.toString(t1));

        System.out.println(ints.size());
        t2 = timeit(Dup::noDups2, ints).toMillis();
        System.out.printf("noDups2: %d ms%n", t2);
        assertEquals(ans, Dup.noDups2(ints));
        method2time.add(Long.toString(t2));

        System.out.println(ints.size());
        t4 = timeit(Dup::noDups4, ints).toMillis();
        System.out.printf("noDups4: %d ms%n", t4);
        assertEquals(ans, Dup.noDups4(ints));
        method4time.add(Long.toString(t4));

        System.out.println(ints.size());
        t5 = timeit(Dup::noDups5, ints).toMillis();
        System.out.printf("noDups5: %d ms%n", t5);
        assertEquals(ans, Dup.noDups5(ints));
        method5time.add(Long.toString(t5));


        System.out.println(ints.size());
        t3 = timeit(Dup::noDups3, ints).toMillis();
        System.out.printf("noDups3: %d ms%n", t3);
        assertEquals(ans, Dup.noDups3(ints));
        method3time.add(Long.toString(t3));
        System.out.println();


        System.out.println("noDups1 times:" + method1time.toString());
        System.out.println("noDups2 times:" + method2time.toString());
        System.out.println("noDups3 times:" + method3time.toString());
        System.out.println("noDups4 times:" + method4time.toString());
        System.out.println("noDups5 times:" + method5time.toString());
    }

}