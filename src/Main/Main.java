package Main;
/*

** RPG 게임 **

플레이어는 직업을 선택하고, 몬스터 사냥을 통해 경험치와 돈을 얻을 수 있다.

각 직업마다 주어지는 공격력이나 방어력이 상이하다.
전사 계열
1. 기사 : 공격력은 낮지만 방어력이 높다.
2. 무사 : 방어력은 낮지만 공격력이 높다.
(마법 계열)
(3. 마법사 : 공격력이 매우 높지만 방어력이 매우 낮다.)
(체력은 전사 계열이 마법 계열보다 높다.)

돈을 모아 상인에게 방어구, 무기, 포션을 구입할 수 있으며, 플레이어가 가진 포션을 상인에게 팔 수도 있다.
각 방어구, 무기는 사용가능한 직업과 레벨이 있다.

일정 이상의 경험치가 쌓이면 레벨업이 가능하다.
레벨업시 공격력과 방어력, 체력이 증가하지만, 등장하는 몬스터의 레벨과 체력 공격력 등이 증가한다.
(단, 사냥시 몬스터가 주는 경험치 역시 증가)

맵 이동은 사냥터와 마을 두가지가 가능

마을에서는 상인과 거래를 하거나 일정 금액을 지불하고, 체력을 회복시킬 수 있다.
사냥터로 이동시 몬스터가 등장하며, 전투 종료 후(무찌르거나 도망치거나), 마을로 이동할지 / 계속 싸울건지 선택

플레이어는 몬스터를 만났을 때, 공격하거나 도망갈 수 있다.
공격하면 몬스터와의 전투가 시작되고, 도망가면 체력이 반으로 줄어들면서 전투에서 벗어난다.

게임 클리어 조건은 만렙인 10 레벨을 달성한 상태에서 보스몬스터를 무찌르는 것이다.(프로그램 종료)
게임 플레이 도중 몬스터에게 사망시, 프로그램 종료(로그라이크식)

*/

import Character_info.Character_combatant_player;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 플레이어 객체
        Character_combatant_player player = new Character_combatant_player();

        // 로딩이나 나레이션 효과 같은 여러 쓰레드 효과를 위한 쓰레드
        Thread_effect thread_effect = new Thread_effect();

        // 프로그램 시작시, 오프닝 사운드
        Play_BG_music bgm = new Play_BG_music("Opening_ost.mp3", true);

        // 타이틀 오프닝 이벤트
        thread_effect.title_opening();

        // 로딩 이벤트
        thread_effect.loading();
        System.out.println("로딩 완료!!!!");
        System.out.println("ENTER 를 눌러 게임을 시작하세요.");

        try {
            System.in.read();
        } catch (IOException e) { }

        bgm.start();

        System.out.print("\n플레이어의 이름을 입력하세요 : ");
        player.name = scan.next();


        // 게임에대한 tip -> 쓰레드 이용하여 키보드로 입력하는 듯한 효과
        System.out.println("\n=====================================================================================================");
        thread_effect.keyboard_effect(player.name + " 님 안녕하세요.");
        thread_effect.keyboard_effect("RPG 게임을 시작하겠습니다.");
        System.out.println("=====================================================================================================");
        thread_effect.sleep();

        thread_effect.keyboard_effect("※ TIPS ※");
        System.out.println("=====================================================================================================");
        thread_effect.keyboard_effect("1. 사냥터");
        thread_effect.sleep();
        thread_effect.keyboard_effect_fast("사냥터에서 몬스터를 사냥하면 경험치와 돈을 획득합니다.\n경험치가 쌓이면 레벨업을 하고, 체력/공격력/방어력이 올라갑니다.\n" +
                "사냥터에서 몬스터를 만났을 때, 싸우거나 도망칠 수 있습니다. (단, 전투중에 도망가면 현재 체력의 반이 깎입니다.)" +
                "\n전투 시, 10% 확률로 크리티컬 공격이 발동되며, 크리티컬 공격은 방어력을 무시합니다. (몬스터도 해당)");
        thread_effect.sleep();
        System.out.println();
        thread_effect.keyboard_effect("2. 마을");
        thread_effect.sleep();
        thread_effect.keyboard_effect_fast("마을에서 치유사에게 일정 금액을 지불하고, 체력과 마력을 회복시킬 수 있습니다.\n상인에게 포션을 구매하여 전투중에 체력을 회복시킬 수 있습니다.");
        thread_effect.sleep();
        System.out.println();
        thread_effect.keyboard_effect("3. 보스방");
        thread_effect.sleep();
        thread_effect.keyboard_effect_fast("만렙(10 레벨)이 되어 3차 전직인 각성을 하면, 보스에게 도전할 수 있습니다.\n보스를 무찌르면, 게임에서 이기게 됩니다.");
        thread_effect.sleep();
        System.out.println();
        thread_effect.keyboard_effect("[ ※ 해당 게임은 로그라이크식 게임으로 플레이어가 사망하면 게임이 종료됩니다. 화이팅!!! ]");
        System.out.println("=====================================================================================================");
        System.out.println("준비가 되면, ENTER 를 눌러 게임을 시작하세요.");

        try {
            System.in.read();
        } catch (IOException e) { }

        // 게임 시작시, 실시간으로 시간을 계산 및 표시하기 위한 스레드
        thread_effect.thread_clock.start();

        System.out.println("\n\n\n");
        // 플레이어 이름 입력 후, 실제 게임 시작
        while(true) {
            thread_effect.sleep();

            player.get_player_status(); // 플레이어의 상태창 표시
            boolean is_play_again = true;

            // 원하는 맵으로 이동하는 이벤트, 게임 종료까지 무한루프
            move_map_loop:
            while(true) {

                if( is_play_again == true ) {
                    bgm.close();
                    bgm = new Play_BG_music("Main_ost.mp3", true);
                    bgm.start();
                    is_play_again = false;
                }

                // 메인화면, 각 맵으로 이동 및 상태창 보기 선택지
                System.out.println("=====================================================================================================");
                System.out.println("[ " + player.name +" ] : 어디로 가볼까나...? (숫자로 입력해주세요.)");
                System.out.print("1. 마을   2. 던전   3. 보스방(각성 후, 입장 가능)  4. 상태창 보기  5.게임 종료  ");

                // 현재 직업에 따라 다르게 표시되는 '전직하기' 선택지
                if( player.player_job.job_name.equals("무직") ) {
                    System.out.print("6. 1차 전직하기(4레벨 이상 가능)");
                }
                else if( player.player_job.job_name.equals("기사") || player.player_job.job_name.equals("무사") || player.player_job.job_name.equals("마법사") ) {
                    System.out.print("6. 2차 전직하기(7레벨 이상 가능)");
                }
                else if( player.player_job.job_name.equals("팔라딘") || player.player_job.job_name.equals("다크나이트")
                        || player.player_job.job_name.equals("검사") || player.player_job.job_name.equals("광전사")
                        || player.player_job.job_name.equals("화법사")|| player.player_job.job_name.equals("빙법사") ) {

                    System.out.print("6. 각성하기(만렙(10레벨)만 가능)");
                }

                System.out.print("\n 입력 : ");
                String map_choose = scan.next();

                // 각 선택에 따른 이벤트
                try { // 게임 전체에 try-catch 예외처리, 각 맵에서 예외 발생시 처음으로 돌아오도록

                    // 선택지 선택시, 클릭 사운드
                    Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
                    click_sound.start();

                    switch (map_choose) {
                        case "1": // 마을 입장 이벤트
                        case "마을":
                            System.out.println("\n마을에 입장합니다.\n");

                            bgm.close();
                            // player_move 메서드를 통한 맵 이동
                            player.player_move("마을", player);
                            is_play_again = true;
                            break;


                        case "2": // 던전 입장 이벤트
                        case "사냥터":
                            System.out.println("\n던전에 입장합니다.\n");

                            boolean first_loop = true;
                            // 던전 선택 화면 배경음악
                            Play_BG_music dungeon_bgm = new Play_BG_music("Dungeon_choice.mp3", true);

                            // 입장하고자 하는 던전 선택 이벤트
                            dungeon_loop:
                            while (true) {
                                if(first_loop) {
                                    dungeon_bgm = new Play_BG_music("Dungeon_choice.mp3", true);
                                    bgm.close();
                                    dungeon_bgm.start();
                                    first_loop = false;
                                }

                                System.out.println("\n가고자 하는 던전의 숫자를 선택해주세요.");
                                System.out.println("=====================================================================================================");
                                System.out.println("1. [ 초보자 ]의 사냥터 - 권장레벨(1 ~ 3)");
                                System.out.println("2. [ 숙련자 ]의 사냥터 - 권장레벨(4 ~ 6)");
                                System.out.println("3. [ 전문가 ]의 사냥터 - 권장레벨(7 ~ 9)");
                                System.out.println("4. 나가기");
                                System.out.println("=====================================================================================================");
                                System.out.print(" 입력 : ");
                                String dungeon_choose = scan.next(); // 선택한 입장 던전 번호

                                int is_enter_dungeon; // '선택한 던전 입장할지, 말지' 선택지

                                // 선택지 선택시, 클릭 사운드
                                click_sound = new Play_BG_music("click_sound.mp3", false);
                                click_sound.start();

                                switch (dungeon_choose) {
                                    case "1": // 초보자 던전 선택 이벤트
                                        System.out.println("\n[ 초보자 ]의 사냥터로 이동합니다.\n");

                                        dungeon_bgm.close();
                                        // player_move 메서드를 통한 초보자 던전 입장
                                        player.player_move("[ 초보자 ]의 사냥터", player);

                                        first_loop = true;

                                        break;

                                    case "2": // 숙련자 던전 선택 이벤트
                                        // 던전 입장 전, 플레이어의 레벨에 따른 경고 문구

                                        if (player.level < 4) {
                                            System.out.println("\n경고!!!! 플레이어의 레벨이 권장레벨보다 낮습니다.");
                                            System.out.println("던전 입장 시, 사망하면 모든 것을 잃습니다. 입장하시겠습니까?");
                                            System.out.println("1. 예  2. 아니요  (숫자로 입력해주세요.)");
                                            System.out.print("입력 : ");
                                            is_enter_dungeon = scan.nextInt();

                                            // 선택지 선택시, 클릭 사운드
                                            click_sound = new Play_BG_music("click_sound.mp3", false);
                                            click_sound.start();

                                            if (is_enter_dungeon == 1) { // 경고 후, 입장
                                                System.out.println("\n[ 숙련자 ]의 사냥터로 이동합니다.\n");

                                                dungeon_bgm.close();
                                                // player_move 메서드를 통한 숙련자 던전 입장
                                                player.player_move("[ 숙련자 ]의 사냥터", player);

                                                first_loop = true;
                                            }
                                            else if (is_enter_dungeon == 2) { // 경고 후, 입장하지 않음
                                                System.out.println("\n위험한 기운을 감지하고, 뒤로 물러섭니다.");
                                            }
                                            else { // 예외처리
                                                System.out.println("\n[ 알 수 없는 말을 하며, 뒤로 물러선다. ]");
                                            }

                                        }
                                        else { // 경고 문구 뜨지 않는 이벤트
                                            System.out.println("\n[ 숙련자 ]의 사냥터로 이동합니다.\n");

                                            dungeon_bgm.close();
                                            // player_move 메서드를 통한 숙련자 던전 입장
                                            player.player_move("[ 숙련자 ]의 사냥터", player);

                                            first_loop = true;
                                        }

                                        break;

                                    case "3":
                                        // 던전 입장 전, 플레이어의 레벨에 따른 경고 문구
                                        if (player.level < 7) {
                                            System.out.println("\n경고!!!! 플레이어의 레벨이 권장레벨보다 낮습니다.");
                                            System.out.println("던전 입장 시, 사망하면 모든 것을 잃습니다. 입장하시겠습니까?");
                                            System.out.println("1. 예  2. 아니요  (숫자로 입력해주세요.)");
                                            System.out.print("입력 : ");
                                            is_enter_dungeon = scan.nextInt();

                                            // 선택지 선택시, 클릭 사운드
                                            click_sound = new Play_BG_music("click_sound.mp3", false);
                                            click_sound.start();

                                            if (is_enter_dungeon == 1) { // 경고 후, 입장
                                                System.out.println("\n[ 전문가 ]의 사냥터로 이동합니다.\n");

                                                dungeon_bgm.close();
                                                // player_move 메서드를 통한 전문가 던전 입장
                                                player.player_move("[ 전문가 ]의 사냥터", player);

                                                first_loop = true;
                                            }
                                            else if (is_enter_dungeon == 2) { // 경고 후, 입장하지 않음
                                                System.out.println("\n위험한 기운을 감지하고, 뒤로 물러섭니다.");
                                            }
                                            else { // 예외처리
                                                System.out.println("\n[ 알 수 없는 말을 하며, 뒤로 물러선다. ]");
                                            }

                                        }
                                        else { // 경고 문구 뜨지 않는 이벤트
                                            System.out.println("\n[ 전문가 ]의 사냥터로 이동합니다.\n");

                                            dungeon_bgm.close();
                                            // player_move 메서드를 통한 전문가 던전 입장
                                            player.player_move("[ 전문가 ]의 사냥터", player);

                                            first_loop = true;
                                        }

                                        break;

                                    case "4": // 입장할 던전 선택하지 않고, 메인 화면으로 이동
                                        System.out.println("\n사냥터에서 나갑니다.\n\n");

                                        dungeon_bgm.close();
                                        is_play_again = true;

                                        break dungeon_loop;

                                    default: // 맵 선택 시, 예외처리
                                        System.out.println("\n잘못된 입력입니다.");
                                        System.out.println("숫자를 가능 범위내에서 다시 입력해주세요.\n");
                                }
                            }

                            break;

                        case "3":
                        case "보스방":
                            // 플레이어의 레벨에 따른 보스방 입장 이벤트
                            if ( (player.player_job.job_name.equals("기사왕")) || (player.player_job.job_name.equals("무신")) || (player.player_job.job_name.equals("대마법사")) ) {
                                System.out.println("\n[ 보스방 ]으로 이동합니다.\n");

                                bgm.close();
                                // player_move 메서드를 통한 보스방 입장
                                player.player_move("[ 보스방 ]", player);
                                is_play_again = true;
                            }
                            else {
                                System.out.println("\n각성을 해야만 입장 가능합니다.");
                                System.out.println("각성하고 다시 오세요.\n");
                            }

                            break;

                        case "4":
                        case "상태창":
                        case "상태창 보기":
                            System.out.println("\n상태창 보기를 선택하셨습니다.\n");
                            player.get_player_status(); // 플레이어의 '상태창 보기' 메서드
                            System.out.println();

                            break;

                        case "5":
                        case "종료":
                        case "게임 종료":
                            System.out.println("\n게임을 종료합니다.");
                            System.exit(0);

                        case "6":
                        case "전직":
                        case "전직하기":
                            // 플레이어의 직업과 레벨에 따른 전직 이벤트
                            // 플레이어의 직업이 무직인 경우
                            if( player.player_job.job_name.equals("무직") ) {
                                if( player.level < 4) {
                                    System.out.println("\n아직 전직하실 수 없습니다. 강해져서 돌아오세요.\n");
                                }
                                else {
                                    System.out.println("\n1차 전직 합니다.\n");

                                    bgm.close();
                                    // 직업 선택하기 메서드
                                    player = player.player_job.select_job(player);
                                    is_play_again = true;
                                }
                            }
                            // 플레이어의 직업이 기사/무사 인 경우
                            else if( player.player_job.job_name.equals("기사") || player.player_job.job_name.equals("무사") || player.player_job.job_name.equals("마법사") ) {
                                if( player.level < 7) {
                                    System.out.println("\n아직 2차 전직하실 수 없습니다. 강해져서 돌아오세요.\n");
                                }
                                else {
                                    System.out.println("\n2차 전직 합니다.\n");

                                    bgm.close();
                                    // 직업 선택하기 메서드
                                    player = player.player_job.select_job(player);
                                    is_play_again = true;
                                }
                            }
                            // 플레이어의 직업이 백기사/흑기사, 검사/광전사, 화염/냉기의 마법사 인 경우
                            else if( player.player_job.job_name.equals("팔라딘") || player.player_job.job_name.equals("다크나이트")
                                    || player.player_job.job_name.equals("검사") || player.player_job.job_name.equals("광전사")
                                    || player.player_job.job_name.equals("화법사")|| player.player_job.job_name.equals("빙법사") ) {

                                // 각성이 가능한 경우 (플레이어의 레벨이 10인 경우)
                                if( player.level == 10 ) {
                                    System.out.println("\n만렙(10레벨)을 달성하면 각성할 수 있습니다. 각성하시겠습니까?");
                                    System.out.println("1. 예  2. 아니요 (숫자로 입력해주세요.)");
                                    System.out.print(" 입력 : ");
                                    int do_or_not = scan.nextInt(); // 선택한 각성 할지/말지

                                    // 선택지 선택시, 클릭 사운드
                                    click_sound = new Play_BG_music("click_sound.mp3", false);
                                    click_sound.start();

                                    // 각성하기로 선택한 경우
                                    if(do_or_not == 1) {
                                        System.out.println("\n각성 합니다.\n");

                                        bgm.close();
                                        // 직업 선택하기 메서드
                                        player = player.player_job.select_job(player);
                                        is_play_again = true;
                                    }
                                    // 각성하지 않기로 한 경우
                                    else if(do_or_not == 2) {
                                        System.out.println("\n각성하지 않고, 돌아갑니다.\n");
                                    }
                                }
                                // 각성이 불가능한 경우 (플레이어의 레벨이 10 미만인 경우)
                                else {
                                    System.out.println("\n아직 각성하실 수 없습니다. 만렙(10레벨)만 가능합니다.\n");
                                }
                            }
                            else {
                                System.out.println("\n더 이상 전직하실 수 없습니다.\n");
                            }
                            System.out.println("");

                            break;


                        // 치트 (시연을 위한)
                        case "돈":
                            player.having_money += 10000; // 플레이어의 소지금 증가
                            break;

                        case "레벨":
                            player.player_level_up(player); // 플레이어의 레벨 증가
                            break;

                        case "시연":
                            for(int i = 0; i < 9; i++) {
                                player.player_level_up(player);
                            }
                            player.having_money += 1000000;
                            break;
                        //

                        default: // 맵 선택 예외 처리
                            // 잘 못 선택시, 에러 사운드
                            Play_BG_music error_sound = new Play_BG_music("error_sound.mp3", false);
                            error_sound.start();

                            System.out.println("\n잘못된 입력입니다.");
                            System.out.println("다시 선택해주세요.\n");
                    }
                } catch (Exception e) { // 게임 전체 try-catch 예외 처리, 각 맵에서 예외 발생시 처음으로 돌아오도록
                    // 잘 못 선택시, 에러 사운드
                    Play_BG_music error_sound = new Play_BG_music("error_sound.mp3", false);
                    error_sound.start();

                    System.out.println("\n잘못된 입력입니다.");
                    System.out.println("다시 선택해주세요.\n");

                    bgm.close();
                    is_play_again = true;
                }
            }
        }
    }
}