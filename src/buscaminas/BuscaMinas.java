package buscaminas;

import java.util.Random;
/**
 * @author Nerea Trillo Pérez
 * @version v1.0
 * @since 26/02/2023
 */
public class BuscaMinas {
    //variable filas, columnas y numero minas colocadas en el tablero
    private int filas;
    private int columnas;
    private int numMinas;
    //minas que vamos destapando durante la partida
    private int casillasDestapadas;
    // creamos dos tableros uno donde estarán los valores 0-9
    private int[][] tableroNormal;
    // otro donde nos informa si la casilla está destapada
    private boolean[][] tableroCasillaDestapada;
    //variable fin de Juego
    private boolean finDeJuego;

    /**
     * Crea un tablero de 10 filas x 10 columnas, donde se colocarán 10 minas
     */
    public BuscaMinas() {
        this(10, 10, 10);
    }

    /**
     * Crea el tablero del buscaminas
     * 
     * @param filas    número de filas del tablero
     * @param columnas número de columnas del tablero
     * @param numMinas número de minas que se colocan en el tablero
     */
    public BuscaMinas(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        //inicializo variables
        casillasDestapadas=0;
        finDeJuego=false;
        //tamaño de los arrays
        tableroNormal=new int[filas][columnas];
        tableroCasillaDestapada=new boolean[filas][columnas];
        colocarMinas();
    }

    /**
     * Informa si el juego acabó
     * @return True si el juego acabó
     */
    public boolean isFinDeJuego() {
        return finDeJuego;
    }

    /**
     * Devuelve el tablero normal con los valores 0 a 9
     * 
     * @return el tablero con los valores, según las minas que tengan cerca
     */
    public int[][] getTableroNormal() {
        // se crea copia del tablero para devolver esa copia y seguir el principio de
        // ocultación
        int[][] copiaTableroNormal = tableroNormal.clone();
        return copiaTableroNormal;
    }

    /**
     * Devuelve el tablero de casillas destapadas.
     * 
     * @return True si la casilla está destapada
     */
    public boolean[][] getTableroCasillaDestapada() {
        // se crea copia del tablero para devolver esa copia y seguir el principio de
        // ocultación
        boolean[][] copiaTableroCasillaDestapada = tableroCasillaDestapada.clone();
        return copiaTableroCasillaDestapada;
    }

    // método que coloca las minas, se colocan aleatoriamente
    private void colocarMinas() {
        int minasColocada = 0;
        Random ran = new Random();
        while (minasColocada != numMinas) {
            // insertamos filas y columnas aleatoriamente
            int maquinaFila = ran.nextInt(filas);
            int maquinaColumna = ran.nextInt(columnas);
            if (tableroNormal[maquinaFila][maquinaColumna] != 9) {
                // coloco mina con el número 9
                tableroNormal[maquinaFila][maquinaColumna] = 9;
                // incremento minas colocadas
                minasColocada++;
                /*incremento numero minas alrededor, usamos Math-max y 
                * Math-min como en las celdas adyacentes
                */
                for (int i = Math.max(0, maquinaFila - 1); i < Math.min(tableroNormal.length, maquinaFila + 2); i++) {
                    for (int j = Math.max(0, maquinaColumna - 1); j < Math.min(tableroNormal[i].length,
                            maquinaColumna + 2); j++) {
                        // si en esa posición no hay mina, aumentamos los valores de esa posición
                        if (tableroNormal[i][j] != 9) {
                            tableroNormal[i][j]++;
                        }
                    }
                }
            }
        }
    }

    // método donde el jugador destapa casilla
    /**
     * Movimiento del jugador que indica que casilla del tablero quiere destapar
     * si la casilla tiene mina se acaba el juego, si la casilla ya está destapada (no
     * pasa nada y se indica otra casilla), sino, se destapa la casilla
     * 
     * @param fila    número de fila indicada por el jugador
     * @param columna número de columna indicada por el jugador
     */
    public void turnoJugador(int fila, int columna) {
        // si la casilla destapada ya hay mina, se acabó el juego
        if (tableroNormal[fila][columna] == 9) {
            finDeJuego = true;
        }
        // si la casilla ya está destapada, se tiene que volver a introducir otra fila y columna
        if (tableroCasillaDestapada[fila][columna] == true) {
            return;
            // sino se procede a destapar la casilla con un método recursivo
        } else {
            destaparCasillas(fila, columna);
        }
    }

    // método recursvivo que destapa casillas
    private void destaparCasillas(int fila, int columna) {
        // casos bases
        if (finDeJuego)
            return;// fin de juego
        if (tableroCasillaDestapada[fila][columna] == true)
            return;// ya destapada
        if (tableroNormal[fila][columna] == 9) {
            return;// es mina
    // si no es mina tiene valores de 0-8, por lo que destapamos
        } else {
            // destapo casilla
            tableroCasillaDestapada[fila][columna] = true;
             // se aumenta el numero de casillasDestapadas
             casillasDestapadas++;
            
            // si el numero de la casilla no es 0, se para recursividad
            if (tableroNormal[fila][columna] != 0)
                return;
           
            // si llegamos hasta aqui es que la celda tiene valor 0 y se miran los vecinos
            // de alrededor
            for (int i = Math.max(0, fila - 1); i < Math.min(tableroNormal.length, fila + 2); i++) {
                for (int j = Math.max(0, fila - 1); j < Math.min(tableroNormal[i].length, fila + 2); j++) {
                    destaparCasillas(fila, columna);
                }
            }
            /*si se llega a destapar 90 casillas se para recursivdad, el buscaminas es 10x10
             * por lo que tiene 100 casillas y se colocan 10 minas, por lo que
             *solo hay 90 casillas sin minas y si se destapan todas se acaba el juego
             */
            if (casillasDestapadas==90) {
                finDeJuego=true;
                return;
              }
        }
    }

    /**
     * Nos indica quien ha ganado
     * 
     * @return Jugador cuando ganamos, Máquina cuando perdemos, No se ha acabado el juego
     */
    public String quienHaGanado() {
        // el juego se acaba cuando la variable finDeJuego es true
        if (finDeJuego) {
            /*El juego se acaba porque ganó la máquina o porque logramos destapar las 90
             * casillas y ganamos nosotros (jugador)
             */
            if (casillasDestapadas == 90) {
                return "Jugador";
            } else {
                return "Máquina";
            }
        } else {
            return "No se ha acabado";
        }
    }

}
