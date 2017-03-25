#include <stdio.h>
#include <stdlib.h>

int main() {
  int size = 1;
  int *numbers = malloc(size * sizeof numbers);
  int number;
  char *line;
  int i;

  while(2 == scanf("%s[^\n] %s[^\n]", );)

  while(1 == scanf("%d", &number)) {
    numbers[size - 1] = number;
    size++;
    numbers = realloc(numbers, size * sizeof numbers);
  }

  for(i = 0; i < size - 1; i++) {
    printf("%d\n", numbers[i]);
  }

  free(numbers);

  return EXIT_SUCCESS;
}
