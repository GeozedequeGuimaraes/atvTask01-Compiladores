import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class RPN {
    private Stack<Integer> pilha;

    public RPN(){
            pilha = new Stack<Integer>();
            }

    public static void main(String[] args) throws Exception{
        String expression;
        int result;
        RPN operador = new RPN();
        expression = "";
        
        FileReader fr = new FileReader("/Users/ggs/Dropbox/Mac/Downloads/Task01//Calc1.stk");
        try (BufferedReader lerArq = new BufferedReader(fr)) {
            String line = lerArq.readLine();
            while (line != null) {
                expression += line + " ";
                line = lerArq.readLine(); 
              }
        }
        result = operador.test(expression);
        System.out.println(result);
   }

	public int test(String expr){
            int portion1, portion2, resultTest = 0;
            String token;
            try (Scanner in = new Scanner(expr)) {
                while (in.hasNext())        
                {
                    token = in.next();          

                    if (isOperador(token))
                    {
                        portion1 = (pilha.pop()).intValue();
                        portion2 = (pilha.pop()).intValue();
                        resultTest = check(token.charAt(0), portion1, portion2);
                        pilha.push(resultTest);
                    }
                    else
                    	pilha.push(Integer.parseInt(token));       
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return resultTest;
        }

     private boolean isOperador(String token){
            return ( token.equals("+") || token.equals("-") ||
                     token.equals("*") || token.equals("/") || token.equals("%") );
        }

     private int check(char operation, int portion1, int portion2){
            int result = 0;

            switch (operation)
            {
                case '+':
                    result = portion1 + portion2;
                    break;
                case '-':
                    result = portion1 - portion2;
                    break;
                case '*':
                    result = portion1 * portion2;
                    break;
                case '/':
                    result = portion1 / portion2;
                    break;
                case '%':
                    result = portion1 % portion2;
                    break;
            }
            return result;
        }
}