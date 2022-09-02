package service;

import java.util.List;
import java.util.Scanner;

import model.Vendedor;
import repository.VendedorRepository;

public class VendedorService {
    Scanner sc;
    VendedorRepository repository = new VendedorRepository();

    public VendedorService(Scanner sc) {
        this.sc = sc;

        repository.salvar(new Vendedor("João", "joão@concessionaria.com.br", "Porto Alegre", "1234",2500));
        repository.salvar(new Vendedor("Maria", "maria@concessionaria.com.br", "Porto Alegre", "12345",2000));
        repository.salvar(new Vendedor("Pedro", "pedro@concessionaria.com.br", "Porto Alegre", "123456",3500));
    }

    public Vendedor ConfereEmail(String email) {
        List<Vendedor> vendedoresCadastrados = repository.BuscarTodos();
        for(Vendedor vendedor : vendedoresCadastrados) {
            if(vendedor.getEmail().equals(email)) {
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
}
