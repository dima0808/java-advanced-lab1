import java.util.Optional;
import java.util.stream.Gatherer;
import lombok.AllArgsConstructor;

/**
 * A custom gatherer that skips a specified number of cars of a given brand.
 */
@AllArgsConstructor
class CarGatherer implements Gatherer<Car, Optional<Car>, Car> {

  private int skipCount;
  private String skipBrand;

  @Override
  public Integrator<Optional<Car>, Car, Car> integrator() {
    return Gatherer.Integrator.of((_, element, downstream) -> {
      if (element.getBrand().equals(skipBrand) && skipCount > 0) {
        skipCount--;
        return true;
      }
      return downstream.push(element);
    });
  }
}
