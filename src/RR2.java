import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RR2 {
    private static class Process {
        int pid; // process id
        int bt; // burst time

        public Process(int pid, int bt) {
            this.pid = pid;
            this.bt = bt;
        }

        public void decrementBt() {
            this.bt -= 2;
        }
    }

    static void findAvgTime(ArrayList<Process> proc) {
        int size = proc.size();
        int timeTracker = 0;
        int totalTurnaround = 0;

        System.out.println("Processes " + " Burst time " + " Remaining process time");
        int pointer = 0;
        while(proc.size() > 0) {
            Process currentProcess = proc.get(pointer%proc.size());
            currentProcess.decrementBt();
            if(currentProcess.bt == 0) {
                timeTracker += 2;
                totalTurnaround += timeTracker;
                System.out.println(" " + currentProcess.pid + " FINISHED AT " + timeTracker + "ms");
                proc.remove(pointer%proc.size());
            }
            else if(currentProcess.bt == -1) {
                timeTracker += 1;
                totalTurnaround += timeTracker;
                System.out.println(" " + currentProcess.pid + " FINISHED AT " + timeTracker + "ms");
                proc.remove(pointer%proc.size());
            }
            else {
                timeTracker += 2;
                System.out.println(" " + currentProcess.pid + "\t\t\t" + "2ms" + "\t\t\t" + currentProcess.bt + "ms");
                ++pointer;
            }
        }

        System.out.println(totalTurnaround + " " + size);
        System.out.println("Average turn around time for FCFS = " + (float)totalTurnaround / (float)size);
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


