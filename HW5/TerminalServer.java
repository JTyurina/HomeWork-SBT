
import java.util.*;


public class TerminalServer implements Terminal {

    private static boolean correctPinEntered;
    private static int currentCashAmount;
    private static boolean accountLocked;
    private static String enteredaccId;
    private static String enteredPin;
    private static int incorrectPinCounter;
    private static Timer timer;
    private static TimerTask timerTask;
    private static MessageHandler mh;
    private static Map<String, Account> accMap = new HashMap<>();

    public static int getCurrentCashAmount() {

        return currentCashAmount;
    }

    public static void setCurrentCashAmount(int currentCashAmount) {
        TerminalServer.currentCashAmount = currentCashAmount;
    }

    public static int getIncorrectPinCounter() {
        return incorrectPinCounter;
    }

    public static void setIncorrectPinCounter(int incorrectPinCounter) throws AccountLockedException {
        if (incorrectPinCounter >= 3) {
            setAccountLocked(true);
            timer.schedule(timerTask, 5000);
            throw new AccountLockedException(5000);
        }
        else TerminalServer.incorrectPinCounter = incorrectPinCounter;
    }

    public static boolean isCorrectPinEntered() {

        return correctPinEntered;
    }

    public static void setCorrectPinEntered(boolean correctPinEntered) {
        TerminalServer.correctPinEntered = correctPinEntered;
    }

    public static void setAccountLocked(boolean accountLocked) {

        TerminalServer.accountLocked = accountLocked;
    }

    public static String getEnteredaccId() {
        return enteredaccId;
    }

    public static String getEnteredPin() {

        return enteredPin;
    }

    public static void setEnteredPin(String enteredPin) {
        TerminalServer.enteredPin = enteredPin;
    }

    public static Map<String, Account> getAccMap() {

        return accMap;
    }

    public static void setAccMap(Map<String, Account> accMap) {
        TerminalServer.accMap = accMap;
    }

    public static void setEnteredaccId(String enteredaccId) {
        TerminalServer.enteredaccId = enteredaccId;

    }

    private static void readAccId() {
        Scanner sc = new Scanner(System.in);
        setEnteredaccId(sc.nextLine());
    }

    private static void getPin() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        setEnteredPin(s.trim());
    }

    private static void createTestAccounts() {
        Account acc1 = new Account("001", 100000, "Linda Goodman", "1234");
        Account acc2 = new Account("002", 143200, "Phlipper", "5465");
        Account acc3 = new Account("003", 70000, "Kate ", "7944");
        Account acc4 = new Account("004", 200000, "Mr. Parkenson", "1234");
        Map<String, Account> startMap = new HashMap<>();
        startMap.put(acc1.getAccountId(), acc1);
        startMap.put(acc2.getAccountId(), acc2);
        startMap.put(acc3.getAccountId(), acc3);
        startMap.put(acc4.getAccountId(), acc4);
        setAccMap(startMap);
    }

    private static boolean pinValidation() {
        Map<String, Account> currAccArr = getAccMap();
        Account acc = currAccArr.get(getEnteredaccId());

        if (acc.getPin().equals(getEnteredPin())) {
            setCorrectPinEntered(true);
            return true;
        } else {
            try {
                setCorrectPinEntered(false);
                setIncorrectPinCounter(getIncorrectPinCounter() + 1);
                mh.invalidPin(3 - getIncorrectPinCounter());
            } catch (AccountLockedException e) {
                e.printStackTrace();
                initializeTimerTask();
            }
            return false;
        }
    }

    private static void initializeTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                setAccountLocked(false);
                try {
                    setIncorrectPinCounter(0);
                } catch (AccountLockedException e) {
                    e.printStackTrace();
                }
                authorization();
            }
        };
    }

    private static void loadTerminalServer() {
        mh = new MessageHandler();
        initializeTimerTask();

        setCorrectPinEntered(false);
        setAccountLocked(false);
        try {
            setIncorrectPinCounter(0);
        } catch (AccountLockedException e) {
            e.printStackTrace();
        }
        setCurrentCashAmount(10000);
        createTestAccounts();
        mh.terminalLaunching();
        readAccId();
    }

    private static void userInteraction() {
        mh.startInteraction();
        int n = 0;
        Scanner sc = new Scanner(System.in);
        TerminalServer ts = new TerminalServer();
        Account currAcc = accMap.get(getEnteredaccId());
        while (n != 4) {
            n = sc.nextInt();
            switch (n) {
                case 1:
                    mh.currentBalanceRequest(ts.checkCurrentBalance(currAcc));
                    mh.startInteraction();
                    break;
                case 2:
                    mh.sumRequest();
                    if (ts.depositMoneyIntoAcc(currAcc, sc.nextInt())) {
                        mh.succesfullOperation();
                        mh.currentBalanceRequest(currAcc.getBalance());
                    }
                    mh.startInteraction();
                    break;
                case 3:
                    mh.sumRequest();
                    if (ts.withdrawMoney(currAcc, sc.nextInt())) {
                        mh.succesfullOperation();
                        mh.currentBalanceRequest(currAcc.getBalance());
                    }
                    mh.startInteraction();
                    break;
                case 4:
                    mh.endInteraction();
                    break;
                default:
                    mh.invalidOperation();
                    mh.startInteraction();
                    break;
            }
        }


    }

    private static void authorization() {

        while (!correctPinEntered && !accountLocked) {
            mh.pinRequest();
            getPin();
            pinValidation();
        }

        if (isCorrectPinEntered()) userInteraction();
    }

    public static void main(String[] args) {
        loadTerminalServer();
        authorization();
    }

    @Override
    public int checkCurrentBalance(Account acc) {
        return acc.getBalance();
    }

    @Override
    public boolean depositMoneyIntoAcc(Account acc, int amount) {

        try {
            if ((amount < 100) || (amount > 100 && amount % 100 != 0)) {
                throw new WithdrawException("Error of withdraw", amount, false, true, false);
            } else {
                acc.setBalance(acc.getBalance() + amount);
                setCurrentCashAmount(getCurrentCashAmount() + amount);
                return true;
            }

        } catch (WithdrawException e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean withdrawMoney(Account acc, int amount) {
        try {
            if (getCurrentCashAmount() < amount)
                throw new WithdrawException("Error of withdraw", amount, true, false, false);

            if ((amount < 100) || (amount > 100 && amount % 100 != 0))
                throw new WithdrawException("Error of withdraw", amount, false, true, false);

            if (acc.getBalance() < amount)
                throw new WithdrawException("Error of withdraw", amount, false, false, true);


            acc.setBalance(acc.getBalance() - amount);
            setCurrentCashAmount(getCurrentCashAmount() - amount);
            return true;
        } catch (WithdrawException e) {
            e.printStackTrace();
            return false;
        }


    }

}
