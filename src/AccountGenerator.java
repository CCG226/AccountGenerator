import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccountGenerator {
    private int minPLength;
    private int maxPLength;

    private int userLen;
    private Random random;
    public static final int USER_LIMIT = 32000;
    private final int ASCII_A_VALUE = 97;
    private final int ASCII_UPPER_A_VALUE = 65;
    private final int ASCII_Z_VALUE = 122;

    public AccountGenerator(int passwordMinLength, int passwordMaxLength, int userLength) throws Exception {

        if (passwordMinLength > passwordMaxLength) {
            throw new Exception("Max Password Length Cant Be Less Than Min Password Length");
        }
        minPLength = passwordMinLength;
        maxPLength = passwordMaxLength;
        userLen = userLength;
        random = new Random();

    }

    public Map<String, String> GenerateRandomAccounts(int amount) throws Exception {

        if (amount > USER_LIMIT) {
            throw new Exception("Can Only Generate Up To + " + USER_LIMIT + " Accounts");
        }

        Map<String, String> accounts = new HashMap<>();
        for (short i = 0; i < amount; i++) {
            String username = "";
            do {
                username = GenerateUsername();

            } while (accounts.containsKey(username));

            String password = GeneratePassword();

            accounts.put(username, password);
        }

        return accounts;
    }

    private String GenerateUsername() throws Exception {
        int charAscii;
        int randomUserLen = random.nextInt(userLen) + 1;

        StringBuilder res = new StringBuilder(randomUserLen);

        for (int i = 0; i < randomUserLen; i++) {
            do {
                charAscii = random.nextInt(ASCII_Z_VALUE - ASCII_UPPER_A_VALUE + 1) + ASCII_UPPER_A_VALUE;
            } while (charAscii >= 91 && charAscii <= 96);
            res.append((char) charAscii);
        }


        return res.toString();
    }

    private String GeneratePassword() throws Exception {


        int randomPasswordLen = random.nextInt((maxPLength - minPLength) + 1) + minPLength;
        StringBuilder res = new StringBuilder(randomPasswordLen);

        for (int i = 0; i < randomPasswordLen; i++) {
            char randomChar = (char) (random.nextInt(((ASCII_Z_VALUE - ASCII_A_VALUE) + 1)) + ASCII_A_VALUE);

            res.append(randomChar);
        }

        return res.toString();
    }
}
