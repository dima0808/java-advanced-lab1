import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    int N = 10;
    String brandToSkip = "Toyota";

    List<Car> cars = getFilteredCars(N, brandToSkip);
    for (Car car : cars) {
      System.out.println(car);
    }

    Map<String, List<Car>> groupedCars = getGroupedCars(cars);
    for (Map.Entry<String, List<Car>> entry : groupedCars.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue().size());
    }

    CarStatistics statistics = cars.stream()
        .collect(new CarStatisticsCollector());
    System.out.println(statistics);

    Map<String, Long> priceAnalysis = PriceAnalysis.analyzePrices(cars);
    System.out.println(priceAnalysis);
  }

  private static List<Car> getFilteredCars(int skipCount, String brandToSkip) {
    return CarGenerator.generateCars()
        .gather(new CarGatherer(skipCount, brandToSkip))
        .limit(500)
        .collect(Collectors.toList());
  }

  private static Map<String, List<Car>> getGroupedCars(List<Car> cars) {
    return cars.stream()
        .filter(car -> car.getManufactureDate().isAfter(LocalDate.now().minusMonths(60)))
        .collect(Collectors.groupingBy(Car::getCarClass));
  }
}
