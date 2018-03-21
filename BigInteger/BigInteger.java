import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BigInteger
{
   public static final String QUIT_COMMAND = "quit";
   public static final String MSG_INVALID_INPUT = "invalid input";

    // implement this
   public static final Pattern EXPRESSION_PATTERN = Pattern.compile("");
   char sign = ' ';
   char[] num = new char[210];
   public BigInteger()
   {

   }
   public char[] array_reverse(char[] a){
      int last_index = -1;
      for(int i=a.length-1;i>=0;i--){
         if(a[i] != '#'){
            last_index = i;
            break;
         }
      }
      for(int i=0;i<(last_index+1)/2;i++){
         char temp = a[i];
         a[i] = a[last_index-i];
         a[last_index-i] = temp;
      }
      return a;
   }
   public int compare_reversedarr(char[] a, char[] b){
      int index1=-1;
      int index2=-1;
      for(int i=a.length-1;i>=0;i--){
         if(a[i] != '#'){
            index1 = i;
            break;
         }
      }
      for(int i=b.length-1;i>=0;i--){
         if(b[i] != '#'){
            index2 = i;
            break;
         }
      }
      if(index1 > index2){
         return 1;
      }
      else if (index1 < index2){
         return 2;
      }
      else{
         for(int i=index1;i>=0;i--){
            if(a[i] > b[i])
               return 1;
            else if(a[i] < b[i])
               return 2;
         }
         return 0;
      }
   }
   public BigInteger add(BigInteger big)
   {
      int i=0;
      char[] num1 = this.num;
      char[] num2 = big.num;
      char[] result = new char[210];
      for(i=0;i<result.length;i++){
         result[i] = '#';
      }
      BigInteger big_result = new BigInteger();
      num1 = array_reverse(num1);
      num2 = array_reverse(num2);
      int carry = 0;
      for(i=0;i<num1.length;i++){
         if(Character.isDigit(num1[i]) && Character.isDigit(num2[i])){
            result[i] = (char)((((num1[i]-'0') + (num2[i]-'0') + carry) % 10)+'0');
            carry = (int)(((num1[i]-'0') + (num2[i]-'0') + carry) / 10);
         }
         else if(Character.isDigit(num1[i]) && !Character.isDigit(num2[i])){
            result[i] = (char)((((num1[i]-'0') + carry) % 10)+'0');
            carry = (int)(((num1[i]-'0') + carry) / 10);
         }
         else if(!Character.isDigit(num1[i]) && Character.isDigit(num2[i])){
            result[i] = (char)((((num2[i]-'0') + carry) % 10)+'0');
            carry = (int)(((num2[i]-'0') + carry) / 10);
         }
         else if(!Character.isDigit(num1[i]) && !Character.isDigit(num2[i]) && carry != 0){
            result[i] = (char)(carry+'0');
            carry = 0;
         }
         else{
            break;
         }
      }
      result = array_reverse(result);
      big_result.num = result;
      big_result.sign = '+';
      return big_result;
   }
   public BigInteger subtract(BigInteger big)
   {
      int i=0;
      int carry=0;
      char[] num1 = this.num;
      char[] num2 = big.num;
      char[] result = new char[210];
      BigInteger big_result = new BigInteger();
      for(i=0;i<result.length;i++){
         result[i] = '#';
      }
      num1 = array_reverse(num1);
      num2 = array_reverse(num2);
      int compare = compare_reversedarr(num1, num2);
      if(compare == 0){
         result[0] = '0';
         big_result.sign = '+';
         big_result.num = result;
      }
      else if(compare == 1){
         for(i=0;i<num1.length;i++){
            if(Character.isDigit(num1[i]) && Character.isDigit(num2[i])){
               if((num1[i]-'0'-carry) >= (num2[i]-'0')){
                  result[i] = (char)(((num1[i]-'0'-carry) - (num2[i]-'0')) + '0');
                  carry = 0;
               }
               else{
                  result[i] = (char)(((num1[i]-'0'-carry+10) - (num2[i]-'0')) + '0');
                  carry = 1;
               }
            }
            else if(Character.isDigit(num1[i]) && !Character.isDigit(num2[i])){
               if((num1[i]-'0'-carry) >= 0){
                  result[i] = (char)((num1[i]-'0'-carry) + '0');
                  carry = 0;
               }
               else{
                  result[i] = (char)((num[i]-'0'-carry+10) + '0');
                  carry = 1;
               }
            }
         }
         result = array_reverse(result);
         big_result.num = result;
         big_result.sign = '+';
      }
      else if(compare ==2){
         for(i=0;i<num1.length;i++){
            if(Character.isDigit(num2[i]) && Character.isDigit(num1[i])){
               if((num2[i]-'0'-carry) >= (num1[i]-'0')){
                  result[i] = (char)(((num2[i]-'0'-carry) - (num1[i]-'0')) + '0');
                  carry = 0;
               }
               else{
                  result[i] = (char)(((num2[i]-'0'-carry+10) - (num1[i]-'0')) + '0');
                  carry = 1;
               }
            }
            else if(Character.isDigit(num2[i]) && !Character.isDigit(num1[i])){
               if((num2[i]-'0'-carry) >= 0){
                  result[i] = (char)((num2[i]-'0'-carry) + '0');
                  carry = 0;
               }
               else{
                  result[i] = (char)((num2[i]-'0'-carry+10) + '0');
                  carry = 1;
               }
            }
         }
         result = array_reverse(result);
         big_result.num = result;
         big_result.sign = '-';
      }
      return big_result;
   }

   public BigInteger multiply(BigInteger big)
   {
      int i=0, j=0;
      int index1=-1, index2=-1;
      int carry=0;
      char[] num1 = this.num;
      char[] num2 = big.num;
      char[] result = new char[210];
      int[] result_carry = new int[210];
      BigInteger big_result = new BigInteger();
      for(i=0;i<result.length;i++){
         result[i] = '#';
      }
      for(i=0;i<result_carry.length;i++){
         result_carry[i] = 0;
      }
      num1 = array_reverse(num1);
      num2 = array_reverse(num2);
      for(i=num1.length-1;i>=0;i--){
         if(num1[i] != '#'){
            index1 = i;
            break;
         }
      }
      for(j=num2.length-1;j>=0;j--){
         if(num2[j] != '#'){
            index2 = j;
            break;
         }
      }
      for(i=0;i<=index1;i++){
         for(j=0;j<=index2;j++){
            if(result[i+j] != '#'){
               result_carry[i+j+1] += (int)(((result[i+j]-'0') + (num1[i]-'0') * (num2[j]-'0')) / 10);
               result[i+j] = (char)((((result[i+j]-'0') + (num1[i]-'0') * (num2[j]-'0')) % 10) + '0');
            }
            else{
               result_carry[i+j+1] += (int)(((num1[i]-'0') * (num2[j]-'0')) / 10);
               result[i+j] = (char)((((num1[i]-'0') * (num2[j]-'0')) % 10) + '0');
            }
         }
      }
      for(i=0;i<result.length;i++){
         if(result[i] == '#' && result_carry[i] == 0)
            break;
         else if(result[i] == '#' && result_carry[i] != 0){
            result_carry[i+1] += (int)(result_carry[i] / 10);
            result[i] = (char)(((result_carry[i]) % 10)+'0');
         }
         else if(result[i] != '#'){
            result_carry[i+1] += (int)(((result[i]-'0') + result_carry[i]) / 10);
            result[i] = (char)((((result[i]-'0') + result_carry[i]) % 10)+'0');
         }
      }
      result = array_reverse(result);
      big_result.num = result;
      big_result.sign = '+';
      return big_result;
   }

   @Override
   public String toString()
   {
      char sign = this.sign;
      char [] num = this.num;
      char [] result_num = new char[210];
      int flag = 0;
      for(int i=0;i<num.length;i++){
         if(flag == 0 && num[i] == '0' && num[i+1] != '#')
            num[i] = '\0';
         else{
            flag = 1;
         }
         if(num[i] == '#')
            break;
         result_num[i] = num[i];
      }
      String num_string = new String(result_num);
      num_string = num_string.replace("\0","");
      if(sign == '+' || num_string.equals("0"))
         return num_string;
      else
         return sign + num_string;
   }

   static BigInteger evaluate(String input) throws IllegalArgumentException
   {
        // implement here
        // parse input
        // using regex is allowed

        // One possible implementation
        // BigInteger num1 = new BigInteger(arg1);
        // BigInteger num2 = new BigInteger(arg2);
        // BigInteger result = num1.add(num2);
        // return result;
      input = input.replace(" ", "");
      char [] input_arr = input.toCharArray();
      char op = ' ';
      int op_index = 0;
      int i;

        //find op start
      for(i=1;i<input_arr.length;i++){
         if(!Character.isDigit(input_arr[i])){
            op = input_arr[i];
            op_index = i;
            break;
         }
      }
        //find op end

        //initialize num1, num2 start
      char [] num1 = new char[210];
      char [] num2 = new char[210];
      for(i=0;i<num1.length;i++){
         num1[i] = '#';
      }
      for(i=0;i<num2.length;i++){
         num2[i] = '#';
      }
        //initialize num1, num2 end

        //split by op_index start
      for(i=0;i<op_index;i++){
         num1[i] = input_arr[i];
      }
      for(i=op_index+1;i<input_arr.length;i++){
         num2[i-op_index-1] = input_arr[i];
      }
        //split by op_index end

      BigInteger big1 = new BigInteger();
      BigInteger big2 = new BigInteger();

      //check if the first vlaue is digit or sign and save sign start
      if(!Character.isDigit(num1[0])){
         big1.sign = num1[0];
         for(i=1;i<num1.length;i++){
            big1.num[i-1] = num1[i];
         }
         big1.num[big1.num.length-1] = '#';
      }
      else{
         big1.sign = '+';
         big1.num = num1;
      }
      if(!Character.isDigit(num2[0])){
         big2.sign = num2[0];
         for(i=1;i<num2.length;i++){
            big2.num[i-1] = num2[i];
         }
         big2.num[big2.num.length-1] = '#';
      }
      else{
         big2.sign = '+';
         big2.num = num2;
      }
      //check if the first vlaue is digit or sign and save sign end

      BigInteger result = new BigInteger();
      if(big1.sign == '+' && op == '+' && big2.sign == '+')
         result = big1.add(big2);
      else if(big1.sign == '+' && op == '+' && big2.sign == '-')
         result = big1.subtract(big2);
      else if(big1.sign == '-' && op == '+' && big2.sign == '+')
         result = big2.subtract(big1);
      else if(big1.sign == '-' && op == '+' && big2.sign == '-'){
         result = big1.add(big2);
         result.sign = '-';
      }

      else if(big1.sign == '+' && op == '-' && big2.sign == '+')
         result = big1.subtract(big2);
      else if(big1.sign == '+' && op == '-' && big2.sign == '-')
         result = big1.add(big2);
      else if(big1.sign == '-' && op == '-' && big2.sign == '+'){
         result = big1.add(big2);
         result.sign = '-';
      }
      else if(big1.sign == '-' && op == '-' && big2.sign == '-')
         result = big2.subtract(big1);

      else if(big1.sign == '+' && op == '*' && big2.sign == '+')
         result = big1.multiply(big2);
      else if(big1.sign == '+' && op == '*' && big2.sign == '-'){
         result = big1.multiply(big2);
         result.sign = '-';
      }
      else if(big1.sign == '-' && op == '*' && big2.sign == '+'){
         result = big1.multiply(big2);
         result.sign = '-';
      }
      else if(big1.sign == '-' && op == '*' && big2.sign == '-')
         result = big1.multiply(big2);
      return result;
   }

   public static void main(String[] args) throws Exception
   {
      try (InputStreamReader isr = new InputStreamReader(System.in))
      {
         try (BufferedReader reader = new BufferedReader(isr))
         {
            boolean done = false;
            while (!done)
            {
               String input = reader.readLine();

               try
               {
                  done = processInput(input);
               }
               catch (IllegalArgumentException e)
               {
                  System.err.println(MSG_INVALID_INPUT);
               }
            }
         }
      }
   }

   static boolean processInput(String input) throws IllegalArgumentException
   {
      boolean quit = isQuitCmd(input);

      if (quit)
      {
         return true;
      }
      else
      {
         BigInteger result = evaluate(input);
         System.out.println(result.toString());

         return false;
      }
   }

   static boolean isQuitCmd(String input)
   {
      return input.equalsIgnoreCase(QUIT_COMMAND);
   }
}