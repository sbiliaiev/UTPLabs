package entities.person;

import java.util.HashMap;
import java.util.Locale;


public class NationalityLocaleMap {
    private static final HashMap<Nationality, Locale> localesMap;

    static {
        localesMap = new HashMap<>();

        localesMap.put(Nationality.POLISH, Locale.forLanguageTag("PL"));
        localesMap.put(Nationality.UKRAINIAN, Locale.forLanguageTag("UK"));
        localesMap.put(Nationality.BELARUSSIAN, Locale.forLanguageTag("BE"));
        localesMap.put(Nationality.SLOVAK, Locale.forLanguageTag("SK"));
        localesMap.put(Nationality.LITHUANIAN, Locale.forLanguageTag("LT"));
        localesMap.put(Nationality.LATVIAN, Locale.forLanguageTag("LV"));
        localesMap.put(Nationality.BRITISH, Locale.forLanguageTag("EN"));
        localesMap.put(Nationality.CHINESE, Locale.forLanguageTag("ZH"));
        localesMap.put(Nationality.VIETNAMESE, Locale.forLanguageTag("VI"));
    }

    public static HashMap<Nationality, Locale> getLocales() {
        return localesMap;
    }

    public static Locale getLocaleByNationality(Nationality nationality) {
        return localesMap.get(nationality);
    }
}
