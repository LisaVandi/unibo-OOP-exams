package a01c.e1;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import java.util.ArrayList;
import java.util.Random;
import java.util.NoSuchElementException;
import java.nio.file.*;

public class EventHistoryFactoryImpl implements EventHistoryFactory {

    @Override
    /**
	 * @param <E>
	 * @param map, a map from time to the event happened at that time
	 * @return the event history made of the ordered sequence of events contained in the map
	 */
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        List<Map.Entry<Double, E>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByKey());
        return new EventHistory<E>() {
            private Iterator<Map.Entry<Double, E>> iterator = entries.iterator();
            private Map.Entry<Double, E> current = iterator.hasNext() ? iterator.next() : null;

            @Override
            public double getTimeOfEvent() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                return current.getKey();
            }

            @Override
            public E getEventContent() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                return current.getValue();
            }

            @Override
            public boolean moveToNextEvent() {
                if (iterator.hasNext()) {
                    current = iterator.next();
                    return true;
                } else {
                    current = null;
                    return false;
                }
            }
        };
    }

    @Override
    /**
	 * @param <E>
	 * @param times, an iterator over the moments of time at which events happen (t1,t2,t3,...)
	 * @param content, an iterator over the content of events happening from time to time (e1,e2,e3,...)
	 * @return the history (t1,e1), (t2,e2), ...
	 * Note the size of the history is them minimum of sizes of times and content iterators
	 */
    public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
        return new EventHistory<E>() {

            @Override
            public double getTimeOfEvent() {
                return times.next();
            }

            @Override
            public E getEventContent() {
                return content.next();
            }

            @Override
            public boolean moveToNextEvent() {
                return content.hasNext();
            }
            
        };
    }

    @Override
    public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
        return new EventHistory<E>() {
            private int index = 0;
            private double currentTime = initial;

            @Override
            public double getTimeOfEvent() {
                return currentTime;
            }

            @Override
            public E getEventContent() {
                return content.get(index);
            }

            @Override
            public boolean moveToNextEvent() {
                if (index < content.size() - 1) {
                    index++;
                    currentTime += delta;
                    return true;
                } else {
                    return false;
                }
            }
            
        };
    }

    @Override
    /**
	 * @param <E>
	 * @param content
	 * @param size
	 * @return the history (t1,e1)..(tn,en) where e1,e2,... are obtained from @content,
	 * t1, t2-t1, t3-t2 are obtained from Math.random(), and n=size
	 */
    public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {

        List<Double> times = new ArrayList<>();
        List<E> events = new ArrayList<>();
        final Random random = new Random();
        double currentTime = 0.0;
        
        for (int i = 0; i < size; i++) {
            currentTime += random.nextDouble();
            times.add(currentTime);
            events.add(content.get());
        }

        return new EventHistory<E>() {
            private int index = 0;

            @Override
            public double getTimeOfEvent() {
                return times.get(index);
            }

            @Override
            public E getEventContent() {
                return events.get(index);
            }

            @Override
            public boolean moveToNextEvent() {
                if (index < times.size() - 1) {
                    index++;
                    return true; 
                } 
                return false;
            }
            
        };
    }

    @Override
    /**
	 * @param file is a text file in which each line is of the kind "1.45:aabb", 
	 * where 1.45 is the time and 'aabb' is the event content as a String.
	 * @return the history made of events, one per line
	 * @throws IOException if the file can't be read
	 * Suggestions if needed:
	 * - use method split(":",2) in String to split "1.45:aabb" into new String[]{"1.45","aabb"}
	 * - use method Double.parseDouble to convert a string into a double
	 * - use a readLine of a BufferedReader that wraps a FileReader(file) to read from text files   
	 */
    public EventHistory<String> fromFile(String file) throws IOException {
        return null;   
    }

}
