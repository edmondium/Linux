#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdint.h>

typedef uint64_t __attribute__((vector_size(32))) v4u64_t;

v4u64_t loop(v4u64_t *buf, size_t reps, size_t blocks, size_t block_size);

int main(int argc, char **argv) {
    if (argc != 5) {
        fprintf(stderr, "Usage: %s <buffer size> <order> <pointers per block> <accesses>\n", argv[0]);
        return 1;
    }

    size_t buffer_size = atoll(argv[1]) * 1024;
    char *order = argv[2];
    size_t pointers_per_block = atoll(argv[3]);
    size_t accesses = atoll(argv[4]) * 1000000;

    size_t   blocks = buffer_size / (pointers_per_block * sizeof(v4u64_t));
    v4u64_t *buffer;
     
    if (posix_memalign((void**)&buffer, 64, blocks * pointers_per_block * sizeof(v4u64_t))) {
        perror("malloc");
        return 1;
    }
    v4u64_t g = {1, 2, 3, 4}, d = g;
    for( size_t i = 0; i < blocks; i++ ) {
      buffer[i*pointers_per_block] = d;
      d += g;
    }

    size_t traversals = (accesses + blocks - 1) / blocks;
    size_t real_accesses = traversals * blocks;

    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);

    v4u64_t v0 = loop(buffer, traversals, blocks, pointers_per_block);
    
    clock_gettime(CLOCK_MONOTONIC, &end);
    long long elapsed = (end.tv_sec - start.tv_sec) * 1000000000 + (end.tv_nsec - start.tv_nsec);

    printf("%.2f ns/access\n", (double)elapsed / real_accesses);
    printf("final val = %lu\n", v0[0]^v0[1]^v0[2]^v0[3]);

    free(buffer);
    return 0;
}
