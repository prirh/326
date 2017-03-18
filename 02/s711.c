/**
 * s711.c
 * COSC326 Etude 2
 * 711 Problem
 *
 * @author Rhianne Price
 * March 2017
 **/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/**
 * Finds four prices that sum and multiply to $7.11.
 *
 * Finds all divisors to 2 dp of 7.11, checks all combinations of 3 numbers
 * for combos that sum to less than 7.11, then checks if those 3 and the
 * difference between the sum of the 3 and 7.11 multiply to
 * and prints it if it does.
 */
int main(){
    double a, b, c, d;
    int i, j, k, divisor_count;
    int TOTAL = 711;
    double *divisors = malloc(TOTAL * sizeof divisors);
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
                    printf("$%.2f $%.2f $%.2f $%.2f\n", a/100, b/100, c/100, d/100);
                }
            }
        }
    }
    free(divisors);
    return EXIT_SUCCESS;
}
