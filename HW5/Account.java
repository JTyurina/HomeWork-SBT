
    class Account {
        private String accountId;
        private int balance;
        private String accountHolder;
        private String pin;

        public int getBalance() {
            return balance;
        }

        public Account(String accountId, int balance, String accountHolder, String pin) {
            this.accountId = accountId;
            this.balance = balance;
            this.accountHolder = accountHolder;
            this.pin = pin;
        }

        public void setBalance(int sum) {
            this.balance = sum;
        }


        public String getPin() {
            return pin;
        }


        public String getAccountId() {
            return accountId;
        }


    }


