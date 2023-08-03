import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PruebaTecnicaTest {

    /*
     * Teniendo un vector de números, utilizando recursividad ordenar considerando 2 factores y considerando
     * la prioridad de los factores ordenar los números
     * Factor 1: Los números que tengan más divisores comunes
     * Factor 2: Los números de menor a mayor
     *Ejemplo
     *Input: 5, 14 y 21
     *Output: 14,21,5
     * */
    @Test
    public void sort() {
        assertArrayEquals(new int[]{3,14,21}, PruebaTecnica.sortArray(new int[]{3,14,21}));
        assertArrayEquals(new int[]{14,21,5}, PruebaTecnica.sortArray(new int[]{5,14,21}));
        assertArrayEquals(new int[]{3,14,21,5}, PruebaTecnica.sortArray(new int[]{5,14,3,21}));
    }
}