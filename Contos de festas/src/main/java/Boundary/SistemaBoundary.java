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

   