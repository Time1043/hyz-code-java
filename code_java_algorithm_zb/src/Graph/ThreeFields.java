package Graph;

import Graph.UnionFind.*;

import java.util.HashMap;
import java.util.List;

public class ThreeFields {
    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int mergeUsers(List<User> users) {
        // 把所有user放入并查集里
        UnionSet<User> userUnionSet = new UnionSet<>(users);

        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();

        for (User user : users) {
            // 重复出现的并查集连接、新出现的登记
            if (mapA.containsKey(user.a)) userUnionSet.union(user, mapA.get(user.a));
            else mapA.put(user.a, user);
            if (mapB.containsKey(user.b)) userUnionSet.union(user, mapB.get(user.b));
            else mapB.put(user.b, user);
            if (mapC.containsKey(user.c)) userUnionSet.union(user, mapC.get(user.c));
            else mapC.put(user.c, user);
        }

        return userUnionSet.getSetNum();  // 向并查集询问 合并后还剩多少集合
    }
}
