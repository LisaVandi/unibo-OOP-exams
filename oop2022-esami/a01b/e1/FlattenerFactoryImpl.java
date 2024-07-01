package a01b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FlattenerFactoryImpl implements FlattenerFactory {

    @Override
    /**
	 * @return a Flattener that turns each inner list in its sum
	 * e.g.: [[e1,e2,e3],[e4,e5,e6],[e7,e8]] --> (e1+e2+e3),(e4+e5+e6),(e7+e8)
	 */
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                int sum = 0;
                List<Integer> res = new ArrayList<>();
                
                for (List<Integer> innerList : list) {
                    for (Integer integer : innerList) {
                        sum += integer;    
                    }
                    res.add(sum);
                    sum = 0;
                }
                return res;
            }
            
        };
    }

    @Override
    /**
	 * @return a generic Flattener that appends all inner lists
	 * e.g.: [[e1,e2,e3],[e4,e5,e6],[e7,e8]] --> [e1,e2,e3,e4,e5,e6,e7,e8]
	 */
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X,X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                List<X> res = new ArrayList<>();
                for (List<X> innerList : list) {
                    for (X x : innerList) {
                        res.add(x);
                    }                   
                }
                return res;
            }
            
        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String,String>() {

            @Override
            public List<String> flatten(List<List<String>> list) {
                // le inner list vengono prese a coppie: per ogni coppia si uniscono tutte le loro stringhe
                // se c'è una inner list finale la si tratta da sola
                
                List<String> res = new ArrayList<>();
                
                for (int i = 0; i < list.size(); i += 2) {
                    StringBuilder sb = new StringBuilder();
                    for (String s : list.get(i)) {
                        sb.append(s);
                    }
                    if (i + 1 < list.size()) {
                        for (String s : list.get(i + 1)) {
                            sb.append(s);
                        }
                    }
                    res.add(sb.toString());
                }

                return res;
            }
            
        };    
    }

    @Override
	/**
	 * @return a generic Flattener that turns each list into a single element of the output
	 * e.g.: [[s1,s2,s3],[s4],[s5,s6]] --> [f([s1,s2,s3]), f([s4]), f([s5,s6])]
	 */
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I,O>() {

            @Override
            public List<O> flatten(List<List<I>> list) {
                List<O> res = new ArrayList<>();
                for (List<I> elem : list) {
                    res.add(mapper.apply(elem));                    
                }
                return res;
            }
            
        };
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumVectors'");
    }

}
