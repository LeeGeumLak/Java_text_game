package Main;

import Character_info.Character_combatant;
import Character_info.Character_combatant_player;

import java.io.IOException;

public class Thread_effect extends Thread {

    // 프로그램 시작시, 현재의 시간 표시를 위한 쓰레드
    Thread_clock thread_clock = new Thread_clock();

    // sleep() 메서드 : 0.5초동안 쓰레드를 대기 상태로 만듦
    public void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }
    }

    // 키보드로 입력하는 듯하게, 문자열을 인자로 받아 한글자씩 출력이 되도록 하는 메서드
    public void keyboard_effect(String string) {
        Thread game_narration = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String narration = string;
                    for(int i = 0; i < narration.length(); i++) {
                        Thread.sleep(100);
                        System.out.print(narration.charAt(i));
                    }
                } catch (Exception e) { }
            }
        });
        try {
            // 키보드 사운드
            Play_BG_music keyboard_sound = new Play_BG_music("KEYSOUND1.mp3", true);
            keyboard_sound.start();

            game_narration.start();
            game_narration.join();

            keyboard_sound.close();
            Thread.sleep(200);
        } catch (Exception e) { }
        System.out.println();
    }

    // 키보드로 입력하는 듯하게, 문자열을 인자로 받아 한글자씩 출력이 되도록 하는 메서드 (keyboard_effect와 같지만, 출력되는 속도가 다름)
    public void keyboard_effect_fast(String string) {
        Thread game_narration = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String narration = string;
                    for(int i = 0; i < narration.length(); i++) {
                        Thread.sleep(20);
                        System.out.print(narration.charAt(i));
                    }
                } catch (Exception e) { }
            }
        });
        try {
            Play_BG_music keyboard_sound = new Play_BG_music("KEYSOUND1.mp3", true);
            keyboard_sound.start();

            game_narration.start();
            game_narration.join();

            keyboard_sound.close();
            Thread.sleep(200);
        } catch (Exception e) { }
        System.out.println();
    }

    // 로딩 메서드 : 일정 간격으로 ▶, ▷ 을 출력하여, 로딩하는 듯한 효과를 줌
    public void loading() {
        System.out.print("\n로딩중....");
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 0) {
                System.out.print("▷ ");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { }
            }

            else {
                System.out.print("▶ ");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { }
            }
        }
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 0) {
                System.out.print("▷ ");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) { }
            }

            else {
                System.out.print("▶ ");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) { }
            }
        }
        for(int i = 0; i < 20; i++) {
            if(i % 2 == 0) {
                System.out.print("▷ ");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }

            else {
                System.out.print("▶ ");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
        }
        System.out.println("\n");
    }

    // 처음 게임 시작시, 게임 타이틀 오프닝 이벤트 메서드
    public void title_opening() {
        System.out.println("==================================================================================================================================================");
        System.out.println(" .----------------.    .----------------.    .----------------.    .----------------.  .----------------.  .----------------.  .----------------. ");
        System.out.println("| .--------------. |  | .--------------. |  | .--------------. |  | .--------------. || .--------------. || .--------------. || .--------------. |");
        this.sleep();
        System.out.println("| |  _______     | |  | |   ______     | |  | |    ______    | |  | |    ______    | || |      __      | || | ____    ____ | || |  _________   | |");
        System.out.println("| | |_   __ \\    | |  | |  |_   __ \\   | |  | |  .' ___  |   | |  | |  .' ___  |   | || |     /  \\     | || ||_   \\  /   _|| || | |_   ___  |  | |");
        System.out.println("| |   | |__) |   | |  | |    | |__) |  | |  | | / .'   \\_|   | |  | | / .'   \\_|   | || |    / /\\ \\    | || |  |   \\/   |  | || |   | |_  \\_|  | |");
        this.sleep();
        System.out.println("| |   |  __ /    | |  | |    |  ___/   | |  | | | |    ____  | |  | | | |    ____  | || |   / ____ \\   | || |  | |\\  /| |  | || |   |  _|  _   | |");
        System.out.println("| |  _| |  \\ \\_  | |  | |   _| |_      | |  | | \\ `.___]  _| | |  | | \\ `.___]  _| | || | _/ /    \\ \\_ | || | _| |_\\/_| |_ | || |  _| |___/ |  | |");
        System.out.println("| | |____| |___| | |  | |  |_____|     | |  | |  `._____.'   | |  | |  `._____.'   | || ||____|  |____|| || ||_____||_____|| || | |_________|  | |");
        this.sleep();
        System.out.println("| |              | |  | |              | |  | |              | |  | |              | || |              | || |              | || |              | |");
        System.out.println("| '--------------' |  | '--------------' |  | '--------------' |  | '--------------' || '--------------' || '--------------' || '--------------' |");
        System.out.println(" '----------------'    '----------------'    '----------------'    '----------------'  '----------------'  '----------------'  '----------------' ");
        this.sleep();
        System.out.println("==================================================================================================================================================");
    }

    // 게임 종료시, 엔딩 크레딧 이벤트 메서드
    public void ending_credit(Character_combatant_player player) {
        this.sleep();
        this.sleep();

        System.out.println("                                                    [ 총 플레이 시간 ]");
        thread_clock.show_play_time();

        this.sleep();
        this.sleep();

        System.out.println("\n                                                        [ 기획 ]\n                                                         이금락");

        this.sleep();
        this.sleep();

        System.out.println("\n                                                        [ 제작 ]\n                                                         이금락");

        this.sleep();
        this.sleep();

        System.out.println("\n                                                      [ 감사합니다 ]");
        System.out.println("\n                                                       [   끝   ]");

    }

    // 보스 몬스터를 처치하여 게임 클리어 했을 때의 이벤트 메서드
    public void game_clear(Character_combatant_player player, Character_combatant monster) {
        // 시간 스레드 종료
        thread_clock.clock_stop();

        // 보스 몬스터가 사망했을 때의 사운드
        Play_BG_music monster_die = new Play_BG_music("devil_die.mp3", false);
        monster_die.start();

        System.out.println("\n[ " + monster.name + " ]이 쓰러졌습니다!!!!!");
        System.out.println("=====================================================================================================");

        // 게임 엔딩했을 때의 사운드
        Play_BG_music ending_bgm = new Play_BG_music("Ending_sound.mp3", true);
        ending_bgm.start();

        this.keyboard_effect("축하합니다 ~~ [ " + player.name + " ]님은 게임을 클리어하셨습니다!!!");
        this.keyboard_effect("게임을 끝까지 플레이 해주셔서 감사합니다.");
        System.out.println("\n========================================================================================================================");
        System.out.println(" .----------------.  .----------------.  .----------------.   .----------------.  .----------------.  .----------------. ");
        System.out.println("| .--------------. || .--------------. || .--------------. | | .--------------. || .--------------. || .--------------. |");
        this.sleep();
        System.out.println("| |  ____  ____  | || |     ____     | || | _____  _____ | | | | _____  _____ | || |     _____    | || | ____  _____  | |");
        System.out.println("| | |_  _||_  _| | || |   .'    `.   | || ||_   _||_   _|| | | ||_   _||_   _|| || |    |_   _|   | || ||_   \\|_   _| | |");
        System.out.println("| |   \\ \\  / /   | || |  /  .--.  \\  | || |  | |    | |  | | | |  | | /\\ | |  | || |      | |     | || |  |   \\ | |   | |");
        this.sleep();
        System.out.println("| |    \\ \\/ /    | || |  | |    | |  | || |  | '    ' |  | | | |  | |/  \\| |  | || |      | |     | || |  | |\\ \\| |   | |");
        System.out.println("| |    _|  |_    | || |  \\  `--'  /  | || |   \\ `--' /   | | | |  |   /\\   |  | || |     _| |_    | || | _| |_\\   |_  | |");
        System.out.println("| |   |______|   | || |   `.____.'   | || |    `.__.'    | | | |  |__/  \\__|  | || |    |_____|   | || ||_____|\\____| | |");
        this.sleep();
        System.out.println("| |              | || |              | || |              | | | |              | || |              | || |              | |");
        System.out.println("| '--------------' || '--------------' || '--------------' | | '--------------' || '--------------' || '--------------' |");
        System.out.println(" '----------------'  '----------------'  '----------------'   '----------------'  '----------------'  '----------------' ");
        this.sleep();
        System.out.println("==========================================================================================================================\n");

        // 엔딩 크레딧 이벤트
        //ending_credit(player);

        System.out.println("\n\n게임이 종료되었습니다. ENTER 키를 누르세요.");

        try {
            System.in.read();
        } catch (IOException e) { }

        System.exit(0);
    }

    // 전투 중, 플레이어가 사망했을 때의 이벤트 메서드
    public void player_dead(Character_combatant_player player) {
        // 시간 스레드 종료
        thread_clock.interrupt();

        System.out.println("\n\n=====================================================================================================");

        // 게임 오버했을 때의 사운드
        Play_BG_music die_bgm = new Play_BG_music("GAMEOVERBGM.mp3", true);
        die_bgm.start();

        this.keyboard_effect("플레이어가 사망했습니다....................");
        this.keyboard_effect("모든 것을 잃습니다......................");
        System.out.println("\n=======================================================================================================================");
        System.out.println(" .----------------.  .----------------.  .----------------.   .----------------.  .----------------.  .----------------. ");
        System.out.println("| .--------------. || .--------------. || .--------------. | | .--------------. || .--------------. || .--------------. |");
        this.sleep();
        System.out.println("| |  ____  ____  | || |     ____     | || | _____  _____ | | | |  ________    | || |     _____    | || |  _________   | |");
        System.out.println("| | |_  _||_  _| | || |   .'    `.   | || ||_   _||_   _|| | | | |_   ___ `.  | || |    |_   _|   | || | |_   ___  |  | |");
        System.out.println("| |   \\ \\  / /   | || |  /  .--.  \\  | || |  | |    | |  | | | |   | |   `. \\ | || |      | |     | || |   | |_  \\_|  | |");
        this.sleep();
        System.out.println("| |    \\ \\/ /    | || |  | |    | |  | || |  | '    ' |  | | | |   | |    | | | || |      | |     | || |   |  _|  _   | |");
        System.out.println("| |    _|  |_    | || |  \\  `--'  /  | || |   \\ `--' /   | | | |  _| |___.' / | || |     _| |_    | || |  _| |___/ |  | |");
        System.out.println("| |   |______|   | || |   `.____.'   | || |    `.__.'    | | | | |________.'  | || |    |_____|   | || | |_________|  | |");
        this.sleep();
        System.out.println("| |              | || |              | || |              | | | |              | || |              | || |              | |");
        System.out.println("| '--------------' || '--------------' || '--------------' | | '--------------' || '--------------' || '--------------' |");
        System.out.println(" '----------------'  '----------------'  '----------------'   '----------------'  '----------------'  '----------------' ");
        this.sleep();
        System.out.println("=========================================================================================================================\n");

        // 엔딩 크레딧 이벤트
        ending_credit(player);

        System.out.println("\n\n게임이 종료되었습니다. ENTER 키를 누르세요.");

        try {
            System.in.read();
        } catch (IOException e) { }

        System.exit(0);
    }
}
