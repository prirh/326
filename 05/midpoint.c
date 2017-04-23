#include <stdlib.h>

int midpoint(int x, int y) {
    if(x < 0 && y < 0) {
        return -midpoint(-(x + 1), -(y + 1)) - 1;
    }

    if(x < 0 || y < 0) {
        return div((x + y), 2).quot;
    }

    return x / 2 + y / 2 + (x % 2 + y % 2) / 2;
}
