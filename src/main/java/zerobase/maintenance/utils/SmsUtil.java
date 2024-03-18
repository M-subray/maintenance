package zerobase.maintenance.utils;

import javax.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {
  @Value("${spring.coolsms.api.key}")
  private String key;

  @Value("${spring.coolsms.api.secretKey}")
  private String secretKey;

  @Value("${spring.coolsms.api.senderNumber}")
  private String senderNumber;

  private DefaultMessageService messageService;

  @PostConstruct
  private void init() {
    this.messageService =
        NurigoApp.INSTANCE.initialize(key, secretKey, "https://api.coolsms.co.kr");
  }

  public SingleMessageSentResponse sendOne(String to, String text) {
    Message message = new Message();

    message.setFrom("01044993345");
    message.setTo(to);
    message.setText(text);

    SingleMessageSentResponse response =
        this.messageService.sendOne(new SingleMessageSendingRequest(message));
    return response;
  }
}
