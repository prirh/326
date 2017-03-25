#include <math.h>
#include <stdio.h>

float hyp(float x, float y) {
    printf("%-10s %.25e\n", "x * x:", x*x);
    printf("%-10s %.25e\n", "y * y:", y*y);
    printf("%-10s %.25e\n", "sum:", y*y + x*x);
    printf("%-10s %.25e\n", "sqrt", sqrt(x*x + y*y));
    return sqrtf(x*x + y*y);
}

// float hyp(float x, float y) {
// float a = fabsf(x), b = fabsf(y); if (a > b) {
// b = b / a;
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
    printf("%-10s %d\n", "iteration:", i);
    printf("%-10s %30.25e\n", "x:", x);
    printf("%-10s %30.25e\n", "y:", y);
    printf("%-10s %30.25e\n", "z:", z);
    printf("%-10s %.25e\n\n", "error:", fabsf(hyp(x, y) - z)/z);
    x *= 10.0f , y *= 10.0f, z *= 10.0f;
  }
  return 0;
}
