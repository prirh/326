#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define PLUS 0
#define TIMES 1

int solve(int *numbers, int size, int *operators, int total, char order) {
  int i, j;
  int first = numbers[0];
  int sum;
  int* working = malloc(size * sizeof working);

  if(size == 1 & numbers[0] == total){
    printf("%c %d\n", order, total);
    return 1;
  }
  /* Left to right. */
  if(order == 'L'){
    sum = first;
    for(i = 0; i < size - 1; i++) {
      if(operators[i] == PLUS) {
        sum += numbers[i + 1];
      } else if(operators[i] == TIMES){
        sum *= numbers[i + 1];
      }
      if(sum > total) return 0;
      if(i == size - 2 && sum == total) {
        printf("%c %d", order, first);
        for(j = 0; j <= i; j++){
          printf(" %c %d", operators[j] == PLUS ? '+' : '*', numbers[j + 1]);
        }
        printf("\n");
        return 1;
      }
    }
  }

  /* Normal order - multiplication first. */
  if(order == 'N'){
    for(i = 0; i < size - 1; i++) {
      if(operators[i] == TIMES) {
        working[i] = 0;
        working[i + 1] = numbers[i + 1] * numbers[i];
        if(working[i + 1] > total) return 0;
      }
    }
    sum = working[0];
    for(i = 0; i < size - 1; i++) {
        sum += working[i + 1];
    }
    if(sum > total) return 0;
    if(i == size - 2 && sum == total) {
      printf("%c %d", order, first);
      for(j = 0; j <= i; j++){
        printf(" %c %d", operators[j] == PLUS ? '+' : '*', numbers[j + 1]);
      }
        printf("\n");
        return 1;
      }
    }
  free(working);
  return 0;
}

int main() {
  int *numbers = malloc(25 * sizeof numbers);
  int number, total, i, bits, size;
  char order = '\0';
  int *operators;
  int solutions;

  while(!feof(stdin)){
    i = 0;
    solutions = 0;
    while(1 == scanf("%d", &number)){
      numbers[i++] = number;
    }
    total = numbers[size = i - 1];
    scanf("%c\n", &order);

    operators = malloc((size - 1) * sizeof operators);
    for(i = 0; i < pow(2, size - 1); i++) {
      for(bits = 0; bits < size - 1; bits++) {
        operators[bits] = (i >> bits) & 1;
      }
      solutions += solve(numbers, size, operators, total, order);
    }
    if(solutions == 0) {
      printf("%c impossible\n", order);
    }
    free(operators);
  }
  free(numbers);
  return EXIT_SUCCESS;
}
