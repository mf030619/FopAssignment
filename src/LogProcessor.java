import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LogProcessor {
    protected LinkedHashMap<String, Long> Start_time = new LinkedHashMap<>();              //create hashmap
    protected LinkedHashMap<String, Long> End_time = new LinkedHashMap<>();
    protected LinkedHashMap<String, Long> Time_difference = new LinkedHashMap<>();
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    protected Pattern pattern = Pattern.compile("\\[(?<date>\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\]");        // regex for date
    protected Pattern pattern1 = Pattern.compile("Allocate JobId=(\\d+)");                     //regex for allocate resouces #1
    protected Pattern pattern2 = Pattern.compile("JobId=(\\d+)");                             //regex for JobID
    protected Pattern pattern3 = Pattern.compile("done");                                     //regex for job completed
    protected Pattern pattern4 = Pattern.compile("_slurm_rpc_allocate_resources");                // regex for allocate resouces #2// declaring Printwriter object

    public LogProcessor() {                                                              //constructor for empty parameter
    }

    public LinkedHashMap<String, Long> processLog(String fileName) throws IOException, ParseException {           //constructor for read
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = input.readLine()) != null) {
            Matcher q = this.pattern.matcher(line);
            Matcher w = this.pattern1.matcher(line);
            Matcher e = this.pattern2.matcher(line);
            Matcher r = this.pattern3.matcher(line);
            Matcher z = this.pattern4.matcher(line);
            if ((q.find() && w.find())) {
                String date = q.group("date");
                Date Start_date = this.sdf.parse(date);
                Long Start_time_1 = Start_date.getTime();
                String job_ID = w.group(1);
                Start_time.put(job_ID, Start_time_1);
            } else if (z.find() && e.find()) {
                String date = q.group("date");
                Date Start_date = this.sdf.parse(date);
                Long Start_time_1 = Start_date.getTime();
                String job_ID = e.group(1);
                this.Start_time.put(job_ID, Start_time_1);
            } else if (e.find() && r.find()) {
                String date = q.group("date");
                Date End_date = sdf.parse(date);
                Long End_time_1 = End_date.getTime();
                String job_ID = e.group(1);
                End_time.put(job_ID,End_time_1);
            }
        }
        for (String key : Start_time.keySet()) {
            if (this.End_time.containsKey(key)) {
                this.Time_difference.put(key, this.End_time.get(key) - this.Start_time.get(key));
                System.out.println(this.Time_difference.get(key) +"\t" + key);
            } else {
                continue;
            }
        }
        return this.Time_difference;
    }

    public static void main(String[] args) throws IOException, ParseException {
        LogProcessor x = new LogProcessor();
        LinkedHashMap<String, Long> quer = x.processLog("C:/Users/User/Desktop/FOP assignment/extracted_log (1)");
        for(String key: quer.keySet()){
            System.out.println(quer.get(key));
        }


        }



    }
