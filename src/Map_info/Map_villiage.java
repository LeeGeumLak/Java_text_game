package Map_info;

import Character_info.Character_merchant;
import Character_info.Character_combatant_player;

import java.util.Random;
import java.util.Scanner;
import Main.*;

public class Map_villiage extends Map {
    Character_merchant appear_merchant; // 상인 객체 생성

    // 마을 맵 객체 생성시, 초기화를 위한 생성자
    public Map_villiage() {
        this.appear_merchant = new Character_merchant();
    }

    // 플레이어가 마을 안에 있을 때, 이벤트 발생을 위한 메서드
    public void in_villiage(Character_combatant_player player) {
        Scanner scan = new Scanner(System.in);
        Thread_effect thread_effect = new Thread_effect();

        // 마을 배경음악
        Play_BG_music villiage_bgm = new Play_BG_music("TownTheme.mp3", true);
        villiage_bgm.start();

        try {
            Thread.sleep(200); // 0.2초
        } catch (Exception e) { }

        // 마을 촌장 소리
        Play_BG_music male_hello_sound = new Play_BG_music("male_hello_sound.mp3", false);
        male_hello_sound.start();

        // 마을 안에서의 이벤트 반복문
        villiage_loop:
        while(true) {
            System.out.println("\n[ 마을 족장 ] : 뭐 하려고..? (숫자로 입력해주세요.)");
            System.out.println("1. 상점 이용  2. 체력과 마력 회복  3. 상태창 보기  4. 주점 이용  5. 나가기");
            System.out.print(" 입력 : ");
            String villiage_choose = scan.next(); // 마을에서의 행동 선택 번호

            switch(villiage_choose) {
                // 상점 이용 이벤트
                case "1":
                    // 상인 사운드
                    Play_BG_music merchant_welcome = new Play_BG_music("merchant_welcome.mp3", false);
                    merchant_welcome.start();

                    // 상점 이벤트 반복문

                    // 첫번째 루프인지 아닌지
                    boolean first_loop = true;
                    merchant_loop:
                    while(true) {
                        if(first_loop) {
                            // 상점 입장시, 문 사운드
                            Play_BG_music door_effect = new Play_BG_music("door_sound.mp3", false);
                            door_effect.start();
                            first_loop = false;
                        }

                        System.out.println("\n[ 상인 ] : 뭐 사시려고..? (숫자로 입력해주세요.)");
                        System.out.println("1. 방어구  2. 무기  3. 포션  4. 상태창 보기  5. 나가기");
                        System.out.print(" 입력 : ");
                        String merchant_choose = scan.next(); // 상점에서 행동 선택 번호

                        int product_choose; // 선택한 제품의 번호
                        switch(merchant_choose) {
                            case "1":
                            case "방어구":
                                // 방어구 사운드
                                Play_BG_music armor_sound = new Play_BG_music("armor_sound.mp3", false);
                                armor_sound.start();

                                System.out.println("\n=====================================================================================================");
                                System.out.println("[ 상인 ] : 방어구?? 어떤거 사시려고..? (숫자로 입력해주세요. 목록 외 선택시 처음으로 돌아갑니다.)\n");

                                // armor_list_up 메서드를 통한 상인이 파는 방어구 리스트 업
                                appear_merchant.armor_list_up(player);

                                System.out.println("=====================================================================================================");
                                System.out.print(" 입력 : ");
                                product_choose = scan.nextInt();

                                // 선택지 선택시, 클릭 사운드
                                Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
                                click_sound.start();

                                // 리스트에서 뒤로가기 선택한 경우
                                if(product_choose == 0) {
                                    System.out.println("\n뒤로 갑니다.");
                                    continue merchant_loop;
                                }

                                product_choose--;

                                // 방어구 구매 이벤트
                                // 소지금이 부족한 경우
                                if(player.having_money - appear_merchant.having_armor[product_choose].item_price < 0) {
                                    System.out.println("\n소지금이 부족합니다. 돈 벌어오세요.");
                                }
                                else {
                                    // 선택한 방어구를 사용할 수 있는 직업이 아닌 경우
                                    if (!player.player_job.job_name.equals(appear_merchant.having_armor[product_choose].possible_job)) {
                                        System.out.println("\n이 방어구를 사용할 수 있는 직업이 아닙니다.");
                                    }
                                    else {
                                        // 테스트에서 사용하는 '개발자' 직업을 위한 코드
                                        /*if(player.player_job.job_name.equals("개발자")) {
                                            player.having_money -= appear_merchant.having_armor[product_choose].item_price; // 돈 계산
                                            System.out.println("\n" + appear_merchant.having_armor[product_choose].item_name + "을 선택하셨습니다. "
                                                    + appear_merchant.having_armor[product_choose].item_price + "원을 차감합니다.");

                                            // 방어구를 착용하고 있지 않을 때
                                            if(player.wearing_armor.item_name.equals("미장착")) {
                                                System.out.println("\n자동으로 구매하신 방어구를 착용합니다.");

                                            }
                                            // 다른 장비를 착용중일 때
                                            else {
                                                System.out.println("\n자동으로 구매하신 방어구를 착용합니다.");
                                                System.out.println("현재 착용중인 방어구는 상인에게 반값으로 팝니다. " + (player.wearing_armor.item_price / 2) + "원을 획득합니다.");
                                            }
                                            // 플레이어의 '소지금' 정보 갱신
                                            player.having_money += (player.wearing_armor.item_price/2);
                                            // 플레이어 '착용중인 방어구' 정보 갱신
                                            player.wearing_armor = appear_merchant.having_armor[product_choose];
                                        }*/

                                        // 방어구를 사용할 수 있는 레벨이 아닐 때,
                                        if(player.level < appear_merchant.having_armor[product_choose].possible_level) {
                                            // 방어구 사용 가능한 레벨이 10 일때,
                                            if (appear_merchant.having_armor[product_choose].possible_level == 10) {
                                                System.out.println("\n이 방어구를 착용하려면 만렙(" + appear_merchant.having_armor[product_choose].possible_level + "레벨)이 되어야 합니다. 강해져서 돌아오세요");
                                            }
                                            // 방어구 사용 가능한 레벨이 10 미만 일때,
                                            else {
                                                System.out.println("\n이 방어구를 착용하려면 " + appear_merchant.having_armor[product_choose].possible_level + "레벨 이상이 되어야 합니다. 강해져서 돌아오세요");
                                            }
                                        }
                                        else {
                                            // 상위이거나 동급의 방어구를 착용중일 때,
                                            if(appear_merchant.having_armor[product_choose].possible_level <= player.wearing_armor.possible_level) {
                                                System.out.println("\n이미 성능이 더 좋거나 같은 방어구를 착용하고 계시군요. 돈 낭비하지 마세요!!!");
                                            }
                                            else {
                                                Play_BG_music coin_effect = new Play_BG_music("coin_sound.mp3", false);
                                                coin_effect.start();

                                                player.having_money -= appear_merchant.having_armor[product_choose].item_price; // 돈 계산
                                                System.out.println("\n" + appear_merchant.having_armor[product_choose].item_name + "을 선택하셨습니다. "
                                                        + appear_merchant.having_armor[product_choose].item_price + "원을 차감합니다.");

                                                // 방어구를 착용하고 있지 않을 때
                                                if(player.wearing_armor.item_name.equals("미장착")) {
                                                    System.out.println("\n자동으로 구매하신 방어구를 착용합니다.");

                                                }
                                                // 다른 장비를 착용중일 때
                                                else {
                                                    System.out.println("\n자동으로 구매하신 방어구를 착용합니다.");

                                                    System.out.println("현재 착용중인 방어구는 상인에게 반값으로 팝니다. " + (player.wearing_armor.item_price / 2) + "원을 획득합니다.");
                                                }
                                                // 플레이어의 '소지금' 정보 갱신
                                                player.having_money += (player.wearing_armor.item_price/2);
                                                // 플레이어 '착용중인 방어구' 정보 갱신
                                                player.wearing_armor = appear_merchant.having_armor[product_choose];
                                            }
                                        }
                                    }
                                }

                                break;

                            case "2":
                            case "무기":
                                // 무기 사운드
                                Play_BG_music weapon_sound = new Play_BG_music("weapon_sound.mp3", false);
                                weapon_sound.start();

                                System.out.println("\n=====================================================================================================");
                                System.out.println("[ 상인 ] : 무기?? 어떤거 사시려고..? (숫자로 입력해주세요. 목록 외 선택시 처음으로 돌아갑니다.)\n");

                                // weapon_list_up 메서드를 통한 상인이 파는 무기 리스트 업
                                appear_merchant.weapon_list_up(player);

                                System.out.println("=====================================================================================================");
                                System.out.print(" 입력 : ");
                                product_choose = scan.nextInt();

                                // 클릭 사운드
                                click_sound = new Play_BG_music("click_sound.mp3", false);
                                click_sound.start();

                                // 리스트에서 뒤로가기 선택한 경우
                                if(product_choose == 0) {
                                    System.out.println("\n뒤로 갑니다.");
                                    continue merchant_loop;
                                }

                                product_choose--;
                                // 무기 구매 이벤트
                                // 소지금이 부족한 경우
                                if(player.having_money - appear_merchant.having_weapon[product_choose].item_price < 0) {
                                    System.out.println("\n소지금이 부족합니다. 돈 벌어오세요.");
                                }
                                else {
                                    // 선택한 무기를 사용할 수 있는 직업이 아닌 경우
                                    if( !player.player_job.job_name.equals(appear_merchant.having_weapon[product_choose].possible_job) ) {
                                        System.out.println("\n이 무기를 사용할 수 있는 직업이 아닙니다.");
                                    }
                                    else {
                                        // 테스트에서 사용하는 '개발자' 직업을 위한 코드
                                        /*if(player.player_job.job_name.equals("개발자")) {
                                            player.having_money -= appear_merchant.having_weapon[product_choose].item_price; // 돈 계산
                                            System.out.println("\n" + appear_merchant.having_weapon[product_choose].item_name + "을 선택하셨습니다. "
                                                    + appear_merchant.having_weapon[product_choose].item_price + "원을 차감합니다.");

                                            // 무기를 착용하고 있지 않을 때
                                            if(player.wearing_weapon.item_name == "미장착") {
                                                System.out.println("\n자동으로 구매하신 무기를 착용합니다.");
                                            }
                                            // 다른 무기를 착용하고 있을 때
                                            else {
                                                System.out.println("\n자동으로 구매하신 무기를 착용합니다.");
                                                System.out.println("현재 착용중인 무기는 상인에게 반값으로 팝니다. " + (player.wearing_weapon.item_price / 2) + "원을 획득합니다.");
                                            }
                                            // 플레이어의 '소지금' 정보 갱신
                                            player.having_money += (player.wearing_weapon.item_price/2);
                                            // 플레이어 '착용중인 무기' 정보 갱신
                                            player.wearing_weapon = appear_merchant.having_weapon[product_choose];
                                        }*/

                                        // 무기를 사용할 수 있는 레벨이 아닐 때,
                                        if(player.level < appear_merchant.having_weapon[product_choose].possible_level) {
                                            // 무기 사용 가능한 레벨이 10 일때,
                                            if (appear_merchant.having_weapon[product_choose].possible_level == 10) {
                                                System.out.println("\n이 무기를 착용하려면 만렙(" + appear_merchant.having_weapon[product_choose].possible_level + "레벨)이 되어야 합니다. 강해져서 돌아오세요");
                                            }
                                            // 무기 사용 가능한 레벨이 10 미만 일때,
                                            else {
                                                System.out.println("\n이 무기를 착용하려면 " + appear_merchant.having_weapon[product_choose].possible_level + "레벨 이상이 되어야 합니다. 강해져서 돌아오세요");
                                            }
                                        }
                                        else {
                                            // 상위이거나 동급의 무기를 착용중일 때,
                                            if(appear_merchant.having_weapon[product_choose].possible_level <= player.wearing_weapon.possible_level) {
                                                System.out.println("\n이미 성능이 더 좋거나 같은 무기를 착용하고 계시군요. 돈 낭비하지 마세요!!!");
                                            }
                                            else {
                                                Play_BG_music coin_effect = new Play_BG_music("coin_sound.mp3", false);
                                                coin_effect.start();

                                                player.having_money -= appear_merchant.having_weapon[product_choose].item_price; // 돈 계산
                                                System.out.println("\n" + appear_merchant.having_weapon[product_choose].item_name + "을 선택하셨습니다. "
                                                        + appear_merchant.having_weapon[product_choose].item_price + "원을 차감합니다.");

                                                // 무기를 착용하고 있지 않을 때
                                                if(player.wearing_weapon.item_name == "미장착") {
                                                    System.out.println("\n자동으로 구매하신 무기를 착용합니다.");
                                                }
                                                // 다른 무기를 착용하고 있을 때
                                                else {
                                                    System.out.println("\n자동으로 구매하신 무기를 착용합니다.");

                                                    System.out.println("현재 착용중인 무기는 상인에게 반값으로 팝니다. " + (player.wearing_weapon.item_price / 2) + "원을 획득합니다.");
                                                }
                                                // 플레이어의 '소지금' 정보 갱신
                                                player.having_money += (player.wearing_weapon.item_price/2);
                                                // 플레이어 '착용중인 무기' 정보 갱신
                                                player.wearing_weapon = appear_merchant.having_weapon[product_choose];
                                            }
                                        }
                                    }
                                }


                                break;

                            case "3":
                            case "포션":
                                // 포션 사운드
                                Play_BG_music potion_effect = new Play_BG_music("potion_bubble.mp3", false);
                                potion_effect.start();

                                potion_loop:
                                while (true) {

                                    System.out.println("\n=====================================================================================================");
                                    System.out.println("[ 상인 ] : 포션?? 어떤거 사시려고..? (숫자로 입력해주세요. 목록 외 선택시 처음으로 돌아갑니다.)\n");

                                    // potion_list_up 메서드를 통한 상인이 파는 포션 리스트 업
                                    appear_merchant.potion_list_up(player);

                                    System.out.println("=====================================================================================================");
                                    System.out.print(" 입력 : ");
                                    product_choose = scan.nextInt();

                                    // 클릭 사운드
                                    click_sound = new Play_BG_music("click_sound.mp3", false);
                                    click_sound.start();

                                    // 리스트에서 뒤로가기 선택 시
                                    if (product_choose == 0) {
                                        System.out.println("\n뒤로 갑니다.");
                                        continue merchant_loop;
                                    }

                                    product_choose--;
                                    // 소지금이 부족한 경우
                                    if (player.having_money - appear_merchant.having_potion[product_choose].item_price < 0) {
                                        System.out.println("\n소지금이 부족합니다. 돈 벌어오세요.");
                                    } else {
                                        System.out.println("\n" + appear_merchant.having_potion[product_choose].item_name + "을 선택하셨습니다.");

                                        // 구매하려는 포션을 인벤토리 어느 슬롯에 넣을지 이벤트 반복문
                                        inventory_loop:
                                        while (true) {
                                            Play_BG_music inventory_sound = new Play_BG_music("inventory_sound.mp3", false);
                                            inventory_sound.start();

                                            System.out.println("\n인벤토리 어느 슬롯에 넣으시겠습니까? (숫자로 입력해주세요. 목록 외 선택시 처음으로 돌아갑니다.)");
                                            System.out.println("1. 1번 슬롯 : " + player.inventory.get(0).item_name);
                                            System.out.println("2. 2번 슬롯 : " + player.inventory.get(1).item_name);
                                            System.out.println("3. 3번 슬롯 : " + player.inventory.get(2).item_name);
                                            System.out.println("4. 4번 슬롯 : " + player.inventory.get(3).item_name);
                                            System.out.println("5. 5번 슬롯 : " + player.inventory.get(4).item_name);
                                            System.out.println("6. 6번 슬롯 : " + player.inventory.get(5).item_name);

                                            System.out.println("\n0. 뒤로가기");
                                            System.out.print(" 입력 : ");
                                            int inventory_choose = scan.nextInt(); // 넣으려는 인벤토리 슬롯 번호

                                            // 클릭 사운드
                                            click_sound = new Play_BG_music("click_sound.mp3", false);
                                            click_sound.start();

                                            // 포션 구매를 취소하는 경우
                                            if (inventory_choose == 0) {

                                                System.out.println("\n포션을 구매하지 않습니다. 뒤로 갑니다.");
                                                break potion_loop;
                                            }

                                            inventory_choose--;
                                            // 넣으려는 인벤토리 슬롯에 이미 포션이 있는 경우
                                            if (player.inventory.get(inventory_choose).item_name != "없음") {
                                                System.out.println("\n해당 인벤토리 슬롯은 이미 아이템이 있습니다.");
                                                System.out.println("[ " + appear_merchant.having_potion[product_choose].item_name + " ]으로 대체하시겠습니까?");
                                                System.out.println("1. 예  2. 아니요");

                                                // 대체 할지 말지 선택 번호
                                                int slot_change_choose = scan.nextInt();

                                                // 클릭 사운드
                                                click_sound = new Play_BG_music("click_sound.mp3", false);
                                                click_sound.start();

                                                // 대체한다고 선택 했을 때
                                                if (slot_change_choose == 1) {
                                                    // 돈 계산
                                                    Play_BG_music coin_effect = new Play_BG_music("coin_sound.mp3", false);
                                                    coin_effect.start();

                                                    System.out.println("\n원래 가지고 있던 " + player.inventory.get(inventory_choose).item_name + "은 상인에게 반값으로 팝니다. "
                                                            + (player.inventory.get(inventory_choose).item_price / 2) + "원을 획득합니다.");

                                                    // 플레이어 '소지금' 정보 갱신
                                                    player.having_money += (player.inventory.get(inventory_choose).item_price / 2);
                                                    player.having_money -= appear_merchant.having_potion[product_choose].item_price;

                                                    try {
                                                        Thread.sleep(300);
                                                    } catch (Exception e) { }

                                                    // 포션 사운드
                                                    potion_effect = new Play_BG_music("potion_bubble.mp3", false);
                                                    potion_effect.start();

                                                    System.out.println("\n포션을 구매하셨습니다.");
                                                    System.out.println((inventory_choose + 1) + "번 슬롯에 " + appear_merchant.having_potion[product_choose].item_name + "을 대체하여 넣습니다.");

                                                    // 플레이어의 인벤토리 갱신
                                                    player.inventory.set(inventory_choose, appear_merchant.having_potion[product_choose]);

                                                    break inventory_loop;
                                                }
                                            } else {
                                                // 돈 계산
                                                Play_BG_music coin_effect = new Play_BG_music("coin_sound.mp3", false);
                                                coin_effect.start();

                                                try {
                                                    Thread.sleep(300); // 0.3초
                                                } catch (Exception e) { }

                                                System.out.println("\n" + appear_merchant.having_potion[product_choose].item_price + "원이 차감됩니다.");
                                                player.having_money -= appear_merchant.having_potion[product_choose].item_price;

                                                // 포션 사운드
                                                potion_effect = new Play_BG_music("potion_bubble.mp3", false);
                                                potion_effect.start();

                                                System.out.println("포션을 구매하셨습니다.");
                                                System.out.println((inventory_choose + 1) + "번 슬롯에 " + appear_merchant.having_potion[product_choose].item_name + "을 넣습니다.");

                                                // 플레이어의 인벤토리 갱신
                                                player.inventory.set(inventory_choose, appear_merchant.having_potion[product_choose]);

                                                break inventory_loop;
                                            }
                                        }
                                    }
                                }

                                break;

                            case "4":
                                click_sound = new Play_BG_music("click_sound.mp3", false);
                                click_sound.start();

                                System.out.println("\n상태창 보기를 선택하셨습니다.\n");
                                // 플레이어의 상태창을 보기 위한 메서드
                                player.get_player_status();
                                break;

                            case "5":
                            case "나가기":
                                click_sound = new Play_BG_music("click_sound.mp3", false);
                                click_sound.start();

                                System.out.println("\n상점에서 나갑니다.");
                                break merchant_loop;

                            default: // 예외처리
                                System.out.println("[ 알 수 없는 말을 하며 상점을 나간다. ]");
                                break merchant_loop;
                        }
                    }

                    break;

                // 치유소 이용 이벤트
                case "2":
                    // 치유사 사운드
                    Play_BG_music female_hello_sound = new Play_BG_music("female_hello_sound.mp3", false);
                    female_hello_sound.start();

                    if(true) {
                        Play_BG_music door_effect = new Play_BG_music("door_sound.mp3", false);
                        door_effect.start();
                    }
                    System.out.println("\n[ 치유사 ] : 금액[ 500 X 레벨 ]을 지불하면 체력과 마력을 회복시켜 드릴게요. (숫자로 입력해주세요.)");
                    System.out.println("1. 네  2. 아니요");
                    System.out.print(" 입력 : ");

                    // 치유 받을지 말지 선택 번호
                    String healer_choose = scan.next();

                    Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
                    click_sound.start();

                    switch (healer_choose) {
                        case "1":
                        case "네":
                            // 이미 체력과 마력이 가득 찬 경우
                            if( (player.now_hp == player.max_hp) && (player.now_mp == player.max_mp) ) {
                                System.out.println("\n이미 체력과 마력이 가득 찼습니다. 치료소를 나갑니다.");
                                System.out.println("현재 체력 : " + player.now_hp);
                                System.out.println("현재 마력 : " + player.now_mp);

                                break;
                            }
                            else {
                                // 소지금이 부족한 경우
                                if( player.having_money < (500 * player.level) ) {
                                    System.out.println("\n소지금이 부족합니다. 돈 벌어오세요.");
                                }
                                else {
                                    Play_BG_music heal_effect = new Play_BG_music("recovery_sound.mp3", false);
                                    heal_effect.start();

                                    System.out.println("\n체력과 마력을 회복합니다. " + 500*player.level + "원을 지불합니다.");

                                    // 돈 계산
                                    Play_BG_music coin_effect = new Play_BG_music("coin_sound.mp3", false);
                                    coin_effect.start();

                                    player.having_money = player.having_money - 500 * player.level;
                                    System.out.println("\n현재 금액 : " + player.having_money);

                                    // 플레이어의 체력과 마력 정보 갱신
                                    int temp_hp = player.now_hp;
                                    int temp_mp = player.now_mp;

                                    player.now_hp = player.max_hp;
                                    player.now_mp = player.max_mp;

                                    System.out.println("체력 : " + temp_hp + " --> " + player.now_hp);
                                    System.out.println("마력 : " + temp_mp + " --> " + player.now_mp);
                                }
                            }

                            break;

                        case "2":
                        case "아니요":

                            System.out.println("\n치유 받지 않고, 치유소를 나갑니다.");
                            System.out.println("현재 체력 : " + player.now_hp);
                            System.out.println("현재 마력 : " + player.now_mp);

                            break;

                        default: // 예외처리
                            System.out.println("\n[ 알 수 없는 말을 하며 치유소를 나간다. ]");
                    }

                    break;

                case "3":
                    click_sound = new Play_BG_music("click_sound.mp3", false);
                    click_sound.start();

                    System.out.println("\n상태창 보기를 선택하셨습니다.\n");

                    // 플레이어의 상태창 보기 위한 메서드
                    player.get_player_status();
                    System.out.println();

                    break;

                // 주점 이용 이벤트
                case "4":
                    // 첫번째 루프인지 아닌지
                    first_loop = true;

                    // 주점 입장시, 소량 회복되는 체력과 마력을 위한 스레드
                    Thread heal_thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(true) {
                                    Thread.sleep(30000); // 30초
                                    if( (player.getNow_hp() == player.max_hp) && (player.getNow_mp() == player.max_mp) ) {
                                        System.out.println("\n[ 온기를 느끼며, 체력과 마력이 회복됩니다. ] : 체력 +100, 마력 +100");

                                        player.setNow_hp(player.getNow_hp() + 100);
                                        player.setNow_mp(player.getNow_mp() + 100);
                                    }
                                    else {
                                        System.out.println("\n[ 온기를 느끼지만, 체력과 마력이 이미 차 있습니다. ]");
                                    }
                                }
                            } catch (Exception e) { }
                        }
                    });

                    // 주점 안에서의 이벤트 반복문
                    pub_loop:
                    while(true) {
                        // 처음 입장시
                        if (first_loop) {
                            Play_BG_music pub_music = new Play_BG_music("pub_music.mp3", false);
                            pub_music.start();

                            System.out.println("\n[ 주점 입장시, 30초마다 체력과 마력이 소량 회복됩니다. (단, 300원의 입장료를 내야합니다.) ]");
                            System.out.println("입장 하시겠습니까? (예/아니요)");
                            String is_enter_pub = scan.next();

                            if( is_enter_pub.equals("예") ) {

                                if(player.having_money >= 300) {
                                    // 돈 계산 사운드
                                    Play_BG_music coin_effect = new Play_BG_music("coin_sound.mp3", false);
                                    coin_effect.start();

                                    player.having_money -= 300;
                                }
                                else {
                                    System.out.println("[ 주점 주인장 ] : 돈이 없으면 못 들어와 ~ ! 다음에 오시길....");

                                    break pub_loop;
                                }

                                System.out.println("\n[ 주점으로 들어갑니다. 온기가 느껴집니다. ]");

                                // 주점 입장시, 문 사운드
                                Play_BG_music door_effect = new Play_BG_music("door_sound.mp3", false);
                                door_effect.start();

                                // 주점 버프 스레드 시작
                                heal_thread.setDaemon(true);
                                heal_thread.start();

                                first_loop = false;
                            }
                            else {
                                System.out.println("\n[ 주점으로 들어가지 않습니다. ]");

                                break pub_loop;
                            }
                        }

                        System.out.println("\n[ 주점 주인장 ] : 뭐 하시려고..? (숫자로 입력해주세요.)");
                        System.out.println("1. 야바위  2. 상태창 보기  3. 나가기");
                        System.out.print(" 입력 : ");
                        String merchant_choose = scan.next(); // 주점에서 행동 선택 번호

                        // 클릭 사운드
                        click_sound = new Play_BG_music("click_sound.mp3", false);
                        click_sound.start();

                        switch(merchant_choose) {
                            // 야바위 게임 이벤트
                            case "1":
                                System.out.println("\n\n========================================================================");
                                System.out.println("[ 주점안의 야바위꾼에게 다가갑니다. ]");
                                thread_effect.sleep();
                                System.out.println("[ 공이 있는 컵의 위치를 맞춰야 합니다. ]");
                                thread_effect.sleep();
                                System.out.println("[ 소지금 내에서 베팅이 가능하며, 승리시 2배로 돌려받습니다. 화이팅!!! ]");
                                thread_effect.sleep();
                                System.out.println("========================================================================\n");

                                // 야바위 게임 이벤트 메서드
                                find_ball(player);

                                break;

                            // 상태창 보기를 선택한 경우
                            case "2":
                                System.out.println("\n상태창 보기를 선택하셨습니다.\n");

                                // 플레이어의 상태창을 보기 위한 메서드
                                player.get_player_status();
                                break;

                            // 주점 나가기를 선택한 경우
                            case "3":
                                System.out.println("\n주점에서 나갑니다....\n");

                                heal_thread.interrupt();

                                try {
                                    Thread.sleep(200); // 0.2초
                                } catch (Exception e) { }

                                break pub_loop;

                            // 예외처리
                            default:
                                System.out.println("\n[ 알 수 없는 말을 하며 주점을 나간다. ]");
                        }
                    }

                    break;

                // 마을 맵 나가는 이벤트
                case "5":
                    click_sound = new Play_BG_music("click_sound.mp3", false);
                    click_sound.start();

                    try {
                        Thread.sleep(100); // 0.1초
                    } catch (Exception e) { }

                    Play_BG_music bye_bye_sound = new Play_BG_music("bye_bye_sound.mp3", false);
                    bye_bye_sound.start();

                    System.out.println("\n[ 마을 족장 ] : 안녕히 가시게...");
                    System.out.println("\n마을에서 나갑니다.\n");

                    break villiage_loop;

                // 예외처리
                default:
                    click_sound = new Play_BG_music("click_sound.mp3", false);
                    click_sound.start();

                    System.out.println("\n[ 마을 족장 ] : 뭐라는 건가!!?? 다시 말해보게!\n");
            }
        }
        villiage_bgm.close();
    }

    // 주점 안에서 야바위 게임 이벤트 메서드
    public void find_ball(Character_combatant_player player) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random(System.currentTimeMillis());

        System.out.println("\n[ 야바위꾼 ] : 엉? 한판 할려고?? 얼마를 걸텐가???");

        int gamble_money; // 판돈

        while(true) {
            System.out.print("\n 판돈을 숫자로 입력하세요. ( 현재 보유 금액 : " + player.having_money + " )");
            System.out.print(" 입력 : ");

            Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
            click_sound.start();

            gamble_money = scan.nextInt();

            if (gamble_money > player.having_money) {
                System.out.println("\n판돈이 부족합니다. 다시 입력하세요.\n");
            }
            else {
                break;
            }
        }

        // 야바위 게임에서 찾아야 할 공 (숫자 0으로 표현)
        int ball = 0;

        // 야바위 게임에서 공이 들어갈 컵
        int cup_1 = 1;
        int cup_2 = 2;
        int cup_3 = 3;

        System.out.println("\n[ 야바위꾼 ] : 클클클...자자 골라골라~!~! (1, 2, 3 중에 공을 넣을 컵의 숫자를 입력하세요.) ");
        System.out.println("n  n  n");
        System.out.println("1  2  3");
        System.out.print(" 입력 : ");
        int cup_number = scan.nextInt(); // 선택한 컵의 번호

        // 클릭 사운드
        Play_BG_music click_sound = new Play_BG_music("click_sound.mp3", false);
        click_sound.start();

        System.out.println("\n========================================================================\n");

        // 컵 숫자 입력에 따른 이벤트 (입력한 숫자의 컵에 공을 넣는 이벤트)
        if(cup_number == 3) {
            cup_3 = ball;
            System.out.println("\ncup_3에 공이 있습니다. : 0 = ball");
        }
        else if(cup_number == 1) {
            cup_1 = ball;
            System.out.println("\ncup_1에 공이 있습니다. : 0 = ball");
        }
        else if(cup_number == 2) {
            cup_2 = ball;
            System.out.println("\ncup_2에 공이 있습니다. : 0 = ball");
        }
        else {
            System.out.println("\n[ 야바위꾼] : 에잉...그냥 내가 알아서 넣을게!!! ");
            cup_number = rand.nextInt(3) + 1;

            if(cup_number == 3) {
                cup_3 = ball;
                System.out.println("\ncup_3에 공이 있습니다. : 0 = ball");
            }
            else if(cup_number == 1) {
                cup_1 = ball;
                System.out.println("\ncup_1에 공이 있습니다. : 0 = ball");
            }
            else if(cup_number == 2) {
                cup_2 = ball;
                System.out.println("\ncup_2에 공이 있습니다. : 0 = ball");
            }
        }

        System.out.println("\n========================================================================\n");

        System.out.println(cup_1 + "  " + cup_2 + "  " + cup_3 + "     <-------- 공의 위치를 확인하세요.");

        System.out.println("\n========================================================================\n");

        try{
            Thread.sleep(2000); // 2초
        }catch(Exception e){ }

        // 랜덤으로 공이 들어갈 컵의 번호
        int shuffle_number;

        // 야바위 게임 시작, 컵 섞는 이벤트
        for(int i = 0; i < 9; i++) {

            shuffle_number = rand.nextInt(3) + 1;

            // 셔플 마다 컵 초기화
            cup_1 = 1;
            cup_2 = 2;
            cup_3 = 3;

            // 랜덤으로나온 숫자와 컵의 숫자가 같으면 공을 넣는다
            if(cup_1 == shuffle_number) {
                cup_1 = ball;
            }
            else if(cup_2 == shuffle_number) {
                cup_2 = ball;
            }
            else if(cup_3 == shuffle_number) {
                cup_3 = ball;
            }

            // 컵을 섞는 모습 출력
            System.out.println(cup_1 + "  " + cup_2 + "  "+cup_3);

            try{
                Thread.sleep(500); // 0.5초
            } catch(Exception e){ }
        }

        // 마지막 셔플 후, 안보여주기
        shuffle_number = rand.nextInt(3) + 1;

        // 셔플 마다 컵 초기화
        cup_1 = 1;
        cup_2 = 2;
        cup_3 = 3;

        // 랜덤으로나온 숫자와 컵의 숫자가 같으면 공을 넣는다
        if(cup_1 == shuffle_number) {
            cup_1 = ball;
        }
        else if(cup_2 == shuffle_number) {
            cup_2 = ball;
        }
        else if(cup_3 == shuffle_number) {
            cup_3 = ball;
        }

        System.out.println("========================================================================");

        System.out.println("n  n  n");
        System.out.println("---------");
        System.out.println("1  2  3");

        try{
            Thread.sleep(1000); // 1초
        } catch(Exception e){ }

        System.out.println("\n[ 야바위꾼 ] : 자....공이 어디에 있을까...? 잘 맞춰 보라고~~클클");
        System.out.print(" 입력 : ");

        cup_number = scan.nextInt();

        click_sound = new Play_BG_music("click_sound.mp3", false);
        click_sound.start();

        // 돈 계산 사운드
        Play_BG_music coin_effect = new Play_BG_music("coin_sound.mp3", false);
        coin_effect.start();

        if(cup_number == cup_1 | cup_number == cup_2 | cup_number == cup_3) {
            System.out.println("\n캬캬캬~ 미안하지만 틀렸어~~ ");
            System.out.println("\n[ 야바위 게임에서 졌습니다. 판돈의 잃습니다. (-" + gamble_money + "원) ]");

            player.having_money -= gamble_money;
        }
        else {
            System.out.println("\n이런...자네가 이겼구만 쩝...");
            System.out.println("\n[ 야바위 게임에서 이겼습니다. 판돈의 2배를 얻습니다. (+" + (gamble_money * 2) + "원) ]");

            player.having_money += (gamble_money * 2);
        }
    }
}
