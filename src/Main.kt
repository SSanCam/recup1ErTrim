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
    var linea = ("| " + row.joinToString("  ") { if (it == 0) "| |" else if (it == 1) "| X |" else "| O |" })
}

/**
 * Imprimir tablero.
 * @param board Recibimos el tablero
 * @return Devuelve la impresión del tablero.
 */
fun printBoard(board: Array<IntArray>) {
    clearConsole(3)
    board.forEach { printRow(it) }
}

/**
 * Crear tablero:
 * @param size es el número de líneas y columnas que forman el tablero.
 * @return Devuelve el tablero vacío de 3x3
 */
fun createBoard(size: Int = 3) = Array(size) { IntArray(size) }

/**
 * Compronar ganador:
 * @param Recibimos el tablero
 * @return Comprobamos si algún jugador a conseguido 3 en línea o ha sido empate
 */
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

/**
 * Colocar pieza:
 * @param board Reibimos el tablero.
 * @param player recibimos quién es el jugador actual
 * @return Actualiza el tablero en la posición que indica el jugador actual
 */
fun placePiece(board: Array<IntArray>, player: Int) {
    var salir = false
    while (!salir) {
        try {
            print("Elige la fila (1, 2, 3): ")
            val row = readln()
            if (row.isBlank()){
                println("¡Gracias por jugar!")
                salir = true
            }else{
                row.toInt().minus(1)
            }

            print("Elige la columna (1, 2, 3): ")
            val col = readln()
            if (col.isBlank()){
                println("¡Gracias por jugar!")
                salir = true

            }else{
                (col.toInt().minus(1)).toString()
            }

            if (row.toInt() in 1 until 4 && col.toInt() in 1 until 4 && (board[row.toInt()][col.toInt()]).toString() == " ") {
                board[row.toInt()][col.toInt()] = player
                salir = true

            } else {
                println("**Error** Movimiento inv�lido. Int�ntalo de nuevo.")
            }
        }catch (e: NumberFormatException){
            println("ENTRADA INVALIDA.")
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

/**
 * Función principal de la partida que ejecuta todas las funciones anteriores en orden.
 *
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