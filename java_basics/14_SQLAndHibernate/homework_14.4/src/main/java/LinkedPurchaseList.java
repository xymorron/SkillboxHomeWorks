import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class LinkedPurchaseList {
    @EmbeddedId
    private SubscriptionKey id;

    public LinkedPurchaseList(SubscriptionKey id) {
        this.id = id;
    }
}
