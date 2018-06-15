package example

import org.w3c.dom.get
import kotlin.browser.window

fun notifyDataChanges() {
    window.asDynamic()["__whenDataChanges__"]()
}

class Var<T>(var _value: T) {
    var value: T
        get () = _value
        set(newValue) {
            _value = newValue
            notifyDataChanges()
        }
}

fun defineWhenDataChanges(fn: () -> Unit) {
    window.asDynamic()["__whenDataChanges__"] = fn
}

fun main(args: Array<String>) {

    defineWhenDataChanges({
        window.alert("__whenDataChanges__ invoked")
    })

    val name = Var("Kotlin")
    name.value = "New value"
    name.value = "New value2"
}



