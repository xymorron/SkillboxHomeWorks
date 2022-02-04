import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainRedis {

    private static final int SLEEP = 1000;
    private static final int TOTAL_USERS = 20;
    private static final int CYCLES_COUNT = 30;
    private static final double PAY_PROBABILITY = 1.0/10;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainRedis.class);

    public static void main(String[] args) {
        RedisStorage redis = new RedisStorage();
        redis.init(TOTAL_USERS);
        int userId;
        for (int i = 0; i < CYCLES_COUNT  ; i++) {
            if (Math.random() < PAY_PROBABILITY){
                userId = redis.getRandom();
                LOGGER.info("> Пользователь {} оплатил платную услугу.", userId);
            } else
                userId = redis.getOldest();
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("— На главной странице показываем пользователя {}", userId);
        }
        redis.shutdown();
    }
}
