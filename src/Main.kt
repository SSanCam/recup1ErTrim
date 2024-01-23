
fun clearConsole(num: Int) {

}

fun printRow(row: IntArray) {
    println(" " + row.joinToString("  ") { if (it == 0) " " else if (it == 1) "X" else "O" })
}

fun printBoard() {
    clearConsole()
    println("-------------")
    board.forEach { printRow(it) }
}

fun createBoard(size: Int = 3) = Array(size) { IntArray(size) }

fun checkWinner(board: Array<IntArray>): Int {
    return 0
}

fun isBoardFull(board: Array<IntArray>): Boolean {
    return board.all { row -> row.all { it != 0 } }
}

fun placePiece(board: Array<IntArray>, player: Int) {
    var salir = false
    while (!salir) {
        print("Elige la fila (1, 2, 3): ")
        val row = readln().toInt().minus(1)

        print("Elige la columna (1, 2, 3): ")
        val col = readln().toInt().minus(1)

        if (row in 1..3 && col in 1..3 && (board[row][col]).toString() == " ") {
            board[row][col] = player
            salir = true
        } else {
            println("**Error** Movimiento inv�lido. Int�ntalo de nuevo.")
        }
    }
}

fun switchPlayer(player: Int): String {
    return if (player == 1) "Jugador 1" else "Jugador2"
}



/*
* Corregir los errores para que el juego funcione.
* Documentar el código.
* Realizar las siguientes mejoras o modificaciones en el código:
    1. Salir del juego en cualquier momento si pulso la tecla ENTER sin introducir nada.
    2. Incluir el texto: Turno del jugador 1 ó 2 según sea el caso.
    3. Incluye una función para limpiar la consola... cómo no es posible en Kotlin, te propongo que tu método reciba
    un número y hagas un bucle dónde se imprima por consola un salto de línea tantas veces cómo el número que recibe.
    4. Modifica el programa para controlar con try-catch errores a la hora de introducir la posición en el tablero,
    muestre un mensaje de error y vuelva a pedir la posición (fila o columna).
    5. El juego debe mostrar el tablero cómo el siguiente:
    -------------
    | X |   |   |
    -------------
    |   | O |   |
    -------------
    |   |   | X |
    -------------
    6. Desarrolla la función que está solo declarada "checkWinner", para comprobar si algún jugador ha ganado.
*/


fun main() {
    val board = createBoard()
    var currentPlayer = 1

    var winner = 0
    var endGame = false

    while (endGame) {
        printBoard(board)
        placePiece(board, currentPlayer)

        winner = checkWinner(board)
        if (winner != 0) {
            printBoard(board)
            println("�El jugador $winner ha ganado!")
            endGame = true
        } else if (isBoardFull(board)) {
            printBoard(board)
            println("El juego ha terminado en empate.")
            endGame = true
        }

        currentPlayer = switchPlayer(currentPlayer)
    }
}