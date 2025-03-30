#include <iostream>
#include <vector>
#include <cmath>
#include <fstream>
#include <sstream>
double maxDist(const std::vector<double>& x_i, const std::vector<double>& x_j) {
    double max_dist = 0.0;
    for (size_t k = 0; k < x_i.size(); ++k) {
        double dist = std::abs(x_i[k] - x_j[k]);
        if (dist > max_dist) {
            max_dist = dist;
        }
    }
    return max_dist;
}

double phi(int m, double r, const std::vector<double>& time_series) {
    int n = time_series.size();
    std::vector<std::vector<double>> x(n - m + 1, std::vector<double>(m));
    for (int i = 0; i <= n - m; ++i) {
        for (int j = 0; j < m; ++j) {
            x[i][j] = time_series[i + j];
        }
    }
    std::vector<double> C(n - m + 1);
    for (int i = 0; i <= n - m; ++i) {
        int count = 0;
        for (int j = 0; j <= n - m; ++j) {
            if (maxDist(x[i], x[j]) <= r) {
                ++count;
            }
        }
        C[i] = static_cast<double>(count) / (n - m + 1);
    }
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
    /*std::vector<double> time_series = {85, 80, 89, 85, 80, 89, 85, 80, 89,
                                       85, 80, 89, 85, 80, 89, 85, 80, 89,
                                       85, 80, 89, 85, 80, 89, 85, 80, 89,
                                       85, 80, 89, 85, 80, 89, 85, 80, 89,
                                       85, 80, 89, 85, 80, 89, 85, 80, 89,
                                       85, 80, 89, 85, 80, 89}; // Example data*/
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
    //int m = 2; // Length of compared run of data
    //double r = 3.0; // Filtering level
    double apEn = approximateEntropy(data, m, r);
    //double apEn = approximateEntropy(time_series, m, r);
    std::cout << "Approximate Entropy: " << apEn << std::endl;

    return 0;
}
