package br.com.comicszup.erro;

public class Message {

    private String mensagem;
    private String erro;

    public Message() {
    }

    public Message(String mensagem, String erro) {
        this.mensagem = mensagem;
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getErro() { return erro; }

    public void setErro(String erro) { this.erro = erro; }
}
