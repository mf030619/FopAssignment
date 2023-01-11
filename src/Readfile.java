import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Readfile {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("C:/Users/User/Desktop/FOP assignment/extracted_log (1)"));
        String line = input.readLine(); //read log file line per line
        Pattern pattern = Pattern.compile("\\[(?<date>\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\]"); //regex
        PrintWriter writer = new PrintWriter(new File("filelog.txt"));//Create a file to write
        writer.println("Date:\t\t Time:");


        while (line != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String timestamp = matcher.group("date"); //extract group date
                String date = timestamp.substring(0, 10);
                String time = timestamp.substring(11, 23);



            }


            line = input.readLine();
        }

    }




}
