package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;  //manual slf4j
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    //private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/current")
    public String orderForm() {
        //log.info("Showing order form");
        log.info("Showing order form");
        return "orderForm";
    }

    // @Valid
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()){
            return "orderForm";
        }

        log.info("Order Submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
