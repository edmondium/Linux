#include <arrayfire.h>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <memory>
#include <stdexcept>
#include <concepts> // Leverage concepts in C++23 for strict type-checking

using namespace af;

// Window management with smart pointers for improved safety
std::unique_ptr<Window> win;

array normalize(array a, float max) {
    float mx = max * 0.5f;
    float mn = -max * 0.5f;
    return (a - mn) / (mx - mn);
}

static void swe(bool console) {
    // Grid length, number and spacing
    constexpr unsigned Lx = 1600, nx = Lx + 1;
    constexpr unsigned Ly = 1600, ny = Ly + 1;
    constexpr float dx = Lx / (nx - 1);
    constexpr float dy = Ly / (ny - 1);
    
    array ZERO = constant(0, nx, ny);
    array um = ZERO, vm = ZERO;
    
    unsigned io = static_cast<unsigned>(std::floor(Lx / 6.0f));
    unsigned jo = static_cast<unsigned>(std::floor(Ly / 6.0f));
    constexpr unsigned k = 15;
    
    array x = tile(range(nx), 1, ny);
    array y = tile(range(dim4(1, ny), 1), nx, 1);
    
    // Initial condition
    array etam = 0.01f * exp(-((x - io) * (x - io) + (y - jo) * (y - jo)) / (k * k));
    float m_eta = max<float>(etam);
    array eta = etam;
    constexpr float dt = 0.5f;

    // Convolution kernels
    std::array<float, 3> h_diff_kernel = {9.81f * (dt / dx), 0.0f, -9.81f * (dt / dx)};
    std::array<float, 9> h_lap_kernel = {0.0f, 1.0f, 0.0f, 1.0f, -4.0f, 1.0f, 0.0f, 1.0f, 0.0f};
    
    array h_diff_kernel_arr(3, h_diff_kernel.data());
    array h_lap_kernel_arr(3, 3, h_lap_kernel.data());
    
    if (!console) {
        win = std::make_unique<Window>(1536, 768, "Shallow Water Equations");
        win->grid(2, 2);
    }

    unsigned iter = 0;
    unsigned random_interval = 30;
    
    while (!win->close()) {
        if (iter > 2000) {
            etam = 0.01f * exp(-((x - io) * (x - io) + (y - jo) * (y - jo)) / (k * k));
            m_eta = max<float>(etam);
            eta = etam;
            iter = 0;
        }
        
        // Raindrops
        if (iter % 100 == 0 || iter % 130 == 0 || iter % random_interval == 0) {
            unsigned io = static_cast<unsigned>(rand() % Lx);
            unsigned jo = static_cast<unsigned>(rand() % Ly);
            random_interval = rand() % 200 + 1;
            eta += 0.01f * exp(-((x - io) * (x - io) + (y - jo) * (y - jo)) / (k * k));
        }
        
        // Compute
        array up = um + convolve(eta, h_diff_kernel_arr);
        array vp = um + convolve(eta, h_diff_kernel_arr.T());
        array e = convolve(eta, h_lap_kernel_arr);
        array etap = 2 * eta - etam + (2 * dt * dt) / (dx * dy) * e;
        
        etam = eta;
        eta = etap;
        m_eta = max<float>(etam);

        if (!console) {
            (*win)(0, 0).setColorMap(AF_COLORMAP_BLUE);
            array hist_out = histogram(normalize(eta, m_eta), 15);
            (*win)(0, 1).setAxesLimits(0, hist_out.elements(), 0, max<float>(hist_out));
            (*win)(0, 0).image(normalize(eta, m_eta));
            (*win)(0, 1).hist(hist_out, 0, 1, "Normalized Pressure Distribution");
            (*win)(1, 0).plot(range(up.dims(1)), vp.col(0), "Pressure at left boundary");
            (*win)(1, 1).plot(flat(eta.col(0)), flat(up.col(0)), flat(vp.col(0)), "Gradients versus Magnitude at left boundary");
            
            win->show();
        } else {
            eval(eta, up, vp);
        }

        iter++;
    }
}

int main(int argc, char* argv[]) {
    int device = argc > 1 ? std::stoi(argv[1]) : 0;
    bool console = argc > 2 ? argv[2][0] == '-' : false;
    
    try {
        af::setDevice(device);
        af::info();
        std::printf("Simulation of shallow water equations\n");
        swe(console);
    } catch (const af::exception& e) {
        std::fprintf(stderr, "%s\n", e.what());
        throw;
    }
    
    return 0;
}
