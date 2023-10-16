//The program reads a sequence of integers from standard input, 
//uses Boost.Lambda to multiply each number by three, 
//and writes them to standard output
#include <boost/lambda/lambda.hpp>
#include <iostream>
#include <iterator>
#include <algorithm>
using namespace std;
int main()
{
    using namespace boost::lambda;
    typedef istream_iterator<int> in;
    for_each(
        in(cin), in(), cout << (_1 * 3) << " "
    );
}