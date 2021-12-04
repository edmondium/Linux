#include <iostream>
using namespace std;
enum Day
{
    Sunday=1, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
};
int main()
{
    Day payDay;
    payDay = Thursday;
    cout << payDay << endl;
    return 0;
}