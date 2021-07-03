import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.OnlinePaymentService;
import services.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class Application {


    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contract data: ");
        System.out.println("Numer: ");
        Integer number = sc.nextInt();
        System.out.println("Date: ");
        Date date = sdf.parse(sc.next());
        System.out.println("Contract value: ");
        Double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.println("Enter number of intallments: ");
        int N = sc.nextInt();

        ContractService cs = new ContractService(new PaypalService());

        cs.processContract(contract, N);

        System.out.println("Intallments: ");
        for (Installment it : contract.getInstallment()) {
            System.out.println(it);
            
        }


        sc.close();
    }
}
