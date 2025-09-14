package com.xushu.transaction;

import org.springframework.transaction.TransactionExecution;
import org.springframework.transaction.TransactionExecutionListener;

public class MyTransactionExecutionListener implements TransactionExecutionListener {

	@Override
	public void beforeBegin(TransactionExecution transaction) {
		System.out.println(transaction.getTransactionName()+"事务开启前");
	}

	@Override
	public void afterBegin(TransactionExecution transaction, Throwable beginFailure) {
		System.out.println(transaction.getTransactionName()+"事务开启后");
	}

	@Override
	public void beforeCommit(TransactionExecution transaction) {
		System.out.println(transaction.getTransactionName()+"事务提交前");
	}

	@Override
	public void afterCommit(TransactionExecution transaction, Throwable commitFailure) {
		System.out.println(transaction.getTransactionName()+"事务提交后");
	}

	@Override
	public void beforeRollback(TransactionExecution transaction) {
		System.out.println(transaction.getTransactionName()+"事务回滚前");
	}

	@Override
	public void afterRollback(TransactionExecution transaction, Throwable rollbackFailure)
	{
		System.out.println(transaction.getTransactionName()+"事务回滚后");
	}
}
