package tacos.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.IngredientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// @Slf4j   lombok annotation
@Controller
@RequestMapping("/design")          // specifies the kind request that controller handles
@SessionAttributes("tacoOrder")     //
public class DesignTacoController {

    //logger
    private static final Logger log =
            LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel (Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.fineAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    // TacoOrder is hold the state for the order being build as user creates
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    // Taco object is placed into the view rendered in response
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    // handle GET request for /design
    @GetMapping
    public String showDesignForm() {
        log.info("From design method.");
        return "design";    // return a String value of "design", which is the view that used to render the model to browser
    }

    // handle POST requests for taco design submissions (when submitted)
    // @Valid annotation tells SpringMVC to perform validation
    @PostMapping
    public String processTaco(@Valid Taco taco,
                              Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }
}
