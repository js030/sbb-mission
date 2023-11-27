package com.example.sbbmission.question;

import com.example.sbbmission.DataNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id){
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()){
            return question.get();
        }else {
            throw new DataNotFoundException("question not found");
        }
    }
}