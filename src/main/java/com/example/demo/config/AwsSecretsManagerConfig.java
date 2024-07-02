package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class AwsSecretsManagerConfig {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    private final ConfigurableEnvironment environment;

    public AwsSecretsManagerConfig(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Bean
    public SecretsManagerClient secretsManagerClient() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        return SecretsManagerClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public String getSecret(String secretName) {
        SecretsManagerClient client = secretsManagerClient();
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();
        GetSecretValueResponse getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        return getSecretValueResponse.secretString();
    }

    @Bean
    public CommandLineRunner loadSecrets() {
        return args -> {
            try {
                String secretValue = getSecret("kostamanage"); // AWS Secrets Manager에서 비밀 이름 'kostamanage'로 값을 로드합니다.
                // JSON 데이터를 파싱하여 key-value 형태로 변환
                Map<String, Object> secretMap = new HashMap<>();
                secretMap.put("manageKey", secretValue); // 비밀 값을 매핑

                System.out.println(secretMap);
                environment.getPropertySources().addFirst(new MapPropertySource("aws-secrets", secretMap));
                // 환경 변수 설정
                System.setProperty("manageKey", secretValue);

            } catch (ResourceNotFoundException e) {
                System.err.println("Secret not found: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error loading secrets: " + e.getMessage());
            }
        };
    }
}