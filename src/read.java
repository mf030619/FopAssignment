import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class read {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("C:/Users/User/Desktop/FOP assignment/extracted_log (1)"));
        String line = input.readLine();
        int sum_workdone = 0; //inititialise
        while(line != null){
            String [] array = line.split(" ");
            for(int i = 0; i < array.length; i++){
                if(array[i].contains("done")){
                    sum_workdone++;
                }
            }
            line = input.readLine();
        }
        System.out.print(sum_workdone);
    }
}
