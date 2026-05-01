// Design a distributed application that generate a random array of numbers on the root
// process (process 0) using Java Message Passing Interface (MPI), Scatter the numbers
// to all processes, giving each process an equal amount of numbers. Each process
// computes the average of their subset of the numbers. Gather all averages to the root
// process. The root process then computes the average of these numbers to get the final
// average.

import mpi.*;
import java.util.Random;

public class avg {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int totalElements = size * 4;          // 4 numbers per process
        int chunkSize     = totalElements / size;

        int[]    data         = new int[totalElements];
        int[]    recv_chunk   = new int[chunkSize];
        double[] local_avg    = new double[1];
        double[] all_avgs     = new double[size];

        // Root generates random array
        if (rank == 0) {
            Random rand = new Random(42);
            System.out.println("Root generated array:");
            for (int i = 0; i < totalElements; i++) {
                data[i] = rand.nextInt(100) + 1;   // 1–100
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        // Scatter equal chunks to each process
        MPI.COMM_WORLD.Scatter(data, 0, chunkSize, MPI.INT,
                               recv_chunk, 0, chunkSize, MPI.INT, 0);

        // Each process computes local average
        double sum = 0;
        for (int x : recv_chunk) sum += x;
        local_avg[0] = sum / chunkSize;

        System.out.println("Process " + rank + " | Chunk avg: " + local_avg[0]);

        // Gather all local averages to root
        MPI.COMM_WORLD.Gather(local_avg, 0, 1, MPI.DOUBLE,
                              all_avgs,  0, 1, MPI.DOUBLE, 0);

        // Root computes final average of averages
        if (rank == 0) {
            double finalAvg = 0;
            for (double a : all_avgs) finalAvg += a;
            finalAvg /= size;
            System.out.println("\nFinal average computed at root: " + finalAvg);
        }

        MPI.Finalize();
    }
}

// samalirajderkar@Samalis-MacBook-Air mpi_avg % javac -cp .:/Users/samalirajderkar/mpj-v0_44/lib/mpj.jar avg.java
// samalirajderkar@Samalis-MacBook-Air mpi_avg % /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh -np 4 avg
// MPJ Express (0.44) is started in the multicore configuration
// Root generated array:
// 31 64 49 85 71 26 6 19 20 94 83 3 77 93 77 33 
// Process 2 | Chunk avg: 50.0
// Process 0 | Chunk avg: 57.25
// Process 1 | Chunk avg: 30.5
// Process 3 | Chunk avg: 70.0
// Final average computed at root: 51.9375

