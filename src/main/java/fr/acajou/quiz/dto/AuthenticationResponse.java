package fr.acajou.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponse(@JsonProperty("access_token")
                                     String accessToken) {

}
