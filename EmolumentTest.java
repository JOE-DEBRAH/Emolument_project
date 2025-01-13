package emolument_project;

import javax.swing.JOptionPane;

class Emolument {
    // i. Encapsulated data fields
    private double basic_salary;
    private double tax_relief;

    // ii. Constructor
    public Emolument(double basic_salary, double tax_relief) {
        this.basic_salary = basic_salary;
        this.tax_relief = tax_relief;
    }

    // iii. getBasicSalary method
    public double getBasicSalary() {
        return basic_salary;
    }

    // iv. getTaxRelief method
    public double getTaxRelief() {
        return tax_relief;
    }

    // v. SSNIT method
    public double SSNIT() {
        return 0.035 * basic_salary;
    }

    // vi. taxableIncome method
    public double taxableIncome() {
        return basic_salary - (tax_relief + SSNIT());
    }
}

class MyEmolument extends Emolument {
    // i. Encapsulated data fields (inherited from Emolument)

    // ii. Non-arg constructor
    public MyEmolument() {
        super(0, 0);
    }

    // iii. Constructor with parameters
    public MyEmolument(double basic_salary, double tax_relief) {
        super(basic_salary, tax_relief);
    }

    // iv. incomeTax method
    public double incomeTax() {
        double taxableIncome = taxableIncome();
        double tax = 0;

        if (taxableIncome <= 500) {
            tax = 0.05 * taxableIncome;
        } else if (taxableIncome <= 1000) {
            tax = 0.05 * 500 + 0.125 * (taxableIncome - 500);
        } else {
            tax = 0.05 * 500 + 0.125 * 500 + 0.175 * (taxableIncome - 1000);
        }

        return tax;
    }

    // v. totalDeduction method
    public double totalDeduction() {
        return SSNIT() + incomeTax();
    }

    // vi. netSalary method
    public double netSalary() {
        return getBasicSalary() - totalDeduction();
    }
}

public class EmolumentTest {
    public static void main(String[] args) {
        // i. Accept input from the user
        String basicSalaryInput = JOptionPane.showInputDialog("Enter Basic Salary:");
        String taxReliefInput = JOptionPane.showInputDialog("Enter Tax Relief:");

        double basicSalary = Double.parseDouble(basicSalaryInput);
        double taxRelief = Double.parseDouble(taxReliefInput);
        
        MyEmolument Staff_Salary = new MyEmolument(basicSalary, taxRelief);

        // ii. Display the results
        String result;
        result = String.format("""
        Basic Salary: GH\u20b5 %.2f
        Tax Relief: GH\u20b5 %.2f
        SSNIT Contribution: GH\u20b5 %.2f
        Taxable Income: GH\u20b5 %.2f
        Income Tax: GH\u20b5 %.2f
        Total Deduction: GH\u20b5 %.2f
        Net Salary: GH\u20b5 %.2f""",
                Staff_Salary.getBasicSalary(),
                Staff_Salary.getTaxRelief(),
                Staff_Salary.SSNIT(),
                Staff_Salary.taxableIncome(),
                Staff_Salary.incomeTax(),
                Staff_Salary.totalDeduction(),
                Staff_Salary.netSalary());

        JOptionPane.showMessageDialog(null, result, "Emolument Details", JOptionPane.INFORMATION_MESSAGE);}
};