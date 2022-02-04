
public class BinarySearch {

	public static void main(String[] args) {
		int[] a = {1, 3, 4, 6, 7, 9};
		System.out.println(bsearch(a, 3));  // true
		System.out.println(bsearch(a, 111));  // false

	}

	public static boolean bsearch(int[] a, int s) {
		
		int l = 0;
		int r = a.length-1;
		while (l != r) {
			int mid = (l+r)/2;
			if (a[mid] >= s) r = mid;
			else l = mid+1;
		}

		if (a[l] == s) return true;
		return false;
		// program will look through a and return true is s is there. If not, it
		// will return false. MUST run O(log n) time
	}

}
