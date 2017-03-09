#include <stdio.h>
#include <stdlib.h>

void *emalloc(size_t s){
  void *result = malloc(s);

  if(NULL == result){
    fprintf(stderr, "Memory allocation failed!\n");
    exit(EXIT_FAILURE);
  }
  return result;
}

int main() {

  while(1 == scanf("%s\n", );)
  return EXIT_SUCCESS;
}
