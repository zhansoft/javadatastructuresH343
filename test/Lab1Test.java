import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Lab1Test {

    @Test
    void square() {
        ArrayList<Integer> a = new ArrayList<Integer>(5);
        for(int i = 0; i < 5; i++){
            a.add(1 + i);
        }
        ArrayList<Integer> output = new ArrayList<Integer>(5);
        for(int i = 0; i <5; i++){
            output.add((1+i) * (1+i));
        }
        assertEquals(Lab1.square(a), output);

        // tests an empty ArrayList
        ArrayList<Integer> b = new ArrayList<Integer>();
        ArrayList<Integer> outputb = new ArrayList<Integer>();
        assertEquals(Lab1.square(b), outputb);

        // tests for an ArrayList like [2, 4, 6, 8, 10]
        ArrayList<Integer> c = new ArrayList<Integer>(5);
        for(int i = 0; i < 5; i++){
            c.add(2 + i);
        }
        ArrayList<Integer> outputc = new ArrayList<Integer>(5);
        for(int i = 0; i <5; i++){
            outputc.add((2+i) * (2+i));
        }
        assertEquals(Lab1.square(c), outputc);
    }

    // tests an empty ArrayList
    @Test
    void emptysquare(){
        ArrayList<Integer> b = new ArrayList<Integer>();
        ArrayList<Integer> outputb = new ArrayList<Integer>();
        assertEquals(Lab1.square(b), outputb);
    }

    // tests for an ArrayList like [2, 4, 6, 8, 10]
    @Test
    void twosquare() {
        ArrayList<Integer> c = new ArrayList<Integer>(5);
        for(int i = 0; i < 10; i+=2){
            c.add(2 + i);
        }
        ArrayList<Integer> outputc = new ArrayList<Integer>(5);
        for(int i = 0; i <10; i+=2){
            outputc.add((2+i) * (2+i));
        }
        assertEquals(Lab1.square(c), outputc);
    }
}