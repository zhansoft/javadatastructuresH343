import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.lang.Math;

class NoSuchElementE extends Exception {}

public abstract class DequeueArray<E> {
    protected Optional<E>[] elements;
    protected int capacity, front, back, size;
    //
    // Invariants to maintain:
    //
    // * if dequeue is not full, front points to an empty location
    // * if dequeue is not full, back points to an empty location
    // * data stored in locations:
    //   front+1, front+2, ... back-2, back-1 (all mod capacity)
    // * after adding to front, decrease 'front' by 1
    // * after adding to back, increase 'back' by 1
    // * removing does the opposite
    //
    //  +-------------------------------------+
    //  | 4  5  6  _  _  _  _  _  _  1  2  3  |
    //  +-------------------------------------+
    //            /\             /\          /\
    //           back          front       capacity
    //

    @SuppressWarnings("unchecked")
    DequeueArray(int initialCapacity) {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity);
        Arrays.fill(elements, Optional.empty());
        capacity = initialCapacity;
        front = capacity - 1;
        back = 0;
        size = 0;
    }

    /**
     *  Creates a new empty array of elements of the given size
     */
    public void clear(int capacity) {
        // TODO
//        this.elements = (Optional<E>[]) Array.newInstance(Optional.class, capacity);
        Arrays.fill(elements, Optional.empty());
        this.capacity = capacity;
        this.front = capacity - 1;
        this.back = 0;
        this.size = 0;
    }

    public int size () { return size; }

    /**
     *  Adds an elements to the front of the dequeue
     */

    public void addFirst(E elem) {
        // TODO
        if(this.size == this.capacity) {
            this.grow();
        }

        if(this.front != 0){
            this.elements[this.front] = Optional.of(elem);
            this.size += 1;
            this.front -= 1;
        }
        else{
            this.elements[this.front] = Optional.of(elem);
            this.front = this.capacity - 1;
            this.size += 1;
        }

    }

    /**
     *  Adds an elements to the back of the dequeue
     */
    public void addLast(E elem) {
        // TODO
        if(this.size == this.capacity){
            this.grow();
        }

        if(this.back != this.capacity - 1){
            this.elements[this.back] = Optional.of(elem);
            this.size += 1;
            this.back += 1;
        }
        else if(this.back == this.capacity - 1) {
            this.elements[this.back] = Optional.of(elem);
            this.back = 0;
            this.size += 1;
        }


    }

    /**
     *  Returns the element at the front (if there is one)
     */
    public E getFirst() throws NoSuchElementE {
        // TODO
        // return null;
        return this.elements[this.front].orElseThrow(NoSuchElementE::new);
    }

    /**
     *  Returns the element at the back of the dequeue (if there is one)
     */
    public E getLast() throws NoSuchElementE {
        // TODO
        // return null;
        return this.elements[this.back].orElseThrow(NoSuchElementE::new);
    }

    /**
     *  Removes and returns the element at the front of the dequeue (if there is one)
     */
    public E removeFirst() throws NoSuchElementE {
        // TODO
        // return null;
        if(this.front == this.capacity - 1){
            this.front = 0;
            E first = getFirst();
            elements[this.front] = Optional.empty();
            this.size -= 1;
            return first;
        }
        else {
            this.front += 1;
            E first = getFirst();
            elements[this.front] = Optional.empty();
            this.size -= 1;
            return first;
        }
    }

    /**
     * Removes and returns the element at the back of the dequeue (if there is one)
     */
    public E removeLast() throws NoSuchElementE {
        // TODO
        //return null;
        if(this.back != 0){
            this.back -= 1;
            E last = getLast();
            elements[this.back] = Optional.empty();
            this.size -= 1;
            return last;
        }else{
            this.back = this.capacity - 1;
            E last = getLast();
            elements[this.back] = Optional.empty();
            this.size -= 1;
            return last;
        }

    }

    /**
     * The following method is implemented in subclasses: it determines the policy for
     * growing the array when full
     */
    abstract void grow ();

    // the following methods are for debugging and testing

    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other){
        if(other instanceof DequeueArray){
           DequeueArray<?> otherDQ = (DequeueArray<?>) other;
           if( otherDQ.getCapacity() == this.getCapacity() && otherDQ.size == this.size){
               for(int i = 0; i < otherDQ.size; i++){
                   if (!elements[i].equals(otherDQ.elements[i])) {
                       return false;
                   }
               }
               return true;
           }
           else{
               return false;
           }
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        String toString = "";
        for (int i=0; i< this.getCapacity(); i++) {
            if (this.elements[i].equals(Optional.empty())){
                toString += "* ";
            } else {
                toString += this.elements[i].get() + " ";
            }
        }
        return toString;
    }

}

// ---------------------------------------------------------

class DequeueArrayDouble<E> extends DequeueArray<E> {

    DequeueArrayDouble (int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Grows the array by increasing the capacity by a factor of 2
     * (i.e., by doubling the capacity)
     */
    @SuppressWarnings("unchecked")
    void grow() {
        // TODO
        Optional<E>[] temp = this.getElements();
        Optional<E>[] updated = (Optional<E>[]) Array.newInstance(Optional.class, this.getCapacity() * 2);
        Arrays.fill(updated, Optional.empty());
        int newBack = 0;
        while(!temp[Math.floorMod(this.front + 1, this.capacity)].isEmpty()){
            try{
                E item = this.removeFirst();
                int tempBack = Math.floorMod(newBack + 1, this.capacity * 2);
                updated[newBack] = Optional.of(item);
                newBack = tempBack;
                this.size += 1;
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        this.elements = updated;
        this.capacity *= 2;
        this.front = this.capacity - 1;
        this.back = newBack;
    }
}

// ---------------------------------------------------------

class DequeueArrayOneAndHalf<E> extends DequeueArray<E> {

    DequeueArrayOneAndHalf (int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Grows the array by increasing the capacity by a factor of 1.5
     * More precisely Math.round(1.5f * capacity)
     */
    @SuppressWarnings("unchecked")
    void grow() {
        // TODO
        int max = Math.round(1.5f * this.getCapacity());
        Optional<E>[] temp = this.getElements();
        Optional<E>[] updated = (Optional<E>[]) Array.newInstance(Optional.class, max);
        Arrays.fill(updated, Optional.empty());
        int newBack = 0;
        while(!temp[Math.floorMod(this.front + 1, this.capacity)].isEmpty()){
            try{
                E item = this.removeFirst();
                int tempBack = Math.floorMod(newBack + 1, max);
                updated[newBack] = Optional.of(item);
                newBack = tempBack;
                this.size += 1;
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        this.elements = updated;
        this.capacity = max;
        this.front = this.capacity - 1;
        this.back = newBack;
    }
}

// ---------------------------------------------------------

class DequeueArrayPlusOne<E> extends DequeueArray<E> {

    DequeueArrayPlusOne (int initialCapacity) {
        super(initialCapacity);
    }

    /**
     *  Grows the array by increasing the capacity by 1
     */
    @SuppressWarnings("unchecked")
    void grow() {
        // TODO
        int max = this.getCapacity() + 1;
        Optional<E>[] temp = this.getElements();
        Optional<E>[] updated = (Optional<E>[]) Array.newInstance(Optional.class, max);
        Arrays.fill(updated, Optional.empty());
        int newBack = 0;
        while(!temp[Math.floorMod(this.front + 1, this.capacity)].isEmpty()){
            try{
                E item = this.removeFirst();
                int tempBack = Math.floorMod(newBack + 1, max);
                updated[newBack] = Optional.of(item);
                newBack = tempBack;
                this.size += 1;
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        this.elements = updated;
        this.capacity = max;
        this.front = this.capacity - 1;
        this.back = newBack;
    }
}
