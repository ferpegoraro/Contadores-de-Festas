package entity;

import java.util.List;

public class Buffet {
    private String nome;
    private String cnpj;
    private String email;
    private String senha;
    private String endereco;
    private String horarioFuncionamento;
    private String descricao;
    private List<String> datasDisponiveis;
    private List<String> horariosDisponiveis;

    public Buffet(String nome, String cnpj, String email, String senha, String endereco, String horarioFuncionamento, String descricao) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.horarioFuncionamento = horarioFuncionamento;
        this.descricao = descricao;
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getDatasDisponiveis() {
        return datasDisponiveis;
    }

    public void setDatasDisponiveis(List<String> datasDisponiveis) {
        this.datasDisponiveis = datasDisponiveis;
    }

    public List<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<String> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
}
