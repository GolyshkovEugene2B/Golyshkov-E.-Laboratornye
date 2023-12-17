import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите выражение: ");
		String expression = scanner.nextLine();
		double result = calculate(expression);
		System.out.printf("Результат: %f%n", result);
	}

	private static double calculate(String expression) {
		char[] chars = expression.toCharArray();
		Double result = new Double(0);
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {
				switch (chars[i]) {
				case '+':
					result += calculatePart(expression, i);
					break;
				case '-':
       result -= calculatePart(expression, i);
break;
case ‘*’:
result *= calculatePart(expression, i);
break;
case ‘/’:
try {
result /= calculatePart(expression, i);
} catch (Exception e) {
System.err.println(“Деление на ноль!”);
}
break;
}
} else {
int number = Integer.parseInt(String.valueOf(chars[i]));
result = result + number;
}
}
return result;
}

private static double calculatePart(String expression, int index) {
	StringBuilder part1 = new StringBuilder();
	StringBuilder part2 = new StringBuilder();

	boolean first = true;       
  for (int i = index - 1; i >= 0; i–) {
part1.append(expression.charAt(i));
first &= Character.isDigit(expression.charAt(i)) || expression.charAt(i) == ‘.’;
}
for (int i = index + 1; i < expression.length(); i++) {
part2.append(expression.charAt(i));
Character.isDigit(expression.charAt(i)) && expression.charAt(i) != ‘.’ && Character.isLetter(expression.charAt(i)));
}

	if (!first) {
		throw new IllegalArgumentException("Invalid input");
	}
	
	try {
		double part1Value = Double.parseDouble(part1.toString());
		double part2Value = Double.parseDouble(part2.toString());

		return part1Value - part2Value;
	} catch (NumberFormatException e) {
		e.printStackTrace();
	}
	
	return 0;
}}
