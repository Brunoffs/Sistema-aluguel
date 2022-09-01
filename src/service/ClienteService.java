package service;

import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Veiculo;
import repository.ClienteRepository;

public class ClienteService {
    Scanner sc;
    ClienteRepository repository = new ClienteRepository();

    public Cliente ConfereEmail(String email) {
        List<Cliente> clientesCadastrados = repository.BuscarTodos();
        for(Cliente cliente : clientesCadastrados) {
            if(cliente.getEmail().equals(email)) {
                return cliente;
            }
        }

        return this.cadastrarCliente();
    }
    public ClienteService(Scanner sc) {
        this.sc = sc;
    }

    private Cliente cadastrarCliente() {
        System.out.println("Criando o seu cadastro!");
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();
        System.out.println("Digite seu email:");
        String email = sc.nextLine();
        System.out.println("Digite sua cidade:");
        String cidade = sc.nextLine();
        System.out.println("Crie sua senha:");
        String senha = sc.nextLine();
        System.out.println("Cadastro criado com sucesso!");

        Cliente cliente = new Cliente(nome, email, cidade, senha);
        this.repository.salvar(cliente);
        return cliente;
    }

    public boolean confereSenha(Cliente clienteParam, String senha) {
        Cliente cliente = repository.buscarPorId(clienteParam.getId());
        
        return cliente.getSenha().equals(senha);
    }

    public void alugarVeiculo(Cliente cliente, Veiculo veiculo) {
        cliente.getVeiculos().add(veiculo);
        this.repository.salvar(cliente);
    }
}
