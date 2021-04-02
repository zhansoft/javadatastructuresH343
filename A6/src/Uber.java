import java.util.Map;
import java.util.WeakHashMap;
import java.util.*;


public class Uber {

    // Each ride has a length of time and a price.

    // We are given a list of rides and a max length of time. We would like to
    // maximize the price without exceeding the available time. In other
    // words, we want to select the most expensive rides whose total length is
    // no more than the time we have.

    // When considering ride i, it might be possible that two solutions of
    // equal cost can be produced where one solution include ride-i and the other
    // does not include ride-i. In case of such ties, report the solution
    // that does include ride-i



    static Pair<List<Ride>, Integer> schedule(List<Ride> rides, int maxLength){
        // TODO. Solution goes here
        if(rides.equals(new Empty())){
            return new Pair<>(new Empty(), 0);
        }
        if(maxLength <= 0){
            return new Pair<List<Ride>, Integer>(new Empty<>(), 0);
        }
        try{
            Pair<List<Ride>, Integer> takept2 = schedule(rides.getRest(), maxLength - rides.getFirst().getLength());
            Pair<List<Ride>, Integer> take = new Pair(new Node<>(rides.getFirst(), takept2.getFirst()), rides.getFirst().getPrice() + takept2.getSecond());

            // error propagation in case of bad take
            if(rides.getFirst().getLength() > maxLength){
                take = new Pair<>(new Empty(), 0);
            }
            Pair<List<Ride>, Integer> skip = schedule(rides.getRest(), maxLength);

            if(take.getSecond() >= skip.getSecond()){
                return take;
            }
            else{
                return skip;
            }
        }
        catch(EmptyListE e) {
            return new Pair<List<Ride>, Integer>(new Empty<>(), 0);
        }
    }

    // memo
    static Map<Pair<List<Ride>, Integer>, Pair<List<Ride>, Integer>> uberList = new WeakHashMap<>();

    // TODO. Top-down solution (mschedule) goes here
    static Pair<List<Ride>, Integer> mschedule(List<Ride> rides, int maxLength){
        // TODO
        Pair<List<Ride>, Integer> pKey = new Pair<>(rides, maxLength);
        if(uberList.containsKey(pKey)){
            return uberList.get(pKey);
        }
        else{
            if(rides.equals(new Empty())){
                return new Pair<>(new Empty(), 0);
            }
            if(maxLength == 0){
                return new Pair<>(new Empty<>(), 0);
            }
            try{
                Pair<List<Ride>, Integer> takept2 = mschedule(rides.getRest(), maxLength - rides.getFirst().getLength());
                Pair<List<Ride>, Integer> take = new Pair(new Node<>(rides.getFirst(), takept2.getFirst()), rides.getFirst().getPrice() + takept2.getSecond());

                // error propagation in case of bad take
                if(rides.getFirst().getLength() > maxLength){
                    take = new Pair<>(new Empty(), 0);
                }

                Pair<List<Ride>, Integer> skip = mschedule(rides.getRest(), maxLength);

                if(take.getSecond() > skip.getSecond()){
                    uberList.put(pKey, take);
                    return take;
                }
                else{
                    uberList.put(pKey, skip);
                    return skip;
                }
            }
            catch(EmptyListE e) {
                return new Pair<>(new Empty<>(), 0);
            }
        }
    }

    // TODO. Bottom-up solution (buschedule) goes here
    // bup adv: no hashmap thus >>>> memo
    static Pair<List<Ride>, Integer> buschedule (List<Ride> rides, int maxLength){
        // TODO
        //    0 ..... maxLength +1
        //     0 1 2 3 4 5 6 7 8 9
        //   0 0 0 0 0 0 0 0 0 0 0
        // R 1 0 x
        // I 2 0 y
        // D 3 0 x
        // E 4 0 x

        Pair<List<Ride>, Integer>[][] table = (Pair<List<Ride>, Integer>[][]) new Pair[rides.length() + 1][maxLength + 1];
        Ride[] ridesArr = new Ride[rides.length()];

        try{
            // to access individual rides
            int ridelength = rides.length();
            for(int i = ridelength -1; i>= 0; i--){
                ridesArr[i] = rides.getFirst();
                rides = rides.getRest();
            }

            // putting the value in the table for the "Empty" solution
            // Base case
            for (int i = 0; i < maxLength + 1; i++) {
                //map.put(new Pair<>(i, maxLength), new Pair<>(new Empty(), 0));
                table[0][i] = new Pair<>(new Empty<>(), 0);
            }
            for (int j = 0; j < ridelength + 1; j++) {
                //map.put(new Pair<>(rides.length(), j), new Pair<>(new Empty(), 0));
                table[j][0] = new Pair<>(new Empty<>(), 0);
            }

            // this will calculate and then put it in the table
            // traverses from bottom right to upper left
            for (int col = 1; col <= maxLength ; col++) {
                for (int row = 1; row < ridelength + 1; row++) {

                    // skip one from the right -- can choose it however you want
                    // skip has to look to the left because it preserves the maxlength
                    // take: add cost from the recursion somewhere else
                    // take must be diagonal upper left to take away from maxlength

                    Pair<List<Ride>, Integer> skip = skip = table[row-1][col];
                    Pair<List<Ride>, Integer> take = new Pair<>(new Empty(), 0);
                    int takePrice = 0;
                    //if(row > 0) {

                    //}

                    // make sure it's <= col
                    if(col - ridesArr[row-1].getLength() >= 0) {
                        //if(row > 0){
                            take = table[row - 1][col - ridesArr[row-1].getLength()];
                            takePrice = take.getSecond() + ridesArr[row-1].getPrice();
                        //}
                    }
                    // the array value is NOT the maxLength, it is the price
                    //if(row > 0){
                        if(skip.getSecond() > takePrice){
                            table[row][col] = new Pair<>(skip.getFirst(), skip.getSecond());
                        }
                        else{
                            table[row][col] = new Pair<>(new Node(ridesArr[row-1], take.getFirst()), takePrice);
                        }
                    //}else{
                      //  table[row][col] = new Pair<>(skip.getFirst(), skip.getSecond());
                   // }
                }
            }
            // returns the last pairing
            return table[ridelength][maxLength];
        }
        catch(EmptyListE e){
            System.out.println("CAught");
            return new Pair<>(new Empty(), 0);
        }
    }
}
