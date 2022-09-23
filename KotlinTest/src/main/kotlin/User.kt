import com.xxc.mvc.annotations.controller.Controller
import com.xxc.mvc.annotations.mapping.Mapping

/**
 * @class User
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-12 23:20
 */
@Controller
open class User {
    
    var name:String = ""

    var age:Int = 0

    @Mapping("/user")
    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }


}