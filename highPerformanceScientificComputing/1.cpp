#include <iostream>
#include <vector>

int main() {
    // First vector declaration
    std::vector<int> v{1, 2};
    int sum_v = 0;
    for (int num : v) {
        sum_v += num;
    }

    // Second vector declaration
    std::vector<int> w(1, 2);
    int sum_w = 0;
    for (int num : w) {
        sum_w += num;
    }

    // Output the sums
    std::cout << "Sum of elements in vector v: " << sum_v << std::endl;
    std::cout << "Sum of elements in vector w: " << sum_w << std::endl;

    return 0;
}