import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}

