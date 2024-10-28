import java.util.*;
import java.util.stream.Collectors;

public class PriceAnalysis {

  public static Map<String, Long> analyzePrices(List<Car> cars) {
    List<Integer> prices = cars.stream()
        .map(Car::getPrice)
        .sorted()
        .collect(Collectors.toList());

    int Q1 = calculatePercentile(prices, 25);
    int Q3 = calculatePercentile(prices, 75);
    int IQR = Q3 - Q1;

    double lowerBound = Q1 - 1.5 * IQR;
    double upperBound = Q3 + 1.5 * IQR;

    Map<Boolean, Long> result = cars.stream()
        .collect(Collectors.partitioningBy(
            car -> car.getPrice() >= lowerBound && car.getPrice() <= upperBound,
            Collectors.counting()
        ));

    Map<String, Long> finalResult = new HashMap<>();
    finalResult.put("data", result.get(true));
    finalResult.put("outliers", result.get(false));

    return finalResult;
  }

  private static int calculatePercentile(List<Integer> sortedPrices, int percentile) {
    int index = (int) Math.ceil(percentile / 100.0 * sortedPrices.size()) - 1;
    return sortedPrices.get(index);
  }
}
