package cn.mmooo.contest;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class split_array_into_fibonacci_sequence {

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> dfs = dfs(new LinkedList<>(), 0, S);
        return dfs == null ? Collections.emptyList() : dfs;
    }

    private List<Integer> dfs(List<Integer> route, int idx, String S) {

        if (idx >= S.length()) {
            if (route.size() > 2)
                return route;
            else return null;
        }

        List<Integer> result = null;
        int size = route.size();
        if (size >= 2) {
            Integer sum = route.get(size - 1) + route.get(size - 2);
            route.add(sum);
            if (S.startsWith(sum.toString(), idx)) {
                result = dfs(route, idx + sum.toString().length(), S);
            }
        } else {


            boolean b = S.charAt(idx) == '0';
            if (b) {
                route.add(0);
                result = dfs(route, idx + 1, S);
            } else {
                for (int i = idx + 1; i <= S.length(); i++) {
                    String s = S.substring(idx, i);
                    Long value = Long.valueOf(s);
                    if (value > Integer.MAX_VALUE) break;
                    route.add(value.intValue());
                    result = dfs(route, i, S);
                    if (result == null) {
                        if (route.size() > 0)
                            route.remove(route.size() - 1);
                    } else {
                        return result;
                    }
                }
            }
        }
        if (result == null) {
            if (route.size() > 0)
                route.remove(route.size() - 1);
            return null;
        } else {
            return result;
        }

    }

    public static void main(String[] args) {
        List<Integer> list = new split_array_into_fibonacci_sequence().splitIntoFibonacci("12345652782872782872579");
        System.out.println(list);
    }
}