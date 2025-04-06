#include <arrayfire.h>
#include <cmath>
#include <cstdio>
#include <iostream>
#include <random>
#include <chrono>

using namespace af;

static constexpr int samples = 20'000'000;

// Generate device estimate of Pi
static double pi_device() {
    array x = randu(samples, f32);
    array y = randu(samples, f32);
    return 4.0 * sum<float>(sqrt(x * x + y * y) < 1) / samples;
}

// Generate host estimate of Pi
static double pi_host() {
    int count = 0;
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<float> dist(0.0f, 1.0f);

    for (int i = 0; i < samples; ++i) {
        float x = dist(gen);
        float y = dist(gen);
        if (sqrt(x * x + y * y) < 1) count++;
    }
    return 4.0 * count / samples;
}

// Wrappers for timing
static void device_wrapper() { pi_device(); }
static void host_wrapper() { pi_host(); }

int main(int argc, char **argv) {
    try {
        int device = argc > 1 ? std::stoi(argv[1]) : 0;
        setDevice(device);
        info();

        auto device_start = std::chrono::high_resolution_clock::now();
        double device_pi = pi_device();
        auto device_end = std::chrono::high_resolution_clock::now();
        std::chrono::duration<double> device_time = device_end - device_start;

        std::cout << "Device: " << device_time.count() << " seconds to estimate Pi = " << device_pi << "\n";

        auto host_start = std::chrono::high_resolution_clock::now();
        double host_pi = pi_host();
        auto host_end = std::chrono::high_resolution_clock::now();
        std::chrono::duration<double> host_time = host_end - host_start;

        std::cout << "Host: " << host_time.count() << " seconds to estimate Pi = " << host_pi << "\n";
    } catch (const std::exception &e) {
        std::cerr << e.what() << "\n";
        throw;
    }

    return 0;
}
