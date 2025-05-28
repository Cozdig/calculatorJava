package pro.sky.skyprocalculator;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }
    @GetMapping
    public String welcome(){
        return "Добро пожаловать в калькулятор";
    }
    @GetMapping(path = "/plus")
    public String plus(@RequestParam("num1") int number1, @RequestParam("num2") int number2){
        int result = calculatorService.plus(number1, number2);
        return number1 + " + " + number2 + " = " + result;
    }
    @GetMapping(path = "/minus")
    public String minus(@RequestParam("num1") int number1, @RequestParam("num2") int number2){
        int result = calculatorService.minus(number1, number2);
        return number1 + " - " + number2 + " = " + result;
    }
    @GetMapping(path = "/multiply")
    public String multiply(@RequestParam("num1") int number1, @RequestParam("num2") int number2){
        int result = calculatorService.multiply(number1, number2);
        return number1 + " * " + number2 + " = " + result;
    }
    @GetMapping(path = "/divide")
    public String divide(@RequestParam("num1") int number1, @RequestParam("num2") int number2){
        try {
            int result = calculatorService.divide(number1, number2);
            return number1 + " / " + number2 + " = " + result;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        return "Ошибка! Один из параметров не указан. Попробуйте ввести " + "/(название действия)?num1=5&num2=3";
    }
}

