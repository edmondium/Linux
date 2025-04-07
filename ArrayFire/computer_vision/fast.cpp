#include <arrayfire.h>
#include <iostream>
#include <cstdlib>

using namespace af;

void fast_demo(bool console) {
    // Load image based on console mode
    array img_color = console 
        ? loadImage("../images/square.png", true)
        : loadImage("../images/man.jpg", true);

    // Convert image from RGB to grayscale
    array img = colorSpace(img_color, AF_GRAY, AF_RGB);

    // Normalize color image for visualization
    img_color /= 255.f;

    // Perform FAST feature detection
    auto feat = fast(img, 20.0f, 9, true, 0.05);

    // Retrieve feature coordinates
    auto h_x = feat.getX().host<float>();
    auto h_y = feat.getY().host<float>();

    // Draw crosshairs at detected features
    constexpr int draw_len = 3;
    for (size_t f = 0; f < feat.getNumFeatures(); f++) {
        int x = h_x[f];
        int y = h_y[f];

        // Draw horizontal line
        img_color(y, seq(x - draw_len, x + draw_len), 0) = 0.f;
        img_color(y, seq(x - draw_len, x + draw_len), 1) = 1.f;
        img_color(y, seq(x - draw_len, x + draw_len), 2) = 0.f;

        // Draw vertical line
        img_color(seq(y - draw_len, y + draw_len), x, 0) = 0.f;
        img_color(seq(y - draw_len, y + draw_len), x, 1) = 1.f;
        img_color(seq(y - draw_len, y + draw_len), x, 2) = 0.f;
    }

    freeHost(h_x);
    freeHost(h_y);

    // Print the number of features found
    std::cout << "Features found: " << feat.getNumFeatures() << '\n';

    // Display or print the results
    if (!console) {
        Window wnd("FAST Feature Detector");
        while (!wnd.close()) wnd.image(img_color);
    } else {
        af_print(feat.getX());
        af_print(feat.getY());
    }
}

int main(int argc, char** argv) {
    // Parse command-line arguments
    int device = argc > 1 ? std::stoi(argv[1]) : 0;
    bool console = argc > 2 && argv[2][0] == '-';

    try {
        // Set device and display ArrayFire info
        af::setDevice(device);
        af::info();

        std::cout << "** ArrayFire FAST Feature Detector Demo **\n\n";
        fast_demo(console);
    } catch (const af::exception& ae) {
        // Handle exceptions and print error
        std::cerr << ae.what() << '\n';
        throw;
    }
    return 0;
}
