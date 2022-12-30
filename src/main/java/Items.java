import javax.persistence.*;

@Entity
@Table(name = "items")public class Items {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;
    @Column(name = "val")
    int val;
    @Version
    long version;

    public Items() {

    }

    public long getVersion() {
        return version;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public Items(int val) {
        this.val = val;
    }
}

