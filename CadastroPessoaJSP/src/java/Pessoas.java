
import java.util.ArrayList;
import java.util.List;

public class Pessoas {

    private static List<Pessoa> pessoas = new ArrayList<Pessoa>();

    static {
        pessoas.add(new Pessoa(1, "Douglas"));
        pessoas.add(new Pessoa(2, "José"));
        pessoas.add(new Pessoa(3, "Joaquim"));
        pessoas.add(new Pessoa(4, "Marco véio"));
    }

    public static void adicionar(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public static void remover(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

    public static Pessoa getPessoa(int codigo) {
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa pessoa = pessoas.get(i);

            if (pessoa.getCodigo() == codigo) {
                return pessoa;
            }

        }
        return null;
    }

    public static List<Pessoa> getPessoa(String nome) {
        List<Pessoa> encontradas = new ArrayList<Pessoa>();
        if (nome != null) {
            for (int i = 0; i < pessoas.size(); i++) {
                Pessoa pessoa = pessoas.get(i);

                if (pessoa.getNome().contains(nome)) {
                    encontradas.add(pessoa);
                }

            }
        }
        return encontradas;
    }
}
