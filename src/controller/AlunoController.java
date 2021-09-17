package controller;

import DAO.ConnectionFactory;
import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoController {

    // 1 - Método pra consulta
    public List<Aluno> list() {
        //Preparar lista que irá retornar alunos após consultar o banco de dados;
        List<Aluno> alunos = new ArrayList<>();

        Connection conexao;
        PreparedStatement instrucaoSQL;
        ResultSet resultados;


        try{
            conexao = ConnectionFactory.getConnection();

            //Prepara statement com os parâmetros recebidos (nesta função não tem parâmetros, pois irá retornar todos os valores da tabela aluno)
            instrucaoSQL = conexao.prepareStatement("select * from aluno");

            //Executa consulta e armazena o retorno da consulta no objeto "resultados   ".
            resultados = instrucaoSQL.executeQuery();

            //Criar um objeto aluno e guardar na lista de alunos.
            while (resultados.next()){
                Aluno aluno = new Aluno(
                        resultados.getString("nome"),
                        resultados.getInt("idade"),
                        resultados.getString("estado"));

                alunos.add(aluno);

            }


        } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!"+e.getMessage());
            e.printStackTrace();
        }
            return alunos;
    }

    //1.1 - Método pra consulta com filtro
    public Aluno getById(int id){
        Aluno aluno = new Aluno();

        Connection conexao;
        PreparedStatement instrucaoSQL;
        ResultSet resultados;

        try {
            conexao = ConnectionFactory.getConnection();

            //prepara a instrucaoSQL com os parametros recebidos
            instrucaoSQL = conexao.prepareStatement("select * FROM aluno WHERE id = ?");
            instrucaoSQL.setInt(1,id);

            resultados =   instrucaoSQL.executeQuery();

            while (resultados.next()){
                aluno.setId(resultados.getInt("id"));
                aluno.setNome(resultados.getString("nome"));
                aluno.setIdade(resultados.getInt("idade"));
                aluno.setEstado(resultados.getString("estado"));
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return aluno;
    }

    //1.2 - Método pra inserção
    public void create(Aluno aluno){
        Connection conexao;
        PreparedStatement instrucaoSQL;

        try {
            conexao = ConnectionFactory.getConnection();
            //Prepara SQL para a inserção de dados do aluno
            instrucaoSQL = conexao.prepareStatement("insert into aluno(nome, idade, estado) values(?,?,?)");

            //Prepara o statement com os parâmetros recebidos
            instrucaoSQL.setString(1, aluno.getNome());
            instrucaoSQL.setInt(2, aluno.getIdade());
            instrucaoSQL.setString(3, aluno.getEstado());

            //Execulta inserção e armazena o numero de linhas afetadas
            int linhasAfetadas = instrucaoSQL.executeUpdate();

            System.out.println("Inserção bem sucedida!. Foi adicionada "+linhasAfetadas+" linha");

        } catch (SQLException e){
            System.out.println("Inserção Falhou! "+e.getMessage());
            e.printStackTrace();
        }
    }

    //1.3 - Método pra deletar
    public void delete(int id){
        Connection conexao;
        PreparedStatement instrucaoSQL;

        try {
            conexao = ConnectionFactory.getConnection();

            instrucaoSQL = conexao.prepareStatement("DELETE FROM aluno WHERE id = ?");
            instrucaoSQL.setInt(1, id);

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            System.out.println("Delete BEM SUCEDIDA! Foi deletada "+linhasAfetadas+" linha");

        }catch (SQLException e){
            System.out.println("DELETE falhou! "+e.getMessage());
            e.printStackTrace();
        }
    }

    //1.4 - Método pra atualizar
    public void update(Aluno aluno){
        Connection conexao;
        PreparedStatement instrucaoSQL;

        try {
            conexao = ConnectionFactory.getConnection();

            //Preparar statement com os parâmetros recebidos
            instrucaoSQL = conexao.prepareStatement("UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?");
            instrucaoSQL.setString(1, aluno.getNome());
            instrucaoSQL.setInt(2, aluno.getIdade());
            instrucaoSQL.setString(3, aluno.getEstado());
            instrucaoSQL.setInt(4, aluno.getId());

            //Executa atualização e armazena o numero de linhas afetadas
            int linhasAfetadas = instrucaoSQL.executeUpdate();

            System.out.println("Atualização BEM SUCEDIDA! Foi atualizada "+linhasAfetadas+ " linha");

        } catch (SQLException exception){
            System.out.println("Atualização FALHOU! "+exception.getMessage());
            exception.printStackTrace();
        }
    }
    }
