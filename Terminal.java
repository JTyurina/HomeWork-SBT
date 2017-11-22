interface Terminal {

    int checkCurrentBalance(Account acc);
    boolean depositMoneyIntoAcc(Account acc, int amount);
    boolean withdrawMoney(Account acc, int amount);



}
