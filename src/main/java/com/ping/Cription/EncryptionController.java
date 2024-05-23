package com.ping.Cription;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

@RestController
public class EncryptionController {

    @PostMapping("/encrypt")
    public EncryptedDataDTO encryptData(@RequestBody EncryptionRequestDTO request) throws Exception {
        try {
            // 验证Base64编码的公钥
            if (!isValidBase64(request.getPublicKey())) {
                throw new IllegalArgumentException("Invalid Base64 public key");
            }

            System.out.println("Data to encrypt: " + request.getData());
            System.out.println("Public key: " + request.getPublicKey());

            byte[] publicKeyBytes = Base64.getDecoder().decode(request.getPublicKey());
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedData = cipher.doFinal(request.getData().getBytes(StandardCharsets.UTF_8));
            String encryptedDataString = Base64.getEncoder().encodeToString(encryptedData);

            return new EncryptedDataDTO(encryptedDataString);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to decode Base64 input: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Encryption error: " + e.getMessage(), e);
        }
    }

    // 验证Base64字符串是否合法
    private boolean isValidBase64(String base64) {
        return base64.matches("^[A-Za-z0-9+/=]+$");
    }

    static class EncryptionRequestDTO {
        private String publicKey;
        private String data;

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    static class EncryptedDataDTO {
        private String encryptedData;

        public EncryptedDataDTO(String encryptedData) {
            this.encryptedData = encryptedData;
        }

        public String getEncryptedData() {
            return encryptedData;
        }
    }
}
