package br.edu.up.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.daos.GerenciadorDeArquivo;
import br.edu.up.models.Pessoa;

public class Menu {

    public void mostrar() throws IOException {

        GerenciadorDeArquivo gerenciador = new GerenciadorDeArquivo();

        List<Pessoa> listaPessoas = new ArrayList<>();
        listaPessoas.add(new Pessoa(1, "João", "Rua A", "São Paulo"));
        listaPessoas.add(new Pessoa(2, "Maria", "Rua B", "Rio de Janeiro"));

        boolean sucesso = gerenciador.gravar(listaPessoas);

        if (sucesso) {
            System.out.println("Dados gravados com sucesso no arquivo PessoasEnderecos.csv!");
        } else {
            System.out.println("Erro ao gravar os dados.");
        }
    }

}
