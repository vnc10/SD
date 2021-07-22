package exercicio2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    static String makeMD5(byte[] fileArray) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(fileArray);
            byte[] digest = messageDigest.digest();
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could not generate MD5 hash!");
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] hashInBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte hashInByte : hashInBytes) {
            stringBuilder.append(Integer.toString((hashInByte & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}
