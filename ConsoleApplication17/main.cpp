#include <iostream>
#include <map>
#include <string>
using namespace std;
int main()
{
    map<string, int> words;
    string s;
    while (cin >> s)
    {
        ++words[s];
    }
    typedef map<string, int>::const_iterator Iter;
    for (Iter i = words.begin(); i != words.end(); ++i)
    {
        cout << i->first << ": " << i->second << '\n';
    }
    return 0;
}