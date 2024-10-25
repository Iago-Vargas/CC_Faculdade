/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package escola.conexao;
import beans.Aluno;
import DAO.AlunoDAO;
import beans.Professor;
import DAO.ProfessorDAO;
/**
 *
 * @author iagov
 */
public class EscolaConexao {

    public static void main(String[] args) {
        Conexao c = new Conexao();
        c.getConexao();
        
        /* Aluno a = new Aluno ();
        a.setNome("Igor");
        a.setIdade(41);
        a.setCurso("Corretor");
        
        AlunoDAO aDAO = new AlunoDAO();
        aDAO.inserir(a); */
        
        Professor p = new Professor();
        p.setNome("Tck10");
        p.setIdade(24);
        p.setDisciplina("Valorant");
        
        ProfessorDAO pDAO = new ProfessorDAO();
        pDAO.inserir(p);
        
    }
    
}
