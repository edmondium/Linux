#include <pthread.h>
#include <iostream>
#define NUM_THREADS 5
using namespace std;
void *PrintHello(void *threadId)
{
    int *data = static_cast <int*> (threadId);
    cout << "Hello World! It's me, thread #" << *data << "!" << endl;
    pthread_exit(NULL);
}
int main(int argc, char *argv[])
{
    pthread_t threads[NUM_THREADS];
    int tids[NUM_THREADS];
    for (int i = 0; i < NUM_THREADS; i++)
    {
        tids[i] = i;
        pthread_create(&threads[i], NULL, PrintHello, (void*)&tids[i]);
    }
    /* Last thing that main() should do */
    pthread_exit(NULL);
}