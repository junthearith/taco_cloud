package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private long id;
    private Date createAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long!")
    private String name;

    @NotNull
    @Size(min = 1, message = "must be choose at least 1 ingredient.")
    private List<IngredientRef> ingredients = new ArrayList<>();

    //Constructor
    public Taco() {

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
