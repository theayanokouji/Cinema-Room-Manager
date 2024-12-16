package cinema

fun getInput(prompt: String): Int {
    var value = 0
    do {
        println(prompt)
        val strValue = readln()
        try {
            value = strValue.toInt()
            if (value > 9) {
                println("\nWrong input\n")
                continue
            }
            break
        } catch (e: NumberFormatException) {
            println("Please enter a valid integer")
        }
    } while (true)
    return value
}

fun main() {
    // write your code here
    val rows = getInput("Enter the number of rows:")
    val cols = getInput("Enter the number of seats in each row:")
    val theatre = Theatre(rows, cols)
    var hasBooked: Boolean
    var row: Int
    var col: Int
    do {
        val input = getInput("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        when (input) {
            1 -> theatre.displaySeats()
            2 -> {
                do {
                    row = getInput("Enter a row number:")
                    col = getInput("Enter a seat number in that row:")
                    hasBooked = theatre.bookSeat(row, col)
                } while (!hasBooked)
                println("\nTicket price: $${theatre.getTicketPrice(row)}\n")
            }
            3 -> theatre.displayStats()
            0 -> break
        }
    } while (true)
    //println("Total income:\n$%d".format(theatre.calculateProfit()))
}