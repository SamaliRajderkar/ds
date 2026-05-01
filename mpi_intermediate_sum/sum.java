// Design a distributed application using MPI for computation where root process
// has an array of elements equal to the size of processors and distributed to the
// worker processes which calculates and displays the intermediate sums
// calculated at different processors. Perform these operations on 2 different
// machines.

import mpi.*;

public class sum{
    public static void main(String[] args) throws Exception{
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] data = new int[size];
        int[] recv_buf = new int[1];
        int[] prefix_sum = new int[size];

        if(rank == 0){
            System.out.println("Root process initialising array : ");
            for(int i=0; i<size; i++){
                data[i] = (i+1)*10;
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        MPI.COMM_WORLD.Scatter(data, 0, 1, MPI.INT, recv_buf, 0, 1, MPI.INT, 0);

        int val = recv_buf[0];

        MPI.COMM_WORLD.Allgather(recv_buf, 0, 1, MPI.INT, prefix_sum, 0, 1, MPI.INT);

        int int_sum = 0;

        for(int i=0; i<=rank; i++){
            int_sum += prefix_sum[i];
        }

        System.out.println("Process rank "+ rank + " received value "+ val + " and computed intermediate sum : "+ int_sum);

        MPI.Finalize();
    }
}

// samalirajderkar@Samalis-MacBook-Air ~ % which mpjrun.sh
// /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh

// samalirajderkar@Samalis-MacBook-Air mpi_intermediate_sum % javac -cp .:/Users/samalirajderkar/mpj-v0_44/lib/mpj.jar sum.java
// samalirajderkar@Samalis-MacBook-Air mpi_intermediate_sum % /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh -np 4 sum

