package br.edu.ifpb.dac.web;

import br.edu.ifpb.dac.ejb.entidades.Aluno;
import br.edu.ifpb.dac.ejb.entidades.Professor;
import br.edu.ifpb.dac.ejb.services.AutenticacaoExcecao;
import br.edu.ifpb.dac.ejb.services.AutenticacaoServiceAluno;
import br.edu.ifpb.dac.ejb.services.AutenticacaoServiceProfessor;


import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@ManagedBean
@SessionScoped
public class LoginUsuarioBean implements Serializable {

    @EJB
    AutenticacaoServiceAluno autenticacaoServiceAluno;

    @EJB
    AutenticacaoServiceProfessor autenticacaoServiceProfessor;


    private String matricula;

    private String senha;

    private Aluno alunoLogado;

    private Professor professorLogado;

    public String efetuarLogin(String matricula) {
        if (matricula.length()==3){
            try {
                alunoLogado = autenticacaoServiceAluno.login(matricula, senha);
            } catch (AutenticacaoExcecao e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Matricula ou senha inválidos"));
                return "/paglogin?faces-redirect=true";
            }
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("matricula",matricula);
            return "/restricted/home?faces-redirect=true";
        }else {
            try {
                professorLogado = autenticacaoServiceProfessor.login(matricula, senha);
            } catch (AutenticacaoExcecao e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Matricula ou senha inválidos"));
                return "/paglogin?faces-redirect=true";
            }
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("matricula",matricula);
            return "/restricted/hprofessor?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/paglogin?faces-redirect=true";
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Aluno getAlunoLogado() {
        return alunoLogado;
    }


}
