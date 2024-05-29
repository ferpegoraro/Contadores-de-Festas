package control;

import entity.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sistema {
    private List<Cliente> clientes;
    private List<Buffet> buffets;
    private List<Agendamento> agendamentos;

    public Sistema() {
        clientes = new ArrayList<>();
        buffets = new ArrayList<>();
        agendamentos = new ArrayList<>();
        inicializarSistema();
    }

    private void inicializarSistema() {
        clientes.add(new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123"));

        Buffet buffet = new Buffet("Cedrom", "xx.xxx.xxx/0001-xx", "cedrom@gmail.com", "123", "Endereço exemplo", "12:00 - 17:00 e 19:00 - 00:00", "Buffet de exemplo");
        List<String> datasDisponiveis = new ArrayList<>();
        datasDisponiveis.add("30/05/2024");
        datasDisponiveis.add("07/06/2024");
        datasDisponiveis.add("10/10/2024");
        datasDisponiveis.add("24/12/2024");
        buffet.setDatasDisponiveis(datasDisponiveis);
        List<String> horariosDisponiveis = new ArrayList<>();
        horariosDisponiveis.add("12:00 às 17:00");
        horariosDisponiveis.add("19:00 às 00:00");
        buffet.setHorariosDisponiveis(horariosDisponiveis);
        buffets.add(buffet);
    }

    public boolean cadastrarCliente(String nomeCompleto, String cpf, String email, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return false;
            }
        }
        clientes.add(new Cliente(nomeCompleto, cpf, email, senha));
        return true;
    }

    public boolean cadastrarBuffet(String nome, String cnpj, String email, String senha, String endereco, String horarioFuncionamento, String descricao) {
        for (Buffet buffet : buffets) {
            if (buffet.getEmail().equals(email)) {
                return false;
            }
        }
        buffets.add(new Buffet(nome, cnpj, email, senha, endereco, horarioFuncionamento, descricao));
        return true;
    }

    public Cliente loginCliente(String email, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
                return cliente;
            }
        }
        return null;
    }

    public Buffet loginBuffet(String email, String senha) {
        for (Buffet buffet : buffets) {
            if (buffet.getEmail().equals(email) && buffet.getSenha().equals(senha)) {
                return buffet;
            }
        }
        return null;
    }

    public boolean agendarBuffet(Cliente cliente, Buffet buffet, String horario, Date data) {
        agendamentos.add(new Agendamento(cliente, buffet, data, horario));
        return true;
    }

    public List<Agendamento> getAgendamentosCliente(Cliente cliente) {
        List<Agendamento> agendamentosCliente = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getCliente().equals(cliente)) {
                agendamentosCliente.add(agendamento);
            }
        }
        return agendamentosCliente;
    }

    public List<Agendamento> getAgendamentosBuffet(Buffet buffet) {
        List<Agendamento> agendamentosBuffet = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getBuffet().equals(buffet)) {
                agendamentosBuffet.add(agendamento);
            }
        }
        return agendamentosBuffet;
    }

    public boolean cancelarAgendamento(Agendamento agendamento) {
        return agendamentos.remove(agendamento);
    }

    public List<Buffet> getBuffets() {
        return buffets;
    }
}
