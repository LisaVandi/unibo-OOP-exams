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
            List<X> currentList = list;

            @Override
            public X getElement() {
                if (!currentList.isEmpty()) {
                    return currentList.get(0);
                }
                return null;
            }

            @Override
            public RecursiveIterator<X> next() {
                if (currentList.size()>1) {
                    return fromList(currentList.subList(1, currentList.size()));
                }
                return null;
            }
            
        };
    }

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        List<X> result = new ArrayList<>();
        int i = 0;
        
        while (i<max && input != null) {
            result.add(input.getElement());
            input = input.next();
            i++;
        }
        return result;
    }

    @Override
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        return new RecursiveIterator<Pair<X,Y>>() {

            @Override
            public Pair<X, Y> getElement() {
                if(first != null && second != null) {
                    return new Pair<>(first.getElement(), second.getElement());
                }
                return null;
            }

            @Override
            public RecursiveIterator<Pair<X, Y>> next() {
                if (first.next() == null || second.next() == null) {
                    return null; 
                }
                return zip(first.next(), second.next());
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
            boolean usedFirst = true;
            RecursiveIterator<X> currentIterator = first;

            @Override
            public X getElement() {
                return currentIterator.getElement();
            }

            @Override
            public RecursiveIterator<X> next() {
                currentIterator = usedFirst ? second : first;
                usedFirst = !usedFirst;
                if (currentIterator != null) {
                    return this;
                }
                return null;
                   
            }
            
        };
    }

     
}