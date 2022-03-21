import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CustomTest {
    public static void main(String[] args) {
        String values = "[#][###][!!]2$$$$1000";
        String delimiter = "";
        String first = "" + values.charAt(0);
        String subStringLast = values.substring(0, values.lastIndexOf(']'));
        String[] delimitersList = null;
        delimitersList = subStringLast.replace('[', ' ').replace(']', ' ').trim().replaceAll("\\s+", ";").split(";");
        Arrays.sort(delimitersList, Collections.reverseOrder());
        for (int i = 0; i < delimitersList.length; i++) {
            System.out.println(delimitersList[i]);
        }
        if ( first.equals("[")){
            delimiter = values.substring(1, values.indexOf(']'));
        }
        String[] splittedList = null;
        String subValues = values.substring(values.indexOf(']') + 1, values.length());
        splittedList = subValues.split(delimiter);
        System.out.println(splittedList);


    }
}
