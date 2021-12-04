#include <cmath>
#include <iostream>
using namespace std;
int main()
{
    float number;
    cout << "Input number: ";
    cin >> number;
    cout << "Value = " << pow(number, (1.0 / 3.0)) << endl;
    return 0;
}