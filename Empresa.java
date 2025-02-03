import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empresa {


    private static final List<Empleado> empleados = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


        static class Empleado {
        private String nombre;
        private int edad;
        private String fechaAlta;
        private double salario; 

        public Empleado(String nombre, int edad, String fechaAlta, double salario) {
            this.nombre = nombre;
            this.edad = edad;
            this.fechaAlta = fechaAlta;
            this.salario = salario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public String getFechaAlta() {
            return fechaAlta;
        }

        public void setFechaAlta(String fechaAlta) {
            this.fechaAlta = fechaAlta;
        }

        public double getSalario() {
            return salario;
        }

        public void setSalario(double salario) {
            this.salario = salario;
        }

        @Override
        public String toString() {
            return "Empleado{" +
                    "nombre='" + nombre + '\'' +
                    ", edad=" + edad +
                    ", fechaAlta='" + fechaAlta + '\'' +
                    ", salario=" + salario +
                    '}';
        }
    }

    
    static class MandoIntermedio extends Empleado {
        private double comision;

        public MandoIntermedio(String nombre, int edad, String fechaAlta, double salario, double comision) {
            super(nombre, edad, fechaAlta, salario);
            this.comision = comision;
        }

        public double getComision() {
            return comision;
        }

        public void setComision(double comision) {
            this.comision = comision;
        }

        @Override
        public String toString() {
            return String.format("MandoIntermedio [Nombre=%s, Edad=%d, FechaAlta=%s, Salario=%.2f, Comision=%.2f]",
                    getNombre(), getEdad(), getFechaAlta(), getSalario(), comision);
        }
    }


    public static void main(String[] args) {
        
        empleados.add(new Empleado("Juan", 30, "2023-01-14", 1300.0));
        empleados.add(new Empleado("Pepe", 27, "2022-04-07", 1100.0));
        empleados.add(new Empleado("Javier", 32, "2023-09-04", 1500.0));
        empleados.add(new MandoIntermedio("Lucia", 43, "2022-01-21", 4300.0, 400.0));
        empleados.add(new MandoIntermedio("Julio", 50, "2021-11-04", 5000.0, 600.0));

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opcion: ");
            switch (opcion) {
                case 1:
                    altaEmpleado();
                    break;
                case 2:
                    listarEmpleados();
                    break;
                case 3:
                    modificarSalario();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida, intentalo de nuevo..");
            }
        } while (opcion != 5);

        scanner.close();
    }

   
    private static void mostrarMenu() {
        System.out.println("=== MENU CRUD EMPLEADOS ===");
        System.out.println("1) Dar de alta empleado");
        System.out.println("2) Listar empleados");
        System.out.println("3) Modificar salario");
        System.out.println("4) Eliminar empleado");
        System.out.println("5) SALIR");
    }

   
    private static void altaEmpleado() {
        System.out.println("=== ALTA DE EMPLEADO ===");
        String nombre = leerCadena("Nombre: ");
        int edad = leerEntero("Edad: ");
        String fechaAlta = leerCadena("Fecha de alta (EJEMPLO: 2023-06-06): ");
        double salario = leerDouble("Salario: ");

        String respuesta = leerCadena("¿Es mando intermedio? (Y/N): ");
        if (respuesta.equalsIgnoreCase("Y")) {
            double comision = leerDouble("Comision: ");
            MandoIntermedio mi = new MandoIntermedio(nombre, edad, fechaAlta, salario, comision);
            empleados.add(mi);
        } else {
            Empleado emp = new Empleado(nombre, edad, fechaAlta, salario);
            empleados.add(emp);
        }

        System.out.println("Empleado añadido correctamente");
    }

    private static void listarEmpleados() {
        System.out.println("=== LISTA DE EMPLEADOS ===");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
        } else {
            for (int i = 0; i < empleados.size(); i++) {
                System.out.println((i + 1) + ") " + empleados.get(i).toString());
            }
        }
    }


    private static void modificarSalario() {
        System.out.println("=== MODIFICAR SALARIO ===");
        listarEmpleados();

        if (!empleados.isEmpty()) {
            int indice = leerEntero("Seleccione el numero de empleado a modificar: ") - 1;
            if (indice >= 0 && indice < empleados.size()) {
                double nuevoSalario = leerDouble("Ingresa el nuevo salario: ");
                empleados.get(indice).setSalario(nuevoSalario);
                System.out.println("Salario actualizado correctamente.");
            } else {
                System.out.println("Indice fuera de rango");
            }
        }
    }


    private static void eliminarEmpleado() {
        System.out.println("=== ELIMINAR EMPLEADO ===");
        listarEmpleados();

        if (!empleados.isEmpty()) {
            int indice = leerEntero("Selecciona el numero de empleado a eliminar: ") - 1;
            if (indice >= 0 && indice < empleados.size()) {
                empleados.remove(indice);
                System.out.println("Empleado eliminado correctamente.");
                listarEmpleados();
            } else {
                System.out.println("Indice fuera de rango");
            }
        }
    }

    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un numero entero valido");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un valor numerico valido");
            }
        }
    }
}
