package br.com.comicszup.response;

public class ComicsResponse {
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private DataComicsResponse data;

    public String getCopyright() { return copyright; }

    public String getAttributionText() { return attributionText; }

    public String getAttributionHTML() { return attributionHTML; }

    public DataComicsResponse getData() { return data; }
}
