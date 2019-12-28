import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/23.
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffPublic {
    private String name;
    private int age;
    private String extra;
}
