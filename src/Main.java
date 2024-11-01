import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountSystemConsole accountSysApp = new AccountSystemConsole(AccountSetUp.ChangeMaxAndMin);

        AccountGenerator accountGen = new AccountGenerator(accountSysApp.GetMinPasswordLengthAPI(), accountSysApp.GetMaxPasswordLengthAPI(), accountSysApp.GetUsernameLengthAPI());

        int amountOfAccounts = AmountOfAccountsRequest();

        Map<String, String> accounts = accountGen.GenerateRandomAccounts(amountOfAccounts);


        for (Map.Entry<String, String> account : accounts.entrySet()) {
            String username = account.getKey();
            String password = account.getValue();

            accountSysApp.SetUsernameAPI(username);
            accountSysApp.SetPasswordAPI(password);

            accountSysApp.CreateAccountAPI();
        }
    }

    private static int AmountOfAccountsRequest() {
        System.out.println("Enter How Many Accounts You Want To Generate: ");
        Scanner scanner = new Scanner(System.in);
        String amount = null;
        do {
            amount = scanner.nextLine();
        } while (!AmountInputValid(amount));

        return Integer.parseInt(amount);
    }

    private static boolean AmountInputValid(String amt) {
        int amount = 0;
        if (amt == null) {
            System.out.println("Error: Amount Must Have A Value!");
            return false;
        }
        try {
            amount = Integer.parseInt(amt);
        } catch (NumberFormatException ex) {
            System.out.println("Error: Amount Value Must Be A Positive Whole Number!");
            return false;
        }

        if (amount < 0) {
            System.out.println("Error: Amount Value Must Be A Positive Number!");
            return false;
        }
        if (amount > AccountGenerator.USER_LIMIT) {
            System.out.println("Error: Amount Value Must Be Lower Than "+ AccountGenerator.USER_LIMIT);
            return false;
        }
        return true;
    }
}