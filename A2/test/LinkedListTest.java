import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;
import java.util.*;

// UPdate: So Professor Sabry was like write the void methods and some still glitch. :(
// Just comment out some out for the stackoverflows :'(
// had to add -Xss4m to some methods & the class???


import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void testStack() throws EmptyListE {
        StackI<Integer> s = new EmptyList<Integer>().push(1).push(2).push(3);
        assertEquals(3, s.top());
        s = s.pop();
        assertEquals(2, s.top());
        s = s.pop();
        assertEquals(1, s.top());
        s = s.pop();
    }

    @Test
    void testQueue() throws EmptyListE {
        QueueI<Integer> q = new EmptyList<Integer>().enqueue(1).enqueue(2).enqueue(3);
        assertEquals(1, q.front());
        q = q.dequeue();
        assertEquals(2, q.front());
        q = q.dequeue();
        assertEquals(3, q.front());
        q = q.dequeue();
    }

    @Test
    void testDequeue() throws EmptyListE {
        DequeueI<Integer> q = new EmptyList<Integer>().
                enqueueBack(1).enqueueBack(2).enqueueBack(3);
        assertEquals(1,q.front());
        assertEquals(2,q.dequeueFront().front());
        assertEquals(3,q.dequeueFront().dequeueFront().front());

        q = q.enqueueFront(4).enqueueFront(5);
        assertEquals(
                3, q.back());
        assertEquals(2, q.dequeueBack().back());
        q = q.dequeueFront();
        q = q.dequeueBack();
        assertEquals(2, q.back());
        assertEquals(4, q.front());
    }

    @Test
    void correctStackLL() throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>();
        LinkedList<Integer> b = new EmptyList<>();
        LinkedList<Integer> c = new EmptyList<>();

        // push()
        assertEquals(new NonEmptyList<Integer>(1, new NonEmptyList<>(2, new NonEmptyList<>(3, new NonEmptyList<>(4, new EmptyList<>())))), a.push(4).push(3).push(2).push(1));
        a = a.push(4).push(3).push(2).push(1);
        assertEquals(new NonEmptyList<Integer>(5, new NonEmptyList<>(6, new NonEmptyList<>(7, new EmptyList<>()))), b.push(7).push(6).push(5));
        b = b.push(7).push(6).push(5);
        assertEquals(new NonEmptyList<Integer>(9, new NonEmptyList<>(10, new EmptyList<>())), c.push(10).push(9));
        c = c.push(10).push(9);

        // pop()
        assertEquals(new EmptyList<Integer>(), a.pop().pop().pop().pop());
        a = a.pop().pop().pop().pop();
        assertEquals(new NonEmptyList<Integer>(7, new EmptyList<>()), b.pop().pop());
        b = b.pop().pop();
        assertEquals(new NonEmptyList<Integer>(10, new EmptyList<>()), c.pop());
        c = c.pop();

        // top()
        assertThrows(EmptyListE.class, () ->{
            LinkedList<Integer> a1 = new EmptyList<>();
            a1.top();
        });
        assertEquals(7, b.top());
        assertEquals(10, c.top());
    }

    @Test
    void correctQueueLL() throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>();
        LinkedList<Integer> b = new EmptyList<>();
        LinkedList<Integer> c = new NonEmptyList<>(3, new EmptyList<>());

        // enqueue()
        assertEquals(new NonEmptyList<Integer>(1, new EmptyList<>()), a.enqueue(1));
        a = a.enqueue(1);
        assertEquals(new NonEmptyList<Integer>(3, new NonEmptyList<>(2, new EmptyList<>())), b.enqueue(3).enqueue(2));
        b = b.enqueue(3).enqueue(2);
        assertEquals(new NonEmptyList<Integer>(3, new NonEmptyList<>(8, new NonEmptyList<>(6, new NonEmptyList<>(4, new EmptyList<>())))), c.enqueue(8).enqueue(6).enqueue(4));
        c = c.enqueue(8).enqueue(6).enqueue(4);

        // dequeue()
        assertEquals(new EmptyList<Integer>(), a.dequeue());
        a = a.dequeue();
        assertEquals(new NonEmptyList<Integer>(2, new EmptyList<>()), b.dequeue());
        b = b.dequeue();
        assertEquals(new NonEmptyList<>(6, new NonEmptyList<>(4, new EmptyList<>())), c.dequeue().dequeue());
        c = c.dequeue().dequeue();


        // front()
        assertThrows(EmptyListE.class, () ->{
            LinkedList<Integer> a1 = new EmptyList<>();
            a1.front();
        });
        assertEquals(2, b.front());
        assertEquals(6, c.front());
    }

    @Test
    void correctDeQueueLL() throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>();
        LinkedList<Integer> b = new EmptyList<>();
        LinkedList<Integer> c = new NonEmptyList<>(10, new EmptyList<>());

        // enqueueback() same as enqueue()
        assertEquals(new NonEmptyList<Integer>(1, new NonEmptyList<>(2, new NonEmptyList<>(3, new NonEmptyList<>(5, new EmptyList<>())))), a.enqueueBack(1).enqueueBack(2).enqueueBack(3).enqueueBack(5));
        a = a.enqueueBack(1).enqueueBack(2).enqueueBack(3).enqueueBack(5);
        assertEquals(new NonEmptyList<Integer>(8, new NonEmptyList<>(3, new NonEmptyList<>(1, new EmptyList<>()))), b.enqueueBack(8).enqueueBack(3).enqueueBack(1));
        b = b.enqueueBack(8).enqueueBack(3).enqueueBack(1);
        assertEquals(new NonEmptyList<Integer>(10, new NonEmptyList<>(4, new EmptyList<>())), c.enqueueBack(4));
        c = c.enqueueBack(4);

        // dequeuefront() same as dequeue
        assertEquals(new NonEmptyList<Integer>(2, new NonEmptyList<>(3, new NonEmptyList<>(5, new EmptyList<>()))), a.dequeueFront());
        a = a.dequeueFront();
        assertEquals(new NonEmptyList<Integer>(1, new EmptyList<>()), b.dequeueFront().dequeueFront());
        b = b.dequeueFront().dequeueFront();
        assertEquals(new EmptyList<Integer>(), c.dequeue().dequeue());
        c = c.dequeueFront().dequeueFront();

        // back()
        assertEquals(5, a.back());
        assertEquals(1, b.back());
        assertThrows(EmptyListE.class, () ->{
            LinkedList<Integer> c1 = new EmptyList<>();
            c1.back();
        });

        // enqueuefront()
        assertEquals(new NonEmptyList<Integer>(7, new NonEmptyList<>(2, new NonEmptyList<>(3, new NonEmptyList<>(5, new EmptyList<>())))), a.enqueueFront(7));
        a = a.enqueueFront(7);
        assertEquals(new NonEmptyList<Integer>(2, new NonEmptyList<>(9, new NonEmptyList<>(1, new EmptyList<>()))), b.enqueueFront(9).enqueueFront(2));
        b = b.enqueueFront(9).enqueueFront(2);
        assertEquals(new NonEmptyList<Integer>(3, new NonEmptyList<>(1, new EmptyList<>())), c.enqueueFront(1).enqueueFront(3));
        c = c.enqueueFront(1).enqueueFront(3);

        // dequeueback()
        assertEquals(new EmptyList<>(), a.dequeueBack().dequeueBack().dequeueBack().dequeueBack());
        a = a.dequeueBack().dequeueBack().dequeueBack().dequeueBack();
        assertEquals(new NonEmptyList<Integer>(2, new NonEmptyList<>(9, new EmptyList<>())), b.dequeueBack());
        b = b.dequeueBack();
        assertEquals(new NonEmptyList<Integer>(3, new EmptyList<>()), c.dequeueBack());
        c = c.dequeueBack();
    }



    @Test
    void correctEmptyList() throws EmptyListE{
        LinkedList<Integer> a1 = new EmptyList<>();
        LinkedList<Integer> extraempty = new EmptyList<>();
        LinkedList<Integer> b1 = new NonEmptyList<>(2, extraempty);

        // length()
        assertEquals(0, a1.length());

        // getRest()
        assertThrows(EmptyListE.class, () -> {
            LinkedList<Integer> empty = new EmptyList<>();
            empty.getRest();
        });

        // insertAt()
        assertEquals(b1, a1.insertAt(0, 2));
        a1 = new EmptyList<>();
        assertThrows(EmptyListE.class, () ->{
            LinkedList<Integer> empty = new EmptyList<>();
            empty.insertAt(3, 2);
        });

        // removeAt()
        assertThrows(EmptyListE.class, () -> {
            LinkedList<Integer> empty = new EmptyList<>();
            empty.removeAt(3);
        });

        // getAt()
        assertThrows(EmptyListE.class, () -> {
           LinkedList<Integer> empty = new EmptyList<>();
           empty.getAt(3);
        });
    }

    void stackLLPush(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;

        long t1 = 0, st1, et1;
        for(int i = 0; i< size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.push(0);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to push() for " + size + " " + t1);
    }
    void stackLLPop(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;

        long t1 = 0, st1, et1;
        for(int i = 0; i< size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.pop();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to pop() for " + size + " " + t1);
    }
    void stackLLTop(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;

        long t1 = 0, st1, et1;
        for(int i = 0; i< size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.top();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to top() for " + size + " " + t1);
    }
    void queueEnqueue(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.enqueue(0);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to enqueue() for " + size + " " + t1);
    }
    void queueDequeue(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.dequeue();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to dequeue() for " + size + " " + t1);
    }
    void queueFront(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.front();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to front() for " + size + " " + t1);
    }

    void dqEnqueueBack(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.enqueueBack(0);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to enqueueBack() for " + size + " " + t1);
    }
    void dqEnqueueFront(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.enqueueFront(0);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to enqueueFront() for " + size + " " + t1);
    }
    void dqDequeueFront(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.dequeueFront();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to dequeueFront() for " + size + " " + t1);
    }
    void dqDequeueBack(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.dequeueBack();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to dequeueBack() for " + size + " " + t1);
    }
    void dqBack(int size) throws EmptyListE{
        LinkedList<Integer> a = new EmptyList<>(); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(10000);
            a = a.push(element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.back();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to back() for " + size + " " + t1);
    }

    @Test
    void stackLLTestPush() throws EmptyListE{
        stackLLPush(10000);
        stackLLPush(20000);
        stackLLPush(40000);
    }

    @Test
    void stackLLTestPop() throws EmptyListE{
        stackLLPop(10000);
        stackLLPop(20000);
        stackLLPop(40000);
    }

    @Test
    void stackLLTestTop() throws EmptyListE{
        stackLLTop(10000);
        stackLLTop(20000);
        stackLLTop(40000);
    }

    @Test
    void queueEnqueueTest() throws EmptyListE{
        queueEnqueue(10000);
        queueEnqueue(20000);
        queueEnqueue(40000); // glitchy
        queueEnqueue(40000);
    }

    @Test
    void queueDequeueTest() throws EmptyListE{
        queueDequeue(10000);
        queueDequeue(20000);
        queueDequeue(40000);
    }

    @Test
    void queueFrontTest() throws EmptyListE{
        queueFront(10000);
        queueFront(20000);
        queueFront(40000);
    }
    @Test
    void dqDequeueFrontTest() throws EmptyListE{
        dqDequeueFront(10000);
        dqDequeueFront(20000);
        dqDequeueFront(40000);
    }
    @Test
    void dqDequeueBackTest() throws EmptyListE{
        dqDequeueBack(10000);
        dqDequeueBack(20000);
        dqDequeueBack(40000);
        dqDequeueBack(40000); //glitchy
    }
    @Test
    void dqEnqueueFrontTest() throws EmptyListE{
        dqEnqueueFront(10000);
        dqEnqueueFront(20000);
        dqEnqueueFront(40000);
    }
    @Test
    void dqEnqueueBackTest() throws EmptyListE{
        dqEnqueueBack(10000);
        dqEnqueueBack(20000);
        dqEnqueueBack(40000);
        dqEnqueueBack(40000); // glitchy
    }
    @Test
    void dqBackTest() throws EmptyListE{
        dqBack(10000);
        dqBack(20000);
        dqBack(40000);
    }

    @Test
    void correctNonEmptyList() throws EmptyListE{
        LinkedList<Integer> empty = new EmptyList<>();
        LinkedList<Integer> b2 = new NonEmptyList<>(2, empty);

        // getRest()
        assertEquals(empty,b2.getRest());
        b2 = new NonEmptyList<>(2, empty);
        assertEquals(empty,b2.getRest());
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, new NonEmptyList<Integer>(10, empty)))))))));
        assertEquals(new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, new NonEmptyList<Integer>(10, empty)))))))), b2.getRest());


        // length()
        b2 = new NonEmptyList<>(2, empty);
        assertEquals(1, b2.length());
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, new NonEmptyList<Integer>(10, empty)))))))));
        assertEquals(9, b2.length());

        // insertAt()
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, empty));
        assertEquals(new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, empty))),b2.insertAt(2, 4));
        //b = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, empty));
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, new NonEmptyList<Integer>(10, empty)))))))));
        assertEquals(new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, new NonEmptyList<Integer>(10, new NonEmptyList<Integer>(11, empty)))))))))), b2.insertAt(9, 11));
        assertThrows(EmptyListE.class, () -> {
            LinkedList<Integer> ex1 = new NonEmptyList<>(2, new EmptyList<Integer>());
            ex1.insertAt(4, 1);
        });

        // removeAt()
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<>(4, empty))); // 2, (3, (4, empty))
        assertEquals(new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(4, empty)),b2.removeAt(1));
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, new NonEmptyList<Integer>(10, empty)))))))));
        assertEquals(new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, empty)))))))), b2.removeAt(8));
        assertThrows(EmptyListE.class, () -> {
            LinkedList<Integer> ex2 = new NonEmptyList<>(2, new EmptyList<Integer>());
            ex2.removeAt(4);
        });

        // getAt()
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<>(4, empty))); // 2, (3, (4, empty))
        assertEquals(3, b2.getAt(1));
        b2 = new NonEmptyList<Integer>(2, new NonEmptyList<Integer>(3, new NonEmptyList<Integer>(4, new NonEmptyList<Integer>(5, new NonEmptyList<Integer>(6, new NonEmptyList<Integer>(7, new NonEmptyList<Integer>(8, new NonEmptyList<Integer>(9, new NonEmptyList<Integer>(10, empty)))))))));
        assertEquals(10, b2.getAt(8));
        assertThrows(EmptyListE.class, () -> {
            LinkedList<Integer> ex3 = new NonEmptyList<>(2, new EmptyList<Integer>());
            ex3.getAt(4);
        });

    }

    void efficiencyNonEmptyListLength(int size) throws EmptyListE{
        LinkedList<Integer> a = new NonEmptyList<>(4, new EmptyList<>()); // 10000
        Random r = new Random();
        int element;

        long t1 = 0, st1, et1;
        for(int i = 0; i< size-1; i++) {
            element = r.nextInt(10000);
            a = a.insertAt(0, element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.length();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to length() for " + size + " " + t1);
    }

    void efficiencyNonEmptyListGetRest(int size) throws EmptyListE{
        LinkedList<Integer> a = new NonEmptyList<>(2, new EmptyList<>()); // 10000
        Random r = new Random();
        int element;
        long t1 = 0,st1,et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(1000);
            a = a.insertAt(0, element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.getRest();
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to getRest() for " + size + " " + t1);
    }

    void efficiencyNonEmptyListInsertAt(int size) throws EmptyListE{
        LinkedList<Integer> a = new NonEmptyList<>(2, new EmptyList<>());
        Random r = new Random();
        int element;
        long t1 = 0,st1,et1;
        for(int i = 0; i < size; i++){
            element = r.nextInt(1000);
            a = a.insertAt(0, element);
        }

        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.insertAt(size-1, 0);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to insertAt() for " + size + " " + t1);
    }

    void efficiencyNonEmptyListRemoveAt(int size) throws EmptyListE{
        LinkedList<Integer> a = new NonEmptyList<>(4, new EmptyList<>()); // 20000
        Random r = new Random();
        int element;
        long t1 = 0, st1, et1;
        for(int i = 0; i< size; i++){
            element = r.nextInt(10000);
            a = a.insertAt(0, element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.removeAt(size-1);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to removeAt() for " + size + " " + t1);
    }

    void efficiencyNonEmptyListGetAt(int size) throws EmptyListE{
        LinkedList<Integer> a = new NonEmptyList<>(4, new EmptyList<>()); // 20000
        Random r = new Random();
        int element;

        long t1 = 0, st1, et1;
        for(int i = 0; i< size; i++){
            element = r.nextInt(10000);
            a = a.insertAt(0, element);
        }
        for(int i = 0; i < 10; i++){
            st1 = System.currentTimeMillis();
            a.getAt(size-1);
            et1 = System.currentTimeMillis();
            t1 += et1 - st1;
        }
        System.out.println("Time to removeAt() for " + size + " " + t1);
    }

    @Test
    void efficiencyNonEmptyListTestGetRest() throws EmptyListE {
        efficiencyNonEmptyListGetRest(10000);
        efficiencyNonEmptyListGetRest(20000);
        efficiencyNonEmptyListGetRest(40000);
    }

    @Test
    void efficiencyNonEmptyListTestLength() throws EmptyListE{
        efficiencyNonEmptyListLength(10000);
        efficiencyNonEmptyListLength(20000);
        efficiencyNonEmptyListLength(40000);
    }

    @Test
    void efficiencyNonEmptyListTestInsertAt() throws EmptyListE{
        efficiencyNonEmptyListInsertAt(10000);
        efficiencyNonEmptyListInsertAt(20000);
        efficiencyNonEmptyListInsertAt(40000);

    }

    @Test
    void efficiencyNonEmptyListTestRemoveAt() throws EmptyListE{
        efficiencyNonEmptyListRemoveAt(10000);
        efficiencyNonEmptyListRemoveAt(20000);
        efficiencyNonEmptyListRemoveAt(40000);
        efficiencyNonEmptyListRemoveAt(40000); // one of these will be crazy idk why
    }

    @Test
    void efficiencyNonEmptyListTestGetAt() throws EmptyListE{
        efficiencyNonEmptyListGetAt(10000);
        efficiencyNonEmptyListGetAt(20000);
        efficiencyNonEmptyListGetAt(40000);
    }


}