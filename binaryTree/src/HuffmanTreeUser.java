import java.util.*;

public class HuffmanTreeUser {

    public static void main(String[] args) {

        String target = "i realjjjly really really dsdreally dont like like like you killer killer do do false false true true";
        // 1.将字符串的字符根据出现次数，转换为哈夫曼树
        byte[] targetBytes = target.getBytes();

        // 获取哈夫曼树的叶子节点集合
        List<HuffmanTreeNode> nodeList = getNodeList(targetBytes);

        // 创建哈夫曼树
        HuffmanTreeNode huffmanRoot = createHuffman(nodeList);

        // 2. 获得每个字符的编码集
        huffmanEncode(huffmanRoot);

        // 3.对字符串进行编码压缩
//        System.out.println(huffmanCode.toString());
        byte[] zip = zip(targetBytes, huffmanCode);
//        byteToBitString((byte) -2);
        // 4.解压缩
        byte[] decode = decode(huffmanCode, zip);
        System.out.println(new String(decode));
    }

    /**
     * 压缩数据的解码
     * 压缩过后的字节数组解码成原来字符串对应的字节数组
     * @param huffmanCode
     * @param zip
     * @return
     */
    public static byte[] decode(Map<Byte, String> huffmanCode, byte[] zip) {
        // 1.先得到zip的字节对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zip.length; i++) {
            // 如果不是最后一个字节，需要补位到8位，是最后一个字节，只需要原样获得其对应的二进制字符串
            if (i < zip.length - 1) {
                stringBuilder.append(byteToBitString(false, zip[i]));
            } else {
                stringBuilder.append(byteToBitString(true, zip[i]));
            }

        }

        // 2.开始解码
        // 将赫夫曼编码表进行键值调换，形成一个解码表
        Map<String, Byte> huffmanDecode = new HashMap<>();
        Set<Map.Entry<Byte, String>> entries = huffmanCode.entrySet();
        for (Map.Entry<Byte, String> entry :
                entries) {
            huffmanDecode.put(entry.getValue(), entry.getKey());
        }

        // 用于存放byte的集合
        List<Byte> targetList = new ArrayList<>();
        // 扫描stringBuilder
        int index = 0;
        while (index < stringBuilder.length()) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (true) {
                String key = stringBuilder.substring(index, index + count);
                b = huffmanDecode.get(key);

                if (b == null) {
                    count++;
                } else {
                    break;
                }
            }
            targetList.add(b);
            index = index + count;
        }
        // 组装
        byte[] targetBytes = new byte[targetList.size()];
        for (int i = 0; i < targetList.size(); i++) {
            targetBytes[i] = targetList.get(i);
        }

        return targetBytes;
    }

    /**
     * bye转换成对应的二进制字符串
     * @param flag 判断该字节是否为压缩后的最后一个字节,true表示是，false表示不是
     * @param b
     * @return
     */
    public static String byteToBitString(boolean flag, byte b) {
        // Integer有一个将int类型的数转换成binaryString的方法，因此，先将byte转成int，然后使用Integer的方法
        // Integer.toBinaryString() -- 返回int值对应的二进制的补码
        // 因为一个int对应4个字节，因此：如果传的b是负数，转换成int后对应的补码需要截取后面八位才是真正需要的；如果是正数，需要补位
        int temp = b;
        String s = null;
        if (!flag) {
            temp |= 256; // 让传进来的数和256按位或
        }
        s = Integer.toBinaryString(temp);
        if (!flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    /**
     * 根据byte数组创建存放哈夫曼树节点的集合
     * @param targetBytes
     * @return
     */
    public static List<HuffmanTreeNode> getNodeList(byte[] targetBytes) {

        List<HuffmanTreeNode> nodeList = new ArrayList<>();
        // 存放字符和其出现的次数
        Map<Byte, Integer> counts = new HashMap<>();

        for (byte i :
                targetBytes) {
            Integer count = counts.get(i);
            if (count == null) {
                counts.put(i, 1);
            } else {
                counts.put(i, count + 1);
            }
        }
        // 将每个字符的信息封装成哈夫曼树节点，并放入集合
        Set<Map.Entry<Byte, Integer>> entries = counts.entrySet();
        for (Map.Entry<Byte, Integer> entry :
                entries) {
            nodeList.add(new HuffmanTreeNode(entry.getKey(), entry.getValue()));
        }
        return nodeList;
    }

    /**
     * 根据哈夫曼节点的集合创建哈夫曼树
     * @param list
     * @return
     */
    public static HuffmanTreeNode createHuffman(List<HuffmanTreeNode> list) {
        // 对集合排序
        Collections.sort(list);

        // 创建哈夫曼树
        while (list.size() > 1) {

            HuffmanTreeNode first = list.get(0);
            HuffmanTreeNode second = list.get(1);

            HuffmanTreeNode temp = new HuffmanTreeNode((byte) 0, first.value + second.value);
            temp.left = first;
            temp.right = second;

            list.remove(first);
            list.remove(second);
            list.add(temp);
        }

        // 返回哈夫曼树的根节点
        return list.get(0);
    }

    /**
     * 为每个字符指定哈夫曼编码
     * @param root
     * @return
     */

    private static StringBuilder stringBuilder = new StringBuilder();
    private static Map<Byte, String> huffmanCode = new HashMap<>();

    public static void huffmanEncode(HuffmanTreeNode root) {
        if (root == null) {
            System.out.println("没有哈夫曼树~");
            return;
        }
        // 根节点左树
        StringBuilder stringBuilder1 = new StringBuilder();
        huffmanEncode(root.left, "0", stringBuilder1);
        // 根节点右树
        StringBuilder stringBuilder2 = new StringBuilder();
        huffmanEncode(root.right, "1", stringBuilder2);
    }

    public static void huffmanEncode(HuffmanTreeNode node, String code, StringBuilder stringBuilder) {
        // 定义一个字符串缓冲来存放编码
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        // 将code加入字符串缓冲区
        stringBuilder1.append(code);
        if (node != null) {
            if (node.left != null) {
                // 如果是非叶子节点
                // 先递归向左
                huffmanEncode(node.left, "0", stringBuilder1);
                // 递归向右
                huffmanEncode(node.right, "1", stringBuilder1);

            } else {
                // 找到某个叶子节点
                // 将叶子结点的字符和字符的编码放入map集合
                huffmanCode.put(node.data, stringBuilder1.toString());
            }
        }
    }

    /**
     * 为字符串的byte[]数组进行哈夫曼编码，生成压缩过的byte[]
     * 将原始字符串按照编码规则生成编码后的字符串，然后将编码后的字符串每8位一个比特，转化为byte[]数组
     * @param target
     * @param huffmanCode
     * @return
     */
    public static byte[] zip(byte[] target, Map<Byte, String> huffmanCode) {
        // 遍历字符串的byte[]，生成编码后的字符串
        StringBuilder strCode = new StringBuilder();
        for (byte i :
                target) {
            strCode.append(huffmanCode.get(i));
        }
        String huffmanStr = strCode.toString();
        // 将编码后的字符串转化为byte[]数组
        // 统计编码后的字符串按照8位一个byte需要的byte[]数组长度
        int len = 0;
        if (huffmanStr.length() % 8 == 0) {
            len = huffmanStr.length() / 8;
        } else {
            len = huffmanStr.length() / 8 + 1;
        }
        // 创建存储压缩后的byte[]数组
        byte[] huffmanBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < huffmanStr.length(); i = i + 8) {
            String strByte;
            if (i + 8 < huffmanStr.length()) {
                // 剩下的够8位，截取8位
                strByte = huffmanStr.substring(i, i + 8);
            } else {
                // 剩下的不够8位，从i截取到结尾
                strByte = huffmanStr.substring(i);
            }
            // 将strByte转成一个byte放入huffmanBytes
            huffmanBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanBytes;
    }

    /**
     * 前序遍历方法
     */
    public static void preOrder(HuffmanTreeNode node) {
        // 输出父节点
        if (node.left == null) {
            System.out.printf("节点权重为%d\n", node.value);
        }
        // 输出左边的树
        if (node.left != null) {
            preOrder(node.left);
        }
        // 输出右边的树
        if (node.right != null) {
            preOrder(node.right);
        }
    }
}

/**
 * 哈夫曼树节点类
 * 为了能够让节点类可以进行排序，需要先让类实现Compareable接口
 */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{

    byte data; // 存放字符
    int value; // 权重
    HuffmanTreeNode left;
    HuffmanTreeNode right;

    public HuffmanTreeNode(byte data, int value) {
        this.data = data;
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanTreeNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 节点之间比较的方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(HuffmanTreeNode o) {
        // 从小往大排序
        return this.value - o.value;
    }
}
