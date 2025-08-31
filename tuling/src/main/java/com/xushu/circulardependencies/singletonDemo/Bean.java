package com.xushu.circulardependencies.singletonDemo;

// 2种方式
public class Bean {

	private  static  Bean bean; //=new Bean();		// 饿汉

	// 懒汉
	public  static Bean getBean() {

		// 锁？  临界资源 = 多线竞争资源

		if(bean!=null){
			return bean;
		}

		synchronized (bean) {
			if(bean!=null){
				return bean;
			}

			bean = new Bean();
		}

		return bean;
	}

















	public static void main(String[] args) {
		for (int i=0;i<1000;i++){
			new Thread(()-> {
			/*	try {
					System.out.println(Singleton.getInstance().hashCode());
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}*/
			}).start();
		}
	}

}
