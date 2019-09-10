package cn.mmooo.contest;


import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        Stack<Integer> keys = new Stack<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        keys.push(0);
        while (!keys.isEmpty()) {
            Integer room = keys.pop();
            visited.add(room);
            rooms.get(room).forEach(
                    r -> {
                        if (!visited.contains(r)) {
                            keys.push(r);
                        }
                    });


        }
        return visited.size() == rooms.size();
    }
}
