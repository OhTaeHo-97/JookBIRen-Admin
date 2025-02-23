package com.ablez.admin.info.service;

import com.ablez.admin.first_db.answer.entity.AnswerEp01;
import com.ablez.admin.first_db.answer.entity.AnswerEp02;
import com.ablez.admin.first_db.answer.repository.AnswerEp01Repository;
import com.ablez.admin.first_db.answer.repository.AnswerEp02Repository;
import com.ablez.admin.first_db.hint.entity.HintEp02;
import com.ablez.admin.first_db.hint.repository.HintEp02Repository;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz0Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz1Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz2Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz3Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz4Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.QuizEp01;
import com.ablez.admin.first_db.quiz.entity.episode1.WrongAnswerEp01;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz0Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz1Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz2Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz3Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.QuizEp02;
import com.ablez.admin.first_db.quiz.entity.episode2.WrongAnswerEp02;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1QuizJpaRepository;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1QuizRepository;
import com.ablez.admin.first_db.quiz.repository.episode2.Ep2QuizJpaRepository;
import com.ablez.admin.first_db.quiz.repository.episode2.Ep2QuizRepository;
import com.ablez.admin.first_db.quiz.service.QuizService;
import com.ablez.admin.first_db.security.entity.Authority;
import com.ablez.admin.first_db.user.entity.UserEp00;
import com.ablez.admin.first_db.user.entity.UserEp01;
import com.ablez.admin.first_db.user.entity.UserEp02;
import com.ablez.admin.first_db.user.entity.UserInfo;
import com.ablez.admin.first_db.user.repository.UserEp01JpaRepository;
import com.ablez.admin.first_db.user.repository.UserEp02JpaRepository;
import com.ablez.admin.first_db.user.repository.UserInfoJpaRepository;
import com.ablez.admin.first_db.user.service.UserService;
import com.ablez.admin.second_db.answer.service.SecondAnswerService;
import com.ablez.admin.second_db.hint.repository.HintEp02QuerydslRepository;
import com.ablez.admin.second_db.quiz.repository.Quiz4Ep01QuerydslRepository;
import com.ablez.admin.second_db.quiz.service.SecondQuizService;
import com.ablez.admin.second_db.user.service.SecondUserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class InfoService {
    private final UserService userService;
    private final QuizService quizService;

    private final SecondAnswerService secondAnswerService;
    private final SecondQuizService secondQuizService;
    private final SecondUserService secondUserService;
    private final HintEp02QuerydslRepository secondHintEp02Repository;

    private final Ep1QuizJpaRepository ep1QuizJpaRepository;
    private final Ep2QuizJpaRepository ep2QuizJpaRepository;
    private final Ep1QuizRepository ep1QuizRepository;
    private final Ep2QuizRepository ep2QuizRepository;
    private final AnswerEp01Repository answerEp01Repository;
    private final AnswerEp02Repository answerEp02Repository;
    private final HintEp02Repository hintEp02Repository;
    private final UserInfoJpaRepository userInfoJpaRepository;
    private final UserEp01JpaRepository userEp01JpaRepository;
    private final UserEp02JpaRepository userEp02JpaRepository;
    private final Quiz4Ep01QuerydslRepository quiz4Ep01QuerydslRepository;

    @Transactional(readOnly = true)
    public Workbook getUserInfos() {
        Workbook workbook = new XSSFWorkbook();
        userService.makeUserInfoEp01(workbook);
        userService.makeUserInfoEp02(workbook);
        userService.makeUserInfoEp03(workbook);
        return workbook;
    }

    @Transactional(readOnly = true)
    public Workbook getQuizInfos() {
        Workbook workbook = new XSSFWorkbook();
        quizService.getAllQuizInfosEp01(workbook);
        quizService.getAllQuizInfosEp02(workbook);
        quizService.getAllQuizInfosEp03(workbook);
        return workbook;
    }

    @Transactional(readOnly = true)
    public Workbook getWrongAnswerInfos() {
        Workbook workbook = new XSSFWorkbook();
        quizService.getWrongAnswerInfosEp01(workbook);
        quizService.getWrongAnswerInfosEp02(workbook);
        quizService.getWrongAnswerInfosEp03(workbook);
        return workbook;
    }

    public void makeUserInfos() {
        log.info("챗봇 시작");
        userService.makeChatbotUserInfos();
        log.info("챗봇 끝");
        log.info("ep1 시작");
        userService.makeEp1UserInfos();
        log.info("ep1 끝");
        log.info("ep2 시작");
        userService.makeEp2UserInfos();
        log.info("ep2 끝");
        log.info("ep3 시작");
        userService.makeEp3UserInfos();
        log.info("ep3 끝");
    }

    public void transferDatabase() {
        // TODO: 퀴즈 데이터 이전
        List<com.ablez.admin.second_db.quiz.entity.QuizEp01> quizEp01s = secondQuizService.findAllEp01();
        List<com.ablez.admin.second_db.quiz.entity.QuizEp02> quizEp02s = secondQuizService.findAllEp02();

        List<QuizEp01> quizEp01 = quizEp01s.stream()
                .map(quiz -> new QuizEp01(quiz.getPlaceCode(), quiz.getQuizNumber(), quiz.getQuizCode()))
                .collect(Collectors.toList());
        ep1QuizJpaRepository.saveAll(quizEp01);
        List<QuizEp02> quizEp02 = quizEp02s.stream()
                .map(quiz -> new QuizEp02(quiz.getPlaceCode(), quiz.getQuizNumber(), quiz.getQuizCode()))
                .collect(Collectors.toList());
        ep2QuizJpaRepository.saveAll(quizEp02);

        // TODO: 정답 데이터 이전
        List<com.ablez.admin.second_db.answer.entity.AnswerEp01> answerEp01s = secondAnswerService.findAllEp01();
        List<com.ablez.admin.second_db.answer.entity.AnswerEp02> answerEp02s = secondAnswerService.findAllEp02();

        List<AnswerEp01> answerEp01 = answerEp01s.stream().map(answer -> {
            QuizEp01 quiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(answer.getQuizEp01().getPlaceCode(),
                    answer.getQuizEp01()
                            .getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
            return new AnswerEp01(answer.getAnswer(), quiz);
        }).collect(Collectors.toList());
        answerEp01Repository.saveAll(answerEp01);

        List<AnswerEp02> answerEp02 = answerEp02s.stream().map(answer -> {
            QuizEp02 quiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(answer.getQuizEp02().getPlaceCode(),
                    answer.getQuizEp02()
                            .getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
            return new AnswerEp02(answer.getAnswer(), quiz);
        }).collect(Collectors.toList());
        answerEp02Repository.saveAll(answerEp02);

        // TODO: 힌트 데이터 이전
        List<com.ablez.admin.second_db.hint.entity.HintEp02> hintEp02s = secondHintEp02Repository.findAll();
        List<HintEp02> hints = hintEp02s.stream().map(hint -> {
            QuizEp02 quiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(hint.getQuiz().getPlaceCode(),
                    hint.getQuiz().getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
            return new HintEp02(hint.getHint(), hint.getHintImage(), hint.getHintOrder(), quiz);
        }).collect(Collectors.toList());
        hintEp02Repository.saveAll(hints);

        // TODO: 사용자 및 로그 데이터 이전
        // 챗봇
        List<com.ablez.admin.second_db.user.entity.UserEp00> userEp00s = secondUserService.findAllEp00();
        userEp00s.stream().forEach(user -> {
            UserInfo userInfo = new UserInfo(user.getCode());
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);
            new UserEp00(user.getCode(), userInfo);
        });

        // Ep01
        List<com.ablez.admin.second_db.user.entity.UserEp01> userEp01s = secondUserService.findAllEp01();
        userEp01s.stream().forEach(user -> {
            UserInfo userInfo = new UserInfo(user.getCode());
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);

            UserEp01 userEp01 = new UserEp01(user.getCode(), user.getScore(), user.getAnswerStatusCode(),
                    user.getAnswerCount(),
                    user.getSolvedQuizCount(), user.getCriminal(), user.getAccessToken(), user.getAnswerTime(),
                    user.getFirstLoginTime(), userInfo);
            userEp01JpaRepository.save(userEp01);

            com.ablez.admin.second_db.quiz.entity.Quiz0Ep01 quiz0Ep01s = user.getQuiz0s();
            Quiz0Ep01 quiz0Ep01 = null;
            if (quiz0Ep01s != null) {
                QuizEp01 quiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(quiz0Ep01s.getQuiz().getPlaceCode(),
                        quiz0Ep01s.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                quiz0Ep01 = new Quiz0Ep01(quiz0Ep01s.getQuizNumber(), quiz0Ep01s.getFirstAccessTime(),
                        quiz0Ep01s.getFirstAnswerTime(), quiz0Ep01s.getGetHintTime(), null,
                        quiz0Ep01s.getGetAnswerTime(),
                        userEp01, quiz);
            }

            List<Quiz1Ep01> quiz1Ep01s = user.getQuiz1s().stream().map(quiz1 -> {
                QuizEp01 currentQuiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(quiz1.getQuiz().getPlaceCode(),
                        quiz1.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new Quiz1Ep01(quiz1.getQuizNumber(), quiz1.getFirstAccessTime(), quiz1.getFirstAnswerTime(),
                        quiz1.getGetHintTime(), null, quiz1.getGetAnswerTime(), userEp01, currentQuiz);
            }).collect(Collectors.toList());

            List<Quiz2Ep01> quiz2Ep01s = user.getQuiz2s().stream().map(quiz2 -> {
                QuizEp01 currentQuiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(quiz2.getQuiz().getPlaceCode(),
                        quiz2.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new Quiz2Ep01(quiz2.getQuizNumber(), quiz2.getFirstAccessTime(), quiz2.getFirstAnswerTime(),
                        quiz2.getGetHintTime(), null, quiz2.getGetAnswerTime(), userEp01, currentQuiz);
            }).collect(Collectors.toList());

            List<Quiz3Ep01> quiz3Ep01s = user.getQuiz3s().stream().map(quiz3 -> {
                QuizEp01 currentQuiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(quiz3.getQuiz().getPlaceCode(),
                        quiz3.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new Quiz3Ep01(quiz3.getQuizNumber(), quiz3.getFirstAccessTime(), quiz3.getFirstAnswerTime(),
                        quiz3.getGetHintTime(), null, quiz3.getGetAnswerTime(), userEp01, currentQuiz);
            }).collect(Collectors.toList());

            com.ablez.admin.second_db.quiz.entity.Quiz4Ep01 quiz4Ep01s = user.getQuiz4s();
//            com.ablez.admin.second_db.quiz.entity.Quiz4Ep01 quiz4Ep01s = quiz4Ep01QuerydslRepository.findByUser(user)
//                    .orElseThrow(() -> new RuntimeException("없음"));
            Quiz4Ep01 quiz4Ep01 = null;
            if (quiz4Ep01s != null) {
                QuizEp01 tempQuiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(
                        quiz4Ep01s.getQuiz().getPlaceCode(),
                        quiz4Ep01s.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                quiz4Ep01 = new Quiz4Ep01(quiz4Ep01s.getQuizNumber(), quiz4Ep01s.getFirstAccessTime(),
                        quiz4Ep01s.getFirstAnswerTime(), quiz4Ep01s.getGetHintTime(), quiz4Ep01s.getGetAnswerTime(),
                        userEp01, tempQuiz);
            }
//            QuizEp01 tempQuiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(quiz4Ep01s.getQuiz().getPlaceCode(),
//                    quiz4Ep01s.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
//            Quiz4Ep01 quiz4Ep01 = new Quiz4Ep01(quiz4Ep01s.getQuizNumber(), quiz4Ep01s.getFirstAccessTime(),
//                    quiz4Ep01s.getFirstAnswerTime(), quiz4Ep01s.getGetHintTime(), quiz4Ep01s.getGetAnswerTime(),
//                    userEp01, tempQuiz);

            List<com.ablez.admin.second_db.quiz.entity.WrongAnswerEp01> prevWrongAnswers = user.getWrongAnswers();
            List<WrongAnswerEp01> wrongAnswers = user.getWrongAnswers().stream().map(wrongAnswer -> {
                QuizEp01 currentQuiz = ep1QuizRepository.findByPlaceNumberAndQuizNumber(
                                wrongAnswer.getQuiz().getPlaceCode(), wrongAnswer.getQuiz().getQuizNumber())
                        .orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new WrongAnswerEp01(wrongAnswer.getAnswer(), wrongAnswer.getTime(), userEp01, currentQuiz);
            }).collect(Collectors.toList());

            userEp01.setQuiz0s(quiz0Ep01);
            userEp01.setQuiz1s(quiz1Ep01s);
            userEp01.setQuiz2s(quiz2Ep01s);
            userEp01.setQuiz3s(quiz3Ep01s);
            userEp01.setQuiz4s(quiz4Ep01);
            userEp01.setWrongAnswers(wrongAnswers);
        });

        // Ep02
        List<com.ablez.admin.second_db.user.entity.UserEp02> userEp02s = secondUserService.findAllEp02();
        userEp02s.stream().forEach(user -> {
            UserInfo userInfo = new UserInfo(user.getCode());
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);

            UserEp02 userEp02 = new UserEp02(user.getCode(), user.getScore(), user.getAnswerStatusCode(),
                    user.getAnswerCount(), user.getSolvedQuizCount(), user.getCriminal1(), user.getCriminal2(),
                    user.getAccessToken(), user.getAnswerTime(), user.getFirstLoginTime(), userInfo);
            userEp02JpaRepository.save(userEp02);

            List<Quiz0Ep02> quiz0Ep02s = user.getQuiz0s().stream().map(quiz0 -> {
                QuizEp02 currentQuiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(quiz0.getQuiz().getPlaceCode(),
                        quiz0.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new Quiz0Ep02(quiz0.getQuizNumber(), quiz0.getFirstAccessTime(), quiz0.getFirstAnswerTime(),
                        quiz0.getFirstGetHintTime(), quiz0.getSecondGetHintTime(), quiz0.getGetAnswerTime(), userEp02,
                        currentQuiz);
            }).collect(Collectors.toList());

            List<Quiz1Ep02> quiz1Ep02s = user.getQuiz1s().stream().map(quiz1 -> {
                QuizEp02 currentQuiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(quiz1.getQuiz().getPlaceCode(),
                        quiz1.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new Quiz1Ep02(quiz1.getQuizNumber(), quiz1.getFirstAccessTime(), quiz1.getFirstAnswerTime(),
                        quiz1.getFirstGetHintTime(), quiz1.getSecondGetHintTime(), quiz1.getGetAnswerTime(), userEp02,
                        currentQuiz);
            }).collect(Collectors.toList());

            List<Quiz2Ep02> quiz2Ep02s = user.getQuiz2s().stream().map(quiz2 -> {
                QuizEp02 currentQuiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(quiz2.getQuiz().getPlaceCode(),
                        quiz2.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new Quiz2Ep02(quiz2.getQuizNumber(), quiz2.getFirstAccessTime(), quiz2.getFirstAnswerTime(),
                        quiz2.getFirstHintTime(), quiz2.getSecondHintTime(), quiz2.getGetAnswerTime(), userEp02,
                        currentQuiz);
            }).collect(Collectors.toList());

            com.ablez.admin.second_db.quiz.entity.Quiz3Ep02 quiz3Ep02s = user.getQuiz3();
            Quiz3Ep02 quiz3Ep02 = null;
            if (quiz3Ep02s != null) {
                QuizEp02 tempQuiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(
                        quiz3Ep02s.getQuiz().getPlaceCode(),
                        quiz3Ep02s.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                quiz3Ep02 = new Quiz3Ep02(quiz3Ep02s.getQuizNumber(), quiz3Ep02s.getFirstAccessTime(),
                        quiz3Ep02s.getFirstAnswerTime(), quiz3Ep02s.getGetHintTime(), quiz3Ep02s.getGetAnswerTime(),
                        userEp02, tempQuiz);
            }
//            QuizEp02 tempQuiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(quiz3Ep02s.getQuiz().getPlaceCode(),
//                    quiz3Ep02s.getQuizNumber()).orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
//            Quiz3Ep02 quiz3Ep02 = new Quiz3Ep02(quiz3Ep02s.getQuizNumber(), quiz3Ep02s.getFirstAccessTime(),
//                    quiz3Ep02s.getFirstAnswerTime(), quiz3Ep02s.getGetHintTime(), quiz3Ep02s.getGetAnswerTime(),
//                    userEp02, tempQuiz);

            List<com.ablez.admin.second_db.quiz.entity.WrongAnswerEp02> prevWrongAnswers = user.getWrongAnswers();
            List<WrongAnswerEp02> wrongAnswers = user.getWrongAnswers().stream().map(wrongAnswer -> {
                QuizEp02 currentQuiz = ep2QuizRepository.findByPlaceNumberAndQuizNumber(
                                wrongAnswer.getQuiz().getPlaceCode(), wrongAnswer.getQuiz().getQuizNumber())
                        .orElseThrow(() -> new RuntimeException("퀴즈 정보가 없음"));
                return new WrongAnswerEp02(wrongAnswer.getAnswer(), wrongAnswer.getTime(), userEp02, currentQuiz);
            }).collect(Collectors.toList());

            userEp02.setQuiz0s(quiz0Ep02s);
            userEp02.setQuiz1s(quiz1Ep02s);
            userEp02.setQuiz2s(quiz2Ep02s);
            userEp02.setQuiz3(quiz3Ep02);
            userEp02.setWrongAnswers(wrongAnswers);
        });
    }
}
