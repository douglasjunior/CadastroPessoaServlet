package com.github.douglasjunior.androidservletclient;

/**
 * Created by Douglas on 30/03/2015.
 */
public class Pessoa {
    private int codigo;
    private String nome;
    private int idade;
    private String time;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        if (codigo != pessoa.codigo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
