package a01b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TimeSheetFactoryImpl implements TimeSheetFactory {

    @Override
    /**
	 * @param numActivities
	 * @param numNames
	 * @param hours
	 * @return a timesheet with:
	 * - activities "act1",..."actn" (numActivities elements)
	 * - days "day1",..."daym" (numDays elements)
	 * - the same number of hours spent in each acticity in each day
	 */
    public TimeSheet flat(int numActivities, int numDays, int hours) {
        
        // Creating a map to store timesheet: activity -> map<day, hours>
        Map<String, Map<String, Integer>> timesheet = new LinkedHashMap<>();
        
        for(int i = 1; i <= numActivities; i++) {
            String activity = "act" + i;
            timesheet.putIfAbsent(activity, new LinkedHashMap<>());
            Map<String, Integer> dayMap = new LinkedHashMap<>();
            for (int j = 1; j <= numDays; j++) {
                String day = "day" + j;
                dayMap.put(day, hours);
            }
            timesheet.put(activity, dayMap);
        }
        
        return new TimeSheet() {

            @Override
            public List<String> activities() {
                return new ArrayList<>(timesheet.keySet());
            }

            @Override
            public List<String> days() {
                return new ArrayList<>(timesheet.values().iterator().next().keySet());
            }

            @Override
            public int getSingleData(String activity, String day) {
                return timesheet.getOrDefault(activity, new LinkedHashMap<>()).getOrDefault(day, 0);
            }

            @Override
            public Map<String, Integer> sumsPerActivity() {Map<String, Integer> result = new HashMap<>();
                for (Map.Entry<String, Map<String, Integer>> entry : timesheet.entrySet()) {
                    int sum = entry.getValue().values().stream().mapToInt(Integer::intValue).sum();
                    result.put(entry.getKey(), sum);
                }
                return result;
            }

            @Override
            /**
             * @return a map from day (names) to hours spent on that day overall
             */
            public Map<String, Integer> sumsPerDay() {
                Map<String, Integer> res = new LinkedHashMap<>();
                for (String day : days()) {
                    int sum = 0;
                    for (Map<String, Integer> dayMap : timesheet.values()) {
                        sum += dayMap.getOrDefault(day, 0);
                    }
                    res.put(day, sum);
                }
                return res;
            }
            
        };
    }

    @Override
    public TimeSheet ofListsOfLists(List<String> activities, List<String> days, List<List<Integer>> data) {
        return flat(activities.size(), days.size(), 0);
    }

    @Override
    public TimeSheet ofRawData(int numActivities, int numDays, List<Pair<Integer, Integer>> data) {
        return flat(numActivities, numDays, data.get(numDays).get1());
    }

    @Override
    public TimeSheet ofPartialMap(List<String> activities, List<String> days, Map<Pair<String, String>, Integer> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofPartialMap'");
    }

}
