/**
 * g711.c
 * COSC326 Etude 2
 * Generalised 711 Problem
 *
 * @author Rhianne Price
 * March 2017
 **/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/**
 * Memory allocation with error checking, exits if memory allocation fails.
 */
void *emalloc(size_t s){
  void *result = malloc(s);
  if(NULL == result){
    fprintf(stderr, "Memory allocation failed!\n");
    exit(EXIT_FAILURE);
  }
  return result;
}

/**
 * Finds all sets of four prices that uniquely sum and multiply to each dollar
 * amount between $0.00 and $9.99.
 *
 * For each price between $0.00 and $9.99, it finds all divisors to 4 sf, checks
 * all combinations of 3 divisors for combos that sum to less than
 * the price, then checks if those 3 and the difference between the sum of the 3
 * and the price multiply to the price and stores the 4 numbers if it does.
 * It then keeps count of how many solutions there are for each price,
 * and prints the solutions for prices that have only one solution.
 */
int main(){
    double a, b, c, d;
    int i, j, k, divisor_count, unique_solutions = 0;
    double total;
    double *divisors;
    double MAX = 999;
    int *solution_count = emalloc(MAX * sizeof solution_count);
    char **first_solution = emalloc(MAX * sizeof first_solution);

    for(total = 1; total < MAX; total++){
        divisors = emalloc(total * sizeof divisors);
        divisor_count = 0;
        for (i = 1; i <= total; i++)
        if ((int)(total * pow(10,6)) % i == 0) {
            divisors[divisor_count++] = i;
        }
        for (i = 0; i < divisor_count; i++) {
            a = divisors[i];
            for (j = i; j < divisor_count; j++) {
                b = divisors[j];
                for (k = j; k < divisor_count; k++) {
                    c = divisors[k];
                    d = total - a - b - c;
                    if (d < c) break;
                    if (a * b * c * d == (total * pow(10,6))){
                        solution_count[(int)total]++;
                        if(solution_count[(int)total] == 1){
                            first_solution[(int)total] = emalloc(128 * sizeof(char));
                            sprintf(first_solution[(int)total],
                                    "$%.2f = $%.2f + $%.2f + $%.2f + $%.2f",
                                    total/100, a/100, b/100, c/100, d/100);
                        }
                    }
                }
            }
        }
        free(divisors);
    }
    for(i = 0; i < MAX; i++) {
        if(solution_count[i] == 1) {
            printf("%s\n", first_solution[i]);
            unique_solutions++;
        }
        free(first_solution[i]);
    }
    printf("%d solutions\n", unique_solutions);
    free(first_solution);
    free(solution_count);
    return EXIT_SUCCESS;
}
