#include <stdlib.h>
#include <stdio.h>

int half(int i) {
    if(i >= 0) {
        return i / 2;
    } else {
        return i / 2 - 1;
    }
}

int midpoint(int x, int y) {
    int half_x = half(x);
    int half_y = half(y);

    if( (x % 2) != 0 && (y % 2) != 0){
      return half_x + half_y + 1;
  }
  return half_x + half_y;

}


    int main() {
        printf("%d\n", midpoint(-2, 3));
    }
