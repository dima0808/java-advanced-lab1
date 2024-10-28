import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

public class CarStatisticsCollector implements Collector<Car, List<Integer>, CarStatistics> {

  @Override
  public Supplier<List<Integer>> supplier() {
    return ArrayList::new;
  }

  @Override
  public BiConsumer<List<Integer>, Car> accumulator() {
    return (accumulator, car) -> accumulator.add(car.getPrice());
  }

  @Override
  public BinaryOperator<List<Integer>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  @Override
  public Function<List<Integer>, CarStatistics> finisher() {
    return prices -> {
      int min = prices.stream().min(Integer::compare).orElse(0);
      int max = prices.stream().max(Integer::compare).orElse(0);
      double avg = prices.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
      double stdDev = Math.sqrt(prices.stream()
          .mapToDouble(price -> Math.pow(price - avg, 2))
          .average().orElse(0.0));
      return new CarStatistics(min, max, avg, stdDev);
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    Set<Characteristics> characteristics = new HashSet<>();
    characteristics.add(Characteristics.CONCURRENT);
    return characteristics;
  }
}
