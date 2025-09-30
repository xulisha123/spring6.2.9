package com.xushu.one.service.tran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//@Service
public class UserService2 {


	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TransactionTemplate transactionTemplate;

	private final ExecutorService executorService = Executors.newFixedThreadPool(2);


	// 主线程事务方法
	public void mainThread() throws Exception {

		transactionTemplate.execute(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction(TransactionStatus status) {
				try {

					// 获取当前线程的数据库连接
					ConnectionHolder connectionHolder = (ConnectionHolder)
							TransactionSynchronizationManager.getResource(dataSource);

					// 主事务的数据库操作
					jdbcTemplate.execute("INSERT INTO `test`.`user` (`age`, `name`, `city`) VALUES (18, 'xushu', 'THREAD1');");

					// 启动子线程并执行子事务
					Future<Void> future = executorService.submit(() -> {

						// 绑定主线程的连接到这个线程
						TransactionSynchronizationManager.bindResource(dataSource, connectionHolder);
						childThread();
						return null;
					});

					future.get();
				} catch (Exception e) {
					// 回滚主事务
					status.setRollbackOnly();
					throw new RuntimeException(e);
				}
				return null;
			}
		});
	}

	// 子线程事务方法
	private void childThread() {
		// 子事务的数据库操作
		transactionTemplate.executeWithoutResult(status -> {
			try {

				jdbcTemplate.execute("INSERT INTO `test`.`user` (`age`, `name`, `city`) VALUES (66, 'xushu666', 'THREAD2');");

				throw  new Exception("子事务抛出异常");
			}
			catch (Exception e) {
				// 回滚主事务
				status.setRollbackOnly();
				throw new RuntimeException(e);
			}

		});
	}
}  