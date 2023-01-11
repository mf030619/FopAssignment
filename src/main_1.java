import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class main_1 {
    public static void main(String[] args) {
        String logMessage = "[2022-06-01T15:30:18.119] error: This association 201(account='free', user='liew.wei.shiung', partition='(null)') does not have access to qos long";
        String pattern = "(does not have access to qos .*)"; //error
        String pattern_user = "(user=')(.*)(', partition=')"; //user
        Pattern r = Pattern.compile(pattern); //error
        Pattern q = Pattern.compile(pattern_user); // user
        Matcher m = r.matcher(logMessage);  //error
        Matcher t = q.matcher(logMessage); //user
        if (m.find() && t.find()) {
            String error = m.group(1);
            String user = t.group(2);
            System.out.println(error + user );

    }
}}
