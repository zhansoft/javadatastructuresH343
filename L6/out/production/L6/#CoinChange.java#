import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

class CoinChange {
    /**
     * Uses a greedy algorithm to return the lowest number of coins needed to make change.
     * @param coins The set of all coin values, sorted from highest to lowest.
     * @param target The target change amount.
     * @return The minimum coins needed to make change. Return Integer.MAX_VALUE if not possible.
     */
    static int greedyCoinChange(List<Integer> coins, int target) throws EmptyListE{
      // TODO
        int total = 0;
        try{
            if(target == 0){
                return 0;
            }
            else if(coins.getFirst() <= target){
                total = 1 + greedyCoinChange(coins, target - coins.getFirst());
            }
            else{
                total = 0 + greedyCoinChange(coins.getRest(), target);
            }
        }
        catch(EmptyListE e){
            return Integer.MAX_VALUE;
        }
        return total;
    }
i
    static Map<Pair<List<Integer>, Integer>, Integer> coinChangeMemo = new WeakHashMap<>();
    /**
     * Uses a greedy algorithm to return the lowest number of coins needed to make change. Map is found above.
     * @param coins The set of all coin values, sorted from highest to lowest.
     * @param target The target change amount.
     * @return The minimum coins needed to make change. Return Integer.MAX_VALUE if not possible.
     */
    static int memoCoinChange(List<Integer> coins, int target) throws EmptyListE {
        // TODO
        if (target == 0) {
            return 0;
        }
        return coinChangeMemo.computeIfAbsent(new Pair<>(coins, target), p -> {
            try {
                int a = memoCoinChange(coins.getRest(), target);
                int b = Integer.MAX_VALUE;
                if(coins.getFirst() <= target){
                    b = memoCoinChange(coins, target - coins.getFirst());
                }
                if(b != Integer.MAX_VALUE){
                    b += 1;
                }

                if (a < b) {
                    return a;
                } else {
                    return b;
                }
            } catch (EmptyListE e){
                return Integer.MAX_VALUE;
            }
        });
    }

    /**
     * Use bottom-up dynamic programming to return the lowest number of coins needed to make change.
     * @param coins The set of all coin values, sorted from highest to lowest.
     * @param target The target change amount.
     * @return The minimum coins needed to make change. Return Integer.MAX_VALUE if not possible.
     */
    static int bupCoinChange(List<Integer> coins, int target) {
      // TODO
        return 1;
    }
}
