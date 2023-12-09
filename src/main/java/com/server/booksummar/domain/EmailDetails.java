package com.server.booksummar.domain;

import lombok.Data;

@Data
public class EmailDetails {

    private String recipient;

    private String subject;

    private String messageBody;

}
