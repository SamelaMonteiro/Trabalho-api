package com.server.booksummar.exception;

import java.time.ZonedDateTime;

public class DefaultException {

    private Integer status;
    private String message;
    private ZonedDateTime zonedDateTime;

    public DefaultException() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return message;
    }

    public void setMensagem(String mensagem) {
        this.message = mensagem;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }
}
