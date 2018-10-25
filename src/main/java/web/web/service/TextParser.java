package web.web.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import web.web.dao.jpa.Site;
import web.web.dao.jpa.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {

    private static final Logger LOGGER = LogManager.getLogger(TextParser.class);

    public static List<Word> parseSite(Site site) {
        try {
            Document doc = Jsoup.connect(site.getUrl()).get();
            List<String> unnecessary = Arrays.asList("в", "без", "до", "из", "к", "на", "по", "о", "от", "перед", "при", "через", "с", "у", "за", "над", "об", "под", "про", "для", "я", "мы", "ты",
                    "вы", "он", "она", "оно", "они", "себя", "мой", "моя", "мое", "мои", "наш", "наша", "наше", "наши", "твой", "твоя", "твое", "твои", "ваш", "ваша", "ваше", "ваши", "его", "ее",
                    "их", "кто", "что", "какой", "каков", "чей", "который", "сколько", "где", "когда", "куда", "откуда", "зачем", "столько", "этот", "тот", "такой", "таков", "тут", "здесь", "сюда",
                    "туда", "оттуда", "отсюда", "тогда", "поэтому", "затем", "весь", "всякий", "все", "сам", "самый", "каждый", "любой", "другой", "иной", "всяческий", "всюду", "везде", "всегда",
                    "никто", "ничто", "некого", "нечего", "никакой", "ничей", "некто", "нечто", "некий", "некоторый", "несколько", "кое-кто", "кое-где", "кое-что", "кое-куда", "какой-либо", "сколько-нибудь",
                    "куда-нибудь", "зачем-нибудь", "чей-либо", "и", "а", "но", "да", "или", "либо", "ни–ни", "то–то", "как", "не", "этой", "почему", "также", "меня");
            String[] subStr = doc.text().replaceAll("[^а-яА-Я\\sa-zA-Z]", "").toLowerCase().split("\\s+");
            List<Word> list = new ArrayList<Word>();
            for (String aSubStr : subStr) {
                list.add(new Word(aSubStr,site));
            }
            for (String aUnnecessary : unnecessary) {
                list.removeIf(s -> s.getWord().equals(aUnnecessary));
            }
            return list;
        } catch (IOException e) {
            LOGGER.error("SMTH GO WRONG!!!");
            return null;
        }
    }
}
