import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
public class RedisStorage {

    private RedissonClient redissonClient;
    private RScoredSortedSet<String> users;
    private final static String KEY = "USERS";
    private final static String REDIS_LINK = "redis://localhost:6379";
    private static final Logger LOGGER = LoggerFactory.getLogger(MainRedis.class);

    void init(int totalUsers) {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_LINK);
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException exception) {
            LOGGER.error("Не удалось подключиться к redis", exception);
        }
        users = redissonClient.getScoredSortedSet(KEY);
        for (int i = 0; i < totalUsers; updateUser(i++));
    }

    public int getOldest() {
        int result = Integer.parseInt(users.first());
        updateUser(result);
        return result;
    }

    public int getRandom() {
        int result = Integer.parseInt(users.random());
        updateUser(result);
        return result;
    }

    void shutdown() {
        redissonClient.shutdown();
    }

    void updateUser(int userId) {
        users.add(new Date().getTime(), String.valueOf(userId));
    }
}
