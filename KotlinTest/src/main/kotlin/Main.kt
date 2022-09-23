
import kotlin.reflect.KProperty

/**
 * @class Main
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-12 23:17
 */

class X{
    private var p:String? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String{
        return p!!
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>,value:String){
        p = value
    }
}


class A(name:String):K{

    override var a: String = ""

    var name:String by X()
}


interface K{
    var a:String
}

class Hello{

    fun say()
    {
        print("hello")
    }
}


var add5 = {i:Int-> i+5}
var multi = {i:Int-> i*2}

fun add4(i:Int):Int{
    return i+4
}

fun add(num1:Int,num2:Int)
{

}

fun main(args:Array<String>)
{
}

infix fun <P1,P2,R> Function1<P1,P2>.andThen(func:Function1<P2,R>):Function1<P1,R>{
    return fun(p1:P1):R{
        return func.invoke(this.invoke(p1))
    }
}