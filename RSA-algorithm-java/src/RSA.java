/**
 * Can Zhong
 * Computer Security
 */
// Import
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    BigInteger pPrime;
    BigInteger qPrime;
    BigInteger modulusN;
    BigInteger phi;
    BigInteger privatekey;
    BigInteger mindiff = new BigInteger("2").pow(1000);
    BigInteger e = new BigInteger("65537");

    int minpqlength = 1536;

    public RSA (int bitlength) {
        SecureRandom numbergenerator = new SecureRandom();
        System.out.println("Finding a random value for P and Q.");
        pPrime = new BigInteger(bitlength, 100, numbergenerator);
        qPrime = new BigInteger(bitlength, 100, numbergenerator);
        modulusN = pPrime.multiply(qPrime);
        phi = (pPrime.subtract(BigInteger.ONE)).multiply(qPrime.subtract(BigInteger.ONE));
        // Will keep searching for prime numbers until it fits the two criterias of having a min difference and to find
        // prime values such that when E = 65537, then we can find a value P-1 which results in E and P-1 being coprime.
        // This will allow us to find a private key D such that D = (1/e)mod phi
        while (((pPrime.subtract(qPrime)).abs().compareTo(mindiff) == -1) && (e.gcd(pPrime.subtract(BigInteger.ONE)).intValue() > 1)) {
            System.out.println("Difference between primes is not large enough. Finding new values for P and Q.");
            pPrime = new BigInteger(bitlength, 100, numbergenerator);
            qPrime = new BigInteger(bitlength, 100, numbergenerator);
            modulusN = pPrime.multiply(qPrime);
            phi = (pPrime.subtract(BigInteger.ONE)).multiply(qPrime.subtract(BigInteger.ONE));
        }
        privatekey = e.modInverse(phi);
    }

    public BigInteger encryptWithTime(BigInteger text) {
        double start = System.nanoTime();
        BigInteger encrypted = text.modPow(e, modulusN);
        double end = System.nanoTime();
        double runtime = (end - start) / 1000000;

        //Check validity of the message by checking coprime and validity of the runtime.
        if (runtime < 0 && text.gcd(modulusN).intValue() != 1) {
            if (runtime < 0) {
                System.out.println("Error : Encryption Runtime should be greater than zero.");
            }
            if (text.gcd(modulusN).intValue() != 1) {
                System.out.println("Message and modulus N are not Coprime.");
            }
            return null;
        }
        else {
            System.out.println("Encryption Runtime : " + runtime);
        }

        return encrypted;
    }

    public BigInteger decryptWithTime(BigInteger text) {
        double start = System.nanoTime();
        BigInteger decrypted = text.modPow(privatekey, modulusN);
        double end = System.nanoTime();
        double runtime = (end - start) / 1000000;
        if (runtime < 0) {
            System.out.println("Error : Decryption Runtime should be greater than zero.");
            return null;
        }
        else {
            System.out.println("Decryption Runtime : " + runtime);
        }
        return decrypted;
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Please Enter the bit length of prime numbers P and Q :");
        String primelength = stdIn.nextLine();
        int bitlength = Integer.parseInt(primelength);
        //Give user option to enter a custom bit length. Ensure it is more than minimum.
        //Else, let's hardcode it.
        //RSA rsa = new RSA(1536);
        //We are going to assume the user inputs a valid input (ie a number not a character
        while (bitlength < 1536) {
            System.out.println("Invalid Bit Length. Please enter a length greater than or equal to 1536 : ");
            primelength = stdIn.nextLine();
            bitlength = Integer.parseInt(primelength);
        }
        RSA rsa = new RSA(bitlength);

        System.out.println("RSA Keys have been created. Now we will encrypt and decrypt texts.");
        //We are going to assume the user does not start with a ~ and intends to encrypt/decrypt at least once
        System.out.println("Please input text or ~ to quit : ");
        String input = stdIn.nextLine();
        BigInteger inputBytes = new BigInteger(input.getBytes());
        BigInteger cipherinput = rsa.encryptWithTime(inputBytes);
        System.out.println("Ciphertext : " + cipherinput);
        BigInteger decryptedBytes = rsa.decryptWithTime(cipherinput);
        String decryptedtext = new String(decryptedBytes.toByteArray());
        System.out.println("Decrypted Text : " + decryptedtext);

        while (input.charAt(0) != '~') {
            System.out.println("Please input text or ~ to quit : ");
            input = stdIn.nextLine();
            inputBytes = new BigInteger(input.getBytes());
            cipherinput = rsa.encryptWithTime(inputBytes);
            System.out.println("Ciphertext : " + cipherinput);
            decryptedBytes = rsa.decryptWithTime(cipherinput);
            decryptedtext = new String(decryptedBytes.toByteArray());
            System.out.println("Decrypted Text : " + decryptedtext);
        }
        System.out.println("Thank you for demoing my RSA Algorithim Implemenation. Enjoy your day!");
    }

}
