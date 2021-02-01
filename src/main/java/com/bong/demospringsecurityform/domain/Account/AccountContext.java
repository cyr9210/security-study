package com.bong.demospringsecurityform.domain.Account;

public class AccountContext {

  private static final ThreadLocal<Account> ACCOUNT_THREAD_LOCAL
      = new ThreadLocal<>();

  public static void setAccount(Account account) {
    ACCOUNT_THREAD_LOCAL.set(account);
  }

  public static Account getAccount(Account account) {
    return ACCOUNT_THREAD_LOCAL.get();
  }

}
