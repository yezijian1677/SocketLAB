package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher Started.");
        //作为搜索方，指定一个端口惊醒数据接收
        DatagramSocket ds = new DatagramSocket();


        //构建一份请求数据
        String requestData = "helloWorld ";
        byte[] requestDataBytes = requestData.getBytes();

        //直接根据发送者构建一份回送信息
        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length);
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);
        ds.send(requestPacket);


        final byte[] buf = new byte[512];
        DatagramPacket recivePack = new DatagramPacket(buf, buf.length);

        //接收
        ds.receive(recivePack);

        //打印接收到的信息与发送者的信息
        //发送者的ip地址
        String ip = recivePack.getAddress().getHostAddress();
        int port = recivePack.getPort();
        int dataLen = recivePack.getLength();
        String data = new String(recivePack.getData(), 0, dataLen);
        System.out.println("UDPSearcher receive from ip: " + ip + "\tport: " + port + "\tdata:" + data);

        //完成
        System.out.println("UDPSearcher Finished.");
        ds.close();
    }
}
