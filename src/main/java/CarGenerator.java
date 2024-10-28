import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

public class CarGenerator {

  private static final String[] BRANDS = {"Toyota", "BMW", "Honda", "Audi", "Ford"};
  private static final String[] MODELS = {"Model S", "X5", "Civic", "A4", "Focus"};
  private static final String[] CLASSES = {"Economy", "Business", "Luxury", "SUV"};
  private static final Random RANDOM = new Random();

  public static Stream<Car> generateCars() {
    return Stream.generate(() -> Car.builder()
        .brand(getRandomBrand())
        .model(getRandomModel())
        .carClass(getRandomCarClass())
        .manufactureDate(LocalDate.now().minusMonths(RANDOM.nextInt(240)))
        .price((int) (200_000 + (1_000_000 * RANDOM.nextDouble())))
        .build());
  }

  private static String getRandomBrand() {
    return BRANDS[RANDOM.nextInt(BRANDS.length)];
  }

  private static String getRandomModel() {
    return MODELS[RANDOM.nextInt(MODELS.length)];
  }

  private static String getRandomCarClass() {
    return CLASSES[RANDOM.nextInt(CLASSES.length)];
  }
}
