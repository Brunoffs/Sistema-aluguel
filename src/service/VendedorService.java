package service;

import java.util.List;
import java.util.Scanner;

import model.Veiculo;
import model.Vendedor;
import repository.VendedorRepository;
import util.Normaliza;

public class VendedorService {
    Scanner sc;
    VendedorRepository repository = new VendedorRepository();

    public VendedorService(Scanner sc) {
        this.sc = sc;

        repository.salvar(new Vendedor("João", "joao@concessionaria.com.br", "Porto Alegre", "1234",2500));
        repository.salvar(new Vendedor("Maria", "maria@concessionaria.com.br", "Porto Alegre", "12345",2000));
        repository.salvar(new Vendedor("Pedro", "pedro@concessionaria.com.br", "Porto Alegre", "123456",3500));
    }

    public Vendedor ConfereEmail(String email) {
        List<Vendedor> vendedoresCadastrados = repository.BuscarTodos();
        for(Vendedor vendedor : vendedoresCadastrados) {
            if(vendedor.getEmail().equals(Normaliza.normalizaEmail(email))) {
                return vendedor;
            }
        }
        return null;
    }

    public boolean confereSenha(Vendedor vendedorParam, String senhaParam) {
        Vendedor vendedor = repository.buscarPorId(vendedorParam.getId());
        
        return vendedor.getSenha().equals(senhaParam);
    }
    
    public void verSalario(Vendedor vendedor) {
        System.out.println("O seu salário atual é: " + vendedor.getSalario());
    }

    public void retornaTodosVendedores() {
        List<Vendedor> vendedores = repository.BuscarTodos();

        for(Vendedor vendedor: vendedores) {
            System.out.println(vendedor.getId() + " - " + vendedor.getNome());
        }
    }

    public void salvarVeiculo(Veiculo veiculo, Integer idVendedor) {
        Vendedor vendedor = repository.buscarPorId(idVendedor);
        vendedor.getVeiculosAlugados().add(veiculo);

        repository.salvar(vendedor);
    }
    
    public void mostrarAlugueisVeiculos(Vendedor vendedor) {
        List<Veiculo> veiculos = vendedor.getVeiculosAlugados();

        for(Veiculo veiculo : veiculos) {
            System.out.println(veiculo);;
        }
     }

    public void verSalarioComComissao(Vendedor vendedor) {
        List<Veiculo> veiculos = vendedor.getVeiculosAlugados();
        double totalVendas = 0;

        for (Veiculo veiculo : veiculos) {
            totalVendas += veiculo.getValorLocacao();
        }

        double comissao = totalVendas * Vendedor.COMISSAO;
        System.out.println("Seu salário atual é: " + vendedor.getSalario());
        System.out.println("Sua comissão é: " + comissao);
        System.out.println("Seu salário com comissão é: " + (vendedor.getSalario() + comissao));
    }

    public void cadastrarVendedor() {
        System.out.println("Criando o seu cadastro!");
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();
        System.out.println("Digite seu email:");
        String email = sc.nextLine();
        System.out.println("Digite sua cidade: ");
        String cidade = sc.nextLine();
        System.out.println("Crie sua senha: ");
        String senha = sc.nextLine();
        System.out.println("Digite o salário: ");
        double salario = sc.nextDouble();
        Vendedor vendedor = new Vendedor(nome, email, cidade, senha, salario);
        repository.salvar(vendedor);
        System.out.println("Cadastro criado com sucesso!");
    }
}
