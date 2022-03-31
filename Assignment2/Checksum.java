import java.io.File;
import java.util.Scanner;

public class Checksum {
    public static final String FILE = "HexPacketWS.txt";
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File(FILE));
            String hex = "";
            String[] tokens;

            while (scan.hasNext()) {
                tokens = scan.nextLine().split(" ");

                for(int i = 1; i < tokens.length; i++) {
                    hex += !tokens[i].isBlank() ? tokens[i] : "";
                }
            }

            if (hex.length() % 4 != 0 ) {
                for (int i = 0; i < 4-(hex.length()%4); i++) {
                    hex += "0";
                }
            }
            int sum = computeChecksum(hex);
            // System.out.println("Sum is: " + sum + " In Binary: " + Integer.toBinaryString(sum));
            // System.out.println(hex);
            verifyChecksum(hex, sum); // This should be valid
            //System.out.println("\n\n\n" + hex.replace('a', '0'));
            verifyChecksum(hex.replaceFirst("a","0"), sum); // This should be invalid
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Compute the checksum for a string of hexidecimal digits
     * 
     * @param hex
     */
    public static int computeChecksum(String hex) {
        int sum = 0;
        final int MOD = (int)Math.pow(2, 8);
        for (int i = 0; i < hex.length()-2; i++) {
            sum += Integer.parseInt(hex.substring(i, i+2), 16); 
            sum = (sum > MOD) ? (sum%MOD) + 1 : sum;
        }
        System.out.println("Sum is: " + sum + " In Binary: " + Integer.toBinaryString(sum));
        System.out.println("One's Complement is: " + (~sum) + " In Binary: " + Integer.toBinaryString(~sum));
        System.out.println("SUM: " + Integer.toBinaryString(sum+ (~sum)));
        return sum;
    }

    public static void verifyChecksum(String hex, int checksum) {
        System.out.println((~checksum+computeChecksum(hex) == -1) ? "Checksum is valid" : "Checksum is not valid");
    }
}