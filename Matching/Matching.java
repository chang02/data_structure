import java.io.*;
import java.util.ArrayList;


public class Matching
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashTable h = new HashTable();
		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;
				if(input.charAt(0) == '<') {
					h = new HashTable();
				}

				command(h, input);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(HashTable h, String input) throws IOException
	{
		if(input.charAt(0) == '<') {
			String filepath = input.split(" ")[1];
			load_file(h, filepath);
		}
		else if(input.charAt(0) == '@') {
			int index = Integer.parseInt(input.split(" ")[1]);
			h.print_index(index);
		}
		else if(input.charAt(0) == '?') {
			String s = input.substring(2);
			h.search(s);
		}
	}
	private static void load_file(HashTable h, String filepath) throws IOException{
		
		ArrayList<String> str = new ArrayList<String>();
		BufferedReader input = new BufferedReader(new FileReader(filepath));
		String s;
		while ((s = input.readLine()) != null) {
			str.add(s);
		}
		
		for(int i=0;i<str.size();i++) {			
			char[] temp_chr = str.get(i).toCharArray();
			for(int j=0;j<temp_chr.length-6+1;j++) {
				String temp_str = new String(temp_chr, j, 6);
				Pair temp_p = new Pair(i+1,j+1);
				h.insert(temp_str, temp_p);
			}
		}
	}
}
