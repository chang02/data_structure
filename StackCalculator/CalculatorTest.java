import java.io.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.regex.*;

public class CalculatorTest
{
	static boolean error = false;
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			error = false;
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
		String regex = ".*[0-9]+[\t ]+[0-9]+.*";
		if(input.matches(regex)){
			error = true;
		}
		input = input.replaceAll(" ","");
		input = input.replaceAll("\t", "");
		if(input.compareTo("") == 0){
			System.out.println("ERROR");
		}
		else{
			ArrayList<String> parse_input = parse(input);
			ArrayList<String> post = infixToPostfix(parse_input);
			long result = calculate(post);
			
			if(error)
				System.out.println("ERROR");
			else{
				String post_result=post.get(0);
				for(int i=1;i<post.size();i++){
					post_result = post_result + " " + post.get(i);
				}
				System.out.println(post_result);
				System.out.println(result);
			}
		}
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
	
	static ArrayList<String> infixToPostfix(ArrayList<String> infix)
	{
		Stack<String> s = new Stack<>();
		ArrayList<String> l = new ArrayList<>();
		String pre ="+";
		for(int i=0;i<infix.size();i++) {
			int c = check(pre, infix.get(i));
			if(c == -1) {
				error = true;
				return l;
			}
			else if(c == 1) {
				l.add(infix.get(i));
			}
			else if(c == 2) {
				while(!s.empty() && s.peek().compareTo("(") != 0) {
					l.add(s.pop());
				}
				if(s.empty()) {
					error = true;
					return l;
				}
				else {
					s.pop();
				}
			}
			else if(c == 3){
				if(s.empty()){
					s.push(infix.get(i));
				}
				else{
					while(!s.empty() && c > check(" ",s.peek())){
						l.add(s.pop());
					}
					s.push(infix.get(i));
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
					while(!s.empty() && c >= check("1",s.peek())) {
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
		return l;
	}
	static long calculate(ArrayList<String> post)
	{
		Stack<Long> s = new Stack<>();
		for(int i=0;i<post.size();i++){
			if(isNum(post.get(i))){
				s.push(Long.parseLong(post.get(i)));
			}
			else if(post.get(i).compareTo("~") == 0){
				if(s.empty()){
					error = true;
				}
				else{
					long tmp = s.pop();
					tmp = tmp * (-1);
					s.push(tmp);
				}
			}
			else{
				if(s.size() < 2){
					error = true;
				}
				else{
					long tmp1 = s.pop();
					long tmp2 = s.pop();
					if(post.get(i).compareTo("+") == 0){
						s.push(tmp2+tmp1);
					}
					else if(post.get(i).compareTo("-") == 0){
						s.push(tmp2-tmp1);
					}
					else if(post.get(i).compareTo("*") == 0){
						s.push(tmp2*tmp1);
					}
					else if(post.get(i).compareTo("/") == 0){
						if(tmp1 == 0){
							error = true;
							return 0;
						}
						s.push(tmp2/tmp1);
					}
					else if(post.get(i).compareTo("%") == 0){
						if(tmp1 == 0){
							error = true;
							return 0;
						}
						s.push(tmp2%tmp1);
					}
					else if(post.get(i).compareTo("^") == 0){
						s.push((long)Math.pow(tmp2, tmp1));
					}
				}
			}
		}
		if(s.empty()){
			error = true;
			return 0;
		}
		long result = s.pop();
		if(s.empty()){
			return result;
		}
		else{
			error = true;
			return 0;
		}
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
