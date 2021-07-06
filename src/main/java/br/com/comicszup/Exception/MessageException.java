package br.com.comicszup.Exception;

public class MessageException {

    private String mensagem;

    public MessageException() {
    }

    public MessageException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
