/**
 * NCK.java
 * COSC326 Etude 13
 * N Choose K
 *
 * @author Erina Jeffery
 * @author Rhianne Price
 * May 2017
 **/
public class NCK {
  /**
   * Main method prints the calculated value of n choose k for a given n and k.
   * @param args first argument n, second k.
   */
  public static void main(String args[]) {
    System.out.println(choose(Long.parseLong(args[0]), Long.parseLong(args[1])));
  }

  /**
   * Our implementation for calculating nCk. This implementation uses the
   * recursive definition: nC(k+1) = (nCk * (n - k)) / (k + 1), and starts with
   * n == k => nCk == 1 and works its way up. We avoid overflow in the
   * nCk * (n - k) step by dividing first and then adding back
   * ((nCk % k + 1) (n - k))/(k + 1).
   * @param n the size of the set to choose from
   * @param k the size of the subset to choose.
   */
  private static long choose(long n, long k) {
    if(k < 0 || k > n) return 0;
    k = Math.min(k, n - k); // nCk == nC(n-k)
    long nCk = 1;
    for(int i = 1; i <= k; i++, n--) {
      nCk = nCk / i * n + nCk % i * n / i;
    }
    return nCk;
  }
}
