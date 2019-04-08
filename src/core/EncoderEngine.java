package core;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

public class EncoderEngine {

    public static long startTime, endTime, runTime;
    private static long UNICODE_LIMIT = 65533;

    // metodo de codificação da String, onde o int key é adicionado ao int representante de cada char da string e recolocado e uma nova string
    public static String encode(String text, int key) throws InterruptedException {
        startTime = System.currentTimeMillis();
        String result = "";
        int tempKey;

        for (int i = 0; i < text.length(); i++) {

            tempKey = text.charAt(i) + key;

            result += (char) tempKey;

        }
        endTime = System.currentTimeMillis();
        runTime = endTime - startTime;

        System.out.println("operação feita em: " + ((float) runTime / 1000) + " s");
        return result;

    }

    private static String encoder(String text, String key) {

        List<Character> textCharList = text.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        String shaKey = getSHAString(key, "SHA-512");
        List<Character> shaKeyCharList = shaKey.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        long keyAux = 0;

        keyAux = shaKeyCharList
                .stream()
                .map(c -> getUnicodeValue(c.charValue()))
                .mapToLong(Long::longValue)
                .sum() + textCharList.size();

        for (Character c : textCharList) {
            long currentCharInt = getUnicodeValue(c.charValue());
            int index = textCharList.indexOf(c);

            char newChar = getCodedCharInt(keyAux, currentCharInt, index);

            if (newChar > UNICODE_LIMIT) {
                while (newChar > UNICODE_LIMIT) {
                    newChar -= UNICODE_LIMIT;
                }
            }
            if (newChar < 0) {
                while (newChar < UNICODE_LIMIT) {
                    newChar += UNICODE_LIMIT;
                }
            }

            textCharList.set(index, new Character(newChar));
        }

        return getStringFromCharList(textCharList);
    }

    private static byte[] xorEngine(byte[] encodedByte, String key, Boolean isDecode) {

        try {
            List<String> keys = new ArrayList<>();
            String keySha = getSHAString(key, "SHA-512");
            keys.add(keySha.substring(0, 42));
            keys.add(keySha.substring(42, 86));
            keys.add(keySha.substring(86, 128));

            if (isDecode) {
                Collections.reverse(keys);
            }

            for (String k : keys) {
                byte[] keyArr = k.getBytes("UTF-8");
                encodedByte = xorBytes(encodedByte, keyArr);
            }

            return encodedByte;
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encodeToHex(String text, String key) throws UnsupportedEncodingException {
        String encodedString = encoder(text, key);
        byte[] encodedBytes = encodedString.getBytes("UTF-8");
        encodedBytes = xorEngine(encodedBytes, key, false);
        String hexString = toHex(encodedBytes);
        return hexString;
    }

    public static String decodeFromHex(String hexString, String key) throws UnsupportedEncodingException {
        byte[] encodedByte = fromHex(hexString);
        String encodedString = new String(xorEngine(encodedByte, key, true), "UTF-8");
        String text = encoder(encodedString, key);
        return text;
    }

    private static byte[] xorBytes(byte[] encArr, byte[] keyArr) {

        byte[] encodedBytes = null;

        int byteSum = 0;

        encodedBytes = new byte[encArr.length];
        int encodedStringLen = encArr.length;

        for (byte b : keyArr) {
            byteSum += b;
        }

        if (byteSum > 128) {
            byteSum = (byteSum * encodedStringLen) % (encodedStringLen - 1);
        }

        for (int i = 0; i < encodedStringLen; i++) {
            encodedBytes[i] = (byte) (encArr[i] ^ byteSum);
        }

        return encodedBytes;
    }

    private static String getStringFromCharList(List<Character> textCharList) {
        char[] encodedTextArr = new char[textCharList.size()];

        for (int i = 0; i < textCharList.size(); i++) {
            encodedTextArr[i] = textCharList.get(i).charValue();
        }

        //encodedTextArr = textCharList.stream().map(c -> c.charValue()).collect(Collectors.toList());

        return String.valueOf(encodedTextArr);
    }

    private static char getCodedCharInt(long tempKey, long charInt, int index) {
        long result;

        if (index == 0) {
            result = charInt ^ (tempKey / 3);

        }
        else if ((index % 2) == 0) {
            result = charInt ^ ((tempKey + index) / 2);

        }
        else {
            result = charInt ^ (tempKey + index);
        }

        return (char) result;
    }

    private static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

    private static byte[] fromHex(String hexString) {
        return DatatypeConverter.parseHexBinary(hexString);
    }

    private static String getSHAString(String input, String shaType) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(shaType);
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            result = hashtext;

        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;

    }

    private static Long getUnicodeValue(char ch) {
        return Integer.toUnsignedLong(ch);
    }

    public String decode(String text, int key) throws InterruptedException {
        startTime = System.currentTimeMillis();

        String result = "";
        int tempKey;
        for (int i = 0; i < text.length(); i++) {

            tempKey = text.charAt(i) - key;

            result += (char) tempKey;

        }
        endTime = System.currentTimeMillis();
        runTime = endTime - startTime;

        System.out.println("operação feita em: " + ((float) (runTime) / 1000) + " s");
        return result;

    }

}
