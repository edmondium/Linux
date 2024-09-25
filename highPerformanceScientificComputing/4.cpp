#include <array>
template <int m, int n>
constexpr int smaller = m < n ? m : n;
template <class A, int m, int n>
class C {
    std::array<A, smaller<m, n>> data;
public:
    constexpr auto size() const { return data.size(); }
};
auto main() -> int
{
    C<int, sizeof(long), sizeof(double)> c;
    C<int, sizeof(int), sizeof(float)> d;
    static_assert(c.size() > d.size(), "Haha!");
}