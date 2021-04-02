import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Dup {
    // Use built-in implementation of Set (which removes duplicates) O(1) < O() < O(n)
    static List<Integer> noDups1 (List<Integer> ints) {
        HashSet<Integer> hs = new HashSet<>(ints);
        return new LinkedList<>(hs);
    }

    // Process elements one-by-one; for each element
    // check if we have seen it before
    static List<Integer> noDups2 (List<Integer> ints) { // O(n)
        List<Integer> res = new LinkedList<>();
        for (int i : ints) {
            if (! (res.contains(i))) res.add(i);
        }
        return res;
    }

    // Sort the list; then make one pass over the list
    // checking each element against the one before it
    // Warning: the method sorts the incoming list in place;
    // the caller will see a new list after the method returns
    static List<Integer> noDups3 (List<Integer> ints) { // O(n^2)ish
        ints.sort(Integer::compareTo);
        int previous = ints.get(0);
        List<Integer> res = new LinkedList<>();
        res.add(previous);
        for (int i=1; i<ints.size(); i++) {
            int current = ints.get(i);
            if (current != previous) {
                res.add(current);
                previous = current;
            }
        }
        return res;
    }

    // convert to a stream and use built-in method "distinct"
    static List<Integer> noDups4 (List<Integer> ints) {
        Stream<Integer> res = ints.stream().distinct();
        return res.collect(Collectors.toList());
    }

    // Similar to solution 2 but using hash table instead
    // of linked list
    static List<Integer> noDups5 (List<Integer> ints) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i : ints) {
            // contains check is not needed but keep
            // it for comparison with solution 2
            if (! (hs.contains(i))) hs.add(i);
        }
        return new LinkedList<>(hs);
    }
}
