// Design a distributed application that generate a random array of numbers on the root
// process (process 0) using Java Message Passing Interface (MPI), Scatter the numbers
// to all processes, giving each process an equal amount of numbers. Each process
// computes the average of their subset of the numbers. Gather all averages to the root
// process. The root process then computes the average of these numbers to get the final
// average.
import mpi.*;

public class avg {
    public static void main(String[] args) {

        MPI.Init(args);

        int size = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();
        int chunk = 2;

        int[] data = new int[size * chunk];
        int[] recv = new int[chunk];
        double[] res = new double[size];
        double[] temp = new double[1];

        // Root initializes data
        if (rank == 0) {
            System.out.println("Initialized Array:");
            for (int i = 0; i < data.length; i++) {
                data[i] = i+1;
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        // Scatter (1 element per process)
        MPI.COMM_WORLD.Scatter(data, 0, chunk, MPI.INT,
                               recv, 0, chunk, MPI.INT, 0);

        double sum =0;
        for(int i =0; i<chunk; i++){
            sum += recv[i];
        }
        temp[0] = sum/chunk;
    

        // Gather all values
        MPI.COMM_WORLD.Allgather(temp, 0, 1, MPI.DOUBLE,
                                res, 0, 1, MPI.DOUBLE);

        // Root computes final average
        if (rank == 0) {
            double tot = 0;
            for (int i = 0; i < size; i++) {
                tot += res[i];
            }
            System.out.println("Final Average: " + (tot / size));
        }

        MPI.Finalize();
    }
}

// samalirajderkar@Samalis-MacBook-Air mpi_avg % javac -cp .:/Users/samalirajderkar/mpj-v0_44/lib/mpj.jar avg.java
// samalirajderkar@Samalis-MacBook-Air mpi_avg % /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh -np 4 avg
