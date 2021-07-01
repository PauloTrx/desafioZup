package br.com.comicszup.erros;

public class Mensagem {

    private String mensagem;
    private String erro;

    public Mensagem() {
    }

    public Mensagem(String mensagem, String erro) {
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
