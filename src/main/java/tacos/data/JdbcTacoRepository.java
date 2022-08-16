package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tacos.Ingredient;
import tacos.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

/**
 * Taco 객체를 저장하는 클래스 (TacoRepository 인터페이스 구현)
 * 타코 디자인을 저장하기 위해 해당 타코와 연관된 식자재 데이터도 Taco_Ingredient 테이블에 저장해야함.
 * save()메서드 - 각 식자재를 저장하는 saveTacoInfo(Taco taco)메서드 호출 -> tacoId 반환.
 *            - 반환된 tacoId를 통해 타코와 식자재의 연관정보를 저장하는 saveIngredientToTaco()호출.
 *
 * saveTacoInfo(Taco taco) - 타코 id반환.
 *
 * saveIngredientToTaco() - 타코 id와 Ingredient 객체 참조를 Taco_Ingredient 테이블에 저장.
 */
public class JdbcTacoRepository implements TacoRepository{

    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory
                        ("insert into Taco (name, createdAt) values (?,?)", Types.VARCHAR, Types.TIMESTAMP)
                        .newPreparedStatementCreator(Arrays.asList(taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient)" + "values (?,?)",
                tacoId, ingredient.getId());
    }
}
