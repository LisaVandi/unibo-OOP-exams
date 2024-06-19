package a04.e1;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    /**
     * @return the simplest bank account, where you can withdraw money that you have, and without any additional tax/fee
     */
    public BankAccount createBasic() {
        return new BankAccount() {
            private int balance = 0;
            private boolean canWithdraw = true; 

            @Override
            public int getBalance() {
                return this.balance;    
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;    
            }

            @Override
            public boolean withdraw(int amount) {
                if (this.balance >= amount) {
                    this.balance -= amount;
                    return canWithdraw;
                }
                canWithdraw = !canWithdraw;
                return canWithdraw;
            }
            
        };
    }

    @Override
    public BankAccount createWithFee(UnaryOperator<Integer> feeFunction) {
        return new BankAccount() {
            private int balance = 0;
            private boolean canWithdraw = true;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;                
            }

            @Override
            public boolean withdraw(int amount) {
                if (this.balance >= amount ) {
                    this.balance = this.balance - (amount + feeFunction.apply(amount));
                    return canWithdraw;
                }
                canWithdraw = !canWithdraw;
                return canWithdraw;
            }

        };
    }

    @Override
    public BankAccount createWithCredit(Predicate<Integer> allowedCredit, UnaryOperator<Integer> rateFunction) {
        return new BankAccount() {
            private int balance = 0;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (this.balance >= amount) {
                    this.balance -= amount;
                    return true;
                } else {
                    int credit = amount - this.balance; 
                    if (allowedCredit.test(credit)) {
                        this.balance = this.balance - (amount + rateFunction.apply(credit));
                        return true;
                    }    
                }
                return false;
            }
            
        };    
    }

    @Override
    public BankAccount createWithBlock(BiPredicate<Integer, Integer> blockingPolicy) {
        return new BankAccount() {
            private int balance = 0;
            private boolean isBlocked = false;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;                
            }

            @Override
            public boolean withdraw(int amount) {
                if (isBlocked) {
                    return false;
                }
                if (blockingPolicy.test(amount, this.balance)) {
                    isBlocked = true;
                    //canWithdraw = !canWithdraw;
                    return false;
                } 
                if (this.balance >= amount) {
                    this.balance -= amount; 
                    return true;
                }
                return false;
            }
            
        };
    }

    @Override
    public BankAccount createWithFeeAndCredit(UnaryOperator<Integer> feeFunction, Predicate<Integer> allowedCredit,
            UnaryOperator<Integer> rateFunction) {
            return new BankAccount() {

                private int balance = 0;

                @Override
                public int getBalance() {
                    return this.balance;
                }

                @Override
                public void deposit(int amount) {
                    this.balance += amount;
                }

                @Override
                public boolean withdraw(int amount) {
                    int totalAmount = amount + feeFunction.apply(amount);
                    if (this.balance >= totalAmount) {
                        this.balance -= totalAmount;
                        return true;
                    } else {
                        int credit = amount - this.balance; 
                        if (allowedCredit.test(credit)) {
                            this.balance = this.balance - (totalAmount + rateFunction.apply(credit));
                            return true;
                        }    
                    }
                    return false;
                }
                
            };
    }

}
