// Design a distributed application using MPI for computation where root process has
// an array of elements equal to the size of processors which is divided to the worker
// processes which calculates the reciprocal and resultant array will be displayed at root.

import mpi.*;

public class reciprocal {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[]    data        = new int[size];
        int[]    recv_buf    = new int[1];
        double[] local_recip = new double[1];
        double[] result      = new double[size];   // gathered at root

        if (rank == 0) {
            System.out.println("Root process initialising array:");
            for (int i = 0; i < size; i++) {
                data[i] = (i + 1) * 10;           // 10, 20, 30, 40
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        // Scatter one element per process
        MPI.COMM_WORLD.Scatter(data, 0, 1, MPI.INT,
                               recv_buf, 0, 1, MPI.INT, 0);

        int val = recv_buf[0];

        // Each process computes reciprocal
        local_recip[0] = 1.0 / val;
        System.out.println("Process " + rank + " | Value: " + val
                         + " | Reciprocal: " + local_recip[0]);

        // Gather all reciprocals back to root
        MPI.COMM_WORLD.Gather(local_recip, 0, 1, MPI.DOUBLE,
                              result,      0, 1, MPI.DOUBLE, 0);

        // Root displays the final resultant array
        if (rank == 0) {
            System.out.println("\nResultant reciprocal array at root:");
            for (int i = 0; i < size; i++) {
                System.out.printf("1/%d = %.4f%n", data[i], result[i]);
            }
        }

        MPI.Finalize();
    }
}

// samalirajderkar@Samalis-MacBook-Air mpi_reciprocal % javac -cp .:/Users/samalirajderkar/mpj-v0_44/lib/mpj.jar reciprocal.java
// samalirajderkar@Samalis-MacBook-Air mpi_reciprocal % /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh -np 4 reciprocal         
// MPJ Express (0.44) is started in the multicore configuration
// Root process initialising array:
// 10 20 30 40 
// Process 0 | Value: 10 | Reciprocal: 0.1
// Process 2 | Value: 30 | Reciprocal: 0.03333333333333333
// Process 3 | Value: 40 | Reciprocal: 0.025
// Process 1 | Value: 20 | Reciprocal: 0.05
// Resultant reciprocal array at root:
// 1/10 = 0.1000
// 1/20 = 0.0500
// 1/30 = 0.0333
// 1/40 = 0.0250
// samalirajderkar@Samalis-MacBook-Air mpi_reciprocal % 
