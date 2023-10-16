//simple program that extracts the subject lines from emails.
#include <boost/regex.hpp>
#include <iostream>
#include <string>
using namespace boost;
using namespace std;
int main()
{
    string line;
    regex pat("^Subject: (Re: |Aw: )*(.*)");
    while (cin)
    {
        getline(cin, line);
        smatch matches;
        if (regex_match(line, matches, pat))
        {
            cout << matches[2] << endl;
        }
        
    }
    
}