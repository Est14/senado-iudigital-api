package com.est14.senadoiudigital.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    // Mandatory fields Auth2.0
    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "expires_in")
    private int expiresIn;

    // Optional Fields
    @JsonProperty(value = "issued_at")
    private String issuedAt;
    @JsonProperty(value = "client_id")
    private String clientId;
}
