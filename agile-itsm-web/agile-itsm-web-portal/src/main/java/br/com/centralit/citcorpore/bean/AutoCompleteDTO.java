package br.com.centralit.citcorpore.bean;

import java.util.List;

public class AutoCompleteDTO {

    private String query;
    private List suggestions;
    private List data;

    public String getQuery() {
        return query;
    }

    public void setQuery(final String query) {
        this.query = query;
    }

    public List getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(final List suggestions) {
        this.suggestions = suggestions;
    }

    public List getData() {
        return data;
    }

    public void setData(final List data) {
        this.data = data;
    }

}
