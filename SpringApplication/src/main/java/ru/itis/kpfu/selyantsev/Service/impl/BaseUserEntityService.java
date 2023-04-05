package ru.itis.kpfu.selyantsev.Service.impl;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.Service.UserEntityService;
import ru.itis.kpfu.selyantsev.config.MailConfig;
import ru.itis.kpfu.selyantsev.dto.request.UserEntityRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserEntityResponseDto;
import ru.itis.kpfu.selyantsev.model.newModel.UserEntity;
import ru.itis.kpfu.selyantsev.repository.newRepository.UserEntityRepository;
import ru.itis.kpfu.selyantsev.util.mapper.UserEntityMapper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BaseUserEntityService implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;


    @Override
    public List<UserEntityResponseDto> findAll() {
        return userEntityRepository.findAll()
                .stream()
                .map(UserEntityMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<UserEntityResponseDto> findById(Integer id) {
        return userEntityRepository.findById(id)
                .map(UserEntityMapper::toResponse);
    }

    @Override
    public UserEntityResponseDto create(UserEntityRequest userEntityRequest, String url) {
        String code = RandomString.make(64);
        userEntityRequest.setVerificationCode(code);
        UserEntity userEntity = UserEntityMapper.toEntity(userEntityRequest);
        userEntityRepository.save(userEntity);
        sendVerificationEmail(userEntityRequest.getUserEmail(), userEntityRequest.getUserEmail(), code, url);
        return UserEntityMapper.toResponse(userEntity);
    }

    @Override
    public boolean verify(String verificationCode) {
        UserEntity userEntity = userEntityRepository.findByVerificationCode(verificationCode)
                .orElseThrow();
        if (userEntity != null) {
            userEntity.setVerificationCode(verificationCode);
            userEntity.setEnabled(true);
            userEntityRepository.save(userEntity);
            return true;
        }
        return false;
    }

    @Override
    public void sendVerificationEmail(String mail, String name, String code, String url) {
        String from = mailConfig.getFrom();
        String sender = mailConfig.getSender();
        String subject = mailConfig.getSubject();
        String content = mailConfig.getContent();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setFrom(from, sender);

            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setSubject(subject);

            content = content.replace("{name}", name);
            content = content.replace("{url}", url + "/verification?code=" + code);

            mimeMessageHelper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

    @Override
    public Optional<UserEntityResponseDto> getUserByEmail(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
        }
        return userEntityRepository.getUserEntityByUserEntityEmail(email)
                .map(UserEntityMapper::toResponse);
    }
}
