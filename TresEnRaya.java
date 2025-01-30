import java.util.Random;
import java.util.Scanner;

public class TresEnRaya {
    
    private static final char VACIO = ' ';
    private static final char JUGADOR = 'X';
    private static final char BOT = 'O';
    
    public static void main(String[] args) {
        char[][] tablero = new char[3][3];
        inicializarTablero(tablero);

        boolean juegoTerminado = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("=== BIENVENIDO A TRES EN RAYA ===");
        imprimirTablero(tablero);

        boolean turnoJugador = true;


        while (!juegoTerminado) {
            if (turnoJugador) {
                System.out.println("Turno del jugador (" + JUGADOR + ").");
                realizarMovimientoJugador(tablero, sc);
            } else {
                System.out.println("Turno del bot (" + BOT + ").");
                realizarMovimientoBot(tablero);
            }

            imprimirTablero(tablero);

            char ganador = obtenerGanador(tablero);
            
            if (ganador != VACIO) {
                if (ganador == JUGADOR) {
                    System.out.println("¡HA GANADO EL JUGADOR!UwU (" + ganador + ")");
                } else {
                    System.out.println("¡HA GANADO EL BOT!UwU (" + ganador + ")");
                }
                juegoTerminado = true;
            } else if (tableroLleno(tablero)) {
                System.out.println("¡EMPATE!");
                juegoTerminado = true;
            }


            turnoJugador = !turnoJugador;
        }

        sc.close();
    }


    private static void inicializarTablero(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = VACIO;
            }
        }
    }


    private static void imprimirTablero(char[][] tablero) {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
              
                char caracter = (tablero[i][j] == VACIO) ? ' ' : tablero[i][j];
                System.out.print(" " + caracter + " ");
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

  
    private static void realizarMovimientoJugador(char[][] tablero, Scanner sc) {
        boolean movimientoValido = false;

        while (!movimientoValido) {
            System.out.print("Ingresa la fila (1-3): ");
            int fila = sc.nextInt() - 1;
            System.out.print("Ingresa la columna (1-3): ");
            int columna = sc.nextInt() - 1;


            if (fila < 0 || fila >= 3 || columna < 0 || columna >= 3) {
                System.out.println("Movimiento no válido (fuera del tablero). Inténtalo de nuevo.");
            } else if (tablero[fila][columna] != VACIO) {
                System.out.println("Esa casilla ya está ocupada. Inténtalo de nuevo.");
            } else {     
                tablero[fila][columna] = JUGADOR;
                movimientoValido = true;
            }
        }
    }


    private static void realizarMovimientoBot(char[][] tablero) {
        Random random = new Random();
        boolean movimientoValido = false;

        while (!movimientoValido) {
            int fila = random.nextInt(3);      
            int columna = random.nextInt(3);   

            if (tablero[fila][columna] == VACIO) {
                tablero[fila][columna] = BOT;
                movimientoValido = true;
            }
        }
    }


    private static char obtenerGanador(char[][] tablero) {
        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == tablero[i][1] &&
                tablero[i][1] == tablero[i][2] &&
                tablero[i][0] != VACIO) {
                return tablero[i][0];
            }
        }

        // Revisar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] == tablero[1][j] &&
                tablero[1][j] == tablero[2][j] &&
                tablero[0][j] != VACIO) {
                return tablero[0][j];
            }
        }
        if (tablero[0][0] == tablero[1][1] &&
            tablero[1][1] == tablero[2][2] &&
            tablero[0][0] != VACIO) {
            return tablero[0][0];
        }
        if (tablero[0][2] == tablero[1][1] &&
            tablero[1][1] == tablero[2][0] &&
            tablero[0][2] != VACIO) {
            return tablero[0][2];
        }

        return VACIO;
    }


    private static boolean tableroLleno(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == VACIO) {
                    return false;
                }
            }
        }
        return true;
    }
}
