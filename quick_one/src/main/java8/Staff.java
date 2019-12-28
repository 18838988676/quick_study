import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/23.
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private String name;
    private int age;
    private BigDecimal salary;
}
