package example.com.musics.domain.model;

public class ErrorResponse {

    private String timeDate;
    private Integer status;
    private String title;
    private String message;

    public ErrorResponse(String timeDate, Integer status, String title, String message){
        this.timeDate = timeDate;
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}