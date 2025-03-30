#include <iostream>
#include <vector>
#include <cmath>
#include <fstream>
#include <sstream>
__global__ void maxDistKernel(const double* data, int m, double r, double* C, int n) {
    int i = blockIdx.x * blockDim.x + threadIdx.x;
    if (i < n - m + 1) {
        int count = 0;
        for (int j = 0; j < n - m + 1; ++j) {
            double max_dist = 0.0;
            for (int k = 0; k < m; ++k) {
                double dist = fabs(data[i + k] - data[j + k]);
                max_dist = fmax(max_dist, dist);
            }
            if (max_dist <= r) {
                ++count;
            }
        }
        C[i] = static_cast<double>(count) / (n - m + 1);
    }
}

double phi(int m, double r, const std::vector<double>& data) {
    int n = data.size();
    double* d_data;
    double* d_C;
    cudaMalloc(&d_data, n * sizeof(double));
    cudaMalloc(&d_C, (n - m + 1) * sizeof(double));
    cudaMemcpy(d_data, data.data(), n * sizeof(double), cudaMemcpyHostToDevice);

    int blockSize = 256;
    int numBlocks = (n - m + 1 + blockSize - 1) / blockSize;
    maxDistKernel<<<numBlocks, blockSize>>>(d_data, m, r, d_C, n);
    cudaDeviceSynchronize();

    std::vector<double> C(n - m + 1);
    cudaMemcpy(C.data(), d_C, (n - m + 1) * sizeof(double), cudaMemcpyDeviceToHost);

    cudaFree(d_data);
    cudaFree(d_C);

    double sum = 0.0;
    for (double c : C) {
        sum += std::log(c);
    }
    return sum / (n - m + 1);
}

double approximateEntropy(const std::vector<double>& time_series, int m, double r) {
    return std::abs(phi(m, r, time_series) - phi(m + 1, r, time_series));
}

std::vector<double> readDataFromFile(const std::string& filename) {
    std::vector<double> data;
    std::ifstream file(filename);
    if (file.is_open()) {
        std::string line;
        while (std::getline(file, line)) {
            std::istringstream iss(line);
            double value;
            while (iss >> value) {
                data.push_back(value);
            }
        }
        file.close();
    } else {
        std::cerr << "Unable to open file: " << filename << std::endl;
    }
    return data;
}

int main() {
    std::string filename = "data.txt"; // Replace with your file name
    std::vector<double> data = readDataFromFile(filename);
    if (data.empty()) {
        std::cerr << "No data read from file." << std::endl;
        return 1;
    }

    int m;
    double r;
    std::cout << "Length of compared run of data m: ";
    std::cin >> m;
    std::cout << "Filtering level r: ";
    std::cin >> r;

    double apEn = approximateEntropy(data, m, r);
    std::cout << "Approximate Entropy: " << apEn << std::endl;

    return 0;
}
