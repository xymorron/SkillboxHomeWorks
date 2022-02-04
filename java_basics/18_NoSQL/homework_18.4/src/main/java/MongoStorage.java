import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.*;

public class MongoStorage {

    private final MongoClient mongoClient;
    private final MongoCollection<Document> shops;
    private final MongoCollection<Document> goods;

    public MongoStorage(String mongoLink) {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        mongoClient = new MongoClient(mongoLink);
        MongoDatabase database = mongoClient.getDatabase("test");
        IndexOptions uniqueIndexOption = new IndexOptions().unique(true);
        shops = database.getCollection("shops");
        shops.createIndex(Indexes.ascending("name"), uniqueIndexOption);
        goods = database.getCollection("goods");
        goods.createIndex(Indexes.ascending("name"), uniqueIndexOption);
    }

    public void addShop(String name) {
        Document shop = new Document("name", name);
        try {
            shops.insertOne(shop);
            System.out.println(name + " added to shops");
        } catch (MongoWriteException exception) {
            System.out.println("Shop adding failed:");
            System.out.println(exception.getError());
        }
    }

    public void addGoods(String name, int cost) {
        Document new_goods = new Document();
        new_goods.put("name", name);
        new_goods.put("cost", cost);
        try {
            goods.insertOne(new_goods);
            System.out.println(name + " added to goods");
        } catch (MongoWriteException exception) {
            System.out.println("Goods adding failed:");
            System.out.println(exception.getError());
        }
    }

    public void addSale(String productName, String shopName) {
        Document shop = new Document("name", shopName);
        if (shops.find(shop).first() == null) {
            System.out.println("there's no " + shopName + " in shops");
            return;
        }
        Document product = new Document("name", productName);
        if (goods.find(product).first() == null) {
            System.out.println("there's no " + productName + " in goods");
            return;
        }
        shops.updateOne(shop, new Document("$addToSet", new Document("goods", productName)));
        System.out.println(productName + " added to " + shopName);
    }

    public void saleStats() {
        AggregateIterable<Document> stats = shops.aggregate(Arrays.asList(
            lookup("goods", "goods", "name", "product"),
            unwind("$product"),
            sort(new Document("product.cost", 1)),
            group("$name",
                sum("items_count", 1),
                avg("avg_price", "$product.cost"),
                first("cheapest_product", "$product"),
                last("pricey_product", "$product"),
                sum("cheep_goods", new Document("$cond", Arrays.asList(new Document("cost", 100), 1, 0)))
            ),
            sort(new Document("_id", 1))
        ));
        stats.forEach((Consumer<? super Document>) shop -> {
            StringJoiner joiner = new StringJoiner("\n\t");
            joiner.add("Statistics for shop '" + shop.get("_id") + "':");
            joiner.add("Total goods count: " + shop.get("items_count"));
            joiner.add("Avg goods price: " + shop.get("avg_price") + "₽");
            Document product = (Document) shop.get("cheapest_product");
            joiner.add("Cheapest product: " + product.get("name") + ", " + product.get("cost") + "₽");
            product = (Document) shop.get("pricey_product");
            joiner.add("Pricey product: "  + product.get("name") + ", " + product.get("cost") + "₽");
            joiner.add("Goods with price cheaper 100₽: " + shop.get("cheep_goods"));
            System.out.println(joiner);
        });
    }

    public void quit() {
        mongoClient.close();
    }
}
