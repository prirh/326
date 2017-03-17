#include <math.h>
#include <stdio.h>

float hyp(float x, float y) {
    double a = (double) x;
    double b = (double) y;
    printf("    squares x: %.25e y: %.25e\n", x*x, y*y);
    printf("    doub squares x: %.25e y: %.25e\n", a*a, b*b);
    printf("    doub sum x: %.25e\n", a*a + b*b);
    printf("    sum: %.25e\n", y*y + x*x);
    printf("    sqrt: %.25e\n", sqrt(x*x + y*y));
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
    printf("    x: %.25e y: %.25e z: %.25e\n", x, y, z);
    printf("%2d %.25e\n\n", i, e);
    x /= 10.0f , y /= 10.0f, z /= 10.0f;
  }
  return 0;
}
