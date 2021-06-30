package br.com.comicszup.response;

import java.util.List;

public class CreatorsComicsResponse {
    private List<ItemsComicsResponse> items;

    public List<ItemsComicsResponse> getItems() {
        return items;
    }

    public void setItems(List<ItemsComicsResponse> items) {
        this.items = items;
    }
}
