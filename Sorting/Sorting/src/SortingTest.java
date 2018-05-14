import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// �Է¹��� �迭�� �����ΰ� �ƴѰ�?
			int[] value;	// �Է� ���� ���ڵ��� �迭
			String nums = br.readLine();	// ù ���� �Է� ����
			if (nums.charAt(0) == 'r')
			{
				// ������ ���
				isRandom = true;	// �������� ǥ��

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// �� ����
				int rminimum = Integer.parseInt(nums_arg[2]);	// �ּҰ�
				int rmaximum = Integer.parseInt(nums_arg[3]);	// �ִ밪

				Random rand = new Random();	// ���� �ν��Ͻ��� �����Ѵ�.

				value = new int[numsize];	// �迭�� �����Ѵ�.
				for (int i = 0; i < value.length; i++)	// ������ �迭�� ������ �����Ͽ� ����
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// ������ �ƴ� ���
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// �迭�� �����Ѵ�.
				for (int i = 0; i < value.length; i++)	// ���پ� �Է¹޾� �迭���ҷ� ����
					value[i] = Integer.parseInt(br.readLine());
			}

			// ���� �Է��� �� �޾����Ƿ� ���� ����� �޾� �׿� �´� ������ �����Ѵ�.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// ���� ���� ��ȣ�� ���� ���纻�� �����Ѵ�.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// ���α׷��� �����Ѵ�.
					default:
						throw new IOException("�߸��� ���� ����� �Է��߽��ϴ�.");
				}
				if (isRandom)
				{
					// ������ ��� ����ð��� ����Ѵ�.
					System.out.println((System.currentTimeMillis() - t) + " ms");
					
					//for test
//					for (int i = 0; i < newvalue.length; i++)
//					{
//						System.out.println(newvalue[i]);
//					}
				}
				else
				{
					// ������ �ƴ� ��� ���ĵ� ������� ����Ѵ�.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("�Է��� �߸��Ǿ����ϴ�. ���� : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
	{
		//copy from https://ko.wikipedia.org/wiki/%EA%B1%B0%ED%92%88_%EC%A0%95%EB%A0%AC
		int temp=0;
		for(int i=0;i<value.length;i++){
			for(int j=1;j<value.length-i;j++){
				if(value[j]<value[j-1]){
					temp = value[j-1];
					value[j-1] = value[j];
					value[j] = temp;
				}
			}
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] value)
	{
		//copy from https://ko.wikipedia.org/wiki/%EC%82%BD%EC%9E%85_%EC%A0%95%EB%A0%AC
		for(int i=1;i<value.length;i++){
			int temp = value[i];
			int j = i - 1;
			while((j >= 0) && (value[j] > temp)){
				value[j+1] = value[j];
				j--;
			}
			value[j+1] = temp;
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoHeapSort(int[] value)
	{
		//implemented by myself
		int[] heap = new int[value.length];
		for(int i=0;i<value.length;i++){
			int index_copy = i;
			heap[index_copy] = value[i];
			while(index_copy != 0){
				if(heap[index_copy] < heap[(index_copy-1)/2]){
					int temp = heap[index_copy];
					heap[index_copy] = heap[(index_copy-1)/2];
					heap[(index_copy-1)/2] = temp;
				}
				else{
					break;
				}
				index_copy = (index_copy-1)/2;
			}
		}
		int heapsize = heap.length;
		for(int i=0;i<value.length;i++){
			value[i] = heap[0];
			heap[0] = heap[heapsize-1];
			heapsize = heapsize-1;
			int index_copy = 0;
			while(true){
				if(index_copy*2+2 > heapsize-1){
					if(index_copy*2+1 > heapsize-1){
						break;
					}
					else{
						if(heap[index_copy*2+1]<heap[index_copy]){
							int temp = heap[index_copy*2+1];
							heap[index_copy*2+1] = heap[index_copy];
							heap[index_copy] = temp;
							index_copy = index_copy*2+1;
						}
						else{
							break;
						}
					}
				}
				else{
					if(heap[index_copy*2+1] > heap[index_copy*2+2]){
						if(heap[index_copy] > heap[index_copy*2+2]){
							int temp = heap[index_copy];
							heap[index_copy] = heap[index_copy*2+2];
							heap[index_copy*2+2] = temp;
							index_copy = index_copy*2+2;
						}
						else{
							break;
						}
					}
					else{
						if(heap[index_copy] > heap[index_copy*2+1]){
							int temp = heap[index_copy];
							heap[index_copy] = heap[index_copy*2+1];
							heap[index_copy*2+1] = temp;
							index_copy = index_copy*2+1;
						}
						else{
							break;
						}
					}
				}
			}
		}
		
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value)
	{
		//implemented by myself
		merge_sort(value, 0, value.length-1);
		return (value);
	}
	private static void merge_sort(int value[], int start, int end){
		int m = (start + end)/2;
		if(start < end){
			merge_sort(value, start, m);
			merge_sort(value, m+1, end);
			merge(value, start, m, end);
		}
	}
	private static void merge(int value[], int start, int m, int end){
		//copy from https://www.geeksforgeeks.org/merge-sort/
		int left_size = m - start + 1;
		int right_size = end - m;
		
		int[] left = new int[left_size];
		int[] right = new int[right_size];
		
		for(int i=0;i<left_size;i++){
			left[i] = value[start+i];
		}
		for(int j=0;j<right_size;j++){
			right[j] = value[m+1+j];
		}
		int i=0, j=0;
		int k = start;
		while(i < left_size && j < right_size){
			if(left[i] <= right[j]){
				value[k] = left[i];
				i++;
			}
			else{
				value[k] = right[j];
				j++;
			}
			k++;
		}
		while(i < left_size){
			value[k] = left[i];
			i++;
			k++;
		}
		while(j < right_size){
			value[k] = right[j];
			j++;
			k++;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoQuickSort(int[] value)
	{
		//implemented by myself
		quick_sort(value, 0, value.length-1);
		return (value);
	}
	private static void quick_sort(int[] num, int start, int end){
		if(start >= end){
			return;
		}
		int q = partition(num, start, end);
		quick_sort(num, start, q-1);
		quick_sort(num, q+1, end);
	}
	private static int partition(int[] num, int start, int end){
		int j=start+1;
		for(int i=start+1;i<=end;i++){
			if(num[start] < num[i]){
				continue;
			}
			else{
				int temp = num[j];
				num[j] = num[i];
				num[i] = temp;
				j++;
			}
		}
		int temp = num[j-1];
		num[j-1] = num[start];
		num[start] = temp;
		return j-1;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] value)
	{
		//modification from geeksforgeeks.org/radix-sort/
		int max = getmax(value);
		for(int i=1;max/i>0;i=i*10){
			countingsort(value, i);
		}
		return (value);
	}
	private static void countingsort(int[] value, int x){
		//modification from geeksforgeeks.org/radix-sort/
		int count[] = new int[10];
		int mcount[] = new int[10];
		//initialize
		for(int i=0;i<10;i++){
			count[i] = 0;
			mcount[i] = 0;
		}
		
		//make count
		for(int i=0;i<value.length;i++){
			if(value[i] >= 0)
				count[getnumber(value[i], x)]++;
			else
				mcount[getnumber(value[i]*(-1), x)]++;
		}
		//make countsum
		for(int i=1;i<10;i++){
			count[i] += count[i-1];
		}
		for(int i=9;i>=1;i--){
			mcount[i-1] += mcount[i];
		}
		int temp[] = new int[count[count.length-1]];
		int temp2[] = new int[mcount[0]];
		//make result
		for(int i=value.length-1;i>=0;i--){
			if(value[i] < 0){
				temp2[mcount[getnumber(value[i]*(-1), x)] - 1] = value[i];
	            mcount[getnumber(value[i]*(-1), x)]--;
			}
			else{
				temp[count[getnumber(value[i], x)] - 1] = value[i];
	            count[getnumber(value[i], x)]--;
			}
		}
		//copy result
		int tmp_i = 0;
		for(int i=0;i<temp2.length;i++){
			value[tmp_i] = temp2[i];
			tmp_i++;
		}
		for(int i=0;i<temp.length;i++){
			value[tmp_i] = temp[i];
			tmp_i++;
		}
	}
	private static int getnumber(int number, int n){
		return (number % (n*10)) / n;
	}
	private static int getmax(int[] value){
		int max = 0;
		for(int i=0;i<value.length;i++){
			if(max<value[i]){
				max = value[i];
			}
		}
		return max;
	}
}
