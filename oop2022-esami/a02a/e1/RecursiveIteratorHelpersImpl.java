package a02a.e1;

import java.util.ArrayList;
import java.util.List;

public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers {

    @Override
    public <X> RecursiveIterator<X> fromList(List<X> list) {
        if (list.isEmpty()) {
            return null;
        }

        return new RecursiveIterator<X>() {
            private int index = 0;


            @Override
            public X getElement() {
                return list.get(index);
            }

            @Override
            public RecursiveIterator<X> next() {
                index += 1;
                if (index < list.size()) {
                    return fromList(list.subList(index, list.size()));
                } else {
                    return null;
                }
            }
            
        };
    }

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        List<X> res = new ArrayList<>();

        for (int i = 0; i < max && input != null; i++) {
            X el = input.getElement();
            input = input.next();
            res.add(el);
        }

        return res;
    }

    @Override
     /**
     * @param first
     * @param second
     * @return an iterator of pairs of elements from first and second, orderly
     * it provides elements until one of the two iterators is over
     */
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        return new RecursiveIterator<Pair<X,Y>>() {

            @Override
            public Pair<X, Y> getElement() {
                if (first != null && second != null) {
                    return new Pair<X,Y>(first.getElement(), second.getElement());
                }
                return null;
            }

            @Override
            public RecursiveIterator<Pair<X, Y>> next() {
                RecursiveIterator<X> firstNext = first.next();
                RecursiveIterator<Y> secondNext = second.next();
                
                if (firstNext != null && secondNext != null) {
                    return zip(firstNext, secondNext);
                }
                return null;
            }
            
        };
    }

    @Override
    public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
        if (iterator == null) {
            return null;
        }        
        return new RecursiveIterator<Pair<X,Integer>>() {
            int index = 0;
            RecursiveIterator<X> currenIterator = iterator;
            
            @Override
            public Pair<X, Integer> getElement() {
                return new Pair<>(currenIterator.getElement(), index);
            }
            
            @Override
            public RecursiveIterator<Pair<X, Integer>> next() {
                RecursiveIterator<X> nextIterator = currenIterator.next();
                if (nextIterator == null) {
                    return null;
                }
                index++;
                currenIterator = nextIterator;
                return this;
            }
            
        };
    }

    @Override
    public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
        return new RecursiveIterator<X>() {

            private boolean isFirst = true;
             RecursiveIterator<X> currentIterator = first;

            @Override
            public X getElement() {
                return currentIterator.getElement();
            }

            @Override
            public RecursiveIterator<X> next() {
                if (currentIterator != null) {
                    currentIterator = isFirst ? second : first;
                    isFirst = !isFirst;
                if (currentIterator != null) {
                    currentIterator = currentIterator.next(); // Avanza l'iteratore corrente
                    if (currentIterator != null) {
                        return this;
                    }
                }
            }
            return null;
                   
            }
            
        };
    }

}
