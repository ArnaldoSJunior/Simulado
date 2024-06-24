package br.edu.up.daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.models.Pessoa;

public class GerenciadorDeArquivo {

    private String header = "";
    private String arquivoPessoas = "C:\\Users\\autologon\\Pictures\\Simulado\\Simulado\\src\\br\\edu\\up\\Pessoas.csv";
    private String arquivoEnderecos = "C:\\Users\\autologon\\Pictures\\Simulado\\Simulado\\src\\br\\edu\\up\\Enderecos.csv";
    private String arquivosPessoasEnderecos = "C:\\Users\\autologon\\Pictures\\Simulado\\Simulado\\src\\br\\edu\\up\\PessoaEndereco.csv";

    public List<Pessoa> lerPessoasComEnderecos() {

        List<Pessoa> listaPessoas = new ArrayList<>();

        try (Scanner scanPessoas = new Scanner(new File(arquivoPessoas))) {
            if (scanPessoas.hasNextLine()) {
                scanPessoas.nextLine();
            }
            while (scanPessoas.hasNextLine()) {
                String linhaPessoa = scanPessoas.nextLine();
                String[] dadosPessoa = linhaPessoa.split(";");
                int codigo = Integer.parseInt(dadosPessoa[0]);
                String nome = dadosPessoa[1];

                Pessoa pessoa = new Pessoa(codigo, nome);
                listaPessoas.add(pessoa);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        try (Scanner scanEnderecos = new Scanner(new File(arquivoEnderecos))) {
            if (scanEnderecos.hasNextLine()) {
                scanEnderecos.nextLine();
            }

            while (scanEnderecos.hasNextLine()) {
                String linhaEndereco = scanEnderecos.nextLine();
                String[] dadosEndereco = linhaEndereco.split(";");
                String rua = dadosEndereco[0];
                String cidade = dadosEndereco[1];
                int codigoPessoa = Integer.parseInt(dadosEndereco[2]);
                for (Pessoa pessoa : listaPessoas) {
                    if (pessoa.getCodigo() == codigoPessoa) {
                        pessoa.setRua(rua);
                        pessoa.setCidade(cidade);

                        break;
                    }
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listaPessoas;
    }

    public boolean gravar(List<Pessoa> listaPessoas) throws IOException {

        try (FileWriter writer = new FileWriter(arquivosPessoasEnderecos)) {

            writer.append("Código;Nome;Rua;Cidade");

            for (Pessoa pessoa : listaPessoas) {
                writer.append("\n" + pessoa.getCodigo() + ";");
                writer.append(pessoa.getNome() + ";");
                writer.append(pessoa.getRua() + ";");
                writer.append(pessoa.getCidade() + "\n");
            }
            writer.flush(); // Forçar escrita dos dados para o arquivo
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
