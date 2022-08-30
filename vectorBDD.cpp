#include <bvec.h>
#include <iostream>
using namespace std;
int main()
{
    int domain[2] = {16, 16};
    bdd_init(100, 100);
    fdd_extdomain(domain, 2);
    bvec y = bvec_varfdd(0);
    bvec v = bvec_con(4, 10);
    bvec z = bvec_add(y, v);
    bvec x = bvec_varfdd(1);
    bdd result = bddtrue;
    for (int i = 0; i < x.bitnum(); i++)
    {
        result &= bdd_apply(x[i], z[i], bddop_biimp);
    }
    cout << fddset << result << endl;
    return 0;
}
