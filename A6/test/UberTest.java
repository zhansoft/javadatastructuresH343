import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;
import java.time.Instant;

public class UberTest {

    @Test
    public void scheduleSmall() throws EmptyListE{

        int numRides = 7;
        int maxLen = 25;
        int maxPrice = 10;
        List<Ride> rides = new Empty<>();

        Random rand = new Random(1);
        for (int i = 0; i < numRides; i++) {
            Ride r = new Ride("r" + i, rand.nextInt(maxLen), rand.nextInt(maxPrice));
            rides = new Node<>(r, rides);
        }
        System.out.println(rides);
        System.out.println(Uber.buschedule(rides, 20));

        Pair<List<Ride>, Integer> s = Uber.buschedule(rides, 20);
        assertEquals(20, s.getSecond());
        var selectedRides = s.getFirst();
        assertEquals(3, selectedRides.length());
        assertEquals(8, selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(4, selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(8, selectedRides.getFirst().getPrice());

    }

    @Test
    public void scheduleBig() throws EmptyListE{
        int numRides = 200;
        int maxLen = 89;
        int maxPrice = 59;
        List<Ride> bigRides = new Empty<>();

        Random rand = new Random(1);
        for (int i=0; i<numRides; i++) {
            Ride r = new Ride("r"+i, rand.nextInt(maxLen), rand.nextInt(maxPrice));
            bigRides = new Node<>(r,bigRides);
        }

        Pair<List<Ride>,Integer> s = Uber.mschedule(bigRides,30);
        assertEquals(344,s.getSecond());
        var selectedRides = s.getFirst();
        assertEquals(8, selectedRides.length());
        assertEquals(53,selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(17,selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(33,selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(57,selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(41,selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(52,selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(58,selectedRides.getFirst().getPrice());
        selectedRides = selectedRides.getRest();
        assertEquals(33,selectedRides.getFirst().getPrice());
    }

    @Test
    public void correctschedule() throws EmptyListE{
        List<Ride> empty = new Empty<>();
        Pair<List<Ride>, Integer> a = Uber.schedule(empty, 32);
        Pair<List<Ride>, Integer> b = Uber.schedule(empty, 0);

        List<Ride> highprice = new Empty<>();
        List<Ride> shortlength = new Empty<>();

        Random rand = new Random(1);
        for (int i=0; i<10; i++) {
            Ride r = new Ride("r"+i, rand.nextInt(30), rand.nextInt(500));
            highprice = new Node<>(r,highprice);
        }
        for (int i=0; i<15; i++) {
            Ride x = new Ride("r"+i, rand.nextInt(5)+1, rand.nextInt(10));
            shortlength = new Node<>(x,shortlength);
        }

        Pair<List<Ride>, Integer> c = Uber.schedule(highprice, 70);
        Pair<List<Ride>, Integer> d = Uber.schedule(shortlength, 10);
        System.out.println(shortlength);

        // empty, price is high, length is short
        assertEquals(new Pair<>(new Empty(), 0), Uber.schedule(empty, 32));
        assertEquals(new Pair<>(new Empty(), 0), Uber.schedule(empty, 0));
        assertEquals(2138,c.getSecond());
        assertEquals(45,d.getSecond());

    }

    @Test
    public void correctmschedule() throws EmptyListE{
        // empty, price is high, length is short
        List<Ride> empty = new Empty<>();
        Pair<List<Ride>, Integer> a = Uber.mschedule(empty, 32);
        Pair<List<Ride>, Integer> b = Uber.mschedule(empty, 10);

        List<Ride> highprice = new Empty<>();
        List<Ride> shortlength = new Empty<>();

        Random rand = new Random(3);
        for (int i=0; i<10; i++) {
            Ride r = new Ride("r"+i, rand.nextInt(30), rand.nextInt(500));
            highprice = new Node<>(r,highprice);
        }
        for (int i=0; i<15; i++) {
            Ride x = new Ride("r"+i, rand.nextInt(5) + 1, rand.nextInt(10));
            shortlength = new Node<>(x,shortlength);
        }

        Pair<List<Ride>, Integer> c = Uber.mschedule(highprice, 70);
        Pair<List<Ride>, Integer> d = Uber.mschedule(shortlength, 10);
        System.out.println(shortlength);

        // empty, price is high, length is short
        assertEquals(new Pair<>(new Empty(), 0), Uber.mschedule(empty, 67));
        assertEquals(new Pair<>(new Empty(), 0), Uber.mschedule(empty, 0));
        assertEquals(1390,c.getSecond());
        assertEquals(31, d.getSecond());
    }

    @Test
    public void correctbuschedule() throws EmptyListE{
        List<Ride> empty = new Empty<>();
        Pair<List<Ride>, Integer> a = Uber.buschedule(empty, 32);
        Pair<List<Ride>, Integer> b = Uber.buschedule(empty, 10);

        List<Ride> highprice = new Empty<>();
        List<Ride> shortlength = new Empty<>();

        Random rand = new Random(4);
        for (int i=0; i<10; i++) {
            Ride r = new Ride("r"+i, rand.nextInt(30), rand.nextInt(500));
            highprice = new Node<>(r,highprice);
        }
        for (int i=0; i<15; i++) {
            Ride x = new Ride("r"+i, rand.nextInt(5) + 1, rand.nextInt(10));
            shortlength = new Node<>(x,shortlength);
        }

        Pair<List<Ride>, Integer> c = Uber.mschedule(highprice, 70);
        Pair<List<Ride>, Integer> d = Uber.mschedule(shortlength, 10);
        System.out.println(shortlength);

        // empty, price is high, length is short
        assertEquals(new Pair<>(new Empty(), 0), Uber.buschedule(empty, 67));
        assertEquals(new Pair<>(new Empty(), 0), Uber.buschedule(empty, 0));
        assertEquals(1390,c.getSecond());
        assertEquals(31, d.getSecond());
    }

    @Test
    public void efficiencyschedule() throws EmptyListE{
        Instant start, end;
        int sNumRides = 100;
        int smNumRides = 500;
        int mNumRides = 1000;
        int sMaxLen = 25;
        int sMaxPrice = 10;
        int mMaxLen = 60;
        int mMaxPrice = 20;

        List<Ride> rides100 = new Empty<>();
        List<Ride> rides500 = new Empty<>();
        List<Ride> rides1000 = new Empty<>();

        Random rand = new Random(1);

        for (int i=0; i<sNumRides; i++) {
            Ride a = new Ride("r"+i, rand.nextInt(sMaxLen), rand.nextInt(sMaxPrice));
            rides100 = new Node<>(a,rides100);
        }
        for (int i=0; i<smNumRides; i++) {
            Ride b = new Ride("r"+i, rand.nextInt(mMaxLen), rand.nextInt(mMaxPrice));
            rides500 = new Node<>(b,rides500);
        }
        for (int i=0; i<mNumRides; i++) {
            Ride c = new Ride("r"+i, rand.nextInt(mMaxLen), rand.nextInt(mMaxPrice));
            rides1000 = new Node<>(c,rides1000);
        }


        start = Instant.now();
        Pair<List<Ride>,Integer> s1 = Uber.schedule(rides100,50);
        end = Instant.now();
        System.out.println("Time for 100 schedule: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        Pair<List<Ride>,Integer> s2 = Uber.schedule(rides500,100);
        end = Instant.now();
        System.out.println("Time for 500 schedule: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        Pair<List<Ride>,Integer> s3 = Uber.schedule(rides1000,500);
        end = Instant.now();
        System.out.println("Time for 1000 schedule: " + Duration.between(start, end).toMillis());



        /*
        // for bu
        Pair<List<Ride>,Integer> bus1 = Uber.buschedule(rides100,50);
        Pair<List<Ride>,Integer> bus2 = Uber.buschedule(rides1000,500);
        Pair<List<Ride>,Integer> bus3 = Uber.buschedule(rides10000,1000);
         */
    }

    @Test
    public void efficiencymschedule() throws EmptyListE{
        Instant start, end;

        int sNumRides = 100;
        int smNumRides = 500;
        int mNumRides = 1000;
        int lNumRides = 10000;
        int sMaxLen = 25;
        int sMaxPrice = 10;
        int mMaxLen = 60;
        int mMaxPrice = 20;
        int lMaxLen = 70;
        int lMaxPrice = 50;

        List<Ride> rides100 = new Empty<>();
        List<Ride> rides500 = new Empty<>();
        List<Ride> rides1000 = new Empty<>();
        List<Ride> rides10000 = new Empty<>();

        Random rand = new Random(1);

        for (int i=0; i<sNumRides; i++) {
            Ride a = new Ride("r"+i, rand.nextInt(sMaxLen), rand.nextInt(sMaxPrice));
            rides100 = new Node<>(a,rides100);
        }
        for (int i=0; i<smNumRides; i++) {
            Ride b = new Ride("r"+i, rand.nextInt(mMaxLen), rand.nextInt(mMaxPrice));
            rides500 = new Node<>(b,rides500);
        }
        for (int i=0; i<mNumRides; i++) {
            Ride c = new Ride("r"+i, rand.nextInt(mMaxLen), rand.nextInt(mMaxPrice));
            rides1000 = new Node<>(c,rides1000);
        }

        start = Instant.now();
        Pair<List<Ride>,Integer> ms1 = Uber.mschedule(rides100,50);
        end = Instant.now();
        System.out.println("Time for 100 mschedule: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        Pair<List<Ride>,Integer> ms2 = Uber.mschedule(rides500,100);
        end = Instant.now();
        System.out.println("Time for 500 mschedule: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        Pair<List<Ride>,Integer> ms3 = Uber.mschedule(rides1000,500);
        end = Instant.now();
        System.out.println("Time for 1000 mschedule: " + Duration.between(start, end).toMillis());

    }

    @Test
    public void efficiencybuschedule() throws EmptyListE{
        Instant start, end;

        int sNumRides = 100;
        int smNumRides = 500;
        int mNumRides = 1000;
        int lNumRides = 10000;
        int sMaxLen = 25;
        int sMaxPrice = 10;
        int mMaxLen = 60;
        int mMaxPrice = 20;
        int lMaxLen = 70;
        int lMaxPrice = 50;

        List<Ride> rides100 = new Empty<>();
        List<Ride> rides500 = new Empty<>();
        List<Ride> rides1000 = new Empty<>();
        List<Ride> rides10000 = new Empty<>();

        Random rand = new Random(1);

        for (int i=0; i<sNumRides; i++) {
            Ride a = new Ride("r"+i, rand.nextInt(sMaxLen), rand.nextInt(sMaxPrice));
            rides100 = new Node<>(a,rides100);
        }
        for (int i=0; i<mNumRides; i++) {
            Ride c = new Ride("r"+i, rand.nextInt(mMaxLen), rand.nextInt(mMaxPrice));
            rides1000 = new Node<>(c,rides1000);
        }
        for (int i=0; i<lNumRides; i++) {
            Ride b = new Ride("r"+i, rand.nextInt(lMaxLen), rand.nextInt(lMaxPrice));
            rides10000 = new Node<>(b,rides10000);
        }

        start = Instant.now();
        Pair<List<Ride>,Integer> ms1 = Uber.buschedule(rides100,50);
        end = Instant.now();
        System.out.println("Time for 100 buschedule: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        Pair<List<Ride>,Integer> ms2 = Uber.buschedule(rides1000,200);
        end = Instant.now();
        System.out.println("Time for 1000 buschedule: " + Duration.between(start, end).toMillis());
        start = Instant.now();
        Pair<List<Ride>,Integer> ms3 = Uber.buschedule(rides10000,500);
        end = Instant.now();
        System.out.println("Time for 10000 buschedule: " + Duration.between(start, end).toMillis());

    }


}