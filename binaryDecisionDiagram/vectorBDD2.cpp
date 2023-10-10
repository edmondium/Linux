#include <bvec.h>
#include <iostream>
using namespace std;
int main()
{
    int domain[2] = {16, 16};
    bdd_init(100, 100);
    fdd_extdomain(domain, 2);
    bvec y = bvec_varfdd(1);
    bvec x = bvec_varfdd(0);
    bdd result = bvec_lte(x, y);
    cout << fddset << result << endl;
    return 0;
}
