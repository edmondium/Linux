#include <iostream>
#include <vector>

int main() {
    const int N = 10'000'000; // 10 million elements

    // 1. Initialize vectors on the host (CPU)
    std::vector<float> A(N, 1.5f);
    std::vector<float> B(N, 2.5f);
    std::vector<float> C(N, 0.0f);

    // Get raw pointers for OpenACC memory mapping
    float* ptr_A = A.data();
    float* ptr_B = B.data();
    float* ptr_C = C.data();

    std::cout << "Starting OpenACC vector addition..." << std::endl;

    // 2. The OpenACC Directive
    // - parallel loop: Tells the compiler to parallelize the following for-loop
    // - copyin: Copies data from CPU to GPU before the loop starts
    // - copyout: Copies data from GPU back to CPU after the loop finishes
    #pragma acc parallel loop copyin(ptr_A[0:N], ptr_B[0:N]) copyout(ptr_C[0:N])
    for (int i = 0; i < N; ++i) {
        ptr_C[i] = ptr_A[i] + ptr_B[i];
    }

    // 3. Verify the results
    std::cout << "Verification:" << std::endl;
    std::cout << "C[0] = " << C[0] << " (Expected: 4.0)" << std::endl;
    std::cout << "C[N-1] = " << C[N-1] << " (Expected: 4.0)" << std::endl;

    return 0;
}
