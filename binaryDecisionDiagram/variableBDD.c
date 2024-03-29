#include <bdd.h>
int main()
{
    bdd v1, v3, a, b;
    static int v[2] = {1, 3};
    bdd_init(100, 100);
    bdd_setvarnum(5);
    v1 = bdd_ithvar(1);
    v3 = bdd_ithvar(3);
    //one way
    a = bdd_addref(bdd_apply(v1, v3, bddop_and));
    bdd_printtable(a);
    //another way
    b = bdd_addref(bdd_makeset(v, 2));
    bdd_printtable(b);
    return 0;
}
