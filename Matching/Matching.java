import java.io.*;


public class Matching
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		HashTable h = new HashTable();
		System.out.println("asdf");
//		if(input.charAt(0) == '<') {
//			String filename = input.split(" ")[1];
//			load_file(filename);
//		}
	}
	private static void load_file(String filename){
		
	}
}
