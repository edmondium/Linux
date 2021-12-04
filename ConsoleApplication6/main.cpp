#include <iostream>
#include <string>
using namespace std;
struct coffeeBean
{
    string name, country;
    int strength;
};
int main()
{
    coffeeBean myBean = { "Strata","Columbia",10 };
    coffeeBean newBean;
    newBean.name = "Flora";
    newBean.country = "Mexico";
    newBean.strength = 9;
    cout << "Coffee bean " + newBean.name + " is from " + newBean.country << endl;
    return 0;
}