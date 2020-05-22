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
				System.out.println("û�иø��ڵ㣬���ڴ���");
				zk.create("/stringhandlers", null, 
						Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				System.out.println("�������ڵ����");
			} else {
				System.out.println("�Ѿ����ڸø��ڵ�");
			}
			if (zk.exists("/stringhandlers/10.222.0.86:8080", new MyWatcher()) == null) {
				System.out.println("�������ӽ�1�㣬���ڴ����ӽڵ�1");
				zk.create("/stringhandlers/10.222.0.86:8080", null, 
						Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
				System.out.println("����ӽڵ�ɹ�1");
			} else {
				System.out.println("�����ӽڵ�1������Ҫ����1");
			}
			if (zk.exists("/stringhandlers/10.222.0.68:8080", new MyWatcher()) == null) {
				System.out.println("�������ӽڵ�2�����ڴ����ӽڵ�2");
				zk.create("/stringhandlers/10.222.0.68:8080", null, 
						Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
				System.out.println("����ӽڵ�ɹ�2");
			} else {
				System.out.println("�����ӽڵ�2������Ҫ����2");
			}
			Thread.sleep(10000);
		}
//			zk.close(); ����close�������ӽڵ���Ӳ��ɹ�
	}
}