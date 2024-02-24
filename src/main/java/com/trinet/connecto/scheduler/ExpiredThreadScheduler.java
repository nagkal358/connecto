package com.trinet.connecto.scheduler;

import com.trinet.connecto.model.Comment;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.repository.impl.CommentRepositoryImpl;
import com.trinet.connecto.repository.impl.ThreadRepositoryImpl;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
@Slf4j
public class ExpiredThreadScheduler {

    @Autowired
    private ThreadRepositoryImpl threadRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CommentRepositoryImpl commentRepository;


    @Scheduled(cron = "0 0 22 * * SUN")
    public void getExpiredThreadContent() throws MessagingException {
        List<Thread> list = threadRepository.getAllExpiredThreads();
        List<Thread> top10Threads = list.stream()
                .sorted(Comparator.comparingInt(Thread::getNoOfLikes).reversed())
                .limit(10)
                .collect(Collectors.toList());
        List<String> formattedContent = new ArrayList<>();
        for (Thread thread : top10Threads) {
            formattedContent.add(formatEmailContent(thread));
        }
        sendEmails(formattedContent);
    }

    private String formatEmailContent(Thread thread) {
        List<Comment> threadComments = commentRepository.getAllCommentsForThread(thread.getId());
        Comment topComment = threadComments.stream()
                .sorted(Comparator.comparingInt(Comment::getNoOfLikes).reversed())
                .findFirst()
                .orElse(null);

        String content = "<b>Dear employees,</b>\n\n"
                + "Here are the top threads of the week, along with their most liked comments:\n\n";

        content += "**Thread Title:** " + thread.getTitle() + "\n"
                + "**Likes:** " + thread.getNoOfLikes() + "\n";

        if (topComment != null) {
            content += "**Top Comment:**\n"
                    + " - " + topComment.getComment() + "\n"
                    + " - Likes: " + topComment.getNoOfLikes() + "\n\n";
        } else {
            content += "**No comments found for this thread.**\n\n";
        }

        content += "You can view and discuss these threads on our platform: [link to platform]\n\n"
                + "Have a great week!\n\n"
                + "Sincerely,\n"
                + "[Your company or sender name]";

        return content;
    }

    private void sendEmails(List<String> contentList) throws MessagingException {
        for (String content : contentList) {
            MimeMessage message = mailSender.createMimeMessage();
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("hr@persistent.com"));
            message.setSubject("Top Threads and Comments This Week");
            message.setText(content, "UTF-8");

            try {
                mailSender.send(message);
                log.info("Email sent successfully for thread: {}", content);
            } catch (Exception e) {
                log.error("Error sending email for thread: {}", content, e);
            }
        }
    }
}


