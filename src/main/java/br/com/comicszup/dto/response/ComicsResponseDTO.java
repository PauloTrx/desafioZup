package br.com.comicszup.dto.response;

public class ComicsResponseDTO {
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private DataComicsResponseDTO data;

    public String getCopyright() { return copyright; }

    public String getAttributionText() { return attributionText; }

    public String getAttributionHTML() { return attributionHTML; }

    public DataComicsResponseDTO getData() { return data; }
}
