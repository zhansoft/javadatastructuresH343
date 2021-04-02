
class EmptyQueueE extends Exception {}

public class Queue {
    private Stack s1, s2;
    // s1 is back of queue
    // s2 is front of queue
    // insert to s1
    // remove from s2

    Queue () {
        s1 = new EmptyS();
        s2 = new EmptyS();
    }

    void enqueue (int i) {
        // implement enqueue here
        this.s1 = s1.push(i);
    }

    int dequeue () throws EmptyQueueE {
        // implement dequeue here
        // hint: use a try catch to throw an exception
        try{
            int top = s2.top();
            this.s2 = s2.pop();
            return top;
        }
        catch(EmptyStackE e){
            copyStacks(); //average: amortize
            try{
                int top = s2.top();
                this.s2 = s2.pop();
                return top;
            }
            catch (EmptyStackE f){
                throw new EmptyQueueE();
            }
        }
    }

    void copyStacks () {
        try {
            int i = s1.top();
            s1 = s1.pop();
            s2 = s2.push(i);
            copyStacks();
        }
        catch (EmptyStackE e) {
            return;
        }
    }

    public String toString () {
        return s1.toString() + " | " + s2.toString();
    }


}
