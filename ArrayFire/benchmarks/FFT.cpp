#include <arrayfire.h>
#include <cmath>
#include <cstdlib>
#include <format>
#include <iostream>
#include <stdexcept>
#include <string_view>

using namespace af;

static array A;

static void fn() {
    array B = fft2(A);
    B.eval();  // Ensure the computation is evaluated
}

int main(int argc, char** argv) {
    try {
        // Using std::string_view for safer command-line argument handling
        std::string_view device_arg = argc > 1 ? argv[1] : "0";
        int device = std::stoi(std::string(device_arg));
        setDevice(device);
        info();

        std::cout << "Benchmark N-by-N 2D FFT\n";

        for (int M = 7; M <= 12; M++) {
            int N = (1 << M);
            std::cout << std::format("{:4d} x {:4d}: ", N, N);
            A = randu(N, N);

            double time = timeit(fn);
            double gflops = 10.0 * N * N * M / (time * 1e9);
            std::cout << std::format("{:4.0f} Gflops\n", gflops) << std::flush;
        }
    } catch (const af::exception& e) {
        std::cerr << e.what() << '\n';
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << '\n';
    }
    return 0;
}
