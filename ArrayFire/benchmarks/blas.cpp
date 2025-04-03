#include <arrayfire.h>
#include <cmath>
#include <iostream>
#include <vector>

using namespace af;

// Wrapper to benchmark matrix multiplication
static array A; // Matrix initialized before timing
constexpr void fn() { array B = matmul(A, A); }

// Main benchmarking function
int main(int argc, char** argv) {
    try {
        // Device and data type initialization
        int device = argc > 1 ? std::stoi(argv[1]) : 0;
        setDevice(device);

        const std::string dtype = (argc > 2 ? argv[2] : "f32");
        const af_dtype dt = (dtype == "f16" ? f16 : f32);
        
        if (dt == f16) {
            std::cout << "Device " << device 
                      << " isHalfAvailable? " << (isHalfAvailable(device) ? "yes" : "no") << "\n";
        }

        info(); // Device information
        std::cout << "Benchmark N-by-N matrix multiply at " << dtype << "\n";

        double peak = 0;
        for (int n = 128; n <= 2048; n += 128) {
            std::cout << n << " x " << n << ": ";

            // Initialize matrix with constants
            A = constant(1, n, n, dt);
            double time = timeit(fn); // Timing matrix multiplication
            
            // Calculate GFLOPS
            double gflops = 2.0 * std::pow(n, 3) / (time * 1e9);
            if (gflops > peak) {
                peak = gflops;
            }

            std::cout << gflops << " GFLOPS\n";
            std::flush(std::cout);
        }

        // Print peak performance
        std::cout << "### Peak: " << peak << " GFLOPS\n";
    } catch (const af::exception& e) {
        std::cerr << "Error: " << e.what() << "\n";
        throw;
    }

    return 0;
}
