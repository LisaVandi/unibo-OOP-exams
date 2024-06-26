package a01a.e1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TimetableFactoryImpl implements TimetableFactory {

    
    @Override
    public Timetable empty() {
        //Map<String, String> timetable = new HashMap<>();
        Map<String, Map<String, Integer>> timetable = new HashMap<>(); // activity -> day, hours

        return new Timetable() {

            @Override
            public Timetable addHour(String activity, String day) {
                timetable.putIfAbsent(activity, new HashMap<>());
                timetable.get(activity).merge(day, 1, Integer::sum);
                return this;
            }

            @Override
            public Set<String> activities() {
                return timetable.keySet();
            }

            @Override
            public Set<String> days() {
                return timetable.values().stream().flatMap(m -> m.keySet().stream()).collect(Collectors.toSet());
            }

            @Override
            public int getSingleData(String activity, String day) {
                return timetable.getOrDefault(activity, Collections.emptyMap()).getOrDefault(day, 0); 
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                return activities.stream()
                        .flatMap(activity -> timetable.getOrDefault(activity, Collections.emptyMap()).entrySet().stream())
                        .filter(entry -> days.contains(entry.getKey()))
                        .mapToInt(Map.Entry::getValue)
                        .sum();
            }
            
        };
    }
 
    @Override
    /**
	 * @param activity
	 * @param day
	 * @return a new timetable with just one hour, spent on @activity at the given @day
	 */
    public Timetable single(String activity, String day) {
        return empty().addHour(activity, day);
    }

    @Override
    public Timetable join(Timetable table1, Timetable table2) {
        Timetable newTable = empty();
        table1.activities().forEach(activity -> 
            table1.days().forEach(day -> {
                int hours = table1.getSingleData(activity, day);
                for (int i = 0; i < hours; i++) {
                    newTable.addHour(activity, day);
                }
            })
        );
        
        table2.activities().forEach(activity -> 
            table2.days().forEach(day -> {
                int hours = table2.getSingleData(activity, day);
                for (int i = 0; i < hours; i++) {
                    newTable.addHour(activity, day);
                }
            })
        );
        return newTable;
    }

    @Override
    public Timetable cut(Timetable table, BiFunction<String, String, Integer> bounds) {
        Timetable newTable = empty();
        table.activities().forEach(activity -> 
        table.days().forEach(day -> {
            int hours = table.getSingleData(activity, day);
            int maxHours = bounds.apply(activity, day);
            int newHours = Math.min(hours, maxHours);
            for (int i = 0; i < newHours; i++) {
                newTable.addHour(activity, day);
            }
        }));
        return newTable;
    }     
}