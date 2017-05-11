import java.math.BigInteger;
import java.util.*;

public class NChooseK {
  public static void main(String[] args) {
    long n = 66;
    long k = 66;
    ArrayList<long[]> wrong = new ArrayList<long[]>();

    boolean pass = true;
    for(int i = (int) n; i > 0; i--) {
      for(int j = i - 1; j > 0; j--){
        if(choose(i, j) != binomial(i, j).longValue()) {
          pass = false;
          long[] a = new long[2];
          a[0] = choose(i, j);
          a[1] = binomial(i, j).longValue();
          wrong.add(a);
        }
      }
    }
    System.out.println(pass);
    for(long[] set : wrong) {
      System.out.println("(" + set[0] + ", " + set[1] + ")");
    }
  }

  public static long choose(long n, long k) {
    if(k == 0 || k == n) return 1;
    return choose(n - 1, k) + choose(n - 1, k - 1);
  }

  public static long f(long n, long k) {
    if(k > n) return 0;
    if(k == 0) return 1;
    if(k > n / 2) return f(n,n-k);
    return n * f(n-1,k-1) / k;
  }

  public static BigInteger binomial(final int N, final int K) {
    BigInteger ret = BigInteger.ONE;
    for (int k = 0; k < K; k++) {
      ret = ret.multiply(BigInteger.valueOf(N-k)).divide(BigInteger.valueOf(k+1));
    }
    return ret;
  }
}
