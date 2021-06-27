package br.com.comicszup.dto.response;

public class ResultComicsResponseDTO {
    private Integer id;
    private String format;
    private String upc;
    private String pageCount;

    public Integer getId() {
        return id;
    }

    public String getFormat() { return format; }

    public String getUpc() {
        return upc;
    }

    public String getPageCount() { return pageCount; }
}
