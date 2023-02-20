enum class Beverage(val visibleName: String, val water: Int, val milk: Int, val coffeeBeans: Int, val price: Int) {
    ESPRESSO("espresso", 250, 0, 16, 4),
    LATTE("latte", 350, 75, 20, 7),
    CAPPUCCINO("cappuccino", 200, 100, 12, 6),
}

class CoffeeMachine() {
    var price: Int = 550
    var water: Int = 400
    var milk: Int = 540
    var coffeeBeans: Int = 120
    var disposableCups: Int = 9
    var state = ""
}

fun CoffeeMachine.getMessage() {
    println("Write action (buy, fill, take, remaining, exit):")
    state = readln()

    println()
    when (state) {
        "buy" -> bayCoffee()
        "fill" -> fillCoffeeMachine()
        "take" -> takeMoney()
        "remaining" -> printInformation()
    }
    println()
}

fun CoffeeMachine.printInformation() {
    println("The coffee machine has:")
    println("$water ml of water")
    println("$milk ml of milk")
    println("$coffeeBeans g of coffee beans")
    println("$disposableCups disposable cups")
    println("$$price of money")
}

fun CoffeeMachine.bayCoffee() {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    val answer = readln()

    val coffee = Beverage.valueOf(
        when (answer) {
            "1" -> "ESPRESSO"
            "2" -> "LATTE"
            "3" -> "CAPPUCCINO"
            else -> return
        }
    )

    if (quantityCheck(coffee)) {
        water -= coffee.water
        milk -= coffee.milk
        coffeeBeans -= coffee.coffeeBeans
        price += coffee.price
        disposableCups -= 1
        println("I have enough resources, making you a coffee!")
    }
}

fun CoffeeMachine.quantityCheck(coffee: Beverage): Boolean {
    println(
        "Sorry, not enough  " + if (water < coffee.water) "water!"
        else if (milk < coffee.milk) "milk!"
        else if (coffeeBeans < coffee.coffeeBeans) "coffee beans!"
        else if (disposableCups < 1) "cups!"
        else return true
    )
    return false
}

fun CoffeeMachine.fillCoffeeMachine() {
    println("Write how many ml of water you want to add:")
    water += readln().toInt()

    println("Write how many ml of milk you want to add:")
    milk += readln().toInt()

    println("Write how many grams of coffee beans you want to add:")
    coffeeBeans += readln().toInt()

    println("Write how many disposable cups you want to add:")
    disposableCups += readln().toInt()
}

fun CoffeeMachine.takeMoney() {
    println("I gave you $$price")
    price = 0
}

fun main() {
    val machine = CoffeeMachine()
    do {
        machine.getMessage()
    } while (machine.state != "exit")
}