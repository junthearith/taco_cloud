package tacos.data;

import tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> fineAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
