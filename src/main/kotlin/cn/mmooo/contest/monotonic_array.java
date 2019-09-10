package cn.mmooo.contest;

/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 */
public class monotonic_array {
    public boolean isMonotonic(int[] A) {
        int flag = 0;
        for (int i = 1; i < A.length; i++) {
            if (flag == 0) {
                if (A[i - 1] > A[i]) {
                    flag = -1;
                }
                if (A[i - 1] < A[i]) {
                    flag = 1;
                }
            } else {
                int res = Integer.compare(A[i], A[i - 1]) * flag;
                if (res < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
