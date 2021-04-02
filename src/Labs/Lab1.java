// Name: Sophia Zhang
// Date: August 27 2020

import java.util.ArrayList;

public class Lab1 {

    public static ArrayList<Integer> square(ArrayList<Integer> list){
        ArrayList<Integer> output = new ArrayList<Integer>(list.size());
        int count = 0;
        for(int i:list){
            output.add(i*i);
        }
        return output;
    }

    public static void main(String[] args){
        ArrayList<Integer> a = new ArrayList<Integer>(5);
        for(int i = 0; i < 10; i+=2){
            a.add(2 + i);
        }
        System.out.println(a);
        System.out.println(square(a));
    }
}
