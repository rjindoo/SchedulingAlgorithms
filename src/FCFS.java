import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FCFS {
    private static class Process {
        int pid; // process id
        int bt; // burst time

        public Process(int pid, int bt) {
            this.pid = pid;
            this.bt = bt;
        }
    }

    static void FirstComeFirstServe(Queue<Process> proc) {
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
            System.out.println(" " + (i+1) + "\t\t\t" + currentProcess + "\t\t\t" + turnAroundTimes[i]
                    + "\t\t\t\t" + "Process " + i + " Completed at " + turnAroundTimes[i] + "ms");
        }
        for(int x: turnAroundTimes) {
            total_tat += x;
        }
        System.out.println("Average turn around time for FCFS = " + (float)total_tat / (float)size);
    }

    // Driver
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/ryandavidson/IdeaProjects/SchedulingAlgorithms/src/job.txt");

        Scanner scan = new Scanner(file);
        Queue<Process> processes = new LinkedList();
        int counter = 1;
        while(scan.hasNextLine()){
            try {
                int x = Integer.parseInt(scan.nextLine());
                processes.add(new Process(counter, x));
                ++counter;
            }
            catch (NumberFormatException e) {}
        }
        FirstComeFirstServe(processes);
    }
}


