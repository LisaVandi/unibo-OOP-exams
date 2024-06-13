package a03c.e1;

import java.util.List;
import java.util.Optional;

public class DeterministicParserFactoryImpl implements DeterministicParserFactory {

    @Override
    /**
	 * @param s
	 * @return a parser consuming exactly one token equals to s
	 */
    public DeterministicParser oneSymbol(String s) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                if (!tokens.isEmpty() && tokens.get(0).equals(s)) {
                    // return Optional.of(tokens);
                    // sublist crea una lista da indice1 a indice2
                    return Optional.of(tokens.subList(1, tokens.size()));
                }
                return Optional.empty();
            }

        };
    }

    @Override
    /**
	 * @param s1
	 * @param s2
	 * @return a parser consuming exactly one token equals to s1 and then one equals to s2
	 */
    public DeterministicParser twoSymbols(String s1, String s2) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                if (tokens.size() >= 2 && tokens.get(0).equals(s1) && tokens.get(1).equals(s2)) {
                    return Optional.of(tokens.subList(2, tokens.size()));
                }
                return Optional.empty();
            }
        };
        
    }

    @Override
    /**
	 * @param s1
	 * @param s2
	 * @return a parser consuming a sequence of strings representing a (possibly empty) sequence of positive numbers, strictly increasing,
	 * e.g.: "10","20","30"
	 * Recall that conversion from String to int can be done with Integer.parseInt() method.
	 */
    public DeterministicParser possiblyEmptyIncreasingSequenceOfPositiveNumbers() {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                int previous = 0;
                int index = 0;
                while (index < tokens.size()) {
                    int number = Integer.parseInt(tokens.get(index));
                    if (number <= previous) {
                        break;
                    }
                    previous = number;
                    index++;
                }
                return Optional.of(tokens.subList(index, tokens.size()));
            }
        };
    }

    @Override
    /**
	 * @param start
	 * @param stop
	 * @param delimiter
	 * @param element
	 * @return a parser consuming a sequence with start + element + delimiter + element + delimiter + ... + element + stop
	 * where at least one element is present
	 * e.g., by this method we could parse the tokenized string "[a;a;a;a]" (see the test)
	 */
    public DeterministicParser sequenceOfParsersWithDelimiter(String start, String stop, String delimiter,
            DeterministicParser element) {
        return null;
    }

    @Override
    /**
    * @param first
    * @param second
    * @return a parser consuming first and then second
    */
    public DeterministicParser sequence(DeterministicParser first, DeterministicParser second) {
        return new DeterministicParser() {
            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                Optional<List<String>> firstResult = first.accepts(tokens);
                if (firstResult.isPresent()) {
                    Optional<List<String>> secondResult = second.accepts(firstResult.get());
                    if (secondResult.isPresent()) {
                        return secondResult;
                    }
                }
                return Optional.empty();
            }
        };    
    }

}
