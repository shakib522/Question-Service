package com.quiz.questionservice.service;


import com.quiz.questionservice.QuestionServiceApplication;
import com.quiz.questionservice.dao.QuestionDao;
import com.quiz.questionservice.entity.Question;
import com.quiz.questionservice.model.QuizModel;
import com.quiz.questionservice.model.QuizSubmitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionDao dao;
    private final QuestionDao questionDao;
    private final QuestionServiceApplication questionServiceApplication;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            if (dao.findByCategory(category).isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(dao.findByCategory(category), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            dao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuizModel>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuizModel> quizModels = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionIds) {
            questions.add(questionDao.findById(id).get());
        }
        for (Question question : questions) {
            QuizModel model = QuizModel.builder()
                    .id(question.getId())
                    .questionTitle(question.getQuestionTitle())
                    .option1(question.getOption1())
                    .option2(question.getOption2())
                    .option3(question.getOption3())
                    .option4(question.getOption4())
                    .build();
            quizModels.add(model);
        }

        return new ResponseEntity<>(quizModels,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<QuizSubmitResponse> responses) {
        int score=0;
        for (QuizSubmitResponse response : responses) {
            Question question = questionDao.findById(response.getId()).get();
            if (question.getRightAnswer().equals(response.getResponse())) {
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
