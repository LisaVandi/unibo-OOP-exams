package a03a.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ParserFactoryImpl implements ParserFactory {

    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> inputSequence = new ArrayList<>();
                while (iterator.hasNext()) {
                    inputSequence.add(iterator.next());
                }
                return acceptedSequences.contains(inputSequence);
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.hasNext()) {
                    return false;
                }
                X currentElem = x0;
                // acceptanceInputs.add(currentElem);
                while (iterator.hasNext()) {
                    X next = iterator.next();
                    if (!transitions.contains(new Pair<X,X>(currentElem, next))) {
                        return false; 
                    }
                    // acceptanceInputs.add(currentElem);         
                    currentElem = next;
                }       
                return acceptanceInputs.contains(currentElem);
                    
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                Optional<X> optional = Optional.of(x0);
                while (iterator.hasNext() && optional.isPresent()) {
                    X number = optional.get();
                    if (!iterator.next().equals(number)) {
                        return false;
                    }
                    optional = next.apply(number);
                }
                return !iterator.hasNext() && optional.isEmpty();
            }
            
        };
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.hasNext()) {
                    return isFinal;
                } 
                X currentEl = iterator.next();
                // isPresent perché If nextParser is the empty Optional, it means the x element is NOT accepted.
                return nextParser.apply(currentEl).isPresent() && nextParser.apply(currentEl).get().accept(iterator);
            }
            
        };   
    }

    @Override
     /**
     * @param <X>
     * @param x
     * @param parser
     * @return a parser that first accepts x, and then proceeds likewise parser
     */
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.hasNext() || !iterator.next().equals(x)) {
                    return false;
                }
                return parser.accept(iterator);
            }
            
        };    
    }

}
