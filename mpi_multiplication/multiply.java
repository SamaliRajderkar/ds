import mpi.*;

public class multiply{
    public static void main(String[] args) throws Exception{

        MPI.Init(args);

        int size = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();

        int[] data = new int[size];
        int[] recv_buffer = new int[1];
        int[] pmul = new int[size];

        if(rank == 0){
            System.out.println("Array : ");
            for(int i =0; i<size; i++){
                data[i] = (i+1)*10;
                System.out.print(data[i] + " ");
            }

            System.out.println();
        }

        MPI.COMM_WORLD.Scatter(data, 0, 1, MPI.INT, recv_buffer, 0, 1, MPI.INT, 0);

        int val = recv_buffer[0];

        MPI.COMM_WORLD.Allgather(recv_buffer, 0, 1, MPI.INT,pmul, 0, 1, MPI.INT);

        int mul = 1;

        for (int i=0; i<=rank; i++){
            mul *= pmul[i];
        }

        System.out.println("Process at rank "+ rank + " received value "+ val+ " and multiplication is : "+ mul);

        MPI.Finalize();
    }
}

// samalirajderkar@Samalis-MacBook-Air mpi_multiplication % javac -cp .:/Users/samalirajderkar/mpj-v0_44/lib/mpj.jar  multiply.java
// samalirajderkar@Samalis-MacBook-Air mpi_multiplication % /Users/samalirajderkar/mpj-v0_44/bin/mpjrun.sh -np 4 multiply          
// MPJ Express (0.44) is started in the multicore configuration
// Array : 
// 10 20 30 40 
// Process at rank 3 received value 40 and multiplication is : 240000
// Process at rank 1 received value 20 and multiplication is : 200
// Process at rank 2 received value 30 and multiplication is : 6000
// Process at rank 0 received value 10 and multiplication is : 10
// samalirajderkar@Samalis-MacBook-Air mpi_multiplication % 
