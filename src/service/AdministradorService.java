package service;


import java.util.Scanner;

import model.Administrador;
import model.Veiculo;
import repository.AdministradorRepository;
import repository.VeiculoRepository;

public class AdministradorService {
    
    AdministradorRepository repository = new AdministradorRepository();
    Scanner sc;
    VeiculoRepository veiculoRepository = new VeiculoRepository();

    public AdministradorService(Scanner sc) {
        this.sc = sc;
        this.repository.salvar(new Administrador("admin", "admin@admin.com", "Porto Alegre", "1111"));
    }
    public void confereEntrada(int entrada) {
        sc.nextLine();
        if(entrada==1) {
            this.cadastrarVeiculo();
        }
    }
    private void cadastrarVeiculo() {
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
        System.out.println("Digite o valor de locação do veículo");
        double valorLocacao = sc.nextDouble();

        Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, tipo, valorLocacao);

        this.veiculoRepository.salvar(veiculo);

        System.out.println("Veículo cadastrado com sucesso!");
    }

}