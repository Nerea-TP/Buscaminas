# Instrucciones

Buscaminas es un clásico juego de lógica en el que el jugador tiene que descubrir celdas de un campo de minas sin hacer estallar ninguna. El campo está dividido en una cuadrícula de filas y columnas, y algunas celdas contienen minas ocultas. El objetivo del juego es revelar todas las celdas que no contienen minas, mientras se evita hacer clic en una celda que contenga una mina.

Cada celda puede tener un número que indica la cantidad de minas que están en las celdas adyacentes. Usando esta información, el jugador debe deducir la ubicación de las minas y evitarlas.

Este proyecto está dividido en dos paquetes:

1. **Paquete Principal (principal)**

- Contiene la clase InterfaceConsola, que maneja la interfaz de usuario en la consola, permitiendo al jugador interactuar con el juego.
- Incluye el método main() para iniciar el juego y gestionar las entradas del jugador.

2. **Paquete Buscaminas (buscaminas)**

- Contiene la clase BuscaMinas, que implementa la lógica principal del juego, como la creación del campo de juego, el control de las minas, el conteo de minas adyacentes y la validación de las jugadas.

>Las filas y columnas del campo de juego comienzan en 0.
El jugador debe introducir las coordenadas de la celda que desea descubrir en el formato "**fila columna**" (con un espacio entre el número de la fila y el de la columna). Ejemplo:
Para la celda en la fila 5 y columna 6, se debe ingresar: 5 6.
Asegúrate de ingresar las coordenadas correctamente para evitar errores durante el juego.