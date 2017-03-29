#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define PLUS 0
#define TIMES 1

/**
 * Memory allocation with error checking, exits if memory allocation fails.
 */
void *emalloc(size_t s){
  void *result = malloc(s);
  if(NULL == result){
    fprintf(stderr, "Memory allocation failed for size: %lu\n", s);
    exit(EXIT_FAILURE);
  }
  return result;
}

int solve(int *numbers, int size, int *operators, int total, char order) {
  int i, j;
  int first = numbers[0];
  int sum;
  int* working = emalloc(size * sizeof working);

  if(size == 1 && numbers[0] == total){
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
    working[0] = numbers[0];
    for(i = 0; i < size - 1; i++) {
      working[i + 1] = numbers[i + 1];
      if(operators[i] == TIMES) {
        working[i + 1] = numbers[i + 1] * numbers[i];
        working[i] = 0;
        if(working[i + 1] > total) return 0;
      }
    }
    sum = working[0];

    for(i = 1; i < size; i++) {
        sum += working[i];
    }
    if(sum > total) return 0;
    if(sum == total) {
      printf("%c %d", order, first);
      for(j = 0; j < size - 1; j++){
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
  int maxSize = 25;
  int *numbers = emalloc(maxSize * sizeof numbers);
  int number, total, bits, size, possible_combos;
  int i;
  char order = '\0';
  int *operators;
  int solutions;

  while(!feof(stdin)){
    i = 0;
    solutions = 0;
    while(1 == scanf("%d", &number)){
      numbers[i++] = number;
      if(i == maxSize){
        maxSize *= 2;
        numbers = realloc(numbers, maxSize * sizeof numbers);
      }
    }
    total = numbers[size = i - 1];
    scanf("%c\n", &order);

    possible_combos = pow(2, size - 1);
    operators = emalloc((size - 1) * sizeof operators);
    for(i = 0; i < possible_combos; i++) {
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
