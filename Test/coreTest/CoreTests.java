package coreTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

import core.EncoderEngine;

class CoreTests {

    @Test
    void encodeUsingDataTypeConverter_test() throws InterruptedException, UnsupportedEncodingException {
        String text = "Isso é um teste";
        String key = "123";

        String hexString = EncoderEngine.encodeToHex(text, key);
        String decodedText = EncoderEngine.decodeFromHex(hexString, key);

        assertTrue(text.equals(decodedText));
    }

}
