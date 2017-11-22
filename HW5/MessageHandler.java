class MessageHandler {

    public MessageHandler() {
    }

    public void terminalLaunching(){
        System.out.println("Good day!");
        System.out.println("Please enter your card number:");
    }

    public void invalidPin(int attempt){
        System.out.println("Invalid pin! Attempts left: " + attempt);
    }

    public void pinRequest(){
        System.out.println("Please enter pin-code: ");
    }

    public void sumRequest(){
        System.out.println("Please enter the sum: ");
    }

    public void currentBalanceRequest(int balance){
        System.out.println("Your current balance is " + balance+ ".");
    }

    public void succesfullOperation(){
        System.out.print("Succesful! ");
    }

    public void invalidOperation(){
        System.out.println("Invalid operation, try again.");
    }

    public void startInteraction(){
        System.out.println();
        System.out.println("Choose operation to proceed: 1. check balance 2. deposit money 3. withdraw money 4. Exit");
    }

    public void endInteraction(){
        System.out.println("Please take your card. Good bye.");
    }



}



