package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //需要和服务端建立链接，设置端口需要和服务端一致
        ServerSocket server = new ServerSocket(2000);
        //如果链接成功
        System.out.println("服务器已经准备就绪，等待下一步操作");
        //获取服务端信息
        System.out.println("服务端信息:" + server.getInetAddress() + "P:" + server.getLocalPort());

        for(;;){
            //等待客户端链接
            Socket client = server.accept();
            //客户端构建异步线程
            ClientHandler clientHandler = new ClientHandler(client);
            //线程启动
            clientHandler.start();
        }
    }

    private static class ClientHandler extends Thread{
        private Socket socket;
        private boolean flag = true;

        ClientHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            super.run();
            System.out.println("新客户端链接：" + socket.getInetAddress() + "P:" + socket.getPort());
            try{
                //打印流 数据输出，服务器回送数据使用
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                //得到输入流，接受数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    //客户端拿到数据
                    String str = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(str)) {
                        flag = false;
                        //回送
                        socketOutput.println("bye");
                    } else {
                        //打印到屏幕，回送数据长度
                        System.out.println(str.toUpperCase());
                        socketOutput.println("回送" + str.length());

                    }
                }while (flag);

                socketInput.close();
                socketOutput.close();

            }catch (Exception e){
                System.out.println("链接异常断开");
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("客户端已退出");
        }
    }
}
