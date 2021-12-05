#include <iostream>
using namespace std;
int main()
{
    int total = 0;
    for (int i = 1; i <= 10; i++)
    {
        total += i;
    }
    cout << "The sum of the numbers 1 to 10 is " << total << endl;
    //cout << "Current value of i is " << i << cout;
    return 0;
}