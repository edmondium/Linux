#include <cstdio>
#include "Rectangle.h"
int main()
{
    Rectangle uninitialized;
    Rectangle value{};
    Rectangle aggregate{ 4,7 };
    value.resize( 5,5 );
    aggregate.resize( 1,2 );
    int area_of_value{ value.area() };
    int area_of_aggregate{ aggregate.area() };
    return 0;
}