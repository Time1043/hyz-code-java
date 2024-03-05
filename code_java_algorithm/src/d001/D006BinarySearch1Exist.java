package d001;

public class D006BinarySearch1Exist {
    // 在有序数组arr中找num
    public static boolean exist(int[] sortedArr, int num) {
        // 边界条件
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }

        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] > num) {
                return true;
            } else if (sortedArr[mid] < num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num;
    }
}
