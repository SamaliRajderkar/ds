/*
Design a distributed application using MPI for computation where root process
has an array of elements equal to the size of processors and distributed to the
worker processes which calculates and displays the intermediate sums
calculated at different processors. Perform these operations on 2 different
machines.
*/

import mpi.*;

public class array_sum {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int unit = 5, root = 0;

        int[] send = new int[unit * size];   // ✅ FIXED
        int[] recv = new int[unit];
        int[] gathered = new int[size];

        if (rank == root) {
            for (int i = 0; i < send.length; i++) {
                send[i] = i;
            }
        }

        MPI.COMM_WORLD.Scatter(send, 0, unit, MPI.INT,
                               recv, 0, unit, MPI.INT, root);

        int sum = 0;
        for (int i = 0; i < unit; i++) sum += recv[i];

        System.out.println("Process " + rank + " sum = " + sum);

        recv[0] = sum;

        MPI.COMM_WORLD.Gather(recv, 0, 1, MPI.INT,
                              gathered, 0, 1, MPI.INT, root);

        if (rank == root) {
            int total = 0;
            for (int i = 0; i < size; i++) total += gathered[i];
            System.out.println("Final Sum = " + total);
        }

        MPI.Finalize();
    }
}

// College : which mpjrun.sh
// u see : /usr/bin/mpjrun.sh

// javac -cp /path/to/mpj.jar array_sum.java
// mpjrun.sh -np 4 -cp . array_sum