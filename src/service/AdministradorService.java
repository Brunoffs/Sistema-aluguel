package service;


import java.util.Scanner;

import model.Administrador;

import repository.AdministradorRepository;


public class AdministradorService {
    
    VeiculoService veiculoService;
    AdministradorRepository repository = new AdministradorRepository();
    Scanner sc;

    public AdministradorService(Scanner sc, VeiculoService veiculoService) {
        this.sc = sc;
        this.repository.salvar(new Administrador("admin", "admin@admin.com", "Porto Alegre", "1111"));
        this.veiculoService = veiculoService;
    }
    public void confereEntrada(int entrada) {
        sc.nextLine();
        if(entrada==1) {
            veiculoService.cadastrarVeiculo();
        }else if(entrada == 2) {
            this.removerVeiculo();
        }
    }
    public void removerVeiculo() {
        System.out.println("Todos os veículos cadastrados e livres no sistema: ");
        veiculoService.buscarTodosVeiculosLivres();
        int opcaoVeiculo = sc.nextInt();
        veiculoService.veiculoRepository.removerPorId(opcaoVeiculo);
        System.out.println("Veículo removido com sucesso!");
    }

}