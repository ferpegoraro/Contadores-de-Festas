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
   
