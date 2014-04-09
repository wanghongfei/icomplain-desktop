package cn.neo.icomplain;

import cn.neo.icomplain.entity.ComplainItem;
import cn.neo.icomplain.util.DbManager;

/**
 * The program main class.
 * @author whf
 *
 */
public class App {
	public static void main(String[] args) {
		ComplainItem item = new ComplainItem();
		item.setNickName("Neo");

		DbManager.persist(item);
	}
}
