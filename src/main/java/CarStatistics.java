import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarStatistics {

  private int minPrice;
  private int maxPrice;
  private double avgPrice;
  private double stdDeviation;
}
