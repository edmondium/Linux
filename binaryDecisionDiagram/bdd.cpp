#include <bdd.h>
#include <iostream>
using namespace std;
int main(void)
{
    bdd x, y, z;
    bdd_init(1000, 100);
    bdd_setvarnum(5);
    x = bdd_ithvar(0);
    y = bdd_ithvar(1);
    z = x & y;
    cout << bddtable << z << endl;
    bdd_done();
    return 0;
}
