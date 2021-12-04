#include <iostream>
#include <string>
using namespace std;
int main()
{
    char isAString[6] = { 'H','e','l','l','o','\0' };
    char isNotAString[5] = { 'H','e','l','l','o' };
    char isString[6] = "Hello";
    char isAnotherString[] = "Array size is inferred";
    string myString = "Hello!";
    string myNewString = "Less typing";
    cout << isAString << endl;
    cout << isNotAString << endl;
    cout << isString << endl;
    cout << isAnotherString << endl;
    cout << myString << endl;
    cout << myNewString << endl;
    return 0;
}