package umg.edu.progra.bst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Walter Cordova
 */
public class BST implements IBST<Empleado> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BST.class);

    private Empleado valor;
    private BST izdo, dcho;
    private BST padre;

    private void insertarImp(Empleado emp, BST padre) {
        if (valor == null) {
            this.valor = emp;
            this.padre = padre;
        } else {
            if (emp.compareTo(valor) < 0) {
                if (izdo == null) {
                    izdo = new BST();
                }
                izdo.insertarImp(emp, this);
            } else if (emp.compareTo(valor) > 0) {
                if (dcho == null) {
                    dcho = new BST();
                }
                dcho.insertarImp(emp, this);
            } else {
                throw new RuntimeException("Insertando elemento duplicado");
            }
        }
    }

    @Override
    public void insertar(Empleado emp) {
        insertarImp(emp, null);
    }

    @Override
    public boolean existe(int id) {
        if (valor != null) {
            if (id == valor.getId()) {
                return true;
            } else if (id < valor.getId() && izdo != null) {
                return izdo.existe(id);
            } else if (id > valor.getId() && dcho != null) {
                return dcho.existe(id);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Empleado obtener(int id) {
        if (valor != null) {
            if (id == valor.getId()) {
                return valor;
            } else if (id < valor.getId() && izdo != null) {
                return izdo.obtener(id);
            } else if (id > valor.getId() && dcho != null) {
                return dcho.obtener(id);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean esHoja() {
        return valor != null && izdo == null && dcho == null;
    }

    @Override
    public boolean esVacio() {
        return valor == null;
    }

    @Override
    public void preOrden() {
        preordenImpl("");
    }

    @Override
    public void postOrden() {
        postordenImpl("");
    }

    @Override
    public void inOrden() {
        inordenImpl("");
    }

    private void inordenImpl(String prefijo) {
        if (izdo != null) {
            izdo.inordenImpl(prefijo + "  ");
        }
        LOGGER.info("{}{}", prefijo, valor);
        if (dcho != null) {
            dcho.inordenImpl(prefijo + "  ");
        }
    }

    private void postordenImpl(String prefijo) {
        if (izdo != null) {
            izdo.postordenImpl(prefijo + "  ");
        }
        if (dcho != null) {
            dcho.postordenImpl(prefijo + "  ");
        }
        LOGGER.info("{}{}", prefijo, valor);
    }

    private void preordenImpl(String prefijo) {
        if (valor != null) {
            LOGGER.info("{}{}", prefijo, valor);
            if (izdo != null) {
                izdo.preordenImpl(prefijo + "  ");
            }
            if (dcho != null) {
                dcho.preordenImpl(prefijo + "  ");
            }
        }
    }

    private void eliminarImpl(int id) {
        if (izdo != null && dcho != null) {
            // eliminar con 2 hijos
            BST sucesor = dcho.minimo();
            valor = sucesor.valor;
            dcho.eliminar(sucesor.valor.getId());
        } else if (izdo != null || dcho != null) {
            // eliminar con 1 hijo
            BST hijo = izdo != null ? izdo : dcho;
            if (padre != null) {
                if (this == padre.izdo) {
                    padre.izdo = hijo;
                } else {
                    padre.dcho = hijo;
                }
                hijo.padre = padre;
            } else {
                valor = hijo.valor;
                izdo = hijo.izdo;
                dcho = hijo.dcho;
            }
        } else {
            // eliminar con 0 hijos
            if (padre != null) {
                if (this == padre.izdo) {
                    padre.izdo = null;
                }
                if (this == padre.dcho) {
                    padre.dcho = null;
                }
                padre = null;
            }
            valor = null;
        }
    }

    @Override
    public void eliminar(int id) {
        if (valor != null) {
            if (id == valor.getId()) {
                // eliminar valor
                eliminarImpl(id);
            } else if (id < valor.getId() && izdo != null) {
                izdo.eliminar(id);
            } else if (id > valor.getId() && dcho != null) {
                dcho.eliminar(id);
            }
        }
    }

    private BST minimo() {
        BST actual = this;
        while (actual.izdo != null) {
            actual = actual.izdo;
        }
        return actual;
    }
}

