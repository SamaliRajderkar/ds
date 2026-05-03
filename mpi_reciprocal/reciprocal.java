// Design a distributed application using MPI for computation where root process has
// an array of elements equal to the size of processors which is divided to the worker
// processes which calculates the reciprocal and resultant array will be displayed at root.

import mpi.*;

public class reciprocal {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] data = new int[size];
        int[] recv = new int[1];
        double[] result = new double[size];
        double[] temp = new double[1];

        // Root initializes data
        if (rank == 0) {
            for (int i = 0; i < size; i++) {
                data[i] = (i + 1) * 10;
            }
        }

        // Distribute one element to each process
        MPI.COMM_WORLD.Scatter(data, 0, 1, MPI.INT,
                               recv, 0, 1, MPI.INT, 0);

        // Compute reciprocal
        temp[0] = 1.0 / recv[0];

        // Gather results at root
        MPI.COMM_WORLD.Gather(temp, 0, 1, MPI.DOUBLE,
                              result, 0, 1, MPI.DOUBLE, 0);

        // Print final result at root
        if (rank == 0) {
            for (int i = 0; i < size; i++) {
                System.out.print(result[i] + " ");
            }
        }

        MPI.Finalize();
    }
}

// samalirajderkar@Samalis-MacBook-Air mpi_reciprocal % javac -cp .:/Users/samalirajderkar/mpj-v0_44/lib/mpj.jar reciprocal.java
// samalirajderkar@Samalis-MacBook-Air mpi_reciprocal % /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh -np 4 reciprocal         
 
