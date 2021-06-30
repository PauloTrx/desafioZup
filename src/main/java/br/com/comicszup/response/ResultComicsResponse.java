package br.com.comicszup.response;

import java.util.List;

public class ResultComicsResponse {
    private String title;
    private List<PricesComicsResponse> prices;
    private CreatorsComicsResponse creators;
    private String isbn;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PricesComicsResponse> getPrices() {
        return prices;
    }

    public void setPrices(List<PricesComicsResponse> prices) {
        this.prices = prices;
    }

    public CreatorsComicsResponse getCreators() {
        return creators;
    }

    public void setCreators(CreatorsComicsResponse creators) {
        this.creators = creators;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
