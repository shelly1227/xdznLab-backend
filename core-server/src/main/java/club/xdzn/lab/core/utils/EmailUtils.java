package club.xdzn.lab.core.utils;

import club.xdzn.lab.common.enums.CodeEnum;
import club.xdzn.lab.common.exception.CustomException;
import club.xdzn.lab.common.model.dto.MailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 邮箱工具类
 * @author shelly
 * @date 2025/5/28
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class EmailUtils {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    private final TemplateEngine templateEngine;

    /**
     * 判断邮箱是否合法
     */
    public static void isValidEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new CustomException(CodeEnum.EMAIL_EMPTY);
        }
        if (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email)) {
            throw new CustomException(CodeEnum.EMAIL_FORMAT_ERROR);
        }
    }
    public static boolean isValid(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
    }

    @Async
    public void sendSimpleMail(MailDTO mailDTO) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom(sendMailer);
        simpleMail.setTo(mailDTO.getToEmail());
        simpleMail.setSubject(mailDTO.getSubject());
        simpleMail.setText(mailDTO.getContent());
        javaMailSender.send(simpleMail);
    }
    @Async
    public void sendHtmlMail(MailDTO mailDTO) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            Context context = new Context();
            context.setVariables(mailDTO.getContentMap());
            String process = templateEngine.process(mailDTO.getTemplate(), context);
            mimeMessageHelper.setFrom(sendMailer);
            mimeMessageHelper.setTo(mailDTO.getToEmail());
            mimeMessageHelper.setSubject(mailDTO.getSubject());
            mimeMessageHelper.setText(process, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("sendHtmlMail fail, {}", e.getMessage());
        }
    }
}