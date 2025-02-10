package com.quiz.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService{
   
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionClient questionClient;
	
	@Override
	public Quiz add(Quiz quiz) {
		return  quizRepository.save(quiz);
	}

	@Override
	public List<Quiz> get() {
		List<Quiz> quizzes=quizRepository.findAll();
		for(Quiz quiz:quizzes) {
			quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
		}
		return quizzes;
	}

	@Override
	public Quiz get(Long id) {
		Quiz quiz=quizRepository.findById(id).orElse(null);
		quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
		return quiz;
	}

}
