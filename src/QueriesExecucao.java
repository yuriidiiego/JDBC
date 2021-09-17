import controller.AlunoController;
import model.Aluno;

public class QueriesExecucao {
    public static void main(String[] args) {
        //=====================================================//
        AlunoController alunoController = new AlunoController();
        //====================================================//

        // 1 - Consulta
        System.out.println(alunoController.list());

        //1.1 - Consulta com (filtro)
        System.out.println(alunoController.getById(2));

        //1.2 - Inserção
        Aluno alunoPraInsercao = new Aluno("Lucilene", 58, "PA");
        alunoController.create(alunoPraInsercao);

        //1.3 - Deletar
        alunoController.list().stream().forEach(System.out::println);
        alunoController.delete(1);
        alunoController.list().forEach(System.out::println);

        //1.4 - Atualizar
        Aluno alunoAtualizar = alunoController.getById(3);
        alunoAtualizar.setNome("Fogaça");
        alunoAtualizar.setIdade(48);
        alunoAtualizar.setEstado("SP");

        alunoController.update(alunoAtualizar);
    }
}
