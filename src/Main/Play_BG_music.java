package Main;

import Main.Main;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Play_BG_music extends Thread {
    private Player music_player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    // Play_BG_music 클래스의 생성자, 객체 생성시 파일 이름과, 반복여부를 인자로 받는다.
    public Play_BG_music(String bgm, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(Main.class.getResource("/Sound_data/" + bgm).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            music_player = new Player(fis);
        } catch(Exception e) { }
    }

    // 반복 재생중인 사운드 정지 메서드
    public void close() {
        isLoop = false;
        music_player.close();
        this.interrupt();
    }

    // 쓰레드 실행시, 실행되는 run() 메서드 (사운드 재생)
    @Override
    public void run () {
        synchronized (this) {
            try {
                do {
                    music_player.play();
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    music_player = new Player(bis);
                } while (isLoop);
            } catch (Exception e) { }
        }
    }


}