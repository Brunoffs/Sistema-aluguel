package service;


import java.util.List;
import java.util.Scanner;

import model.Administrador;
import repository.AdministradorRepository;
import util.Normaliza;


public class AdministradorService {
    
    VeiculoService veiculoService;
    AdministradorRepository repository = new AdministradorRepository();
    Scanner sc;
    private VendedorService vendedorService;

    public AdministradorService(Scanner sc, VeiculoService veiculoService, VendedorService vendedorService) {
        this.sc = sc;
        this.repository.salvar(new Administrador("admin", "admin@admin.com", "Porto Alegre", "1111"));
        this.veiculoService = veiculoService;
        this.vendedorService = vendedorService;
    }
    public void confereEntrada(int entrada) {
        sc.nextLine();
        if(entrada==1) {
            veiculoService.cadastrarVeiculo();
        }else if(entrada == 2) {
            this.removerVeiculo();
        }else if(entrada == 3) {
            vendedorService.cadastrarVendedor();
        }else if(entrada == 4) {
            this.removerVendedor();
        }
    }
    public void removerVeiculo() {
        System.out.println("Todos os veículos cadastrados e livres no sistema: ");
        veiculoService.buscarTodosVeiculosLivres();
        int opcaoVeiculo = sc.nextInt();
        veiculoService.veiculoRepository.removerPorId(opcaoVeiculo);
        System.out.println("Veículo removido com sucesso!");
    }
    public void removerVendedor() {
        System.out.println("Todos os vendedores cadastrados no sistema: ");
        vendedorService.retornaTodosVendedores();
        int opcaoVendedor = sc.nextInt();
        vendedorService.repository.removerPorId(opcaoVendedor);;
        System.out.println("Vendedor removido com sucesso!");
    }
    public Administrador ConfereEmail(String email) {
        List<Administrador> administradoresCadastrados = repository.BuscarTodos();
        for(Administrador administrador : administradoresCadastrados) {
            if(administrador.getEmail().equals(Normaliza.normalizaEmail(email))) {
                return administrador;
            }
        }
        return null;
    }
    public boolean confereSenha(Administrador administradorParam, String senhaParam) {
        Administrador administrador = repository.buscarPorId(administradorParam.getId());
        
        return administrador.getSenha().equals(senhaParam);
    }
}