#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define PLUS 0
#define TIMES 1

int main() {
  int *numbers = malloc(25 * sizeof numbers);
  int number, total, i, bits, size; sum;
  char order = '\0';
  int *operators;

  while(!feof(stdin)){
    i = 0;
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
      if(order == 'L') {
        solveL(numbers, size, operators, total);
      } else if(order == 'N') {
        solveN(numbers, size, operators, total);
      }
    }
    free(operators);
  }

  free(numbers);

  return EXIT_SUCCESS;
}

char *solveN(int *numbers, int size, int *operators, int total) {

}

void solveL(int *numbers, int size, int *operators, int total) {
  int i, j;
  int sum = numbers[0];

  for(i = 0; i < size - 1; i++) {
    if(operators[i] == PLUS) {
      sum += numbers[i + 1];
    } else if(operators[i] == TIMES){
      sum *= numbers[i + 1];
    }
    if(sum > target) break;
    if(sum == target) {
      for(j = 0; j < i; j++) {
        printf("%d %c ", numbers[j], operators[j] == PLUS ? '+' : '*');
      }
    }
  }
  printf("no solution\n");
}
