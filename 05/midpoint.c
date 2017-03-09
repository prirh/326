#include <stdlib.h>
#include <stdio.h>

int half(int i) {
    if(i % 2 == 0) {
        return i / 2;
    } else if (i < 0){
        return i / 2 - 1;
    }
    return i / 2;

}

int correct_midpoint(int x, int y){
    return (x + y)/2;
}

int midpoint(int x, int y) {
    if(x < 0 && y < 0) {
        return -1 * midpoint(-x, -y);
    }

    if(x % 2 != 0 && y % 2 != 0){
        return half(x) + half(y) + 1;
    }

    if((x < 0 && x % 2 != 0) || (y < 0 && y % 2 != 0)) {
        return half(x) + half(y) + 1;
    }
    return half(x) + half(y);

}
    int main() {
        int x, y;
        while(scanf("%d %d", &x, &y) == 2){
            if(correct_midpoint(x, y) != midpoint(x, y)){
            printf("%d and %d\n", x, y);
            printf("should be %d\n", correct_midpoint(x, y));
            printf("but is %d\n\n", midpoint(x, y));
        }
        }
    }
