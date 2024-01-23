/**
 * Limpiar consola.
 * @param num va a ser el número de filas que vamos a 'limpiar'
 * @return Los saltos de línea que se van a hacer para limpiar la consola.
 */
fun clearConsole(num: Int) {
    for (clrLn in 1..num){
        println()
    }
}

/**
 * Imprimir fila.
 * @param row Es una de las filas del tablero.
 * @return Deberá devolver un espacio en blanco, una 'X' o una 'O',
 *          según si no ha sido ocupada o ha sido por alguno de los jugadores.
 */
fun printRow(row: IntArray) {
    println("-------------")
    println(" " + row.joinToString("  ") { if (it == 0) " " else if (it == 1) "X" else "O" })
}

/**
 * Imprimir tablero.
 * @param board Recibimos el tablero
 * @return Devuelve la impresión del tablero.
 */
fun printBoard(board: Array<IntArray>) {
    clearConsole(20)
    println("-------------")
    board.forEach { printRow(it) }
    println("-------------")
}

/**
 * Crear tablero:
 * @param size es el número de líneas y columnas que forman el tablero.
 * @return Devuelve el tablero vacío de 3x3
 */
fun createBoard(size: Int = 3) = Array(size) { IntArray(size) }

fun checkWinner(board: Array<IntArray>): Int {
    return 0
}

/**
 * Tablero completo:
 * @return Devolverá el valor booleano True si el tablero está completo, sino devolverá False.
 */
fun isBoardFull(board: Array<IntArray>): Boolean {
    return board.all { row -> row.all { it != 0 } }
}

fun placePiece(board: Array<IntArray>, player: Int) {
    var salir = false
    while (!salir) {
        //Agregamos la condición de salir del programa si se presiona enter sin introducir datos.
        val input = readln()
        if (input.isBlank()){
            println("¡Gracias por jugar!")
            salir = true
        }

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

/**
 * @param player el jugador en partida
 * @return Intercambian sus números para indicar el cambio de jugador.
 */
fun switchPlayer(player: Int): Int {
    return if (player == 1) 2 else 1
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

    var winner: Int
    var endGame = false

    while (!endGame) {
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