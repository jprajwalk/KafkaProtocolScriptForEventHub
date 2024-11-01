package com.apache.KafkaAutomation;

import org.junit.jupiter.api.Test;

import KafkaProducer.KafkaProducerSendMessage;


public class AppTest 
{

    @Test
    public void shouldAnswerWithTrue() 
    {
    	KafkaProducerSendMessage p = new KafkaProducerSendMessage();
    	p.SendingMessageKafkaProtocol();
    }
}
