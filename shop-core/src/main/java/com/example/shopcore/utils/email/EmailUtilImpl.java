package com.example.shopcore.utils.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmailUtilImpl implements EmailUtil {

    private final JavaMailSender javaMailSender;

    /**
     * 이메일을 발송한다
     * @param emailDto from, to, title, text 필수
     */
    @Override
    public void sendEmail(EmailDto emailDto) {
        try {
            if (!ObjectUtils.isEmpty(emailDto.getAttachments())) {
                sendMimeEmail(emailDto);
                return;
            }
            sendSimpleEmail(emailDto);
        } catch (Exception e) {
            throw new RuntimeException("이메일발송을 실패했습니다.");
        }
    }

    private void sendSimpleEmail(EmailDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(emailDto.getTitle());
        message.setText(emailDto.getText());
        message.setFrom(emailDto.getFrom());
        message.setTo(emailDto.getTo().toArray(new String[]{}));
        message.setCc(emailDto.getCc().toArray(new String[]{}));
        javaMailSender.send(message);
    }

    private void sendMimeEmail(EmailDto emailDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(emailDto.getTitle());
        helper.setText(emailDto.getText());
        helper.setFrom(emailDto.getFrom());
        helper.setTo(emailDto.getTo().toArray(new String[]{}));
        helper.setCc(emailDto.getCc().toArray(new String[]{}));

        for (Resource resource : emailDto.getAttachments()) {
            String fileName = resource.getFilename() == null ? "attachment" : resource.getFilename();
            if (resource.exists()) {
                helper.addAttachment(fileName, resource);
            }
            log.info("There isn't a file to send email. filename={}", fileName);
        }

        javaMailSender.send(message);
    }

}