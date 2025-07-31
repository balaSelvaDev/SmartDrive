package mca.finalyearproject.smartDrive.SmartDrive.Config;

public class OrderNotification {
    private String message;
    private Long orderId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderNotification(String message, Long orderId) {
        this.message = message;
        this.orderId = orderId;
    }
}
