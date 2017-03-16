#include <math.h>
#include <stdio.h>

float hyp(float x, float y) {
    double a = (double) x;
    double b = (double) y;
    printf("    squares x: %e   y: %e\n", a*a - x*x, b*b - y*y);
    printf("    sum: %e\n", (double)x*x + (double)y*y - x*x + y*y);
    printf("    sqrt: %e\n", sqrt(a*a + b*b) - sqrtf(x*x + y*y));
    return sqrtf(x*x + y*y);
}

// float hyp(float x, float y) {
// float a = fabsf(x), b = fabsf(y); if (a > b) {
// b = b/a;
// return sqrtf(1.0f + b*b)*a; } else
// if (a < b) { a = a/b;
// return sqrtf(1.0f + a*a)*b;
// } else { // This works even when a == b == 0.0f
// return a*sqrtf(2.0f); }
// }

int main(void) {
  float x = 3.0f, y = 4.0f, z = 5.0f;
  int i;
  for (i = 0; i < 20; i++) {
    float e = fabsf(hyp(x, y) - z)/z;
    printf("%2d %e\n\n", i, e);
    x /= 10.0f , y /= 10.0f, z /= 10.0f;
  }
  return 0;
}
