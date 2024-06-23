package a06.e1;

import java.util.ArrayList;
import java.util.List;

public class CirclerFactoryImpl implements CirclerFactory {

    @Override
    /**
	 * @param <T>
	 * @return a new circler that keeps iterating the source from left to right,
	 * doing multiple passes, hence yielding: 
	 * E1,E2,...,En,E1,E2,...,En,E1,...
	 */
    public <T> Circler<T> leftToRight() {
        return new Circler<T>() {
            List<T> source; 
            int index = 0;

            @Override
            // it sets the source: each time this is called, the iteration will be reset
            public void setSource(List<T> elements) {
                this.source = elements;
                this.index = 0;
            }

            @Override
            public T produceOne() {
                T elem = source.get(index);
                index = (index + 1) % source.size(); // incremento l'indice
                return elem;
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> result = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    result.add(produceOne());
                }
                return result;
            }
            
        };
    }

    @Override
    /**
	 * @param <T>
	 * @return a new circler that iterates the source from left to right, then in the
	 * opposite way, then in the opposite way, and so on, hence yielding: 
	 * E1,E2,...,En,En,...,E2,E1,E1,E2,...,En,En,...
	 */
    public <T> Circler<T> alternate() {
        return new Circler<T>() {

            private boolean inAvanti;
            private List<T> source;
            private int index;

            @Override
            public void setSource(List<T> elements) {
                this.source = elements;
                this.index = 0;
                this.inAvanti = true;
            }

            @Override
            public T produceOne() {
                T elem = source.get(index);
                if (inAvanti) {
                    index = (index+1) % source.size();
                    if(index==0) {
                        inAvanti = false;
                    }
                } else {
                    index = (index - 1 + source.size()) % source.size();
                    if (index == source.size() - 1) {
                        inAvanti = true;
                    }
                }
                return elem;
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> result = new ArrayList<>();
                for (int i = 0; i<n; i++) {
                    result.add(produceOne());
                }
                return result;
            }
            
        };   
    }

    @Override
    /**
	 * @param <T>
	 * @return a new circler that iterates the source from left to right, and then stays
	 * on last element, hence yielding: 
	 * E1,E2,...,En,En,En,En,En,En,...
	 */
    public <T> Circler<T> stayToLast() {
        return new Circler<T>() {
            List<T> source; 
            int index = 0;
            T lastElement;

            @Override
            // it sets the source: each time this is called, the iteration will be reset
            public void setSource(List<T> elements) {
                this.source = elements;
                this.index = 0;
                this.lastElement = null;
            }

            @Override
            public T produceOne() {
                lastElement = source.get(index);
                if (index == 0) {
                    return lastElement;
                }
                T elem = lastElement;
                index = (index + 1) % source.size(); // incremento l'indice
                return elem;
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> result = new ArrayList<>();
                for (int i = 0; i<n; i++) {
                    result.add(produceOne());
                }
                return result;
            }
            
        };
    }

    @Override
    /**
	 * @param <T>
	 * @return a new circler that behaves like the one created by leftToRight(), but skipping
	 * one element each time, that is, if the one created by leftToRight() would give
	 * A1,A2,A3,A4,A5,...., this one gives: A1,A3,A5,A7,...  
	 */
    public <T> Circler<T> leftToRightSkipOne() {
        return new Circler<T>() {
            List<T> source; 
            int index = 0;

            @Override
            public void setSource(List<T> elements) {
                this.source = elements;
                this.index = 0;
            }

            @Override
            public T produceOne() {
                T elem = source.get(index);
                index = (index + 2) % source.size(); // incremento l'indice, saltando 1
                return elem;
            }

            @Override
            public List<T> produceMany(int n) {
                List<T> result = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    result.add(produceOne());
                }
                return result;
            }
            
        };
    }

    // OPZIONALE
    @Override
    public <T> Circler<T> alternateSkipOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alternateSkipOne'");
    }

    // OPZIONALE
    @Override
    public <T> Circler<T> stayToLastSkipOne() {
        return new Circler<T>() {

            @Override
            public void setSource(List<T> elements) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'setSource'");
            }

            @Override
            public T produceOne() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'produceOne'");
            }

            @Override
            public List<T> produceMany(int n) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'produceMany'");
            }
            
        };
    }

}
