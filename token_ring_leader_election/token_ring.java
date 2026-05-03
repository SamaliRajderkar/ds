import java.util.*;

public class token_ring {

    static int n;
    static int[] priority;
    static boolean[] active;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes : ");
        n = sc.nextInt();

        priority = new int[n];
        active = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Priority of Process " + i + " : ");
            priority[i] = sc.nextInt();
            System.out.print("Is Process " + i + " active? (1=y, 0=n) : ");
            active[i] = sc.nextInt() == 1;
        }

        System.out.print("Enter Initial Process : ");
        int ini = sc.nextInt();

        System.out.println("Token starts from Process " + ini);

        int leader = elect(ini);
        System.out.println("Leader process is : " + leader);
    }

    static int elect(int pid) {
    int leader = -1;
    int i = pid;

    while (true) {
        if (active[i]) {
            System.out.println("Process " + i + " passes token");

            if (leader == -1 || priority[i] > priority[leader]) {
                leader = i;
            }
        }

        i = (i + 1) % n;

        if (i == pid) break; // stop after full cycle
    }
    return leader;
}
}
