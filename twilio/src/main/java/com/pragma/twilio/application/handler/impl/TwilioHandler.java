package com.pragma.twilio.application.handler.impl;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

import com.pragma.twilio.application.dto.request.TwilioRequestDto;
import com.pragma.twilio.application.handler.ITwilioHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TwilioHandler implements ITwilioHandler {

    public static final String ACCOUNT_SID = "ACb7b21c77f05b590aa7581c7f4ac39af0";
    public static final String AUTH_TOKEN = "0e8b45ef2cba4a18acb4895847c6faf8";

    @Override
    public void sendMessage(TwilioRequestDto twilioRequestDto) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(twilioRequestDto.getNumber()),
                        new com.twilio.type.PhoneNumber("+12706791028"),
                        twilioRequestDto.getMessage())
                .create();
    }
}
