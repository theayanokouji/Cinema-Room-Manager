package cinema

class Theatre(
    private var rows: Int,
    private var cols: Int
) {
    // 2D list representing the theatre seats
    private var seats = mutableListOf<MutableList<Char>>()
    private var purchasedTickets = 0
    private var currentIncome = 0

    // initialize the 2D lists with empty seats
    init {
        for (i in 0 until rows) {
            val list = mutableListOf<Char>()
            for (j in 0 until cols) {
                list.add('S')
            }
            // add the list to the 2D list
            seats.add(list)
        }
    }

    // print the sitting arrangement
    fun displaySeats() {
        println()
        println("Cinema:")
        // display the cols
        print("  ")
        for (i in 0 until cols) {
            if (i != cols - 1) {
                print("${i + 1} ")
            } else {
                print("${i + 1}")
            }
        }
        println()
        // display seats
        for (i in 0 until rows) {
            print("${i + 1} ")
            for (j in 0 until cols) {
                if (j != cols - 1) {
                    print("${seats[i][j]} ")
                } else {
                    print("${seats[i][j]}")
                }
            }
            // move the cursor to the next line
            println()
        }
        println()
    }

    fun calculateProfit(): Int {
        val totalSeats = rows * cols
        return when {
            totalSeats <= 60 -> {
                totalSeats * 10 // 10 dollars each seat
            }
            else -> {
                val firstHalf = rows / 2
                val secondHalf = rows - firstHalf
                firstHalf * cols * 10 + secondHalf * cols * 8
            }
        }
    }

    /**
     * @param row - the row in the theatre
     * @param col - the seat in that row
     * @return the price of the ticket
     */
    fun bookSeat(
        row: Int,
        col: Int
    ): Boolean {
        return if (seats[row - 1][col - 1] == 'B') {
            println("\nThat ticket has already been purchased!\n")
            false
        } else {
            seats[row - 1][col - 1] = 'B'
            purchasedTickets++
            // calculate current income
            currentIncome += getTicketPrice(row)
            true
        }
    }

    private fun calcTotalIncome(): Int {
        var total = 0
        for (i in 1..rows) {
            for (j in 1..cols) {
                total += getTicketPrice(i)
            }
        }
        return total
    }

    /**
     * @param row - the row in the theatre
     * @return the price of the ticket
     */
    fun getTicketPrice(
        row: Int
    ): Int {
        // check in which half the seat resides in, first or second half
        val totalSeats = rows * cols
        var ticketPrice = 0
        if (totalSeats <= 60) {
            ticketPrice = 10
        } else {
            val firstHalf = rows / 2
            ticketPrice = if (row in 1..firstHalf) {
                10
            } else {
                8
            }
        }
        return ticketPrice
    }

    fun displayStats() {
        /*
        Number of purchased tickets: 0
        Percentage: 0.00%
        Current income: $0
        Total income: $360
        * */
        var percentage = purchasedTickets.toDouble() / (rows * cols)
        percentage *= 100
        println()
        println("Number of purchased tickets: $purchasedTickets")
        println("Percentage: %.2f".format(percentage).plus("%"))
        println("Current income: $$currentIncome")
        println("Total income: $${calcTotalIncome()}")
        println()
    }
}