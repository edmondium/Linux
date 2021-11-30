#include <cstdio>
#include <cmath>
int main()
{
    float value = 0.0;
    for (short i = -9; i <= 9; i++)
    {
        value += 1 / (1 + pow(10, i));
    }
    printf("value = %f\n", value);
    return 0;
}