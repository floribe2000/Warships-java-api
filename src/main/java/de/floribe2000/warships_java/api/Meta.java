package de.floribe2000.warships_java.api;

import lombok.Getter;

/**
 * A representation of the general meta block that is available on all api requests.
 */
@Getter
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
}
