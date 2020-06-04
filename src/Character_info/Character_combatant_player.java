package Character_info;

import Item_info.Item_equipment_armor;
import Item_info.Item_equipment_weapon;
import Item_info.Item_potion;
import Job_info.Job;
import Main.Play_BG_music;
import Main.Thread_effect;
import Map_info.Map_boss;
import Map_info.Map_dungeon;
import Map_info.Map_villiage;
import Skill_info.Skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Character_combatant_player extends Character_combatant {

    public int max_exp; // 현재 레벨에서의 최대 경험치

    public int defense_power; // 플레이어의 방어력

    public Job player_job; // 플레이어의 직업
    public ArrayList<Item_potion> inventory = new ArrayList<Item_potion>(); // 플레이어의 인벤토리
    public Item_equipment_armor wearing_armor; // 착용중인 방어구
    public Item_equipment_weapon wearing_weapon; // 착용중인 무기

    // 플레이어가 생성될 때, 초기화를 위한 생성자
    public Character_combatant_player() {
        this.level = 1;
        this.having_money = 0;

        this.exp = 0;
        this.max_exp = 10;

        this.now_hp = 100;
        this.max_hp = 100;

        this.now_mp = 0;
        this.now_mp = 0;

        this.attack_power = 10;
        this.defense_power = 0;

        Job initial_job = new Job();
        this.player_job = initial_job;

        Item_equipment_armor dummy_armor = new Item_equipment_armor("미장착", null, 0, null, 0, 0);
        this.wearing_armor = dummy_armor;

        Item_equipment_weapon dummy_weapon = new Item_equipment_weapon("미장착", null, 0, null, 0, 0);
        this.wearing_weapon = dummy_weapon;

        Item_potion dummy = new Item_potion("없음", " ", 0,0);
        this.inventory.add(0, dummy);
        this.inventory.add(1, dummy);
        this.inventory.add(2, dummy);
        this.inventory.add(3, dummy);
        this.inventory.add(4, dummy);
        this.inventory.add(5, dummy);

    }

    // 플레이어의 방어력 getter
    public int getDefense_power() {
        return defense_power;
    }

    // 플레이어의 방어력 setter
    public void setDefense_power(int defense_power) {
        this.defense_power = defense_power;
    }

    // 플레이어의 상태창을 보기 위한 메서드
    public void get_player_status() {
        Play_BG_music status_sound = new Play_BG_music("status_sound.mp3", false);
        status_sound.start();

        System.out.println("-----[상태창]-----------------------------------------------------------------------------------------");
        System.out.println("이름 : " + this.name + "\t\t\t\t\t\t레벨 : " + this.level + " ( 경험치 : " + this.exp + " / " + this.max_exp + " )");
        System.out.println("직업 : " + this.player_job.job_name + "\n보유 스킬 : " + this.player_job.job_skill.skill_name[0] + " . " + this.player_job.job_skill.skill_name[1]);
        System.out.println("HP : " + this.now_hp + " / " + this.max_hp + "\t\t\t\t\t\tMP : " + this.now_mp + " / " + this.max_mp);
        System.out.println("공격력 : " + this.attack_power + "(+" + this.wearing_weapon.attack_power + ")" + "\t\t\t\t\t\t방어력 : " + this.defense_power + "(+" + this.wearing_armor.defense_power + ")");
        System.out.println("착용 무기 : " + this.wearing_weapon.item_name + "\t\t\t\t\t착용 방어구 : " + this.wearing_armor.item_name);
        System.out.println("소지금 : " + this.having_money + " 원");
        System.out.println("보유 포션 : [ " + this.inventory.get(0).item_name + " ], [ " + this.inventory.get(1).item_name + " ], [ " + this.inventory.get(2).item_name + " ]");
        System.out.println("           [ " + this.inventory.get(3).item_name + " ], [ " + this.inventory.get(4).item_name + " ], [ " + this.inventory.get(5).item_name + " ]");
        System.out.println("----------------------------------------------------------------------------------------------------\n");
    }

    // 플레이어가 맵을 이동할 때, 사용하는 메서드
    public void player_move(String map_name, Character_combatant_player player) {

        // 몬스터 객체
        Character_combatant monster[] = new Character_combatant[10];
        monster[0] = new Character_combatant("슬라임", 1, 50, 10, 5, 100, "slime.mp3");
        monster[1] = new Character_combatant("고블린", 2, 100,15, 10, 200, "goblin.mp3");
        monster[2] = new Character_combatant("오크", 3, 200, 20, 20, 250, "orc.mp3");
        monster[3] = new Character_combatant("트롤", 4, 400,50, 40, 300, "troll.mp3");
        monster[4] = new Character_combatant("오거", 5, 500, 70, 80, 400, "ogre.mp3");
        monster[5] = new Character_combatant("골렘", 6, 600, 100, 100, 500, "golem.mp3");
        monster[6] = new Character_combatant("그리핀", 7, 700, 150, 200, 1000, "griffin.mp3");
        monster[7] = new Character_combatant("드래곤", 8, 1000, 200, 300, 1200, "dragon.mp3");
        monster[8] = new Character_combatant("발록", 9, 1200, 250, 400, 1500, "balrog.mp3");
        monster[9] = new Character_combatant("마왕", 10, 5000, 500, 0, 0, "evil_king.mp3");

        // 마을으로 이동할 때
        if(map_name == "마을") {
            Map_villiage villiage = new Map_villiage(); // 마을 맵 객체
            villiage.in_villiage(player); // 마을 안에 있을 때의 이벤트 발생

        }
        // 던전으로 이동할 때,
        // 초보자 사냥터 이동 시
        else if(map_name == "[ 초보자 ]의 사냥터") {
            // 던전 맵 객체( + 던전에서 생성되는 몬스터들 인자)
            Map_dungeon dungeon = new Map_dungeon(monster[0], monster[1], monster[2]);
            dungeon.in_dungeon(player); // 던전 안에 있을 때의 이벤트 발생

        }
        // 숙련자 사냥터 이동 시
        else if(map_name == "[ 숙련자 ]의 사냥터") {
            // 던전 맵 객체( + 던전에서 생성되는 몬스터들 인자)
            Map_dungeon dungeon = new Map_dungeon(monster[3], monster[4], monster[5]);
            dungeon.in_dungeon(player); // 던전 안에 있을 때의 이벤트 발생

        }
        // 전무가 사냥터 이동 시
        else if(map_name == "[ 전문가 ]의 사냥터") {
            // 던전 맵 객체( + 던전에서 생성되는 몬스터들 인자)
            Map_dungeon dungeon = new Map_dungeon(monster[6], monster[7], monster[8]);
            dungeon.in_dungeon(player); // 던전 안에 있을 때의 이벤트 발생

        }
        // 보스방으로 이동 시
        else if(map_name == "[ 보스방 ]") {
            // 보스방 맵 객체( + 보스방에서 생성되는 보스 몬스터 인자)
            Map_boss boss = new Map_boss(monster[9]);
            boss.in_boss(player); // 보스방 안에 있을 때의 이벤트 발생
        }
    }

    // 플레이어 케릭터가 레벨업할 때를 위한 메서드
    public Character_combatant_player player_level_up(Character_combatant_player player) {
        // 플레이어의 레벨이 만렙(10레벨) 미만일 때,
        if(player.level < 10 ) {
            System.out.println("\n레벨업!!!!!!!!");
            System.out.println("레벨이 상승합니다.");

            Play_BG_music level_up_sound = new Play_BG_music("level_up_sound.mp3", false);
            level_up_sound.start();

            player.level++;
            // 경험치 계산
            player.exp = player.exp - max_exp;

            // 경험치가 음수로 되는 경우 예외처리
            if(player.exp < 0) {
                player.exp = 0;
            }

            // 레벨업에 따른 플레이어 케릭터 능력치 조정
            player.max_exp += 100;

            player.attack_power += player.player_job.job_attack_power;
            player.defense_power += player.player_job.job_defense_power;

            player.max_hp += player.player_job.job_hp;
            player.now_hp = player.max_hp;

            player.max_mp += player.player_job.job_mp;
            player.now_mp = player.max_mp;

            // 레벨업 후, 10레벨이 된 경우
            if(player.level == 10) {
                System.out.println("\n만렙(10 레벨)입니다.");

                // 만렙이기에 경험치 0으로 만듦
                player.exp = 0;
                player.max_exp = 0;
            }
        }
        // 이미 만렙(10레벨)이여서 레벨업이 안되는 경우
        else if(player.level == 10) {
            System.out.println("\n레벨업을 할 수 없습니다.");
            System.out.println("이미 만렙(10 레벨)입니다.");
        }

        return player;
    }

    // 플레이어가 전투중에 포션을 마시는 경우를 위한 메서드
    public void player_drink_portion(Character_combatant_player player) {
        Scanner scan = new Scanner(System.in);

        // 포션을 마셨는지 안마셨는지
        boolean drink_potion = true;

        // 더미 포션 객체, 포션을 마신 경우 빈 슬롯 표시를 위한 객체
        Item_potion dummy = new Item_potion("없음", " ", 0, 0);

        Play_BG_music inventory_sound = new Play_BG_music("inventory_sound.mp3", false);
        inventory_sound.start();

        System.out.println("\n=====================================================================================================");
        System.out.println("어떤 포션을 마시겠습니까? (숫자로 입력해주세요. 목록 외 선택시 처음으로 돌아갑니다.)");
        System.out.println("1. 1번 슬롯 : " + player.inventory.get(0).item_name + ", 회복량 : " + player.inventory.get(0).recovery_amount);
        System.out.println("2. 2번 슬롯 : " + player.inventory.get(1).item_name + ", 회복량 : " + player.inventory.get(1).recovery_amount);
        System.out.println("3. 3번 슬롯 : " + player.inventory.get(2).item_name + ", 회복량 : " + player.inventory.get(2).recovery_amount);
        System.out.println("4. 4번 슬롯 : " + player.inventory.get(3).item_name + ", 회복량 : " + player.inventory.get(3).recovery_amount);
        System.out.println("5. 5번 슬롯 : " + player.inventory.get(4).item_name + ", 회복량 : " + player.inventory.get(4).recovery_amount);
        System.out.println("6. 6번 슬롯 : " + player.inventory.get(5).item_name + ", 회복량 : " + player.inventory.get(5).recovery_amount);

        System.out.println("\n0. 그냥 마지지 않는다.");
        System.out.print(" 입력 : ");

        Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
        click_sound.start();

        // 마시고자 하는 포션의 인벤토리 슬롯 번호
        int inventory_choose = scan.nextInt();
        System.out.println("=====================================================================================================");

        // 0번 선택한 경우, (포션을 마시지 않는 경우)
        if(inventory_choose == 0) {
            System.out.println("\n포션을 그냥 마시지 않습니다.\n");
            drink_potion = false;
        }
        // 포션을 마시는 경우
        else if(inventory_choose != 4) {
            inventory_choose--;

            // 선택한 슬롯에 포션이 없는 경우
            if(player.inventory.get(inventory_choose).item_name == "없음") {
                System.out.println("\n포션이 없습니다.\n");
                drink_potion = false;
            }
            // 선택한 슬롯에 포션이 있는 경우
            // 체력 포션일 경우
            else if(player.inventory.get(inventory_choose).item_type == "체력 포션") {
                // 이미 체력이 가득 차 있는 경우
                if(player.now_hp == player.max_hp) {
                    System.out.println("\n이미 체력이 가득 차 있습니다. 포션을 마시지 않습니다.");
                    drink_potion = false;
                }
                // 체력이 닳아 있는 경우
                else {
                    Play_BG_music potion_sound = new Play_BG_music("potion_bubble.mp3", false);
                    potion_sound.start();

                    System.out.println("\n" + player.inventory.get(inventory_choose).item_name + "을 마십니다.");

                    try {
                        Thread.sleep(200);
                        Play_BG_music recovery_sound = new Play_BG_music("recovery_sound.mp3", false);
                        recovery_sound.start();
                    } catch (Exception e) { }

                    System.out.println("체력이 " + player.inventory.get(inventory_choose).recovery_amount + "만큼 회복됩니다.");

                    int original_hp = player.now_hp; // 체력 변화 출력을 위한, 현재 체력 저장 임시 변수

                    // 체력 회복 이벤트
                    if (player.now_hp + player.inventory.get(inventory_choose).recovery_amount > player.max_hp) {
                        player.now_hp = player.max_hp;
                        player.setNow_hp(player.now_hp);

                        System.out.println("체력이 가득찹니다.");
                    }
                    else {
                        player.now_hp += player.inventory.get(inventory_choose).recovery_amount;
                    }

                    // 체력 회복 변화 출력
                    System.out.println("체력 : " + original_hp + " --> " + player.now_hp);
                    System.out.println();

                    // 마신 포션이 있던 인벤토리 슬롯 : '없음'으로 바꾸는 작업
                    player.inventory.set(inventory_choose, dummy);

                    drink_potion = true;
                }
            }
            // 마력 포션일 경우
            else if(player.inventory.get(inventory_choose).item_type == "마력 포션") {
                // 이미 마력이 가득 차 있는 경우
                if(player.now_mp == player.max_mp) {
                    System.out.println("\n이미 마력이 가득 차 있습니다. 포션을 마시지 않습니다.");
                    drink_potion = false;
                }
                // 마력이 닳아 있는 경우
                else {
                    Play_BG_music potion_sound = new Play_BG_music("potion_bubble.mp3", false);
                    potion_sound.start();

                    System.out.println("\n" + player.inventory.get(inventory_choose).item_name + "을 마십니다.");

                    try {
                        Thread.sleep(200);
                        Play_BG_music recovery_sound = new Play_BG_music("recovery_sound.mp3", false);
                        recovery_sound.start();
                    } catch (Exception e) { }

                    System.out.println("마력이 " + player.inventory.get(inventory_choose).recovery_amount + "만큼 회복됩니다.");

                    int original_mp = player.now_mp; // 마력 변화 출력을 위한, 현재 마력 저장 임시 변수

                    // 마력 회복 이벤트
                    if (player.now_mp + player.inventory.get(inventory_choose).recovery_amount > player.max_mp) {
                        player.now_mp = player.max_mp;
                        System.out.println("마력이 가득찹니다.");
                    }
                    else {
                        player.now_mp += player.inventory.get(inventory_choose).recovery_amount;
                    }

                    // 마력 회복 변화 출력
                    System.out.println("마력 : " + original_mp + " --> " + player.now_mp);
                    System.out.println();

                    // 마신 포션이 있던 인벤토리 슬롯 : '없음'으로 바꾸는 작업
                    player.inventory.set(inventory_choose, dummy);

                    drink_potion = true;
                }
            }
        }
    }

    // 전투 시, 플레이어가 몬스터에게 일반 공격하는 이벤트
    public int player_normal_attack_monster(Character_combatant_player player, Character_combatant monster) {
        Random ciritical_chance = new Random(System.currentTimeMillis());
        System.out.println("\n=====================================================================================================");

        // 크리티컬 확률 10% 를 위한 조건문
        // 크리티컬 일반 공격을 하는 경우
        if(ciritical_chance.nextInt(10) < 1) {
            System.out.println("\n크리티컬 발동!! (+50% 공격력)");

            // 크리티컬 공격 시, 이를 알리기 위한 사운드
            Play_BG_music attack_sound = new Play_BG_music("attack_sound.mp3", false);
            attack_sound.start();

            System.out.println("[ " + player.name + " ]님이 " + player.attack_power + "(+" + player.wearing_weapon.attack_power + ")(X 1.5) --> 총 "
                    + (int)((player.attack_power + player.wearing_weapon.attack_power)* 1.5) + "의 피해를 입혔습니다!");

            // 공격을 받은 몬스터의 체력 계산
            monster.now_hp -= ( (player.attack_power + player.wearing_weapon.attack_power) * 1.5 );
        }
        // 일반 공격을 하는 경우
        else {
            // 플레이어의 일반 공격 사운드
            Play_BG_music attack_sound = new Play_BG_music("attack_sound.mp3", false);
            attack_sound.start();

            try {
                Thread.sleep(100); // 0.1초
            } catch (Exception e) { }

            System.out.println("\n[ " + player.name + " ]님이 " + player.attack_power + "(+" + player.wearing_weapon.attack_power + ")의 피해를 입혔습니다!");

            // 공격을 받은 몬스터의 체력 계산
            monster.now_hp -= (player.attack_power + player.wearing_weapon.attack_power);
        }

        System.out.println("\n=====================================================================================================\n");

        return monster.now_hp;
    }

    // 전투 시, 플레이어가 몬스터에게 스킬 공격하는 이벤트
    public void player_skill_attack_monster(Character_combatant_player player, Character_combatant monster) {
        Skill skill = player.player_job.job_skill;

        // 광전사의 경우, 액티브 시킬이 없기에 예외처리
        if( player.player_job.job_name.equals("광전사")) {
            System.out.println("\n[ 광전사는 액티브 스킬이 없습니다. ]\n");
        }
        // 광전사를 제외한 나머지 직업인 경우
        else {
            System.out.println("\n=====================================================================================================");

            System.out.println("\n[ " + skill.skill_name[0] + " ] 스킬 발동!!!\n");

            // active_skill_use 메서드를 통한 플레이어의 스킬 공격 이벤트
            skill.active_skill_use(player, monster);

            System.out.println("\n=====================================================================================================\n");
        }
        //return monster.now_hp;
    }
}
