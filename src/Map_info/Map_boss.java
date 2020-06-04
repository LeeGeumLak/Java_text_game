package Map_info;

import Character_info.Character_combatant_player;
import Character_info.Character_combatant;

import java.util.Scanner;

import Main.*;

public class Map_boss extends Map {
    Character_combatant boss_monster; // 보스 몬스터 객체

    // 보스 몬스터 생성자
    public Map_boss(Character_combatant boss_monster) {
        this.boss_monster = boss_monster;
    }

    // 플레이어가 보스방 맵에 있을 때의 이벤트를 위한 메서드
    public void in_boss(Character_combatant_player player) {
        Scanner scan = new Scanner(System.in);

        // 보스방 입장시, 배경음악
        Play_BG_music boss_bgm = new Play_BG_music("Dungeon_boss.mp3", true);
        boss_bgm.start();

        System.out.println("\n두둥!!!!!");
        System.out.println("[ 보스방 ]에 입장하셨습니다.");
        System.out.println("보스 몬스터와의 결전을 치룰 준비가 되셨습니까? (사망 시, 모든 것을 잃게 됩니다.)");
        System.out.println("1. 예  2. 아니요  (숫자로 입력해주세요.)");

        // 보스 몬스터와 싸울지 말지 선택 번호
        String boss_fight_or_not = scan.next();

        // 선택지 선택시, 클릭 사운드
        Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
        click_sound.start();

        // 싸운다고 선택한 경우
        if(boss_fight_or_not.equals("1")) {
            System.out.println("\n보스 몬스터와의 결전이 시작됩니다. 준비하세요!!");

            // 나레이션 효과한 글자씩 입력되는)
            Thread_effect narration_effect = new Thread_effect();
            Play_BG_music devil_laugh = new Play_BG_music(boss_monster.appear_sound, false);
            devil_laugh.start();

            System.out.print("[ 마왕 ] : ");
            narration_effect.keyboard_effect("하하하하하!!! 죽을 자리를 제 발로 찾아왔구나!!!!!");
            System.out.print("\n[ " + player.name + " ] : ");
            narration_effect.keyboard_effect("너를 무찌르고 이 게임을 끝내겠다!!!!!");

            System.out.println("\n[ ※ 마왕은 50% 확률로 두 번 공격합니다. 참고하세요! ]");

            // 보스 싸움 이벤트
            Character_combatant fight_monster = boss_monster;

            boss_bgm.close();

            player = player.player_fight_monster(player, fight_monster);
        }
        // 보스와 싸우지 않는다는 선택을 한 경우
        else if(boss_fight_or_not.equals("2")){
            System.out.println("\n보스를 뒤로하고 잠시 물러납니다.\n");
        }
        // 예외처리
        else {
            System.out.println("\n[ 알 수 없는 말을 하면서, 뒤로 물러난다. ]\n");
        }

        boss_bgm.close();
    }
}
