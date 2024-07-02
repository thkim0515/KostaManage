package com.example.demo.config;


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@Component
public class AwsSecretsManagerConfig  {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, String> getSecret() {
        String secretName = "kostamanage"; // 시크릿 이름
        Region region = Region.AP_NORTHEAST_2; // AWS 지역 설정

        // AWS 자격 증명 읽어오기
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                accessKey,
                secretKey
        );

        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        String secret = getSecretValueResponse.secretString();

        Map<String, String> secretMap = null;
        try {
            secretMap = objectMapper.readValue(secret, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretMap;
    }
}
