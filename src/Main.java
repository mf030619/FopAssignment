import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String range = "2022-06-01 to 2022-12-16";
        LocalDate startDate = LocalDate.parse(range.split("to")[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(range.split("to")[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        int day_Count = 0;
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)){
            day_Count++;
        }
        System.out.println(day_Count);
    }

}