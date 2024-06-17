package a02b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers {

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return new Cursor<X>() {
            int index = 0;

            @Override
            public X getElement() {
                if (!list.isEmpty() || !advance()) {
                    return list.get(index);
                }
                return null;
            }

            @Override
            public boolean advance() {
                index++;
                return index < list.size();
            }
            
        };
    }

    @Override
    public Cursor<Integer> naturals() {
        return new Cursor<Integer>() {
            int index = 0;

            @Override
            public Integer getElement() {
                return index;
            }

            @Override
            public boolean advance() {
                index++;
                return true;
            }
            
        };
    }

    @Override
    /**
    * @param input, assumed not to be empty, by definition
    * @param max, assumed to be positive
    * @return a cursor considering the first max-elements given by input; if
    * max is greater than input's size, then the output is the same as the input
    */
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        return new Cursor<X>() {
            private int count = 0;

            @Override
            public X getElement() {
                this.count++;
                return input.getElement();
            }

            @Override
            public boolean advance() {
                if (input.advance() && count < max){
                    return true;
                } else {
                    return false;
                }
            }
            
        };
    }

    @Override
    /**
	 * @param input, assumed not to be empty, by definition
	 * @param consumer
	 * Applies the consumer (that is, its method accept) to all elements of the cursor.
	 * This application typically produces some side-effect.
	 */
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        while (input.advance()) {
            consumer.accept(input.getElement());
        }
    }

    @Override
    /**
	 * @param input, assumed not to be empty, by definition
	 * @param max, assumed to be positive
	 * @return extract elements from cursor (no more than max), and creates a list
	 */
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> result = new ArrayList<>();
        Cursor<X> newCursor = take(input, max);
        result.add(newCursor.getElement());
        while(newCursor.advance()){
            result.add(newCursor.getElement());
        }
        return result;
    }

}
