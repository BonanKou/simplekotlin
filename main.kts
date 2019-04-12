// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(x:Any):String {
    when (x) {
        1 -> return ("one")
        0 -> return ("zero")
        "Hello" -> return("world")
        is String -> return("Say what?")
        in 2..10 -> return("low number")
        is Int -> return("a number")
        else -> return("I don't understand")
    }
}


// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(left:Int, right:Int):Int {
    return left + right;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(left:Int, right:Int):Int {
    return left-right
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(left:Int, right:Int, agent:(left:Int, right:Int)->Int):Int {
    return agent(left, right)
}
// write a class "Person" with first name, last name and age
class Person(var firstName:String, val lastName:String, var age:Int) {
    var debugString:String="def"
        get() {
            return "[Person firstName:$firstName lastName:$lastName age:$age]"
        }
    fun equals(other:Person):Boolean {
        if (this.firstName == other.firstName && this.lastName == other.lastName && this.age==other.age)
            return true
        else return false
    }

    override fun hashCode():Int {
        var hash = 7
        hash = 31 * hash + age
        hash = 31 * hash + firstName.hashCode()
        hash = 31 * hash + lastName.hashCode()
        return hash

    }

}

class Money(val amount:Int, val currency:String) {
    init {
        if ((currency != "USD" && currency != "CAN" && currency != "EUR" && currency != "GBP")||amount < 0) {
            throw Exception("Wrong")
        }
    }
    operator fun plus(other: Money):Money {
        var other2 = other
        if (this.currency != other.currency) {
            other2 = other.convert(this.currency)
        }
        return Money(this.amount + other2.amount, this.currency)
    }

    fun convert(otherCur: String):Money {
        var result = this.amount
        when (this.currency) {
            "USD" ->
                when(otherCur) {
                    "USD" -> result = result
                    "GBP" -> result = result / 2
                    "CAN" -> result = result * 15 / 12
                    "EUR" -> result = result * 15 / 10
                }
            "GBP" ->
                when(otherCur) {
                    "USD" -> result=result*2
                    "GBP" -> result=result
                    "CAN" -> result=result*30/12
                    "EUR" -> result=result*3
                }
            "CAN" ->
                when(otherCur) {
                    "USD" -> result=result*12/15
                    "GBP" -> result=result*12/15/2
                    "CAN" -> result=result
                    "EUR" -> result=result*12/10
                }
            "EUR" ->
                when(otherCur) {
                    "USD" -> result=result*10/15
                    "GBP" -> result=result*10/15/2
                    "CAN" -> result= result*10/12
                    "EUR" -> result=result
                }
        }
        return Money(result, otherCur)
    }
}
// write a class "Money"

// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
        "Hello" to "world",
        "Howdy" to "Say what?",
        "Bonjour" to "Say what?",
        0 to "zero",
        1 to "one",
        5 to "low number",
        9 to "low number",
        17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
        Pair(0, 0) to 0,
        Pair(1, 2) to 3,
        Pair(-2, 2) to 0,
        Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
        Pair(0, 0) to 0,
        Pair(2, 1) to 1,
        Pair(-2, 2) to -4,
        Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
        Pair(tenUSD, tenUSD),
        Pair(tenUSD, fiveGBP),
        Pair(tenUSD, fifteenEUR),
        Pair(twelveUSD, fifteenCAN),
        Pair(fiveGBP, tenUSD),
        Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
        Pair(tenUSD, tenUSD) to Money(20, "USD"),
        Pair(tenUSD, fiveGBP) to Money(20, "USD"),
        Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
            (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
