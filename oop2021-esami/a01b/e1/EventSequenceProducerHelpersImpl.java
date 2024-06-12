package a01b.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import javax.management.relation.RoleList;

public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers {

    @Override
    /**
	 * @param <E>
	 * @param iterator
	 * @return an EventSequenceProducers from the iterator
	 */
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                if (!iterator.hasNext()) {
                    throw new NoSuchElementException();
                }                
                return iterator.next();
            }
            
        };
    }

    @Override
    /**
	 * @param <E>
	 * @param sequence
	 * @param fromTime
	 * @param toTime
	 * @return the EventSequenceProducer obtained from sequence by taking all events happening
	 * from @fromTime to @toTime
	 */
    public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
        List<E> windowEvents = new ArrayList<>();
        Pair<Double, E> event;
        while (true) {
            try {
                event = sequence.getNext();
            } catch (NoSuchElementException e) {
                break;
            }
            if (event.get1() >= fromTime && event.get1() <= toTime) {
                windowEvents.add(event.get2());
            }
        }
        return windowEvents;
    }

    @Override
    /**
	 * @param <E>
	 * @param sequence
	 * @return an iterable over the content of all events
	 */
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
        List<E> contentList = new ArrayList<>(); // per iterabile, si intende un qualsiasi oggetto con iteratore
    
        try {
            Pair<Double, E> nextPair;
            while ((nextPair = sequence.getNext()) != null) {
                contentList.add(nextPair.get2());
            }
        } catch (NoSuchElementException e) {
            // La sequenza ha terminato, non fare nulla
        }
        
        return contentList;
    }

    @Override
    /**
	 * @param <E>
	 * @param sequence
	 * @param time
	 * @return the first event produced by @sequence after @time, or the empty Optional if there is not one 
	 */
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
        Pair<Double, E> event;

        while (true) {
            try {
                event = sequence.getNext();
            } catch (NoSuchElementException e) {
                return Optional.empty();
            }
        
            if (event.get1() >= time) {
                return Optional.of(event);
            }  
        }
    }

    @Override
    /**
	 * @param <E>
	 * @param sequence
	 * @param predicate
	 * @return the EventSequenceProducer obtained from @sequence by filtering away all events whose 
	 * content does not pass the @predicate
	 */
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                Pair<Double, E> event;
                while (true) {
                    try {
                        event = sequence.getNext();
                    } catch (NoSuchElementException e) {
                        throw new NoSuchElementException("No more elements after filtering");
                    }
                    if (predicate.test(event.get2())) {
                        return event;
                    }
                }
            };
        };  
    }
}
