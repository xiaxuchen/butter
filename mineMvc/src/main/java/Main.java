import com.xxc.util.parser.json.JSONParser;
import com.xxc.util.parser.json.exception.JSONParseException;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class Main
 * @Description
 * @createTime 2019-05-28 17:59
 */
public class Main {

    static class User{

        private String name;

        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    public static void main(String[] args)
    {
        User u = new User();
        u.setName("hahah");
        u.setAge(10);
        try {
            System.out.println(JSONParser.Companion.getInstance().toJson(u));
        } catch (JSONParseException e) {
            e.printStackTrace();
        }
    }
}
