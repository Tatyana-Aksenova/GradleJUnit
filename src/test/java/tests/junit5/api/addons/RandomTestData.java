package tests.junit5.api.addons;

import com.github.javafaker.Faker;
import tests.junit5.api.models.swager.*;
import tests.junit5.api.models.swaggerpets.Category;
import tests.junit5.api.models.swaggerpets.Pet;
import tests.junit5.api.models.swaggerpets.TagsItem;

import java.time.LocalDateTime;
import java.util.*;

public class RandomTestData {
    private static final Random random = new Random();
    private static final Faker faker = new Faker();

    public static GamesItem getRandomGame() {
        SimilarDlc similarDlc = SimilarDlc.builder()
                .isFree(false)
                .dlcNameFromAnotherGame(faker.funnyName().name())
                .build();

        DlcsItem dlcsItem = DlcsItem.builder()
                    .rating(faker.random().nextInt(10))
                    .price(faker.random().nextInt(1, 500))
                    .description(faker.funnyName().name())
                    .dlcName(faker.dragonBall().character())
                    .isDlcFree(false)
                    .similarDlc(similarDlc).build();


        Requirements requirements = Requirements.builder()
                .ramGb(faker.random().nextInt(4, 16))
                .osName("Windows")
                .hardDrive(faker.random().nextInt(30, 70))
                .videoCard("NVIDEA")
                .build();


        return GamesItem.builder()
                .requirements(requirements)
                .genre(faker.book().genre())
                .price(random.nextInt(400))
                .description(faker.funnyName().name())
                .company(faker.company().name())
                .isFree(false)
                .title(faker.beer().name())
                .rating(faker.random().nextInt(10))
                .publishDate(LocalDateTime.now().toString())
                .requiredAge(random.nextBoolean())
                .tags(Arrays.asList("shooter", "quests"))
                .dlcs(Collections.singletonList(dlcsItem))
                .build();
    }

    public static FullUser getRandomUserWithGames() {
        int randomNumber = Math.abs(random.nextInt());
        GamesItem gamesItem = getRandomGame();
        return FullUser.builder()
                .login(faker.name().username() + randomNumber)
                .pass(faker.internet().password())
                .games(Collections.singletonList(gamesItem))
                .build();
    }

    public static FullUser getRandomUser() {
        int randomNumber = Math.abs(random.nextInt());
        return FullUser.builder()
                .login("threadQATestUser" + randomNumber)
                .pass("passwordCOOL")
                .build();
    }

    public static FullUser getAdminUser() {
        return FullUser.builder()
                .login("admin")
                .pass("admin")
                .build();
    }

    public static GamesItem createRandomGame(){
        // Faker faker = new Faker();

        SimilarDlc similarDlc = SimilarDlc.builder()
                .isFree(false)
                .dlcNameFromAnotherGame(faker.funnyName().name())
                .build();

        DlcsItem dlcsItem = DlcsItem.builder()
                .rating(faker.random().nextInt(10))
                .price(faker.random().nextInt(1, 500))
                .description(faker.funnyName().name())
                .dlcName(faker.dragonBall().character())
                .isDlcFree(false)
                .similarDlc(similarDlc).build();


        Requirements requirements = Requirements.builder()
                .ramGb(faker.random().nextInt(4, 16))
                .osName("Windows")
                .hardDrive(faker.random().nextInt(30, 70))
                .videoCard("NVIDEA")
                .build();


        return GamesItem.builder()
                .requirements(requirements)
                .genre(faker.book().genre())
                .price(faker.random().nextInt(400))
                .description(faker.funnyName().name())
                .company(faker.company().name())
                .isFree(false)
                .title(faker.beer().name())
                .rating(faker.random().nextInt(10))
                .publishDate(LocalDateTime.now().toString())
                .requiredAge(faker.random().nextBoolean())
                .tags(Arrays.asList("shooter", "quests"))
                .dlcs(Collections.singletonList(dlcsItem))
                .build();
    }

    public static Pet getRandomPet() {
        int randomNumber = Math.abs(random.nextInt());
        List<TagsItem> tagsItems = new ArrayList<>();
        tagsItems.add(new TagsItem(1, "cute"));
        tagsItems.add(new TagsItem(2, "fluffy"));
        Category category = new Category("1", "cat");
        String status = "available";

        return Pet.builder()
                .name("Kuzya" + randomNumber)
                .category(category)
                .status(status)
                .tags(tagsItems)
                .build();
    }

    public static Pet getUpdatedPet(Pet pet) {

        return Pet.builder()
                .id(pet.getId())
                .name("UPD" +  pet.getName())
                .status("sold")
                .build();

    }
}
