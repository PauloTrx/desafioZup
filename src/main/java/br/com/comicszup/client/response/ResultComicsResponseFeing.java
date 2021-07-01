package br.com.comicszup.client.response;

import java.util.List;

public class ResultComicsResponseFeing {
    private String title;
    private List<PricesComicsResponseFeing> prices;
    private CreatorsComicsResponseFeing creators;
    private String isbn;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PricesComicsResponseFeing> getPrices() {
        return prices;
    }

    public void setPrices(List<PricesComicsResponseFeing> prices) {
        this.prices = prices;
    }

    public CreatorsComicsResponseFeing getCreators() {
        return creators;
    }

    public void setCreators(CreatorsComicsResponseFeing creators) {
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
