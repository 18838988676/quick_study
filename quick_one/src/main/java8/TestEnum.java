/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/23.
 * @Description:
 */
public enum TestEnum {
    ZERO(0, "000"),
    ONE(1, "001"),
    TWO(2, "002"),
    Thress(3, "003");

    private int id;
    private String title;

    private TestEnum(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static TestEnum getTestEnumById(int id) {
        switch (id) {
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return Thress;
        }
        return ZERO;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
