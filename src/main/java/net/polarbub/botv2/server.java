package net.polarbub.botv2;

import java.io.*;

public class server extends Thread{
    public void run() {
        Main.serverRunning = true;
        try {
            Main.p = Main.pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.br = new BufferedReader(new InputStreamReader(Main.p.getInputStream()));
        Main.bw = new BufferedWriter(new OutputStreamWriter(Main.p.getOutputStream()));
        try {
            for (String line = Main.br.readLine(); line != null; line = Main.br.readLine()) {
                System.out.println(line);
                out.add(line);
            }
            Main.p.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }
        Main.serverRunning = false;
        git.backup("Server_shutdown");
    }

    public static void main() { (new server()).start(); }
}
