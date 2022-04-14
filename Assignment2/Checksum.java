import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Computes the Checksum from file "HexPacketWS.txt"
 * Josue Molina Morales & Adam Weiss
 */
public class Checksum {
    public static final String FILE = "HexPacketWS.txt";
    public static final String OUT_FILE = "HexPacketWS_CheckSum.txt";
    public static void main(String[] args) {
        Scanner scan = null;
        FileWriter outFile = null;
        try {
            scan = new Scanner(new File(FILE));
            outFile = new FileWriter(OUT_FILE);

            String hex = "";
            String[] tokens;
            String input = "";

            while (scan.hasNext()) {
                input = scan.nextLine();
                tokens = input.split(" ");
                
                for(int i = 1; i < tokens.length; i++) {
                    hex += !tokens[i].isBlank() ? tokens[i] : "";
                }
            }
            
            if (hex.length() % 4 != 0 ) {
                for (int i = 0; i < 4-(hex.length()%4); i++) {
                    hex += "0";
                }
            }
            //Write hex to output file
            outFile.write(hex);

            int sum = computeChecksum(hex);
            System.out.println("Sum is: " + sum + " In Binary: " + Integer.toBinaryString(sum));

            //Appending sum to file
            String hexChecksum = Integer.toHexString(sum);
            outFile.write(hexChecksum);
            System.out.println("Wrote " + hexChecksum + " output file.");

            verifyChecksum(hex, sum); // This should be valid
            verifyChecksum(hex.replaceFirst("a","0"), sum); // This should be invalid

            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

    /**
     * Compute the checksum for a string of hexidecimal digits
     * 
     * @param hex
     */
    public static int computeChecksum(String hex) {
        int sum = 0;
        final int MOD = (int)Math.pow(2, 16);
        for (int i = 0; i < hex.length()-4; i++) {
            sum += Integer.parseInt(hex.substring(i, i+4), 16); 
            sum = (sum > MOD) ? (sum%MOD) + 1 : sum;
        }
        return sum;
    }
    /**
     * Verify the checksum
     * @param hex The hex to compute the checksum
     * @param checksum The sum calculated by the sender
     */
    public static void verifyChecksum(String hex, int checksum) {
        // System.out.println(Integer.toHexString(~checksum+computeChecksum(hex))); //uncomment to see all f's or not
        System.out.println((~checksum+computeChecksum(hex) == -1) ? "Checksum is valid" : "Checksum is not valid");
    }
}