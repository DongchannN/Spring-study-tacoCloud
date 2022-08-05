package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data  //lombok 이 final 속성들을 초기화하는 생성자와 게터,세터를 생성하라고 알려준다.
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
