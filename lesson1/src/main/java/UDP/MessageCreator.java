package UDP;

public class MessageCreator {
    private static final String SN_HEEADER = "收到暗号，我是（SN）:";
    private static final String Port_HEEADER = "这是暗号，请回电脑端口（port）:";

    public static String buildWithPort(int port) {
        return Port_HEEADER + port;
    }

    public static int parse(String data) {
        if (data.startsWith(Port_HEEADER)) {
            return Integer.parseInt(data.substring(Port_HEEADER.length()));
        }
        return -1;
    }

    public static String buildWithSn(String sn) {
        return SN_HEEADER+sn;
    }

    public static String parseSn(String data) {
        if (data.startsWith(SN_HEEADER)) {
            return data.substring(SN_HEEADER.length());
        }

        return null;
    }
}
