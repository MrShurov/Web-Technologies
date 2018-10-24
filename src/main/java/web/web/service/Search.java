package web.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static web.web.service.TextParser.parseString;

public class Search {

    public static void search(String searchString){
        List<String> result = new ArrayList<>();
        List<String> list = parseString("Привет ававы ");
        String[] searchArray = searchString.replaceAll("[^а-яА-Я\\sa-zA-Z]", "").toLowerCase().split("\\s+");
        List<String> searchList = new ArrayList<>(Arrays.asList(searchArray));
        for (String aSearchList : searchList) {
            if (aSearchList.length() > 15) {
                result.add(aSearchList.substring(3, aSearchList.length() - 3));
            } else if (aSearchList.length() > 10) {
                result.add(aSearchList.substring(0, aSearchList.length() - 3));
            } else if (aSearchList.length() > 5) {
                result.add(aSearchList.substring(0, aSearchList.length() - 2));
            } else {
                result.add(aSearchList);
            }
        }
        for (String aResult : result) {
            Pattern pattern = Pattern.compile("\\W*" + aResult + "\\W*");
            for (String aList : list) {
                Matcher matcher = pattern.matcher(aList);
                boolean found = matcher.matches();
                if (found)
                    System.out.println(aList);
            }
        }
    }
}
