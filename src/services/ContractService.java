package services;

import entities.Contract;
import entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {

    OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months){
        double basicQuota = contract.getTotalValue() / months;
        for (int i = 1; i <= months; i++) {
            double updatatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
            double fullQuota = updatatedQuota + onlinePaymentService.paymentFee(updatatedQuota);
            Date dueDate = addMonths(contract.getDate(), 1);
            contract.getInstallment().add(new Installment(dueDate, fullQuota));

        }
    }

    private Date addMonths(Date date, int N){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, N);
        return calendar.getTime();
    }

}
