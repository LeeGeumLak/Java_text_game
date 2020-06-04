package Map_info;

import Character_info.Character_combatant;
import Character_info.Character_combatant_player;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

import Main.*;

public class Map_dungeon extends Map {

    Character_combatant appear_monster[] = new Character_combatant[3]; // 몬스터 객체 배열

    // 던전 맵 생성 시, 출현하는 몬스터 객체 배열 초기화
    public Map_dungeon(Character_combatant monster_1, Character_combatant monster_2, Character_combatant monster_3) {
        this.appear_monster[0] = monster_1;
        this.appear_monster[1] = monster_2;
        this.appear_monster[2] = monster_3;
    }

    // 플레이어가 던전 안에 있을 때의 이벤트를 위한 메서드
    public void in_dungeon(Character_combatant_player player) {
        Scanner scan = new Scanner(System.in);
        Random monster_random = new Random(System.currentTimeMillis());

        // 몬스터 탐색 선택을 위한 변수
        String search_monster = "1";

        // 던전 입장시 배경음악
        Play_BG_music dungeon_bgm = new Play_BG_music("Dungeon_normal.mp3", true);
        dungeon_bgm.start();

        boolean is_play_again = false;
        // 던전 안에 있을 때의 이벤트
        while(search_monster != "2") {

            //System.out.println("2");
            if(is_play_again) {
                //System.out.println("3");
                dungeon_bgm = new Play_BG_music("Dungeon_normal.mp3", true);
                dungeon_bgm.start();
                //System.out.println("5");
                is_play_again = false;
            }
            //System.out.println("4");
            // 몬스터가 랜덤으로 등장도록 하는 랜덤 변수
            int monster_seed = monster_random.nextInt(3);

            System.out.println("\n=====================================================================================================");
            System.out.println("몬스터를 탐색하시겠습니까? (숫자로 입력해주세요.)");
            System.out.println("1. 예  2. 아니요, 나갈래요");
            System.out.print(" 입력 : ");
            search_monster = scan.next();

            // 선택지 선택시, 클릭 사운드
            Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
            click_sound.start();

            // 선택 후, 이벤트
            switch (search_monster) {
                // 몬스터 탐색 이벤트
                case "1":
                    // 몬스터와 만난 경우, 이벤트 발생
                    System.out.println("\n=====================================================================================================");

                    // 몬스터 등장시, 각 몬스터의 등장 소리
                    Play_BG_music monster_sound = new Play_BG_music(appear_monster[monster_seed].appear_sound, false);
                    monster_sound.start();
                    if(appear_monster[monster_seed].name.equals("슬라임")) {
                        try {
                            Thread.sleep(400); // 0.4초
                        } catch (Exception e) { }

                        monster_sound = new Play_BG_music(appear_monster[monster_seed].appear_sound, false);
                        monster_sound.start();
                    }

                    System.out.println(appear_monster[monster_seed].name + "(" + appear_monster[monster_seed].level + "레벨)" +"이(가) 나타났다!");
                    System.out.println("1. 싸운다  2. 도망간다 (숫자로 입력해주세요.)");
                    System.out.print(" 입력 : ");

                    // 몬스터와 싸울지 말지 선택 번호
                    String fight_or_not = scan.next();

                    // 선택지 선택시, 클릭 사운드
                    click_sound = new Play_BG_music("click_sound.mp3", false);
                    click_sound.start();

                    switch (fight_or_not) {
                        // 몬스터와 싸우는 경우
                        case "1":
                            System.out.println("\n전투가 시작됩니다. 준비하세요!!");
                            Character_combatant fight_monster = appear_monster[monster_seed];

                            dungeon_bgm.close();

                            player = player.player_fight_monster(player, fight_monster);

                            is_play_again = true;

                            break;

                        // 도망가는 경우
                        case "2":
                            System.out.println("\n싸우지 않고 도망갑니다.");

                            break;

                        // 예외 처리
                        default:
                            System.out.println("\n[ 알 수  없는 말을 하며 도망갑니다. ]");
                    }

                    break;

                // 던전 탐색 종료 이벤트
                case "2":
                    System.out.println("\n탐색을 종료하고, 던전에서 나갑니다.");
                    search_monster = "2";

                    break;

                // 예외처리
                default:
                    System.out.println("\n[ 알 수 없는 말을 하며 던전에서 나갑니다. ]\n");
                    search_monster = "2";
            }
        }

        //System.out.println("여기인가1");
        dungeon_bgm.close();
        //System.out.println("여기인가2");
    }
}
