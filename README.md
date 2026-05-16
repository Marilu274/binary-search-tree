# 🌳 Árbol Binario de Búsqueda (BST) — Java

Implementación funcional de un **Binary Search Tree** desarrollada desde cero en Java, sin utilizar librerías nativas de árboles.

> **Estudiante:** Maria Eugenia Herrera Cifuentes
> **Carné:** 202605716
> **Curso:** Estructura de Datos
> **Universidad:** Da Vinci 

---

## ¿Qué es un BST?

Un Árbol Binario de Búsqueda es una estructura de datos jerárquica donde cada nodo cumple una regla fundamental: todos los valores del subárbol **izquierdo** son **menores** que el nodo actual, y todos los del subárbol **derecho** son **mayores**. Esto permite búsquedas, inserciones y eliminaciones muy eficientes.

---

## Métodos implementados

| Método | Descripción |
|--------|-------------|
| `insert(int value)` | Inserta un valor en la posición correcta respetando las reglas del BST |
| `search(int value)` | Retorna `true` si el valor existe, `false` si no |
| `delete(int value)` | Elimina un nodo contemplando los 3 casos críticos |
| `inOrder()` | Recorrido Izquierda → Raíz → Derecha (imprime valores en orden ascendente) |
| `preOrder()` | Recorrido Raíz → Izquierda → Derecha |
| `postOrder()` | Recorrido Izquierda → Derecha → Raíz |

### Los 3 casos de la eliminación

- **Caso 1 — Nodo hoja** (sin hijos): se elimina directamente retornando `null`.
- **Caso 2 — Un solo hijo**: el padre apunta directamente al hijo del nodo eliminado.
- **Caso 3 — Dos hijos**: se reemplaza el valor por el **sucesor in-order** (el menor valor del subárbol derecho) y luego se elimina ese sucesor.

---

## Complejidad Big-O

| Método | Mejor caso | Caso promedio | Peor caso |
|--------|-----------|---------------|-----------|
| `insert` | O(log n) | O(log n) | O(n) |
| `search` | O(log n) | O(log n) | O(n) |
| `delete` | O(log n) | O(log n) | O(n) |

> El **peor caso O(n)** ocurre cuando el árbol está degenerado (por ejemplo, insertar valores en orden ascendente 1, 2, 3, 4… genera una estructura equivalente a una lista enlazada). El **caso promedio O(log n)** aplica a árboles con distribución aleatoria de valores.

---

## Cómo ejecutar

### Requisitos
- Java JDK 8 o superior instalado

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/TU_USUARIO/binary-search-tree.git
cd binary-search-tree

# 2. Compilar
javac BinarySearchTree.java

# 3. Ejecutar
java BinarySearchTree
```

---

## Ejemplo de salida

```
>>> Insertando valores: 50, 30, 70, 20, 40, 60

--- Estructura del Árbol ---
└── 50
    ├── 30
    │   ├── 20
    │   └── 40
    └── 70
        └── 60
----------------------------

>>> Recorridos del árbol:
In-Order (Ascendente): 20 30 40 50 60 70
Pre-Order:             50 30 20 40 70 60
Post-Order:            20 40 30 60 70 50

>>> Búsquedas:
¿Existe 40? → true
¿Existe 99? → false

>>> CASO 1: Eliminar 20 (nodo hoja, sin hijos)
In-Order (Ascendente): 30 40 50 60 70

>>> CASO 2: Eliminar 60 (nodo con un solo hijo)
    → Después de eliminar 60, su hijo 65 ocupa su lugar.
In-Order (Ascendente): 30 40 50 65 70

>>> CASO 3: Eliminar 30 (nodo con dos hijos)
    → Reemplazado por su sucesor in-order: 35
In-Order (Ascendente): 35 40 50 65 70
```

---

## Video demostrativo

🎥https://youtu.be/-3CSNg1oa7M

---

## Estructura del proyecto

```
binary-search-tree/
└── BinarySearchTree.java   # Implementación completa del BST
└── README.md               # Este archivo
```
