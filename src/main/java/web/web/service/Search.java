package web.web.service;

import web.web.dao.jpa.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    public static List<Word> search(String searchString, List<Word> words) {
        List<String> searchWords = new ArrayList<>();
        List<Word> result = new ArrayList<>();
        String[] searchArray = searchString.replaceAll("[^а-яА-Я\\sa-zA-Z]", "").toLowerCase().split("\\s+");
        List<String> searchList = new ArrayList<>(Arrays.asList(searchArray));
        for (String aSearchList : searchList) {
            if (aSearchList.length() > 15) {
                searchWords.add(aSearchList.substring(3, aSearchList.length() - 3));
            } else if (aSearchList.length() > 10) {
                searchWords.add(aSearchList.substring(0, aSearchList.length() - 3));
            } else if (aSearchList.length() > 5) {
                searchWords.add(aSearchList.substring(0, aSearchList.length() - 2));
            } else {
                searchWords.add(aSearchList);
            }
        }
        for (String aSearchWords : searchWords) {
            Pattern pattern = Pattern.compile("\\W*" + aSearchWords + "\\W*");
            for (Word aList : words) {
                Matcher matcher = pattern.matcher(aList.getWord());
                boolean found = matcher.lookingAt();
                if (found) {
                    result.add(aList);
                }
            }
        }
        return result;
    }
}
