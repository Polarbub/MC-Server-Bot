package net.polarbub.botv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class git extends Thread {
    public static boolean gitInUse = false;

    public void run() {

    }

    public static void backup(String comment) {
        gitInUse = true;
        System.out.println("Backup started");
        out.add("Backup started");
        runProg("git add *.*");
        if(comment == null) {
            runProg("git commit -a");
        } else if(comment.contains(" ")) {
            throw new IllegalArgumentException("No spaces allowed in commit comment");
        } else {
            runProg("git commit -a -m " + comment);
        }
        gitInUse = false;
        System.out.println("Backup complete");
        out.add("Backup complete");
    }

    public static void runProg(String prog) {
        ProcessBuilder pb = new ProcessBuilder(prog);
        Process p = null;
        try {
            p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
                out.add(line);
            }
            Main.p.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }
    }

    public static void main() {
        (new git()).start();
    }
}
