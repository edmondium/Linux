/*******************************************************
 * Copyright (c) 2014, ArrayFire
 * All rights reserved.
 *
 * This file is distributed under 3-clause BSD license.
 * The complete license agreement can be obtained at:
 * http://arrayfire.com/licenses/BSD-3-Clause
 ********************************************************/
 
#include <arrayfire.h>
//#include <cstdio>
//#include <cstdlib>
#include  <iostream>
using namespace af;
using namespace std; 
int main(int argc, char* argv[]) {
    try {
        // Select a device and display arrayfire info
        int device = argc > 1 ? atoi(argv[1]) : 0;
        // af::setDevice(device);
        // af::info();
        setDevice(device);
        info(); 
        //printf("Create a 5-by-3 matrix of random floats on the GPU\n");
        cout << "Create a 5-by-3 matrix of random floats on the GPU" << endl;
        //array A = randu(5, 3, f32);
        af::array A = randu(5, 3, f32);
        af_print(A);
 
        //printf("Element-wise arithmetic\n");
        cout << "Element-wise arithmetic" << endl;
        //array B = sin(A) + 1.5;
        af::array B = sin(A) + 1.5;
        af_print(B);
 
        //printf("Negate the first three elements of second column\n");
        cout << "Negate the first three elements of second column" << endl;
        B(seq(0, 2), 1) = B(seq(0, 2), 1) * -1;
        af_print(B);
 
        //printf("Fourier transform the result\n");
        cout << "Fourier transform the result" << endl;
        //array C = fft(B);
        af::array C = fft(B);
        af_print(C);
 
        //printf("Grab last row\n");
        cout << "Grab last row" << endl;
        //array c = C.row(end);
        af::array c = C.row(af::end);
        af_print(c);
 
        //printf("Scan Test\n");
        cout << "Scan Test" << endl;
        dim4 dims(16, 4, 1, 1);
        //array r = constant(2, dims);
        af::array r = constant(2, dims);
        af_print(r);
 
        //printf("Scan\n");
        cout << "Scan" << endl;
        //array S = af::scan(r, 0, AF_BINARY_MUL);
        af::array S = scan(r, 0, AF_BINARY_MUL);
        af_print(S);
 
        //printf("Create 2-by-3 matrix from host data\n");
        cout << "Create 2-by-3 matrix from host data" << endl;
        float d[] = {1, 2, 3, 4, 5, 6};
        //array D(2, 3, d, afHost);
        af::array D(2, 3, d, afHost);
        af_print(D);
 
        //printf("Copy last column onto first\n");
        cout << "Copy last column onto first" << endl;
        //D.col(0) = D.col(end);
        D.col(0) = D.col(af::end);
        af_print(D);
 
        // Sort A
        //printf("Sort A and print sorted array and corresponding indices\n");
        cout << "Sort A and print sorted array and corresponding indices" << endl;
        //array vals, inds;
        af::array vals, inds;
        sort(vals, inds, A);
        af_print(vals);
        af_print(inds);
 
    } catch (af::exception& e) {
        fprintf(stderr, "%s\n", e.what());
        throw;
    }
 
    return 0;
}