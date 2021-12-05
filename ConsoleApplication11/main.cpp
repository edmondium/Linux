#include <cstdio>
inline void swap(int a, int b)
{
    int temp = a;
    a = b;
    b = temp;
}
int main()
{
    swap(5, 6);
    return 0;
}