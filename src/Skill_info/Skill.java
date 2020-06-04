package Skill_info;

import Character_info.Character_combatant;
import Character_info.Character_combatant_player;
import Main.Play_BG_music;

import java.util.Random;

public class Skill {
    public String[] skill_name = new String[2]; // 각 직업 별 스킬 이름
    public String[] skill_type = new String[2]; // 각 직업 별 스킬 종류
    public int required_mp; // 스킬을 사용하는데 필요한 마력
    public boolean skill_possible; // 스킬을 사용할 수 있는지 없는지

    public Skill() {
        this.skill_name[0] = "없음"; // 각 직업 별 스킬의 이름
        this.skill_name[1] = ""; // 보유 스킬이 두개인 경우 사용

        this.skill_type[0] = ""; // 각 직업 별 소유한 스킬의 종류
        this.skill_type[1] = ""; // 보유 스킬이 두개인 경우, 두번째 스킬의 종류

        this.skill_possible = true;

        this.required_mp = 0; // skill_name[0] 스킬을 사용하는데 필요한 마력
    }

    // 액티브 스킬을 사용한 경우
    public void active_skill_use(Character_combatant_player player, Character_combatant monster) {
        Random random_chance = new Random(System.currentTimeMillis());

        Skill skill = player.player_job.job_skill;
        System.out.println("");

        if(!skill_possible) {
            System.out.println("\n[ ※ 이미 스킬을 사용하여, 사용하실 수 없습니다. ]\n");
        }
        else {
            // 기사
            if (player.player_job.job_name.equals("기사")) {
                // 기사 스킬 소리
                Play_BG_music knight_skill = new Play_BG_music("knight_skill.mp3", false);
                knight_skill.start();

                System.out.println(skill.skill_name[0] + "이 발동되어 방어력이 1.5배 증가합니다.\n");

                int original_dp = player.getDefense_power();
                player.setDefense_power( (int)(player.getDefense_power() * 1.5) );

                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                skill_possible = false;
            }
            // 무사
            else if (player.player_job.job_name.equals("무사")) {
                if (random_chance.nextInt(10) < 3) {
                    // 무사 스킬 소리
                    Play_BG_music warrior_skill = new Play_BG_music("warrior_skill.mp3", false);
                    warrior_skill.start();

                    System.out.println("공격에 " + skill.skill_name[0] + "이 담겨 1.5배의 공격력으로 공격합니다. (30% 확률)");

                    int original_op = player.getAttack_power();
                    player.setAttack_power((int) (player.getAttack_power() * 1.5));

                    player.player_normal_attack_monster(player, monster);

                    player.setAttack_power(original_op);
                } else {
                    System.out.println("공격에 " + skill.skill_name[0] + "이 담기지 않아 원래의 공격력으로 공격합니다. (70% 확률)");

                    player.player_normal_attack_monster(player, monster);
                }
            }
            // 마법사
            else if (player.player_job.job_name.equals("마법사")) {
                System.out.println(skill.skill_name[0] + "로 " + player.getMax_mp() + "(X 2) 만큼의 데미지를 가합니다.\n");
                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                // 에너지 볼트 소리
                Play_BG_music energy_bolt = new Play_BG_music("energy_bolt.mp3", false);
                energy_bolt.start();

                int original_op = player.getAttack_power();
                player.setAttack_power((player.getMax_mp() * 2));

                player.player_normal_attack_monster(player, monster);

                player.setAttack_power(original_op);
            }
            // 팔라딘
            else if (player.player_job.job_name.equals("팔라딘")) {
                // 팔라딘 스킬 소리
                Play_BG_music paladin_skill = new Play_BG_music("paladin_skill.mp3", false);
                paladin_skill.start();

                System.out.println(skill.skill_name[0] + "가 발동되어 몬스터가 위축됩니다.\n몬스터 체력이 30% 감소합니다.\n");
                monster.setNow_hp((int) (monster.getNow_hp() * 0.7));

                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                skill_possible = false;
            }
            // 다크나이트
            else if (player.player_job.job_name.equals("다크나이트")) {
                // 공포스런 소리 or 몬스터가 겁먹은 소리
                Play_BG_music darknight_skill = new Play_BG_music("darknight_skill.mp3", false);
                darknight_skill.start();

                System.out.println(skill.skill_name[0] + "가 발동되어 몬스터가 공포에 빠집니다.\n공격력이 30% 감소합니다.\n");
                monster.setAttack_power((int) (monster.getAttack_power() * 0.7));

                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                skill_possible = false;
            }
            // 검사
            else if (player.player_job.job_name.equals("검사")) {
                if (random_chance.nextInt(10) < 3) {
                    // 두번 공격하는 소리
                    Play_BG_music swordman_skill = new Play_BG_music("swordman_skill.mp3", false);
                    swordman_skill.start();

                    System.out.println("검과 하나가 되어, 2배의 공격력으로 두 번 공격합니다. (30% 확률)");

                    int original_op = player.getAttack_power();
                    player.setAttack_power((player.getAttack_power() * 2));

                    player.player_normal_attack_monster(player, monster);
                    player.player_normal_attack_monster(player, monster);

                    player.setAttack_power(original_op);
                } else {
                    System.out.println("검과 하나가 되지 못하여, 원래의 공격력으로 공격합니다. (70% 확률)");

                    player.player_normal_attack_monster(player, monster);
                }
            }
            // 화법사
            else if (player.player_job.job_name.equals("화법사")) {
                System.out.println(skill.skill_name[0] + "로 " + player.getMax_mp() + "(X 2) 만큼의 데미지를 가합니다.\n화염으로 10%의 데미지가 추가됩니다.\n");
                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                // 파이어 볼트 소리
                Play_BG_music fire_bolt = new Play_BG_music("fire_bolt.mp3", false);
                fire_bolt.start();

                int original_op = player.getAttack_power();
                player.setAttack_power((int) (player.getMax_mp() * 2.2));

                player.player_normal_attack_monster(player, monster);

                player.setAttack_power(original_op);
            }
            // 빙법사
            else if (player.player_job.job_name.equals("빙법사")) {
                System.out.println(skill.skill_name[0] + "로 " + player.getMax_mp() + "(X 2) 만큼의 데미지를 가합니다.\n냉기로 10%의 데미지가 추가됩니다.\n");
                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                // 아이스 볼트 소리
                Play_BG_music ice_bolt = new Play_BG_music("ice_bolt.mp3", false);
                ice_bolt.start();

                int original_op = player.getAttack_power();
                player.setAttack_power((int) (player.getMax_mp() * 2.2));

                player.player_normal_attack_monster(player, monster);

                player.setAttack_power(original_op);
            }
            // 기사왕
            else if (player.player_job.job_name.equals("기사왕")) {
                // 포더킹!, 기사왕 스킬 소리
                Play_BG_music knight_king_skill = new Play_BG_music("knight_king_skill.mp3", false);
                knight_king_skill.start();

                System.out.println(skill.skill_name[0] + "가 발동되어 몬스터가 위축되며 공포에 빠집니다.\n몬스터 체력과 공격력이 각각 50% 감소합니다.\n");
                monster.setNow_hp((int) (monster.getNow_hp() * 0.5));
                monster.setAttack_power((int) (monster.getAttack_power() * 0.5));

                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                skill_possible = false;
            }
            // 무신
            else if (player.player_job.job_name.equals("무신")) {
                System.out.println("검에 마음을 담아 공격합니다.\n");
                if (random_chance.nextInt(10) < 3) {
                    // 칼소리, 무신 스킬 소리
                    Play_BG_music martial_god_skill = new Play_BG_music("martial_god_skill.mp3", false);
                    martial_god_skill.start();

                    System.out.println("공격에 " + skill.skill_name[0] + "이 담겨 적을 처형합니다. (30% 확률)");

                    monster.setNow_hp(0);
                } else {
                    System.out.println("공격에 " + skill.skill_name[0] + "이 담기지 않아 원래의 공격력으로 공격합니다. (70% 확률)");

                    player.player_normal_attack_monster(player, monster);
                }
            }
            // 대마법사
            else if (player.player_job.job_name.equals("대마법사")) {
                System.out.println(skill.skill_name[0] + "로 " + player.getMax_mp() + "(X 2) 만큼의 데미지를 가합니다.\n화염과 냉기가 섞여 100%의 데미지가 추가됩니다.\n");
                System.out.println(skill.required_mp + "의 마력이 소모됩니다.");
                player.setNow_mp(player.getNow_mp() - skill.required_mp);

                // 마법 소리, 대마법사 스킬 소리
                Play_BG_music fire_and_ice_dance = new Play_BG_music("fire_and_ice_dance.mp3", false);
                fire_and_ice_dance.start();

                int original_op = player.getAttack_power();
                player.setAttack_power((player.getMax_mp() * 4));

                player.player_normal_attack_monster(player, monster);

                player.setAttack_power(original_op);
            }
        }

        System.out.println("\n");
    }

    // 패시브 스킬을 사용한 경우
    public boolean passive_skill_use(Character_combatant_player player, Character_combatant monster) {
        Random random_chance = new Random(System.currentTimeMillis());
        Play_BG_music magic_shield_sound = new Play_BG_music("magic_shield_sound.mp3", false);

        boolean success_shield = false;

        // 플레이어 직업이 광전사인 경우, 공격 받을 때마다 공격력이 오르고, 방어력이 낮아짐
        if(player.player_job.job_name.equals("광전사")) {
            // 분노에 차는 소리
            Play_BG_music berserker_anger = new Play_BG_music("berserker_anger.mp3", false);
            berserker_anger.start();

            System.out.println("\n플레이어가 공격을 받아 분노가 차오릅니다!!!");
            System.out.println("\n공격력 +50, 방어력 -50");
            player.setAttack_power( player.attack_power + 50 );
            player.setDefense_power( player.defense_power - 50 );

            if(player.getDefense_power() <= 0) {
                player.setDefense_power(0);
            }
        }

        // 플레이어 직업이 마법사 계열인 경우, 30% 확률로 패시브 스킬 발동
        if( player.player_job.job_name.equals("마법사") || player.player_job.job_name.equals("화법사") || player.player_job.job_name.equals("빙법사") || player.player_job.job_name.equals("대마법사") ) {
            if (random_chance.nextInt(10) < 3) {
                System.out.println("\n" + player.player_job.job_skill.skill_name[1] + "가 발동됩니다. (30% 확률)");
                success_shield = true;

                magic_shield_sound.start();
                if (player.player_job.job_name.equals("마법사")) {
                    System.out.println("\n쉴드가 발동되어 적의 공격을 막습니다.");
                }
                else if (player.player_job.job_name.equals("화법사")) {
                    System.out.println("\n파이어 쉴드가 발동되어 적의 공격을 막습니다.");

                    System.out.println("파이어 쉴드의 열기로 몬스터가 가한 공격의 50% 데미지를 입습니다. (-" + (int)(monster.attack_power * 0.5) +")");
                    monster.now_hp -= (int)(monster.attack_power * 0.5);
                    monster.setNow_hp(monster.now_hp);
                }
                else if (player.player_job.job_name.equals("빙법사")) {
                    System.out.println("\n아이스 쉴드가 발동되어 적의 공격을 막습니다.");

                    if(random_chance.nextInt(10) < 2) {
                        System.out.println("아이스 쉴드의 냉기로 몬스터가 3초동안 빙결됩니다. (20% 확률)");
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) { }
                    }
                }
                else if (player.player_job.job_name.equals("대마법사")) {
                    System.out.println("\n반사가 발동되어 적의 공격을 되돌려줍니다. (-" + monster.attack_power +")");
                    monster.now_hp -= monster.attack_power;
                    monster.setNow_hp(monster.now_hp);

                    if(random_chance.nextInt(10) < 3) {
                        System.out.println("반사로 충격을 받은 몬스터가 5초동안 기절됩니다. (30% 확률)");
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) { }
                    }
                }
            }
        }

        System.out.println("");
        return success_shield;
    }
}
