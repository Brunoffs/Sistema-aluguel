import java.util.Scanner;

import menu.Menu;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import service.AdministradorService;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;

public class App {
    public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    ClienteService clienteService = new ClienteService(sc);
    VeiculoService veiculoService = new VeiculoService(sc);
    VendedorService vendedorService = new VendedorService(sc);
    AdministradorService adminService = new AdministradorService(sc, veiculoService);
    boolean continua = true;
    do{
        Menu.menu1();
        int opcao1 = sc.nextInt();
        sc.nextLine();
        switch(opcao1) {
            case 1:
                Menu.menu2();
                String email = sc.nextLine();
                Cliente cliente = clienteService.ConfereEmail(email);
                boolean senhaCorreta = false;
                for (int i = 0; i < 3; i++) {
                    System.out.println("Agora digite a sua senha:");
                    String senha = sc.nextLine();
                    senhaCorreta = clienteService.confereSenha(cliente, senha);
                    if(!senhaCorreta) {
                        System.out.println("Senha incorreta, tente novamente (tentativa " + (i+1) + " de 3).");
                    }else {
                        break;
                    }
                }
            if(!senhaCorreta) {
                            break;
                }    
                Menu.menuCliente2();
                int opcao2 = sc.nextInt();

                if(opcao2 == 1) {
                    System.out.println("Digite o número referente ao veículo desejado: ");
                    veiculoService.buscarTodosVeiculosLivres();
                    int opcaoCarro = sc.nextInt();
                    Veiculo veiculo = veiculoService.alugarVeiculaPorId(opcaoCarro);
                    clienteService.alugarVeiculo(cliente, veiculo);
                    System.out.println("Digite o número do vendedor que lhe atendeu: ");
                    vendedorService.retornaTodosVendedores();
                    int opcaoVendedor = sc.nextInt();
                    vendedorService.salvarVeiculo(veiculo, opcaoVendedor);
                }else if (opcao2 == 2) {
                    System.out.println("Digite o número referente ao veículo desejado: ");
                    clienteService.buscarCarrosAlugados(cliente);

                    int opcaoCarro = sc.nextInt();
                    Veiculo veiculoDevolvido = veiculoService.devolverVeiculo(opcaoCarro);
                    clienteService.removerVeiculo(cliente, veiculoDevolvido);
                }
                break;
            case 2:
                Menu.menu2();
                email = sc.nextLine();
                Vendedor vendedor = vendedorService.ConfereEmail(email);
                senhaCorreta = false;
                for (int i = 0; i < 3; i++) {
                    System.out.println("Agora digite a sua senha:");
                    String senha = sc.nextLine();
                    senhaCorreta = vendedorService.confereSenha(vendedor, senha);
                    if(!senhaCorreta) {
                        System.out.println("Senha incorreta, tente novamente (tentativa " + (i+1) + " de 3)."); //fazer um método para verificar e não reétir codigo
                    }else {
                        break;
                    }
                }
            if(!senhaCorreta) {
                 break;
                }    
                Menu.menuVendedor1();
                opcao2 = sc.nextInt();
                if(opcao2 == 1) {
                    vendedorService.mostrarAlugueisVeiculos(vendedor);
                }else if(opcao2 == 2) {
                    vendedorService.verSalario(vendedor);
                }else if(opcao2 == 3) {
                    vendedorService.verSalarioComComissao(vendedor);
                }
            break;
            case 3:
                Menu.menuAdministrador();
                opcao2 = sc.nextInt();
                adminService.confereEntrada(opcao2);
            break;
            case 0:
                continua = false;
                break;
            default:
                System.out.println("Opção inválida! Tente novamente");
                break;
        }
    }while(continua);

    }
}
