import java.io.*;
import java.util.Stack;
import java.util.ArrayList;

public class CalculatorTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		input = input.replaceAll(" ","");
		input = input.replaceAll("\t", "");
		String post;
		post = infixToPostfix(input);
		
		System.out.println(post);
		
		int result = calculate(post);
	}
	
	static String infixToPostfix(String infix)
	{
		char[] infix_arr = infix.toCharArray();
		Stack<Character> s = new Stack<>();
		ArrayList<Integer> l = new ArrayList<>();
		return "postfix";
	}
	static int calculate(String post)
	{
		return 0;
	}
}
