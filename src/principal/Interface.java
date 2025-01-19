package principal;

import java.util.Scanner;

import buscaminas.BuscaMinas;

public class Interface {
    public static final String RESET = "\033[0m";
    public static final String RED_BOLD = "\033[1;31m";
    Scanner sc = new Scanner(System.in);
    BuscaMinas busca;

    Interface() {
        busca = new BuscaMinas();
        presentarJuego();
        juego();
    }

    private void presentarJuego() {
        System.out.println("El juego coniste en destapar casillas de un tablero");
        System.out.println("La casilla destapada mostrará su contenido: ");
        System.out.println("\t- Si es un"+RED_BOLD+"*"+RESET+": corresponde a una mina, en cuyo caso perderemos la partida");
        System.out.println("\t- Valores de 0-8: indicarán la cantidad de minas que hay en las casillas adyacentes");
        System.out.println("El juego se gana si logramos destapar todas las casillas sin minas del tablero");
        System.out.println("\n VAMOS ALLÁ!!!!!");
    }

    // método que muestra el tablero
    private void mostrarTablero() {
        String[][] nuevo = new String[busca.getTableroNormal().length][busca.getTableroNormal().length];
        for (int i = 0; i < busca.getTableroCasillaDestapada().length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < busca.getTableroCasillaDestapada()[i].length; j++) {
                // si el tablero no tiene la casilla destapada aparece en blanco
                if (busca.getTableroCasillaDestapada()[i][j] == false) {
                    nuevo[i][j] = " ";
                } else {
                    // si está destapada mostrará los números
                    nuevo[i][j] = String.valueOf(busca.getTableroNormal()[i][j]);
                }
                System.out.print(nuevo[i][j] + " | ");
            }
            System.out.println("");
        }
    }

    private void tableroFinal() {
        String[][] nuevo = new String[busca.getTableroNormal().length][busca.getTableroNormal().length];
        for (int i = 0; i < busca.getTableroNormal().length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < busca.getTableroNormal()[i].length; j++) {
                nuevo[i][j] = String.valueOf(busca.getTableroNormal()[i][j]);
                if (busca.getTableroNormal()[i][j] == 9) {
                    nuevo[i][j] = RED_BOLD+"*"+RESET;
                }
                if(busca.getTableroNormal()[i][j] == 0){
                    nuevo[i][j] = "\u25A0";
                }
                System.out.print(nuevo[i][j] + " | ");
            }
            System.out.println("");
        }
    }

    private void juego() {
        // se repite mientras no se fin de juego
        do {
            mostrarTablero();
            System.out.println("Indique posición de la casilla que quiere destapar (fila y columna):");
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            busca.turnoJugador(fila, columna);
        } while (!busca.isFinDeJuego());

        if (busca.quienHaGanado() == "Jugador") {
            System.out.println("\n");
            System.out.println("##########################################################################");
            System.out.println("#                                                                        #");
            System.out.println("#  VV   VV  IIIIII   CCCCC  TTTTTTTT   OOOOO   RRRRRR   IIIIII   AAAAA   #");
            System.out.println("#  VV   VV    II    CC         TT     OO   OO  RR   RR    II    AA   AA  #");
            System.out.println("#  VV   VV    II    CC         TT     OO   OO  RR   RR    II    AA   AA  #");
            System.out.println("#  VV   VV    II    CC         TT     OO   OO  RRRRRR     II    AAAAAAA  #");
            System.out.println("#   V   V     II    CC         TT     OO   OO  RR   RR    II    AA   AA  #");
            System.out.println("#    V V      II    CC         TT     OO   OO  RR   RR    II    AA   AA  #");
            System.out.println("#     V     IIIIII   CCCCC     TT      OOOOO   RR   RR  IIIIII  AA   AA  #");
            System.out.println("#                                                                        #");
            System.out.println("##########################################################################");
            System.out.println("\n");
        } else {
            System.out.println("\n");
            System.out.println("###################################################################################");
            System.out.println("#                                                                                 #");
            System.out.println("#   GGGGGG   AAAAA    MM   MM    EEEEE         OOOOO   VV   VV   EEEEE   RRRRRR   #");
            System.out.println("#  GG       AA   AA  MM M M MM  EE            OO   OO  VV   VV  EE       RR   RR  #");
            System.out.println("#  GG       AA   AA  MM M M MM  EE            OO   OO  VV   VV  EE       RR   RR  #");
            System.out.println("#  GG  GGG  AAAAAAA  MM  M  MM  EEEEE         OO   OO  VV   VV  EEEEE    RRRRRR   #");
            System.out.println("#  GG   GG  AA   AA  MM     MM  EE            OO   OO   V   V   EE       RR   RR  #");
            System.out.println("#  GG   GG  AA   AA  MM     MM  EE            OO   OO    V V    EE       RR   RR  #");
            System.out.println("#   GGGGG   AA   AA  MM     MM   EEEEE         OOOOO      V      EEEEE   RR   RR  #");
            System.out.println("#                                                                                 #");
            System.out.println("###################################################################################");
            System.out.println("\n");
        }
        tableroFinal();
    }

    public static void main(String[] args) {
        new Interface();
    }

}