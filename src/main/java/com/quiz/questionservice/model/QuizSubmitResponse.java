package com.quiz.questionservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizSubmitResponse {
    Integer id;
    String response;
}
