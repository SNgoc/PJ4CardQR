package fpt.aptech.projectcard.domain;

public class Order_Process {

    private Integer id;

    private EProecess name;

    private Orders order;

    public Order_Process(Integer id, EProecess name) {
        this.id = id;
        this.name = name;
    }

    public Order_Process(EProecess name) {
        this.name = name;
    }

    public Order_Process() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EProecess getName() {
        return name;
    }

    public void setName(EProecess name) {
        this.name = name;
    }

}
