package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPProvider {

    public static void main(String[] args) throws IOException {
        System.out.println("UDPProvider Started.");
        //作为接收者，指定一个端口惊醒数据接收
        DatagramSocket ds = new DatagramSocket(20000);

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
        System.out.println("UDPProvider receive from ip: " + ip + "\tport: " + port + "\tdata:" + data);

        //构建一份回送数据
        String responseData = "receive data with len: "+ dataLen;
        byte[] responsesDataBytes = responseData.getBytes();

        //直接根据发送者构建一份回送信息
        DatagramPacket responsePacket = new DatagramPacket(responsesDataBytes, responsesDataBytes.length,
                recivePack.getAddress(), recivePack.getPort());
        ds.send(responsePacket);

        //完成
        System.out.println("UDPProvider Finished.");
        ds.close();
    }

}
