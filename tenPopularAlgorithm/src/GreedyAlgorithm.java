import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 * 贪心算法解决广播站问题
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 城市集合
        HashSet<String> cities = new HashSet<>();
        cities.add("北京");
        cities.add("上海");
        cities.add("天津");
        cities.add("广州");
        cities.add("深圳");
        cities.add("成都");
        cities.add("杭州");
        cities.add("大连");
        
        // 电台集合
        HashMap<String, HashSet<String>> stations = new HashMap<>();

        HashSet<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        stations.put("k1", k1);

        HashSet<String> k2 = new HashSet<>();
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");
        stations.put("k2", k2);

        HashSet<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        stations.put("k3", k3);

        HashSet<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");
        stations.put("k4", k4);

        HashSet<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");
        stations.put("k5", k5);
        
        // 最终获取的电台集合
        HashSet<String> finalStations = new HashSet<>();

        greedy(stations, cities, finalStations);

        for (String i :
                finalStations) {
            System.out.println(i);
        }
    }


    /**
     * 贪心算法解决广播站问题
     * @param stations
     * @param cities
     * @param finalStations
     */
    public static void greedy(HashMap<String, HashSet<String>> stations, HashSet<String> cities, HashSet<String> finalStations) {
        // 定义临时集合
        HashSet<String> temp = new HashSet<>();
        String maxStation = null;
        int preCount = 0;
        
        while (cities.size() != 0) {
            // 每次都初始化覆盖最大范围的电台编号
            maxStation = null;

            // 遍历电台集合
            for (String i :
                    stations.keySet()) {
                // 每次都清空temp
                temp.clear();

                // 获取电台覆盖的城市
                temp.addAll(stations.get(i));
                // 获取电台覆盖的城市和所有城市的交集
                temp.retainAll(cities);
                if (temp.size() > 0) {
                    // 如果是第一次进入while循环且第一次进入foreach循环，maxStation==null，maxStation需要赋值，这个判断也要加上
                    if (maxStation == null || temp.size() > preCount) {
                        maxStation = i;
                        preCount = stations.get(i).size();
                    }
                }
            }

            // 将最大范围电台添加入最终电台集合，并从cities删除对应的城市
            if (maxStation != null) {
                finalStations.add(maxStation);
                // 移除城市
                cities.removeAll(stations.get(maxStation));
            }
        }
    }
}
