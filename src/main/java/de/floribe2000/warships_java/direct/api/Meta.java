package de.floribe2000.warships_java.direct.api;

/**
 * A representation of the general meta block that is available on all api requests.
 */
public class Meta {

    /**
     * The number of results returned on this page
     */
    private int count = 0;

    /**
     * The total number of pages for this request
     */
    private int page_total = 0;

    /**
     * The total number of results for this request
     */
    private int total = 0;

    /**
     * The limit of results per page
     */
    private int limit = 0;

    /**
     * The current page number
     */
    private int page = 0;

    public int getCount() {
        return this.count;
    }

    public int getPage_total() {
        return this.page_total;
    }

    public int getTotal() {
        return this.total;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getPage() {
        return this.page;
    }
}
