import java.util.Optional;

public class B extends A{
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("Hello");
//        Optional<String> upperCaseOptional = optional.map(String::toUpperCase);
        if (optional.isPresent()) {
            String upperCaseValue = optional.get();
            System.out.println("Upper Case Value: " + upperCaseValue);
        } else {
            System.out.println("Value is not present");
        }
    }
}
