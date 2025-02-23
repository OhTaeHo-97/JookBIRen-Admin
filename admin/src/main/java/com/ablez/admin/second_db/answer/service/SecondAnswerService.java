package com.ablez.admin.second_db.answer.service;

import com.ablez.admin.second_db.answer.entity.AnswerEp01;
import com.ablez.admin.second_db.answer.entity.AnswerEp02;
import com.ablez.admin.second_db.answer.repository.AnswerEp01QuerydslRepository;
import com.ablez.admin.second_db.answer.repository.AnswerEp02QuerydslRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SecondAnswerService {
    private final AnswerEp01QuerydslRepository answerEp01Repository;
    private final AnswerEp02QuerydslRepository answerEp02Repository;

    public List<AnswerEp01> findAllEp01() {
        return answerEp01Repository.findAll();
    }

    public List<AnswerEp02> findAllEp02() {
        return answerEp02Repository.findAll();
    }
}
