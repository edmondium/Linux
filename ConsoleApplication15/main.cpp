#include <cstdio>
#include "Rectangle.h"
int main()
{
    Rectangle uninitialized;
    Rectangle const value{};
    Rectangle aggregate{ 4,7 };
    int area_of_value{ value.area() };
    int area_of_aggregate{ aggregate.area() };
    return 0;
}