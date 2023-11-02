//  auto_cpu_timer_example.cpp  ------------------------------------------------------//

//  Copyright Beman Dawes 2006

//  Distributed under the Boost Software License, Version 1.0.
//  See http://www.boost.org/LICENSE_1_0.txt

#include <boost/timer/timer.hpp>
#include <cmath>
using namespace boost;
using namespace std;
int main()
{
  //boost::timer::auto_cpu_timer t;
  timer::auto_cpu_timer t;
  for ( long i = 0; i < 100000000; ++i )
    //std::sqrt( 123.456L );  // burn some time
    sqrt( 123.456L );  // burn some time
  return 0;
}