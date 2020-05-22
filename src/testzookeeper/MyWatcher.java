package testzookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class MyWatcher implements Watcher{

	@Override
	public void process(WatchedEvent event) {
		System.out.println("hello zookeeper");
        System.out.println(String.format("hello event! type=%s, stat=%s, path=%s",
        		event.getType(),event.getState(),event.getPath()));
	}

}
