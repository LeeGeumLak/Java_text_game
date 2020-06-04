package Character_info;

import Job_info.Job;
import Main.Play_BG_music;
import Main.Thread_clock;
import Main.Thread_effect;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Character_combatant extends Character {
    public int level; // 전투원의 레벨

    public int now_hp; // 전투원의 현재 체력
    public int max_hp; // 전투원의 최대 체력

    public int now_mp; // 전투원의 현재 마력

    public int max_mp; // 전투원의 최대 마력

    public int attack_power; // 전투원의 공격력

    public int exp; // 전투원의 보유 경험치

    public String appear_sound; // 전투원(몬스터)의 소리

    // 전투원 객체 생성시, 초기화를 위한 생성자
    public Character_combatant() {
        this.having_money = 0;
        this.level = 0;

        this.now_hp = 0;
        this.max_hp = 0;

        this.now_mp = 0;
        this.max_mp = 0;

        this.exp = 0;
        this.attack_power = 0;
    }

    // 전투원 객체 생성시, 초기화를 위한 생성자 (객체의 구체적인 정보를 위해 인자를 받음)
    public Character_combatant(String name, int level, int hp, int attack_power, int exp, int money, String sound) {
        this.name = name;
        this.level = level;
        this.having_money = money;

        this.now_hp = hp;
        this.max_hp = hp;

        this.attack_power = attack_power;
        this.exp = exp;

        this.appear_sound = sound;
    }

    // 전투원의 최대 마력 getter
    public int getMax_mp() {
        return max_mp;
    }

    // 전투원의 최대 마력 setter
    public void setMax_mp(int max_mp) {
        this.max_mp = max_mp;
    }

    // 전투원의 현재 마력 getter
    public int getNow_mp() {
        return now_mp;
    }

    // 전투원의 현재 마력 setter
    public void setNow_mp(int now_mp) {
        this.now_mp = now_mp;
    }

    // 전투원의 현재 체력 getter
    public int getNow_hp() {
        return now_hp;
    }

    // 전투원의 현재 체력 setter
    public void setNow_hp(int now_hp) {
        this.now_hp = now_hp;
    }

    // 전투원의 공격력 getter
    public int getAttack_power() {
        return attack_power;
    }

    // 전투원의 공격력 setter
    public void setAttack_power(int attack_power) {
        this.attack_power = attack_power;
    }

    // 플레이어와 몬스터 간에 전투하는 이벤트
    public Character_combatant_player player_fight_monster(Character_combatant_player player, Character_combatant monster) {
        Thread_effect thread_effect = new Thread_effect();

        Scanner scan = new Scanner(System.in);

        int player_original_hp; // 전투 시, 플레이어의 체력이 깎이거나/회복되었을 때, 변화 전의 체력 의미
        int player_original_op = player.attack_power; // 전투 시, 플레이어의 공격력에 변화가 있을 때, 변화 전의 공격력 의미 (전투 종료 시, 다시 원상복구)
        int player_original_dp = player.defense_power; // 전투 시, 플레이어의 방어력에 변화가 있을 때, 변화 전의 방어력 의미 (전투 종료 시, 다시 원상복구)

        int monster_original_hp = monster.now_hp; // 전투 중인 몬스터의 체력 변화를 위한 변수
        int monster_original_attack_power = monster.attack_power; // 전투 중인 몬스터의 공격력 변화를 위한 변수

        boolean first_loop = true;
        Play_BG_music fight_bgm;

        // 보스 몬스터 혹은 일반 몬스터와의 전투 시, 배경 음악
        if(monster.name.equals("마왕")) {
            fight_bgm = new Play_BG_music("battleThemeA.mp3", true);

        }
        else {
            fight_bgm = new Play_BG_music("fight_bg_music.mp3", true);
        }

        // monster_attack_player 메서드를 통한 몬스터의 공격 이벤트 발생
        Thread monster_attact_event = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 몬스터 혹은 플레이어의 체력이 0 이 되기 전까지 반복
                    while ((monster.getNow_hp() >= 0) || (player.getNow_hp() >= 0)) {

                        // 중간에 몬스터 사망시 예외 처리
                        if(monster.getNow_hp() > 0) {
                            Thread.sleep(5000); // 5초

                            //monster_attack_player 메서드를 통한 몬스터 공격 이벤트
                            player.setNow_hp(monster.monster_attack_player(monster, player));

                            // 전투 과정 몬스터의 체력이 음수로 되면 0으로 만들어줌 (아래 상태창 보여줄 때의 예외처리)
                            if( monster.getNow_hp() <= 0 ) {
                                monster.setNow_hp(0);
                            }
                            // 전투 과정 플레이어의 체력이 음수로 되면 0으로 만들어줌 (아래 상태창 보여줄 때의 예외처리)
                            else if( player.getNow_hp() <= 0 ) {
                                player.setNow_hp(0);
                            }


                            System.out.println("=====================================================================================================");
                            System.out.println("ㅡㅡㅡ" + player.name + "ㅡㅡㅡ");
                            System.out.println("레벨 : " + player.level);
                            System.out.println("직업 : " + player.player_job.job_name);
                            System.out.println("경험치 : " + player.exp + " / " + player.max_exp);
                            System.out.println("HP : " + player.now_hp + " / " + player.max_hp);
                            System.out.println("MP : " + player.now_mp + " / " + player.max_mp);

                            if(player_original_op == player.attack_power) {
                                System.out.println("공격력 : " + player.attack_power + "(+" + player.wearing_weapon.attack_power + ")");
                            } else {
                                System.out.println("공격력 : " + player_original_op + "(+" + player.wearing_weapon.attack_power + ")" +
                                        " --> " + player.attack_power + "(+" + player.wearing_weapon.attack_power + ")" + " ( " + player.player_job.job_skill.skill_name[0] + " )");
                            }


                            if(player_original_dp == player.defense_power) {
                                System.out.println("방어력 : " + player.defense_power + "(+" + player.wearing_armor.defense_power + ")");
                            } else {
                                System.out.println("방어력 : " + player_original_dp + "(+" + player.wearing_armor.defense_power + ")" +
                                        " --> " + player.defense_power + "(+" + player.wearing_armor.defense_power + ")" + " ( " + player.player_job.job_skill.skill_name[0] + " )");
                            }

                            System.out.println("보유 스킬 : " + player.player_job.job_skill.skill_name[0] + " . " + player.player_job.job_skill.skill_name[1]);

                            System.out.println("\nㅡㅡㅡ" + monster.name + "ㅡㅡㅡ");
                            System.out.println("레벨 : " + monster.level);
                            System.out.println("보유 경험치 : " + monster.exp);
                            System.out.println("HP : " + monster.now_hp + " / " + monster.max_hp);

                            if (monster_original_attack_power == monster.attack_power) {
                                System.out.println("공격력 : " + monster.attack_power);
                            } else {
                                System.out.println("공격력 : " + monster_original_attack_power + " --> " + monster.attack_power + "( " + player.player_job.job_skill.skill_name[0] + " )");
                            }

                            System.out.println("=====================================================================================================");

                            // 플레이어의 행동 이벤트
                            System.out.println("\n행동을 고르세요. (숫자로 입력해주세요.) [ ※ 몬스터는 5초마다 공격합니다. ]");
                            System.out.println("1. 일반 공격  2. 스킬 공격  3. 포션 먹기  4. 도망가기");
                            System.out.print(" 입력 : ");
                        }

                        // 전투 중, 몬스터의 체력이 0이 된 경우
                        if(monster.getNow_hp() == 0) {
                            System.out.println("\n몬스터의 체력이 0이 되어, 행동불능 상태가 되었습니다.");
                            System.out.println("공격하여, 몬스터를 처지하세요\n");
                            break;
                        }
                        // 전투 중, 플레이어가 사망한 경우
                        else if(player.getNow_hp() <= 0) {
                            fight_bgm.close();
                            thread_effect.player_dead(player);
                        }
                    }
                } catch(Exception e) { }
            }
        });
        // 몬스터 공격 쓰레드 시작
        monster_attact_event.setDaemon(true);
        monster_attact_event.start();

        // 플레이어 전투 이벤트 반복문
        fight_loop:
        while (true) {
            if (first_loop) {
                fight_bgm.start();
                first_loop = false;
            }
            System.out.println("=====================================================================================================");
            System.out.println("ㅡㅡㅡ" + player.name + "ㅡㅡㅡ");
            System.out.println("레벨 : " + player.level);
            System.out.println("직업 : " + player.player_job.job_name);
            System.out.println("경험치 : " + player.exp + " / " + player.max_exp);
            System.out.println("HP : " + player.now_hp + " / " + player.max_hp);
            System.out.println("MP : " + player.now_mp + " / " + player.max_mp);

            if(player_original_op == player.attack_power) {
                System.out.println("공격력 : " + player.attack_power + "(+" + player.wearing_weapon.attack_power + ")");
            } else {
                System.out.println("공격력 : " + player_original_op + "(+" + player.wearing_weapon.attack_power + ")" +
                        " --> " + player.attack_power + "(+" + player.wearing_weapon.attack_power + ")" + " ( " + player.player_job.job_skill.skill_name[0] + " )");
            }


            if(player_original_dp == player.defense_power) {
                System.out.println("방어력 : " + player.defense_power + "(+" + player.wearing_armor.defense_power + ")");
            } else {
                System.out.println("방어력 : " + player_original_dp + "(+" + player.wearing_armor.defense_power + ")" +
                        " --> " + player.defense_power + "(+" + player.wearing_armor.defense_power + ")" + " ( " + player.player_job.job_skill.skill_name[0] + " )");
            }

            System.out.println("보유 스킬 : " + player.player_job.job_skill.skill_name[0] + " . " + player.player_job.job_skill.skill_name[1]);

            System.out.println("\nㅡㅡㅡ" + monster.name + "ㅡㅡㅡ");
            System.out.println("레벨 : " + monster.level);
            System.out.println("보유 경험치 : " + monster.exp);
            System.out.println("HP : " + monster.now_hp + " / " + monster.max_hp);

            if (monster_original_attack_power == monster.attack_power) {
                System.out.println("공격력 : " + monster.attack_power);
            } else {
                System.out.println("공격력 : " + monster_original_attack_power + " --> " + monster.attack_power + "( " + player.player_job.job_skill.skill_name[0] + " )");
            }

            System.out.println("=====================================================================================================");

            // 플레이어의 행동 이벤트
            System.out.println("\n행동을 고르세요. (숫자로 입력해주세요.) [ ※ 몬스터는 5초마다 공격합니다. ]");
            System.out.println("1. 일반 공격  2. 스킬 공격  3. 포션 먹기  4. 도망가기");
            System.out.print(" 입력 : ");
            int fight_action = scan.nextInt(); // 전투 중, 행동 선택 번호

            System.out.println("=====================================================================================================");

            // 플레이어 선택지 선택 시, 클릭 사운드
            Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
            click_sound.start();

            // 선택에 따른 행동 이벤트
            switch (fight_action) {
                case 1: // 일반 공격 선택한 경우
                    System.out.println("[ " + player.name + " ]님의 공격!!!");

                    // player_normal_attack_monster 메서드를 통한 공격 이벤트
                    monster.setNow_hp(player.player_normal_attack_monster(player, monster));

                    break;

                case 2: // (액티브)스킬 공격 선택한 경우

                    // player_skill_attack_monster 메서드를 통한 공격 이벤트
                    // 스킬 사용이 가능할 때,
                    if (player.now_mp >= player.player_job.job_skill.required_mp) {
                        //monster.setNow_hp(player.player_skill_attack_monster(player, monster));
                        player.player_skill_attack_monster(player, monster);
                    }
                    // 스킬 사용이 불가능할 때,
                    else {
                        System.out.println("마력이 부족합니다!!! 스킬을 사용할 수 없습니다.");

                        continue fight_loop;
                    }


                    break;

                case 3: // 포션먹기 선택한 경우
                    boolean drink_potion;
                    System.out.println("\n포션을 마십니다.");

                    // 포션 먹기 선택 시, 몬스터 자동 공격 쓰레드 잠시 일시정지
                    try {
                        monster_attact_event.wait();
                    }catch (Exception e) { }

                    // player_drink_potion 메서드를 통한 포션먹기 이벤트
                    player.player_drink_portion(player);

                    // 몬스터 자동 공격 쓰레드 다시 시작
                    notify();

                    break;

                case 4: // 도망가기 선택한 경우
                    // 도망가기 선택시 몬스터 자동 공격 쓰레드 잠시 일시정지
                    try {
                        monster_attact_event.wait();
                    }catch (Exception e) { }

                    if(monster.name.equals("마왕")) {
                        System.out.println("\n보스 몬스터에게서 도망가면 캐릭터가 초기화 됩니다. 도망가시겠습니까?");
                        System.out.println("(신중하게 선택하세요! 잘 못 입력 시, 모든 정보를 잃습니다.)");
                        System.out.println("1. 도망갈래ㅠㅠ  2. 어떻게 키운건데, 그럴 순 없지!  (숫자로 입력해주세요.)");
                        System.out.print(" 입력 : ");

                        // 도망갈지 말지 선택 번호
                        int run_or_not = scan.nextInt();

                        // 도망갈질 말지 선택 시, 클릭 사운드
                        click_sound = new Play_BG_music("click_sound.mp3", false);
                        click_sound.start();

                        // 도망간다고 선택한 경우
                        if(run_or_not == 1) {
                            monster_attact_event.interrupt();

                            Thread_effect narration_effect = new Thread_effect();
                            System.out.print("\n[ 마왕 ] : ");
                            narration_effect.keyboard_effect("감히 도망을 쳐? 저주를 내려주마!!!!");

                            // 보스 몬스터 사운드
                            Play_BG_music devil_laugh = new Play_BG_music("devil_laugh.mp3", false);
                            devil_laugh.start();

                            try {
                                Thread.sleep(1000); // 1초
                            } catch (Exception e) { }
                            narration_effect.keyboard_effect("\n[ 캐릭터의 정보가 초기화됩니다. ]");

                            player.having_money = 0;
                            player.exp = 0;
                            player.max_exp = 10;
                            player.level = 1;
                            player.now_hp = 100;
                            player.max_hp = 100;
                            player.attack_power = 10;
                            player.defense_power = 0;

                            Job initial_job = new Job();
                            player.player_job = initial_job;

                            System.out.println("\n모든 것을 잃은 채 보스 몬스터에게서 도망갑니다.");

                            fight_bgm.close();
                            monster_attact_event.interrupt();

                            break fight_loop;
                        }
                        // 도망가지 않는다고 선택한 경우
                        else if(run_or_not == 2) {
                            System.out.println("\n다시 보스와의 결전으로 돌아갑니다.");

                        }
                        else { // 예외처리
                            System.out.println("\n.....");
                            System.out.println("[ 기절해서 도망가지 못합니다. 다시 일어납니다. ]");
                        }

                        // 일시 정지 했던, 몬스터 자동공격 쓰레드 다시 시작
                        notify();
                        break;
                    }
                    else {
                        System.out.println("\n싸우다 말고 도망갑니다.");
                        System.out.println("현재 체력의 절반이 깎입니다. (단, 체력이 1 미만으로 내려가지 않습니다.)");
                        player_original_hp = player.now_hp;
                        player.now_hp = player.now_hp / 2;

                        // 반으로 깎인 플레이어의 체력이 0이 될 경우
                        if (player.now_hp == 0) {
                            player.now_hp = 1;
                        }
                        System.out.println("체력 : " + player_original_hp + " --> " + player.now_hp);

                        fight_bgm.close();

                        monster_attact_event.interrupt();
                        monster_attact_event.interrupt();

                        break fight_loop;
                    }

                default: // 예외처리
                    System.out.println("\n[ 하라는 공격은 안하고, 알 수 없는 행동을 한다. ]");
            }

            // 몬스터가 죽은 경우
            if (monster.getNow_hp() <= 0) {
                monster_attact_event.interrupt();

                if(monster.name.equals("마왕")) {
                    // 보스 몬스터를 무찌른 경우, 게임 클리어
                    fight_bgm.close();
                    thread_effect.game_clear(player, monster);
                }
                else {
                    // 일반 몬스터를 무찌른 경우, 경험치 및 돈 계산, 몬스터 정보 초기화
                    monster_attact_event.interrupt();

                    // 몬스터 사망시, 사운드
                    Play_BG_music monster_die = new Play_BG_music("monster_die.mp3", false);
                    monster_die.start();

                    System.out.println("\n[ " + monster.name + " ]이(가) 쓰러졌습니다.");

                    // 플레이어 레벨이 만렙(10레벨)인 경우
                    if (player.level == 10) {
                        System.out.println("\n이미 만렙(10레벨)이기에 경험치가 상승하지 않습니다.");
                    }
                    // 만렙이 아닌 경우
                    else {
                        try {
                            Thread.sleep(200); // 0.2초

                            // 경험치 오르는 사운드
                            Play_BG_music gain_exp_sound = new Play_BG_music("chipquest.mp3", false);
                            gain_exp_sound.start();

                        } catch (Exception e) { }
                        System.out.println("\n경험치가 " + monster.exp + " 상승합니다.");
                        player.exp += monster.exp; // 경험치 계산

                        System.out.println(monster.having_money + "원을 획득합니다.");
                        player.having_money += monster.having_money; // 소지금 계산

                        // 현재 경험치를 계산하여, 레벨업 이벤트 발생
                        while (player.exp >= player.max_exp) {
                            // 10레벨인 경우, 반복문 빠져나감
                            if (player.level == 10) {
                                break;
                            }
                            // 레벨업 조건을 만족하는 경우, plyaer_level_up 메서드를 통한 이벤트 발생
                            player.player_level_up(player);
                        }
                    }

                    fight_bgm.close();

                    //System.out.println("1");
                    break fight_loop;
                }
            }

            // 플레이어 케릭터가 사망한 경우 (프로그램 종료)
            if (player.getNow_hp() <= 0) {
                monster_attact_event.interrupt();

                fight_bgm.close();
                thread_effect.player_dead(player);
            }
        }

        // 전투가 끝난 경우, 플레이어의 공격력 및 방어력 원상복구 (다음 전투를 위함)
        player.setAttack_power(player_original_op);
        player.setDefense_power(player_original_dp);

        // 전투가 끝난 경우, 몬스터 객체의 체력과 공격력 원상복구 (다음 전투를 위함)
        monster.setNow_hp(monster_original_hp);
        monster.setAttack_power(monster_original_attack_power);

        return player;
    }

    // 전투 시, 몬스터가 플레이어에게 공격하는 이벤트
    public int monster_attack_player(Character_combatant monster, Character_combatant_player player) {

        // 중간에 몬스터 혹은 플레이어 사망시, 예외 처리
        if(monster.getNow_hp() <= 0 || player.getNow_hp() <= 0) {
            return player.now_hp;
        }

        Random random_chance = new Random(System.currentTimeMillis());

        System.out.println("\n=====================================================================================================");
        System.out.println("[ " + monster.name + "(" + monster.level + "레벨) ]의 공격!!!");

        // 마법사 계열인 경우, 30% 확률로 패시브 스킬인 쉴드 발동 / 발동되지 않는다면 정상 진행
        if( !player.player_job.job_skill.passive_skill_use(player, monster) ) {

            // 크리티컬 확률 10% 를 위한 조건문
            // 크리티컬 공격을 하는 경우 (방어력 무시)
            if (random_chance.nextInt(10) < 1) {
                // 크리티컬 공격 시, 사운드
                Play_BG_music critical_sound = new Play_BG_music("critical_attack.mp3", false);
                critical_sound.start();

                try {
                    Thread.sleep(100); // 0.1초
                } catch (Exception e) { }

                System.out.println("\n크리티컬 발동!! (+50% 공격력)");

                // 공격 사운드
                Play_BG_music attack_sound = new Play_BG_music("attack_sound.mp3", false);
                attack_sound.start();

                try {
                    Thread.sleep(100); // 0.1초
                } catch (Exception e) { }

                System.out.println("[ " + monster.name + " ]이(가) 방어력을 무시하고 " + monster.attack_power + ")(X 1.5) --> 총 " + (int) (monster.attack_power * 1.5) + "의 피해를 입혔습니다!");

                // 플레이어가 피해를 입을 때의 사운드
                Play_BG_music human_hurt = new Play_BG_music("human_hurt.mp3", false);
                human_hurt.start();

                // 공격을 받은 플레이어의 체력 계산
                player.now_hp -= (monster.attack_power * 1.5);
            }
            // 일반 공격을 하는 경우
            else {
                System.out.println("\n[ " + monster.name + " ]이(가) " + monster.attack_power + "의 피해를 입혔습니다!");

                // 몬스터 공격 사운드
                Play_BG_music attack_sound = new Play_BG_music("attack_sound.mp3", false);
                attack_sound.start();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                System.out.println("\n[ " + player.name + " ]님의 방어력 " + player.defense_power + "만큼 피해를 줄입니다.");

                // 플레이어의 방어력이 몬스터의 공격력보다 같거나 높은 경우
                if (monster.attack_power - player.defense_power <= 0) {
                    Play_BG_music block_perfect_sound = new Play_BG_music("shield_block_sound.mp3", false);
                    block_perfect_sound.start();

                    System.out.println("\n완벽한 방어!! 0 의 피해를 입습니다.");
                } else {
                    // 플레이어가 피해를 입을 때의 사운드
                    Play_BG_music human_hurt = new Play_BG_music("human_hurt.mp3", false);
                    human_hurt.start();

                    System.out.println((monster.attack_power - player.defense_power) + " 의 피해를 입습니다.");


                    // 공격받은 플레이어의 체력 계산
                    player.now_hp -= (monster.attack_power - player.defense_power);
                }

                // 보스 몬스터와 전투하는 경우, (보스 몬스터는 50% 확률로 한번 더 공격함)
                if (monster.name.equals("마왕")) {
                    if (random_chance.nextInt(10) < 5) {
                        // 보스 몬스터 등장시, 사운드(이 상황에서는 다시 공격할 때)
                        Play_BG_music evil_king = new Play_BG_music(monster.appear_sound, false);
                        evil_king.start();

                        System.out.println("\n[ 앗! [ 마왕 ]이 한번 더 공격합니다! ]");

                        // 크리티컬 공격을 하는 경우 (방어력 무시)
                        if (random_chance.nextInt(10) < 1) {
                            System.out.println("\n크리티컬 발동!! (+50% 공격력)");
                            System.out.println("[ " + monster.name + " ]이(가) 방어력을 무시하고 " + monster.attack_power + "(X 1.5) --> 총 " + (int) (monster.attack_power * 1.5) + "의 피해를 입혔습니다!");

                            // 공격 사운드
                            attack_sound = new Play_BG_music("attack_sound.mp3", false);
                            attack_sound.start();

                            try {
                                Thread.sleep(100); // 0.1초
                            } catch (Exception e) { }

                            //  플레이어가 피해를 입을 때의 사운드
                            Play_BG_music human_hurt = new Play_BG_music("human_hurt.mp3", false);
                            human_hurt.start();

                            // 공격 받은 플레이어의 체력 계산
                            player.now_hp -= (monster.attack_power * 1.5);
                        }
                        // 일반 공격하는 경우
                        else {
                            // 공격 사운드
                            attack_sound = new Play_BG_music("attack_sound.mp3", false);
                            attack_sound.start();

                            try {
                                Thread.sleep(100); // 0.1초
                            } catch (Exception e) { }

                            System.out.println("\n[ " + monster.name + " ]이(가) " + monster.attack_power + "의 피해를 입혔습니다!");
                            System.out.println("\n[ " + player.name + " ]님의 방어력 " + player.defense_power + "만큼 피해를 줄입니다.");

                            // 플레이어의 방어력이 보스 몬스터의 공격력보다 같거나 높은 경우
                            if (monster.attack_power - player.defense_power <= 0) {
                                Play_BG_music block_perfect_sound = new Play_BG_music("shield_block_sound.mp3", false);
                                block_perfect_sound.start();

                                System.out.println("\n완벽한 방어!! 0 의 피해를 입습니다.");
                            } else {
                                System.out.println("\n" + (monster.attack_power - player.defense_power) + " 의 피해를 입습니다.");

                                // 플레이어 피해 입을 때의 사운드
                                Play_BG_music human_hurt = new Play_BG_music("human_hurt.mp3", false);
                                human_hurt.start();

                                // 공격받은 플레이어의 체력 계산
                                player.now_hp -= (monster.attack_power - player.defense_power);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\n=====================================================================================================");

        return player.now_hp;
    }
}
