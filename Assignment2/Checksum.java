import java.io.File;
import java.util.Scanner;

public class Checksum {
    public static final String FILE = "hex.txt";
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File(FILE));
            String hex = "";
            
            while (scan.hasNext()) {
                hex += scan.nextLine();
            }

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
        int sum = 0;
        final int MOD = (int)Math.pow(2, 8);
        for (int i = 0; i < hex.length()-2; i++) {
            sum += Integer.parseInt(hex.substring(i, i+2), 16); 
            sum = (sum > MOD) ? (sum%MOD) + 1 : sum;
        }
        System.out.println("Sum is: " + sum + " In Binary: " + Integer.toBinaryString(sum));
        System.out.println("One's Complement is: " + (~sum) + " In Binary: " + Integer.toBinaryString(~sum));
        System.out.println("SUM: " + Integer.toBinaryString(sum+ (~sum)));
    }

    public static void verifyChecksum(String hex) {
        
    }
}