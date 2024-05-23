package com.ping.Cription;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

@RestController
public class DecryptionController {
    @PostMapping("/decrypt")
    public DecryptedDataDTO decryptData(@RequestBody DecryptionRequestDTO request) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(request.getPrivateKey());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] encryptedData = Base64.getDecoder().decode(request.getData());
        byte[] decryptedData = cipher.doFinal(encryptedData);
        String decryptedDataString = new String(decryptedData, StandardCharsets.UTF_8);

        return new DecryptedDataDTO(decryptedDataString);
    }

    static class DecryptionRequestDTO {
        private String privateKey;
        private String data;

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    static class DecryptedDataDTO {
        private String decryptedData;

        public DecryptedDataDTO(String decryptedData) {
            this.decryptedData = decryptedData;
        }

        public String getDecryptedData() {
            return decryptedData;
        }
    }
}