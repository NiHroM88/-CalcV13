import java.sql.SQLOutput;
import java.util.Scanner;

public class CalculatorV13 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // сканер ввода в консоль
        System.out.println("Введите два числа(арабских или римских): "); // печатаем в консоль
        String expression = scanner.nextLine(); // принимаем данные из консоли
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]"); // проверка введенного знака
        if (operands.length !=2) throw new Exception("Должно быть два оператора");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Не поддерживаемая математическая операция");
        // два числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])){
            //конвертируем в арабские
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        else if(!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])){
            num1 = Integer.parseInt(operands[0]);// конвертируем в int
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        //если одно число римское а другое арабское
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10 || num2>10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman){
            //если римское число получилось меньше либо равным нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("Римское чило должно быть больше нуля");

            }
            //конвертируем результакт из арабское в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //конверт арабское число в строку
            result = String.valueOf(arabian);
        }
        //возврат результата
        return result;
    }
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+"; // после проверки если содержит знак плюс, ставим плюс и т.д
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper){
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a-b;
        else if (oper.equals("*")) return a*b;
        else return a/b;
    }

}
class Roman {
    static String[] romanArray = new String[]{"0","I","II","III","IV","V","VI","VII","VIII","IX","X", "XI", "XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX"}; // массив на римскеие цифры

    public static boolean isRoman(String val) {
        for (int i=0; i<romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
            return true;
            }
        }
        return false;
    }
    public static int convertToArabian(String roman){
        for (int i=0; i<romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;

    }
    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}