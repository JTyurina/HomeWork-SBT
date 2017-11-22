class AccountLockedException extends Exception {
private long secToEnd;


    public AccountLockedException(long secToEnd) {
        this.secToEnd = secToEnd/1000;
    }

    @Override
    public void printStackTrace() {
        System.out.println("Account is locked! Please wait for " + secToEnd  + " seconds.");
    }
}
