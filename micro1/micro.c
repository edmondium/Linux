#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to find and replace occurrences of a pattern in a document
char* find_and_replace(const char* document, const char* pattern, const char* replacement) {
    // Allocate a buffer for the resulting string
    size_t buffer_size = strlen(document) + 1;
    char* result = malloc(buffer_size);
    if (!result) return NULL;
    result[0] = '\0'; // Initialize result as an empty string

    // Variables for navigating the document and finding the pattern
    const char* current = document;
    const char* match;

    while ((match = strstr(current, pattern)) != NULL) {
        // Calculate the size of the buffer needed for replacement
        buffer_size += strlen(replacement) - strlen(pattern);
        result = realloc(result, buffer_size);
        if (!result) return NULL;

        // Copy the part before the match
        strncat(result, current, match - current);
        
        // Append the replacement
        strcat(result, replacement);

        // Move the current pointer beyond the match
        current = match + strlen(pattern);
    }

    // Append the rest of the document
    strcat(result, current);

    return result;
}

int main() {
    // Example Usage
    /*const char* document = "The quick brown fox jumps over the lazy dog.";
    const char* pattern = "quick brown fox";
    const char* replacement = "fast orange";*/
    const char* document = "apple apple orange banana apple orange.";
    const char* pattern = "apple apple orange";
    const char* replacement = "fruit";
    char* modified_document = find_and_replace(document, pattern, replacement);
    if (modified_document) {
        printf("Modified Document: %s\n", modified_document);
        free(modified_document); // Free allocated memory
    } else {
        printf("Memory allocation failed.\n");
    }

    return 0;
}
