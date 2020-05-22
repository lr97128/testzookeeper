package testzookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class main1 {

	public static void main(String[] args) throws Exception {
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper("10.223.40.70:2181", 30000, 
					new MyWatcher(), false);
		}catch (Exception e) {
			e.getStackTrace();
		}
		while (true) {
			if (zk.exists("/stringhandlers", new MyWatcher()) == null ) {
				System.out.println("没有该父节点，正在创建");
				zk.create("/stringhandlers", null, 
						Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				System.out.println("创建父节点完毕");
			} else {
				System.out.println("已经存在该父节点");
			}
			if (zk.exists("/stringhandlers/10.222.0.86:8080", new MyWatcher()) == null) {
				System.out.println("不存在子节1点，正在创建子节点1");
				zk.create("/stringhandlers/10.222.0.86:8080", null, 
						Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
				System.out.println("添加子节点成功1");
			} else {
				System.out.println("存在子节点1，不需要创建1");
			}
			if (zk.exists("/stringhandlers/10.222.0.68:8080", new MyWatcher()) == null) {
				System.out.println("不存在子节点2，正在创建子节点2");
				zk.create("/stringhandlers/10.222.0.68:8080", null, 
						Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
				System.out.println("添加子节点成功2");
			} else {
				System.out.println("存在子节点2，不需要创建2");
			}
			Thread.sleep(10000);
		}
//			zk.close(); 不能close，否则子节点添加不成功
	}
}