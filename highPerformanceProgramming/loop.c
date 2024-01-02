#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdint.h>

typedef uint64_t __attribute__((vector_size(32))) v4u64_t;

v4u64_t loop(v4u64_t *buffer, size_t traversals, size_t blocks, size_t pointers_per_block) {
    v4u64_t v0 = {0, 0, 0, 0}, v1 = {0, 0, 0, 0};
    v4u64_t *b0 = buffer;
    v4u64_t *b1 = buffer + pointers_per_block;
    v4u64_t *b2 = buffer + 2*pointers_per_block;
    v4u64_t *b3 = buffer + 3*pointers_per_block;
    size_t pt2 = 2*pointers_per_block;
    size_t pt3 = 3*pointers_per_block;
    for (size_t i = 0; i < traversals; i++) {
        for (size_t j = 0; j < blocks*pointers_per_block; j += 4*pointers_per_block) {
            v0 ^= b0[j];
            v1 ^= b1[j];
            v0 ^= b2[j];
            v1 ^= b3[j];
        }
    }
    return v0 ^ v1;
}
