package br.edu.ifpb.dac.ejb.sessionbeans;

import br.edu.ifpb.dac.ejb.dao.ProfessorDao;
import br.edu.ifpb.dac.ejb.entidades.Professor;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


@RequestScoped
@Named
public class ProfessorController implements Serializable {

    private Professor professor;

    @Inject
    private ProfessorDao professorDao;

    public List<Professor> buscarTodosOsProfessores(){
        return professorDao.buscarTodos();
    }

    public String salvar(Professor professor){
        professorDao.salvar(professor);
        return null;
    }

    public void goPageTemas(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("temas.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goPageSolicitacoes(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("solicitacoes.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String atualizar(Professor professor){
        this.professorDao.atualizar(professor);
        return "??????"; // Definir para onde será redirecionado
    }
    public String buscarProfessor(Long id){
        this.professorDao.buscar(id);
        return "?????w"; // Definir para onde será redirecionado
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
