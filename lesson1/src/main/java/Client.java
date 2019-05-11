import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        //设置超时时间
        socket.setSoTimeout(3000);
        //设置链接端口号 ipv4本机地址，端口号2000 超时时间
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        //如果链接成功
        System.out.println("已发起服务器连接，等待下一步操作");
        //打印本地地址和端口，显示本地服务器信息
        System.out.println("客户端信息:" + socket.getLocalAddress() + "P:" + socket.getLocalPort());
        //获取服务端信息
        System.out.println("服务端信息:" + socket.getInetAddress() + "P:" + socket.getPort());

        try {
            //发送接受数组
            todo(socket);
        } catch (Exception e) {
            System.out.println("一场关闭");
        }

        //释放资源
        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo(Socket client) throws IOException {
        //构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        //客户端输出流
        OutputStream out = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(out);

        //得到socket输入流
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do {
            //从键盘读取
            String str = input.readLine();
            //发送到服务器
            socketPrintStream.println(str);

            String echo = socketBufferReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            } else {
                System.out.println(echo);
            }
        } while (flag);

        //释放资源
        socketBufferReader.close();
        socketPrintStream.close();

    }

}























