import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Car {

  private String brand;
  private String model;
  private String carClass;
  private LocalDate manufactureDate;
  private int price;
}
