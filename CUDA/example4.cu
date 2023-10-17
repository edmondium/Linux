// Floyd-Warshall algorithm
#include <iostream>
using namespace std;
// Define the size of the graph
#define n 4

// Define the kernel function
__global__ void FW_APSP(int *D, int)
{
    int i = threadIdx.x;
    int j = threadIdx.y;
    for (int k = 0; k < n; k++)
    {
        if (D[i * n + j] > D[i * n + k] + D[k * n + j])
        {
            D[i * n + j] = D[i * n + k] + D[k * n + j];
        }
        __syncthreads();
    }
    
}

int main()
{
    // Define the graph as an adjacency matrix
    int h_D[n][n] = {{0, 9999, -2, 9999},
                     {4, 0, 3, 9999},
                     {9999, 9999, 0, 2},
                     {9999, -1, 9999, 0}};
    
    // Allocate memory for the array on the device
    int *d_D;
    cudaMalloc(&d_D, n * n * sizeof(int));
    
    // Copy the data from the host to the device
    cudaMemcpy(d_D, h_D, n * n * sizeof(int), cudaMemcpyHostToDevice);
    
    // Define the block size
    dim3 threadsPerBlock(n, n);
    
    // Launch the kernel
    FW_APSP<<<1, threadsPerBlock>>>(d_D, n);
    
    // Check for errors
    cudaError_t err = cudaGetLastError();
    if (err != cudaSuccess)
    {
        //printf("CUDA error: %s\n", cudaGetErrorString(err));
        cout << "CUDA error: " << cudaGetErrorString(err) << endl;
        exit(EXIT_FAILURE);
    }
    
    // Copy the data from the device to the host
    cudaMemcpy(h_D, d_D, n * n * sizeof(int), cudaMemcpyDeviceToHost);
    
    // Print the result
    //printf("The shortest paths are:\n");
    cout << "The shortest paths are:" << endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            //printf("%d ", h_D[i][j]);
            cout << " " << h_D[i][j];
        }
        //printf("\n");
        cout << endl;
    }
    
    // Free the memory on the device
    cudaFree(d_D);
    
    return 0;
}