package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Veiculo;
import model.Veiculo.Status;
import repository.VeiculoRepository;

public class VeiculoService {
    Scanner sc;
    VeiculoRepository veiculoRepository = new VeiculoRepository();

    public VeiculoService(Scanner sc) {
        this.sc = sc;
    }
    
    public void cadastrarVeiculo() {
        System.out.println("Digite o modelo do veículo:");
        String modelo = sc.nextLine();
        System.out.println("Digite a marca do veículo:");
        String marca = sc.nextLine();
        System.out.println("Digite a cor do veículo:");
        String cor = sc.nextLine();
        System.out.println("Digite a placa do veículo:");
        String placa = sc.nextLine();
        System.out.println("Digite o segmento do veículo:");
        String tipo = sc.nextLine();
        System.out.println("Digite o valor de locação do veículo:");
        double valorLocacao = sc.nextDouble();

        Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, tipo, valorLocacao);

        this.veiculoRepository.salvar(veiculo);

        System.out.println("Veículo cadastrado com sucesso!");
    }

    public void buscarTodosVeiculosLivres() {
        List<Veiculo> todosVeiculos = this.veiculoRepository.BuscarTodos();

        for(Veiculo veiculo : todosVeiculos) {
            if(veiculo.getStatus() == Status.LIVRE) {
                System.out.println(veiculo);
            }
        }
    }

    public Veiculo alugarVeiculaPorId(int id) {
        Veiculo veiculo = this.veiculoRepository.buscarPorId(id);

        veiculo.setStatus(Status.ALUGADO);

        this.veiculoRepository.salvar(veiculo);

        return veiculo;
    }    
}
