public class Dice {
    static int numWays(int n){
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return numWays(n-1) + numWays(n-2);
    }


    public static void main(String[] args){
        System.out.println(numWays(3));
        System.out.println(numWays(4));
        System.out.println(numWays(5));
        System.out.println(numWays(6));
    }
}
