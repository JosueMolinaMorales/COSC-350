import java.io.File;
import java.util.Scanner;

public class Checksum {
    public static final String FILE = "hex.txt";
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File(FILE));
            String hex = scan.nextLine();

            if (hex.length() % 4 != 0 ) {
                for (int i = 0; i < 4-(hex.length()%4); i++) {
                    hex += "0";
                }
            }
            computeChecksum(hex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Compute the checksum for a string of hexidecimal digits
     * 
     * @param hex
     */
    public static void computeChecksum(String hex) {
        long sum = 0;
        final int MOD = (int)Math.pow(2, 16);
        for (int i = 0; i < hex.length()-2; i++) {
            sum += Integer.parseInt(hex.substring(i, i+2), 16); 
            System.out.printf("%d MOD %d\n", sum, MOD);
            sum = (sum > MOD) ? (sum%MOD) + 1 : sum;
        }
        System.out.println("Sum is: " + sum);
        System.out.println("One's Complement is: " + (~sum));
        System.out.println("SUM: " + (sum+ (~sum)));
    }
}