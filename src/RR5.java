import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RR5 {
    private static class Process {
        int pid; // process id
        int bt; // burst time

        public Process(int pid, int bt) {
            this.pid = pid;
            this.bt = bt;
        }

        public void decrementBt() {
            this.bt -= 5;
        }
    }

    static void findAvgTime(ArrayList<Process> proc) {
        int size = proc.size();
        int timeTracker = 0;
        int totalTurnaround = 0;

        System.out.println("Processes " + " Burst time " + " Remaining process time");
        int pointer = 0;

        // Loop to process in bursts of 5ms, if (process.bt < 5), line 34 handles
        while(proc.size() > 0) {
            Process currentProcess = proc.get(pointer%proc.size());
            currentProcess.decrementBt();
            if(currentProcess.bt <= 0) {
                timeTracker += (5+currentProcess.bt);
                totalTurnaround += timeTracker;
                System.out.println(" " + currentProcess.pid + " FINISHED AT " + timeTracker + "ms");
                proc.remove(pointer%proc.size());
            }
            else {
                timeTracker += 5;
                System.out.println(" " + currentProcess.pid + "\t\t\t" + "5ms" + "\t\t\t" + currentProcess.bt + "ms");
                ++pointer;
            }
        }

        System.out.println("Average turn around time for RR-5 = " + (float)totalTurnaround / (float)size);
    }

    // Driver
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/ryandavidson/IdeaProjects/SchedulingAlgorithms/src/job.txt");

        Scanner scan = new Scanner(file);
        ArrayList<Process> processes = new ArrayList();
        int counter = 1;
        while(scan.hasNextLine()){
            try {
                int x = Integer.parseInt(scan.nextLine());
                processes.add(new Process(counter, x));
                ++counter;
            }
            catch (NumberFormatException e) {}
        }

        findAvgTime(processes);
    }
}
