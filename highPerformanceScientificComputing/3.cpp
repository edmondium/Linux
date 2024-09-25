#include <iostream>
#include <memory>

class C {
    std::unique_ptr<double[]> data; //inserting []
    size_t N{};
public:
    C() = default;
    ~C() = default;
    C(size_t n) : data{ std::make_unique<double[]>(n) }, N{n} {}
    auto size() const -> size_t { return N; }
    auto operator[](size_t i) const -> const double& { return data[i]; }
    auto operator[](size_t i) -> double& { return data[i]; }
};

int main() {
    C c(5);
    std::cout << "Size: " << c.size() << std::endl;
    c[0] = 1.1;
    std::cout << "First element: " << c[0] << std::endl;
    return 0;
}