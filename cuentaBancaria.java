package CuentaBancaria;
import java.util.Random;
import java.util.Scanner;

public class cuentaBancaria {
    double saldo;
    String tipoCuenta;
    String banco;
    String fechaVigencia;
    String numeroTarjeta;

    public cuentaBancaria(){
        this.saldo = 0;
        this.tipoCuenta = generarTipoCuenta();
        this.banco = generarBanco();
        this.fechaVigencia = generarVigencia();
        this.numeroTarjeta = generarNumeroCuenta();
}
    public cuentaBancaria(double saldo, String tipoCuenta, String banco, String fechaVigencia, String numeroTarjeta){
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.banco = banco;
        this.fechaVigencia = fechaVigencia;
        this.numeroTarjeta = numeroTarjeta;
    }
    public void datosBancarios(int indice){
        System.out.println("Cuenta: "+(indice+1));
        System.out.println("Banco: "+ banco);
        System.out.println("Tipo de cuenta: "+tipoCuenta);
        System.out.println("Saldo actual: "+saldo);
        System.out.println("Fecha de vigencia: "+fechaVigencia);
        System.out.println("Numero de cuenta: "+numeroTarjeta);
        System.out.println("*--------------------------------*");
    }

    public void deposito(int deposito){
        if (deposito > 0) {
                saldo += deposito;
                System.out.println("Depósito de $" + deposito + " realizado exitosamente.");
            } else {
                System.out.println("Error: El monto del depósito debe ser mayor que cero.");
            }
    }

    public void retiro(int retiro){
        if (retiro > 0) {
            if (saldo >= retiro) {
                saldo -= retiro;
                System.out.println("Retiro de $" + retiro + " realizado exitosamente.");
            } else {
                System.out.println("Error: Fondos insuficientes para realizar el retiro.");
            }
        } else {
            System.out.println("Error: El monto del retiro debe ser mayor que cero.");
        }
    }

    public void transferencia(int cuentaDestino, int monto, cuentaBancaria[] cuenta) {
        if (cuentaDestino <= cuenta.length){
            if (monto > 0 && saldo >= monto) {
                saldo -= monto;
                cuenta[cuentaDestino].deposito(monto);
                System.out.println("Transferencia de $" + monto + " a la cuenta " + cuenta[cuentaDestino].numeroTarjeta + " realizada exitosamente.");
            } else {
                System.out.println("Error en la transferencia: fondos insuficientes o monto inválido.");
            }
        } else{
            System.out.println("Cuenta no encontrada");
        }
    }

    public double consultaSaldo(){
        return(saldo);
    }
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    cuentaBancaria[] cuenta = new cuentaBancaria[5];
    cuenta[0] = new cuentaBancaria(10, "Debito", "Coppel", "08/24", (generarNumeroCuenta()));
    cuenta[0].datosBancarios(0);
    for (int i=1; i<cuenta.length; i++) {
        cuenta[i] = new cuentaBancaria();
        cuenta[i].datosBancarios(i);
    }
  


        int opcionCuenta = 0;
        do {
            System.out.println();
            System.out.println("Seleccione una cuenta:");
            System.out.println("1. Cuenta 1");
            System.out.println("2. Cuenta 2");
            System.out.println("3. Cuenta 3");
            System.out.println("4. Cuenta 4");
            System.out.println("5. Cuenta 5");
            System.out.println("6. Salir");
            opcionCuenta = sc.nextInt();

            switch (opcionCuenta) {
                case 1:
                    cuenta[0].menuOperaciones(sc, cuenta);
                    break;
                case 2:
                    cuenta[1].menuOperaciones(sc, cuenta);
                    break;
                case 3:
                    cuenta[2].menuOperaciones(sc, cuenta);
                    break;
                case 4:
                    cuenta[3].menuOperaciones(sc, cuenta);
                    break;
                case 5:
                    cuenta[4].menuOperaciones(sc, cuenta);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionCuenta != 6);
}

    public void menuOperaciones(Scanner sc, cuentaBancaria[] cuentaAuxiliar) {
        int opcionOperacion = 0;

        do {
            System.out.println("Seleccione una operación:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Transferir dinero");
            System.out.println("5. Cambiar datos");
            System.out.println("6. Volver al menú anterior");

            opcionOperacion = sc.nextInt();

            switch (opcionOperacion) {
                case 1:
                    System.out.println("Saldo actual: "+consultaSaldo());
                    break;
                case 2:
                    System.out.println("Ingrese el monto a depositar:");
                    int montoDeposito = sc.nextInt();
                    deposito(montoDeposito);
                    break;
                case 3:
                    System.out.println("Ingrese el monto a retirar:");
                    int montoRetiro = sc.nextInt();
                    retiro(montoRetiro);
                    break;
                case 4:
                    System.out.println("¿A qué cuenta deseas transferir el dinero?");
                    int numeroCuenta = sc.nextInt();

                    System.out.println("¿Cuanto deseas trasnferir?");
                    int transferencia = sc.nextInt();
                    transferencia((numeroCuenta-1), transferencia, cuentaAuxiliar);
                    break;
                case 5:
                    menuCambio(sc, cuentaAuxiliar);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionOperacion != 6);
    }

    public void menuCambio(Scanner sc, cuentaBancaria[] cuentaAuxiliar){
        int opcion;

        do {
            System.out.println("¿Qué datos deseas cambiar?");
            System.out.println("1. Saldo");
            System.out.println("2. Tipo de cuenta");
            System.out.println("3. Banco");
            System.out.println("4. Fecha de vigencia");
            System.out.println("5. Numero de la tarjeta");
            System.out.println("6. volver al menú anterior");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    setSaldo(sc.nextDouble());
                    break;
                case 2:
                    setTipoCuenta(sc.nextLine());
                    break;
                case 3:
                    setBanco(sc.nextLine());
                    break;
                case 4:
                    setFechaVigente(sc.nextLine());
                    break;
                case 5:
                    setNumeroTarjeta(sc.nextLine());
                    break;
                case 6:
                    break;
                default:
                System.out.println("Opcion no valida");
                    break;
            }
            
        } while (opcion != 6);

    }
//Generadores randoms
    public static String generarNumeroCuenta() {
        Random rng = new Random();    
        StringBuilder sb = new StringBuilder(19);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j<4; j++){
                    sb.append(rng.nextInt(10));
                }
                sb.append(" ");
            }
        return sb.toString();
    }

    public static String generarBanco() {
        Random rng = new Random();    
        String[] bancos = {"BBVA", "Citibanamex", "Coppel", "Azteca", "HSBC"};
        int indiceAleatorio = rng.nextInt(bancos.length);
        String nombreBanco = bancos[indiceAleatorio];
        return nombreBanco;
    }

    public static String generarVigencia() {
        Random rng = new Random();    
        StringBuilder sb = new StringBuilder(14);

        int mes = rng.nextInt(12) + 1;
        int año = rng.nextInt(11) + 24;
        sb.append(mes+ "/"+ año);
            
        return sb.toString();
    }

    public static String generarTipoCuenta() {
        Random rng = new Random();    
        String[] tipoCuenta = {"Debito", "Credito"};
        String cuenta = tipoCuenta[rng.nextInt(2)];
            
        return (cuenta);
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setFechaVigente(String fechaVigente) {
        this.fechaVigencia = fechaVigente;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        if (numeroTarjeta.length() == 19){
        this.numeroTarjeta = numeroTarjeta;
        }
        else {
            System.out.println("Error; por favor ingrese 16 digitos");
        }
    }

}