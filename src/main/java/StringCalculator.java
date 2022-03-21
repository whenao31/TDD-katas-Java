//import org.omg.CORBA.SystemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String values) throws Exception { //"1,1"
        if(values.length() > 0) {
            int temp;
            String delimiter = null;
            String[] delimitersArr = new String[1];
            try {
                temp = Integer.parseInt("" + values.charAt(0));
                delimitersArr[0] = null;
            } catch(Exception e) {
                if(("" + values.charAt(0)) == "-") {
                    delimiter = null;
                    delimitersArr[0] = null;
                } else if (values.charAt(0) == '['){
                    delimiter = values.substring(1, values.indexOf(']'));
                    String subStringLast = values.substring(0, values.lastIndexOf(']'));
                    delimitersArr = subStringLast.replace('[', ' ').replace(']', ' ').trim().replaceAll("\\s+", ";").split(";");
                    Arrays.sort(delimitersArr, Collections.reverseOrder());
                } else {
                    delimiter = "" + values.charAt(0);
                    delimitersArr[0] = "" + values.charAt(0);
                }
            }

            String[] splittedList2 = null;
            if((delimiter != null && delimiter.length() == 1) && (delimitersArr.length == 1 && delimitersArr[0] != null)) {
                splittedList2 = values.substring(1, values.length()).split(delimitersArr[0]);
            } else if ((delimiter != null && delimiter.length() > 1) || (delimitersArr[0] != null && delimitersArr.length > 1)){
                List<String> delimiterList = Arrays.asList(delimitersArr);
                for (String del: delimiterList){
                    values = values.replaceAll(del, "_");
                }
                splittedList2 = values.substring(values.lastIndexOf(']') + 1, values.length()).split("_");
            }
            else {
                splittedList2 = values.split("[,|\n]");
            }

            ArrayList<Integer> numberList = new ArrayList<Integer>();
            int accumulator = 0;
            for(String element: splittedList2) {
                int tempValue = Integer.parseInt(element);
                if(tempValue < 0) {
                    throw new Exception("NegativeNumberException");
                }
                if(tempValue > 1000) {
                    continue;
                }
                numberList.add(tempValue);
            }
            for(Integer number: numberList) {
                accumulator += number;
            }
            return accumulator;
        }
        return 0;
    }
}