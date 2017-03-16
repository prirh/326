#include <stdlib.h>
#include <stdio.h>

int midpoint(int x, int y) {
    if(x < 0 && y < 0) {
        return -midpoint(-x, -y);
    }

    if(x < 0 || y < 0) {
        return (x + y) / 2;
    }

    return (x / 2) + (y / 2) + (x % 2 + y % 2) / 2;
}
