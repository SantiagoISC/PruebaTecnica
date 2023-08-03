import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PruebaTecnica {
    /**
     * Se divide en 3 partes el algoritmo
     *     1: Se obtienen las listas de los divisores de cada numero de la lista
     *     2: Se obtiene el conteo de cada numero divisor para cada numero de la lista
     *     3: Se ordena bajo los 2 factores
     * @param numeros a ordenar
     * @return array ordenado
     */
    public static int[] sortArray(int[] numeros) {
        Map<Integer, List<Integer>> mapaDivisores = new HashMap<>();
        Map<Integer, List<Integer>> divisores = numeroDivisores(numeros, numeros.length, mapaDivisores);
        Map<Integer, Integer> divisoresComunes = obtenerDivisoresComunes(divisores);
        return sort(numeros, divisoresComunes, numeros.length);
    }

    /**
     * Se llama al metodo divisores para obtener la lista de los divisores y guardarlos eb un mapa
     * @param arr array para obtener la lista de sus divisores
     * @param n logintud del array
     * @param map guardar la lista de divisores de cada elemento del array
     * @return mapa de los divisores de cada elemento
     */
    private static Map<Integer, List<Integer>> numeroDivisores(int[] arr, int n, Map<Integer, List<Integer>> map) {
        if (n == 0) {
            return map;
        }
        map.put(arr[n - 1], divisores(arr[n - 1]));
        return numeroDivisores(arr, n - 1, map);
    }

    /**
     * Se obtienen las listas de los divisores de cada numero
     * @param number elemento para obtener sus divisores
     * @return lista de los divisores
     */
    private static List<Integer> divisores(int number) {
        return IntStream.rangeClosed(1, number).filter(i -> number % i == 0)
                .boxed().collect(Collectors.toList());
    }

    /**
     * Se busca el numero maximo divisor entre las listas de divisores de cada elemento del array,
     * buscando los numeros divisores de una listaA en una listaB y obtener el maximo
     * @param numberList mapa con los numeros divisores
     * @return mapa con el numero de divisores comunes
     */
    private static Map<Integer, Integer> obtenerDivisoresComunes(Map<Integer, List<Integer>> numberList) {
        Map<Integer, Integer> divisoresComunesCount = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : numberList.entrySet()) {
            int keyA = entry.getKey();
            List<Integer> listA = entry.getValue();
            int maxValor = 0;
            for (Map.Entry<Integer, List<Integer>> newEntry : numberList.entrySet()) {
                int keyB = newEntry.getKey();
                List<Integer> listB = newEntry.getValue();
                if (keyA != keyB) {
                    int count = divisoresComunes(listA, listB);
                    if (count > maxValor) maxValor = count;
                }
            }
            divisoresComunesCount.put(keyA, maxValor);
        }
        return divisoresComunesCount;
    }

    /**
     * Obtiene el conteo de cada divisor comun de cada elemento de la listaA en la listaB
     * @param listA divisores a buscar
     * @param listB divisores a comparar con listA
     * @return numero de divisores comunes
     */
    private static int divisoresComunes(List<Integer> listA, List<Integer> listB) {
        return (int)listA.stream().filter(listB::contains).count();
    }

    /**
     * Ordena el array dependiento de dos factores, se ordenan primero los numero con mayor numero de
     * divisores comunes y de forma ascendente
     * @param array para ordenar
     * @param map mapa de todos los numeros divisores comunes
     * @param n longitud del array
     * @return array ordenado
     */
    private static int[] sort(int[] array, Map<Integer, Integer> map, int n) {
        if (n == 1) {
            return array;
        }
        for (int i = 0; i < n - 1; i++) {
            if (map.get(array[i]) < map.get(array[i + 1]) ||
                    (map.get(array[i]).equals(map.get(array[i + 1])) && array[i] > array[i + 1])) {
                swap(array, i, i + 1);
            }
        }
        return sort(array, map, n - 1);
    }

    /**
     * Invierte los valores de dos elementos del array con las posiciones i y j
     * @param array para intercambiar los elementos
     * @param i posicion a intercambiar
     * @param j posicion a intercambiar
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
