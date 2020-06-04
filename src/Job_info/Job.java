package Job_info;

import Character_info.Character_combatant_player;
import Main.Play_BG_music;
import Skill_info.Skill;

import java.util.Scanner;

public class Job {
    public String job_name; // 직업 이름
    public int job_attack_power; // 직업 추가 공격력
    public int job_defense_power; // 직업 추가 방어력
    public int job_hp; // 직업 추가 체력
    public int job_mp; // 직업 추가 마력
    public Skill job_skill; // 직업 스킬

    // job 클래스 생성자(가장 초기 플레이어의 상태)
    public Job() {
        this.job_name = "무직";

        this.job_hp = 50;
        this.job_mp = 0;

        this.job_attack_power = 10;
        this.job_defense_power = 10;

        this.job_skill = new Skill();
    }

    // 플레이어의 직업에 따라 전직가능한 직업 중 선택하기 메서드
    public Character_combatant_player select_job(Character_combatant_player player) {
        Scanner scan = new Scanner(System.in);

        // 전직 화면 배경음악
        Play_BG_music bgm = new Play_BG_music("change_job_ost.mp3", true);
        bgm.start();

        job_loop:
        while(true) {
            System.out.println("\n[ " + player.name + " ] 님의 직업을 선택해주세요.");
            System.out.println("=====================================================================================================");
            System.out.println(" ※ 한번 선택한 직업은 되돌릴 수 없습니다. 신중하게 선택해주세요.");
            System.out.println(" ※ 원하시는 직업의 이름을 입력해주시면 됩니다.");
            System.out.println("\n ※ 직업에 대한 설명을 원하시면 '직업설명' 이라고 입력해주세요.");
            System.out.println(" ※ 직업을 선택하지 않고, 돌아가시려면 '뒤로가기' 라고 입력해주세요.");
            System.out.println("=====================================================================================================");

            // 플레이어의 직업에 맞는 전직 가능 직업의 리스트를 보여주는 메서드
            show_possible_job(player);

            System.out.println("=====================================================================================================");
            System.out.print(" 입력 : ");
            String job_choose = scan.next();

            try {
                switch (job_choose) {
                    case "뒤로가기":
                        System.out.println("\n직업을 선택하지 않고 뒤로 돌아갑니다.");
                        break job_loop;

                    case "직업설명":
                        System.out.println("\n[ 직업설명 ]을 선택하셨습니다.");

                        // 플레이어가 전직 가능한 직업의 설명을 보여주는 메서드
                        show_job_explain(player);
                        break;

                    // 개발자 테스트용
                    /*
                    case "개발자":
                        player.player_job.job_name = "개발자";
                        System.out.println("\n[ 개발자 ]를 선택하셨습니다.");
                        System.out.println("에러신의 가호를 받습니다.");
                        System.out.println("온몸에 분노와 함께 힘이 차오릅니다.");
                        System.out.println("공격력 +99999, 방어력 +99999, 체력 +99999, 마력 +99999");

                        player.level = 10;
                        player.attack_power += 99999;
                        player.defense_power += 99999;

                        player.now_hp += 99999;
                        player.max_hp += 99999;

                        player.now_mp += 99999;
                        player.max_mp += 99999;

                        player.having_money += 99999999;

                        player.exp = 0;
                        player.max_exp = 0;

                        break job_loop;*/

                    default:
                        // 선택한 직업으로 플레이어의 정보를 갱신하기 위한 메서드(위에서 선택한 직업으로 전직)
                        player = change_player_job(job_choose, player);

                        // 플레이어가 전직에 성공했을 때, 메인으로 돌아가면서 나는 소리
                        Play_BG_music success_change_job = new Play_BG_music("success_change_job.mp3", false);
                        success_change_job.start();

                        System.out.println("\n[ " + job_choose + " (으)로 전직하셨습니다. ]\n");

                        try {
                            Thread.sleep(500); // 0.5초
                        } catch (Exception e) { }
                        //player.get_player_status();

                        break job_loop;
                }
            } catch (Exception e) {
                System.out.println("\n잘못된 입력입니다.");
                System.out.println("다시 선택해주세요.\n");
            }
        }

        // 전직 화면 배경음악 종료
        bgm.close();

        return player;
    }

    // 플레이어 정보를 인자로 받아 선택한 직업의 특성, 스킬 이름 등의 정보 저장하여 반환해주는 메서드(쉽게 말해 전직하기)
    public Character_combatant_player change_player_job (String name, Character_combatant_player player) {
        // 아래의 직업들의 정보를 모두 저장할 Job형 배열
        Job[] job_list = new Job[12];

        // 플레이어의 직업 객체
        //=========================================================
        // 1차 전직 직업
        // 기사 직업 객체 선언 및 초기화
        Job knight = new Job();
        job_list[0] = knight;

        knight.job_name = "기사";

        knight.job_skill.skill_name[0] = "굳건한 의지";
        knight.job_skill.skill_type[0] = "액티브";
        knight.job_skill.required_mp = 30;

        knight.job_hp = 200;
        knight.job_mp = 100;

        knight.job_attack_power = 50;
        knight.job_defense_power = 100;


        // 무사 직업 객체 선언 및 초기화
        Job warrior = new Job();
        job_list[1] = warrior;

        warrior.job_name = "무사";

        warrior.job_skill.skill_name[0] = "무사의 혼";
        warrior.job_skill.skill_type[0] = "액티브";
        warrior.job_skill.required_mp = 0;

        warrior.job_hp = 150;

        warrior.job_attack_power = 150;
        warrior.job_defense_power = 50;


        // 마법사 직업 객체 선언 및 초기화
        Job wizard = new Job();
        job_list[2] = wizard;

        wizard.job_name = "마법사";

        wizard.job_skill.skill_name[0] = "에너지 볼트";
        wizard.job_skill.skill_type[0] = "액티브";
        wizard.job_skill.required_mp = 10;

        wizard.job_skill.skill_name[1] = "쉴드";
        wizard.job_skill.skill_type[1] = "패시브";

        wizard.job_hp = 100;
        wizard.job_mp = 500;

        wizard.job_attack_power = 10;
        wizard.job_defense_power = 10;

        //=========================================================
        //2차 전직 직업
        // 팔라딘 직업 객체 선언 및 초기화
        Job paladin = new Job();
        job_list[3] = paladin;

        paladin.job_name = "팔라딘";

        paladin.job_skill.skill_name[0] = "신성한 오라";
        paladin.job_skill.skill_type[0] = "액티브";
        paladin.job_skill.required_mp = 100;

        paladin.job_hp = 2000;
        paladin.job_mp = 1000;

        paladin.job_attack_power = 200;
        paladin.job_defense_power = 500;

        // 다크나이트 직업 객체 선언 및 초기화
        Job dark_knight = new Job();
        job_list[4] = dark_knight;

        dark_knight.job_name = "다크나이트";

        dark_knight.job_skill.skill_name[0] = "악의 승천";
        dark_knight.job_skill.skill_type[0] = "액티브";
        dark_knight.job_skill.required_mp = 100;

        dark_knight.job_hp = 1500;
        dark_knight.job_mp = 1000;

        dark_knight.job_attack_power = 250;
        dark_knight.job_defense_power = 400;

        // 검사 직업 객체 선언 및 초기화
        Job sword_man = new Job();
        job_list[5] = sword_man;

        sword_man.job_name = "검사";

        sword_man.job_skill.skill_name[0] = "신검합일";
        sword_man.job_skill.skill_type[0] = "액티브";
        sword_man.job_skill.required_mp = 0;

        sword_man.job_hp = 1000;
        sword_man.job_mp = 0;

        sword_man.job_attack_power = 600;
        sword_man.job_defense_power = 200;

        // 광전사 직업 객체 선언 및 초기화
        Job berserker = new Job();
        job_list[6] = berserker;

        berserker.job_name = "광전사";

        berserker.job_skill.skill_name[0] = "분노";
        berserker.job_skill.skill_type[0] = "패시브";
        berserker.job_skill.required_mp = 0;

        berserker.job_hp = 1000;
        berserker.job_mp = 0;

        berserker.job_attack_power = 600;
        berserker.job_defense_power = 200;

        // 화염의 마법사 직업 객체 선언 및 초기화
        Job fire_wizard = new Job();
        job_list[7] = fire_wizard;

        fire_wizard.job_name = "화법사";

        fire_wizard.job_skill.skill_name[0] = "파이어 볼트";
        fire_wizard.job_skill.skill_type[0] = "액티브";
        fire_wizard.job_skill.required_mp = 300;

        fire_wizard.job_skill.skill_name[1] = "파이어 쉴드";
        fire_wizard.job_skill.skill_type[1] = "패시브";

        fire_wizard.job_hp = 400;
        fire_wizard.job_mp = 3000;

        fire_wizard.job_attack_power = 100;
        fire_wizard.job_defense_power = 100;

        // 냉기의 마법사 직업 객체 선언 및 초기화
        Job frost_wizard = new Job();
        job_list[8] = frost_wizard;

        frost_wizard.job_name = "빙법사";

        frost_wizard.job_skill.skill_name[0] = "아이스 볼트";
        frost_wizard.job_skill.skill_type[0] = "액티브";
        frost_wizard.job_skill.required_mp = 300;

        frost_wizard.job_skill.skill_name[1] = "아이스 쉴드";
        frost_wizard.job_skill.skill_type[1] = "패시브";

        frost_wizard.job_hp = 400;
        frost_wizard.job_mp = 3000;

        frost_wizard.job_attack_power = 100;
        frost_wizard.job_defense_power = 100;

        //=========================================================
        // 각성 전직 직업
        // 기사왕 직업 객체 선언 및 초기화
        Job knight_king = new Job();
        job_list[9] = knight_king;

        knight_king.job_name = "기사왕";

        knight_king.job_skill.skill_name[0] = "기사도 정신";
        knight_king.job_skill.skill_type[0] = "액티브";
        knight_king.job_skill.required_mp = 3000;

        knight_king.job_hp = 10000;
        knight_king.job_mp = 5000;

        knight_king.job_attack_power = 2000;
        knight_king.job_defense_power = 4000;

        // 무신 직업 객체 선언 및 초기화
        Job martial_god = new Job();
        job_list[10] = martial_god;

        martial_god.job_name = "무신";

        martial_god.job_skill.skill_name[0] = "심검";
        martial_god.job_skill.skill_type[0] = "액티브";
        martial_god.job_skill.required_mp = 0;

        martial_god.job_hp = 5000;
        martial_god.job_mp = 0;

        martial_god.job_attack_power = 4000;
        martial_god.job_defense_power = 2000;

        // 대마법사 직업 객체 선언 및 초기화
        Job archmage = new Job();
        job_list[11] = archmage;

        archmage.job_name = "대마법사";

        archmage.job_skill.skill_name[0] = "불과 얼음의 노래";
        archmage.job_skill.skill_type[0] = "액티브";
        archmage.job_skill.required_mp = 1000;

        archmage.job_skill.skill_name[1] = "반사";
        archmage.job_skill.skill_type[1] = "패시브";

        archmage.job_hp = 2500;
        archmage.job_mp = 20000;

        archmage.job_attack_power = 800;
        archmage.job_defense_power = 800;


        // 선택한 직업을 job_list에서 찾아서 플레이어의 속성 갱신(전직하기)
        int i;
        for(i = 0; i < 12; i++) {
            if(job_list[i].job_name.equals(name)) {
                break;
            }
        }

        player.player_job = job_list[i];

        player.attack_power += job_list[i].job_attack_power;
        player.defense_power += job_list[i].job_defense_power;

        player.now_hp += job_list[i].job_hp;
        player.max_hp += job_list[i].job_hp;

        player.now_mp += job_list[i].job_mp;
        player.max_mp += job_list[i].job_mp;

        return player;
    }

    // 각 직업별 전직 가능한 직업의 리스트를 보여주는 메서드
    public void show_possible_job(Character_combatant_player player) {
        // 1차 전직 리스트
        if( player.player_job.job_name == "무직" ) {
            System.out.println("- 기사   (공격력 +50, 방어력 +100, 체력 +200, 마력 +100)");
            System.out.println("- 무사   (공격력 +150, 방어력 +50, 체력 +150)");
            System.out.println("- 마법사 (공격력 +10, 방어력 +10, 체력 +50, 마력 +500)");
        }
        // 2차 전직 리스트
        else if( player.player_job.job_name == "기사" ) {
            System.out.println("- 팔라딘 (공격력 +200, 방어력 +500, 체력 +2000, 마력 +1000)");
            System.out.println("- 다크나이트 (공격력 +250, 방어력 +400, 체력 +1500, 마력 +1000)");
        }
        else if( player.player_job.job_name == "무사" ) {
            System.out.println("- 검사 (공격력 +600, 방어력 +200, 체력 +1000)");
            System.out.println("- 광전사 (공격력 +600, 방어력 +200, 체력 +1000)");
        }
        else if( player.player_job.job_name == "마법사" ) {
            System.out.println("- 화법사 (공격력 +100, 방어력 +100, 체력 +400, 마력 +3000)");
            System.out.println("- 빙법사 (공격력 +100, 방어력 +100, 체력 +400, 마력 +3000)");
        }
        // 각성 리스트
        else if( (player.player_job.job_name == "팔라딘") || (player.player_job.job_name == "다크나이트") ) {
            System.out.println("- 기사왕 (공격력 +2000, 방어력 +4000, 체력 +10000, 마력 +5000)");
        }
        else if( (player.player_job.job_name == "검사") || (player.player_job.job_name == "광전사") ) {
            System.out.println("- 무신 (공격력 +5000, 방어력 +2000, 체력 +5000)");
        }
        else if( (player.player_job.job_name == "화법사") || (player.player_job.job_name == "빙법사") ) {
            System.out.println("- 대마법사 (공격력 +800, 방어력 +800, 체력 +2500, 마력 +20000)");
        }

        System.out.println("\n- 직업설명");
        System.out.println("- 뒤로가기");
    }

    // 플레이어가 전직 가능한 직업의 설명을 보여주는 메서드
    public void show_job_explain(Character_combatant_player player) {
        Scanner scan = new Scanner(System.in);


        System.out.println("\n=====================================================================================================");

        // 1차 전직 리스트
        if (player.player_job.job_name == "무직") {
            System.out.println("\n- 기사 : 높은 방어력과 상대적으로 낮은 공격력을 바탕으로 몬스터를 공략합니다.");
            System.out.println("         스킬을 통해 방어력을 더 강화할 수 있습니다.");
            System.out.println("\n- 무사 : 높은 공격력과 상대적으로 낮은 방어력을 바탕으로 몬스터를 공략합니다.");
            System.out.println("         스킬 사용 시, 일정한 확률로 공격력이 더 강화됩니다. ※ 무사는 마력을 사용하지 않습니다.");
            System.out.println("\n- 마법사 : 매우 높은 마력과 낮은 공격력, 방어력을 바탕으로 몬스터를 공략합니다.");
            System.out.println("           스킬을 통해 공격을 하며 공격을 받을 때, 일정 확률로 [ 쉴드 ]가 발동되어 공격을 방어합니다.");
        }

        // 2차 전직 리스트
        else if (player.player_job.job_name == "기사") {
            System.out.println("\n- 팔라딘 : 한층 더 높은 방어력을 바탕으로 몬스터를 공략합니다.");
            System.out.println("           [ 신성한 오라 ] 스킬을 통해 몬스터를 위축시켜 체력을 30% 감소시킵니다.");
            System.out.println("\n- 다크나이트 : 약간의 공격력과 높은 방어력을 바탕으로 몬스터를 공략합니다.");
            System.out.println("              [ 악의 승천 ] 스킬을 통해 몬스터를 공포에 빠뜨려 공격력을 30% 감소시킵니다.");

        }

        else if (player.player_job.job_name == "무사") {
            System.out.println("\n※ 검사와 광전사는 마력을 사용하지 않습니다.");
            System.out.println("\n- 검사 : 검에 대한 이해도가 늘어, 스킬 사용 시, 한번에 두 번 공격합니다.");
            System.out.println("\n- 광전사 : 전투 시, 분노가 차오릅니다. 몬스터에게 공격 받을 때마다 공격력이 상승하지만 방어력은 하락합니다.");
        }

        else if (player.player_job.job_name == "마법사") {
            System.out.println("\n- 화법사 : 화염계 공격에 특화됩니다. 타오르는 화염으로 10% 추가 데미지를 입힙니다.");
            System.out.println("           공격 받을 때, 일정 확률로 발동되는 [ 파이어 쉴드 ]는 공격을 방어하고, 몬스터가 가한 공격의 50% 데미지를 반사합니다.");
            System.out.println("\n- 빙법사 : 빙결계 공격에 특화됩니다. 절대영도의 냉기로 10% 추가 데미지를 입힙니다.");
            System.out.println("           공격 받을 때, 일정 확률로 발동되는 [ 아이스 쉴드 ]는 공격을 방어하고, 다시 일정 확률로 2초동안 몬스터가 공격하지 못하게 합니다");
        }

        // 각성 리스트
        else if ( (player.player_job.job_name == "팔라딘") || (player.player_job.job_name == "다크나이트") ) {
            System.out.println("\n- 기사왕 : 기사의 정점에 올라섭니다. 높은 공격력과 압도적인 방어력으로 몬스터를 공략합니다.");
            System.out.println("           [ 기사도 정신 ] 스킬을 통해 몬스터의 공격력과 체력을 각각 30% 감소시킵니다.");
        }

        else if ( (player.player_job.job_name == "검사") || (player.player_job.job_name == "광전사") ) {
            System.out.println("\n※ 무신을 마력을 사용하지 않습니다.");
            System.out.println("\n- 무신 : 무사의 정점에 올라섭니다. 압도적인 공격력과 높은 방어력으로 몬스터를 공략합니다.");
            System.out.println("         스킬 사용 시, 일정 확률로 [ 심검 ]이 발동되어 몬스터를 처형합니다. (처형당하면 체력과 상관없이 사망합니다.)");
        }

        else if ( (player.player_job.job_name == "화법사") || (player.player_job.job_name == "빙법사") ) {
            System.out.println("\n- 대마법사 : 마법사의 정점에 올라섭니다. 압도적인 마력을 바탕으로 동시에 화염과 냉기 속성 공격을 하여 몬스터를 공략합니다.");
            System.out.println("             화염과 냉기의 속성이 섞어 대량의 추가 데미지를 입힙니다. (위력이 100% 증가합니다.)");
            System.out.println("             공격을 받을 때, 일정 확률로 발동되는 [ 반사 ]는 공격을 방어하고, 몬스터에게 몬스터의 공격력 만큼의 피해를 입힙니다.");
            System.out.println("             [ 반사 ]에 맞은 몬스터는 다시 일정 확률로 5초동안 공격하지 못합니다");
        }

        System.out.println("\n=====================================================================================================\n");
    }
}