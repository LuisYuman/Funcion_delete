package umg.edu.progra.bst;

import org.junit.Test;
import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void testEliminarNodoSinHijos() {
        // Crear el árbol con un nodo
        BST arbol = new BST();
        Empleado emp = new Empleado(1, "Juan");
        arbol.insertar(emp);

        // Eliminar el nodo sin hijos
        arbol.eliminar(emp.getId());

        // Verificar que el árbol esté vacío
        assertTrue(arbol.esVacio());
    }

    @Test
    public void testEliminarNodoConUnHijo() {
        // Crear el árbol
        BST arbol = new BST();
        Empleado emp1 = new Empleado(1, "Juan");
        Empleado emp2 = new Empleado(2, "Pedro");
        arbol.insertar(emp1);
        arbol.insertar(emp2); // emp2 será hijo de emp1

        // Eliminar el nodo con un hijo
        arbol.eliminar(emp1.getId());

        // Verificar que el nodo emp2 sea la nueva raíz del árbol
        assertFalse(arbol.esVacio());
        assertNull(arbol.obtener(emp1.getId()));
        assertNotNull(arbol.obtener(emp2.getId()));
        assertNull(arbol.obtener(emp1.getId()));
    }

    @Test
    public void testEliminarNodoConDosHijos() {
        // Crear el árbol
        BST arbol = new BST();
        Empleado emp1 = new Empleado(1, "Juan");
        Empleado emp2 = new Empleado(2, "Pedro");
        Empleado emp3 = new Empleado(3, "Carlos");
        arbol.insertar(emp2);
        arbol.insertar(emp1);
        arbol.insertar(emp3); // emp1 y emp3 serán hijos de emp2

        // Eliminar el nodo con dos hijos
        arbol.eliminar(emp2.getId());

        // Verificar que el árbol siga siendo un BST válido
        assertFalse(arbol.esVacio());
        assertNull(arbol.obtener(emp2.getId()));
        assertNotNull(arbol.obtener(emp1.getId()));
        assertNotNull(arbol.obtener(emp3.getId()));
    }

    @Test
    public void testEliminarRaiz() {
        // Crear el árbol
        BST arbol = new BST();
        Empleado emp1 = new Empleado(1, "Juan");
        Empleado emp2 = new Empleado(2, "Pedro");
        Empleado emp3 = new Empleado(3, "Carlos");
        arbol.insertar(emp2);
        arbol.insertar(emp1);
        arbol.insertar(emp3);

        // Eliminar la raíz del árbol
        arbol.eliminar(emp2.getId());

        // Verificar que el árbol siga siendo un BST válido
        assertFalse(arbol.esVacio());
        assertNull(arbol.obtener(emp2.getId()));
        assertNotNull(arbol.obtener(emp1.getId()));
        assertNotNull(arbol.obtener(emp3.getId()));
    }

    @Test
    public void testEliminarNodoInexistente() {
        // Crear el árbol
        BST arbol = new BST();
        Empleado emp1 = new Empleado(1, "Juan");
        Empleado emp2 = new Empleado(2, "Pedro");
        Empleado emp3 = new Empleado(3, "Carlos");
        arbol.insertar(emp1);
        arbol.insertar(emp2);
        arbol.insertar(emp3);

        // Eliminar un nodo que no existe
        arbol.eliminar(10);

        // Verificar que el árbol siga siendo un BST válido
        assertFalse(arbol.esVacio());
        assertNotNull(arbol.obtener(emp1.getId()));
        assertNotNull(arbol.obtener(emp2.getId()));
        assertNotNull(arbol.obtener(emp3.getId()));
    }
}



