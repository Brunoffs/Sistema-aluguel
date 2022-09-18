package service;

import java.util.List;
import java.util.Scanner;

import exceptions.SistemaException;
import model.Cliente;
import model.Veiculo;
import repository.ClienteRepository;
import util.Normaliza;

public class ClienteService {
    Scanner sc;
    ClienteRepository repository = new ClienteRepository();

    public Cliente ConfereEmail(String email) {
        List<Cliente> clientesCadastrados = repository.BuscarTodos();
        for(Cliente cliente : clientesCadastrados) {
            if(cliente.getEmail().equals(Normaliza.normalizaEmail(email))) {
                return cliente;
            }
        }

        return this.cadastrarCliente(email);
    }
    public ClienteService(Scanner sc) {
        this.sc = sc;
        this.repository.salvar(new Cliente("Eduardo", "eduardo@gmail.com", "Canoas", "1234"));
    }

    private Cliente cadastrarCliente(String email) {
        System.out.println("Criando o seu cadastro!");
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();
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

    public void buscarCarrosAlugados(Cliente cliente) {
        List<Veiculo> veiculosAlugados = cliente.getVeiculos();
        for(Veiculo veiculo : veiculosAlugados) {
            System.out.println(veiculo);
        }
    }

    public void removerVeiculo(Cliente clienteParam, Veiculo veiculoParam) throws SistemaException {
         Cliente cliente = this.repository.buscarPorId(clienteParam.getId());
         if(cliente == null) {
            throw new SistemaException("Veículo não encontrado");
        }
         cliente.getVeiculos().remove(veiculoParam);
         this.repository.salvar(clienteParam);
    }
}
