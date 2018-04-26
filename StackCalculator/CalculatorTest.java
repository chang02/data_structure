import java.io.*;
import java.util.Stack;
import java.util.ArrayList;

public class CalculatorTest
{
	boolean error = false;
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
				System.out.println("�엯�젰�씠 �옒紐삳릺�뿀�뒿�땲�떎. �삤瑜� : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		input = input.replaceAll(" ","");
		input = input.replaceAll("\t", "");
		ArrayList<String> parse_input = new ArrayList<>();
		parse_input = parse(input);
		String post;
		post = infixToPostfix(parse_input);
		
		System.out.println(post);
		
		int result = calculate(post);
		System.out.println(result);
	}
	
	static ArrayList<String> parse(String input){
		ArrayList<String> parse_input = new ArrayList<>();
		for(int i=0;i<input.length();i++) {
			if(Character.isDigit(input.charAt(i))) {
				String tmp = ""+input.charAt(i);
				while(i+1<input.length() && Character.isDigit(input.charAt(i+1))) {
					i++;
					tmp = tmp+input.charAt(i);
				}
				parse_input.add(tmp);
			}
			else {
				parse_input.add(Character.toString(input.charAt(i)));
			}
		}
		return parse_input;
	}
	
	static String infixToPostfix(ArrayList<String> infix)
	{
		Stack<String> s = new Stack<>();
		ArrayList<String> l = new ArrayList<>();
		String pre ="+";
		for(int i=0;i<infix.size();i++) {
			int c = check(pre, infix.get(i));
			if(c == -1) {
				System.out.println("ERROR");
			}
			else if(c == 1) {
				l.add(infix.get(i));
			}
			else if(c == 2) {
				while(!s.empty() && s.peek().compareTo("(") != 0) {
					l.add(s.pop());
				}
				if(s.empty()) {
					System.out.println("ERROR");
				}
				else {
					s.pop();
				}
			}
			else if(c == 4) {
				if(s.empty()) {
					s.push("~");
				}
				else {
					while(!s.empty() && c > check(" ",s.peek())) {
						l.add(s.pop());
					}
					s.push("~");
				}
			}
			else if(c == 7) {
				s.push(infix.get(i));
			}
			else {
				if(s.empty()) {
					s.push(infix.get(i));
				}
				else {
					while(!s.empty() && c >= check(" ",s.peek())) {
						l.add(s.pop());
					}
					s.push(infix.get(i));
				}
			}
			pre = infix.get(i);
		}
		while(!s.empty()) {
			l.add(s.pop());
		}
		String post = ""+l.get(0);
		for(int i=1;i<l.size();i++) {
			post = post + " " + l.get(i);
		}
		return post;
	}
	static int calculate(String post)
	{
		return 0;
	}
	static int check(String pre, String now) {
		if(isNum(now)) {
			return 1;
		}
		else if(now.compareTo(")") == 0) {
			return 2;
		}
		else if(now.compareTo("^") == 0) {
			return 3;
		}
		else if(!isNum(pre) && pre.compareTo(")") != 0 && now.compareTo("-") == 0) {
			return 4;
		}
		else if(now.compareTo("~") == 0) {
			return 4;
		}
		else if(now.compareTo("*") == 0 || now.compareTo("/") == 0 || now.compareTo("%") == 0) {
			return 5;
		}
		else if(now.compareTo("+") == 0 || now.compareTo("-") == 0) {
			return 6;
		}
		else if(now.compareTo("(") == 0) {
			return 7;
		}
		else {
			return -1;
		}
	}
	static boolean isNum(String a) {
		boolean flag = true;
		for(int i=0;i<a.length();i++){
			if(!Character.isDigit(a.charAt(i))) {
				flag = false;
			}
		}
		return flag;
	}
}
