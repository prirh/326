#include <math.h>
#include <stdio.h>

float heron_area(float a, float c) {
    float s = (a+a+c)/2.0f;
    printf("a + a = %-19.0f\t", a + a);
    printf("a + a + c = %-19.0f\t", a + a + c);
    printf("s = %-21.1f\t", s);
    return (s-a)*sqrtf(s*(s-c));
}

float baseht_area(float a, float c) {
    float d = c/(2.0f*a);
    return sqrtf(1.0f - d*d)*a*c*0.5f;
}

int main(void) {
  float a = 1;
  float c = 1;
  int i;
  printf("heron: \n");
  for (i = 0; i < 19; i++) {
      printf("a = %-19.0f %-20.0f", a, heron_area(a, c));
      printf("\n");
     a *= 10.0f;
  }

  a = 1;
  printf("\n\nbase * height: \n");
  for (i = 0; i < 19; i++) {
      printf("%.2f\n", baseht_area(a, c));
     a *= 10.0f;
  }
  return 0;
}
