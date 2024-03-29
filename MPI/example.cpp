#include <mpi.h>
#include <iostream>
using namespace std;
int main(int argc, char *argv[])
{
    int numtasks, rank, rc;
    rc = MPI_Init(&argc, &argv);
    if (rc != MPI_SUCCESS)
    {
        cout << "Error starting MPI program. Terminating." << endl;
        MPI_Abort(MPI_COMM_WORLD, rc);
    }
    MPI_Comm_size(MPI_COMM_WORLD, &numtasks);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    cout << "Number of tasks = " << numtasks << ", My rank = " << rank << endl;
    MPI_Finalize();
}