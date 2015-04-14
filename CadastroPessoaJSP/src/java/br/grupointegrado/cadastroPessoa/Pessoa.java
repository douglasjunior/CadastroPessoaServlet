package br.grupointegrado.cadastroPessoa;

public class Pessoa {

    private int codigo;
    private String nome;
    private int idade;
    private String time;

    public Pessoa(int cod, String nome) {
       this.codigo = cod;
       this.nome = nome; 
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
