package a01a.e1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class TimetableFactoryImpl implements TimetableFactory {

    @Override
    /**
	 * @return a new timetable with 0 hours, no activities and no days
	 */
    public Timetable empty() {
        return new Timetable() {
            private final Map<String, Map<String, Integer>> data = new HashMap<>();

            @Override
            public Timetable addHour(String activity, String day) {
                return null;
            }

            @Override
            public Set<String> activities() {
                return data.keySet();
            }

            @Override
            public Set<String> days() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'days'");
            }

            @Override
            public int getSingleData(String activity, String day) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getSingleData'");
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'sums'");
            }
            
        };
    }

    @Override
    public Timetable single(String activity, String day) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'single'");
    }

    @Override
    public Timetable join(Timetable table1, Timetable table2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }

    @Override
    public Timetable cut(Timetable table, BiFunction<String, String, Integer> bounds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cut'");
    }

}
