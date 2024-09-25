#include <iostream>
#include <list>

int main() {
    // Initialize the list with given elements
    std::list<int> L{1, 1, 2, 3, 5, 8, 13};

    // Sort the list in descending order using a lambda function
    L.sort([](auto i, auto j){
        return i > j;
    });

    // Print the sorted list
    for (auto a : L) {
        std::cout << a << "\n";
    }

    return 0;
}