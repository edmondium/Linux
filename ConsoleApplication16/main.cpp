#include <iostream>
#include <map>
#include <string>
#include <string_view>
using namespace std;
void print_map(string_view comment, const map<string, int>& m)
{
    cout << comment;
    for (const auto& [key,value]:m)
    {
        cout << key << " = " << value << "; ";
    }
    cout << "\n";
}
int main()
{
    map<string, int> m{ {"CPU",10},{"GPU",15},{"RAM",20}, };
    print_map("Initial map: ", m);
    m["CPU"] = 25;
    m["SSD"] = 30;
    print_map("Updated map: ", m);
    return 0;
}