package br.grupointegrado.cadastroPessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaDao {

    private static List<Pessoa> pessoas = new ArrayList<Pessoa>();

    static {
        pessoas.add(new Pessoa(1, "Douglas", 26, "Vai curintia!"));
        pessoas.add(new Pessoa(2, "Outro Douglas", 20, "Chora porco!"));
        pessoas.add(new Pessoa(3, "Tibúrcio", 24, "Ai Tricolor!"));
    }

    public PessoaDao() {

    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    /**
     * Busca as pessoas por parte do nome, sem distinção de maiúsculo e
     * minúsculo
     *
     * @param nome Parâmetro informado pelo usuário
     * @return Uma lista de pessoas com o nome pesquisado
     */
    public List<Pessoa> getPessoasPorNome(String nome) {
        List<Pessoa> resultado = new ArrayList<Pessoa>();
        if (nome == null || nome.isEmpty())
            return resultado;
        // percorre as pessoas do "banco de dados"
        for (Pessoa p : pessoas) {
            // verifica se a pessoas contem o nome pesquisado
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Retorna uma pessoa pelo ID
     *
     * @param id
     * @return
     */
    public Pessoa getPessoaPorID(int id) {
        for (Pessoa p : pessoas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void excluirPessoa(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

}
