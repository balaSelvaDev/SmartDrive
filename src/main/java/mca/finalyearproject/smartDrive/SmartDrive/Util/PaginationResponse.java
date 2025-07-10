package mca.finalyearproject.smartDrive.SmartDrive.Util;

import java.util.List;

public class PaginationResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPages;
    private long totalItems;

    // Constructors
    public PaginationResponse() {}

    public PaginationResponse(List<T> content, int currentPage, int totalPages, long totalItems) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    // Getters and Setters
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}