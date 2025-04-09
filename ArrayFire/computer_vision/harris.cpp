#include <arrayfire.h>
#include <cstdlib>
#include <iostream>
#include <string_view>

constexpr std::string_view ASSETS_DIR = "../images/";

void harris_demo(bool console) {
    af::Window wnd("Harris Corner Detector");

    // Load image
    af::array img_color = console
        ? af::loadImage((ASSETS_DIR.data() + std::string("square.png")).c_str(), true)
        : af::loadImage((ASSETS_DIR.data() + std::string("man.jpg")).c_str(), true);

    // Convert RGB to grayscale
    af::array img = af::colorSpace(img_color, AF_GRAY, AF_RGB);
    img_color /= 255.0f; // Normalize for visualization

    // Compute gradients
    af::array ix, iy;
    af::grad(ix, iy, img);

    // Compute second-order derivatives
    af::array ixx = ix * ix;
    af::array ixy = ix * iy;
    af::array iyy = iy * iy;

    // Gaussian kernel
    af::array gauss_filt = af::gaussianKernel(5, 5, 1.0, 1.0);

    // Apply Gaussian filter
    ixx = af::convolve(ixx, gauss_filt);
    ixy = af::convolve(ixy, gauss_filt);
    iyy = af::convolve(iyy, gauss_filt);

    // Compute Harris response
    af::array itr = ixx + iyy;
    af::array idet = ixx * iyy - ixy * ixy;
    af::array response = idet - 0.04f * (itr * itr);

    // Non-maximum suppression and thresholding
    af::array mask = af::constant(1, 3, 3);
    af::array max_resp = af::dilate(response, mask);
    af::array corners = response > 1e5f;
    corners = corners * response;
    corners = (corners == max_resp) * corners;

    // Draw crosshairs for detected corners
    float* h_corners = corners.host<float>();
    const int draw_len = 3;
    unsigned good_corners = 0;

    for (int y = draw_len; y < img_color.dims(0) - draw_len; ++y) {
        for (int x = draw_len; x < img_color.dims(1) - draw_len; ++x) {
            if (h_corners[x * corners.dims(0) + y] > 1e5f) {
                // Draw crosshairs
                img_color(y, af::seq(x - draw_len, x + draw_len), 0) = 0.f;
                img_color(y, af::seq(x - draw_len, x + draw_len), 1) = 1.f;
                img_color(y, af::seq(x - draw_len, x + draw_len), 2) = 0.f;

                img_color(af::seq(y - draw_len, y + draw_len), x, 0) = 0.f;
                img_color(af::seq(y - draw_len, y + draw_len), x, 1) = 1.f;
                img_color(af::seq(y - draw_len, y + draw_len), x, 2) = 0.f;
                ++good_corners;
            }
        }
    }
    af::freeHost(h_corners);

    std::cout << "Corners found: " << good_corners << '\n';

    if (!console) {
        while (!wnd.close())
            wnd.image(img_color);
    }
}

int main(int argc, char** argv) {
    try {
        int device = argc > 1 ? std::stoi(argv[1]) : 0;
        bool console = argc > 2 && argv[2][0] == '-';

        af::setDevice(device);
        af::info();
        std::cout << "** ArrayFire Harris Corner Detector Demo **\n\n";

        harris_demo(console);
    } catch (const af::exception& ae) {
        std::cerr << ae.what() << '\n';
        throw;
    }
    return 0;
}
