import java.util.Scanner;

import menu.Menu;
import model.Cliente;
import repository.AdministradorRepository;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import repository.VendedorRepository;
import service.AdministradorService;
import service.ClienteService;

public class App {
    public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    ClienteService clienteService = new ClienteService(sc);
    AdministradorService adminService = new AdministradorService(sc);
    boolean continua = true;
    do{
        Menu.menu1();
        int opcao1 = sc.nextInt();
        sc.nextLine();
        switch(opcao1) {
            case 1:
                Menu.menuCliente1();
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
                break;
            case 2:
                Menu.menuVendedor1();
                int opcao2 = sc.nextInt();
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
