#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "mylib.h"

int main(){
    double a, b, c, d;
    int i, j, k, divisor_count, unique_solutions = 0;
    double total;
    double *divisors;
    double MAX = 999;
    int *solution_count = emalloc(MAX * sizeof solution_count);
    double **latest_solution = emalloc(MAX * sizeof latest_solution);

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
                            latest_solution[(int)total] = emalloc(4 * sizeof (double));
                            latest_solution[(int)total][0] = a;
                            latest_solution[(int)total][1] = b;
                            latest_solution[(int)total][2] = c;
                            latest_solution[(int)total][3] = d;
                        }
                    }
                }
            }
        }
        free(divisors);
    }
    for(i = 0; i < MAX; i++) {
        if(solution_count[i] == 1) {
            printf("%.2f = %.2f + %.2f + %.2f + %.2f\n",
            (double) i/100, latest_solution[i][0]/100,
            latest_solution[i][1]/100, latest_solution[i][2]/100,
            latest_solution[i][3]/100);
            unique_solutions++;
        }
        free(latest_solution[i]);
    }
    printf("%d solutions\n", unique_solutions);
    free(latest_solution);
    free(solution_count);
    return EXIT_SUCCESS;
}
