import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class JobWriter {

    private static int numberOfJobs;

    public JobWriter(int numberOfJobs) {
        if(numberOfJobs > 30) {
            numberOfJobs = 30;
        }
        else {
            this.numberOfJobs = numberOfJobs;
        }
    }

    public void createJobs() {
        try {
            Random rand = new Random();
            FileWriter jobs = new FileWriter("/Users/ryandavidson/IdeaProjects/SchedulingAlgorithms/src/job.txt");
            for(int i=0; i<numberOfJobs; i++) {
                jobs.write("Job" + (i+1) + "\n");
                jobs.write(String.valueOf(rand.nextInt(50)) + "\n");
            }
            jobs.close();
        }
        catch(IOException e) {
            System.out.println("Error");
        }
    }
}
