package Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Thread_clock extends Thread {
    public long current_time; // 현재 시간
    public long first_time; // 처음 시간
    public long last_time; // 마지막 시간

    // 총 플레이타임 계산 및 출력
    public void show_play_time() {
        long play_time = last_time - first_time; // 총 플레이 타임

        SimpleDateFormat sdf = new SimpleDateFormat("   mm 분 ss 초");
        String date_time = sdf.format(new Date(play_time));

        System.out.println("                                                    " + date_time);

    }

    // 시계 스레드 정지
    public void clock_stop() {
        this.interrupt();
    }

    // 스레드 실행시, 실행되는 run() 메서드
    @Override
    public void run() {
        try {
            boolean isFirst = true;
            current_time = System.currentTimeMillis(); // 현재 시간
            first_time = current_time; // 처음 시간 == 가장 초기 현재 시간

            // 시간 포맷 설정
            SimpleDateFormat sdf = new SimpleDateFormat("HH 시 mm 분 ss 초");
            String date_time = sdf.format(new Date(current_time));

            if(isFirst) {
                System.out.println("\n\n=============================================================");
                System.out.print("[현재시간] " + date_time + "\n");
                System.out.println("=============================================================\n\n");

                isFirst = false;
            }
            // 1분 부터 30분까지 카운트
            for(int i = 1; i <= 30; i++) {
                Thread.sleep(60000); // 1분
                date_time = sdf.format(new Date(current_time));

                // 1분 단위로 시간 표시
                System.out.println("\n\n=============================================================");
                System.out.println("게임시작 후 " + i + "분이 경과하였습니다.");
                System.out.print("[현재시간] " + date_time);
                System.out.println("=============================================================\n");

                // 5분 단위로 경고 문구 출력
                if( i == 5 || i == 10 || i == 15 || i == 20 || i == 25 ) {
                    System.out.println("[시스템] 게임을 장시간 플레이하는 것은 건강에 해롭습니다.\n");
                }
                // 30분 경과시 경고 문구와 함께 프로그램 종료
                else if (i == 30) {
                    System.out.println("[시스템] 게임을 장시간 플레이하여 건강이 우려되어 종료합니다.\n");

                    Play_BG_music bye_bye_sound = new Play_BG_music("bye_bye_sound.mp3", false);
                    bye_bye_sound.start();
                    Thread.sleep(500); //0.5초

                    System.exit(0);
                }
            }
        } catch (Exception e) { // 스레드 종료시(interrupt로 인한) 마지막 시간 저장
            last_time = System.currentTimeMillis();
            //System.out.println("1");
        }
    }
}
