#include <iostream>
#include <array>
#include <vector>
#include <cassert>

int main() {
    // Initialize std::array and std::vector
    std::array<double, 12> A{};
    std::vector<double> V(12, 0.);

    // First assertion: Check if sizes are equal
    assert(A.size() == V.size());

    // Second assertion: Check if sizes in bytes are equal
    assert(sizeof(A) == sizeof(V));

    // Output to indicate the program has run successfully
    std::cout << "All assertions passed!" << std::endl;

    return 0;
}