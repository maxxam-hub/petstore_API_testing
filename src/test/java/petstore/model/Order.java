package petstore.model;

public class Order {
    
    public long id;
    public long petId;
    public int quantity;
    public String shipDate;
    public String status;
    public boolean complete;

    public Order() {
    }

    public Order(long petId, int quantity, String shipDate, String status, boolean complete) {
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Order(long id, long petId, int quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }
}
