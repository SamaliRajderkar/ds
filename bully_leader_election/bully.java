import java.util.*;

public class bully {
    static int n;
    static int[] priority;
    static boolean[] active;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        priority = new int[n];
        active = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Is process " + i + " active? (1=Yes, 0=No): ");
            active[i] = sc.nextInt() == 1;
            System.out.print("Priority of process " + i + ": ");
            priority[i] = sc.nextInt();
        }

        System.out.print("Enter initiator process: ");
        int initiator = sc.nextInt();

        if (!active[initiator]) {
            System.out.println("Initiator is down! Choose an active process.");
            return;
        }

        System.out.println("Election Started by Process " + initiator );
        int leader = elect(initiator);
        System.out.println(" Final Leader: Process " + leader);
    }

    static int elect(int pid) {
        System.out.println(" Process " + pid + " starts election.");
        boolean higherFound = false;

        for (int i = 0; i < n; i++) {
            if (priority[i] > priority[pid] && active[i]) {
                System.out.println("Process " + pid + " sends ELECTION to process " + i);
                higherFound = true;
            }
        }

        if (!higherFound) {
            System.out.println("Process " + pid + " has highest priority → becomes LEADER!");
            return pid;
        }
        for (int i = 0; i < n; i++) {
            if (priority[i] > priority[pid] && active[i]) {
                System.out.println("Process " + i + " sends OK to process " + pid);
                System.out.println("Process " + pid + " steps down.");
                return elect(i);
            }
        }
        return pid;
    }
}