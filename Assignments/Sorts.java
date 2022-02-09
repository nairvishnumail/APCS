
public class Sorts {
	public static void main(String[] args) {

		int[] arr = {10, 7, 15, 1, -10};

		
		print(smartBubbleSort(arr));
		print(insertionSort(arr));
		print(selectionSort(arr));
		

	}

	public static void print(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; i++) System.out.print(arr[i] + " , ");
		System.out.println(arr[arr.length-1]+"]");
	}

	public static int[] smartBubbleSort(int[] arr) {
		System.out.println("Smart bubble sort:");
		
		
		int i = 0;
		boolean anySwap = false;
		boolean goodArr = false;
		while(i < arr.length-1 && !goodArr) {
			anySwap = false;
			for (int j = 0; j < arr.length-i-1; j++) {
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					anySwap = true;
				}
			}
			i++;
			if (!anySwap) {
				goodArr = true;
			}
		}
		
		return arr;

	}
	public static int[] insertionSort(int[] arr) {
		System.out.println("Insertion sort:");
		
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i - 1;

			while (temp < arr[j] && j > -1) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
		return arr;

	}
	public static int[] selectionSort(int[] arr) {
		System.out.println("Selection sort:");
		
		int minValue = 0;
		int temp = 0;

		for (int i = 0; i < arr.length; i++) {

			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}

		return arr;
	}

}
