#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "mylib.h"

/**
 * Finds all divisors to 4 sf of 7.11, checks all combinations of 3
 * of those divisors for combos that sum to less than 7.11, then checks if
 * those 3 and the difference between the sum of the 3 and 7.11 multiply to
 * and prints it if it does.
 */
int main(){
    double a, b, c, d;
    int i, j, k, divisor_count;
    int TOTAL = 711;
    double *divisors = emalloc(TOTAL * sizeof divisors);
    divisor_count = 0;

    /* Find all clean divisors of 7.11. */
    for (i = 1; i <= TOTAL; i++) {
        if ((TOTAL * (int) pow(10,6)) % i == 0) divisors[divisor_count++] = i;
    }

    /* Check all possible triple combinations of the divisors */
    for (i = 0; i < divisor_count; i++) {
        a = divisors[i];
        for (j = i; j < divisor_count; j++) {
            b = divisors[j];
            for (k = j; k < divisor_count; k++) {
                c = divisors[k];
                d = TOTAL - a - b - c;
                if (d < c) break;
                if (a * b * c * d == (TOTAL * pow(10,6))) {
                    printf("%.2f %.2f %.2f %.2f\n", a/100, b/100, c/100, d/100);
                }
            }
        }
    }
    return EXIT_SUCCESS;
}
