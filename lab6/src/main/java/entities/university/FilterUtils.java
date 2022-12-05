package entities.university;

import entities.person.Nationality;
import entities.person.NationalityLocaleMap;
import entities.person.Person;
import entities.person.Teacher;
import java.text.Collator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FilterUtils {
    public static <T extends Person> List<T> filterByNationality(TreeSet<T> treeSet, Nationality nationality) {
        return treeSet
            .stream()
            .filter(e -> e.getNationality() == nationality)
            .sorted(Collator.getInstance(NationalityLocaleMap.getLocaleByNationality(nationality)))
            .collect(Collectors.toList());
    }
}
