//import org.omg.CORBA.SystemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringCalculator {
    public int add(String values) throws Exception { //"1,1"
        if(values.length() > 0) {

            String[] delimitersArr = buildDelimiterArray(values);

            String[] splittedList = generateOperandList(values, delimitersArr);

            ArrayList<Integer> numberList = generateNumberList(splittedList);

            int accumulator = 0;
            for(Integer number: numberList) {
                accumulator += number;
            }
            return accumulator;
        }
        return 0;
    }

    private ArrayList<Integer> generateNumberList(String[] splittedList) throws Exception{
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        for(String element: splittedList) {
            int tempValue = Integer.parseInt(element);
            if(tempValue < 0) {
                throw new Exception("NegativeNumberException");
            }
            if(tempValue > 1000) {
                continue;
            }
            numberList.add(tempValue);
        }
        return numberList;
    }

    private String[] generateOperandList(String values, String[] delimitersArr) {
        String[] splittedList = null;
        if(delimitersArr.length == 1 && delimitersArr[0] != null && delimitersArr[0].length() == 1) {
            return splittedList = values.substring(1, values.length()).split(delimitersArr[0]);
        }
        if (delimitersArr[0] != null && (delimitersArr.length > 1 || delimitersArr[0].length() > 1)){
            List<String> delimiterList = Arrays.asList(delimitersArr);
            for (String del: delimiterList){
                values = values.replaceAll(del, "_");
            }
            return splittedList = values.substring(values.lastIndexOf(']') + 1, values.length()).split("_");
        }
        return splittedList = values.split("[,|\n]");
    }

    private String[] buildDelimiterArray(String values) {
        int temp;
        String[] delimitersArr = new String[1];
        try {
            temp = Integer.parseInt("" + values.charAt(0));
            delimitersArr[0] = null;
            return delimitersArr;
        } catch(Exception e) {
            if(("" + values.charAt(0)) == "-") {
                delimitersArr[0] = null;
                return delimitersArr;
            }
            if (values.charAt(0) == '['){
                String subStringLast = values.substring(0, values.lastIndexOf(']'));
                delimitersArr = subStringLast
                        .replace('[', ' ')
                        .replace(']', ' ')
                        .trim()
                        .replaceAll("\\s+", ";")
                        .split(";");
                Arrays.sort(delimitersArr, Collections.reverseOrder());
                return delimitersArr;
            }
            delimitersArr[0] = "" + values.charAt(0);
        }
        return delimitersArr;
    }
}