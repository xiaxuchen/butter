import java.io.File;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class Test
 * @Description
 * @createTime 2019-06-13 1:42
 */
public class Test {

    @org.junit.Test
    public void temp(){
        System.out.println((new File("/F:/program files/apache-tomcat-8.5.34-windows-x64/apache-tomcat-8.5.34/webapps/homework/WEB-INF/classes/com/xxc/homework/controller/").exists()));
    }
}
