package program;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import entities.Funcionario;
import utils.enums.Role;

public class Principal {
	public static void insertEmployees(ArrayList<Funcionario> employeesList) {
		employeesList.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), BigDecimal.valueOf(2009.44), Role.OPERADOR.getValor()));
		employeesList.add(new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), BigDecimal.valueOf(2284.38), Role.OPERADOR.getValor()));
		employeesList.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), BigDecimal.valueOf(9836.14), Role.COORDENADOR.getValor()));
		employeesList.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), BigDecimal.valueOf(19119.88), Role.DIRETOR.getValor()));
		employeesList.add(new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), BigDecimal.valueOf(2234.68), Role.RECEPCIONISTA.getValor()));
		employeesList.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), BigDecimal.valueOf(1582.72), Role.OPERADOR.getValor()));
		employeesList.add(new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), BigDecimal.valueOf(4071.84), Role.CONTADOR.getValor()));
		employeesList.add(new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), BigDecimal.valueOf(3017.45), Role.GERENTE.getValor()));
		employeesList.add(new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24), BigDecimal.valueOf(1606.85), Role.ELETRICISTA.getValor()));
		employeesList.add(new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), BigDecimal.valueOf(2799.93), Role.GERENTE.getValor()));
	}

	public static void removeEmployeeByName(ArrayList<Funcionario> employeesList, String name) {
		employeesList.removeIf(f -> f.getNome().equals(name));
	}
	
	public static void printEmployees(ArrayList<Funcionario> employeesList) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        
        System.out.println("Lista de todos os funcionários");
        
        for (Funcionario employee : employeesList) {
        	String name = employee.getNome();
        	String date = dtf.format(employee.getDataNascimento());
        	String salary = df.format(employee.getSalario());
        	String role = employee.getFuncao();

            System.out.println("Nome: " + name + ", Data de nascimento: " + date + ", Salário: R$ " + salary + ", Função: " + role);
        }
	}
	
	public static void increaseSalary(ArrayList<Funcionario> employeesList, BigDecimal percentage) {
		employeesList.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100))))));
	}
	
	public static void grouppingEmployeeByRole(Map<String, ArrayList<Funcionario>> employeeByRole, ArrayList<Funcionario> employeesList) {
        for (Funcionario employee : employeesList) {
            String funcao = employee.getFuncao();
            if (!employeeByRole.containsKey(funcao)) {
            	employeeByRole.put(funcao, new ArrayList<>());
            }
            employeeByRole.get(funcao).add(employee);
        }
	}
	
	public static void printEmployeeByRole(Map<String, ArrayList<Funcionario>> employeeByRole) {
        System.out.println("Funcionários por função:");
        for (String role : employeeByRole.keySet()) {
            System.out.println("- " + role + ":");
            for (Funcionario e : employeeByRole.get(role)) {
                System.out.println("° " + e.getNome());
            }
        }
	}
	
	public static void printEmployeeByBirthdayMonth(ArrayList<Funcionario> employeesList) {
		System.out.println("Aniversariantes de outubro ou dezembro:");

		for (Funcionario employee : employeesList) {
		    int mesAniversario = employee.getDataNascimento().getMonthValue();
		    if (mesAniversario == 10 || mesAniversario == 12) {
		        System.out.println(employee);
		    }
		}
	}
	
	public static void printOlderEmployee(ArrayList<Funcionario> employeesList) {
		Funcionario older = null;
		int biggerAge = 0;

		for (Funcionario e : employeesList) {
		    int age = Period.between(e.getDataNascimento(), LocalDate.now()).getYears();
		    if (age > biggerAge) {
		    	biggerAge = age;
		        older = e;
		    }
		}

		System.out.printf("Funcionário mais velho: %s, %d anos\n", older.getNome(), biggerAge);
	}
	
	public static void printEmployeesSortedByName(ArrayList<Funcionario> employeesList) {
		Collections.sort(employeesList, Comparator.comparing(Funcionario::getNome));

		System.out.println("Funcionários em ordem alfabética:");
		for (Funcionario e : employeesList) {
		    System.out.println(e);
		}	
	}
	
	public static void printSumOfEmployeesSalary(ArrayList<Funcionario> employeesList) {
		BigDecimal sumSalary = BigDecimal.ZERO;
		for (Funcionario e : employeesList) {
			sumSalary = sumSalary.add(e.getSalario());
		}

		System.out.printf("Soma dos salários: R$%,.2f\n", sumSalary);
	}
	
	public static void printMinSalaryByEmployee(ArrayList<Funcionario> employeesList) {
		BigDecimal minSalary = new BigDecimal("1212.00");

		System.out.println("Salários em salários mínimos:");
		for (Funcionario e : employeesList) {
		    BigDecimal numSalariosMinimos = e.getSalario().divide(minSalary, 2, RoundingMode.DOWN);
		    System.out.printf("%s: recebe  %.2f salários mínimos\n", e.getNome(), numSalariosMinimos);
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Funcionario> employeesList = new ArrayList<>();

		insertEmployees(employeesList);
		
		removeEmployeeByName(employeesList, "João");
		
		printEmployees(employeesList);
		
		increaseSalary(employeesList, new BigDecimal("10"));
		
        	Map<String, ArrayList<Funcionario>> employeeByRole = new HashMap<>();
        
        	grouppingEmployeeByRole(employeeByRole, employeesList);

        	System.out.println();

        	printEmployeeByRole(employeeByRole);
        
        	System.out.println();
        
        	printEmployeeByBirthdayMonth(employeesList);
        
        	System.out.println();
        
        	printOlderEmployee(employeesList);
        
        	System.out.println();
        
        	printEmployeesSortedByName(employeesList);
        
        	System.out.println();
        
        	printSumOfEmployeesSalary(employeesList);

        	System.out.println();
        
        	printMinSalaryByEmployee(employeesList);
	}
}
