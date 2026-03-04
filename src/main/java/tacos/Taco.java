package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long!")
    private String name;

    private long id;

    private Date createAt = new Date();

    @NotNull
    @Size(min = 1, message = "must be choose at least 1 ingredient.")
    private List<Ingredient> ingredients;

    //Constructor
    public Taco() {

    }

    public Taco(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }


    // lists tacos
//    @Override
//    public String toString() {
//        return "Taco{" +
//                "name='" + name + '\'' +
//                ", ingredients=" + ingredients +
//                '}';
//    }

}
