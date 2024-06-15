package a01b.e1;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

public class FlattenerFactoryImpl implements FlattenerFactory {

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                int sum = 0;
                
                List<Integer> result = new ArrayList<>();
                for (List<Integer> innerList : list) {
                    for (Integer elem : innerList) {
                        sum += elem;
                    }
                   result.add(sum);
                   sum = 0;
                }
                return result;
            }
            
        };
    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X,X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                List<X> result = new ArrayList<>();
                for (List<X> innerList : list) {
                    for (X x : innerList) {
                        result.add(x);
                    }
                }
                return result;
            }
            
        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String,String>() {

            @Override
            public List<String> flatten(List<List<String>> list) {
                
                List<String> result = new ArrayList<>();
                StringBuilder currentConcat = new StringBuilder();
                boolean isPair = false;
    
                for (List<String> innerList : list) {
                    for (String string : innerList) {
                        currentConcat.append(string);
                    }
                    if (isPair) {
                        result.add(currentConcat.toString());
                        currentConcat.setLength(0); // reset the StringBuilder
                        isPair = false;
                    } else {
                        isPair = true;
                    }
                }
    
                // If there's an unmatched inner list, add it as is
                if (currentConcat.length() > 0) {
                    result.add(currentConcat.toString());
                }
    
                return result;
            }  
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I,O>() {

            @Override
            public List<O> flatten(List<List<I>> list) {
                List<O> result = new ArrayList<>();
                for (List<I> innerList : list) {
                    result.add(mapper.apply(innerList));
                }
                return result;
            }
            
        };
    }

    @Override
    /**
    * @return a  Flattener that implements algebraic sum of vectors of integers
    * You can assume all inner lists have same size
    * e.g.: [[s1,s2],[s3,s4],[s5,s6],[s7,s8]] --> [s1+s3+s5+s7, s2+s4+s6+s8]
    */
    public Flattener<Integer, Integer> sumVectors() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                int size = list.get(0).size();
                List<Integer> result = new ArrayList<>();
                // popolo la lista result con 0 per averla della stessa dimensione di list
                for (int i = 0; i<size; i++) {
                    result.add(i, 0);
                }
               
                for (List<Integer> innerList : list) {
                    for (int i = 0; i<size; i++) {
                        // sostituisco l'i-esimo elemento con la somma
                        result.set(i, result.get(i) + innerList.get(i));
                    }
                }
               return result;
            }
            
        };
    }

}
