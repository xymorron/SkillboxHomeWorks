import java.util.*;
import java.util.concurrent.RecursiveTask;

public class UrlParseTask extends RecursiveTask<Set<Url>> {

    private Url url;

    public UrlParseTask(Url url) {
        this.url = url;
    }

    @Override
    protected Set<Url> compute() {
        HashSet<Url> children = new HashSet<>();
        children.add(url);
        ArrayList<UrlParseTask> taskList = new ArrayList<>();
        for (Url child: url.getChildren()) {
            if (child.isVisited())
                continue;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UrlParseTask task = new UrlParseTask(child);
            task.fork();
            taskList.add(task);
        }
        for (UrlParseTask task: taskList)
            children.addAll(task.join());
        return children;
    }
}
