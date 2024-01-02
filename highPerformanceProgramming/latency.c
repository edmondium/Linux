#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void shuffle(void **buffer, size_t blocks, size_t pointers_per_block) {
    size_t *indices = malloc(blocks * sizeof(size_t));
    if (!indices) {
        perror("malloc");
        return;
    }

    for (size_t i = 0; i < blocks; i++) {
        indices[i] = i;
    }

    for (size_t i = blocks - 1; i > 0; i--) {
        size_t j = rand() % (i + 1);
        size_t temp = indices[i];
        indices[i] = indices[j];
        indices[j] = temp;
    }

    for (size_t i = 0; i < blocks; i++) {
        size_t next_index = indices[(i + 1) % blocks];
        buffer[indices[i] * pointers_per_block] = &buffer[next_index * pointers_per_block];
    }

    free(indices);
}

int main(int argc, char **argv) {
    if (argc != 5) {
        fprintf(stderr, "Usage: %s <buffer size> <order> <pointers per block> <accesses>\n", argv[0]);
        return 1;
    }

    size_t buffer_size = atoll(argv[1]) * 1024;
    char *order = argv[2];
    size_t pointers_per_block = atoll(argv[3]);
    size_t accesses = atoll(argv[4]) * 1000000;

    size_t blocks = buffer_size / (pointers_per_block * sizeof(void *));
    void **buffer = malloc(blocks * pointers_per_block * sizeof(void *));
    if (!buffer) {
        perror("malloc");
        return 1;
    }

    for (size_t i = 0; i < blocks; i++) {
        buffer[i * pointers_per_block] = &buffer[((i + 1) % blocks) * pointers_per_block];
    }

    if (strcmp(order, "random") == 0) {
        shuffle(buffer, blocks, pointers_per_block);
    }

    size_t traversals = (accesses + blocks - 1) / blocks;
    void **p = &buffer[0];

    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);

    for (size_t i = 0; i < traversals; i++) {
        for (size_t j = 0; j < blocks; j++) {
            p = (void **)*p;
        }
    }

    clock_gettime(CLOCK_MONOTONIC, &end);
    long long elapsed = (end.tv_sec - start.tv_sec) * 1000000000 + (end.tv_nsec - start.tv_nsec);

    printf("%.2f ns/access\n", (double)elapsed / accesses);
    printf("final p = %p\n", p);

    free(buffer);
    return 0;
}
