import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main class that demonstrates filtering, grouping, statistical analysis,
 * and price analysis of a list of Car objects.
 */
public class Main {

  /**
   * Main entry point of the program.
   */
  public static void main(String[] args) {
    int N = 10;
    String brandToSkip = "Toyota";

    // Generate and filter cars
    List<Car> cars = getFilteredCars(N, brandToSkip);
    for (Car car : cars) {
      System.out.println(car);
    }

    // Group cars by their class
    Map<String, List<Car>> groupedCars = getGroupedCars(cars);
    for (Map.Entry<String, List<Car>> entry : groupedCars.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue().size());
    }

    // Collect car statistics
    CarStatistics statistics = cars.stream()
        .collect(new CarStatisticsCollector());
    System.out.println(statistics);

    // Analyze price distribution and identify outliers
    Map<String, Long> priceAnalysis = PriceAnalysis.analyzePrices(cars);
    System.out.println(priceAnalysis);
  }

  /**
   * Filters cars based on a specified number to skip and a brand to skip.
   *
   * @param skipCount The number of cars of a specific brand to skip.
   * @param brandToSkip The brand of cars to skip during filtering.
   * @return A list of filtered Car objects.
   */
  private static List<Car> getFilteredCars(int skipCount, String brandToSkip) {
    return CarGenerator.generateCars()
        .gather(new CarGatherer(skipCount, brandToSkip))
        .limit(500)
        .collect(Collectors.toList());
  }

  /**
   * Groups cars by their class and filters them by manufacture date.
   *
   * @param cars List of Car objects to group.
   * @return A map where the keys are car classes and values are lists of Car objects.
   */
  private static Map<String, List<Car>> getGroupedCars(List<Car> cars) {
    return cars.stream()
        .filter(car -> car.getManufactureDate().isAfter(LocalDate.now().minusMonths(60)))
        .collect(Collectors.groupingBy(Car::getCarClass));
  }
}
