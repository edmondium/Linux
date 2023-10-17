#include <boost/integer/common_factor.hpp>
#include <algorithm>
#include <iterator>
#include <iostream>
using namespace boost;
using namespace std;
int main()
{
   using std::cout;
   using std::endl;

   cout << "The GCD and LCM of 6 and 15 are "
//    << boost::integer::gcd(6, 15) << " and "
//    << boost::integer::lcm(6, 15) << ", respectively."
   << integer::gcd(6, 15) << " and "
   << integer::lcm(6, 15) << ", respectively."
   << endl;

   cout << "The GCD and LCM of 8 and 9 are "
   //<< boost::integer::static_gcd<8, 9>::value
   << integer::static_gcd<8, 9>::value
   << " and "
   //<< boost::integer::static_lcm<8, 9>::value
   << integer::static_lcm<8, 9>::value
   << ", respectively." << endl;

   int  a[] = { 4, 5, 6 }, b[] = { 7, 8, 9 }, c[3];
//    std::transform( a, a + 3, b, c, boost::integer::gcd_evaluator<int>() );
//    std::copy( c, c + 3, std::ostream_iterator<int>(cout, " ") );
   transform( a, a + 3, b, c, integer::gcd_evaluator<int>() );
   copy( c, c + 3, ostream_iterator<int>(cout, " ") );
}