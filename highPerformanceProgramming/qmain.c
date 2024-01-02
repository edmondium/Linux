#include <stdio.h>
#include <stdlib.h>

void quicksort(int a[], int n);

int main(int argc, char **argv) {
  int n;
  char *alg = "rand1";
  if( argc == 2 ) {
    n = atoi(argv[1]);
  } else if( argc == 3 ) {
    alg = argv[1];
    n = atoi(argv[2]);
  } else {
    perror( "One or two arguments\n" );
    exit(EXIT_FAILURE);
  }
  unsigned seed = 1;
  if( alg[0] == 'r' ) seed = atoi(alg+4);
  
  int *a = malloc(n * sizeof(int));
  
  if( alg[0] == 'r' ) {
    for( int i = 0; i < n; i++ ) {
      a[i] = rand_r(&seed);
    }
  } else {
    for( int i = 0; i < n; i++ ) {
      a[i] = i;
    }
  }
  
  quicksort(a, n);
  
  int m = a[0];
  for( int i = 1; i < n; i++ ) {
    if( m > a[i] ) {
      printf( "Fail\n" );
      exit(EXIT_FAILURE);
    }
    m = a[i];
  }
  printf( "Success\n" );
}
