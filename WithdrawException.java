@SuppressWarnings("SpellCheckingInspection")
class WithdrawException extends Exception {

    private int amountToWithdraw;
    private boolean terminaloutOfCash;
    private boolean notSupportedAmount;
    private boolean balanceExcess;

    public WithdrawException(String message, int amountToWithdraw, boolean terminaloutOfCash, boolean notSupportedAmount, boolean balanceExcess) {
        super(message);
        this.amountToWithdraw = amountToWithdraw;
        this.terminaloutOfCash = terminaloutOfCash;
        this.notSupportedAmount = notSupportedAmount;
        this.balanceExcess = balanceExcess;
    }


    @Override
    public void printStackTrace() {
            System.out.print("Ошибка вывода средств! Операция не выполнена: ");

        if (terminaloutOfCash)
            System.out.println("в терминале недостаточно банкнот для выдачи! Запрошенная суммма: " + amountToWithdraw);

        if (notSupportedAmount)
            System.out.println("терминал работает только с купюрами, кратными 100! Запрошенная сумма: " + amountToWithdraw);

         if (balanceExcess)
            System.out.println("на Вашем счете недостаточно средств! Запрошенная сумма: " + amountToWithdraw);
    }

    }

