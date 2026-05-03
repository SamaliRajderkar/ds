// Design a distributed application that generate a random array of numbers on the root
// process (process 0) using Java Message Passing Interface (MPI), Scatter the numbers
// to all processes, giving each process an equal amount of numbers. Each process
// computes the average of their subset of the numbers. Gather all averages to the root
// process. The root process then computes the average of these numbers to get the final
// average.

import mpi.*;

public class avg {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int chunk = 2;
        int[] recv = new int[chunk];
        double[] localAvg = new double[1];
        double[] allAvg = new double[size];

        int[] data = new int[size * chunk]; 

        // Root process initializes data
        if (rank == 0) {
            data = new int[size * chunk];
            for (int i = 0; i < data.length; i++) {
                data[i] = (i + 1) * 10;
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        // Distribute data
        MPI.COMM_WORLD.Scatter(data, 0, chunk, MPI.INT,
                               recv, 0, chunk, MPI.INT, 0);

        // Compute local average
        double sum = 0;
        for (int i = 0; i < chunk; i++) {
            sum += recv[i];
        }
        localAvg[0] = sum / chunk;

        // Collect all local averages
        MPI.COMM_WORLD.Gather(localAvg, 0, 1, MPI.DOUBLE,
                              allAvg, 0, 1, MPI.DOUBLE, 0);

        // Final average at root
        if (rank == 0) {
            double total = 0;
            for (int i = 0; i < size; i++) {
                total += allAvg[i];
            }
            System.out.println("Final average: " + total / size);
        }

        MPI.Finalize();
    }
}

// samalirajderkar@Samalis-MacBook-Air mpi_avg % javac -cp .:/Users/samalirajderkar/mpj-v0_44/lib/mpj.jar avg.java
// samalirajderkar@Samalis-MacBook-Air mpi_avg % /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh -np 4 avg
