package com.trinet.connecto.scheduler;

import com.trinet.connecto.exception.DuplicateVoteException;
import com.trinet.connecto.model.Comment;
import com.trinet.connecto.model.Thread;
import com.trinet.connecto.model.Vote;
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

import java.util.*;

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

    private static final int maxEmployeeId = 100;
    @Scheduled(cron = "0 0 22 * * SUN")
    public void getExpiredThreadContent() throws MessagingException {
        List<Thread> list = threadRepository.getAllExpiredThreads();
        List<Thread> top10Threads = list.stream()
                .sorted(Comparator.comparingInt(Thread::getNoOfLikes).reversed())
                .limit(10)
                .toList();
        List<String> formattedContent = new ArrayList<>();
        for (Thread thread : top10Threads) {
            formattedContent.add(formatEmailContent(thread));
        }
        sendEmails(formattedContent);
    }

    private String formatEmailContent(Thread thread) {
        List<Comment> threadComments = commentRepository.getAllCommentsForThread(thread.getId());
        Comment topComment = threadComments.stream().max(Comparator.comparingInt(Comment::getNoOfLikes))
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
    @Scheduled(fixedRate = 120000)
    public void increaseVoteCountForOpenThreads() {
        List<Thread> openThreads = threadRepository.getAllExpiredThreads();
        for (Thread thread : openThreads) {
            Set<Integer> votedEmpIds = new HashSet<>();
            int numVotes = new Random().nextInt(5) + 1;

            for (int i = 0; i < numVotes; i++) {
                int randomEmpId;
                do {
                    randomEmpId = new Random().nextInt(maxEmployeeId) + 1;
                } while (votedEmpIds.contains(randomEmpId));
                votedEmpIds.add(randomEmpId);
                int agree = new Random().nextInt(2);

                Vote vote = new Vote();
                vote.setThreadId(Math.toIntExact(thread.getId()));
                vote.setEmployeeId(randomEmpId);
                vote.setAgree(agree);

                try {
                    threadRepository.addVoteForThread(vote);
                    threadRepository.increaseVoteCountsForThread(vote);
                } catch (DuplicateVoteException e) {
                    log.warn("Duplicate vote attempt for thread {} with employee ID {}", thread.getId(), randomEmpId);
                }
            }
        }
    }
}


