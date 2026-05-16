/**
 * Árbol Binario de Búsqueda (Binary Search Tree - BST)
 * Implementación en Java desde cero - sin librerías nativas de árboles
 *
 * Autor: María Eugenia Herrera Cifuentes
 * Descripción: Implementación completa de un BST que almacena enteros con los
 *              métodos de inserción, búsqueda, eliminación y tres tipos de recorrido.
 */
public class BinarySearchTree {

    // =========================================================================
    // CLASE INTERNA: Nodo del árbol
    // =========================================================================
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // =========================================================================
    // ATRIBUTO PRINCIPAL: Raíz del árbol
    // =========================================================================
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    // =========================================================================
    // MÉTODO 1: INSERCIÓN
    // Inserta un nuevo valor respetando las reglas del BST:
    //   - Menores que el nodo actual van a la izquierda
    //   - Mayores van a la derecha
    // Complejidad: O(h), donde h es la altura del árbol.
    //              En el mejor caso O(log n), en el peor O(n).
    // =========================================================================
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        // Caso base: posición vacía encontrada → crear nuevo nodo aquí
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            // El valor es menor → va hacia la izquierda
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            // El valor es mayor → va hacia la derecha
            current.right = insertRecursive(current.right, value);
        }
        // Si value == current.value, ignoramos el duplicado

        return current; // Devolvemos el nodo sin cambiar
    }

    // =========================================================================
    // MÉTODO 2: BÚSQUEDA
    // Retorna true si el valor existe en el árbol, false si no existe.
    // Complejidad: O(h) — O(log n) árbol balanceado, O(n) árbol degenerado.
    // La búsqueda en un BST es eficiente porque en cada paso descartamos
    // la mitad del árbol (similar a búsqueda binaria en arreglos).
    // =========================================================================
    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node current, int value) {
        // Caso base 1: llegamos a un nodo vacío → el valor no está
        if (current == null) {
            return false;
        }

        // Caso base 2: encontramos el valor
        if (value == current.value) {
            return true;
        }

        // Si es menor, buscamos en el subárbol izquierdo; si es mayor, en el derecho
        if (value < current.value) {
            return searchRecursive(current.left, value);
        } else {
            return searchRecursive(current.right, value);
        }
    }

    // =========================================================================
    // MÉTODO 3: ELIMINACIÓN
    // Contempla los tres casos críticos:
    //   CASO 1: Nodo hoja (sin hijos) → se elimina directamente.
    //   CASO 2: Nodo con un solo hijo → el padre apunta al hijo del eliminado.
    //   CASO 3: Nodo con dos hijos → se reemplaza por su sucesor in-order
    //           (el menor valor del subárbol derecho), luego se elimina ese sucesor.
    // Complejidad: O(h).
    // =========================================================================
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        // Caso base: el valor no existe en el árbol
        if (current == null) {
            System.out.println("Valor " + value + " no encontrado en el árbol.");
            return null;
        }

        if (value < current.value) {
            // El valor a eliminar está en el subárbol izquierdo
            current.left = deleteRecursive(current.left, value);

        } else if (value > current.value) {
            // El valor a eliminar está en el subárbol derecho
            current.right = deleteRecursive(current.right, value);

        } else {
            // ¡Encontramos el nodo a eliminar!

            // --- CASO 1: Nodo hoja (sin hijos) ---
            if (current.left == null && current.right == null) {
                return null; // Se elimina simplemente devolviendo null
            }

            // --- CASO 2: Nodo con UN solo hijo ---
            if (current.left == null) {
                return current.right; // Sustituimos por su hijo derecho
            }
            if (current.right == null) {
                return current.left;  // Sustituimos por su hijo izquierdo
            }

            // --- CASO 3: Nodo con DOS hijos ---
            // Estrategia: encontrar el sucesor in-order (el menor del subárbol derecho)
            // El sucesor es el valor más pequeño que es mayor que el nodo actual,
            // garantizando que el árbol siga siendo un BST válido después de la sustitución.
            int successorValue = findMinValue(current.right);
            current.value = successorValue; // Reemplazamos el valor
            // Eliminamos el sucesor de su posición original (siempre será Caso 1 o 2)
            current.right = deleteRecursive(current.right, successorValue);
        }

        return current;
    }

    // Método auxiliar: encuentra el valor mínimo de un subárbol (el más a la izquierda)
    private int findMinValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    // =========================================================================
    // MÉTODO 4: RECORRIDO IN-ORDER (Izquierda → Raíz → Derecha)
    // Imprime los valores en orden ASCENDENTE. Propiedad fundamental del BST.
    // =========================================================================
    public void inOrder() {
        System.out.print("In-Order (Ascendente): ");
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node current) {
        if (current != null) {
            inOrderRecursive(current.left);
            System.out.print(current.value + " ");
            inOrderRecursive(current.right);
        }
    }

    // =========================================================================
    // MÉTODO 5: RECORRIDO PRE-ORDER (Raíz → Izquierda → Derecha)
    // Útil para copiar/serializar el árbol (preserva la estructura).
    // =========================================================================
    public void preOrder() {
        System.out.print("Pre-Order:            ");
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node current) {
        if (current != null) {
            System.out.print(current.value + " ");
            preOrderRecursive(current.left);
            preOrderRecursive(current.right);
        }
    }

    // =========================================================================
    // MÉTODO 6: RECORRIDO POST-ORDER (Izquierda → Derecha → Raíz)
    // Útil para eliminar el árbol completo o calcular expresiones.
    // =========================================================================
    public void postOrder() {
        System.out.print("Post-Order:           ");
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(Node current) {
        if (current != null) {
            postOrderRecursive(current.left);
            postOrderRecursive(current.right);
            System.out.print(current.value + " ");
        }
    }

    // =========================================================================
    // MÉTODO EXTRA: Imprimir el árbol visualmente en consola
    // Útil para visualizar la estructura durante la defensa en video.
    // =========================================================================
    public void printTree() {
        System.out.println("\n--- Estructura del Árbol ---");
        printTreeRecursive(root, "", true);
        System.out.println("----------------------------\n");
    }

    private void printTreeRecursive(Node current, String prefix, boolean isLeft) {
        if (current != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + current.value);
            printTreeRecursive(current.left,  prefix + (isLeft ? "│   " : "    "), true);
            printTreeRecursive(current.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    // =========================================================================
    // MAIN: Menú interactivo
    // =========================================================================
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcion;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Árbol Binario de Búsqueda - Java   ║");
        System.out.println("╚══════════════════════════════════════╝");

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar valor");
            System.out.println("2. Buscar valor");
            System.out.println("3. Eliminar valor");
            System.out.println("4. Mostrar árbol");
            System.out.println("5. Recorridos (In-Order, Pre-Order, Post-Order)");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    int valIns = scanner.nextInt();
                    bst.insert(valIns);
                    System.out.println("✓ Valor " + valIns + " insertado.");
                    bst.printTree();
                    break;

                case 2:
                    System.out.print("Valor a buscar: ");
                    int valBus = scanner.nextInt();
                    boolean encontrado = bst.search(valBus);
                    System.out.println(encontrado
                        ? "✓ El valor " + valBus + " SÍ existe en el árbol."
                        : "✗ El valor " + valBus + " NO existe en el árbol.");
                    break;

                case 3:
                    System.out.print("Valor a eliminar: ");
                    int valDel = scanner.nextInt();
                    bst.delete(valDel);
                    bst.printTree();
                    break;

                case 4:
                    bst.printTree();
                    break;

                case 5:
                    bst.inOrder();
                    bst.preOrder();
                    bst.postOrder();
                    break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}