import entities.Account;
import entities.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //pagador pix
        Map<Long, Account> payer = new HashMap<>();

        //beneficiario pix
        Map<String, Account> receiver = new HashMap<>();

        Client c1 = new Client(2211L, "Edvaldo Leite");
        Client c2 = new Client(2715L, "Luciene Leite");

        Account a1 = new Account(1, "96125", c1, 10_000.00);
        Account a2 = new Account(2, "lsilva", c2, 8_000.00);

        // atribui o cpf do cliente a sua respectiva conta
        payer.put(a1.getClient().getId(), a1);
        payer.put(a2.getClient().getId(), a2);

        // atribui a chave pix a sua respectiva conta
        receiver.put(a1.getPix(), a1);
        receiver.put(a2.getPix(), a2);

        System.out.print("Qual o CPF do pagador: ");
        Long cpfValue = scanner.nextLong();
        
        Account accountPayer = payer.get(cpfValue);

        if (accountPayer != null) {

            System.out.print("Digite uma chave pix: ");
            String pixKey = scanner.next();
            Account accountReceiver = receiver.get(pixKey);

            if (accountReceiver != null) {
                System.out.print("Qual o valor do pix: R$ ");
                Double valuePix = scanner.nextDouble();

                System.out.println("******************************");
                System.out.println("Beneficiário: " + accountReceiver.getClient().getName());
                System.out.println("Chave pix: " + accountReceiver.getPix());
                System.out.println("Valor recebido: R$ " + valuePix);
                System.out.println();
                System.out.println("Pagador: " + accountPayer.getClient().getName());
                System.out.println("CPF do pagador: " + accountPayer.getClient().getId());
                System.out.println("******************************");
                System.out.println();

                // decrementa o saldo do pagador
                accountPayer.setBalance(accountPayer.getBalance() - valuePix);

                // incrementa o saldo do recebedor
                accountReceiver.setBalance(accountReceiver.getBalance() + valuePix);

                System.out.println();
                System.out.println("Saldo do pagador: R$ " + accountPayer.getBalance());
                System.out.println("Saldo do recebedor: R$ " + accountReceiver.getBalance());

            } else {
                System.out.println("Chave não encontrada...");
            }
        } else {
            System.out.println("CPF não encontrado...");
        }

        scanner.close();
    }
}