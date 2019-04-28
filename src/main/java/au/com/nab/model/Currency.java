package au.com.nab.model;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Currency {
    private String name;
    private Double price;
    private @Id @GeneratedValue Long id;
    private LocalDateTime time;
    public Currency(String name, Double price, LocalDateTime time){
        this.name= name;
        this.price=price;
        this.time=time;

    }
public Currency () {

}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
