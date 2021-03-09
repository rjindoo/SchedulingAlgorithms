import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SJF {
    private static class Process implements Comparable<Process> {
        int pid; // process id
        int bt; // burst time

        public Process(int pid, int bt) {
            this.pid = pid;
            this.bt = bt;
        }

        @Override
        public int compareTo(Process other) {
            if(this.bt < other.bt) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }


    private static void findavgTime(Queue<Process> proc) {
        int size = proc.size();
        int turnAroundTimes[] = new int[size];
        int total_tat = 0;
        /**
         * Display turn around time for each process to console
         **/
        System.out.println("Processes " + " Burst time " + " Turn around time");
        for (int i = 0; i < size; i++) {
            int currentProcess = proc.poll().bt;
            if(i != 0) {
                turnAroundTimes[i] = currentProcess + turnAroundTimes[i - 1];
            }
            else {
                turnAroundTimes[i] = currentProcess;
            }
            System.out.println(" " + (i+1) + "\t\t\t" + currentProcess + "\t\t\t" + turnAroundTimes[i] +
                    "\t\t\t\t" + "Process " + i + " Completed at " + turnAroundTimes[i] + "ms");
        }
        for(int x: turnAroundTimes) {
            total_tat += x;
        }
        System.out.println("Average turn around time for SJF = " + (float)total_tat / (float)size);
    }

    // Driver Method
    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("/Users/ryandavidson/IdeaProjects/SchedulingAlgorithms/src/job.txt");

        Scanner scan = new Scanner(file);
        Collection<Integer> shortestFirstQueue = new ArrayList();
        ArrayList<Integer> list = new ArrayList<>();
        while(scan.hasNextLine()){
            try {
                int x = Integer.parseInt(scan.nextLine());
                list.add(x);
            }
            catch (NumberFormatException e) {}
        }

        Collections.sort(list);
        PriorityQueue<Process> processes = new PriorityQueue();

        for(int i=0; i<list.size(); i++) {
            processes.add(new Process(i+1, list.get(i)));
        }

        findavgTime(processes);
    }
}
