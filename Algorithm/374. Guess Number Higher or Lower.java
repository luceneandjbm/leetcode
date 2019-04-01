/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {//my指的是他们给的数字而不是我们猜的数字
        int lo = 1; 
        int hi = n;
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if(guess(mid)==1) lo = mid + 1;
            else if(guess(mid)==-1) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
}
