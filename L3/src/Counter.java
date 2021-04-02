import java.util.Arrays;

public class Counter {
    private int[] array;

    Counter (int size) {
        array = new int[size];
    }

    int get (int i) {
        return array[i];
    }

    int toDecimal () {
        int result = 0;
        int power = 1;
        for (int i=0; i<array.length; i++) {
            result += array[i] * power;
            power *= 2;
        }
        return result;
    }

    void inc () {
        // implement inc here
        // best way to test: for loop up to n; test and then do a higher one
        // amount of time should incrase linearly lmao ok
        int index = 0;
        do {
            if (index == array.length) {
                break;
            }

            if (this.get(index) == 0) {
                this.array[index] = 1;
                break;
            } else if (this.get(index) == 1) {
                this.array[index] = 0;
            }
            index++;
        } while(true);

    }

    public String toString () {
        return Arrays.toString(array);
    }


}
