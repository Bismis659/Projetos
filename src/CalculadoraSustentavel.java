import java.util.ArrayList;
import java.util.Scanner;

public class CalculadoraSustentavel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(java.util.Locale.US);
        CalculadoraClimatica calc = new CalculadoraClimatica();
        while (true) {
            Menu.exibir();
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (opcao) {
                case 1:
                    System.out.print("Nome do hábito: ");
                    String nome = scanner.nextLine();
                    System.out.print("Tipo de gasto (1 - Água / 2 - Energia): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    double agua = 0;
                    double energia = 0;
                    if (tipo == 1) {
                        System.out.print("Consumo de água (litros): ");
                        agua = scanner.nextDouble();
                        scanner.nextLine();
                    } else {
                        System.out.print("Consumo de energia (kWh): ");
                        energia = scanner.nextDouble();
                        scanner.nextLine();
                    }
                    System.out.print("Dias de uso por mês: ");
                    int dias = scanner.nextInt();
                    System.out.print("É poluente? (true/false): ");
                    boolean poluente = scanner.nextBoolean();
                    scanner.nextLine();
                    Habito habito = new Habito(nome, tipo, agua, energia, dias, poluente);
                    calc.adicionarHabito(habito);
                    System.out.println();
                    System.out.println("Habito adicionado!");
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Nome do hábito: ");
                    String nomeRemover = scanner.nextLine();
                    Menu.remover(calc.getLista(), nomeRemover);
                    System.out.println();
                    break;
                case 3:
                    Menu.verHabitos(calc.getLista());
                    System.out.println();
                    break;
                case 4:
                    calc.consumoTotalAgua();
                    calc.consumoTotalEnergia();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
            }
        }
    }
}

class Habito {
    private String nomeHabito;
    private int tipoDoHabito;
    private double consumoEstimadoAgua;
    private double consumoEstimadoEnergia;
    private int diasDeUso;
    private boolean ePoluente;

    public String getNomeHabito() {
        return nomeHabito;
    }

    public void setNomeHabito(String nomeHabito) {
        this.nomeHabito = nomeHabito;
    }

    public int getTipoDoHabito() {
        return tipoDoHabito;
    }

    public void setTipoDoHabito(int tipoDoHabito) {
        this.tipoDoHabito = tipoDoHabito;
    }

    public double getConsumoEstimadoAgua() {
        return consumoEstimadoAgua;
    }

    public void setConsumoEstimadoAgua(double consumoEstimadoAgua) {
        this.consumoEstimadoAgua = consumoEstimadoAgua;
    }

    public double getConsumoEstimadoEnergia() {
        return consumoEstimadoEnergia;
    }

    public void setConsumoEstimadoEnergia(double consumoEstimadoEnergia) {
        this.consumoEstimadoEnergia = consumoEstimadoEnergia;
    }

    public int getDiasDeUso() {
        return diasDeUso;
    }

    public void setDiasDeUso(int diasDeUso) {
        this.diasDeUso = diasDeUso;
    }

    public boolean getePoluente() {
        return ePoluente;
    }

    public void setePoluente(boolean ePoluente) {
        this.ePoluente = ePoluente;
    }

    Habito(String nomeHabito, int tipoDoHabito, double consumoEstimadoAgua, double consumoEstimadoEnergia, int diasDeUso, boolean ePoluente) {
        this.nomeHabito = nomeHabito;
        this.tipoDoHabito = tipoDoHabito;
        this.consumoEstimadoAgua = consumoEstimadoAgua;
        this.consumoEstimadoEnergia = consumoEstimadoEnergia;
        this.diasDeUso = diasDeUso;
        this.ePoluente = ePoluente;
    }

    public double calcularConsumoAgua(int diasDeUso) {

        return consumoEstimadoAgua * diasDeUso;
    }

    public double calcularConsumoEnergia(int diasDeUso) {
        return consumoEstimadoEnergia * diasDeUso;
    }
}

class CalculadoraClimatica {
    private final ArrayList<Habito> lista = new ArrayList<>();

    public void adicionarHabito(Habito habito) {
        lista.add(habito);
    }

    public ArrayList<Habito> getLista() {
        return lista;
    }

    public void consumoTotalAgua() {
        double totalAgua = 0;
        for (Habito h : lista) {
            totalAgua += h.calcularConsumoAgua(h.getDiasDeUso());
        }
        System.out.println("Total de água: " + totalAgua + " L");
    }

    public void consumoTotalEnergia() {
        double totalEnergia = 0;
        for (Habito h : lista) {
            totalEnergia += h.calcularConsumoEnergia(h.getDiasDeUso());
        }
        System.out.println("Total de KWh: " + totalEnergia + " KWh");
    }
}

class Menu {
    public static void exibir() {
        System.out.println("1 - Adicionar habito");
        System.out.println("2 - Remover habito");
        System.out.println("3 - Ver todos os habitos");
        System.out.println("4 - Ver consumo total");
        System.out.println("0 - Sair");
    }

    public static void remover(ArrayList<Habito> lista, String nome) {
        boolean encontrou = false;
        for (Habito h : lista) {
            if (h.getNomeHabito().equals(nome)) {
                lista.remove(h);
                System.out.println();
                System.out.println("Hábito removido!");
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            System.out.println();
            System.out.println("Hábito não encontrado!");
        }
    }

    public static void verHabitos(ArrayList<Habito> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum hábito cadastrado!");
        } else {
            for (Habito l : lista) {
                System.out.println("- " + l.getNomeHabito());
            }
        }
    }
}