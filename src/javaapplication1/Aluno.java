package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Classe que representa um Aluno com todos seus atributos e métodos.
 *
 * @author lucas
 */
public class Aluno {

    int id;
    private final String nome;
    private final String endereco;
    private final int idade;

    /**
     * Método Construtor da classe Aluno.
     *
     * @param id - valor inteiro para o aluno. Padrão 0.
     * @param nome - Nome do aluno.
     * @param endereco - Endereço do aluno.
     * @param idade - Idade do aluno.
     */
    public Aluno(int id, String nome, String endereco, int idade) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.idade = idade;
    }

    /**
     * Método que insere o aluno no banco de dados.
     */
    public void inserir() {
//        Conexao c = new Conexao();
//        Connection conexao = c.getConexao();
        Connection conexao = new Conexao().getConexao();

//        INSERT INTO tb_aluno (nome, endereco, idade)
//        VALUES ("Fulano", "RÃua 1", 21);
        String sql = "INSERT INTO tb_aluno (nome, endereco, idade) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, this.nome);
            stmt.setString(2, this.endereco);
            stmt.setInt(3, this.idade);

            stmt.execute();
            stmt.close();

            conexao.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remover() {

        String sql = "DELETE FROM tb_aluno WHERE id = ?";

        PreparedStatement pstm = null;

        try {
            Connection conexao = new Conexao().getConexao();

            pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, this.id);

            pstm.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (pstm != null) {

                    pstm.close();
                }

            } catch (SQLException e) {

                System.out.println(e.getMessage());
            }
        }
    }

    public void alterar() {

        String sql = "UPDATE tb_aluno SET nome = ?, endereco = ?, idade = ?"
                + " WHERE id = ?";

        PreparedStatement pstm = null;

        try {
            Connection conexao = new Conexao().getConexao();

            pstm = conexao.prepareStatement(sql);

            pstm.setString(1, this.nome);
            pstm.setString(2, this.endereco);
            pstm.setInt(3, this.idade);
            pstm.setInt(4, this.id);

            pstm.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (pstm != null) {

                    pstm.close();
                }

            } catch (SQLException e) {

                System.out.println(e.getMessage());
            }
        }
    }

    public void listar() {

        String sql = "SELECT * FROM tb_aluno";

        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            Connection conexao = new Conexao().getConexao();

            pstm = conexao.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                System.out.println(rset.getInt("id"));
                System.out.println(rset.getString("nome"));
                System.out.println(rset.getString("endereco"));
                System.out.println(rset.getInt("idade"));
                System.out.println("---------------------------");

            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            try {

                if (rset != null) {

                    rset.close();
                }

                if (pstm != null) {

                    pstm.close();
                }

            } catch (SQLException e) {

                System.out.println(e.getMessage());
            }
        }

    }

}
