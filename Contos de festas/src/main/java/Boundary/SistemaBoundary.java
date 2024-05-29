package boundary;

import control.Sistema;
import entity.Agendamento;
import entity.Buffet;
import entity.Cliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SistemaBoundary {
    private Sistema sistema;
    private Scanner scanner;

    public SistemaBoundary() {
        sistema = new Sistema();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("Bem-vindo ao sistema de aluguel de buffet!");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                login();
            } else if (opcao == 2) {
                cadastro();
            } else if (opcao == 3) {
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void login() {
        System.out.println("Digite seu email:");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        Cliente cliente = sistema.loginCliente(email, senha);
        if (cliente != null) {
            menuCliente(cliente);
            return;
        }

        Buffet buffet = sistema.loginBuffet(email, senha);
        if (buffet != null) {
            menuBuffet(buffet);
            return;
        }

        System.out.println("Email ou senha incorretos.");
    }

    private void cadastro() {
        System.out.println("Cadastro");
        System.out.println("1. Cliente");
        System.out.println("2. Buffet");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.println("Nome completo:");
            String nome = scanner.nextLine();
            System.out.println("CPF:");
            String cpf = scanner.nextLine();
            System.out.println("Email:");
            String email = scanner.nextLine();
            System.out.println("Senha:");
            String senha = scanner.nextLine();
            System.out.println("Confirmar senha:");
            String confirmarSenha = scanner.nextLine();

            if (!senha.equals(confirmarSenha)) {
                System.out.println("Senhas não conferem.");
                return;
            }

            if (sistema.cadastrarCliente(nome, cpf, email, senha)) {
                System.out.println("Cadastro realizado com sucesso!");
            } else {
                System.out.println("Email já cadastrado.");
            }
        } else if (opcao == 2) {
            System.out.println("Nome do buffet:");
            String nome = scanner.nextLine();
            System.out.println("CNPJ:");
            String cnpj = scanner.nextLine();
            System.out.println("Email:");
            String email = scanner.nextLine();
            System.out.println("Senha:");
            String senha = scanner.nextLine();
            System.out.println("Confirmar senha:");
            String confirmarSenha = scanner.nextLine();

            if (!senha.equals(confirmarSenha)) {
                System.out.println("Senhas não conferem.");
                return;
            }

            System.out.println("Endereço:");
            String endereco = scanner.nextLine();
            System.out.println("Horário de funcionamento:");
            String horarioFuncionamento = scanner.nextLine();
            System.out.println("Descrição:");
            String descricao = scanner.nextLine();

            if (sistema.cadastrarBuffet(nome, cnpj, email, senha, endereco, horarioFuncionamento, descricao)) {
                System.out.println("Cadastro realizado com sucesso!");
            } else {
                System.out.println("Email já cadastrado.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void menuCliente(Cliente cliente) {
        while (true) {
            System.out.println("Menu Cliente");
            System.out.println("1. Novo agendamento");
            System.out.println("2. Meus agendamentos");
            System.out.println("3. Cancelar agendamento");
            System.out.println("4. Avaliar buffet");
            System.out.println("5. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                novoAgendamento(cliente);
            } else if (opcao == 2) {
                meusAgendamentos(cliente);
            } else if (opcao == 3) {
                cancelarAgendamento(cliente);
            } else if (opcao == 4) {
                avaliarBuffet(cliente);
            } else if (opcao == 5) {
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
    private void menuBuffet(Buffet buffet) {
        while (true) {
            System.out.println("Menu Buffet");
            System.out.println("1. Ver solicitações de reserva");
            System.out.println("2. Ver agendamentos");
            System.out.println("3. Cancelar agendamento");
            System.out.println("4. Avaliar cliente");
            System.out.println("5. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                verSolicitacoesReserva(buffet);
            } else if (opcao == 2) {
                verAgendamentos(buffet);
            } else if (opcao == 3) {
                cancelarAgendamentoBuffet(buffet);
            } else if (opcao == 4) {
                avaliarCliente(buffet);
            } else if (opcao == 5) {
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void novoAgendamento(Cliente cliente) {
        System.out.println("Novo Agendamento");
    
        System.out.println("Selecione o buffet:");
        List<Buffet> buffetsDisponiveis = sistema.getBuffets();
        for (int i = 0; i < buffetsDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + buffetsDisponiveis.get(i).getNome());
        }
        int opcaoBuffet = scanner.nextInt();
        scanner.nextLine();
        if (opcaoBuffet < 1 || opcaoBuffet > buffetsDisponiveis.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Buffet buffetSelecionado = buffetsDisponiveis.get(opcaoBuffet - 1);
    
        System.out.println("Selecione a data:");
        List<String> datasDisponiveisString = buffetSelecionado.getDatasDisponiveis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < datasDisponiveisString.size(); i++) {
            System.out.println((i + 1) + ". " + datasDisponiveisString.get(i));
        }
        int opcaoData = scanner.nextInt();
        scanner.nextLine();
        if (opcaoData < 1 || opcaoData > datasDisponiveisString.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        String dataSelecionadaString = datasDisponiveisString.get(opcaoData - 1);
        Date dataSelecionada;
        try {
            dataSelecionada = dateFormat.parse(dataSelecionadaString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter data.");
            return;
        }
    
        System.out.println("Selecione o horário:");
        List<String> horariosDisponiveis = buffetSelecionado.getHorariosDisponiveis();
        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + horariosDisponiveis.get(i));
        }
        int opcaoHorario = scanner.nextInt();
        scanner.nextLine();
        if (opcaoHorario < 1 || opcaoHorario > horariosDisponiveis.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        String horarioSelecionado = horariosDisponiveis.get(opcaoHorario - 1);
    
        if (sistema.agendarBuffet(cliente, buffetSelecionado, horarioSelecionado, dataSelecionada)) {
            System.out.println("Agendamento realizado com sucesso!");
        } else {
            System.out.println("Falha ao realizar agendamento.");
        }
    }

    private void meusAgendamentos(Cliente cliente) {
        System.out.println("Meus Agendamentos");
        List<Agendamento> agendamentos = sistema.getAgendamentosCliente(cliente);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Agendamento agendamento : agendamentos) {
            System.out.println("Buffet: " + agendamento.getBuffet().getNome() + ", Data: " + dateFormat.format(agendamento.getData()) + ", Horário: " + agendamento.getHorario());
        }
    }
        private void cancelarAgendamento(Cliente cliente) {
        System.out.println("Suas Reservas:");
        List<Agendamento> agendamentos = sistema.getAgendamentosCliente(cliente);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < agendamentos.size(); i++) {
            Agendamento agendamento = agendamentos.get(i);
            System.out.println((i + 1) + ". Buffet: " + agendamento.getBuffet().getNome() + ", Data: " + dateFormat.format(agendamento.getData()) + ", Horário: " + agendamento.getHorario());
        }
        System.out.println("Digite o número da reserva que deseja cancelar ou reagendar:");
        int opcaoReserva = scanner.nextInt();
        scanner.nextLine();
        if (opcaoReserva < 1 || opcaoReserva > agendamentos.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Agendamento agendamentoSelecionado = agendamentos.get(opcaoReserva - 1);
        System.out.println("1. Cancelar reserva");
        System.out.println("2. Reagendar reserva");
        int opcaoAcao = scanner.nextInt();
        scanner.nextLine();
        if (opcaoAcao == 1) {
            if (sistema.cancelarAgendamento(agendamentoSelecionado)) {
                System.out.println("Reserva cancelada com sucesso!");
            } else {
                System.out.println("Falha ao cancelar reserva.");
            }
        } else if (opcaoAcao == 2) {
            System.out.println("Selecione a nova data:");
            List<String> datasDisponiveis = agendamentoSelecionado.getBuffet().getDatasDisponiveis();
            for (int i = 0; i < datasDisponiveis.size(); i++) {
                System.out.println((i + 1) + ". " + datasDisponiveis.get(i));
            }
            int opcaoData = scanner.nextInt();
            scanner.nextLine();
            if (opcaoData < 1 || opcaoData > datasDisponiveis.size()) {
                System.out.println("Opção inválida.");
                return;
            }
            String novaDataString = datasDisponiveis.get(opcaoData - 1);
            Date novaData;
            try {
                novaData = dateFormat.parse(novaDataString);
            } catch (ParseException e) {
                System.out.println("Erro ao converter data.");
                return;
            }
            
            System.out.println("Selecione o novo horário:");
            List<String> horariosDisponiveis = agendamentoSelecionado.getBuffet().getHorariosDisponiveis();
            for (int i = 0; i < horariosDisponiveis.size(); i++) {
                System.out.println((i + 1) + ". " + horariosDisponiveis.get(i));
            }
            int opcaoHorario = scanner.nextInt();
            scanner.nextLine();
            if (opcaoHorario < 1 || opcaoHorario > horariosDisponiveis.size()) {
                System.out.println("Opção inválida.");
                return;
            }
            String novoHorario = horariosDisponiveis.get(opcaoHorario - 1);
            agendamentoSelecionado.setData(novaData);
            agendamentoSelecionado.setHorario(novoHorario);
            System.out.println("Reserva reagendada com sucesso para a data " + dateFormat.format(novaData) + " e horário " + novoHorario);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void avaliarBuffet(Cliente cliente) {
        System.out.println("Avaliar Buffet");
        
        List<Agendamento> agendamentos = sistema.getAgendamentosCliente(cliente);
        if (agendamentos.isEmpty()) {
            System.out.println("Você não possui agendamentos para avaliar.");
            return;
        }
        
        System.out.println("Selecione o buffet que deseja avaliar:");
        for (int i = 0; i < agendamentos.size(); i++) {
            Agendamento agendamento = agendamentos.get(i);
            System.out.println((i + 1) + ". Buffet: " + agendamento.getBuffet().getNome());
        }
        
        int opcaoBuffet = scanner.nextInt();
        scanner.nextLine();
        
        if (opcaoBuffet < 1 || opcaoBuffet > agendamentos.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Agendamento agendamentoSelecionado = agendamentos.get(opcaoBuffet - 1);
        
        System.out.println("Digite a avaliação (0 a 5 estrelas):");
        int avaliacao = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Avaliação registrada com sucesso!");
    }

    private void verSolicitacoesReserva(Buffet buffet) {
        System.out.println("Solicitações de Reserva");
        List<Agendamento> agendamentos = sistema.getAgendamentosBuffet(buffet);
        for (Agendamento agendamento : agendamentos) {
            System.out.println("Cliente: " + agendamento.getCliente().getNomeCompleto() + ", Data: " + new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getData()) + ", Horário: " + agendamento.getHorario());
        }
    }

    private void verAgendamentos(Buffet buffet) {
        System.out.println("Agendamentos");
        List<Agendamento> agendamentos = sistema.getAgendamentosBuffet(buffet);
        for (Agendamento agendamento : agendamentos) {
            System.out.println("Cliente: " + agendamento.getCliente().getNomeCompleto() + ", Data: " + new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getData()) + ", Horário: " + agendamento.getHorario());
        }
    }

    private void cancelarAgendamentoBuffet(Buffet buffet) {
        System.out.println("Cancelar Agendamento");
        verAgendamentos(buffet);
        System.out.println("Digite o índice do agendamento para cancelar:");
        int indice = scanner.nextInt();
        scanner.nextLine();

        List<Agendamento> agendamentos = sistema.getAgendamentosBuffet(buffet);
        if (indice < 0 || indice >= agendamentos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        if (sistema.cancelarAgendamento(agendamentos.get(indice))) {
            System.out.println("Agendamento cancelado com sucesso!");
        } else {
            System.out.println("Falha ao cancelar agendamento.");
        }
    }

    private void avaliarCliente(Buffet buffet) {
        System.out.println("Avaliar Cliente");
        
        List<Agendamento> agendamentos = sistema.getAgendamentosBuffet(buffet);
        if (agendamentos.isEmpty()) {
            System.out.println("Não há agendamentos para avaliar clientes.");
            return;
        }
        
        System.out.println("Selecione o agendamento do cliente que deseja avaliar:");
        for (int i = 0; i < agendamentos.size(); i++) {
            Agendamento agendamento = agendamentos.get(i);
            System.out.println((i + 1) + ". Cliente: " + agendamento.getCliente().getNomeCompleto());
        }
        
        int opcaoAgendamento = scanner.nextInt();
        scanner.nextLine();
        
        if (opcaoAgendamento < 1 || opcaoAgendamento > agendamentos.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Agendamento agendamentoSelecionado = agendamentos.get(opcaoAgendamento - 1);
        
        System.out.println("Digite a avaliação (0 a 5 estrelas):");
        int avaliacao = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Avaliação registrada com sucesso!");
    }
}  
